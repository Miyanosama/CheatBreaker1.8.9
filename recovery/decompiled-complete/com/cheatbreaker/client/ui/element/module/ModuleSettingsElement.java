package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.module.CBModulesGui$1;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleZRoom;
import net.optifine.reflect.FieldLocatorTypes;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass5100;

public class ModuleSettingsElement extends AbstractModulesGuiElement {
   public AbstractScrollableElement parent;
   public AbstractModule module;
   public StructureOceanMonumentPieces$DoubleZRoom field_0002;
   public CBModulesGui$1 field_0006;
   public int field_0007 = 0;
   public ResourceLocation rightIcon = new ResourceLocation("client/icons/right.png");
   public int field_0001;
   public FieldLocatorTypes field_0004;

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      double var4 = this.height - 10;
      double var6 = var4 / this.parent.field_0009 * 100.0;
      double var8 = var4 / 100.0 * var6;
      double var10 = this.parent.field_0013 / 100.0 * var6;
      boolean var12 = var1 > (this.x + this.width - 9) * this.scale
         && var1 < (this.x + this.width - 3) * this.scale
         && var2 > (this.y + 11 - var10) * this.scale
         && var2 < (this.y + 8 + var8 - var10) * this.scale;
      boolean var13 = var1 > (this.x + this.width - 9) * this.scale
         && var1 < (this.x + this.width - 3) * this.scale
         && var2 > (this.y + 11) * this.scale
         && var2 < (this.y + 6 + var4 - 3.0) * this.scale;
      if (var3 == 0 && var13 || var12) {
         this.parent.field_0001 = true;
      }

      this.parent.method_03200(this.module);
   }

   public ModuleSettingsElement(AbstractScrollableElement var1, int var2, AbstractModule var3, float var4) {
      super(var4);
      this.parent = var1;
      this.field_0001 = var2;
      this.module = var3;
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var4 = this.isMouseInside(var1, var2);
      byte var5 = 75;
      Gui.a(
         this.x,
         this.y + this.height - 1,
         this.x + this.width,
         this.y + this.height,
         GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0035 : UnidentifiedClass5100.field_0026
      );
      if (this.parent.method_03198(this.module)) {
         if (var4) {
            float var6 = CBModulesGui.getSmoothFloat(790.0F);
            if (this.field_0007 + var6 < var5) {
               this.field_0007 = (int)(this.field_0007 + var6);
               if (this.field_0007 > var5) {
                  this.field_0007 = var5;
               }
            }
         } else if (this.field_0007 > 0) {
            float var7 = CBModulesGui.getSmoothFloat(790.0F);
            this.field_0007 = this.field_0007 - var7 < 0.0F ? 0 : (int)(this.field_0007 - var7);
         }

         if (this.field_0007 > 0) {
            float var8 = (float)this.field_0007 / var5 * 100.0F;
            Gui.a(this.x, (int)(this.y + (this.height - this.height * var8 / 100.0F)), this.x + this.width, this.y + this.height, this.field_0001);
         }
      }

      float var9 = GlobalSettings.field_0099.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var9, var9, var9, 0.35F);
      RenderUtil.drawIcon(this.rightIcon, 2.5F, this.x + 6, this.y + 6.0F);
      CheatBreaker.getInstance()
         .field_0039
         .drawString(
            this.module.getName().toUpperCase(),
            this.x + 14.0F,
            this.y + 3.0F,
            this.parent.method_03198(this.module)
               ? (GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0005 : UnidentifiedClass5100.field_0024)
               : (GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0035 : UnidentifiedClass5100.field_0026)
         );
   }
}
