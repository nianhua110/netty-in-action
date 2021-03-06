/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler  extends SimpleChannelInboundHandler<ByteBuf> {
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    //super.channelActive(ctx);
    ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
  }

  protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
      System.out.println("Client received: "+msg.toString(CharsetUtil.UTF_8));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    super.exceptionCaught(ctx, cause);
  }
}
