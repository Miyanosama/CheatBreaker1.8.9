package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.particle.EntityBreakingFX$Factory;
import net.minecraft.util.ResourceLocation;
import net.optifine.util.ArrayCache;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass0878 extends AbstractModulesGuiElement {
   public ColorFade field_0001 = new ColorFade(0, 788529152);
   public EntityBreakingFX$Factory field_0002;
   public ArrayCache field_0000;
   public ClientResourceManager field_0003;

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var4 = var1 > this.x && var1 < this.x + this.width && var2 > this.y && var2 < this.y + this.height;
      Gui.a(this.x, this.y, this.x + this.width, this.y + this.height, this.field_0001.method_25066(var4).getRGB());
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if (this.field_0003.method_20848().method_00485().equals("cape")) {
         Minecraft.getMinecraft().renderEngine.bindTexture(this.field_0003.method_20859());
         GL11.glPushMatrix();
         GL11.glTranslatef(this.x + 20, this.y + 7, 0.0F);
         GL11.glScalef(0.25F, 0.13F, 0.25F);
         RenderUtil.method_22065(0.0F, 0.0F, 2.0F, 7.0F, 44, 120);
         GL11.glPopMatrix();
      } else {
         try {
            RenderUtil.drawIcon(this.field_0003.method_20850(), 8.0F, this.x + 20, this.y + 7);
         } catch (Exception var6) {
         }
      }

      CheatBreaker.getInstance()
         .field_0036
         .drawString(this.field_0003.method_20858().replace("_", " "), this.x + 42, this.y + this.height / 2 - 5, -1342177281);
      if (this.field_0003.method_20849()) {
         GL11.glColor4f(0.0F, 0.8F, 0.0F, 0.45F);
      } else {
         GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.25F);
      }

      RenderUtil.method_22052(this.x + 8, this.y + this.height / 2.0F, 3.0);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      boolean var4 = var1 > this.x && var1 < this.x + this.width && var2 > this.y && var2 < this.y + this.height;
      if (var4) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         if (this.field_0003.method_20849()) {
            this.field_0003.method_20857(false);
         } else if (this.field_0003.method_20848() == CosmeticType.field_0006) {
            this.field_0003.method_20857(true);

            for (ClientResourceManager var8 : CheatBreaker.getInstance().method_19791().method_27046()) {
               if (var8 != this.field_0003 && var8.method_20848().equals(CosmeticType.field_0006)) {
                  var8.method_20857(false);
               }
            }

            this.field_0003.method_20857(true);
         } else {
            this.field_0003.method_20857(true);

            for (ClientResourceManager var6 : CheatBreaker.getInstance().method_19791().method_27046()) {
               if (var6 != this.field_0003 && var6.method_20848() != CosmeticType.field_0006) {
                  var6.method_20857(false);
               }
            }

            this.field_0003.method_20857(true);
         }

         CheatBreaker.getInstance().getAssetsWebSocket().sendClientCosmetics();
      }
   }

   public UnidentifiedClass0878(ClientResourceManager var1, float var2) {
      super(var2);
      this.height = 30;
      this.field_0003 = var1;
   }
}
