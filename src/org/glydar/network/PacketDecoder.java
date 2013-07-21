package org.glydar.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.MessageList;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.ByteOrder;

import org.glydar.Glydar;
import org.glydar.packets.IPacketCreator;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;

public class PacketDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf inb,
			MessageList<Object> out) throws Exception {

		ByteBuf in = inb.order(ByteOrder.LITTLE_ENDIAN);

		if (in.readableBytes() < 4)
			return;

		in.markReaderIndex();

		int id = in.readInt();

		if (id != 0) {
			Glydar.getServer().getLogger().info("Got Packet ID " + id + "!");
		}

		IPacketCreator creator = Glydar.getServer().getPacketCreatorList()
				.getCreatorWithId(id);

		if (creator == null) {

			in.markReaderIndex(); // Sets new 0 to current position

			return;

		}

		int len = 0;

		for (PacketStructure structure : creator.getStructures()) {

			len += structure.getTotalLength();

			for (PacketDataType pdt : structure.getDataTypes()) {
				if (pdt.isDynamicLength()) {
					len += PacketDataType.IntegerSize;
				}
			}

			if (in.readableBytes() < len) {

				in.resetReaderIndex();

				return;

			}

			for (PacketDataType pdt : structure.getDataTypes()) {

				if (pdt.isDynamicLength()) {

					/*
					 * Read next integer, which *should* contain the size of the
					 * dynamic data type. This would, for example, be used for
					 * chat messages
					 */
					int dLen = in.readInt();

					// TODO Change to PROPERLY handle types with mixed data
					if (!pdt.getDataType().isAssignableFrom(String.class)) {
						len += dLen - 4;
					} else {
						len += dLen * 2 - 4;
					}

					// Glydar.getServer().getLogger().info("Discovered length "+len+" dLength "+dLen);

				}

			}

			if (in.readableBytes() < len) {

				in.resetReaderIndex();

				return;

			}

		}

		if (in.readableBytes() < len) {

			in.resetReaderIndex();

			return;

		}

		byte[] data = new byte[len];

		in.readBytes(data);

		out.add(creator.createPacket(data));

	}

}
