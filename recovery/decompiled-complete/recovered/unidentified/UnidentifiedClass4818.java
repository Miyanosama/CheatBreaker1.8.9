package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.awt.Color;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass4818 extends AbstractElement {
   public ColorFade field_0003;
   public float field_0005;
   public ColorFade field_0002;
   public UnidentifiedClass0127 field_0004;
   public Color field_0000 = new Color(0.3F, 0.3F, 0.3F, 0.3F);
   public GenLayerBiomeEdge field_0001;
   public UnidentifiedClass4535 field_0006;

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      this.field_0002.method_25068(CheatBreaker.getInstance().getGlobalSettings().field_0053.method_08901());
      boolean var4 = this.field_0004 != null && this.method_28761(var1, var2) && var3;
      Color var5 = this.field_0002.method_25066(var4);
      ScaledResolution var6 = new ScaledResolution(this.mc);
      int var7 = var6.getScaledWidth();
      int var8 = var6.getScaledHeight();
      float var9 = 10.0F;
      float var10 = this.field_0006.field_0000 >= var9 ? 1.0F : this.field_0006.field_0000 / var9;
      GL11.glPushMatrix();
      if (this.field_0004 == null) {
         GL11.glColor4d(
            this.field_0000.getRed() / 255.0F,
            this.field_0000.getGreen() / 255.0F,
            this.field_0000.getBlue() / 255.0F,
            this.field_0000.getAlpha() / 255.0F * var10
         );
      } else {
         GL11.glColor4d(var5.getRed() / 255.0F, var5.getGreen() / 255.0F, var5.getBlue() / 255.0F, var5.getAlpha() / 255.0F * var10);
      }

      RenderUtil.method_22053(var7 / 2.0F, var8 / 2.0F, 88.0, 20.0, 360.0F - this.field_0005 + 90.0F, 360.0F - this.field_0005 + 90.0F + 45.0F);
      GL11.glPopMatrix();
      if (this.field_0004 != null) {
         var5 = this.field_0003.method_25066(var4);
         GL11.glPushMatrix();
         float var11 = this.field_0005 - 90.0F;
         var11 -= 22.5F;
         double var12 = Math.toRadians(var11);
         byte var14 = 60;
         double var15 = var7 / 2.0F + var14 * Math.cos(var12);
         double var17 = var8 / 2.0F + var14 * Math.sin(var12);
         GL11.glColor4d(var5.getRed() / 255.0F, var5.getGreen() / 255.0F, var5.getBlue() / 255.0F, var5.getAlpha() / 255.0F * var10);
         RenderUtil.drawIcon(this.field_0004.method_00994(), 20.0F, (float)var15 - 20.0F, (float)var17 - 20.0F);
         GL11.glPopMatrix();
      }
   }

   public boolean method_28761(float var1, float var2) {
      ScaledResolution var3 = new ScaledResolution(this.mc);
      int var4 = var3.getScaledWidth();
      int var5 = var3.getScaledHeight();
      int var6 = (int)(var1 - var4 / 2);
      int var7 = (int)(var2 - var5 / 2);
      double var8 = Math.sqrt(var6 * var6 + var7 * var7);
      double var10 = Math.toDegrees(Math.atan2(var7, var6)) + 90.0;
      double var12 = this.field_0005 - 45.0F;
      if (var12 < 0.0) {
         var12 += 360.0;
      }

      double var14 = this.field_0005;
      boolean var16 = var10 < 0.0;
      if (var12 > var14) {
         var14 += 360.0;
         var16 = true;
      }

      if (var16) {
         var10 += 360.0;
      }

      return var8 >= 20.0 && var8 <= 175.0 && var10 >= var12 && var10 <= var14;
   }

   public UnidentifiedClass4818(UnidentifiedClass4535 var1, int var2, UnidentifiedClass0127 var3) {
      this.field_0006 = var1;
      float var4 = 45.0F;
      this.field_0005 = var4 / 2.0F + var2 * var4;
      this.field_0004 = var3;
      this.field_0002 = new ColorFade(
         -380373565380752393L & 380373565144310260L,
         new Color(0.2F, 0.2F, 0.2F, 0.1F).getRGB(),
         CheatBreaker.getInstance().getGlobalSettings().field_0053.method_08901()
      );
      this.field_0003 = new ColorFade(1613249012L & -3176710079441255947L, new Color(0.0F, 0.0F, 0.0F, 0.9F).getRGB(), -1);
   }
}
