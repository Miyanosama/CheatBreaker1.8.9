package recovered.unidentified;

import com.cheatbreaker.client.event.EventBus$Event;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import org.json.JSONObject;

public class UnidentifiedClass1798 extends EventBus$Event {
   public LayerEndermanEyes field_0002;
   public JSONObject field_0001;
   public ScaledResolution field_0000;

   public UnidentifiedClass1798(ScaledResolution var1) {
      this.field_0000 = var1;
   }

   public ScaledResolution method_12440() {
      return this.field_0000;
   }
}
