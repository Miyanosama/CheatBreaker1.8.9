package org.java_websocket.server;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import net.minecraft.command.PlayerSelector$5;
import net.minecraft.entity.monster.EntityZombie$GroupData;
import net.minecraft.item.crafting.RecipesBanners;
import net.optifine.CustomColormap;
import net.optifine.gui.TooltipManager;
import org.java_websocket.WebSocketImpl;

public class WebSocketServer$WebSocketWorker extends Thread {
   public TooltipManager field_0003;
   public EntityZombie$GroupData field_0006;
   public CustomColormap field_0000;
   public PlayerSelector$5 field_0001;
   public RecipesBanners field_0007;
   public BlockingQueue<WebSocketImpl> iqueue;

   public WebSocketServer$WebSocketWorker(WebSocketServer var1) {
      this.this$0 = var1;
      super();
      this.iqueue = new LinkedBlockingQueue<>();
      this.setName("WebSocketWorker-" + this.getId());
      this.setUncaughtExceptionHandler(new WebSocketServer$WebSocketWorker$1(this, var1));
   }

   public void doDecode(WebSocketImpl var1, ByteBuffer var2) {
      try {
         var1.decode(var2);
      } catch (Exception var7) {
         WebSocketServer.access$000(this.this$0).error("Error while reading from remote connection", (Throwable)var7);
      } finally {
         WebSocketServer.access$200(this.this$0, var2);
      }
   }

   public void put(WebSocketImpl var1) {
      this.iqueue.put(var1);
   }

   @Override
   public void run() {
      WebSocketImpl var1 = null;

      try {
         while (true) {
            var1 = this.iqueue.take();
            ByteBuffer var2 = var1.inQueue.poll();
            if (!$assertionsDisabled && var2 == null) {
               throw new AssertionError();
            }

            this.doDecode(var1, var2);
            var1 = null;
         }
      } catch (InterruptedException var3) {
         Thread.currentThread().interrupt();
      } catch (RuntimeException var4) {
         WebSocketServer.access$100(this.this$0, var1, var4);
      }
   }
}
