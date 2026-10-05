package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.ExponentialFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import io.netty.channel.oio.OioEventLoopGroup;
import javax.vecmath.Vector4d;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.tileentity.TileEntityEndPortalRenderer;

public class UnidentifiedClass4798 extends UnidentifiedClass0300 {
   public UnidentifiedEnum1205 field_0001;
   public ExponentialFade field_0002 = new ExponentialFade(821010820422435199L & -821010821411348105L);
   public CosineFade field_0006;
   public TileEntityEndPortalRenderer field_0005;
   public OioEventLoopGroup field_0003;
   public boolean field_0007;
   public Vector4d field_0000;
   public CosineFade field_0004 = new CosineFade(2372983L & 9044863L);

   public UnidentifiedClass4798() {
      super("Floss", new FloatFade(1225891660L & -2208711120879870644L));
      this.field_0006 = new CosineFade(4960250311307952378L & 335544574L);
      this.field_0007 = false;
      this.field_0006.method_20200();
      this.field_0001 = UnidentifiedEnum1205.field_0004;
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      CheatBreaker.getInstance().method_19783().method_01383().put(var1.aK(), var2);
      if (!this.field_0002.method_21217()) {
         if (this.field_0006.method_21227() >= 0.5) {
            this.field_0002.method_20200();
            this.field_0004.method_20200();
         }
      } else if (this.field_0002.method_21210()) {
         this.field_0002.method_20200();
         this.field_0004.method_20200();
         this.field_0001 = this.method_28680();
      }

      if (this.field_0006.method_21210()) {
         this.field_0007 = !this.field_0007;
         this.field_0006.method_20200();
      }

      float var4 = this.field_0002.method_21227();
      float var5 = this.field_0004.method_21227();
      var2.h.rotateAngleX = (float)Math.toRadians((this.field_0001 == UnidentifiedEnum1205.field_0005 ? 45 : -45) * var5);
      var2.i.rotateAngleX = (float)Math.toRadians((this.field_0001 == UnidentifiedEnum1205.field_0003 ? 45 : -45) * var5);
      var2.bipedRightArmwear.rotateAngleX = (float)Math.toRadians((this.field_0001 == UnidentifiedEnum1205.field_0005 ? 45 : -45) * var5);
      var2.bipedLeftArmwear.rotateAngleX = (float)Math.toRadians((this.field_0001 == UnidentifiedEnum1205.field_0003 ? 45 : -45) * var5);
      float var6 = 150.0F;
      float var7 = var6 / 2.0F;
      switch (UnidentifiedClass3460.field_0004[this.field_0001.ordinal()]) {
         case 1:
            var2.h.rotateAngleZ = (float)Math.toRadians(var6 * var4 - var7);
            var2.i.rotateAngleZ = (float)Math.toRadians(var6 * var4 - var7);
            var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(var6 * var4 - var7);
            var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(var6 * var4 - var7);
            break;
         case 2:
            var2.h.rotateAngleZ = (float)Math.toRadians(var7 - var7 * var5);
            var2.i.rotateAngleZ = (float)Math.toRadians(var7 - var7 * var5);
            var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(var7 - var7 * var5);
            var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(var7 - var7 * var5);
            break;
         case 3:
            var2.h.rotateAngleZ = (float)Math.toRadians(-var6 * var4 + var7);
            var2.i.rotateAngleZ = (float)Math.toRadians(-var6 * var4 + var7);
            var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(-var6 * var4 + var7);
            var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(-var6 * var4 + var7);
            break;
         case 4:
            var2.h.rotateAngleZ = (float)Math.toRadians(var7 * var5 - var7);
            var2.i.rotateAngleZ = (float)Math.toRadians(var7 * var5 - var7);
            var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(var7 * var5 - var7);
            var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(var7 * var5 - var7);
      }

      var5 = this.field_0006.method_21227();
      if (this.field_0007) {
         var2.g.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.bipedCape.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.j.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.k.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.k.offsetX = 0.2F * var5;
         var2.j.offsetX = 0.2F * var5;
         var2.bipedBodyWear.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.bipedRightLegwear.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.bipedLeftLegwear.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.bipedLeftLegwear.offsetX = 0.2F * var5;
         var2.bipedRightLegwear.offsetX = 0.2F * var5;
      } else {
         var2.g.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.bipedCape.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.j.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.k.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.k.offsetX = -0.2F * var5;
         var2.j.offsetX = -0.2F * var5;
         var2.bipedBodyWear.rotateAngleZ = (float)Math.toRadians(15.0F * var5);
         var2.bipedRightLegwear.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.bipedLeftLegwear.rotateAngleZ = (float)Math.toRadians(-15.0F * var5);
         var2.bipedLeftLegwear.offsetX = -0.2F * var5;
         var2.bipedRightLegwear.offsetX = -0.2F * var5;
      }
   }

   public UnidentifiedEnum1205 method_28680() {
      switch (UnidentifiedClass3460.field_0004[this.field_0001.ordinal()]) {
         case 2:
            return UnidentifiedEnum1205.field_0000;
         case 3:
            return UnidentifiedEnum1205.field_0003;
         case 4:
            return UnidentifiedEnum1205.field_0004;
         default:
            return UnidentifiedEnum1205.field_0005;
      }
   }
}
