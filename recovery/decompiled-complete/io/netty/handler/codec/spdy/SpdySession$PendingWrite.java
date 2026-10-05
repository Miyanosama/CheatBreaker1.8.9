package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.MultithreadEventExecutorGroup$1;
import net.minecraft.entity.item.EntityItemFrame;
import recovered.unidentified.UnidentifiedClass1092;

public class SpdySession$PendingWrite {
   public UnidentifiedClass1092 __junk1645141536462261499;
   public SpdyDataFrame spdyDataFrame;
   public MultithreadEventExecutorGroup$1 __junk2068252126411471978;
   public EntityItemFrame __junk4374451746887797515;
   public ChannelPromise promise;

   public SpdySession$PendingWrite(SpdyDataFrame var1, ChannelPromise var2) {
      this.spdyDataFrame = var1;
      this.promise = var2;
   }

   public void fail(Throwable var1) {
      this.spdyDataFrame.release();
      this.promise.setFailure(var1);
   }
}
