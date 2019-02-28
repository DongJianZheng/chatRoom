package com.djz;

import java.net.InetSocketAddress;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.djz.config.bean.UploadConfig;
import com.djz.netty.ChatServer;

import io.netty.channel.ChannelFuture;

@EnableConfigurationProperties(UploadConfig.class)
@SpringBootApplication
public class NettyWebSocketServerApplication implements CommandLineRunner{
	@Autowired
	private ChatServer chatServer;

    public static void main(String[] args) {
        SpringApplication.run(NettyWebSocketServerApplication.class, args);
    }
    
    @Bean
    public ChatServer chatServer() {
    	return new ChatServer();
    }

	@Override
	public void run(String... args) throws Exception {
		InetSocketAddress address = new InetSocketAddress("192.168.10.59", 9090);
		ChannelFuture future = chatServer.start(address);
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				chatServer.destroy();
			}
		});
		
		future.channel().closeFuture().syncUninterruptibly();
	}
	


}