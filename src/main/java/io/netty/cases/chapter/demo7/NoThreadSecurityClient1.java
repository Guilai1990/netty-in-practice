/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.cases.chapter.demo7;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by ���ַ� on 2018/8/11.
 */
public class NoThreadSecurityClient1 {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "18087"));
    static final int MSG_SIZE = 256;

    public void run() throws Exception  {
			connect();
    }
    
    public void connect() throws Exception
    {
        EventLoopGroup group = new NioEventLoopGroup(8);
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new LoggingHandler());
                     ch.pipeline().addLast(new NoThreadSecurityClientHandler());
                     ch.pipeline().addLast(new NoThreadSecurityClientHandler());
                     ch.pipeline().addLast(new NoThreadSecurityClientHandler());
                     ch.pipeline().addLast(new NoThreadSecurityClientHandler());

//                     ch.pipeline().addLast(new SharableClientHandler());
                     //ch.pipeline().addLast(new IotCarsClientHandler());
                 }
             });
        ChannelFuture f = null;
        for(int i = 0; i < 8; i++)
        {
            f = b.connect(HOST, PORT).sync();
        }
        f.channel().closeFuture().sync();
        group.shutdownGracefully();
        }

    public static void main(String[] args) throws Exception {
        new NoThreadSecurityClient1().run();
    }
}