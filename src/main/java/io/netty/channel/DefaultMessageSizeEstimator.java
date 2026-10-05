package io.netty.channel;

import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.socks.SocksAuthResponseDecoder;
import io.netty.util.HashedWheelTimer;
import junit.framework.TestSuite;
import net.minecraft.block.BlockBanner;
import net.minecraft.world.gen.ChunkProviderHell;
import com.cheatbreaker.client.util.branch.BranchManager;

public class DefaultMessageSizeEstimator implements MessageSizeEstimator {
   public static MessageSizeEstimator DEFAULT = new DefaultMessageSizeEstimator(0);
   public MessageSizeEstimator.Handle handle;

   public DefaultMessageSizeEstimator(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("unknownSize: " + var1 + " (expected: >= 0)");
      } else {
         this.handle = new DefaultMessageSizeEstimator.HandleImpl(var1);
      }
   }

   @Override
   public MessageSizeEstimator.Handle newHandle() {
      return this.handle;
   }

   public static final class HandleImpl implements MessageSizeEstimator.Handle {
      public int unknownSize;

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

      public HandleImpl(int var1) {
         this.unknownSize = var1;
      }
   }
}
