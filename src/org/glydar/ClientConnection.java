package org.glydar;

import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketReader;
import org.glydar.packets.PacketWriter;
import org.glydar.protocol.NetworkProtocol;
import org.glydar.protocol.Version3;
import org.glydar.protocol.clientpackets.ClientPacketCreator;
import org.glydar.protocol.clientpackets.ClientVersionPacket;
import org.glydar.protocol.serverpackets.ServerMismatchPacket;

public class ClientConnection implements Runnable
{
	
	private SocketChannel channel;
	
	private AtomicBoolean isRunning;
	
	private NetworkProtocol protocol = new Version3();
	
	private PacketReader packetReader;
	private PacketWriter packetWriter;
	
	private BlockingQueue<Packet> packetWriteQueue;
	
	private Thread readThread;
	private Thread writeThread;
	
	public ClientConnection(SocketChannel channel)
	{
		
		this.channel = channel;
		
		packetReader = new PacketReader(channel, new ClientPacketCreator());
		packetWriter = new PacketWriter(channel);
		
		packetWriteQueue = new LinkedBlockingQueue<Packet>();
		
		isRunning = new AtomicBoolean(true);
		
	}
	
	@Override
	public void run()
	{
		
		readThread = new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				
				while (isRunning.get())
				{
					
					try
					{
						
						Packet packet = packetReader.readPacket();
						
						if (packet == null)
							continue;
						
						System.out.println("Packet Read!");
						
						System.out.println("ID: " + packet.getId());
						System.out.println("Version: " + ((ClientVersionPacket) packet).getVersion());
						
						if (Glydar.getServer().getClientConnections().size() > Glydar.getServer().getMaxPlayers())
						{
							//TODO: Insert code to send a ServerFullPacket to the player!
						}
						
						ServerMismatchPacket smp = new ServerMismatchPacket();
						smp.setVersion(Glydar.getServer().getVersion());
						
						packetWriteQueue.add(smp);
						//						packetWriteQueue.add(new RawPacket(17, ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(5).array()));
						
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
				}
				
			}
		});
		
		writeThread = new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				
				while (isRunning.get())
				{
					
					try
					{
						
						Packet packet = packetWriteQueue.take();
						
						System.out.println("Writing Packet...");
						
						packetWriter.writePacket(packet);
						
					}
					catch (Exception e)
					{
					}
					
				}
				
			}
		});
		
		readThread.start();
		writeThread.start();
		
	}
	
}
