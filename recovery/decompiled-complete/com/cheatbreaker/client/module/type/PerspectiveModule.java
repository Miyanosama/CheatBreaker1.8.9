package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;
import io.netty.channel.local.LocalChannel$5;
import net.minecraft.block.BlockBeacon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class PerspectiveModule extends AbstractModule {
   public Setting field_0011;
   public Setting field_0013;
   public BlockBeacon field_0003;
   public Setting field_0005;
   public LocalChannel$5 field_0020;
   public Setting field_0015;
   public Setting field_0024;
   public Setting field_0019;
   public Setting field_0001;
   public float field_0008 = 0.0F;
   public Setting field_0012;
   public Setting field_0016;
   public Setting field_0002;
   public Setting field_0010;
   public Setting field_0007;
   public Setting field_0022;
   public Setting field_0006;
   public Setting field_0014;
   public Setting field_0000;
   public Setting field_0009;
   public Setting field_0021;
   public Setting field_0017;
   public Setting field_0023;
   public Setting field_0004;
   public Setting field_0018;

   public void method_24319(float var1, Setting var2) {
      if (this.minecraft.getRenderViewEntity() instanceof EntityPlayer && var2.method_08908()) {
         EntityPlayer var3 = (EntityPlayer)this.minecraft.getRenderViewEntity();
         boolean var4 = this.field_0014.getValue().equals("Only Screen");
         float var5 = this.field_0010.method_08905() / 100.0F;
         if (var2 == this.field_0018) {
            var4 = this.field_0014.getValue().equals("Only Hand");
            var5 = this.field_0009.method_08905() / 100.0F;
         }

         float var6 = var3.M - var3.L;
         float var7 = -(var3.M + var6 * var1);
         float var8 = var3.prevCameraYaw + (var3.cameraYaw - var3.prevCameraYaw) * var1;
         float var9 = var3.aE + (var3.aF - var3.aE) * var1;
         float var10 = !this.field_0014.getValue().equals("ON") && !var4 ? var8 : this.field_0008;
         if (this.field_0014.getValue().equals("ON") || var4) {
            if (var3.isSprinting()) {
               this.field_0008 = var8;
            } else {
               this.field_0008 = (float)(this.field_0008 - 9.0E-5);
            }

            if (this.field_0008 < 0.0F) {
               return;
            }
         }

         GL11.glTranslatef(MathHelper.sin(var7 * (float) Math.PI) * var10 * 0.5F * var5, -Math.abs(MathHelper.cos(var7 * (float) Math.PI) * var10 * var5), 0.0F);
         GL11.glRotatef(MathHelper.sin(var7 * (float) Math.PI) * var10 * 3.0F * var5, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(Math.abs(MathHelper.cos(var7 * (float) Math.PI - 0.2F) * var10) * 5.0F * var5, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(var9, 1.0F, 0.0F, 0.0F);
      }
   }

   public PerspectiveModule() {
      super("Perspective");
      this.method_28821("Allows you to change how your perspective looks.");
      this.method_28807("Freelook", "Snaplook", "360 Perspective");
      this.method_28823("Drag to Look is disabled.\nDamage affects camera is enabled.", "hypixel");
      this.setDefaultState(true);
      this.setPreviewLabel("Perspective", 1.0F);
      this.field_0024 = new Setting(this, "label").setValue("Third Person Options");
      this.field_0004 = new Setting(this, "Toggle Back Camera", "Make the back camera keybind toggled instead of held.").setValue(false);
      this.field_0019 = new Setting(this, "Toggle Front Camera", "Make the front camera keybind toggled instead of held.").setValue(false);
      this.field_0021 = new Setting(this, "label").setValue("Drag to Look Options");
      this.field_0017 = new Setting(this, "Look View", "Change which perspective mode to use when toggling Drag to Look.")
         .setValue("Third")
         .acceptedValues("Third", "Reverse", "First");
      this.field_0006 = new Setting(this, "Toggle Drag to Look", "Make the Drag to Look keybind toggled instead of held.").setValue(false);
      this.field_0007 = new Setting(this, "Pitch Lock", "Toggle the pitch axis limitation.").setValue(true);
      this.field_0011 = new Setting(this, "Invert Pitch (Up and Down)", "Invert the rotation pitch when moving the camera.").setValue(false);
      this.field_0005 = new Setting(this, "Invert Yaw (Left and Right)", "Invert the rotation yaw when moving the camera.").setValue(false);
      this.field_0013 = new Setting(this, "label").setValue("View Bobbing Options");
      this.field_0000 = new Setting(this, "Bob Screen", "Bob the camera screen when moving.").setValue(true);
      this.field_0018 = new Setting(this, "Bob Hand", "Bob the hand when moving.").setValue(true);
      this.field_0014 = new Setting(this, "Bob Only While Sprinting", "Bob the hand only when sprinting.")
         .setValue("OFF")
         .acceptedValues("OFF", "ON", "Only Hand", "Only Screen");
      this.field_0010 = new Setting(this, "Screen Bobbing Intensity").method_08892("%").setValue(100.0F).setMinMax(0.0F, 200.0F);
      this.field_0009 = new Setting(this, "Hand Bobbing Intensity").method_08892("%").setValue(100.0F).setMinMax(0.0F, 200.0F);
      this.field_0002 = new Setting(this, "label").setValue("Camera Options");
      this.field_0001 = new Setting(this, "Damage affects camera").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0016 = new Setting(this, "Hurt Camera Intensity")
         .method_08892("%")
         .setValue(14.0F)
         .setMinMax(5.0F, 35.0F)
         .method_08894(() -> this.field_0001.method_08908());
      this.field_0022 = new Setting(this, "Static Swiftness", "Determines if your FOV changes as you move.").setValue(false);
      this.field_0015 = new Setting(this, "Default FOV").method_08892(" FOV").setValue(30).setMinMax(30, 110);
      this.field_0012 = new Setting(this, "Aiming Multiplier").method_08892("x").setValue(0.15F).setMinMax(0.15F, 0.75F);
   }
}
