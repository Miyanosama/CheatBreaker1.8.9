package recovered.unidentified;

import com.cheatbreaker.client.ui.fading.CosineFade;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.resources.GrassColorReloadListener;
import net.minecraft.network.play.server.S46PacketSetCompressionLevel;

public class UnidentifiedClass1182 extends UnidentifiedClass0300 {
   public GrassColorReloadListener field_0000;
   public S46PacketSetCompressionLevel field_0001;

   @Override
   public void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3) {
      var2.h.offsetY = -0.2F * this.field_0001.method_21227();
      var2.i.offsetY = -0.2F * this.field_0001.method_21227();
      var2.bipedRightArmwear.offsetY = -0.2F * this.field_0001.method_21227();
      var2.bipedLeftArmwear.offsetY = -0.2F * this.field_0001.method_21227();
      var2.e.offsetY = 0.05F * this.field_0001.method_21227();
      var2.f.offsetY = 0.05F * this.field_0001.method_21227();
   }

   @Override
   public void method_00243(AbstractClientPlayer var1, float var2) {
   }

   public UnidentifiedClass1182() {
      super("Shrug", new CosineFade(-2461285629060599306L & 2461285628344797685L));
   }
}
