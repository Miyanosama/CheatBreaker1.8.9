package com.cheatbreaker.client.module.type.notifications;

import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.KeepAliveEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.ScaledResolution;
import com.cheatbreaker.client.module.type.notifications.NotificationKind;

public class CBNotificationsModule extends AbstractModule {
   public long time = System.currentTimeMillis();
   public List<CBNotificationRenderer> notifications = new ArrayList<>();

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
      if (var3 < 2000L) {
         var3 = 2000L;
      }

      var2 = var2.replaceAll("&([abcdefghijklmrABCDEFGHIJKLMNR0-9])|(&$)", "§$1");
      String var7 = var1.toLowerCase();
      NotificationKind var6;
      switch (var7) {
         case "info":
            var6 = NotificationKind.INFO;
            break;
         case "error":
            var6 = NotificationKind.ERROR;
            break;
         default:
            var6 = NotificationKind.NEUTRAL;
      }

      CBNotificationRenderer var12 = new CBNotificationRenderer(this, this, var5, var6, var2, var3);
      int var13 = var12.recoveredField2014 - var12.recoveredField2012 - 2;

      for (int var9 = this.notifications.size() - 1; var9 >= 0; var9--) {
         CBNotificationRenderer var10 = this.notifications.get(var9);
         var10.recoveredField2015 = 0;
         var10.recoveredField2014 = var13;
         var13 -= 2 + var10.recoveredField2012;
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
         long var4 = var3.recoveredField2018 + var3.recoveredField2019 - System.currentTimeMillis();
         if (var4 <= 0L) {
            int var6 = var3.recoveredField2021;

            for (CBNotificationRenderer var8 : this.notifications) {
               if (var8.recoveredField2021 < var3.recoveredField2021) {
                  var8.recoveredField2015 = 0;
                  var8.recoveredField2014 = var6;
                  var6 = var8.recoveredField2021;
               }
            }

            var2.remove();
         }
      }
   }
}
