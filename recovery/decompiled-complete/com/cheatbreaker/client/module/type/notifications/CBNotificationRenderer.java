package com.cheatbreaker.client.module.type.notifications;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderGiantZombie;
import net.minecraft.command.PlayerSelector$1;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.Item$15;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass3279;
import recovered.unidentified.UnidentifiedEnum3979;

public class CBNotificationRenderer {
   public int field_0006;
   public EntitySquid field_0005;
   public RenderGiantZombie field_0010;
   public int field_0001;
   public int field_0002;
   public UnidentifiedEnum3979 field_0012;
   public CBNotificationsModule field_0009;
   public long field_0003;
   public Item$15 field_0013;
   public long field_0000;
   public String field_0007;
   public int field_0008;
   public PlayerSelector$1 field_0004;

   public CBNotificationRenderer(
      CBNotificationsModule var1, CBNotificationsModule var2, ScaledResolution var3, UnidentifiedEnum3979 var4, String var5, long var6
   ) {
      this.field_0011 = var1;
      super();
      this.field_0003 = System.currentTimeMillis();
      this.field_0002 = 0;
      this.field_0009 = var2;
      this.field_0012 = var4;
      this.field_0007 = var5;
      this.field_0000 = var6;
      this.field_0006 = var4 == UnidentifiedEnum3979.field_0005 ? 16 : 20;
      this.field_0001 = var3.getScaledHeight() - 14 - this.field_0006;
      this.field_0008 = var3.getScaledHeight() + this.field_0006;
   }

   public void method_08860() {
      if (this.field_0001 != -1) {
         this.field_0002++;
         float var1 = this.field_0002 * (this.field_0002 / 5.0F) / 7.0F;
         if (this.field_0008 > this.field_0001) {
            if (this.field_0008 - var1 < this.field_0001) {
               this.field_0008 = this.field_0001;
               this.field_0001 = -1;
            } else {
               this.field_0008 = (int)(this.field_0008 - var1);
            }
         } else if (this.field_0008 < this.field_0001) {
            if (this.field_0008 + var1 > this.field_0001) {
               this.field_0008 = this.field_0001;
               this.field_0001 = -1;
            } else {
               this.field_0008 = (int)(this.field_0008 + var1);
            }
         } else if (this.field_0008 == this.field_0001) {
            this.field_0001 = -1;
         }
      }
   }

   public void method_08861(int var1) {
      CBFontRenderer var2 = CheatBreaker.getInstance().field_0036;
      int var3 = this.field_0008;
      float var4 = var2.getStringWidth(this.field_0007);
      int var5 = (int)(this.field_0012 == UnidentifiedEnum3979.field_0005 ? var4 + 10.0F : var4 + 30.0F);
      Gui.a(var1 - 5 - var5, var3, var1 - 5, var3 + this.field_0006, -1358954496);
      switch (UnidentifiedClass3279.field_0003[this.field_0012.ordinal()]) {
         case 1:
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderUtil.drawIcon(new ResourceLocation("client/icons/error-64.png"), 6.0F, var1 - 10 - var5 + 9, var3 + 4);
            Gui.drawRect(var1 - 10 - var4 - 4.5F, var3 + 4, var1 - 10 - var4 - 4.0F, var3 + this.field_0006 - 4, -1342177281);
            break;
         case 2:
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.65F);
            RenderUtil.drawIcon(new ResourceLocation("client/icons/info-64.png"), 6.0F, var1 - 10 - var5 + 9, var3 + 4);
            Gui.drawRect(var1 - 10 - var4 - 4.5F, var3 + 4, var1 - 10 - var4 - 4.0F, var3 + this.field_0006 - 4, -1342177281);
      }

      long var6 = this.field_0000 - (this.field_0003 + this.field_0000 - System.currentTimeMillis());
      if (var6 > this.field_0000) {
         var6 = this.field_0000;
      }

      if (var6 < (143720470L & 1073880576L)) {
         var6 = 134481985L & 1090535940L;
      }

      float var8 = var4 * ((float)var6 / (float)this.field_0000 * 100.0F / 100.0F);
      Gui.drawRect(var1 - 10 - var4, var3 + this.field_0006 - 4.4F, var1 - 10 - var4 + var4, var3 + this.field_0006 - 4, 812017254);
      Gui.drawRect(var1 - 10 - var4, var3 + this.field_0006 - 4.4F, var1 - 10 - var4 + var8, var3 + this.field_0006 - 4, -1878982912);
      var2.drawString(this.field_0007, var1 - 10 - var4, var3 + (this.field_0012 == UnidentifiedEnum3979.field_0005 ? 2 : 4), -1);
   }
}
