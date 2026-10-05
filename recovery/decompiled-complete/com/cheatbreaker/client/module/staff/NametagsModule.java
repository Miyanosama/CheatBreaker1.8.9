package com.cheatbreaker.client.module.staff;

import com.cheatbreaker.client.event.type.GuiDrawEvent;
import io.netty.channel.socket.DefaultDatagramChannelConfig;
import io.netty.handler.codec.http.HttpResponseEncoder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.vecmath.Tuple4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.particle.MobAppearance$Factory;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.optifine.util.RenderChunkUtils;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryAbstractCellEditor;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import recovered.unidentified.UnidentifiedClass4262;

public class NametagsModule extends StaffModule {
   public UnidentifiedClass4262 field_0006;
   public FloatBuffer field_0009;
   public FloatBuffer field_0008 = BufferUtils.createFloatBuffer(16);
   public RenderChunkUtils field_0000;
   public HttpResponseEncoder field_0003;
   public static Map<UUID, List<String>> field_0005 = null;
   public MobAppearance$Factory field_0007;
   public DefaultDatagramChannelConfig field_0001;
   public Tuple4f field_0004;
   public CategoryAbstractCellEditor field_0002;

   public void method_27978(GuiDrawEvent var1) {
      Minecraft var2 = Minecraft.getMinecraft();
      IntBuffer var3 = BufferUtils.createIntBuffer(16);
      GL11.glGetInteger(2978, var3);
      float var4 = var2.field_0069.field_0005;
      float var5 = (float)(var2.thePlayer.P + (var2.thePlayer.s - var2.thePlayer.P) * var4);
      float var6 = (float)(var2.thePlayer.Q + (var2.thePlayer.t - var2.thePlayer.Q) * var4);
      float var7 = (float)(var2.thePlayer.R + (var2.thePlayer.u - var2.thePlayer.R) * var4);
      double var8 = (var2.thePlayer.z + 90.0F) * Math.PI / 180.0;
      double var10 = (var2.thePlayer.y + 90.0F) * Math.PI / 180.0;
      boolean var12 = field_0005 != null;
      Vec3 var13 = new Vec3(Math.sin(var8) * Math.cos(var10), Math.cos(var8), Math.sin(var8) * Math.sin(var10));
      if (var2.gameSettings.thirdPersonView == 2) {
         var13 = new Vec3(var13.xCoord * -1.0, var13.yCoord * -1.0, var13.zCoord * -1.0);
      }

      for (int var14 = 0; var14 < var2.theWorld.getLoadedEntityList().size(); var14++) {
         Entity var15 = var2.theWorld.getLoadedEntityList().get(var14);
         if (var15 != null && var15 != var2.thePlayer && var15 instanceof AbstractClientPlayer) {
            AbstractClientPlayer var16 = (AbstractClientPlayer)var15;
            Scoreboard var17 = var16.getWorldScoreboard();
            ScoreObjective var18 = var17.getObjectiveInDisplaySlot(2);
            float var19 = 0.0F;
            if (var16.h(var2.thePlayer) < 100.0 && var18 != null) {
               var19 = (float)((double)var19 + var2.fontRendererObj.FONT_HEIGHT * 0.0375F);
            }

            float var20 = (float)(var16.P + (var16.s - var16.P) * var4 - var5);
            float var21 = (float)(var16.Q + (var16.t - var16.Q) * var4 - var6) + var16.K + (var16.isSneaking() ? 0.3F : 0.55F) + var19;
            float var22 = (float)(var16.R + (var16.u - var16.R) * var4 - var7);
            Vec3 var23 = new Vec3(var20, var21, var22);
            double var24 = var23.lengthVector();
            if (var13.dotProduct(var23 = var23.normalize()) <= 0.02) {
               double var26 = 1.5533430342749535;
               double var28 = Math.sin(1.5533430342749535);
               double var30 = Math.cos(1.5533430342749535);
               Vec3 var32 = var13.crossProduct(var23);
               double var33 = var32.xCoord;
               double var35 = var32.yCoord;
               double var37 = var32.zCoord;
               double var39 = var30 + var33 * var33 * (1.0 - var30);
               double var41 = var33 * var35 * (1.0 - var30) - var37 * var28;
               double var43 = var33 * var37 * (1.0 - var30) + var35 * var28;
               double var45 = var35 * var33 * (1.0 - var30) + var37 * var28;
               double var47 = var30 + var35 * var35 * (1.0 - var30);
               double var49 = var35 * var37 * (1.0 - var30) - var33 * var28;
               double var51 = var37 * var33 * (1.0 - var30) - var35 * var28;
               double var53 = var37 * var35 * (1.0 - var30) + var33 * var28;
               double var55 = var30 + var37 * var37 * (1.0 - var30);
               var20 = (float)(var24 * (var39 * var13.xCoord + var41 * var13.yCoord + var43 * var13.zCoord));
               var21 = (float)(var24 * (var45 * var13.xCoord + var47 * var13.yCoord + var49 * var13.zCoord));
               var22 = (float)(var24 * (var51 * var13.xCoord + var53 * var13.yCoord + var55 * var13.zCoord));
            }

            FloatBuffer var58 = BufferUtils.createFloatBuffer(3);
            GLU.gluProject(var20, var21, var22, this.field_0008, this.field_0009, var3, var58);
            float var27 = var58.get(0) / var1.getResolution().getScaleFactor();
            float var59 = var58.get(1) / var1.getResolution().getScaleFactor();
            GL11.glPushMatrix();
            GL11.glTranslatef(var27, var1.getResolution().getScaledHeight() - var59, 0.0F);
            float var29 = var16.getHealth();
            Object var60 = var16.z_() + (var29 != 1.0 ? " | " + this.method_27979(var29) + var29 : "");
            if (var18 != null) {
               Score var31 = var17.getValueFromObjective(var16.z_(), var18);
               var60 = var60 + EnumChatFormatting.WHITE + " | " + this.method_27979(var31.getScorePoints()) + var31.getScorePoints();
            }

            float var61 = var2.fontRendererObj.getStringWidth(EnumChatFormatting.getTextWithoutFormattingCodes((String)var60));
            var2.fontRendererObj.drawStringWithShadow((String)var60, (int)(-var61) / 2, -var2.fontRendererObj.FONT_HEIGHT, -1);
            if (var12 && field_0005.containsKey(var16.getGameProfile().getId().toString())) {
               int var62 = 1;

               for (Object var34 : field_0005.get(var16.getGameProfile().getId().toString())) {
                  String var64 = (String)var34;
                  var2.fontRendererObj.method_08776(var64, (int)(-var61) / 2, -var2.fontRendererObj.FONT_HEIGHT * ++var62, -1);
               }
            }

            GL11.glPopMatrix();
         }
      }
   }

   public static void method_27977(Map<UUID, List<String>> var0) {
      field_0005 = var0;
   }

   public NametagsModule() {
      super("Nametags");
      this.field_0009 = BufferUtils.createFloatBuffer(16);
      this.method_28828(true);
      this.method_28820(GuiDrawEvent.class, this::method_27978);
   }

   public static Map<UUID, List<String>> method_27976() {
      return field_0005;
   }

   public EnumChatFormatting method_27979(float var1) {
      return var1 > 15.0F
         ? EnumChatFormatting.DARK_GREEN
         : (var1 > 10.0F ? EnumChatFormatting.YELLOW : (var1 > 5.0F ? EnumChatFormatting.RED : EnumChatFormatting.DARK_RED));
   }
}
