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

public class TileEntityEnchantmentTable extends TileEntity implements IInteractionObject, ITickable {
   public float recoveredField1817;
   public float recoveredField1818;
   public float recoveredField1819;
   public float recoveredField1820;
   public float recoveredField1821;
   public float recoveredField1822;
   public float recoveredField1823;
   public static Random rand = new Random();
   public int tickCount;
   public String customName;
   public float recoveredField1824;
   public float recoveredField1825;

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
      this.recoveredField1825 = this.recoveredField1820;
      this.recoveredField1819 = this.recoveredField1818;
      EntityPlayer var1 = this.b.getClosestPlayer(this.c.getX() + 0.5F, this.c.getY() + 0.5F, this.c.getZ() + 0.5F, 3.0);
      if (var1 != null) {
         double var2 = var1.s - (this.c.getX() + 0.5F);
         double var4 = var1.u - (this.c.getZ() + 0.5F);
         this.recoveredField1821 = (float)MathHelper.atan2(var4, var2);
         this.recoveredField1820 += 0.1F;
         if (this.recoveredField1820 < 0.5F || rand.nextInt(40) == 0) {
            float var6 = this.recoveredField1824;

            do {
               this.recoveredField1824 = this.recoveredField1824 + (rand.nextInt(4) - rand.nextInt(4));
            } while (var6 == this.recoveredField1824);
         }
      } else {
         this.recoveredField1821 += 0.02F;
         this.recoveredField1820 -= 0.1F;
      }

      while (this.recoveredField1818 >= (float) Math.PI) {
         this.recoveredField1818 -= (float) (Math.PI * 2);
      }

      while (this.recoveredField1818 < (float) -Math.PI) {
         this.recoveredField1818 += (float) (Math.PI * 2);
      }

      while (this.recoveredField1821 >= (float) Math.PI) {
         this.recoveredField1821 -= (float) (Math.PI * 2);
      }

      while (this.recoveredField1821 < (float) -Math.PI) {
         this.recoveredField1821 += (float) (Math.PI * 2);
      }

      float var7 = this.recoveredField1821 - this.recoveredField1818;

      while (var7 >= (float) Math.PI) {
         var7 -= (float) (Math.PI * 2);
      }

      while (var7 < (float) -Math.PI) {
         var7 += (float) (Math.PI * 2);
      }

      this.recoveredField1818 += var7 * 0.4F;
      this.recoveredField1820 = MathHelper.clamp_float(this.recoveredField1820, 0.0F, 1.0F);
      this.tickCount++;
      this.recoveredField1822 = this.recoveredField1817;
      float var3 = (this.recoveredField1824 - this.recoveredField1817) * 0.4F;
      float var9 = 0.2F;
      var3 = MathHelper.clamp_float(var3, -var9, var9);
      this.recoveredField1823 = this.recoveredField1823 + (var3 - this.recoveredField1823) * 0.9F;
      this.recoveredField1817 = this.recoveredField1817 + this.recoveredField1823;
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
