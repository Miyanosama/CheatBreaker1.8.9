package recovered.unidentified;

import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.network.play.server.S0FPacketSpawnMob;

public class UnidentifiedClass0030 extends UnidentifiedClass0300 {
   public FloatFade field_0000;
   public CosineFade field_0001;
   public S0FPacketSpawnMob field_0003;
   public FloatFade field_0002 = new FloatFade(1279149307L & 50403834L);

   public UnidentifiedClass0030() {
      super("Wave", new FloatFade(38692829L & 6607203360636409808L));
      this.field_0000 = new FloatFade(373477627L & 538445050L);
      this.field_0001 = new CosineFade(6291965L & 1377313780L);
      this.field_0002.method_20200();
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      float var4 = 1.0F;
      float var5 = 0.5F;
      if (this.field_0002.method_21240() > this.field_0001.method_21207()) {
         var4 = this.field_0002.method_21227();
      } else if (this.field_0001.method_21229() <= this.field_0000.method_21240()) {
         if (!this.field_0000.method_21217()) {
            this.field_0000.method_20200();
         }

         var4 = 1.0F - this.field_0000.method_21227();
      } else {
         if (!this.field_0001.method_21217()) {
            this.field_0001.method_21221(125.0F);
            this.field_0001.method_21234();
         }

         var5 = this.field_0001.method_21227();
      }

      var2.i.rotateAngleX = (float)Math.toRadians(-150.0F * var4);
      var2.i.rotateAngleZ = (float)Math.toRadians(40.0F * var5 - 20.0F);
      var2.bipedLeftArmwear.rotateAngleX = (float)Math.toRadians(-150.0F * var4);
      var2.bipedLeftArmwear.rotateAngleZ = (float)Math.toRadians(40.0F * var5 - 20.0F);
   }
}
