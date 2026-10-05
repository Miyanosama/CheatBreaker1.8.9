package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LargeAnimatedLogoElement extends AbstractElement {
   public LargeLogoStarFade[] recoveredField1239;
   public boolean recoveredField1240;
   public float[] recoveredField1241;
   public ResourceLocation recoveredField1242 = new ResourceLocation("client/animatedlogo/128/logo_128_no_stars.png");
   public static ResourceLocation[] recoveredField1243 = new ResourceLocation[8];
   public boolean recoveredField1244;

   public LargeAnimatedLogoElement() {
      this(true);
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
   }

   public void method_10144() {
      for (int var1 = 1; var1 <= 8; var1++) {
         if (this.recoveredField1239[var1 - 1] != null && !this.recoveredField1239[var1 - 1].method_21233()) {
            this.recoveredField1244 = false;
         }

         if (this.recoveredField1239[var1 - 1] == null || !this.recoveredField1239[var1 - 1].method_21233()) {
            long var2 = ThreadLocalRandom.current().nextLong(4000L, 12000L);
            if (this.recoveredField1244) {
               this.recoveredField1241[var1 - 1] = Math.max(ThreadLocalRandom.current().nextFloat(), 0.8F);
            }

            this.recoveredField1239[var1 - 1] = new LargeLogoStarFade(this, var2);
         }
      }
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      GL11.glPushMatrix();
      if (this.recoveredField1240) {
         GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
         RenderUtil.method_22064(this.recoveredField1242, this.x + 1.0F, this.y + 1.0F, this.width, this.height);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.method_22064(this.recoveredField1242, this.x, this.y, this.width, this.height);

      for (int var4 = 0; var4 < 8; var4++) {
         LargeLogoStarFade var5 = this.recoveredField1239[var4];
         if (!var5.method_21217()) {
            var5.method_20200();
         }

         GL11.glPushMatrix();
         if (!var5.method_21233()) {
            this.method_10144();
         }

         float var6 = var5.method_24497();
         if (var5.method_24498() && this.recoveredField1244) {
            this.recoveredField1244 = false;
         }

         if (this.recoveredField1244) {
            var6 = Math.max(var6, this.recoveredField1241[var4]);
         }

         if (this.recoveredField1240) {
            GL11.glColor4f(0.0F, 0.0F, 0.0F, var6 / 5.0F);
            RenderUtil.method_22064(recoveredField1243[var4], this.x + 1.0F, this.y + 1.0F, this.width, this.height);
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, var6);
         RenderUtil.method_22064(recoveredField1243[var4], this.x, this.y, this.width, this.height);
         GL11.glPopMatrix();
      }

      GL11.glPopMatrix();
   }

   @Override
   public void handleElementUpdate() {
      this.method_10144();
   }

   public LargeAnimatedLogoElement(boolean var1) {
      this.recoveredField1239 = new LargeLogoStarFade[8];
      this.recoveredField1244 = true;
      this.recoveredField1241 = new float[8];

      for (int var2 = 1; var2 <= 8; var2++) {
         if (recoveredField1243[var2 - 1] == null) {
            recoveredField1243[var2 - 1] = new ResourceLocation("client/animatedlogo/128/logo_128_star_" + var2 + ".png");
         }
      }

      this.method_10144();
      this.recoveredField1240 = var1;
   }
}
