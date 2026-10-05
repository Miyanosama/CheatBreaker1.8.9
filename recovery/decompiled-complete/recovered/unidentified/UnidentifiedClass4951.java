package recovered.unidentified;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Deflater;
import net.minecraft.block.BlockBarrier;
import net.minecraft.inventory.ContainerRepair$2;
import net.minecraft.item.ItemColored;
import net.minecraft.network.PacketBuffer;
import org.java_websocket.util.ByteBufferUtils;

public class UnidentifiedClass4951 extends MessageToByteEncoder<ByteBuf> {
   public Deflater field_0003;
   public ContainerRepair$2 field_0005;
   public byte[] field_0002 = new byte[8192];
   public ItemColored field_0004;
   public int field_0000;
   public BlockBarrier field_0001;
   public ByteBufferUtils field_0006;

   public void method_29533(int var1) {
      this.field_0000 = var1;
   }

   public void method_29534(ChannelHandlerContext var1, ByteBuf var2, ByteBuf var3) {
      int var4 = var2.readableBytes();
      PacketBuffer var5 = new PacketBuffer(var3);
      if (var4 < this.field_0000) {
         var5.writeVarIntToBuffer(0);
         var5.writeBytes(var2);
      } else {
         byte[] var6 = new byte[var4];
         var2.readBytes(var6);
         var5.writeVarIntToBuffer(var6.length);
         this.field_0003.setInput(var6, 0, var4);
         this.field_0003.finish();

         while (!this.field_0003.finished()) {
            int var7 = this.field_0003.deflate(this.field_0002);
            var5.writeBytes(this.field_0002, 0, var7);
         }

         this.field_0003.reset();
      }
   }

   public UnidentifiedClass4951(int var1) {
      this.field_0000 = var1;
      this.field_0003 = new Deflater();
   }
}
