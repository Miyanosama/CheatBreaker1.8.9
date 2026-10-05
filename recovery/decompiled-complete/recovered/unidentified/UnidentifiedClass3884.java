package recovered.unidentified;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.crash.CrashReport$3;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import org.apache.log4j.chainsaw.MyTableModel$1;
import org.apache.log4j.helpers.PatternParser$DatePatternConverter;
import org.slf4j.MDC$1;

public class UnidentifiedClass3884 extends Packet {
   public MDC$1 field_0001;
   public MyTableModel$1 field_0002;
   public CrashReport$3 field_0006;
   public PatternParser$DatePatternConverter field_0005;
   public String field_0003;
   public EntitySnowball field_0007;
   public WSPacket field_0000;
   public WorldGenBigMushroom field_0004;

   public String method_23476() {
      return this.field_0003;
   }

   public UnidentifiedClass3884() {
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.field_0003);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.field_0003 = var1.readString();
   }

   public UnidentifiedClass3884(String var1) {
      this.field_0003 = var1;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11462(this);
   }
}
