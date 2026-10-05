package net.minecraft.entity.boss;

import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe$3;
import junit.swingui.ProgressBar;
import net.minecraft.client.renderer.chunk.ListChunkFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.DamageSource;

public class EntityDragonPart extends Entity {
   public ProgressBar field_0003;
   public ListChunkFactory field_0005;
   public EpollSocketChannel$EpollSocketUnsafe$3 field_0002;
   public IEntityMultiPart entityDragonObj;
   public TileEntityLockable field_0000;
   public String partName;

   @Override
   public void k_() {
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
   }

   @Override
   public boolean isEntityEqual(Entity var1) {
      return this == var1 || this.entityDragonObj == var1;
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return this.isEntityInvulnerable(var1) ? false : this.entityDragonObj.attackEntityFromPart(this, var1, var2);
   }

   @Override
   public boolean canBeCollidedWith() {
      return true;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
   }

   public EntityDragonPart(IEntityMultiPart var1, String var2, float var3, float var4) {
      super(var1.getWorld());
      this.setSize(var3, var4);
      this.entityDragonObj = var1;
      this.partName = var2;
   }
}
