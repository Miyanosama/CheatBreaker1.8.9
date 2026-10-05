package recovered.unidentified;

import com.cheatbreaker.client.ui.DisconnectConfirmationGui;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.ExponentialFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import net.minecraft.block.material.Material$1;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import org.apache.log4j.pattern.IntegerPatternConverter;

public class UnidentifiedClass1113 extends UnidentifiedClass0300 {
   public Material$1 field_0001;
   public float field_0002;
   public IntegerPatternConverter field_0007;
   public CosineFade field_0006;
   public float field_0003;
   public DisconnectConfirmationGui field_0008;
   public float field_0000;
   public ExponentialFade field_0004 = new ExponentialFade(809651176L & 1208853484L);
   public ExponentialFade field_0005 = new ExponentialFade(7019839271869876185L & -7019839272834824208L);

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }

   public UnidentifiedClass1113() {
      super("Super Facepalm", new FloatFade(7279750084261072420L & -7279750084749525264L));
      this.field_0006 = new CosineFade(2625241220544272484L & 76292214L);
      this.field_0000 = (float)Math.toRadians(45.0);
      this.field_0003 = (float)Math.toRadians(-30.0);
      this.field_0002 = (float)Math.toRadians(-100.0);
   }

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      float var4 = this.field_0004.method_21227();
      if (!this.field_0004.method_21217() && this.field_0001.method_21207() >= (-3125000276637625706L & 3125000276572899542L)) {
         this.field_0004.method_20200();
      }

      if (this.field_0004.method_21217()) {
         if (this.field_0004.method_21210() && !this.field_0006.method_21233() && !this.field_0005.method_21217()) {
            this.field_0006.method_20200();
         }

         float var5 = var2.e.rotateAngleX;
         float var6 = var2.e.rotateAngleY;
         var2.e.rotateAngleZ = -((float)Math.toRadians(10.0F * this.field_0006.method_21227()));
         var2.f.rotateAngleZ = -((float)Math.toRadians(10.0F * this.field_0006.method_21227()));
         var2.e.rotateAngleY = (float)Math.toRadians(10.0) * var4 - (float)Math.toRadians(10.0F * this.field_0006.method_21227());
         var2.f.rotateAngleY = (float)Math.toRadians(10.0) * var4 - (float)Math.toRadians(10.0F * this.field_0006.method_21227());
         var2.e.rotateAngleX = this.field_0000 * var4;
         var2.f.rotateAngleX = this.field_0000 * var4;
         var2.h.rotateAngleY = this.field_0003 * var4 - (this.field_0005.method_21217() ? 0.0F : (float)Math.toRadians(10.0F * this.field_0006.method_21227()));
         var2.h.rotateAngleX = this.field_0002 * var4;
         var2.bipedRightArmwear.rotateAngleY = this.field_0003 * var4
            - (this.field_0005.method_21217() ? 0.0F : (float)Math.toRadians(10.0F * this.field_0006.method_21227()));
         var2.bipedRightArmwear.rotateAngleX = this.field_0002 * var4;
         if (!this.field_0005.method_21217() && this.field_0001.method_21229() <= this.field_0005.method_21240()) {
            this.field_0005.method_20200();
         }

         if (this.field_0005.method_21217()) {
            var4 = this.field_0005.method_21227();
            var2.e.rotateAngleY = var6 * var4;
            var2.f.rotateAngleY = var6 * var4;
            var2.e.rotateAngleZ = 0.0F;
            var2.f.rotateAngleZ = 0.0F;
            var2.e.rotateAngleX = var2.e.rotateAngleX - (this.field_0000 - var5) * var4;
            var2.f.rotateAngleX = var2.f.rotateAngleX - (this.field_0000 - var5) * var4;
            var2.h.rotateAngleY = var2.h.rotateAngleY - this.field_0003 * var4;
            var2.h.rotateAngleX = var2.h.rotateAngleX - this.field_0002 * var4;
            var2.bipedRightArmwear.rotateAngleY = var2.bipedRightArmwear.rotateAngleY - this.field_0003 * var4;
            var2.bipedRightArmwear.rotateAngleX = var2.bipedRightArmwear.rotateAngleX - this.field_0002 * var4;
         }
      }
   }
}
