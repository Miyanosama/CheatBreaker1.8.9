package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.ui.mainmenu.AccountLoginButton;
import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.item.Item$17;
import recovered.unidentified.UnidentifiedClass4439;

public class PingModule extends NumberHudModule {
   public Item$17 field_0006;
   public OldServerPinger field_0002 = new OldServerPinger();
   public EntityAIAttackOnCollide field_0009;
   public long field_0011 = -1L & -1L;
   public Setting field_0010;
   public Setting field_0005;
   public Setting field_0004;
   public Setting field_0003;
   public long field_0007;
   public Setting field_0000;
   public AccountLoginButton field_0001;
   public Setting field_0008;

   @Override
   public String method_00167() {
      ServerData var1 = this.minecraft.getCurrentServerData();
      this.method_26323(var1);
      if (var1 != null && var1.pingToServer > (-482216919457257471L & 146855700L)) {
         if ((Boolean)this.field_0008.getValue()) {
            if (this.field_0011 <= (7932237511919665171L & 22289449L)) {
               this.field_0011 = var1.pingToServer;
            } else if (this.field_0011 >= (6989250921280576410L & 1442846600L)) {
               this.field_0011 = var1.pingToServer;
            }
         } else {
            this.field_0011 = var1.pingToServer;
         }
      }

      if (this.method_09815(this.field_0003, (int)this.field_0011, this.field_0004.method_08912())) {
         return null;
      } else {
         return (this.minecraft.isIntegratedServerRunning() || this.minecraft.theWorld == null) && this.field_0000.getValue()
               || this.field_0011 == (-1L & -1L) && this.field_0005.getValue()
            ? null
            : this.field_0011 + "";
      }
   }

   @Override
   public String method_00164() {
      return "119";
   }

   @Override
   public String method_00166() {
      return "ms";
   }

   public PingModule() {
      super("Ping", "[19 ms]");
      this.method_28821("Displays your server latency.");
   }

   @Override
   public int getTextOffsetY() {
      if ((Boolean)this.field_0010.getValue()) {
         return this.method_00167() != null
            ? Color.getHSBColor(Math.max((125.0F - (float)this.field_0011 * 10.0F / (Float)this.field_0004.getValue()) / 360.0F, 0.0F), 1.0F, 1.0F).getRGB()
            : Color.getHSBColor(Math.max((125.0F - 1190.0F / (Float)this.field_0004.getValue()) / 360.0F, 0.0F), 1.0F, 1.0F).getRGB();
      } else {
         return this.field_0012.method_08901();
      }
   }

   @Override
   public void method_04335() {
      this.field_0012.method_08894(this.field_0010::method_08908);
   }

   @Override
   public UnidentifiedClass4439 method_00168() {
      return new UnidentifiedClass4439(0, 100, 1000);
   }

   @Override
   public void method_00165() {
      super.method_00165();
      this.field_0003 = new Setting(this, "Refresh Time", "Changes how many seconds the mod should refresh itself.")
         .method_08892("s")
         .setValue(15)
         .setMinMax(10, 90)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0005 = new Setting(this, "Hide if no connection", "Hide the mod when no connection is found.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0000 = new Setting(this, "Hide in Singleplayer", "Hid the mod in Singleplayer.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> !this.field_0005.method_08908());
      this.field_0008 = new Setting(this, "Accurate Mode", "Shows ping from server list so its accurate at all times.").setValue(true);
      this.field_0010 = new Setting(this, "Dynamic Color Range", "Determine if the text color should change dynamically based on ping amount.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0004 = new Setting(this, "Bad Ping Range Threshold").setValue(25.0F).setMinMax(10.0F, 100.0F).method_08894(this.field_0010::method_08908);
   }

   public void method_26323(ServerData var1) {
      if (this.minecraft.isIntegratedServerRunning() || this.minecraft.theWorld == null) {
         this.field_0011 = -1L & -1L;
      } else if (var1 != null && System.currentTimeMillis() - this.field_0007 >= TimeUnit.SECONDS.toMillis(((Integer)this.field_0003.getValue()).intValue())) {
         new Thread(() -> {
            try {
               if ((Boolean)this.field_0008.getValue()) {
                  String var2 = var1.serverIP;

                  try {
                     InetAddress var3 = InetAddress.getByName(var2);
                     long var4 = System.currentTimeMillis();
                     var3.isReachable(5000);
                     long var6 = System.currentTimeMillis();
                     this.field_0011 = var6 - var4;
                  } catch (IOException var8) {
                     var8.printStackTrace();
                     this.field_0011 = 8115583529301345373L & 9644035L;
                  }
               } else {
                  this.field_0002.method_08034(var1);
               }
            } catch (UnknownHostException var9) {
               var9.printStackTrace();
            }
         }).start();
         this.field_0007 = System.currentTimeMillis();
      }
   }
}
