package recovered.unidentified;

import com.cheatbreaker.client.ui.fading.FloatFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import javazoom.jl.decoder.SampleBuffer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.optifine.CrashReporter;

public class UnidentifiedClass0606 extends UnidentifiedClass0300 {
   public MinMaxFade field_0000 = new MinMaxFade(612631035L & 33623290L);
   public SampleBuffer field_0001;
   public MinMaxFade field_0003 = new MinMaxFade(-6098702426863173382L & 6098702425543460350L);
   public CrashReporter field_0002;

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      float var4 = 1.0F;
      if (this.field_0000.method_21240() > this.field_0001.method_21207()) {
         var4 = this.field_0000.method_21227();
      } else if (this.field_0001.method_21229() <= this.field_0003.method_21240()) {
         if (!this.field_0003.method_21217()) {
            this.field_0003.method_20200();
         }

         var4 = 1.0F - this.field_0003.method_21227();
      }

      var2.i.rotateAngleX = (float)Math.toRadians(-180.0F * var4);
      var2.h.rotateAngleX = (float)Math.toRadians(-180.0F * var4);
      var2.h.rotateAngleZ = (float)Math.toRadians(-15.0F * var4);
      var2.i.rotateAngleZ = (float)Math.toRadians(15.0F * var4);
      var2.bipedLeftArmwear.rotateAngleX = (float)Math.toRadians(-180.0F * var4);
      var2.bipedRightArmwear.rotateAngleX = (float)Math.toRadians(-180.0F * var4);
      var2.bipedRightArmwear.rotateAngleZ = (float)Math.toRadians(-15.0F * var4);
      var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(15.0F * var4);
   }

   public UnidentifiedClass0606() {
      super("Hands Up", new FloatFade(330660637259073496L & 7389170L));
      this.field_0000.method_20200();
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }
}
