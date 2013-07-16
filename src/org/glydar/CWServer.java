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

public class CWServer implements Runnable
{
	
	private static final int PORT = 12345;
	
	private ServerSocketChannel ssChannel;
	
	private List<ClientConnection> clients;
	
	public CWServer()
	{
		clients = new ArrayList<ClientConnection>();
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
			SelectionKey skey = ssChannel.register(sel, SelectionKey.OP_ACCEPT);
			
			while (true)
			{
				
				sel.select();
				
				Iterator selectedKeys = sel.selectedKeys().iterator();
				
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
