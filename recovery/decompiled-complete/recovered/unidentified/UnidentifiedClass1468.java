package recovered.unidentified;

import com.cheatbreaker.client.BuildBranch;
import com.cheatbreaker.client.CheatBreaker;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.optifine.util.KeyUtils;

public class UnidentifiedClass1468 {
   public BuildBranch field_0001;
   public MessageToMessageDecoder field_0002;
   public KeyUtils field_0000;

   public BuildBranch method_10217() {
      return this.field_0001;
   }

   public void method_10218(BuildBranch var1) {
      this.field_0001 = var1;
   }

   public UnidentifiedClass1468() {
      CheatBreaker.getInstance().method_19789().info(CheatBreaker.getInstance().method_19748() + "Created Branch Manager");
   }
}
