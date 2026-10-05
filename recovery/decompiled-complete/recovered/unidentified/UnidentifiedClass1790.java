package recovered.unidentified;

import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.ExponentialFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import io.netty.util.internal.MpscLinkedQueueNode;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class UnidentifiedClass1790 extends UnidentifiedClass0300 {
   public float field_0001;
   public ExponentialFade field_0002;
   public ExponentialFade field_0005 = new ExponentialFade(17358486L & 2736276997557911710L);
   public MpscLinkedQueueNode field_0004;
   public CosineFade field_0003;
   public float field_0006;
   public float field_0000;

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      float var4 = this.field_0005.method_21227();
      if (!this.field_0005.method_21217() && this.field_0001.method_21207() >= (360858014L & 1208827543L)) {
         this.field_0005.method_20200();
      }

      if (this.field_0005.method_21217()) {
         if (this.field_0005.method_21210() && !this.field_0003.method_21233() && !this.field_0002.method_21217()) {
            this.field_0003.method_20200();
         }

         float var5 = var2.e.rotateAngleX;
         float var6 = var2.e.rotateAngleY;
         var2.e.rotateAngleZ = -((float)Math.toRadians(10.0F * this.field_0003.method_21227()));
         var2.f.rotateAngleZ = -((float)Math.toRadians(10.0F * this.field_0003.method_21227()));
         var2.e.rotateAngleY = (float)Math.toRadians(10.0) * var4 - (float)Math.toRadians(10.0F * this.field_0003.method_21227());
         var2.f.rotateAngleY = (float)Math.toRadians(10.0) * var4 - (float)Math.toRadians(10.0F * this.field_0003.method_21227());
         var2.e.rotateAngleX = this.field_0001 * var4;
         var2.f.rotateAngleX = this.field_0001 * var4;
         var2.h.rotateAngleY = this.field_0000 * var4 - (this.field_0002.method_21217() ? 0.0F : (float)Math.toRadians(10.0F * this.field_0003.method_21227()));
         var2.h.rotateAngleX = this.field_0006 * var4;
         var2.bipedRightArmwear.rotateAngleY = this.field_0000 * var4
            - (this.field_0002.method_21217() ? 0.0F : (float)Math.toRadians(10.0F * this.field_0003.method_21227()));
         var2.bipedRightArmwear.rotateAngleX = this.field_0006 * var4;
         if (!this.field_0002.method_21217() && this.field_0001.method_21229() <= this.field_0002.method_21240()) {
            this.field_0002.method_20200();
         }

         if (this.field_0002.method_21217()) {
            var4 = this.field_0002.method_21227();
            var2.e.rotateAngleY = var6 * var4;
            var2.f.rotateAngleY = var6 * var4;
            var2.e.rotateAngleZ = 0.0F;
            var2.f.rotateAngleZ = 0.0F;
            var2.e.rotateAngleX = var2.e.rotateAngleX - (this.field_0001 - var5) * var4;
            var2.f.rotateAngleX = var2.f.rotateAngleX - (this.field_0001 - var5) * var4;
            var2.h.rotateAngleY = var2.h.rotateAngleY - this.field_0000 * var4;
            var2.h.rotateAngleX = var2.h.rotateAngleX - this.field_0006 * var4;
            var2.bipedRightArmwear.rotateAngleY = var2.bipedRightArmwear.rotateAngleY - this.field_0000 * var4;
            var2.bipedRightArmwear.rotateAngleX = var2.bipedRightArmwear.rotateAngleX - this.field_0006 * var4;
         }
      }
   }

   public UnidentifiedClass1790() {
      super("Facepalm", new FloatFade(5223188348047751126L & -5223188348509354000L));
      this.field_0002 = new ExponentialFade(7823787709654110409L & -7823787710442362136L);
      this.field_0003 = new CosineFade(434540L & 92291388L);
      this.field_0001 = (float)Math.toRadians(45.0);
      this.field_0000 = (float)Math.toRadians(-30.0);
      this.field_0006 = (float)Math.toRadians(-100.0);
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }
}
