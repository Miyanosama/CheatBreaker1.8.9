package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import net.minecraft.block.BlockDoublePlant$EnumBlockHalf;
import net.minecraft.entity.passive.EntityVillager$ListItemForEmeralds;

public class FixedLengthFrameDecoder extends ByteToMessageDecoder {
   public EntityVillager$ListItemForEmeralds __junk9012841610269296301;
   public int frameLength;
   public BlockDoublePlant$EnumBlockHalf __junk3863161593165064295;

   public FixedLengthFrameDecoder(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("frameLength must be a positive integer: " + var1);
      } else {
         this.frameLength = var1;
      }
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      Object var4 = this.decode(var1, var2);
      if (var4 != null) {
         var3.add(var4);
      }
   }

   public Object decode(ChannelHandlerContext var1, ByteBuf var2) {
      return var2.readableBytes() < this.frameLength ? null : var2.readSlice(this.frameLength).retain();
   }
}
