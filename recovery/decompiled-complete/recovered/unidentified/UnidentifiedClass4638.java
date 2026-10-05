package recovered.unidentified;

import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.type.CoordinatesModule;
import com.google.common.base.Predicate;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.entity.Entity;
import net.minecraft.network.NetHandlerPlayServer$2;
import net.minecraft.util.AxisAlignedBB;

public class UnidentifiedClass4638 implements Predicate<Entity> {
   public ModelSlime field_0003;
   public WebSocketClientProtocolHandler field_0005;
   public SettingsDetailLevel field_0002;
   public CoordinatesModule field_0004;
   public NetHandlerPlayServer$2 field_0001;

   public boolean method_28010(Entity var1) {
      return var1.s >= this.field_0000.a && var1.t >= this.field_0000.b && var1.u >= this.field_0000.c
         ? var1.s < this.field_0000.d && var1.t < this.field_0000.e && var1.u < this.field_0000.f
         : false;
   }

   public UnidentifiedClass4638(AxisAlignedBB var1) {
      this.field_0000 = var1;
      super();
   }
}
