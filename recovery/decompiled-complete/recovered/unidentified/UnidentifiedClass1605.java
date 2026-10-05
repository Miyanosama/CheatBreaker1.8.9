package recovered.unidentified;

import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.world.WorldServer;

public abstract class UnidentifiedClass1605 {
   public String[] field_0002;
   public String field_0004;
   public S3CPacketUpdateScore field_0001;
   public WorldServer field_0003;
   public String field_0000;

   public UnidentifiedClass1605(String var1, String var2, String[] var3) {
      this.field_0004 = var1;
      this.field_0000 = var2;
      this.field_0002 = var3;
   }

   public String[] method_10953() {
      return this.field_0002;
   }

   public String method_10954() {
      return this.field_0000;
   }

   public String method_10952() {
      return this.field_0004;
   }
}
