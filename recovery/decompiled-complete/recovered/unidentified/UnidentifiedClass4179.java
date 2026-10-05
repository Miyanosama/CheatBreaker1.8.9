package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.Setting$Type;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.EnumTypeAdapterFactory$1;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$PieceWeight;
import net.optifine.player.PlayerItemModel;
import net.optifine.shaders.gui.GuiShaders$1;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass4179 extends AbstractModulesGuiElement {
   public ResourceLocation field_0008;
   public ResourceLocation field_0011;
   public boolean field_0006;
   public float field_0013;
   public boolean field_0016;
   public ResourceLocation field_0003;
   public EnumTypeAdapterFactory$1 field_0005;
   public float field_0009 = -1.0F;
   public GuiShaders$1 field_0015;
   public boolean field_0002 = false;
   public ResourceLocation field_0001;
   public PackMetadataSection field_0004;
   public ResourceLocation field_0000;
   public ResourceLocation field_0014 = new ResourceLocation("client/icons/sun-64.png");
   public StructureNetherBridgePieces$PieceWeight field_0010;
   public PlayerItemModel field_0012;
   public RenderEntity field_0007;
   public ResourceLocation field_0017 = new ResourceLocation("client/icons/moon-64.png");

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      short var6 = 148;
      if (this.field_0002 && !Mouse.isButtonDown(0)) {
         this.field_0002 = false;
      }

      float var7 = 8.0F;
      float var8 = 16.0F;
      float var9 = 17.25F;
      byte var10 = 20;
      if (!this.setting.method_08872().isEmpty()) {
         CheatBreaker.getInstance()
            .field_0068
            .drawCenteredString(
               this.setting.method_08872(),
               this.x + 172 + var6 / 2,
               this.y - 2,
               GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
            );
         Gui.drawRect(
            this.x + 172 + var6 / 2 - 0.5F,
            this.y + 8,
            this.x + 172 + var6 / 2 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         this.field_0006 = true;
      }

      float var11 = GlobalSettings.field_0099.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var11, var11, var11, 0.43F);
      if (!this.setting.method_08882().isEmpty() && !this.setting.method_08875().isEmpty()) {
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(new ResourceLocation("client/icons/" + this.setting.method_08882() + ".png"), this.x + 180 - 5.0F, this.y + 3, 10.0F, 10.0F);
         Gui.drawRect(
            this.x + 180 - 0.5F,
            this.y + 12,
            this.x + 180 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(
            new ResourceLocation("client/icons/" + this.setting.method_08875() + ".png"), this.x + 170 + var6 - 10.0F - 5.0F, this.y + 3, 10.0F, 10.0F
         );
         Gui.drawRect(
            this.x + 170 + var6 - 10 - 0.5F,
            this.y + 12,
            this.x + 170 + var6 - 10 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         this.field_0016 = true;
      } else if (this.setting.method_08911().endsWith("Opacity")) {
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.field_0008, this.x + 180 - 4.0F, this.y + 3, 7.5F, 7.5F);
         Gui.drawRect(
            this.x + 180 - 0.5F,
            this.y + 12,
            this.x + 180 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.field_0001, this.x + 170 + var6 - 7.5F - 6.5F, this.y + 3, 7.5F, 7.5F);
         Gui.drawRect(
            this.x + 170 + var6 - 10 - 0.5F,
            this.y + 12,
            this.x + 170 + var6 - 10 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         this.field_0016 = true;
      } else if (this.setting.method_08911().endsWith("Volume")) {
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.field_0011, this.x + 180 - 3.25F, this.y + 3, 7.5F, 7.5F);
         Gui.drawRect(
            this.x + 180 - 0.5F,
            this.y + 12,
            this.x + 180 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.field_0000, this.x + 170 + var6 - 7.5F - 5.0F, this.y + 3, 7.5F, 7.5F);
         Gui.drawRect(
            this.x + 170 + var6 - 10 - 0.5F,
            this.y + 12,
            this.x + 170 + var6 - 10 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         this.field_0016 = true;
      } else if (this.setting.method_08911().equals("World Time")) {
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.field_0017, this.x + 180 - 3.25F, this.y + 3, 7.5F, 7.5F);
         Gui.drawRect(
            this.x + 180 - 0.5F,
            this.y + 12,
            this.x + 180 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.field_0014, this.x + 170 + var6 - 10 - 5.0F, this.y + 2, 10.0F, 10.0F);
         Gui.drawRect(
            this.x + 170 + var6 - 10 - 0.5F,
            this.y + 12,
            this.x + 170 + var6 - 10 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         this.field_0016 = true;
      }

      if (this.setting.method_08911().endsWith("Scale") || this.setting.method_08911().endsWith("Scale Multiplier")) {
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.field_0003, this.x + 180 - 3.0F, this.y + 4, 6.0F, 6.0F);
         Gui.drawRect(
            this.x + 180 - 0.5F,
            this.y + 12,
            this.x + 180 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.field_0003, this.x + 170 + var6 - 10 - 4.0F, this.y + 2.5F, 8.0F, 8.0F);
         Gui.drawRect(
            this.x + 170 + var6 - 10 - 0.5F,
            this.y + 12,
            this.x + 170 + var6 - 10 + 0.5F,
            this.y + 14,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
         this.field_0016 = true;
      }

      if (!this.field_0016 && !this.field_0006) {
         this.height = 12;
         var7 = 2.0F;
         var8 = 6.0F;
         var9 = 7.25F;
         var10 = 10;
      }

      CheatBreaker.getInstance()
         .field_0068
         .drawString(
            this.setting.method_08911().toUpperCase(),
            this.x + 10,
            this.y + var7,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
         );
      if (this.setting.method_08876()) {
         String var12 = this.setting.getValue().toString() + this.setting.method_08870();
         CheatBreaker.getInstance()
            .field_0068
            .drawString(
               var12,
               (float)(this.x + 169) - CheatBreaker.getInstance().field_0068.getStringWidth(var12),
               this.y + var7,
               GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
            );
      }

      boolean var22 = var1 > (this.x + 170) * this.scale
         && var1 < (this.x + 170 + var6 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + var10 + this.yOffset) * this.scale;
      RenderUtil.method_22054(
         this.x + 174,
         this.y + var8,
         this.x + 170 + var6 - 4,
         this.y + var8 + 2.0F,
         1.0,
         var22
            ? (GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010)
            : (GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015)
      );
      double var13 = var6 - 18;
      if (this.setting.method_08904() != null && this.setting.method_08878() != null) {
         float var15 = Float.parseFloat("" + this.setting.method_08904());
         float var16 = Float.parseFloat("" + this.setting.method_08878());
         if (this.field_0002) {
            this.field_0013 = (float)Math.round((var15 + (var1 - (this.x + 180) * this.scale) * ((var16 - var15) / (var13 * this.scale))) * 100.0) / 100.0F;
            if (this.setting.getType().equals(Setting$Type.field_0002) || Keyboard.isKeyDown(42)) {
               this.field_0013 = Math.round(this.field_0013);
            }

            if (this.field_0013 < var15) {
               this.field_0013 = var15;
            } else if (this.field_0013 > var16) {
               this.field_0013 = var16;
            }

            switch (UnidentifiedClass3858.field_0001[this.setting.getType().ordinal()]) {
               case 1:
                  this.setting.setValue(Integer.parseInt((int)this.field_0013 + ""));
                  break;
               case 2:
                  this.setting.setValue(this.field_0013);
                  break;
               case 3:
                  this.setting.setValue(Double.parseDouble(this.field_0013 + ""));
            }

            Minecraft.getMinecraft().ingameGUI.getChatGUI().refreshChat();
         }

         float var5;
         float var20;
         var5 = (var5 = Float.parseFloat(this.setting.getValue() + "")) < this.field_0009 ? this.field_0009 - var5 : (var20 = var5 - this.field_0009);
         float var17 = ((var16 - var15) / 20.0F + var5 * 8.0F) / (Minecraft.debugFPS + 1);
         if (var17 < 1.0E-4) {
            var17 = 1.0E-4F;
         }

         float var4;
         if (this.field_0009 < (var4 = Float.parseFloat(this.setting.getValue() + ""))) {
            this.field_0009 = this.field_0009 + var17 <= var4 ? (this.field_0009 += var17) : var4;
         } else if (this.field_0009 > var4) {
            this.field_0009 = this.field_0009 - var17 >= var4 ? (this.field_0009 -= var17) : var4;
         }

         double var18 = 100.0F * ((this.field_0009 - var15) / (var16 - var15));
         RenderUtil.method_22054(this.x + 174, this.y + var8, this.x + 180 + var13 * var18 / 100.0, this.y + var8 + 2.0F, 4.0, -12418828);
         GL11.glColor4f(0.25F, 0.45F, 1.0F, 1.0F);
         RenderUtil.method_22052(this.x + 181.25F + var13 * var18 / 100.0, this.y + var9, 4.5);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.method_22052(this.x + 181.25F + var13 * var18 / 100.0, this.y + var9, 2.7F);
         this.method_23220(this.setting, var1, var2);
      }
   }

   public float method_25117(float var1) {
      return Math.round(var1 * 2.0F) / 2.0F;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      short var4 = 170;
      byte var5 = 20;
      if (this.height == 12) {
         var5 = 10;
      }

      boolean var6 = var1 > (this.x + 170) * this.scale
         && var1 < (this.x + 170 + 148 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + var5 + this.yOffset) * this.scale;
      if (var3 == 0 && var6) {
         this.field_0002 = true;
      }
   }

   public UnidentifiedClass4179(Setting var1, float var2) {
      super(var2);
      this.field_0011 = new ResourceLocation("client/icons/volume-mute-64.png");
      this.field_0000 = new ResourceLocation("client/icons/volume-up-64.png");
      this.field_0001 = new ResourceLocation("client/icons/circle-64.png");
      this.field_0008 = new ResourceLocation("client/icons/circle-hollow-64.png");
      this.field_0003 = new ResourceLocation("client/icons/letter-t-64.png");
      this.field_0016 = false;
      this.field_0006 = false;
      this.setting = var1;
      this.height = 22;
      this.field_0009 = Float.parseFloat("" + var1.getValue());
   }
}
