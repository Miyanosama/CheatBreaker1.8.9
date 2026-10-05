package net.minecraft.client.particle;

import net.minecraft.block.BlockHugeMushroom$EnumType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.world.World;
import org.java_websocket.handshake.HandshakedataImpl1;

public class EntityLavaFX$Factory implements IParticleFactory {
   public BlockHugeMushroom$EnumType field_0001;
   public EnumCreatureAttribute field_0002;
   public HandshakedataImpl1 field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityLavaFX(var2, var3, var5, var7);
   }
}
