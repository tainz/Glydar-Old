package org.glydar.protocol.clientpackets.readers;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketReader;
import org.glydar.paraglydar.entity.Appearance;
import org.glydar.paraglydar.entity.Entity;
import org.glydar.paraglydar.entity.Item;
import org.glydar.paraglydar.entity.ItemUpgrade;
import org.glydar.paraglydar.vectors.Vec3;
import org.glydar.protocol.clientpackets.ClientEntityUpdatePacket;
import org.glydar.util.ZLibUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;

public class ClientEntityUpdatePacketReader extends PacketReader {

    @Override
    public Class<?> getPacketClass() {
        return ClientEntityUpdatePacket.class;
    }

    @Override
    public int getPacketId() {
        return ClientPacketType.EntityUpdate.getId();
    }

    @Override
    public ClientEntityUpdatePacket readPacket(ByteBuffer buffer) {

        ClientEntityUpdatePacket ceup = new ClientEntityUpdatePacket();
        Entity ent = new Entity();

        try {

            byte[] de = ZLibUtil.decompress(buffer.array());

            ByteBuffer buf = ByteBuffer.wrap(de).order(ByteOrder.LITTLE_ENDIAN);
            buf.getLong(); //skip

            long mask = buf.getLong();

            byte[] maskB = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(mask).array();

            BitSet bs = BitSet.valueOf(maskB);

            if (bs.get(0)) {
                ent.setPosition(new Vec3(buf.getLong(), buf.getLong(), buf.getLong()));
            } else {
                ent.setPosition(null);
            }

            if (bs.get(1)) {
                ent.setRotation(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));
            } else {
                ent.setRotation(null);
            }

            if (bs.get(2)) {
                ent.setVelocity(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));
            } else {
                ent.setVelocity(null);
            }

            if (bs.get(3)) {
                ent.setAcceleration(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));
            } else {
                ent.setAcceleration(null);
            }

            if (bs.get(4)) {
                ent.setExtraVelocity(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));
            } else {
                ent.setExtraVelocity(null);
            }

            if (bs.get(5)) {
                ent.setLookPitch(buf.getFloat());
            } else {
                ent.setLookPitch(null);
            }

            if (bs.get(6)) {
                ent.setPhysicsFlags(buf.getInt());
            } else {
                ent.setPhysicsFlags(null);
            }

            if (bs.get(7)) {
                ent.setSpeedFlags(buf.get());
            } else {
                ent.setSpeedFlags(null);
            }

            if (bs.get(8)) {
                ent.setEntityType(buf.getInt());
            } else {
                ent.setEntityType(null);
            }

            if (bs.get(9)) {
                ent.setCurrentMode(buf.get());
            } else {
                ent.setCurrentMode(null);
            }

            if (bs.get(10)) {
                ent.setLastShootTime(buf.getInt());
            } else {
                ent.setLastShootTime(null);
            }

            if (bs.get(11)) {
                ent.setHitCounter(buf.getInt());
            } else {
                ent.setHitCounter(null);
            }

            if (bs.get(12)) {
                ent.setLastHitTime(buf.getInt());
            } else {
                ent.setLastHitTime(null);
            }

            if (bs.get(13)) {

                Appearance appearance = new Appearance();
                appearance.setNotUsed1(buf.get());
                appearance.setNotUsed2(buf.get());
                appearance.setHairR(buf.get());
                appearance.setHairG(buf.get());
                appearance.setHairB(buf.get());
                buf.get(); //skip
                appearance.setMovementFlags(buf.get());
                appearance.setEntityFlags(buf.get());
                appearance.setScale(buf.getFloat());
                appearance.setBoundingRadius(buf.getFloat());
                appearance.setBoundingHeight(buf.getFloat());
                appearance.setHeadModel(buf.getShort());
                appearance.setHairModel(buf.getShort());
                appearance.setHandModel(buf.getShort());
                appearance.setFootModel(buf.getShort());
                appearance.setBodyModel(buf.getShort());
                appearance.setBackModel(buf.getShort());
                appearance.setShoulderModel(buf.getShort());
                appearance.setWingModel(buf.getShort());
                appearance.setHeadScale(buf.getFloat());
                appearance.setBodyScale(buf.getFloat());
                appearance.setHandScale(buf.getFloat());
                appearance.setFootScale(buf.getFloat());
                appearance.setShoulderScale(buf.getFloat());
                appearance.setWeaponScale(buf.getFloat());
                appearance.setBackScale(buf.getFloat());
                appearance.setUnknown(buf.getFloat());
                appearance.setWingScale(buf.getFloat());
                appearance.setBodyPitch(buf.getFloat());
                appearance.setArmPitch(buf.getFloat());
                appearance.setArmRoll(buf.getFloat());
                appearance.setArmYaw(buf.getFloat());
                appearance.setFeetPitch(buf.getFloat());
                appearance.setWingPitch(buf.getFloat());
                appearance.setBackPitch(buf.getFloat());
                appearance.setBodyOffset(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));
                appearance.setHeadOffset(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));
                appearance.setHandOffset(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));
                appearance.setFootOffset(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));
                appearance.setBackOffset(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));
                appearance.setWingOffset(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));

                ent.setAppearance(appearance);

            } else {
                ent.setAppearance(null);
            }

            if (bs.get(14)) {
                ent.setFlags1(buf.get());
                ent.setFlags2(buf.get());
            } else {
                ent.setFlags1(null);
                ent.setFlags2(null);
            }

            if (bs.get(15)) {
                ent.setRollTime(buf.getInt());
            } else {
                ent.setRollTime(null);
            }

            if (bs.get(16)) {
                ent.setStunTime(buf.getInt());
            } else {
                ent.setStunTime(null);
            }

            if (bs.get(17)) {
                ent.setSlowedTime(buf.getInt());
            } else {
                ent.setSlowedTime(null);
            }

            if (bs.get(18)) {
                ent.setMakeBlueTime(buf.getInt());
            } else {
                ent.setMakeBlueTime(null);
            }

            if (bs.get(19)) {
                ent.setSpeedUpTime(buf.getInt());
            } else {
                ent.setSpeedUpTime(null);
            }

            if (bs.get(20)) {
                ent.setShowPatchTime(buf.getFloat());
            } else {
                ent.setShowPatchTime(null);
            }

            if (bs.get(21)) {
                ent.setClassType(buf.get());
            } else {
                ent.setClassType(null);
            }

            if (bs.get(22)) {
                ent.setSpecialization(buf.get());
            } else {
                ent.setSpecialization(null);
            }

            if (bs.get(23)) {
                ent.setChargedMP(buf.getFloat());
            } else {
                ent.setChargedMP(null);
            }

            if (bs.get(24)) {
                ent.setNot_used1(buf.getInt());
                ent.setNot_used2(buf.getInt());
                ent.setNot_used3(buf.getInt());
            } else {
                ent.setNot_used1(null);
                ent.setNot_used2(null);
                ent.setNot_used3(null);
            }

            if (bs.get(25)) {
                ent.setNot_used4(buf.getInt());
                ent.setNot_used5(buf.getInt());
                ent.setNot_used6(buf.getInt());
            } else {
                ent.setNot_used4(null);
                ent.setNot_used5(null);
                ent.setNot_used6(null);
            }

            if (bs.get(26)) {
                ent.setRayHit(new Vec3(buf.getFloat(), buf.getFloat(), buf.getFloat()));
            } else {
                ent.setRayHit(null);
            }

            if (bs.get(27)) {
                ent.setHp(buf.getFloat());
            } else {
                ent.setHp(null);
            }

            if (bs.get(28)) {
                ent.setMp(buf.getFloat());
            } else {
                ent.setMp(null);
            }

            if (bs.get(29)) {
                ent.setBlockPower(buf.getFloat());
            } else {
                ent.setBlockPower(null);
            }

            if (bs.get(30)) {
                ent.setMaxHPMultiplier(buf.getFloat());
                ent.setShootSpeed(buf.getFloat());
                ent.setDamageMultiplier(buf.getFloat());
                ent.setArmorMultiplier(buf.getFloat());
                ent.setResistanceMultiplier(buf.getFloat());
            } else {
                ent.setMaxHPMultiplier(null);
                ent.setShootSpeed(null);
                ent.setDamageMultiplier(null);
                ent.setArmorMultiplier(null);
                ent.setResistanceMultiplier(null);
            }

            if (bs.get(31)) {
                ent.setNot_used7(buf.get());
            } else {
                ent.setNot_used7(null);
            }

            if (bs.get(32)) {
                ent.setNot_used8(buf.get());
            } else {
                ent.setNot_used8(null);
            }

            if (bs.get(33)) {
                ent.setLevel(buf.getInt());
            } else {
                ent.setLevel(null);
            }

            if (bs.get(34)) {
                ent.setCurrentXP(buf.getInt());
            } else {
                ent.setCurrentXP(null);
            }

            if (bs.get(35)) {
                ent.setParentOwner(buf.getLong());
            } else {
                ent.setParentOwner(null);
            }

            if (bs.get(36)) {
                ent.setUnknown_or_not_used1(buf.getInt());
                ent.setUnknown_or_not_used2(buf.getInt());
            } else {
                ent.setUnknown_or_not_used1(null);
                ent.setUnknown_or_not_used2(null);
            }

            if (bs.get(37)) {
                ent.setUnknown_or_not_used3(buf.get());
            } else {
                ent.setUnknown_or_not_used3(null);
            }

            if (bs.get(38)) {
                ent.setUnknown_or_not_used4(buf.getInt());
            } else {
                ent.setUnknown_or_not_used4(null);
            }

            if (bs.get(39)) {
                ent.setUnknown_or_not_used5(buf.getInt());
                ent.setNot_used11(buf.getInt());
                ent.setNot_used12(buf.getInt());
            } else {
                ent.setUnknown_or_not_used5(null);
                ent.setNot_used11(null);
                ent.setNot_used12(null);
            }

            if (bs.get(40)) {
                ent.setNot_used13(buf.getInt());
                ent.setNot_used14(buf.getInt());
                ent.setNot_used15(buf.getInt());
                ent.setNot_used15(buf.getInt());
                ent.setNot_used16(buf.getInt());
                ent.setNot_used17(buf.getInt());
                ent.setNot_used18(buf.getInt());
            } else {
                ent.setNot_used13(null);
                ent.setNot_used14(null);
                ent.setNot_used15(null);
                ent.setNot_used15(null);
                ent.setNot_used16(null);
                ent.setNot_used17(null);
                ent.setNot_used18(null);
            }

            if (bs.get(41)) {
                ent.setNot_used20(buf.getInt());
                ent.setNot_used21(buf.getInt());
                ent.setNot_used22(buf.getInt());
            } else {
                ent.setNot_used20(null);
                ent.setNot_used21(null);
                ent.setNot_used22(null);
            }

            if (bs.get(42)) {
                ent.setNot_used19(buf.get());
            } else {
                ent.setNot_used19(null);
            }

            if (bs.get(43)) {

                Item item = new Item();

                item.setType(buf.get());
                item.setSubType(buf.get());
                buf.getShort(); //skip
                item.setModifier(buf.getInt());
                item.setMinusModifier(buf.getInt());
                item.setRarity(buf.get());
                item.setMaterial(buf.get());
                item.setFlags(buf.get());
                buf.get(); //skip
                item.setLevel(buf.getShort());
                buf.getShort();

                ItemUpgrade[] iu = new ItemUpgrade[32];

                for (int i = 0; i < 32; i++) {

                    iu[i] = new ItemUpgrade();
                    iu[i].setX(buf.get());
                    iu[i].setY(buf.get());
                    iu[i].setZ(buf.get());
                    iu[i].setMaterial(buf.get());
                    iu[i].setLevel(buf.getInt());

                }

                item.setUpgrades(iu);
                item.setUpgradeCount(buf.getInt());

                ent.setItemData(item);

            } else {
                ent.setItemData(null);
            }

            if (bs.get(44)) {

                Item[] eq = new Item[13];

                for (int i = 0; i < 13; i++) {

                    Item item = new Item();

                    item.setType(buf.get());
                    item.setSubType(buf.get());
                    buf.getShort(); //skip
                    item.setModifier(buf.getInt());
                    item.setMinusModifier(buf.getInt());
                    item.setRarity(buf.get());
                    item.setMaterial(buf.get());
                    item.setFlags(buf.get());
                    buf.get(); //skip
                    item.setLevel(buf.getShort());
                    buf.getShort();

                    eq[i] = item;
                }

                ent.setEquipment(eq);

            } else {
                ent.setEquipment(null);
            }

            if (bs.get(45)) {

                byte[] strB = new byte[16];

                ent.setName(new String(strB));

            } else {
                ent.setName(null);
            }

            if (bs.get(46)) {

                Integer[] skills = new Integer[11];

                for (int i = 0; i < skills.length; i++) {
                    skills[i] = buf.getInt();
                }

                ent.setSkills(skills);

            } else {
                ent.setSkills(null);
            }

            if (bs.get(47)) {
                ent.setIceBlockFour(buf.getInt());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        ceup.setEntity(ent);

        return ceup;

    }
}
