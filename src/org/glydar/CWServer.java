package org.glydar;

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

public class CWServer implements Runnable
{
	
	private static final int PORT = 12345;
	
	private ServerSocketChannel ssChannel;
	
	private AtomicBoolean isRunning;
	
	private List<ClientConnection> clients;
	
	public CWServer()
	{
		clients = new ArrayList<ClientConnection>();
		isRunning = new AtomicBoolean(true);
	}
	
	public void stopServer()
	{
		isRunning.set(false);
	}
	
	@Override
	public void run()
	{
		
		try
		{
			
			System.out.println("Starting Server...");
			
			Selector sel = Selector.open();
			ssChannel = ServerSocketChannel.open();
			
			ssChannel.configureBlocking(false);
			ssChannel.socket().bind(new InetSocketAddress(PORT));
			ssChannel.register(sel, SelectionKey.OP_ACCEPT);
			
			while (isRunning.get())
			{
				
				sel.select();
				
				Iterator<SelectionKey> selectedKeys = sel.selectedKeys().iterator();
				
				while (selectedKeys.hasNext())
				{
					SelectionKey key = (SelectionKey) selectedKeys.next();
					selectedKeys.remove();
					
					if (!key.isValid())
					{
						continue;
					}
					
					if (key.isAcceptable())
					{
						
						ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
						
						SocketChannel socketChannel = serverSocketChannel.accept();
						Socket socket = socketChannel.socket();
						socketChannel.configureBlocking(true);
						
						//						socketChannel.register(sel, SelectionKey.OP_READ);
						
						ClientConnection cc = new ClientConnection(socketChannel);
						new Thread(cc).run();
						
						clients.add(cc);
						
					}
					
				}
				
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
}
