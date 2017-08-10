/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.omg.CORBA.PUBLIC_MEMBER;

public class EchoService {
  private final int port;

  public EchoService(int port) {
    this.port = port;
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.print("Usage: " + EchoService.class.getSimpleName() + " <port>");
    }
    int port = Integer.parseInt(args[0]);
    new EchoService(port).start();


  }

  public void start() throws Exception {
    final EchoServiceHandler serviceHandler = new EchoServiceHandler();
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      {
        ServerBootstrap b = new ServerBootstrap();
        b.group(group)
            .channel(NioServerSocketChannel.class)
            .localAddress(port)
            .childHandler(new ChannelInitializer<SocketChannel>() {
              protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(serviceHandler);
              }
            });
        ChannelFuture f = b.bind().sync();
        f.channel().closeFuture().sync();
      }
    } finally {
      group.shutdownGracefully().sync();
    }
  }
}
