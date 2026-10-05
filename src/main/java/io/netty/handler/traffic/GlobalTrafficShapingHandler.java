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
import javazoom.jl.decoder.Bitstream;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.command.CommandNotFoundException;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.network.play.server.S46PacketSetCompressionLevel;
import com.cheatbreaker.client.module.type.TeammateHudSide;

public class GlobalTrafficShapingHandler extends AbstractTrafficShapingHandler {
   public Map<Integer, List<GlobalTrafficShapingHandler.ToSend>> messagesQueues = new HashMap<>();

   public synchronized void sendAllValid(ChannelHandlerContext var1, List<GlobalTrafficShapingHandler.ToSend> var2) {
      while (!var2.isEmpty()) {
         GlobalTrafficShapingHandler.ToSend var3 = (GlobalTrafficShapingHandler.ToSend)var2.remove(0);
         if (var3.date > System.currentTimeMillis()) {
            var2.add(0, var3);
            break;
         }

         var1.write(var3.toSend, var3.promise);
      }

      var1.flush();
   }

   @Override
   public synchronized void handlerRemoved(ChannelHandlerContext var1) throws java.lang.Exception {
      Integer var2 = var1.channel().hashCode();
      List var3 = this.messagesQueues.remove(var2);
      if (var3 != null) {
         for (GlobalTrafficShapingHandler.ToSend var5 : (Iterable<GlobalTrafficShapingHandler.ToSend>)(Iterable<?>)(var3)) {
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
   public void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception {
      Integer var2 = var1.channel().hashCode();
      LinkedList var3 = new LinkedList();
      this.messagesQueues.put(var2, var3);
   }

   @Override
   public synchronized void submitWrite(final ChannelHandlerContext var1, Object var2, long var3, ChannelPromise var5) {
      Integer var6 = var1.channel().hashCode();
      List<GlobalTrafficShapingHandler.ToSend> var7 = this.messagesQueues.get(var6);
      if (var3 != 0L || var7 != null && !var7.isEmpty()) {
         GlobalTrafficShapingHandler.ToSend var8 = new GlobalTrafficShapingHandler.ToSend(var3, var2, var5);
         if (var7 == null) {
            var7 = new LinkedList();
            this.messagesQueues.put(var6, (List<GlobalTrafficShapingHandler.ToSend>)var7);
         }

         var7.add(var8);
         final List<GlobalTrafficShapingHandler.ToSend> pendingWrites = var7;
         var1.executor().schedule(new Runnable() {

            @Override
            public void run() {
               GlobalTrafficShapingHandler.this.sendAllValid(var1, pendingWrites);
            }
         }, var3, TimeUnit.MILLISECONDS);
      } else {
         var1.write(var2, var5);
      }
   }

   public static final class ToSend {
      public long date;
      public Object toSend;
      public ChannelPromise promise;

      public ToSend(long var1, Object var3, ChannelPromise var4) {
         this.date = System.currentTimeMillis() + var1;
         this.toSend = var3;
         this.promise = var4;
      }
   }
}
