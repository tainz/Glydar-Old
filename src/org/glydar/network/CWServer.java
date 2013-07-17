package org.glydar.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.glydar.packets.PacketHandlerList;
import org.glydar.protocol.handlers.ClientVersionPacketHandler;

public class CWServer
{
	
	private static final int PORT = 12345;
	
	private static PacketHandlerList handlerList;
	
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	
	private GlydarServerInitializer initializer;
	
	static
	{
		
		handlerList = new PacketHandlerList();
		
		try
		{
			
			handlerList.addHandler(new ClientVersionPacketHandler());
			
		}
		catch (Exception e)
		{
			//log here
		}
		
	}
	
	public void startServer()
	{
		
		new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				
				if (bossGroup != null || workerGroup != null || initializer != null)
					stopServer();
				
				initializer = new GlydarServerInitializer();
				
				bossGroup = new NioEventLoopGroup();
				workerGroup = new NioEventLoopGroup();
				
				try
				{
					
					ServerBootstrap b = new ServerBootstrap();
					b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(initializer);
					
					b.bind(PORT).sync().channel().closeFuture().sync();
					
				}
				catch (Exception e)
				{
					
				}
				finally
				{
					bossGroup.shutdownGracefully();
					workerGroup.shutdownGracefully();
				}
				
			}
			
		}).start();
		
	}
	
	public void stopServer()
	{
		if (bossGroup != null && workerGroup != null)
		{
			bossGroup.shutdownGracefully().awaitUninterruptibly();
			workerGroup.shutdownGracefully().awaitUninterruptibly();
		}
	}
	
	public synchronized PacketHandlerList getHandlerList()
	{
		return new PacketHandlerList(handlerList);
	}
	
}
