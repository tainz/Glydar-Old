package org.glydar.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.glydar.packets.PacketCreatorList;
import org.glydar.packets.PacketHandlerList;
import org.glydar.packets.creators.ClientVersionPacketCreator;
import org.glydar.protocol.handlers.ClientVersionPacketHandler;

public class CWServer
{
	
	private static final int PORT = 12345;
	
	private PacketCreatorList creatorList;
	private PacketHandlerList handlerList;
	
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	
	private GlydarServerInitializer initializer;
	
	public CWServer() throws Exception
	{
		
		creatorList = new PacketCreatorList();
		creatorList.addPacketCreator(new ClientVersionPacketCreator());
		
		handlerList = new PacketHandlerList();
		handlerList.addHandler(new ClientVersionPacketHandler());
		
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
	
	public synchronized PacketCreatorList getPacketCreatorList()
	{
		return new PacketCreatorList(creatorList);
	}
	
	public synchronized PacketHandlerList getPacketHandlerList()
	{
		return handlerList;
	}
	
}
