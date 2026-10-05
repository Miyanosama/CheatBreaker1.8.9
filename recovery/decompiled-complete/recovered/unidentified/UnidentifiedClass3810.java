package recovered.unidentified;

import com.cheatbreaker.client.event.EventBus$Event;
import io.netty.handler.codec.rtsp.RtspMethods;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.ItemModelMesher;

public class UnidentifiedClass3810 extends EventBus$Event {
   public ModelPlayer field_0004;
   public float field_0003;
   public ItemModelMesher field_0001;
   public AbstractClientPlayer field_0005;
   public RtspMethods field_0000;
   public UnidentifiedEnum0393 field_0002;

   public UnidentifiedEnum0393 method_23156() {
      return this.field_0002;
   }

   public UnidentifiedClass3810(UnidentifiedEnum0393 var1, AbstractClientPlayer var2, ModelPlayer var3, float var4) {
      this.field_0002 = var1;
      this.field_0005 = var2;
      this.field_0004 = var3;
      this.field_0003 = var4;
   }

   public ModelPlayer method_23158() {
      return this.field_0004;
   }

   public AbstractClientPlayer method_23155() {
      return this.field_0005;
   }

   public float method_23157() {
      return this.field_0003;
   }
}
