package com.cheatbreaker.client.module.type.notifications;

import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.KeepAliveEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.type.CombatCounterModule;
import com.jagrosh.discordipc.entities.RichPresence$Builder;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceMappingsToIntTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.ServerStatusResponse;
import recovered.unidentified.UnidentifiedEnum3979;

public class CBNotificationsModule extends AbstractModule {
   public NioSocketChannel field_0002;
   public RichPresence$Builder field_0003;
   public ConcurrentHashMapV8$MapReduceMappingsToIntTask field_0000;
   public long time = System.currentTimeMillis();
   public ServerStatusResponse field_0005;
   public List<CBNotificationRenderer> notifications = new ArrayList<>();
   public CombatCounterModule field_0006;

   public CBNotificationsModule() {
      super("Notifications");
      this.method_28820(KeepAliveEvent.class, this::onKeepAlive);
      this.method_28820(TickEvent.class, this::onTick);
      this.method_28820(GuiDrawEvent.class, this::onDraw);
      this.setDefaultState(true);
   }

   public void onDraw(GuiDrawEvent var1) {
      for (CBNotificationRenderer var3 : this.notifications) {
         var3.method_08861(var1.getResolution().getScaledWidth());
      }
   }

   public void queueNotification(String var1, String var2, long var3) {
      ScaledResolution var5 = new ScaledResolution(this.minecraft);
      if (var3 < (-3677545743119771692L & 100665298L)) {
         var3 = -6624235666592854022L & 6624235665691781073L;
      }

      var2 = var2.replaceAll("&([abcdefghijklmrABCDEFGHIJKLMNR0-9])|(&$)", "§$1");
      String var7 = var1.toLowerCase();
      UnidentifiedEnum3979 var6;
      switch (var7) {
         case "info":
            var6 = UnidentifiedEnum3979.field_0006;
            break;
         case "error":
            var6 = UnidentifiedEnum3979.field_0000;
            break;
         default:
            var6 = UnidentifiedEnum3979.field_0005;
      }

      CBNotificationRenderer var12 = new CBNotificationRenderer(this, this, var5, var6, var2, var3);
      int var13 = var12.field_0001 - var12.field_0006 - 2;

      for (int var9 = this.notifications.size() - 1; var9 >= 0; var9--) {
         CBNotificationRenderer var10 = this.notifications.get(var9);
         var10.field_0002 = 0;
         var10.field_0001 = var13;
         var13 -= 2 + var10.field_0006;
      }

      this.notifications.add(var12);
   }

   public void onKeepAlive(KeepAliveEvent var1) {
      this.time = System.currentTimeMillis();
   }

   public void onTick(TickEvent var1) {
      Iterator var2 = this.notifications.iterator();

      while (var2.hasNext()) {
         CBNotificationRenderer var3 = (CBNotificationRenderer)var2.next();
         var3.method_08860();
         long var4 = var3.field_0003 + var3.field_0000 - System.currentTimeMillis();
         if (var4 <= (148263184L & 17336834L)) {
            int var6 = var3.field_0008;

            for (CBNotificationRenderer var8 : this.notifications) {
               if (var8.field_0008 < var3.field_0008) {
                  var8.field_0002 = 0;
                  var8.field_0001 = var6;
                  var6 = var8.field_0008;
               }
            }

            var2.remove();
         }
      }
   }
}
