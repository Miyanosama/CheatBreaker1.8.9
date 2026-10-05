package com.cheatbreaker.client.util.worldborder;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.RenderWorldEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.util.Vec2d;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.event.type.CollisionEvent;

public class WorldBorderManager {
   public Minecraft recoveredField3473 = Minecraft.getMinecraft();
   public CheatBreaker cheatbreaker = CheatBreaker.getInstance();
   public List<WorldBorder> borderList = new ArrayList<>();
   public static ResourceLocation forceFieldTexture = new ResourceLocation("textures/misc/forcefield.png");

   public void method_29496() {
      this.borderList.clear();
   }

   public List<WorldBorder> method_29507() {
      return this.borderList;
   }

   public void onTick(TickEvent var1) {
      this.borderList.forEach(WorldBorder::ting);
   }

   public void method_29503(String var1, String var2, int var3, double var4, double var6, double var8, double var10, boolean var12, boolean var13) {
      this.borderList.add(new WorldBorder(this, var1, var2, var3, var4, var6, var8, var10, var12, var13));
   }

   public void method_29498(RenderWorldEvent var1) {
      if (!this.borderList.isEmpty()) {
         EntityPlayerSP var2 = this.recoveredField3473.thePlayer;
         float var3 = var1.method_00120();
         this.borderList
            .stream()
            .filter(WorldBorder::worldEqualsWorld)
            .forEach(
               var3x -> {
                  Tessellator var4 = Tessellator.getInstance();
                  WorldRenderer var5 = var4.getWorldRenderer();
                  double var6 = this.recoveredField3473.gameSettings.renderDistanceChunks * 16;
                  if (var2.s >= var3x.method_20889() - var6
                     || var2.s <= var3x.method_20890() + var6
                     || var2.u >= var3x.method_20897() - var6
                     || var2.u <= var3x.method_20881() + var6) {
                     double var14 = 1.0 - var3x.method_20885(var2) / var6;
                     var14 = Math.pow(var14, 4.0);
                     double var16 = var2.P + (var2.s - var2.P) * var3;
                     double var18 = var2.Q + (var2.t - var2.Q) * var3;
                     double var20 = var2.R + (var2.u - var2.R) * var3;
                     GL11.glEnable(3042);
                     GL11.glBlendFunc(770, 1);
                     this.recoveredField3473.renderEngine.bindTexture(forceFieldTexture);
                     GL11.glDepthMask(false);
                     GL11.glPushMatrix();
                     boolean var22 = true;
                     float var23 = (WorldBorder.method_20899(var3x).getRed() & 0xFF) / 255.0F;
                     float var24 = (WorldBorder.method_20899(var3x).getGreen() & 0xFF) / 255.0F;
                     float var25 = (WorldBorder.method_20899(var3x).getBlue() & 0xFF) / 255.0F;
                     GL11.glPolygonOffset(-3.0F, -3.0F);
                     GL11.glEnable(32823);
                     GL11.glAlphaFunc(516, 0.1F);
                     GL11.glEnable(3008);
                     GL11.glDisable(2884);
                     float var26 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F;
                     var5.begin(7, DefaultVertexFormats.POSITION_COLOR);
                     GL11.glTranslated(-var16, -var18, -var20);
                     double var27 = Math.max((double)MathHelper.floor_double(var20 - var6), var3x.method_20881());
                     double var29 = Math.min((double)MathHelper.ceiling_double_int(var20 + var6), var3x.method_20897());
                     if (var16 > var3x.method_20889() - var6) {
                        float var13 = 0.0F;

                        for (double var11 = var27; var11 < var29; var13 += 0.5F) {
                           double var9 = Math.min(1.0, var29 - var11);
                           float var8 = (float)var9 * 0.5F;
                           var5.pos(var3x.method_20889(), 256.0, var11).tex(var26 + var13, var26 + 0.0F).color(var23, var24, var25, 1.0F).endVertex();
                           var5.pos(var3x.method_20889(), 256.0, var11 + var9)
                              .tex(var26 + var8 + var13, var26 + 0.0F)
                              .color(var23, var24, var25, 1.0F)
                              .endVertex();
                           var5.pos(var3x.method_20889(), 0.0, var11 + var9)
                              .tex(var26 + var8 + var13, var26 + 128.0F)
                              .color(var23, var24, var25, 1.0F)
                              .endVertex();
                           var5.pos(var3x.method_20889(), 0.0, var11).tex(var26 + var13, var26 + 128.0F).color(var23, var24, var25, 1.0F).endVertex();
                           var11++;
                        }
                     }

                     if (var16 < var3x.method_20890() + var6) {
                        float var40 = 0.0F;

                        for (double var37 = var27; var37 < var29; var40 += 0.5F) {
                           double var34 = Math.min(1.0, var29 - var37);
                           float var31 = (float)var34 * 0.5F;
                           var5.pos(var3x.method_20890(), 256.0, var37).tex(var26 + var40, var26 + 0.0F).color(var23, var24, var25, 1.0F).endVertex();
                           var5.pos(var3x.method_20890(), 256.0, var37 + var34)
                              .tex(var26 + var31 + var40, var26 + 0.0F)
                              .color(var23, var24, var25, 1.0F)
                              .endVertex();
                           var5.pos(var3x.method_20890(), 0.0, var37 + var34)
                              .tex(var26 + var31 + var40, var26 + 128.0F)
                              .color(var23, var24, var25, 1.0F)
                              .endVertex();
                           var5.pos(var3x.method_20890(), 0.0, var37).tex(var26 + var40, var26 + 128.0F).color(var23, var24, var25, 1.0F).endVertex();
                           var37++;
                        }
                     }

                     var27 = Math.max((double)MathHelper.floor_double(var16 - var6), var3x.method_20890());
                     var29 = Math.min((double)MathHelper.ceiling_double_int(var16 + var6), var3x.method_20889());
                     if (var20 > var3x.method_20897() - var6) {
                        float var41 = 0.0F;

                        for (double var38 = var27; var38 < var29; var41 += 0.5F) {
                           double var35 = Math.min(1.0, var29 - var38);
                           float var32 = (float)var35 * 0.5F;
                           var5.pos(var38, 256.0, var3x.method_20897()).tex(var26 + var41, var26 + 0.0F).color(var23, var24, var25, 1.0F).endVertex();
                           var5.pos(var38 + var35, 256.0, var3x.method_20897())
                              .tex(var26 + var32 + var41, var26 + 0.0F)
                              .color(var23, var24, var25, 1.0F)
                              .endVertex();
                           var5.pos(var38 + var35, 0.0, var3x.method_20897())
                              .tex(var26 + var32 + var41, var26 + 128.0F)
                              .color(var23, var24, var25, 1.0F)
                              .endVertex();
                           var5.pos(var38, 0.0, var3x.method_20897()).tex(var26 + var41, var26 + 128.0F).color(var23, var24, var25, 1.0F).endVertex();
                           var38++;
                        }
                     }

                     if (var20 < var3x.method_20881() + var6) {
                        float var42 = 0.0F;

                        for (double var39 = var27; var39 < var29; var42 += 0.5F) {
                           double var36 = Math.min(1.0, var29 - var39);
                           float var33 = (float)var36 * 0.5F;
                           var5.pos(var39, 256.0, var3x.method_20881()).tex(var26 + var42, var26 + 0.0F).color(var23, var24, var25, 1.0F).endVertex();
                           var5.pos(var39 + var36, 256.0, var3x.method_20881())
                              .tex(var26 + var33 + var42, var26 + 0.0F)
                              .color(var23, var24, var25, 1.0F)
                              .endVertex();
                           var5.pos(var39 + var36, 0.0, var3x.method_20881())
                              .tex(var26 + var33 + var42, var26 + 128.0F)
                              .color(var23, var24, var25, 1.0F)
                              .endVertex();
                           var5.pos(var39, 0.0, var3x.method_20881()).tex(var26 + var42, var26 + 128.0F).color(var23, var24, var25, 1.0F).endVertex();
                           var39++;
                        }
                     }

                     var4.draw();
                     GL11.glTranslated(0.0, 0.0, 0.0);
                     GL11.glEnable(2884);
                     GL11.glDisable(3008);
                     GL11.glPolygonOffset(0.0F, 0.0F);
                     GL11.glDisable(32823);
                     GL11.glEnable(3008);
                     GL11.glDisable(3042);
                     GL11.glPopMatrix();
                     GL11.glDepthMask(true);
                  }
               }
            );
      }
   }

   public void method_29505(CollisionEvent var1) {
      for (WorldBorder var3 : this.borderList) {
         if (!var3.method_20884(var1.method_21504(), var1.method_21507())) {
            var1.method_21505()
               .add(
                  new AxisAlignedBB(
                     var1.method_21504(),
                     var1.method_21506(),
                     var1.method_21507(),
                     var1.method_21504() + 1.0,
                     var1.method_21506() + 1.0,
                     var1.method_21507() + 1.0
                  )
               );
         }
      }
   }

   public void method_29502(String var1, double var2, double var4, double var6, double var8, int var10) {
      this.borderList
         .stream()
         .filter(var1x -> Objects.equals(WorldBorder.getPlayer(var1x), var1) && WorldBorder.method_20882(var1x))
         .findFirst()
         .ifPresent(var9 -> {
            WorldBorder.method_20888(var9, new Vec2d(var2, var4));
            WorldBorder.method_20901(var9, new Vec2d(var6, var8));
            WorldBorder.method_20887(var9, 0);
            WorldBorder.method_20900(var9, var10);
         });
   }

   public WorldBorderManager() {
      CheatBreaker.getInstance().recoveredField1579.info(CheatBreaker.getInstance().recoveredField1553 + "Created Friend Manager");
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::onTick);
      CheatBreaker.getInstance().method_19817().method_21938(RenderWorldEvent.class, this::method_29498);
      CheatBreaker.getInstance().method_19817().method_21938(CollisionEvent.class, this::method_29505);
   }

   public void method_29501(String var1) {
      this.borderList.removeIf(var1x -> Objects.equals(WorldBorder.getPlayer(var1x), var1));
   }

   public static CheatBreaker method_29506(WorldBorderManager var0) {
      return var0.cheatbreaker;
   }
}
