package org.glydar.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.glydar.packets.Packet;
import org.glydar.packets.annotations.PacketStruct;
import org.glydar.paraglydar.vectors.Vec3;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf outb) throws Exception {

        ByteBuf out = outb.order(ByteOrder.LITTLE_ENDIAN);

        out.order(ByteOrder.LITTLE_ENDIAN).writeInt(msg.getId());

        HashMap<Integer, Field> fields = new HashMap<Integer, Field>();

        int len = 0;
        for (Field f : msg.getClass().getDeclaredFields()) {

            if (f.isAnnotationPresent(PacketStruct.class)) {

                PacketStruct struct = f.getAnnotation(PacketStruct.class);

                int sLen = struct.length();

                if (sLen == 0) {

                    Class<?> sType = f.getType();

                    if (sType.equals(byte.class)) {
                        len += 1;
                    } else if (sType.equals(short.class)) {
                        len += 2;
                    } else if (sType.equals(int.class)) {
                        len += 4;
                    } else if (sType.equals(float.class)) {
                        len += 4;
                    } else if (sType.equals(long.class)) {
                        len += 8;
                    }

                } else {
                    len += sLen;
                }

                fields.put(struct.index(), f);

            }

        }

        ByteBuffer buffer = ByteBuffer.allocate(len);

        for (int i = 0; i < fields.size(); i++) {

            Field field = fields.get(i);
            field.setAccessible(true);

            Class<?> sType = field.getType();

            Object obj = field.get(msg);

            if (sType.equals(byte.class)) {
                buffer.put((byte) obj);
            } else if (sType.equals(short.class)) {
                buffer.putShort((short) obj);
            } else if (sType.equals(int.class)) {
                buffer.putInt((int) obj);
            } else if (sType.equals(float.class)) {
                buffer.putFloat((float) obj);
            } else if (sType.equals(long.class)) {
                buffer.putLong((long) obj);
            } else if (sType.equals(Vec3.class)) {

                Vec3 vec = (Vec3)obj;

                buffer.putFloat(vec.getX());
                buffer.putFloat(vec.getY());
                buffer.putFloat(vec.getZ());

            }

        }

        out.order(ByteOrder.LITTLE_ENDIAN).writeBytes(buffer.order(ByteOrder.LITTLE_ENDIAN).array());

    }

}
