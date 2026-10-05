package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObjectDecoder;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceEntriesToDoubleTask;
import io.netty.util.internal.chmv8.ForkJoinTask$1;
import javazoom.jl.player.FactoryRegistry;
import net.minecraft.block.BlockRail;
import net.minecraft.client.stream.IngestServerTester;

public abstract class RtspObjectDecoder extends HttpObjectDecoder {
   public ConcurrentHashMapV8$MapReduceEntriesToDoubleTask __junk3175262867826470122;
   public IngestServerTester __junk1071607788167520872;
   public ForkJoinTask$1 __junk7563624546479960178;
   public FactoryRegistry __junk8804453724494440949;
   public BlockRail __junk4037046709899005641;

   public RtspObjectDecoder(int var1, int var2, int var3, boolean var4) {
      super(var1, var2, var3 * 2, false, var4);
   }

   public RtspObjectDecoder(int var1, int var2, int var3) {
      super(var1, var2, var3 * 2, false);
   }

   @Override
   public boolean isContentAlwaysEmpty(HttpMessage var1) {
      boolean var2 = super.isContentAlwaysEmpty(var1);
      if (var2) {
         return true;
      } else {
         return !var1.headers().contains("Content-Length") ? true : var2;
      }
   }

   public RtspObjectDecoder() {
      this(4096, 8192, 8192);
   }
}
