package net.minecraft.client.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.log4j.lf5.LogLevelFormatException;

public class EntityOtherPlayerMP extends AbstractClientPlayer {
   public double otherPlayerMPX;
   public double otherPlayerMPPitch;
   public LogLevelFormatException field_0003;
   public double otherPlayerMPY;
   public boolean isItemInUse;
   public double otherPlayerMPZ;
   public double otherPlayerMPYaw;
   public int otherPlayerMPPosRotationIncrements;

   @Override
   public void onUpdate() {
      this.bZ = 0.0F;
      super.onUpdate();
      this.aA = this.aB;
      double var1 = this.s - this.p;
      double var3 = this.u - this.r;
      float var5 = MathHelper.sqrt_double(var1 * var1 + var3 * var3) * 4.0F;
      if (var5 > 1.0F) {
         var5 = 1.0F;
      }

      this.aB = this.aB + (var5 - this.aB) * 0.4F;
      this.aC = this.aC + this.aB;
      if (!this.isItemInUse && this.isEating() && this.bi.mainInventory[this.bi.currentItem] != null) {
         ItemStack var6 = this.bi.mainInventory[this.bi.currentItem];
         this.setItemInUse(this.bi.mainInventory[this.bi.currentItem], var6.getItem().getMaxItemUseDuration(var6));
         this.isItemInUse = true;
      } else if (this.isItemInUse && !this.isEating()) {
         this.clearItemInUse();
         this.isItemInUse = false;
      }
   }

   @Override
   public void onLivingUpdate() {
      if (this.otherPlayerMPPosRotationIncrements > 0) {
         double var1 = this.s + (this.otherPlayerMPX - this.s) / this.otherPlayerMPPosRotationIncrements;
         double var3 = this.t + (this.otherPlayerMPY - this.t) / this.otherPlayerMPPosRotationIncrements;
         double var5 = this.u + (this.otherPlayerMPZ - this.u) / this.otherPlayerMPPosRotationIncrements;
         double var7 = this.otherPlayerMPYaw - this.y;

         while (var7 < -180.0) {
            var7 += 360.0;
         }

         while (var7 >= 180.0) {
            var7 -= 360.0;
         }

         this.y = (float)(this.y + var7 / this.otherPlayerMPPosRotationIncrements);
         this.z = (float)(this.z + (this.otherPlayerMPPitch - this.z) / this.otherPlayerMPPosRotationIncrements);
         this.otherPlayerMPPosRotationIncrements--;
         this.b(var1, var3, var5);
         this.setRotation(this.y, this.z);
      }

      this.prevCameraYaw = this.cameraYaw;
      this.updateArmSwingProgress();
      float var9 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x);
      float var2 = (float)Math.atan(-this.w * 0.2F) * 15.0F;
      if (var9 > 0.1F) {
         var9 = 0.1F;
      }

      if (!this.C || this.getHealth() <= 0.0F) {
         var9 = 0.0F;
      }

      if (this.C || this.getHealth() <= 0.0F) {
         var2 = 0.0F;
      }

      this.cameraYaw = this.cameraYaw + (var9 - this.cameraYaw) * 0.4F;
      this.aF = this.aF + (var2 - this.aF) * 0.8F;
   }

   @Override
   public BlockPos getPosition() {
      return new BlockPos(this.s + 0.5, this.t + 0.5, this.u + 0.5);
   }

   @Override
   public void addChatMessage(IChatComponent var1) {
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var1);
   }

   public EntityOtherPlayerMP(World var1, GameProfile var2) {
      super(var1, var2);
      this.S = 0.0F;
      this.T = true;
      this.bZ = 0.25F;
      this.j = 10.0;
   }

   @Override
   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return false;
   }

   @Override
   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9, boolean var10) {
      this.otherPlayerMPX = var1;
      this.otherPlayerMPY = var3;
      this.otherPlayerMPZ = var5;
      this.otherPlayerMPYaw = var7;
      this.otherPlayerMPPitch = var8;
      this.otherPlayerMPPosRotationIncrements = var9;
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return true;
   }

   @Override
   public void setCurrentItemOrArmor(int var1, ItemStack var2) {
      if (var1 == 0) {
         this.bi.mainInventory[this.bi.currentItem] = var2;
      } else {
         this.bi.armorInventory[var1 - 1] = var2;
      }
   }
}
