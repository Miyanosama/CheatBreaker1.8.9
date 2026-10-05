package recovered.unidentified;

import com.cheatbreaker.client.ui.fading.ExponentialFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import io.netty.util.internal.logging.Slf4JLogger;
import net.minecraft.block.BlockStem;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class UnidentifiedClass1688 extends UnidentifiedClass0300 {
   public Slf4JLogger field_0000;
   public BlockStem field_0001;
   public UnidentifiedClass0173 field_0004;
   public MinMaxFade field_0003;
   public ExponentialFade field_0002 = new ExponentialFade(-7686801026736651474L & 7686801026441543996L);

   public UnidentifiedClass1688() {
      super("Naruto Run", new MinMaxFade(-2764797517994905803L & 155461466L));
      this.field_0003 = new MinMaxFade(541088247L & 1208100340L);
      this.field_0002.method_20200();
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      var2.h.rotateAngleX = var2.h.rotateAngleX * this.field_0003.method_21227();
      var2.i.rotateAngleX = var2.i.rotateAngleX * this.field_0003.method_21227();
      var2.bipedRightArmwear.rotateAngleX = var2.bipedRightArmwear.rotateAngleX * this.field_0003.method_21227();
      var2.bipedLeftArmwear.rotateAngleX = var2.bipedLeftArmwear.rotateAngleX * this.field_0003.method_21227();
      if (this.field_0002.method_21233()) {
         var2.h.rotateAngleX = (float)Math.toRadians(90.0F * this.field_0002.method_21227());
         var2.i.rotateAngleX = (float)Math.toRadians(90.0F * this.field_0002.method_21227());
         var2.bipedRightArmwear.rotateAngleX = (float)Math.toRadians(90.0F * this.field_0002.method_21227());
         var2.bipedLeftArmwear.rotateAngleX = (float)Math.toRadians(90.0F * this.field_0002.method_21227());
      } else if (this.field_0001.method_21229() <= this.field_0003.method_21240()) {
         if (!this.field_0003.method_21217()) {
            this.field_0003.method_20200();
         }

         var2.h.rotateAngleX = Math.max((float)Math.toRadians(90.0F - 90.0F * this.field_0003.method_21227()), var2.h.rotateAngleZ);
         var2.i.rotateAngleX = Math.min((float)Math.toRadians(-270.0F - 90.0F * this.field_0003.method_21227()), var2.h.rotateAngleZ);
         var2.bipedRightArmwear.rotateAngleX = Math.max(
            (float)Math.toRadians(90.0F - 90.0F * this.field_0003.method_21227()), var2.bipedRightArmwear.rotateAngleZ
         );
         var2.bipedLeftArmwear.rotateAngleX = Math.min(
            (float)Math.toRadians(-270.0F - 90.0F * this.field_0003.method_21227()), var2.bipedRightArmwear.rotateAngleZ
         );
      } else {
         var1.setSneaking(true);
         var2.h.rotateAngleX = (float)Math.toRadians(90.0);
         var2.i.rotateAngleX = (float)Math.toRadians(90.0);
         var2.bipedRightArmwear.rotateAngleX = (float)Math.toRadians(90.0);
         var2.bipedLeftArmwear.rotateAngleX = (float)Math.toRadians(90.0);
      }
   }
}
