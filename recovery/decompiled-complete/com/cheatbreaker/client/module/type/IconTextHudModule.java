package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.ResourcePackRepository$1;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.realms.RealmsEditBox;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass1369;

public abstract class IconTextHudModule extends TextHudModule {
   public BlockBookshelf field_0002;
   public ResourcePackRepository$1 field_0006;
   public ResourceLocation field_0000 = null;
   public AssetsWebSocket field_0003;
   public Setting field_0005;
   public RealmsEditBox field_0001;
   public EntityNotFoundException field_0004;

   @Override
   public void method_04331(String var1) {
      boolean var2 = this.field_0005.method_08908();
      String var3 = this.method_00166();
      if (var3 == null || var3.isEmpty()) {
         var3 = "";
      }

      String var4;
      if (this.method_21170() != null && this.method_00167() != null) {
         var4 = this.method_21170();
      } else if (!var3.equals("")) {
         if (!var2 && !(Boolean)this.field_0005.getValue()) {
            var4 = this.field_0006.getValue().toString().replaceAll("%LABEL%", var3).replaceAll("%VALUE%", var1);
         } else {
            var4 = this.field_0003.getValue().toString().replaceAll("%LABEL%", var3).replaceAll("%VALUE%", var1);
         }
      } else {
         var4 = var1;
      }

      this.field_0000 = this.method_01868();
      float var5 = this.minecraft.fontRendererObj.getStringWidth(var4);
      float var6 = (Float)this.field_0018.getValue();
      float var7 = var2 ? var6 : 0.0F;
      float var8 = !this.field_0002.getValue() ? var5 + (Float)this.field_0009.getValue() + var7 : (Float)this.field_0010.getValue();
      if (this.field_0001.getValue().equals("Extend Width") && (Boolean)this.field_0002.getValue()) {
         var8 = Math.max((Float)this.field_0010.getValue(), var5 + (Float)this.field_0009.getValue() + var7);
      }

      if (!(Boolean)this.field_0005.getValue() && !var2 && !(Boolean)this.field_0007.getValue()) {
         GL11.glEnable(3042);
         this.method_28812(
            this.minecraft.fontRendererObj.drawString(var4, 0.0F, 0.0F, this.getTextOffsetY(), (Boolean)this.field_0000.getValue()),
            this.minecraft.fontRendererObj.FONT_HEIGHT
         );
      } else {
         this.method_28812(var8, var6);
         if ((Boolean)this.field_0005.getValue()) {
            Gui.drawRect(0.0F, 0.0F, var8, var6, this.field_0019.method_08901());
            if ((Boolean)this.field_0014.getValue()) {
               float var9 = (Float)this.field_0017.getValue();
               Gui.method_00886(-var9, -var9, var8 + var9, var6 + var9, var9, this.field_0016.method_08901());
            }
         }

         if (var2) {
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            RenderUtil.method_22063(this.field_0000, var6 / 2.0F, 0.0F, 0.0F);
         }

         GL11.glEnable(3042);
         float var10 = 1.0F;
         if (this.minecraft.fontRendererObj.getStringWidth(var4) > var8 - this.field_0009.method_08905() - var7
            && this.field_0001.getValue().equals("Scale Text")) {
            var10 = (var8 - this.field_0009.method_08905() - var7) / this.minecraft.fontRendererObj.getStringWidth(var4);
            GL11.glScalef(var10, var10, 1.0F);
            var10 = this.minecraft.fontRendererObj.getStringWidth(var4) / (var8 - this.field_0009.method_08905() - var7);
         }

         this.minecraft
            .fontRendererObj
            .drawString(
               var4,
               this.field_0041 / 2.0F * var10 - this.minecraft.fontRendererObj.getStringWidth(var4) / 2 + var7 * var10 / 2.0F + 0.6F,
               var6 / 2.0F * var10 - 3.49F,
               this.getTextOffsetY(),
               this.field_0005.getValue() ? (Boolean)this.field_0015.getValue() : (Boolean)this.field_0000.getValue()
            );
         if (this.minecraft.fontRendererObj.getStringWidth(var4) > var8) {
            GL11.glScalef(var10, var10, 1.0F);
         }
      }

      GL11.glDisable(3042);
   }

   @Override
   public void method_00165() {
      this.field_0005 = new Setting(this, "Show icon", "Show an icon corresponding to the mod.").setValue(true);
   }

   public IconTextHudModule(String var1, String var2) {
      super(var1, var2);
   }

   @Override
   public UnidentifiedClass1369 method_08395() {
      return new UnidentifiedClass1369(10.0F, 16.0F, 64.0F, 40.0F, 56.0F, 80.0F);
   }

   @Override
   public boolean method_08396() {
      return false;
   }

   public ResourceLocation method_01868() {
      return this.field_0000;
   }
}
