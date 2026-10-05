package recovered.unidentified;

import com.cheatbreaker.client.ui.mainmenu.MainMenuBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import javax.crypto.SecretKey;

public class UnidentifiedClass4078 extends ChannelInboundHandlerAdapter {
   public long field_0003;
   public long field_0005;
   public MainMenuBase field_0002;
   public long field_0004;
   public long field_0000;
   public byte[] field_0001 = "cf2O02b1QJSZOcVHphHucA".getBytes();

   public long method_24511() {
      return this.field_0000;
   }

   public UnidentifiedClass4078(SecretKey var1) {
      this.field_0003 = -1005459627078106527L & 21233799L;
      this.field_0005 = -6744052224296484864L & 353663158L;

      for (byte var5 : var1.getEncoded()) {
         this.field_0003 = (this.field_0003 + (var5 & 255)) % (3907807627077353457L & 1079050225L);
         this.field_0005 = (this.field_0005 + this.field_0003) % (152109045L & 3898317119105990651L);
      }
   }

   public long method_24513() {
      return this.field_0005;
   }

   public long method_24510() {
      return this.field_0004;
   }

   public byte[] method_24512() {
      return this.field_0001;
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      ByteBuf var3 = (ByteBuf)var2;

      while (var3.readableBytes() > 0) {
         int var4 = var3.readByte() & 255;
         this.field_0003 = (this.field_0003 + var4) % (4214139479677534197L & 135462897L);
         this.field_0005 = (this.field_0005 + this.field_0003) % (402980853L & -3578602915681468431L);
      }

      var3.readerIndex(0);

      for (byte var7 : this.field_0001) {
         this.field_0003 = (this.field_0003 + (var7 & 255)) % (6422525L & -291942404648271887L);
         this.field_0005 = (this.field_0005 + this.field_0003) % (-2978279526713131015L & 2978279526469664759L);
      }

      this.field_0000 = this.field_0004;
      this.field_0004 = this.field_0005 << 16 | this.field_0003;

      try {
         super.channelRead(var1, var2);
      } catch (Exception var8) {
         var8.printStackTrace();
      }
   }

   public long method_24509() {
      return this.field_0003;
   }
}
