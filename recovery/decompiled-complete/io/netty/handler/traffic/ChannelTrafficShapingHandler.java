package io.netty.handler.traffic;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChannelTrafficShapingHandler extends AbstractTrafficShapingHandler {
   public WebSocketClientHandshaker __junk9164772804418657571;
   public List<ChannelTrafficShapingHandler$ToSend> messagesQueue = new LinkedList<>();

   public ChannelTrafficShapingHandler(long var1, long var3, long var5) {
      super(var1, var3, var5);
   }

   public synchronized void sendAllValid(ChannelHandlerContext var1) {
      while (!this.messagesQueue.isEmpty()) {
         ChannelTrafficShapingHandler$ToSend var2 = this.messagesQueue.remove(0);
         if (var2.date > System.currentTimeMillis()) {
            this.messagesQueue.add(0, var2);
            break;
         }

         var1.write(var2.toSend, var2.promise);
      }

      var1.flush();
   }

   public ChannelTrafficShapingHandler(long var1, long var3, long var5, long var7) {
      super(var1, var3, var5, var7);
   }

   @Override
   public synchronized void handlerRemoved(ChannelHandlerContext var1) {
      if (this.trafficCounter != null) {
         this.trafficCounter.stop();
      }

      for (ChannelTrafficShapingHandler$ToSend var3 : this.messagesQueue) {
         if (var3.toSend instanceof ByteBuf) {
            ((ByteBuf)var3.toSend).release();
         }
      }

      this.messagesQueue.clear();
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
      TrafficCounter var2 = new TrafficCounter(this, var1.executor(), "ChannelTC" + var1.channel().hashCode(), this.checkInterval);
      this.setTrafficCounter(var2);
      var2.start();
   }

   @Override
   public synchronized void submitWrite(ChannelHandlerContext var1, Object var2, long var3, ChannelPromise var5) {
      if (var3 == (-8343116269040408279L & 269287936L) && this.messagesQueue.isEmpty()) {
         var1.write(var2, var5);
      } else {
         ChannelTrafficShapingHandler$ToSend var6 = new ChannelTrafficShapingHandler$ToSend(var3, var2, var5, null);
         this.messagesQueue.add(var6);
         var1.executor().schedule(new ChannelTrafficShapingHandler$1(this, var1), var3, TimeUnit.MILLISECONDS);
      }
   }

   public ChannelTrafficShapingHandler(long var1) {
      super(var1);
   }

   public ChannelTrafficShapingHandler(long var1, long var3) {
      super(var1, var3);
   }
}
