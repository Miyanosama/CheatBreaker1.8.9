package net.minecraft.tileentity;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IInteractionObject;
import recovered.unidentified.UnidentifiedClass1290;

public class TileEntityEnchantmentTable extends TileEntity implements IInteractionObject, ITickable {
   public float field_0006;
   public float field_0012;
   public float field_0005;
   public float field_0010;
   public float field_0004;
   public float field_0007;
   public UnidentifiedClass1290 field_0011;
   public float field_0002;
   public static Random rand = new Random();
   public int tickCount;
   public String customName;
   public float field_0009;
   public float field_0008;

   @Override
   public String z_() {
      return this.u_() ? this.customName : "container.enchant";
   }

   @Override
   public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
      return new ContainerEnchantment(var1, this.b, this.c);
   }

   public void setCustomName(String var1) {
      this.customName = var1;
   }

   @Override
   public String getGuiID() {
      return "minecraft:enchanting_table";
   }

   @Override
   public boolean u_() {
      return this.customName != null && this.customName.length() > 0;
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      if (var1.hasKey("CustomName", 8)) {
         this.customName = var1.getString("CustomName");
      }
   }

   @Override
   public void update() {
      this.field_0008 = this.field_0010;
      this.field_0005 = this.field_0012;
      EntityPlayer var1 = this.b.getClosestPlayer(this.c.getX() + 0.5F, this.c.getY() + 0.5F, this.c.getZ() + 0.5F, 3.0);
      if (var1 != null) {
         double var2 = var1.s - (this.c.getX() + 0.5F);
         double var4 = var1.u - (this.c.getZ() + 0.5F);
         this.field_0004 = (float)MathHelper.atan2(var4, var2);
         this.field_0010 += 0.1F;
         if (this.field_0010 < 0.5F || rand.nextInt(40) == 0) {
            float var6 = this.field_0009;

            do {
               this.field_0009 = this.field_0009 + (rand.nextInt(4) - rand.nextInt(4));
            } while (var6 == this.field_0009);
         }
      } else {
         this.field_0004 += 0.02F;
         this.field_0010 -= 0.1F;
      }

      while (this.field_0012 >= (float) Math.PI) {
         this.field_0012 -= (float) (Math.PI * 2);
      }

      while (this.field_0012 < (float) -Math.PI) {
         this.field_0012 += (float) (Math.PI * 2);
      }

      while (this.field_0004 >= (float) Math.PI) {
         this.field_0004 -= (float) (Math.PI * 2);
      }

      while (this.field_0004 < (float) -Math.PI) {
         this.field_0004 += (float) (Math.PI * 2);
      }

      float var7 = this.field_0004 - this.field_0012;

      while (var7 >= (float) Math.PI) {
         var7 -= (float) (Math.PI * 2);
      }

      while (var7 < (float) -Math.PI) {
         var7 += (float) (Math.PI * 2);
      }

      this.field_0012 += var7 * 0.4F;
      this.field_0010 = MathHelper.clamp_float(this.field_0010, 0.0F, 1.0F);
      this.tickCount++;
      this.field_0007 = this.field_0006;
      float var3 = (this.field_0009 - this.field_0006) * 0.4F;
      float var9 = 0.2F;
      var3 = MathHelper.clamp_float(var3, -var9, var9);
      this.field_0002 = this.field_0002 + (var3 - this.field_0002) * 0.9F;
      this.field_0006 = this.field_0006 + this.field_0002;
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      if (this.u_()) {
         var1.setString("CustomName", this.customName);
      }
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.u_() ? new ChatComponentText(this.z_()) : new ChatComponentTranslation(this.z_()));
   }
}
