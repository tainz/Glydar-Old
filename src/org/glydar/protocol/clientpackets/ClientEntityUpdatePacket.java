package org.glydar.protocol.clientpackets;

import org.glydar.packets.*;
import org.glydar.paraglydar.vectors.Vec3;
import org.glydar.util.ZLibUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class ClientEntityUpdatePacket extends StructuredPacket {

    private static ArrayList<PacketStructure> compressedStructures;
    private static ArrayList<PacketStructure> decompressedStructures;

    private static int nameIndex;

    static {

        compressedStructures = new ArrayList<PacketStructure>();
        decompressedStructures = new ArrayList<PacketStructure>();

        PacketStructure structure = new PacketStructure();

        structure.addDataType(new PacketDataType(Long.class)); //entity id

        PacketStructure structure1 = new PacketStructure();

        structure1.addDataType(new PacketDataType(Byte.class, true)); //data

        compressedStructures.add(structure1);
        compressedStructures.add(structure);

        PacketStructure structure3 = new PacketStructure();
        structure3.addDataType(new PacketDataType(Long.class));

        PacketStructure structure2 = new PacketStructure();

        structure2.addDataType(new PacketDataType(Long.class)); //x
        structure2.addDataType(new PacketDataType(Long.class)); //y
        structure2.addDataType(new PacketDataType(Long.class)); //z
        structure2.addDataType(new PacketDataType(Float.class)); //body roll
        structure2.addDataType(new PacketDataType(Float.class)); //body pitch
        structure2.addDataType(new PacketDataType(Float.class)); //body yaw
        structure2.addDataType(new PacketDataType(Vec3.class)); //velocity
        structure2.addDataType(new PacketDataType(Vec3.class)); //acceleration
        structure2.addDataType(new PacketDataType(Vec3.class)); //extra velocity
        structure2.addDataType(new PacketDataType(Float.class)); //look pitch
        structure2.addDataType(new PacketDataType(Integer.class)); //physics flags
        structure2.addDataType(new PacketDataType(Byte.class)); //speed flags
        structure2.addDataType(new PacketDataType(Byte.class, 3)); //skip
        structure2.addDataType(new PacketDataType(Integer.class)); //entity type
        structure2.addDataType(new PacketDataType(Byte.class)); //current mode
        structure2.addDataType(new PacketDataType(Byte.class, 3)); //skip
        structure2.addDataType(new PacketDataType(Integer.class)); //last shoot time
        structure2.addDataType(new PacketDataType(Integer.class)); //hit counter
        structure2.addDataType(new PacketDataType(Integer.class)); //last hit time

        //start appearance
        structure2.addDataType(new PacketDataType(Byte.class)); //not used 1
        structure2.addDataType(new PacketDataType(Byte.class)); //not used 2
        structure2.addDataType(new PacketDataType(Byte.class)); //hair red
        structure2.addDataType(new PacketDataType(Byte.class)); //hair green
        structure2.addDataType(new PacketDataType(Byte.class)); //hair blue
        structure2.addDataType(new PacketDataType(Byte.class, 1)); //skip
        structure2.addDataType(new PacketDataType(Byte.class)); //movement flags
        structure2.addDataType(new PacketDataType(Byte.class)); //entity flags
        structure2.addDataType(new PacketDataType(Float.class)); //scale
        structure2.addDataType(new PacketDataType(Float.class)); //bounding radius
        structure2.addDataType(new PacketDataType(Float.class)); //bounding height
        structure2.addDataType(new PacketDataType(Short.class)); //head model
        structure2.addDataType(new PacketDataType(Short.class)); //hair model
        structure2.addDataType(new PacketDataType(Short.class)); //hand model
        structure2.addDataType(new PacketDataType(Short.class)); //foot model
        structure2.addDataType(new PacketDataType(Short.class)); //body model
        structure2.addDataType(new PacketDataType(Short.class)); //back model
        structure2.addDataType(new PacketDataType(Short.class)); //shoulder model
        structure2.addDataType(new PacketDataType(Short.class)); //wing model
        structure2.addDataType(new PacketDataType(Float.class)); //head scale
        structure2.addDataType(new PacketDataType(Float.class)); //body scale
        structure2.addDataType(new PacketDataType(Float.class)); //hand scale
        structure2.addDataType(new PacketDataType(Float.class)); //foot scale
        structure2.addDataType(new PacketDataType(Float.class)); //shoulder scale
        structure2.addDataType(new PacketDataType(Float.class)); //weapon scale
        structure2.addDataType(new PacketDataType(Float.class)); //back scale
        structure2.addDataType(new PacketDataType(Float.class)); //unknown
        structure2.addDataType(new PacketDataType(Float.class)); //wing scale
        structure2.addDataType(new PacketDataType(Float.class)); //body pitch
        structure2.addDataType(new PacketDataType(Float.class)); //arm pitch
        structure2.addDataType(new PacketDataType(Float.class)); //arm roll
        structure2.addDataType(new PacketDataType(Float.class)); //arm yaw
        structure2.addDataType(new PacketDataType(Float.class)); //feet pitch
        structure2.addDataType(new PacketDataType(Float.class)); //wing pitch
        structure2.addDataType(new PacketDataType(Float.class)); //back pitch
        structure2.addDataType(new PacketDataType(Vec3.class)); //body offset
        structure2.addDataType(new PacketDataType(Vec3.class)); //head offset
        structure2.addDataType(new PacketDataType(Vec3.class)); //hand offset
        structure2.addDataType(new PacketDataType(Vec3.class)); //foot offset
        structure2.addDataType(new PacketDataType(Vec3.class)); //back offset
        structure2.addDataType(new PacketDataType(Vec3.class)); //wing offset
        //end appearance

        structure2.addDataType(new PacketDataType(Byte.class)); //flags 1
        structure2.addDataType(new PacketDataType(Byte.class)); //flags 2
        structure2.addDataType(new PacketDataType(Byte.class, 2)); //skip
        structure2.addDataType(new PacketDataType(Integer.class)); //roll time
        structure2.addDataType(new PacketDataType(Integer.class)); //stun time
        structure2.addDataType(new PacketDataType(Integer.class)); //slowed time
        structure2.addDataType(new PacketDataType(Integer.class)); //make blue time
        structure2.addDataType(new PacketDataType(Integer.class)); //speed up time
        structure2.addDataType(new PacketDataType(Float.class)); //show patch time
        structure2.addDataType(new PacketDataType(Byte.class)); //class type
        structure2.addDataType(new PacketDataType(Byte.class)); //specialization
        structure2.addDataType(new PacketDataType(Byte.class, 2)); //skip 2
        structure2.addDataType(new PacketDataType(Float.class)); //charged mp
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 1
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 2
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 3
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 4
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 5
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 6
        structure2.addDataType(new PacketDataType(Vec3.class)); //ray hit
        structure2.addDataType(new PacketDataType(Float.class)); //hp
        structure2.addDataType(new PacketDataType(Float.class)); //mp
        structure2.addDataType(new PacketDataType(Float.class)); //block power
        structure2.addDataType(new PacketDataType(Float.class)); //max hp multiplier
        structure2.addDataType(new PacketDataType(Float.class)); //shoot speed
        structure2.addDataType(new PacketDataType(Float.class)); //damage multiplier
        structure2.addDataType(new PacketDataType(Float.class)); //armor multiplier
        structure2.addDataType(new PacketDataType(Float.class)); //resi multiplier
        structure2.addDataType(new PacketDataType(Byte.class)); //not used 7
        structure2.addDataType(new PacketDataType(Byte.class)); //not used 8
        structure2.addDataType(new PacketDataType(Byte.class, 2)); //skip
        structure2.addDataType(new PacketDataType(Integer.class)); //character level
        structure2.addDataType(new PacketDataType(Integer.class)); //current xp
        structure2.addDataType(new PacketDataType(Long.class)); //parent owner
        structure2.addDataType(new PacketDataType(Integer.class)); //unknown/not used 1
        structure2.addDataType(new PacketDataType(Integer.class)); //unknown/not used 2
        structure2.addDataType(new PacketDataType(Byte.class)); //unknown/not used 3
        structure2.addDataType(new PacketDataType(Byte.class, 3)); //skip
        structure2.addDataType(new PacketDataType(Integer.class)); //unknown/not used 4
        structure2.addDataType(new PacketDataType(Integer.class)); //unknown/not used 5
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 11
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 12
        structure2.addDataType(new PacketDataType(Integer.class)); //"super weird"
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 13
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 14
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 15
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 16
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 17
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 18
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 19
        structure2.addDataType(new PacketDataType(Byte.class, 3)); //skip
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 20
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 21
        structure2.addDataType(new PacketDataType(Integer.class)); //not used 22

        //start item data
        structure2.addDataType(new PacketDataType(Byte.class)); //type
        structure2.addDataType(new PacketDataType(Byte.class)); //sub type
        structure2.addDataType(new PacketDataType(Byte.class, 2)); //skip
        structure2.addDataType(new PacketDataType(Integer.class)); //modifier
        structure2.addDataType(new PacketDataType(Integer.class)); //minus modifier
        structure2.addDataType(new PacketDataType(Byte.class)); //rarity
        structure2.addDataType(new PacketDataType(Byte.class)); //material
        structure2.addDataType(new PacketDataType(Byte.class)); //flags
        structure2.addDataType(new PacketDataType(Byte.class, 1)); //skip
        structure2.addDataType(new PacketDataType(Short.class)); //level
        structure2.addDataType(new PacketDataType(Byte.class, 2)); //skip

        //start item upgrade
        for (int i = 0; i < 32; i++) {

            structure2.addDataType(new PacketDataType(Byte.class)); //x
            structure2.addDataType(new PacketDataType(Byte.class)); //y
            structure2.addDataType(new PacketDataType(Byte.class)); //z
            structure2.addDataType(new PacketDataType(Byte.class)); //material
            structure2.addDataType(new PacketDataType(Integer.class)); //level

        }
        //end item upgrade

        structure2.addDataType(new PacketDataType(Integer.class)); //upgrade count
        //end item data

        for (int i = 0; i < 13; i++) {
            //start item data
            structure2.addDataType(new PacketDataType(Byte.class)); //type
            structure2.addDataType(new PacketDataType(Byte.class)); //sub type
            structure2.addDataType(new PacketDataType(Byte.class, 2)); //skip
            structure2.addDataType(new PacketDataType(Integer.class)); //modifier
            structure2.addDataType(new PacketDataType(Integer.class)); //minus modifier
            structure2.addDataType(new PacketDataType(Byte.class)); //rarity
            structure2.addDataType(new PacketDataType(Byte.class)); //material
            structure2.addDataType(new PacketDataType(Byte.class)); //flags
            structure2.addDataType(new PacketDataType(Byte.class, 1)); //skip
            structure2.addDataType(new PacketDataType(Short.class)); //level
            structure2.addDataType(new PacketDataType(Byte.class, 2)); //skip
            //end item data
        }

        for (int i = 0; i < 11; i++) {
            structure2.addDataType(new PacketDataType(Integer.class)); //skill
        }

        structure2.addDataType(new PacketDataType(Integer.class)); //mana cubes
        nameIndex = structure2.addDataType(new PacketDataType(String.class, 16)); //name

        decompressedStructures.add(structure2);
        decompressedStructures.add(structure3);

    }

    private int entityId;

    public ClientEntityUpdatePacket() {
        super(ClientPacketType.EntityUpdate.getId());
    }

    public void decompress() throws Exception {


        StructuredPacketData spd1 = (StructuredPacketData) data;

        StructuredPacketData spd = new StructuredPacketData(decompressedStructures);

        byte[] dat = ZLibUtil.decompress(spd1.getDataAtStructure(0));

        spd.setDataAtStructureIndex(0, 0, dat);
        spd.setDataAtStructureIndex(1, 0, spd1.getDataAtStructure(1));

        this.data = spd;

    }

    public String getName() throws Exception {

        StructuredPacketData spd = (StructuredPacketData)data;

        return new String(spd.getDataAtIndex(spd.getLengthToStructure(0) + nameIndex, 16));

    }

    public ClientEntityUpdatePacket(byte[] data) throws Exception {

        super(ClientPacketType.EntityUpdate.getId());

        StructuredPacketData spd = new StructuredPacketData(compressedStructures);

        byte[] d = new byte[data.length - PacketDataType.LongSize];
        byte[] d2 = new byte[PacketDataType.LongSize];

        for (int i = 0; i < d.length; i++) {
            d[i] = data[i];
        }

        for (int i = 0; i < d2.length; i++) {
            d2[i] = data[(data.length - PacketDataType.LongSize) + i];
        }

        spd.setDataAtStructureIndex(0, 0, d);
        spd.setDataAtStructureIndex(1, 0, d2);

        this.data = spd;

    }

    public static ArrayList<PacketStructure> getCompressedStructures() {
        return compressedStructures;
    }

    public static ArrayList<PacketStructure> getDecompressedStructures() {
        return decompressedStructures;
    }

}
