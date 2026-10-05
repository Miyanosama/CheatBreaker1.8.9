package io.netty.handler.traffic;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.EventExecutor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.renderer.chunk.ChunkRenderWorker$1;
import net.minecraft.entity.ai.EntityAIFleeSun;

public class GlobalTrafficShapingHandler extends AbstractTrafficShapingHandler {
   public ChunkRenderWorker$1 __junk17857990310696889;
   public Map<Integer, List<GlobalTrafficShapingHandler$ToSend>> messagesQueues = new HashMap<>();
   public EntityAIFleeSun __junk4921729197686446013;

   public synchronized void sendAllValid(ChannelHandlerContext var1, List<GlobalTrafficShapingHandler$ToSend> var2) {
      while (!var2.isEmpty()) {
         GlobalTrafficShapingHandler$ToSend var3 = (GlobalTrafficShapingHandler$ToSend)var2.remove(0);
         if (var3.date > System.currentTimeMillis()) {
            var2.add(0, var3);
            break;
         }

         var1.write(var3.toSend, var3.promise);
      }

      var1.flush();
   }

   @Override
   public synchronized void handlerRemoved(ChannelHandlerContext var1) {
      Integer var2 = var1.channel().hashCode();
      List var3 = this.messagesQueues.remove(var2);
      if (var3 != null) {
         for (GlobalTrafficShapingHandler$ToSend var5 : var3) {
            if (var5.toSend instanceof ByteBuf) {
               ((ByteBuf)var5.toSend).release();
            }
         }

         var3.clear();
      }
   }

   public GlobalTrafficShapingHandler(ScheduledExecutorService var1, long var2, long var4) {
      super(var2, var4);
      this.createGlobalTrafficCounter(var1);
   }

   public GlobalTrafficShapingHandler(ScheduledExecutorService var1, long var2) {
      super(var2);
      this.createGlobalTrafficCounter(var1);
   }

   public GlobalTrafficShapingHandler(EventExecutor var1) {
      this.createGlobalTrafficCounter(var1);
   }

   public void release() {
      if (this.trafficCounter != null) {
         this.trafficCounter.stop();
      }
   }

   public void createGlobalTrafficCounter(ScheduledExecutorService var1) {
      if (var1 == null) {
         throw new NullPointerException("executor");
      } else {
         TrafficCounter var2 = new TrafficCounter(this, var1, "GlobalTC", this.checkInterval);
         this.setTrafficCounter(var2);
         var2.start();
      }
   }

   public GlobalTrafficShapingHandler(ScheduledExecutorService var1, long var2, long var4, long var6) {
      super(var2, var4, var6);
      this.createGlobalTrafficCounter(var1);
   }

   public GlobalTrafficShapingHandler(ScheduledExecutorService var1, long var2, long var4, long var6, long var8) {
      super(var2, var4, var6, var8);
      this.createGlobalTrafficCounter(var1);
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
      Integer var2 = var1.channel().hashCode();
      LinkedList var3 = new LinkedList();
      this.messagesQueues.put(var2, var3);
   }

   @Override
   public synchronized void submitWrite(ChannelHandlerContext var1, Object var2, long var3, ChannelPromise var5) {
      Integer var6 = var1.channel().hashCode();
      Object var7 = this.messagesQueues.get(var6);
      if (var3 != (369268737L & 640346780773909060L) || var7 != null && !var7.isEmpty()) {
         GlobalTrafficShapingHandler$ToSend var8 = new GlobalTrafficShapingHandler$ToSend(var3, var2, var5, null);
         if (var7 == null) {
            var7 = new LinkedList();
            this.messagesQueues.put(var6, (List<GlobalTrafficShapingHandler$ToSend>)var7);
         }

         var7.add(var8);
         var1.executor().schedule(new GlobalTrafficShapingHandler$1(this, var1, (List)var7), var3, TimeUnit.MILLISECONDS);
      } else {
         var1.write(var2, var5);
      }
   }
}
