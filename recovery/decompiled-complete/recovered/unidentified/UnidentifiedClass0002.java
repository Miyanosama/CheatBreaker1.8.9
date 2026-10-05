package recovered.unidentified;

import io.netty.util.concurrent.GlobalEventExecutor$PurgeTask;
import net.minecraft.command.server.CommandPublishLocalServer;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import org.apache.log4j.net.SocketNode;

public class UnidentifiedClass0002 {
   public int field_0003;
   public float field_0005;
   public GlobalEventExecutor$PurgeTask field_0002;
   public SocketNode field_0004;
   public CommandPublishLocalServer field_0000;
   public S13PacketDestroyEntities field_0001;

   @Override
   public int hashCode() {
      int var1 = this.field_0003;
      return 31 * var1 + (this.field_0005 != 0.0F ? Float.floatToIntBits(this.field_0005) : 0);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof UnidentifiedClass0002)) {
         return false;
      } else {
         UnidentifiedClass0002 var2 = (UnidentifiedClass0002)var1;
         return this.field_0003 == var2.field_0003 && Float.compare(var2.field_0005, this.field_0005) == 0;
      }
   }

   public float method_00007() {
      return this.field_0003 * this.field_0005 * 2.0F;
   }

   public UnidentifiedClass0002(int var1, float var2) {
      this.field_0003 = var1;
      this.field_0005 = var2;
   }
}
