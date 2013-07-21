package org.glydar.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.glydar.packets.Packet;

import java.nio.ByteOrder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf outb) throws Exception {

    	ByteBuf out = outb.order(ByteOrder.LITTLE_ENDIAN);
    	
        out.order(ByteOrder.LITTLE_ENDIAN).writeInt(msg.getId());

        if (msg.getData() != null) {
            out.order(ByteOrder.LITTLE_ENDIAN).writeBytes(msg.getData().getByteData());
        }

    }

}
