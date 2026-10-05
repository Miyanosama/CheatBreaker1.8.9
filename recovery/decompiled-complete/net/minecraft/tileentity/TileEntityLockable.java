package net.minecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase$1;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public abstract class TileEntityLockable extends TileEntity implements IInteractionObject, ILockableContainer {
   public StatBase$1 field_0000;
   public LockCode code = LockCode.EMPTY_CODE;

   @Override
   public void setLockCode(LockCode var1) {
      this.code = var1;
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.u_() ? new ChatComponentText(this.z_()) : new ChatComponentTranslation(this.z_()));
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      if (this.code != null) {
         this.code.toNBT(var1);
      }
   }

   @Override
   public LockCode getLockCode() {
      return this.code;
   }

   @Override
   public boolean B_() {
      return this.code != null && !this.code.isEmpty();
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.code = LockCode.fromNBT(var1);
   }
}
