package org.glydar.protocol.clientpackets;

import org.glydar.item.Item;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.annotations.PacketStruct;
import org.glydar.paraglydar.entity.Appearance;
import org.glydar.paraglydar.entity.Entity;
import org.glydar.paraglydar.vectors.Vec3;

public class ClientEntityUpdatePacket extends Packet {

    @PacketStruct(index = 0, dynamicLength = true)
    private byte data;

    @PacketStruct(index = 1)
    private long entityId;

    private Entity entity;

    private long id;
    private byte mask;

    private Vec3 position;
    private Vec3 rotation;
    private Vec3 velocity;
    private Vec3 acceleration;
    private Vec3 extraVelocity;
    private float lookPitch;
    private int physicsFlags;
    private byte speedFlags;
    private int entityType;
    private byte currentMode;
    private int lastShootTime;
    private int hitCounter;
    private int lastHitTime;
    private Appearance appearance;
    private byte flags1;
    private byte flags2;
    private int rollTime;
    private int stunTime;
    private int slowedTime;
    private int makeBlueTime;
    private int speedUpTime;
    private float showPatchTime;
    private byte classType;
    private byte specialization;
    private float chargedMP;
    private Vec3 rayHit;
    private float hp;
    private float mp;
    private float blockPower;
    private float maxHPMultiplier;
    private float shootSpeed;
    private float damageMultiplier;
    private float armorMultiplier;
    private float resistanceMultiplier;
    private int level;
    private int currentXP;
    private Item itemData;
    private Item[] equipment;
    private int iceBlockFour;
    private int[] skills;
    private String name;

    private int unknown_or_not_used1;
    private int unknown_or_not_used2;
    private byte unknown_or_not_used3;
    private int unknown_or_not_used4;
    private int unknown_or_not_used5;
    private int not_used1;
    private int not_used2;
    private int not_used3;
    private int not_used4;
    private int not_used5;
    private int not_used6;
    private byte not_used7;
    private byte not_used8;
    private long parentOwner;
    private int not_used11;
    private int not_used12;
    private int not_used13;
    private int not_used14;
    private int not_used15;
    private int not_used16;
    private int not_used17;
    private int not_used18;
    private int not_used20;
    private int not_used21;
    private int not_used22;
    private byte not_used19;

    public ClientEntityUpdatePacket() {
        super(ClientPacketType.EntityUpdate.getId());
    }

    public void setEntity(Entity ent) {
        this.entity = ent;
    }

    public Entity getEntity() {
        return entity;
    }

}
