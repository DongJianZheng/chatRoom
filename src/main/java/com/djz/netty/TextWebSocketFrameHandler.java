package com.djz.netty;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.djz.dao.UserRepository;
import com.djz.pojo.ChatMessage;
import com.djz.pojo.User;
import com.djz.util.SpringUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelMatcher;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	private final ChannelGroup group;
	private static UserRepository userRepository;
	static {
		userRepository = SpringUtil.getBean(UserRepository.class);
	}
	private static User system = new User();
	static {
		system.setHeadImg("resources/img/head/gm.jpg");
		system.setName("系统消息");
	}
	
	public TextWebSocketFrameHandler(ChannelGroup group) {
		this.group = group;
	}
	
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
			ctx.pipeline().remove(HttpRequestHandler.class);
			Channel channel = ctx.channel();
			String token = channel.attr(ChatConstants.CHANNEL_TOKEN_KEY).get();
			User user = userRepository.getById(Long.valueOf(token));
			ChatMessage message = new ChatMessage(system, "用户[" + user.getName() + "]上线");
			System.out.println(new TextWebSocketFrame(JSON.toJSONString(message,SerializerFeature.DisableCircularReferenceDetect)));
			 group.flushAndWrite(message, new ChannelMatcher() {
				
				@Override
				public boolean matches(Channel channel) {
					System.out.println(channel);
					return false;
				}
			});
			group.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message,SerializerFeature.DisableCircularReferenceDetect)));
		}
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		group.add(channel);
	}

	
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		String token = channel.attr(ChatConstants.CHANNEL_TOKEN_KEY).get();
		User user = userRepository.getById(Long.valueOf(token));
		ChatMessage message = new ChatMessage(system, "用户[" + user.getName() + "]下线");
		group.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message,SerializerFeature.DisableCircularReferenceDetect)));
	}

	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		Channel channel = ctx.channel();
		
		String token = channel.attr(ChatConstants.CHANNEL_TOKEN_KEY).get();
		User from = ChatConstants.onlines.get(token);
		if(from == null) {
			group.writeAndFlush("OK");
		}else {
			ChatMessage message = new ChatMessage(from, msg.text());
			System.out.println(new TextWebSocketFrame(JSON.toJSONString(message,SerializerFeature.DisableCircularReferenceDetect)));
			group.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message,SerializerFeature.DisableCircularReferenceDetect)),new ChannelMatcher() {
				
				@Override
				public boolean matches(Channel channel) {
					String token = channel.attr(ChatConstants.CHANNEL_TOKEN_KEY).get();
					User from = ChatConstants.onlines.get(token);
					System.out.println(from.getName());
					return true;
				}
			});
		}
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		offlines(ctx);
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		offlines(ctx);
	}
	
	private void offlines(ChannelHandlerContext ctx) {
		Channel channel = ctx.channel();
		String token = channel.attr(ChatConstants.CHANNEL_TOKEN_KEY).get();
		ChatConstants.removeOnlines(token);
		
		group.remove(channel);
		ctx.close();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		offlines(ctx);
	}
}
