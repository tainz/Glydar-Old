package org.glydar.packets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StructuredPacketData extends PacketData {

    private HashMap<Integer, PacketStructure> packetStructures;

    public StructuredPacketData(PacketStructure... structures) {

        super();

        packetStructures = new HashMap<Integer, PacketStructure>();

        for (int i = 0; i < structures.length; i++) {

            PacketStructure newStructure = new PacketStructure(structures[i]);

            packetStructures.put(i, newStructure);

            for (PacketDataType dType : newStructure.getDataTypes()) {

                Byte[] emptyDat = new Byte[dType.getLength()];

                for (int x = 0; x < emptyDat.length; x++) {
                    emptyDat[x] = 0;
                }

                data.addAll(Arrays.asList(emptyDat));

            }

        }

    }

    public StructuredPacketData(ArrayList<PacketStructure> structures) {
        this(structures.toArray(new PacketStructure[structures.size()]));
    }

    public byte[] getDataAtStructure(int structureIndex) throws Exception {


        PacketStructure structure = getPacketStructure(structureIndex);

        return getDataAtIndex(getLengthToStructure(structureIndex), structure.getTotalLength());

    }

    public <T> StructuredPacketData setDataAtStructureIndex(int structureIndex, int index, T dat) {

        List<Byte> bytes = getDataList(dat);

        PacketStructure packetStructure = packetStructures.get(structureIndex);

        int bIndex = getLengthToStructure(structureIndex) + packetStructure.getLengthFromIndex(index);

        PacketDataType dType = packetStructure.getDataTypeAtIndex(index);

        if (dType.isDynamicLength()) {

            data.addAll(bIndex, bytes);

            dType.setLength(bytes.size());
            dType.setDynamicLength(false);

        } else {

            if (dType.getLength() != bytes.size()) {

                removeDataRange(bIndex, dType.getLength());

                if (bIndex < data.size())
                    data.addAll(bIndex, bytes);
                else
                    data.addAll(bytes);

            } else {
                setDataAtIndex(bIndex, dat);
            }

        }

        return this;

    }

    public <T> T getStructuredDataAtIndex(Class<T> dataType, int structureIndex, int index) throws Exception {

        PacketStructure structure = packetStructures.get(structureIndex);

        return (T) getDataAtIndex(dataType, getLengthToStructure(structureIndex) + structure.getLengthFromIndex(index));

    }

    public PacketStructure getPacketStructure(int index) {
        return packetStructures.get(index);
    }

    public int getStructureCount() {
        return packetStructures.size();
    }

    private int getLengthToStructure(int structureIndex) {

        int ctr = 0;
        for (int i = 0; i < packetStructures.size(); i++) {

            PacketStructure structure = packetStructures.get(i);

            if (structureIndex == i)
                return ctr;

            for (int x = 0; x < structure.getDataTypes().size(); x++) {
                ctr += structure.getDataTypes().get(i).getLength();
            }

        }

        return 0;

    }

}
