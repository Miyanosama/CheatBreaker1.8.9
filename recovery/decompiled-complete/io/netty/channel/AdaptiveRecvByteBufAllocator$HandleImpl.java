package io.netty.channel;

import com.cheatbreaker.client.event.EventBus$Event;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import net.minecraft.util.ObjectIntIdentityMap;
import org.java_websocket.framing.CloseFrame;
import org.slf4j.helpers.BasicMarker;

public class AdaptiveRecvByteBufAllocator$HandleImpl implements RecvByteBufAllocator$Handle {
   public int maxIndex;
   public boolean decreaseNow;
   public ObjectIntIdentityMap __junk1235616458303103876;
   public int nextReceiveBufferSize;
   public int index;
   public CloseFrame __junk1053003829958088029;
   public int minIndex;
   public BasicMarker __junk7724448229035067501;
   public EventBus$Event __junk3421412092989804253;

   @Override
   public int guess() {
      return this.nextReceiveBufferSize;
   }

   @Override
   public void record(int var1) {
      if (var1 <= AdaptiveRecvByteBufAllocator.access$100()[Math.max(0, this.index - 1 - 1)]) {
         if (this.decreaseNow) {
            this.index = Math.max(this.index - 1, this.minIndex);
            this.nextReceiveBufferSize = AdaptiveRecvByteBufAllocator.access$100()[this.index];
            this.decreaseNow = false;
         } else {
            this.decreaseNow = true;
         }
      } else if (var1 >= this.nextReceiveBufferSize) {
         this.index = Math.min(this.index + 4, this.maxIndex);
         this.nextReceiveBufferSize = AdaptiveRecvByteBufAllocator.access$100()[this.index];
         this.decreaseNow = false;
      }
   }

   @Override
   public ByteBuf allocate(ByteBufAllocator var1) {
      return var1.ioBuffer(this.nextReceiveBufferSize);
   }

   public AdaptiveRecvByteBufAllocator$HandleImpl(int var1, int var2, int var3) {
      this.minIndex = var1;
      this.maxIndex = var2;
      this.index = AdaptiveRecvByteBufAllocator.access$000(var3);
      this.nextReceiveBufferSize = AdaptiveRecvByteBufAllocator.access$100()[this.index];
   }
}
