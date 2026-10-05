package recovered.unidentified;

import com.cheatbreaker.client.ui.fading.MinMaxFade;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.timeout.IdleStateHandler$1;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.world.gen.layer.GenLayerRiverInit;

public class UnidentifiedClass4157 extends UnidentifiedClass0300 {
   public MessageToMessageEncoder field_0001;
   public UnidentifiedClass4541 field_0002;
   public GenLayerRiverInit field_0005;
   public IdleStateHandler$1 field_0004;
   public MinMaxFade field_0003 = new MinMaxFade(2833165312340266238L & 567804155L);
   public MinMaxFade field_0006 = new MinMaxFade(1258467149777742591L & 349094138L);
   public EntityAILookAtTradePlayer field_0000;

   public UnidentifiedClass4157() {
      super("Dab", new MinMaxFade(-5129818599073954838L & 5129818598327233513L));
      this.field_0003.method_20200();
   }

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      float var4 = 1.0F;
      if (this.field_0003.method_21240() > this.field_0001.method_21207()) {
         var4 = this.field_0003.method_21227();
      } else if (this.field_0001.method_21229() <= this.field_0006.method_21240()) {
         if (!this.field_0006.method_21217()) {
            this.field_0006.method_20200();
         }

         var4 = 1.0F - this.field_0006.method_21227();
      }

      var2.h.rotateAngleX = (float)Math.toRadians(-90.0F * var4);
      var2.h.rotateAngleY = (float)Math.toRadians(-35.0F * var4);
      var2.i.rotateAngleX = (float)Math.toRadians(15.0F * var4);
      var2.i.rotateAngleY = (float)Math.toRadians(15.0F * var4);
      var2.i.rotateAngleZ = (float)Math.toRadians(-110.0F * var4);
      var2.bipedRightArmwear.rotateAngleX = (float)Math.toRadians(-90.0F * var4);
      var2.bipedRightArmwear.rotateAngleY = (float)Math.toRadians(-35.0F * var4);
      var2.bipedLeftArmwear.rotateAngleX = (float)Math.toRadians(15.0F * var4);
      var2.bipedLeftArmwear.rotateAngleY = (float)Math.toRadians(15.0F * var4);
      var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(-110.0F * var4);
      float var5 = var1.z;
      float var6 = var1.aJ - var1.y;
      var2.e.rotateAngleX = (float)Math.toRadians(-var5 * var4) + (float)Math.toRadians(45.0F * var4 + var5);
      var2.e.rotateAngleY = (float)Math.toRadians(var6 * var4) + (float)Math.toRadians(35.0F * var4 - var6);
      var2.f.rotateAngleX = (float)Math.toRadians(-var5 * var4) + (float)Math.toRadians(45.0F * var4 + var5);
      var2.f.rotateAngleY = (float)Math.toRadians(var6 * var4) + (float)Math.toRadians(35.0F * var4 - var6);
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }
}
