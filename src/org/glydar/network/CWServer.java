package org.glydar.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.glydar.packets.PacketHandlerList;
import org.glydar.protocol.handlers.ClientVersionPacketHandler;

public class CWServer
{
	
	private static final int PORT = 12345;
	
	private static final int MAX_PLAYERS = 4;
	
	private static final int SERVER_VERSION = 1;
	
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
	
	public int getVersion()
	{
		return SERVER_VERSION;
	}
	
	public int getMaxPlayers()
	{
		return MAX_PLAYERS;
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
		return handlerList;
	}
	
}
