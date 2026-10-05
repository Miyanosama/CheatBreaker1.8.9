package io.netty.channel;

import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.handler.codec.http.HttpObjectAggregator$AggregatedFullHttpRequest;
import io.netty.util.HashedWheelTimer$HashedWheelTimeout;
import net.minecraft.block.BlockBanner$BlockBannerHanging;
import net.minecraft.world.gen.ChunkProviderHell;
import recovered.unidentified.UnidentifiedClass1468;

public class DefaultMessageSizeEstimator$HandleImpl implements MessageSizeEstimator$Handle {
   public ChunkProviderHell __junk5401297839341903451;
   public WSPacket __junk2437656119124209169;
   public BlockBanner$BlockBannerHanging __junk6510914503276277815;
   public HashedWheelTimer$HashedWheelTimeout __junk3121541887988723434;
   public int unknownSize;
   public UnidentifiedClass1468 __junk1708870969174956124;
   public HttpObjectAggregator$AggregatedFullHttpRequest __junk1247601454896833687;

   @Override
   public int size(Object var1) {
      if (var1 instanceof ByteBuf) {
         return ((ByteBuf)var1).readableBytes();
      } else if (var1 instanceof ByteBufHolder) {
         return ((ByteBufHolder)var1).content().readableBytes();
      } else {
         return var1 instanceof FileRegion ? 0 : this.unknownSize;
      }
   }

   public DefaultMessageSizeEstimator$HandleImpl(int var1) {
      this.unknownSize = var1;
   }
}
