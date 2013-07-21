package org.glydar.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import org.glydar.Glydar;
import org.glydar.LogFormatter;
import org.glydar.packets.PacketCreatorList;
import org.glydar.packets.PacketHandlerList;
import org.glydar.packets.creators.*;
import org.glydar.plugin.CubePluginLoader;
import org.glydar.protocol.handlers.ClientChatPacketHandler;
import org.glydar.protocol.handlers.ClientEntityEchoHandler;
import org.glydar.protocol.handlers.ClientEntityUpdatePacketHandler;
import org.glydar.protocol.handlers.ClientVersionPacketHandler;
import org.glydar.util.LogUtil;

public class CWServer {

    private static final int PORT = 12345;
    private static final int PROTOCOL_VERSION = 3;

    //TODO: Read value from an auto-generated Properties file
    private int maxPlayers = 4;

    //TODO: Read value from an auto-generated Properties file
    private int seed = 6969;
    
    private int conId = 0;

    private PacketCreatorList creatorList;
    private PacketHandlerList handlerList;

    private final Logger LOGGER = Logger.getLogger(Glydar.class.getName());
    private static LogUtil log;
    private final CubePluginLoader loader = new CubePluginLoader();

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private GlydarServerInitializer initializer;

    private Thread serverThread = new Thread(new Runnable() //TODO Move into own class or make it look nicer.
    {

        @Override
        public void run() {
        	log = new LogUtil();
        	
			log.output("Server starting on port " + PORT);

            if (bossGroup != null || workerGroup != null || initializer != null)
                stopServer();

            initializer = new GlydarServerInitializer();

            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();

            try {

                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(initializer);

                ChannelFuture cf = b.bind(PORT).sync();
                loader.loadPlugins();
                cf.channel().closeFuture().sync();
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        }

    });

    public CWServer() throws Exception {
        LOGGER.setUseParentHandlers(false);
        LogFormatter format = new LogFormatter();
        ConsoleHandler lch = new ConsoleHandler();
        lch.setFormatter(format);
        LOGGER.addHandler(lch);

        creatorList = new PacketCreatorList();
        creatorList.addPacketCreator(new ClientVersionPacketCreator());
        //creatorList.addPacketCreator(new ClientEntityUpdatePacketCreator());
        creatorList.addPacketCreator(new ClientEntityUpdateEchoCreator());
        creatorList.addPacketCreator(new ClientChatPacketCreator());
        //TODO Can't we use a Class loader or something?
        creatorList.addPacketCreator(new ClientInteractionPacketCreator());
        creatorList.addPacketCreator(new ClientHitNPCPacketCreator());
        creatorList.addPacketCreator(new ClientStealthPacketCreator());
        creatorList.addPacketCreator(new ClientShootArrowPacketCreator());
        creatorList.addPacketCreator(new ClientChunkDiscoveredPacketCreator());
        creatorList.addPacketCreator(new ClientSectorDiscoveredPacketCreator());

        
        handlerList = new PacketHandlerList();
        handlerList.addHandler(new ClientVersionPacketHandler());
        //handlerList.addHandler(new ClientEntityUpdatePacketHandler());
        //handlerList.addHandler(new ClientEntityEchoHandler());
        handlerList.addHandler(new ClientChatPacketHandler());

    }

    public void startServer() {
        serverThread.start();
    }

    public void stopServer() {
        loader.unloadPlugins();
        if (bossGroup != null && workerGroup != null) {
            bossGroup.shutdownGracefully().awaitUninterruptibly();
            workerGroup.shutdownGracefully().awaitUninterruptibly();
        }
        System.exit(0);
    }

    public synchronized PacketCreatorList getPacketCreatorList() {
        return new PacketCreatorList(creatorList);
    }

    public synchronized PacketHandlerList getPacketHandlerList() {
        return handlerList;
    }

    public Logger getLogger() {
        return LOGGER;
    }

    public List<GlydarClient> getClients() {
        return initializer.getClients();
    }

    public int getCurrentProtocolVersion() {
        return PROTOCOL_VERSION;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getSeed() {
        return seed;
    }

    public boolean isRunning() {
        return serverThread.isAlive();
    }
    
    public int incrementConId(){
    	return ++conId;
    }
}
