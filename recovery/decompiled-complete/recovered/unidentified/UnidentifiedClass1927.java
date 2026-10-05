package recovered.unidentified;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import com.cheatbreaker.client.nethandler.obj.ServerRule;
import net.minecraft.client.renderer.entity.RenderPigZombie$1;

public class UnidentifiedClass1927 extends Packet {
   public ServerRule field_0000;
   public float field_0001;
   public RenderPigZombie$1 field_0004;
   public boolean field_0003;
   public String field_0002 = "";
   public int field_0005;

   public boolean method_13131() {
      return this.field_0003;
   }

   public ServerRule method_13129() {
      return this.field_0000;
   }

   public UnidentifiedClass1927(ServerRule var1, float var2) {
      this(var1);
      this.field_0001 = var2;
   }

   public UnidentifiedClass1927(ServerRule var1) {
      this.field_0000 = var1;
   }

   public UnidentifiedClass1927(ServerRule var1, int var2) {
      this(var1);
      this.field_0005 = var2;
   }

   public UnidentifiedClass1927() {
   }

   public UnidentifiedClass1927(ServerRule var1, boolean var2) {
      this(var1);
      this.field_0003 = var2;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.field_0000.getRuleName());
      var1.buf().writeBoolean(this.field_0003);
      var1.buf().writeInt(this.field_0005);
      var1.buf().writeFloat(this.field_0001);
      var1.writeString(this.field_0002);
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11450(this);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.field_0000 = ServerRule.getRuleName(var1.readString());
      this.field_0003 = var1.buf().readBoolean();
      this.field_0005 = var1.buf().readInt();
      this.field_0001 = var1.buf().readFloat();
      this.field_0002 = var1.readString();
   }

   public String method_13130() {
      return this.field_0002;
   }

   public float method_13127() {
      return this.field_0001;
   }

   @Override
   public byte[] readBlob(ByteBufWrapper var1) {
      return super.readBlob(var1);
   }

   public int method_13128() {
      return this.field_0005;
   }

   public UnidentifiedClass1927(ServerRule var1, String var2) {
      this(var1);
      this.field_0002 = var2;
   }
}
