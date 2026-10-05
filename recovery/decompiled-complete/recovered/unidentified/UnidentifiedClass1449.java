package recovered.unidentified;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.concurrent.ThreadLocalRandom;
import javax.vecmath.Point2f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFishWakeFX$Factory;
import net.minecraft.util.ResourceLocation;
import org.java_websocket.framing.ContinuousFrame;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass1449 extends AbstractElement {
   public UnidentifiedClass4074[] field_0005;
   public ContinuousFrame field_0009;
   public boolean field_0004;
   public float[] field_0008;
   public EntityFishWakeFX$Factory field_0001;
   public Minecraft field_0002;
   public ResourceLocation field_0010 = new ResourceLocation("client/animatedlogo/128/logo_128_no_stars.png");
   public UnidentifiedClass0201 field_0007;
   public Point2f field_0003;
   public RtspHeaders field_0011;
   public static ResourceLocation[] field_0000 = new ResourceLocation[8];
   public boolean field_0006;

   public UnidentifiedClass1449() {
      this(true);
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
   }

   public void method_10144() {
      for (int var1 = 1; var1 <= 8; var1++) {
         if (this.field_0005[var1 - 1] != null && !this.field_0005[var1 - 1].method_21233()) {
            this.field_0006 = false;
         }

         if (this.field_0005[var1 - 1] == null || !this.field_0005[var1 - 1].method_21233()) {
            long var2 = ThreadLocalRandom.current().nextLong(285274020L & -5883899414971347029L, -671982155332423960L & 947957488L);
            if (this.field_0006) {
               this.field_0008[var1 - 1] = Math.max(ThreadLocalRandom.current().nextFloat(), 0.8F);
            }

            this.field_0005[var1 - 1] = new UnidentifiedClass4074(this, var2, null);
         }
      }
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      GL11.glPushMatrix();
      if (this.field_0004) {
         GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
         RenderUtil.method_22064(this.field_0010, this.x + 1.0F, this.y + 1.0F, this.width, this.height);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.method_22064(this.field_0010, this.x, this.y, this.width, this.height);

      for (int var4 = 0; var4 < 8; var4++) {
         UnidentifiedClass4074 var5 = this.field_0005[var4];
         if (!var5.method_21217()) {
            var5.method_20200();
         }

         GL11.glPushMatrix();
         if (!var5.method_21233()) {
            this.method_10144();
         }

         float var6 = var5.method_24497();
         if (var5.method_24498() && this.field_0006) {
            this.field_0006 = false;
         }

         if (this.field_0006) {
            var6 = Math.max(var6, this.field_0008[var4]);
         }

         if (this.field_0004) {
            GL11.glColor4f(0.0F, 0.0F, 0.0F, var6 / 5.0F);
            RenderUtil.method_22064(field_0000[var4], this.x + 1.0F, this.y + 1.0F, this.width, this.height);
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, var6);
         RenderUtil.method_22064(field_0000[var4], this.x, this.y, this.width, this.height);
         GL11.glPopMatrix();
      }

      GL11.glPopMatrix();
   }

   @Override
   public void handleElementUpdate() {
      this.method_10144();
   }

   public UnidentifiedClass1449(boolean var1) {
      this.field_0005 = new UnidentifiedClass4074[8];
      this.field_0006 = true;
      this.field_0008 = new float[8];

      for (int var2 = 1; var2 <= 8; var2++) {
         if (field_0000[var2 - 1] == null) {
            field_0000[var2 - 1] = new ResourceLocation("client/animatedlogo/128/logo_128_star_" + var2 + ".png");
         }
      }

      this.method_10144();
      this.field_0004 = var1;
   }
}
