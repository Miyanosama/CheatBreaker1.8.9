package recovered.unidentified;

import com.cheatbreaker.client.ui.fading.ExponentialFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import io.netty.handler.codec.http.HttpHeaderDateFormat;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class UnidentifiedClass0998 extends UnidentifiedClass0300 {
   public ExponentialFade field_0000 = new ExponentialFade(1074279000L & 6520048027990868570L);
   public MinMaxFade field_0001 = new MinMaxFade(-6136309379547921825L & 6136309377421315928L);
   public HttpHeaderDateFormat field_0002;

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      var2.h.rotateAngleX = var2.h.rotateAngleX * this.field_0001.method_21227();
      var2.i.rotateAngleX = var2.i.rotateAngleX * this.field_0001.method_21227();
      var2.bipedRightArmwear.rotateAngleX = var2.bipedRightArmwear.rotateAngleX * this.field_0001.method_21227();
      var2.bipedLeftArmwear.rotateAngleX = var2.bipedLeftArmwear.rotateAngleX * this.field_0001.method_21227();
      if (this.field_0000.method_21233()) {
         var2.h.rotateAngleZ = (float)Math.toRadians(90.0F * this.field_0000.method_21227());
         var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(90.0F * this.field_0000.method_21227());
         var2.i.rotateAngleZ = (float)Math.toRadians(-90.0F * this.field_0000.method_21227());
         var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(-90.0F * this.field_0000.method_21227());
      } else if (this.field_0001.method_21229() <= this.field_0001.method_21240()) {
         if (!this.field_0001.method_21217()) {
            this.field_0001.method_20200();
         }

         var2.i.rotateAngleZ = Math.min((float)Math.toRadians(-90.0F + 90.0F * this.field_0001.method_21227()), var2.i.rotateAngleZ);
         var2.bipedLeftArmwear.rotateAngleZ = Math.min(
            (float)Math.toRadians(-90.0F + 90.0F * this.field_0001.method_21227()), var2.bipedLeftArmwear.rotateAngleZ
         );
         var2.h.rotateAngleZ = Math.max((float)Math.toRadians(90.0F - 90.0F * this.field_0001.method_21227()), var2.h.rotateAngleZ);
         var2.bipedRightArmwear.rotateAngleZ = Math.max(
            (float)Math.toRadians(90.0F - 90.0F * this.field_0001.method_21227()), var2.bipedRightArmwear.rotateAngleZ
         );
      } else {
         var2.h.rotateAngleZ = (float)Math.toRadians(90.0);
         var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(90.0);
         var2.i.rotateAngleZ = (float)Math.toRadians(-90.0);
         var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(-90.0);
      }
   }

   public UnidentifiedClass0998() {
      super("T-Pose", new MinMaxFade(-4810059053724066936L & 4810059052158556108L));
      this.field_0000.method_20200();
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }
}
