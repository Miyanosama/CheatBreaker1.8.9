package io.netty.channel.oio;

import io.netty.buffer.PoolArena$DirectArena;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelPipeline;
import io.netty.util.Recycler$WeakOrderQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiOptionsRowList$Row;
import net.minecraft.client.renderer.ImageBufferDownload;
import recovered.unidentified.UnidentifiedClass0525;
import recovered.unidentified.UnidentifiedClass1590;

public abstract class AbstractOioMessageChannel extends AbstractOioChannel {
   public PoolArena$DirectArena __junk5449480308963331422;
   public Recycler$WeakOrderQueue __junk3838582847992447697;
   public List<Object> readBuf = new ArrayList<>();
   public UnidentifiedClass1590 __junk485037430724820563;
   public UnidentifiedClass0525 __junk5409453848229446156;
   public ImageBufferDownload __junk9025510401020713730;
   public GuiOptionsRowList$Row __junk5916032847826548007;

   public abstract int doReadMessages(List<Object> var1);

   public AbstractOioMessageChannel(Channel var1) {
      super(var1);
   }

   @Override
   public void doRead() {
      ChannelConfig var1 = this.config();
      ChannelPipeline var2 = this.pipeline();
      boolean var3 = false;
      int var4 = var1.getMaxMessagesPerRead();
      Throwable var5 = null;
      int var6 = 0;

      try {
         do {
            var6 = this.doReadMessages(this.readBuf);
            if (var6 == 0) {
               break;
            }

            if (var6 < 0) {
               var3 = true;
               break;
            }
         } while (this.readBuf.size() < var4 && var1.isAutoRead());
      } catch (Throwable var9) {
         var5 = var9;
      }

      int var7 = this.readBuf.size();

      for (int var8 = 0; var8 < var7; var8++) {
         var2.fireChannelRead(this.readBuf.get(var8));
      }

      this.readBuf.clear();
      var2.fireChannelReadComplete();
      if (var5 != null) {
         if (var5 instanceof IOException) {
            var3 = true;
         }

         this.pipeline().fireExceptionCaught(var5);
      }

      if (var3) {
         if (this.isOpen()) {
            this.unsafe().close(this.unsafe().voidPromise());
         }
      } else if (var6 == 0 && this.isActive()) {
         this.read();
      }
   }
}
