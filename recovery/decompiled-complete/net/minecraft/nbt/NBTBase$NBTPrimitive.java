package net.minecraft.nbt;

import io.netty.channel.socket.oio.OioSocketChannel;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.passive.EntityWolf$1;

public abstract class NBTBase$NBTPrimitive extends NBTBase {
   public EntityWolf$1 field_0002;
   public EntityAIFleeSun field_0001;
   public OioSocketChannel field_0000;

   public abstract float getFloat();

   public abstract byte getByte();

   public abstract int getInt();

   public abstract short getShort();

   public abstract double getDouble();

   public abstract long getLong();
}
