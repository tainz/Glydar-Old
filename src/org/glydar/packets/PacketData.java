package org.glydar.packets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.glydar.paraglydar.vectors.Vec3;
import org.glydar.util.MiscUtil;

public abstract class PacketData {

	protected List<Byte> data;

	protected PacketData() {
		data = new ArrayList<Byte>();
	}

	protected PacketData(byte[] dat) {
		data = MiscUtil.toByteList(dat);
	}

	public <T> PacketData addData(T dat) {

		data.addAll(getDataList(dat));

		return this;

	}

	public <T> List<Byte> getDataList(T dat) {

		List<Byte> tmpDat = new ArrayList<Byte>();

		if (dat instanceof Float) {
			tmpDat.addAll(MiscUtil.toByteList(ByteBuffer.allocate(4)
					.order(ByteOrder.LITTLE_ENDIAN).putFloat((Float) dat)
					.array()));
		} else if (dat instanceof Long) {
			tmpDat.addAll(MiscUtil
					.toByteList(ByteBuffer.allocate(8)
							.order(ByteOrder.LITTLE_ENDIAN).putLong((Long) dat)
							.array()));
		} else if (dat instanceof Integer) {
			tmpDat.addAll(MiscUtil.toByteList(ByteBuffer.allocate(4)
					.order(ByteOrder.LITTLE_ENDIAN).putInt((Integer) dat)
					.array()));
		} else if (dat instanceof Short) {
			tmpDat.addAll(MiscUtil.toByteList(ByteBuffer.allocate(2)
					.order(ByteOrder.LITTLE_ENDIAN).putShort((Short) dat)
					.array()));
		} else if (dat instanceof String) {

			byte[] dt = ((String) dat).getBytes(Charset.forName("UTF-16LE"));

			tmpDat.addAll(MiscUtil.toByteList(ByteBuffer.allocate(4)
					.order(ByteOrder.LITTLE_ENDIAN).putInt(dt.length / 2)
					.array()));
			tmpDat.addAll(MiscUtil.toByteList(dt));

		} else if (dat instanceof Vec3) {

			Vec3 vec = (Vec3) dat;

			byte[] x = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
					.putFloat(vec.getX()).array();
			byte[] y = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
					.putFloat(vec.getY()).array();
			byte[] z = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
					.putFloat(vec.getZ()).array();

			tmpDat.addAll(MiscUtil.toByteList(x));
			tmpDat.addAll(MiscUtil.toByteList(y));
			tmpDat.addAll(MiscUtil.toByteList(z));

		} else if (dat instanceof byte[]) {
			tmpDat.addAll(MiscUtil.toByteList((byte[]) dat));
		} else if (dat instanceof Byte) {
			tmpDat.add((Byte) dat);
		} else if (dat instanceof Byte[]) {
			tmpDat.addAll(Arrays.asList((Byte[]) dat));
		}

		return tmpDat;

	}

	public void setDataAtIndex(int index, byte[] dat) {

		for (int i = 0; i < dat.length; i++) {
			data.set(index, dat[i]);
		}

	}

	public void removeDataRange(int from, int to) {

		if (from > data.size() - 1 || to > data.size() - 1)
			return;

		List<Byte> toRemove = new ArrayList<Byte>();

		for (int i = from; i < to; i++) {
			toRemove.add(data.get(i));
		}

		data.removeAll(toRemove);

	}

	public <T> void setDataAtIndex(int index, T dat) {

		List<Byte> tmp = getDataList(dat);

		for (int i = 0; i < tmp.size(); i++) {
			data.set(index + i, tmp.get(i));
		}

	}

	public byte[] getDataAtIndex(int index, int len) throws Exception {

		byte[] dat = new byte[len];

		if (dat.length > data.size())
			throw new Exception("Out of bounds!");

		for (int i = index; i < len; i++) {
			dat[i] = data.get(i);
		}

		return dat;

	}

	@SuppressWarnings("unchecked")
	public <T> T getDataAtIndex(Class<T> dataType, int index) {

		if (dataType.isAssignableFrom(Float.class)) {

			byte[] dt = MiscUtil.getPrimByteData(data, index, 4);

			return (T) new Double(ByteBuffer.wrap(dt)
					.order(ByteOrder.LITTLE_ENDIAN).getFloat());

		} else if (dataType.isAssignableFrom(Long.class)) {

			byte[] dt = MiscUtil.getPrimByteData(data, index, 8);

			return (T) new Long(ByteBuffer.wrap(dt)
					.order(ByteOrder.LITTLE_ENDIAN).getLong());

		} else if (dataType.isAssignableFrom(Integer.class)) {

			byte[] dt = MiscUtil.getPrimByteData(data, index, 4);

			return (T) new Integer(ByteBuffer.wrap(dt)
					.order(ByteOrder.LITTLE_ENDIAN).getInt());

		} else if (dataType.isAssignableFrom(Short.class)) {

			byte[] dt = MiscUtil.getPrimByteData(data, index, 2);

			return (T) new Short(ByteBuffer.wrap(dt)
					.order(ByteOrder.LITTLE_ENDIAN).getShort());

		} else if (dataType.isAssignableFrom(Vec3.class)) {

			byte[] xDat = MiscUtil.getPrimByteData(data, index, 4);
			byte[] yDat = MiscUtil.getPrimByteData(data, index + 4, 4);
			byte[] zDat = MiscUtil.getPrimByteData(data, index + 8, 4);

			float x = ByteBuffer.wrap(xDat).order(ByteOrder.LITTLE_ENDIAN)
					.getFloat();
			float y = ByteBuffer.wrap(yDat).order(ByteOrder.LITTLE_ENDIAN)
					.getFloat();
			float z = ByteBuffer.wrap(zDat).order(ByteOrder.LITTLE_ENDIAN)
					.getFloat();

			return (T) new Vec3(x, y, z);

		} else if (dataType.isAssignableFrom(String.class)) {

			byte[] sizeDt = MiscUtil.getPrimByteData(data, 0, 4);

			int size = ByteBuffer.wrap(sizeDt).order(ByteOrder.LITTLE_ENDIAN)
					.getInt();

			byte[] dt = MiscUtil.getPrimByteData(data, 4, size);

			return (T) new String(dt);

		}

		return null;

	}

	public byte[] getByteData() {

		byte[] bData = new byte[data.size()];

		for (int i = 0; i < data.size(); i++) {
			bData[i] = data.get(i).byteValue();
		}

		return bData;

	}

}
