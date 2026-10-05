package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.block.BlockAnvil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.optifine.GlDebugHandler;
import net.optifine.shaders.gui.GuiButtonEnumShaderOption$1;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass3599 extends AbstractModulesGuiElement {
   public String field_0003;
   public int field_0005;
   public GlDebugHandler field_0002;
   public ResourceLocation field_0006;
   public ResourceLocation field_0007 = new ResourceLocation("client/icons/left.png");
   public BlockAnvil field_0000;
   public float field_0001;
   public GuiButtonEnumShaderOption$1 field_0004;

   public UnidentifiedClass3599(Setting var1, float var2) {
      super(var2);
      this.field_0006 = new ResourceLocation("client/icons/right.png");
      this.field_0005 = 0;
      this.field_0001 = 0.0F;
      this.setting = var1;
      this.height = 12;
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var4 = var1 > (this.x + this.width - 48.0F) * this.scale
         && var1 < (this.x + this.width - 10.0F) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 10.0F + this.yOffset) * this.scale;
      boolean var5 = var1 > (this.x + this.width - 92.0F) * this.scale
         && var1 < (this.x + this.width - 48.0F) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 10.0F + this.yOffset) * this.scale;
      CheatBreaker.getInstance()
         .field_0068
         .drawString(
            this.setting.method_08911().toUpperCase(),
            this.x + 10,
            this.y + 2,
            !var5 && !var4
               ? (GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010)
               : (GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0003 : UnidentifiedClass5100.field_0023)
         );
      if (this.field_0005 == 0) {
         CheatBreaker.getInstance()
            .field_0068
            .drawCenteredString(
               this.setting.method_08908() ? this.setting.method_08871() : this.setting.method_08919(),
               this.x + this.width - 48,
               this.y + 2,
               GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
            );
      } else {
         boolean var6 = this.field_0005 == 1;
         float var10002 = this.x + this.width - 48.0F - (var6 ? -this.field_0001 : this.field_0001);
         CheatBreaker.getInstance()
            .field_0068
            .drawCenteredString(
               this.field_0003,
               var10002,
               this.y + 2,
               GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
            );
         if (var6) {
            CheatBreaker.getInstance()
               .field_0068
               .drawCenteredString(
                  this.setting.method_08908() ? this.setting.method_08871() : this.setting.method_08919(),
                  this.x + this.width - 98 + this.field_0001,
                  this.y + 2,
                  GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
               );
         } else {
            CheatBreaker.getInstance()
               .field_0068
               .drawCenteredString(
                  this.setting.method_08908() ? this.setting.method_08871() : this.setting.method_08919(),
                  this.x + this.width + 2 - this.field_0001,
                  this.y + 2,
                  GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
               );
         }

         if (this.field_0001 >= 50.0F) {
            this.field_0005 = 0;
            this.field_0001 = 0.0F;
         } else {
            float var7 = CBModulesGui.getSmoothFloat(50.0F + this.field_0001 * 15.0F);
            this.field_0001 = Math.min(this.field_0001 + var7, 50.0F);
         }

         Gui.a(
            this.x + this.width - 130,
            this.y + 2,
            this.x + this.width - 72,
            this.y + 12,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0032 : UnidentifiedClass5100.field_0011
         );
         Gui.a(
            this.x + this.width - 22,
            this.y + 2,
            this.x + this.width + 4,
            this.y + 12,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0032 : UnidentifiedClass5100.field_0011
         );
      }

      float var8 = GlobalSettings.field_0099.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var8, var8, var8, var5 ? 0.8F : 0.45F);
      RenderUtil.drawIcon(this.field_0007, 4.0F, this.x + this.width - 82.0F, this.y + 3.0F);
      GL11.glColor4f(var8, var8, var8, var4 ? 0.8F : 0.45F);
      RenderUtil.drawIcon(this.field_0006, 4.0F, this.x + this.width - 22.0F, this.y + 3.0F);
      this.method_23220(this.setting, var1, var2);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      boolean var4 = var1 > (this.x + this.width - 92.0F) * this.scale
         && var1 < (this.x + this.width - 48.0F) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 10.0F + this.yOffset) * this.scale;
      boolean var5 = var1 > (this.x + this.width - 48.0F) * this.scale
         && var1 < (this.x + this.width - 10.0F) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 10.0F + this.yOffset) * this.scale;
      if ((var4 || var5) && this.field_0005 == 0) {
         this.field_0005 = var4 ? 1 : 2;
         this.field_0001 = 0.0F;
         this.field_0003 = this.setting.getValue() ? this.setting.method_08871() : this.setting.method_08919();
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.setting.setValue(!(Boolean)this.setting.getValue());
         if (this.setting == CheatBreaker.getInstance().getGlobalSettings().field_0067
            && !(Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0067.getValue()) {
            CheatBreaker.getInstance().getModuleManager().teammatesModule.method_01340(false);
         }
      }
   }
}
