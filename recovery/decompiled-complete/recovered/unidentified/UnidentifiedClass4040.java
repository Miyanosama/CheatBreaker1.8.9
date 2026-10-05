package recovered.unidentified;

import com.cheatbreaker.client.module.staff.StaffModule;
import com.cheatbreaker.client.nethandler.server.PacketUpdateNametags;
import io.netty.util.DefaultAttributeMap;

public class UnidentifiedClass4040 extends StaffModule {
   public DefaultAttributeMap field_0000;
   public PacketUpdateNametags field_0001;

   public void method_24325(UnidentifiedClass3475 var1) {
      var1.method_10233(true);
   }

   public UnidentifiedClass4040() {
      super("noclip");
      this.method_28828(true);
      this.method_28820(UnidentifiedClass3475.class, this::method_24325);
   }
}
