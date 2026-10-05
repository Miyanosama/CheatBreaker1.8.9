package com.cheatbreaker.client.module.type.notifications;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.module.type.notifications.CBNotificationRenderer$EnumSwitch;
import com.cheatbreaker.client.module.type.notifications.NotificationKind;

public class CBNotificationRenderer {
   public int recoveredField2012;
   public CBNotificationsModule recoveredField2013;
   public int recoveredField2014;
   public int recoveredField2015;
   public NotificationKind recoveredField2016;
   public CBNotificationsModule recoveredField2017;
   public long recoveredField2018;
   public long recoveredField2019;
   public String recoveredField2020;
   public int recoveredField2021;

   public CBNotificationRenderer(
      CBNotificationsModule var1, CBNotificationsModule var2, ScaledResolution var3, NotificationKind var4, String var5, long var6
   ) {
      this.recoveredField2013 = var1;
      this.recoveredField2018 = System.currentTimeMillis();
      this.recoveredField2015 = 0;
      this.recoveredField2017 = var2;
      this.recoveredField2016 = var4;
      this.recoveredField2020 = var5;
      this.recoveredField2019 = var6;
      this.recoveredField2012 = var4 == NotificationKind.NEUTRAL ? 16 : 20;
      this.recoveredField2014 = var3.getScaledHeight() - 14 - this.recoveredField2012;
      this.recoveredField2021 = var3.getScaledHeight() + this.recoveredField2012;
   }

   public void method_08860() {
      if (this.recoveredField2014 != -1) {
         this.recoveredField2015++;
         float var1 = this.recoveredField2015 * (this.recoveredField2015 / 5.0F) / 7.0F;
         if (this.recoveredField2021 > this.recoveredField2014) {
            if (this.recoveredField2021 - var1 < this.recoveredField2014) {
               this.recoveredField2021 = this.recoveredField2014;
               this.recoveredField2014 = -1;
            } else {
               this.recoveredField2021 = (int)(this.recoveredField2021 - var1);
            }
         } else if (this.recoveredField2021 < this.recoveredField2014) {
            if (this.recoveredField2021 + var1 > this.recoveredField2014) {
               this.recoveredField2021 = this.recoveredField2014;
               this.recoveredField2014 = -1;
            } else {
               this.recoveredField2021 = (int)(this.recoveredField2021 + var1);
            }
         } else if (this.recoveredField2021 == this.recoveredField2014) {
            this.recoveredField2014 = -1;
         }
      }
   }

   public void method_08861(int var1) {
      CBFontRenderer var2 = CheatBreaker.getInstance().recoveredField1548;
      int var3 = this.recoveredField2021;
      float var4 = var2.getStringWidth(this.recoveredField2020);
      int var5 = (int)(this.recoveredField2016 == NotificationKind.NEUTRAL ? var4 + 10.0F : var4 + 30.0F);
      Gui.a(var1 - 5 - var5, var3, var1 - 5, var3 + this.recoveredField2012, -1358954496);
      switch (CBNotificationRenderer$EnumSwitch.recoveredField2844[this.recoveredField2016.ordinal()]) {
         case 1:
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderUtil.drawIcon(new ResourceLocation("client/icons/error-64.png"), 6.0F, var1 - 10 - var5 + 9, var3 + 4);
            Gui.drawRect(var1 - 10 - var4 - 4.5F, var3 + 4, var1 - 10 - var4 - 4.0F, var3 + this.recoveredField2012 - 4, -1342177281);
            break;
         case 2:
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.65F);
            RenderUtil.drawIcon(new ResourceLocation("client/icons/info-64.png"), 6.0F, var1 - 10 - var5 + 9, var3 + 4);
            Gui.drawRect(var1 - 10 - var4 - 4.5F, var3 + 4, var1 - 10 - var4 - 4.0F, var3 + this.recoveredField2012 - 4, -1342177281);
      }

      long var6 = this.recoveredField2019 - (this.recoveredField2018 + this.recoveredField2019 - System.currentTimeMillis());
      if (var6 > this.recoveredField2019) {
         var6 = this.recoveredField2019;
      }

      if (var6 < 0L) {
         var6 = 0L;
      }

      float var8 = var4 * ((float)var6 / (float)this.recoveredField2019 * 100.0F / 100.0F);
      Gui.drawRect(var1 - 10 - var4, var3 + this.recoveredField2012 - 4.4F, var1 - 10 - var4 + var4, var3 + this.recoveredField2012 - 4, 812017254);
      Gui.drawRect(var1 - 10 - var4, var3 + this.recoveredField2012 - 4.4F, var1 - 10 - var4 + var8, var3 + this.recoveredField2012 - 4, -1878982912);
      var2.drawString(this.recoveredField2020, var1 - 10 - var4, var3 + (this.recoveredField2016 == NotificationKind.NEUTRAL ? 2 : 4), -1);
   }
}
