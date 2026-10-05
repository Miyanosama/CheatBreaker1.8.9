package net.minecraft.entity.ai;

import io.netty.handler.traffic.ChannelTrafficShapingHandler$1;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecart$EnumMinecartType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.world.World;

public class EntityMinecartMobSpawner extends EntityMinecart {
   public MobSpawnerBaseLogic mobSpawnerLogic = new EntityMinecartMobSpawner$1(this);
   public BlockStateHelper field_0002;
   public ChannelTrafficShapingHandler$1 field_0000;

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      this.mobSpawnerLogic.method_06493(var1);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.mobSpawnerLogic.method_06497(var1);
   }

   @Override
   public IBlockState getDefaultDisplayTile() {
      return Blocks.mob_spawner.getDefaultState();
   }

   public EntityMinecartMobSpawner(World var1) {
      super(var1);
   }

   public EntityMinecartMobSpawner(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      this.mobSpawnerLogic.setDelayToMin(var1);
   }

   public MobSpawnerBaseLogic func_98039_d() {
      return this.mobSpawnerLogic;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      this.mobSpawnerLogic.updateSpawner();
   }

   @Override
   public EntityMinecart$EnumMinecartType getMinecartType() {
      return EntityMinecart$EnumMinecartType.SPAWNER;
   }
}
