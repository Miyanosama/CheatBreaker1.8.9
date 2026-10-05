package recovered.unidentified;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass0557 extends AbstractElement {
   public boolean field_0003;
   public ResourceLocation[] field_0005 = new ResourceLocation[8];
   public RenderOcelot field_0002;
   public UnidentifiedClass3249[] field_0004 = new UnidentifiedClass3249[8];
   public transient int field_0000;
   public float[] field_0001;
   public ResourceLocation field_0006;

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.method_22064(this.field_0006, this.x, this.y, this.width, this.height);

      for (int var4 = 0; var4 < 8; var4++) {
         UnidentifiedClass3249 var5 = this.field_0004[var4];
         if (!var5.method_21217()) {
            var5.method_20200();
         }

         GL11.glPushMatrix();
         if (!var5.method_21233()) {
            this.method_04175();
         }

         float var6 = var5.method_20202();
         if (var5.method_20201() && this.field_0003) {
            this.field_0003 = false;
         }

         if (this.field_0003) {
            var6 = Math.max(var6, this.field_0001[var4]);
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, var6);
         RenderUtil.method_22064(this.field_0005[var4], this.x, this.y, this.width, this.height);
         GL11.glPopMatrix();
      }

      GL11.glPopMatrix();
   }

   public void method_04175() {
      for (int var1 = 1; var1 <= 8; var1++) {
         if (this.field_0004[var1 - 1] != null && !this.field_0004[var1 - 1].method_21233()) {
            this.field_0003 = false;
         }

         if (this.field_0004[var1 - 1] == null || !this.field_0004[var1 - 1].method_21233()) {
            long var2 = ThreadLocalRandom.current().nextLong(537182128L & 733784041777598446L, -3763075661788729632L & 847294440L);
            if (this.field_0003) {
               this.field_0001[var1 - 1] = Math.max(ThreadLocalRandom.current().nextFloat(), 0.8F);
            }

            this.field_0004[var1 - 1] = new UnidentifiedClass3249(this, this, var2, null);
         }
      }
   }

   @Override
   public void handleElementUpdate() {
      this.field_0000++;
      this.method_04175();
   }

   public UnidentifiedClass0557() {
      this.field_0003 = true;
      this.field_0001 = new float[8];
      this.field_0006 = new ResourceLocation("client/animatedlogo/64/logo_64_no_stars.png");

      for (int var1 = 1; var1 <= 8; var1++) {
         this.field_0005[var1 - 1] = new ResourceLocation("client/animatedlogo/64/logo_64_star_" + var1 + ".png");
      }

      this.method_04175();
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
   }
}
