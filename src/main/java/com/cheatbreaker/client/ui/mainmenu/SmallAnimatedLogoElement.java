package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SmallAnimatedLogoElement extends AbstractElement {
   public boolean recoveredField1257;
   public ResourceLocation[] recoveredField1258 = new ResourceLocation[8];
   public SmallLogoStarFade[] recoveredField1259 = new SmallLogoStarFade[8];
   public transient int recoveredField1260;
   public float[] recoveredField1261;
   public ResourceLocation recoveredField1262;

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.method_22064(this.recoveredField1262, this.x, this.y, this.width, this.height);

      for (int var4 = 0; var4 < 8; var4++) {
         SmallLogoStarFade var5 = this.recoveredField1259[var4];
         if (!var5.method_21217()) {
            var5.method_20200();
         }

         GL11.glPushMatrix();
         if (!var5.method_21233()) {
            this.method_04175();
         }

         float var6 = var5.method_20202();
         if (var5.method_20201() && this.recoveredField1257) {
            this.recoveredField1257 = false;
         }

         if (this.recoveredField1257) {
            var6 = Math.max(var6, this.recoveredField1261[var4]);
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, var6);
         RenderUtil.method_22064(this.recoveredField1258[var4], this.x, this.y, this.width, this.height);
         GL11.glPopMatrix();
      }

      GL11.glPopMatrix();
   }

   public void method_04175() {
      for (int var1 = 1; var1 <= 8; var1++) {
         if (this.recoveredField1259[var1 - 1] != null && !this.recoveredField1259[var1 - 1].method_21233()) {
            this.recoveredField1257 = false;
         }

         if (this.recoveredField1259[var1 - 1] == null || !this.recoveredField1259[var1 - 1].method_21233()) {
            long var2 = ThreadLocalRandom.current().nextLong(4000L, 12000L);
            if (this.recoveredField1257) {
               this.recoveredField1261[var1 - 1] = Math.max(ThreadLocalRandom.current().nextFloat(), 0.8F);
            }

            this.recoveredField1259[var1 - 1] = new SmallLogoStarFade(this, this, var2);
         }
      }
   }

   @Override
   public void handleElementUpdate() {
      this.recoveredField1260++;
      this.method_04175();
   }

   public SmallAnimatedLogoElement() {
      this.recoveredField1257 = true;
      this.recoveredField1261 = new float[8];
      this.recoveredField1262 = new ResourceLocation("client/animatedlogo/64/logo_64_no_stars.png");

      for (int var1 = 1; var1 <= 8; var1++) {
         this.recoveredField1258[var1 - 1] = new ResourceLocation("client/animatedlogo/64/logo_64_star_" + var1 + ".png");
      }

      this.method_04175();
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
   }
}
