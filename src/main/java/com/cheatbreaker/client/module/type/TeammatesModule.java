package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.DisconnectEvent;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.util.teammates.Teammate;
import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import com.cheatbreaker.client.module.type.TeammatesModule$EnumSwitch;
import com.cheatbreaker.client.module.type.TeammateHudSide;

public class TeammatesModule {
   public boolean recoveredField3471;
   public FloatBuffer projectionMatrixBuffer;
   public List<Teammate> teammates;
   public int[] recoveredField3472;
   public FloatBuffer modelViewMatrixBuffer = BufferUtils.createFloatBuffer(16);
   public Minecraft minecraft;

   public void drawMarker(Teammate var1, float var2, float var3, float var4) {
      Tessellator var5 = Tessellator.getInstance();
      WorldRenderer var6 = var5.getWorldRenderer();
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      if (var1.method_04981()) {
         GL11.glColor4f(0.0F, 0.0F, 1.0F, 0.66F);
      } else {
         Color var7 = var1.method_04980();
         GL11.glColor4f(var7.getRed() / 255.0F, var7.getGreen() / 255.0F, var7.getBlue() / 255.0F, 0.66F);
      }

      GL11.glPushMatrix();
      GL11.glScalef(0.6F, 0.6F, 0.6F);
      GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(var2 * 2.0F, 0.0F, 0.0F);
      GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
      var6.begin(7, DefaultVertexFormats.POSITION);
      var6.pos(-var2, var3, 0.0).endVertex();
      var6.pos(-var2, var3 + var4 / 2.0F, 0.0).endVertex();
      var6.pos(var2, var3 + var4 / 2.0F, 0.0).endVertex();
      var6.pos(var2, var3, 0.0).endVertex();
      var5.draw();
      GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
      GL11.glTranslatef(var2 * 2.0F + 1.0F, var4 / 2.0F + 1.0F, 0.0F);
      var6.begin(7, DefaultVertexFormats.POSITION);
      var6.pos(-var2 / 2.0F + 1.0F, var3, 0.0).endVertex();
      var6.pos(-var2 / 2.0F + 1.0F, var3 + var4 / 2.0F, 0.0).endVertex();
      var6.pos(var2, var3 + var4 / 2.0F, 0.0).endVertex();
      var6.pos(var2, var3, 0.0).endVertex();
      var5.draw();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   public List<Teammate> method_01332() {
      return this.teammates;
   }

   public FloatBuffer method_01342() {
      return this.modelViewMatrixBuffer;
   }

   public FloatBuffer method_01331() {
      return this.projectionMatrixBuffer;
   }

   public void onDraw(GuiDrawEvent var1) {
      if (!this.teammates.isEmpty()) {
         IntBuffer var2 = BufferUtils.createIntBuffer(16);
         GL11.glGetInteger(2978, var2);
         float var3 = this.minecraft.recoveredField3821.recoveredField2493;
         float var4 = (float)(this.minecraft.thePlayer.P + (this.minecraft.thePlayer.s - this.minecraft.thePlayer.P) * var3);
         float var5 = (float)(this.minecraft.thePlayer.Q + (this.minecraft.thePlayer.t - this.minecraft.thePlayer.Q) * var3);
         float var6 = (float)(this.minecraft.thePlayer.R + (this.minecraft.thePlayer.u - this.minecraft.thePlayer.R) * var3);
         double var7 = (this.minecraft.thePlayer.z + 90.0F) * Math.PI / 180.0;
         double var9 = (this.minecraft.thePlayer.y + 90.0F) * Math.PI / 180.0;
         Vec3 var11 = new Vec3(Math.sin(var7) * Math.cos(var9), Math.cos(var7), Math.sin(var7) * Math.sin(var9));
         if (this.minecraft.gameSettings.thirdPersonView == 2) {
            var11 = new Vec3(var11.xCoord * -1.0, var11.yCoord * -1.0, var11.zCoord * -1.0);
         }

         for (Teammate var13 : this.teammates) {
            EntityPlayer var14 = this.minecraft.theWorld.method_09945(var13.method_04983());
            if (var14 == null) {
               if (System.currentTimeMillis() - var13.method_04991() <= var13.method_04982()) {
                  double var17 = var13.getVector3D().zCoord - var4;
                  double var19 = var13.getVector3D().yCoord - var5;
                  double var21 = var13.getVector3D().zCoord - var6;
                  double var23 = this.getDistance(var13.getVector3D().xCoord, var13.getVector3D().yCoord, var13.getVector3D().zCoord);
                  double var15;
                  if (var23 > (var15 = this.minecraft.gameSettings.getOptionFloatValue(GameSettings.Options.RENDER_DISTANCE) * 16.0F)) {
                     var17 = var17 / var23 * var15;
                     var19 = var19 / var23 * var15;
                     var21 = var21 / var23 * var15;
                  }

                  this.method_01336(var1.getResolution(), var13, (float)var17, (float)var19, (float)var21, var2, var11, (int)var23);
               }
            } else if (var14 != this.minecraft.thePlayer) {
               float var25 = (float)(var14.P + (var14.s - var14.P) * var3 - var4);
               float var16 = (float)(var14.Q + (var14.t - var14.Q) * var3 - var5) + var14.K + 1.0F;
               float var26 = (float)(var14.R + (var14.u - var14.R) * var3 - var6);
               double var18 = this.getDistance(var14.s, var14.t, var14.u);
               this.method_01336(var1.getResolution(), var13, var25, var16, var26, var2, var11, (int)var18);
            }
         }
      }
   }

   public void method_01339(DisconnectEvent var1) {
      this.teammates.clear();
   }

   public void method_01336(ScaledResolution var1, Teammate var2, float var3, float var4, float var5, IntBuffer var6, Vec3 var7, int var8) {
      Vec3 var9 = new Vec3(var3, var4, var5);
      double var10 = var9.lengthVector();
      if (var7.dotProduct(var9 = var9.normalize()) <= 0.02) {
         double var12 = Math.sin(1.5533430342749535);
         double var14 = Math.cos(1.5533430342749535);
         Vec3 var16 = var7.crossProduct(var9);
         double var17 = var16.xCoord;
         double var19 = var16.yCoord;
         double var21 = var16.zCoord;
         double var23 = var14 + var17 * var17 * (1.0 - var14);
         double var25 = var17 * var19 * (1.0 - var14) - var21 * var12;
         double var27 = var17 * var21 * (1.0 - var14) + var19 * var12;
         double var29 = var19 * var17 * (1.0 - var14) + var21 * var12;
         double var31 = var14 + var19 * var19 * (1.0 - var14);
         double var33 = var19 * var21 * (1.0 - var14) - var17 * var12;
         double var35 = var21 * var17 * (1.0 - var14) - var19 * var12;
         double var37 = var21 * var19 * (1.0 - var14) + var17 * var12;
         double var39 = var14 + var21 * var21 * (1.0 - var14);
         var3 = (float)(var10 * (var23 * var7.xCoord + var25 * var7.yCoord + var27 * var7.zCoord));
         var4 = (float)(var10 * (var29 * var7.xCoord + var31 * var7.yCoord + var33 * var7.zCoord));
         var5 = (float)(var10 * (var35 * var7.xCoord + var37 * var7.yCoord + var39 * var7.zCoord));
      }

      FloatBuffer var42 = BufferUtils.createFloatBuffer(3);
      GLU.gluProject(var3, var4, var5, this.modelViewMatrixBuffer, this.projectionMatrixBuffer, var6, var42);
      float var13 = var42.get(0) / var1.getScaleFactor();
      float var43 = var42.get(1) / var1.getScaleFactor();
      TeammateHudSide var15 = null;
      byte var44 = 8;
      byte var45 = 10;
      int var18 = -4 - var45;
      float var46 = var1.getScaledHeight() - var43;
      if (var46 < 0.0F) {
         var15 = TeammateHudSide.RIGHT;
         var43 = var1.getScaledHeight() - 6;
      } else if (var46 > var1.getScaledHeight() - var45) {
         var15 = TeammateHudSide.BOTTOM;
         var43 = 6.0F;
      }

      if (var13 - var44 < 0.0F) {
         var15 = TeammateHudSide.TOP;
         var13 = 6.0F;
      } else if (var13 > var1.getScaledWidth() - var44) {
         var15 = TeammateHudSide.LEFT;
         var13 = var1.getScaledWidth() - 6;
      }

      GL11.glPushMatrix();
      GL11.glTranslatef(var13, var1.getScaledHeight() - var43, 0.0F);
      if (var15 != null) {
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField519.getValue()) {
            this.method_01335(var2, var15, 0.0F, 0.0F);
         }
      } else {
         this.drawMarker(var2, var44, var18, var45);
         if (var8 > 40 && (Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField598.getValue()) {
            this.minecraft.fontRendererObj.method_08776("(" + var8 + "m)", 0, 10, -1);
         }
      }

      GL11.glPopMatrix();
   }

   public boolean method_01341() {
      return this.recoveredField3471;
   }

   public int[] method_01329() {
      return this.recoveredField3472;
   }

   public Teammate method_01337(String var1) {
      for (Teammate var3 : this.teammates) {
         if (var3.method_04983().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public Minecraft method_01330() {
      return this.minecraft;
   }

   public void method_01340(boolean var1) {
      if (var1 && !this.recoveredField3471) {
         this.recoveredField3471 = true;
         CheatBreaker.getInstance().method_19817().method_21938(GuiDrawEvent.class, this::onDraw);
         CheatBreaker.getInstance().method_19817().method_21938(DisconnectEvent.class, this::method_01339);
      } else if (!var1 && this.recoveredField3471) {
         this.recoveredField3471 = false;
         CheatBreaker.getInstance().method_19817().method_21939(GuiDrawEvent.class, this::onDraw);
         CheatBreaker.getInstance().method_19817().method_21939(DisconnectEvent.class, this::method_01339);
      }
   }

   public TeammatesModule() {
      this.projectionMatrixBuffer = BufferUtils.createFloatBuffer(16);
      this.recoveredField3472 = new int[]{-15007996, -43234, -3603713, -16580641, -8912129, -16601345, -2786, -64828, -15629042, -10744187};
      this.recoveredField3471 = false;
      this.minecraft = Minecraft.getMinecraft();
      this.teammates = new ArrayList<>();
   }

   public double getDistance(double var1, double var3, double var5) {
      double var7 = var1 - this.minecraft.thePlayer.s;
      double var9 = var3 - this.minecraft.thePlayer.t;
      double var11 = var5 - this.minecraft.thePlayer.u;
      return Math.sqrt(var7 * var7 + var9 * var9 + var11 * var11);
   }

   public void method_01335(Teammate var1, TeammateHudSide var2, float var3, float var4) {
      Tessellator var5 = Tessellator.getInstance();
      WorldRenderer var6 = var5.getWorldRenderer();
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      if (var1.method_04981()) {
         GL11.glColor4f(0.0F, 0.0F, 1.0F, 0.66F);
      } else {
         Color var7 = var1.method_04980();
         GL11.glColor4f(var7.getRed() / 255.0F, var7.getGreen() / 255.0F, var7.getBlue() / 255.0F, 0.66F);
      }

      float var9 = 8.0F;
      float var8 = 10.0F;
      GL11.glPushMatrix();
      GL11.glTranslatef(var3, var4, 0.0F);
      switch (TeammatesModule$EnumSwitch.recoveredField2292[var2.ordinal()]) {
         case 1:
            var6.begin(7, DefaultVertexFormats.POSITION);
            var6.pos(var9 / 2.0F, var8 / 2.0F, 0.0).endVertex();
            var6.pos(-var9 / 2.0F, 0.0, 0.0).endVertex();
            var6.pos(var9 / 2.0F, -var8 / 2.0F, 0.0).endVertex();
            var6.pos(-var9 / 2.0F, 0.0, 0.0).endVertex();
            var5.draw();
            break;
         case 2:
            var6.begin(7, DefaultVertexFormats.POSITION);
            var6.pos(-var9 / 2.0F, var8 / 2.0F, 0.0).endVertex();
            var6.pos(var9 / 2.0F, 0.0, 0.0).endVertex();
            var6.pos(-var9 / 2.0F, -var8 / 2.0F, 0.0).endVertex();
            var6.pos(var9 / 2.0F, 0.0, 0.0).endVertex();
            var5.draw();
            break;
         case 3:
            var6.begin(7, DefaultVertexFormats.POSITION);
            var6.pos(-var9 / 2.0F, -var8 / 2.0F, 0.0).endVertex();
            var6.pos(0.0, var8 / 2.0F, 0.0).endVertex();
            var6.pos(var9 / 2.0F, -var8 / 2.0F, 0.0).endVertex();
            var6.pos(0.0, var8 / 2.0F, 0.0).endVertex();
            var5.draw();
            break;
         case 4:
            var6.begin(7, DefaultVertexFormats.POSITION);
            var6.pos(-var9 / 2.0F, var8 / 2.0F, 0.0).endVertex();
            var6.pos(0.0, -var8 / 2.0F, 0.0).endVertex();
            var6.pos(var9 / 2.0F, var8 / 2.0F, 0.0).endVertex();
            var6.pos(0.0, -var8 / 2.0F, 0.0).endVertex();
            var5.draw();
      }

      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }
}
