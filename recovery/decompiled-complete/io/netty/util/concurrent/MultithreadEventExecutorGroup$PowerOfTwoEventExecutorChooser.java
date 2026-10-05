package io.netty.util.concurrent;

import io.netty.handler.codec.http.multipart.MemoryFileUpload;
import io.netty.handler.codec.spdy.SpdyHttpCodec;
import io.netty.handler.timeout.IdleStateHandler;
import net.optifine.util.IntegratedServerUtils;

public class MultithreadEventExecutorGroup$PowerOfTwoEventExecutorChooser implements MultithreadEventExecutorGroup$EventExecutorChooser {
   public IntegratedServerUtils __junk5465845813521856264;
   public IdleStateHandler __junk8225036411314754710;
   public SpdyHttpCodec __junk3378464128376275931;
   public MemoryFileUpload __junk5867424009931030387;

   @Override
   public EventExecutor next() {
      return MultithreadEventExecutorGroup.access$300(this.this$0)[MultithreadEventExecutorGroup.access$500(this.this$0).getAndIncrement()
         & MultithreadEventExecutorGroup.access$300(this.this$0).length - 1];
   }

   public MultithreadEventExecutorGroup$PowerOfTwoEventExecutorChooser(MultithreadEventExecutorGroup var1) {
      this.this$0 = var1;
      super();
   }
}
