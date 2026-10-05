package net.minecraft.world.chunk;

import io.netty.channel.AbstractChannelHandlerContext$5;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Session;

public class Chunk$3 implements Callable<String> {
   public WebSocket08FrameDecoder$State field_0005;
   public Session field_0002;
   public HttpRequestEncoder field_0004;
   public AbstractChannelHandlerContext$5 field_0001;

   public Chunk$3(Chunk var1, BlockPos var2) {
      this.field_177449_b = var1;
      this.field_177450_a = var2;
      super();
   }

   public String call() {
      return CrashReportCategory.getCoordinateInfo(this.field_177450_a);
   }
}
