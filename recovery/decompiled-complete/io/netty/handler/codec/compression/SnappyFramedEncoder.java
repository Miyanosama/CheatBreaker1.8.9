package io.netty.handler.codec.compression;

import com.cheatbreaker.client.ui.element.type.ColorPickerColorElement;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator$AggregatedFullHttpResponse;
import net.minecraft.client.main.llIlllIIlllIIllIIlllIlIII;
import net.minecraft.client.renderer.RenderList;
import net.minecraft.world.gen.structure.StructureVillagePieces$House1;

public class SnappyFramedEncoder extends MessageToByteEncoder<ByteBuf> {
   public llIlllIIlllIIllIIlllIlIII __junk1786105340424148956;
   public boolean started;
   public Snappy snappy = new Snappy();
   public StructureVillagePieces$House1 __junk2745331875759662879;
   public ColorPickerColorElement __junk8876356845927509800;
   public static byte[] STREAM_START = new byte[]{-1, 6, 0, 0, 115, 78, 97, 80, 112, 89};
   public RenderList __junk2082913768961792752;
   public HttpObjectAggregator$AggregatedFullHttpResponse __junk1357577203595604428;
   public static int MIN_COMPRESSIBLE_LENGTH;

   public static void setChunkLength(ByteBuf var0, int var1) {
      int var2 = var0.writerIndex() - var1 - 3;
      if (var2 >>> 24 != 0) {
         throw new CompressionException("compressed data too large: " + var2);
      } else {
         var0.setMedium(var1, ByteBufUtil.swapMedium(var2));
      }
   }

   public static void writeChunkLength(ByteBuf var0, int var1) {
      var0.writeMedium(ByteBufUtil.swapMedium(var1));
   }

   public static void writeUnencodedChunk(ByteBuf var0, ByteBuf var1, int var2) {
      var1.writeByte(1);
      writeChunkLength(var1, var2 + 4);
      calculateAndWriteChecksum(var0, var1);
      var1.writeBytes(var0, var2);
   }

   public void encode(ChannelHandlerContext var1, ByteBuf var2, ByteBuf var3) {
      if (var2.isReadable()) {
         if (!this.started) {
            this.started = true;
            var3.writeBytes(STREAM_START);
         }

         int var4 = var2.readableBytes();
         if (var4 <= 18) {
            writeUnencodedChunk(var2, var3, var4);
         } else {
            while (true) {
               int var5 = var3.writerIndex() + 1;
               if (var4 < 18) {
                  ByteBuf var8 = var2.readSlice(var4);
                  writeUnencodedChunk(var8, var3, var4);
                  break;
               }

               var3.writeInt(0);
               if (var4 <= 32767) {
                  ByteBuf var7 = var2.readSlice(var4);
                  calculateAndWriteChecksum(var7, var3);
                  this.snappy.encode(var7, var3, var4);
                  setChunkLength(var3, var5);
                  break;
               }

               ByteBuf var6 = var2.readSlice(32767);
               calculateAndWriteChecksum(var6, var3);
               this.snappy.encode(var6, var3, 32767);
               setChunkLength(var3, var5);
               var4 -= 32767;
            }
         }
      }
   }

   public static void calculateAndWriteChecksum(ByteBuf var0, ByteBuf var1) {
      var1.writeInt(ByteBufUtil.swapInt(Snappy.calculateChecksum(var0)));
   }
}
