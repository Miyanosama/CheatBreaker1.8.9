package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.Block$EnumOffsetType;
import net.minecraft.block.material.Material;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderPainting;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.monster.EntityEnderman$AIFindPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.Config;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.optifine.model.BlockModelUtils;
import net.optifine.shaders.Shaders;
import org.apache.log4j.chainsaw.ControlPanel$2;
import org.apache.log4j.varia.ExternallyRolledFileAppender;
import org.lwjgl.opengl.GL11;
import org.slf4j.MDC$MDCCloseable;
import recovered.unidentified.UnidentifiedClass0890;
import recovered.unidentified.UnidentifiedClass5030;

public class BlockOverlayModule extends AbstractModule {
   public Setting field_0008;
   public Setting field_0010;
   public static Tessellator field_0002 = Tessellator.getInstance();
   public RenderPainting field_0003;
   public ExternallyRolledFileAppender field_0015;
   public MDC$MDCCloseable field_0012;
   public Setting field_0017;
   public Setting field_0014;
   public EntityEnderman$AIFindPlayer field_0000;
   public Setting field_0006;
   public ControlPanel$2 field_0009;
   public UnidentifiedClass0890 field_0013;
   public Setting field_0001;
   public Setting field_0007;
   public Setting field_0005;
   public Setting field_0016;
   public Setting field_0004;
   public static WorldRenderer field_0011 = field_0002.getWorldRenderer();

   public static void method_12512(AxisAlignedBB var0, Color var1, Color var2, Color var3, Color var4, boolean var5, boolean var6) {
      if (var5) {
         field_0011.begin(7, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.d, var0.e, var0.c).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.b, var0.c).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.c).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.e, var0.c).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0002.draw();
      }

      if (var6) {
         field_0011.begin(2, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.d, var0.e, var0.c).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.b, var0.c).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.c).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.e, var0.c).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.e, var0.c).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0002.draw();
      }
   }

   public static void method_12509(AxisAlignedBB var0) {
      Tessellator var1 = Tessellator.getInstance();
      WorldRenderer var2 = var1.getWorldRenderer();
      var2.begin(7, DefaultVertexFormats.POSITION);
      var2.pos(var0.a, var0.b, var0.c);
      var2.pos(var0.a, var0.e, var0.c);
      var2.pos(var0.d, var0.b, var0.c);
      var2.pos(var0.d, var0.e, var0.c);
      var2.pos(var0.d, var0.b, var0.f);
      var2.pos(var0.d, var0.e, var0.f);
      var2.pos(var0.a, var0.b, var0.f);
      var2.pos(var0.a, var0.e, var0.f);
      var1.draw();
      var2.begin(7, DefaultVertexFormats.POSITION);
      var2.pos(var0.d, var0.e, var0.c);
      var2.pos(var0.d, var0.b, var0.c);
      var2.pos(var0.a, var0.e, var0.c);
      var2.pos(var0.a, var0.b, var0.c);
      var2.pos(var0.a, var0.e, var0.f);
      var2.pos(var0.a, var0.b, var0.f);
      var2.pos(var0.d, var0.e, var0.f);
      var2.pos(var0.d, var0.b, var0.f);
      var1.draw();
      var2.begin(7, DefaultVertexFormats.POSITION);
      var2.pos(var0.a, var0.e, var0.c);
      var2.pos(var0.d, var0.e, var0.c);
      var2.pos(var0.d, var0.e, var0.f);
      var2.pos(var0.a, var0.e, var0.f);
      var2.pos(var0.a, var0.e, var0.c);
      var2.pos(var0.a, var0.e, var0.f);
      var2.pos(var0.d, var0.e, var0.f);
      var2.pos(var0.d, var0.e, var0.c);
      var1.draw();
      var2.begin(7, DefaultVertexFormats.POSITION);
      var2.pos(var0.a, var0.b, var0.c);
      var2.pos(var0.d, var0.b, var0.c);
      var2.pos(var0.d, var0.b, var0.f);
      var2.pos(var0.a, var0.b, var0.f);
      var2.pos(var0.a, var0.b, var0.c);
      var2.pos(var0.a, var0.b, var0.f);
      var2.pos(var0.d, var0.b, var0.f);
      var2.pos(var0.d, var0.b, var0.c);
      var1.draw();
      var2.begin(7, DefaultVertexFormats.POSITION);
      var2.pos(var0.a, var0.b, var0.c);
      var2.pos(var0.a, var0.e, var0.c);
      var2.pos(var0.a, var0.b, var0.f);
      var2.pos(var0.a, var0.e, var0.f);
      var2.pos(var0.d, var0.b, var0.f);
      var2.pos(var0.d, var0.e, var0.f);
      var2.pos(var0.d, var0.b, var0.c);
      var2.pos(var0.d, var0.e, var0.c);
      var1.draw();
      var2.begin(7, DefaultVertexFormats.POSITION);
      var2.pos(var0.a, var0.e, var0.f);
      var2.pos(var0.a, var0.b, var0.f);
      var2.pos(var0.a, var0.e, var0.c);
      var2.pos(var0.a, var0.b, var0.c);
      var2.pos(var0.d, var0.e, var0.c);
      var2.pos(var0.d, var0.b, var0.c);
      var2.pos(var0.d, var0.e, var0.f);
      var2.pos(var0.d, var0.b, var0.f);
      var1.draw();
   }

   public static void method_12519(AxisAlignedBB var0, Color var1, Color var2, Color var3, Color var4, boolean var5, boolean var6) {
      if (var5) {
         field_0011.begin(7, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.a, var0.e, var0.f).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.e, var0.c).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.c).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.f).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0002.draw();
      }

      if (var6) {
         field_0011.begin(2, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.a, var0.e, var0.f).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.e, var0.c).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.c).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.f).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0002.draw();
      }
   }

   public static void method_12511(AxisAlignedBB var0, EnumFacing var1, Color var2, Color var3, Color var4, Color var5, boolean var6, boolean var7) {
      switch (UnidentifiedClass5030.field_0002[var1.ordinal()]) {
         case 1:
            method_12517(var0, var2, var3, var4, var5, var6, var7);
            break;
         case 2:
            method_12520(var0, var2, var3, var4, var5, var6, var7);
            break;
         case 3:
            method_12512(var0, var2, var3, var4, var5, var6, var7);
            break;
         case 4:
            method_12505(var0, var2, var3, var4, var5, var6, var7);
            break;
         case 5:
            method_12503(var0, var2, var3, var4, var5, var6, var7);
            break;
         case 6:
            method_12519(var0, var2, var3, var4, var5, var6, var7);
      }
   }

   public static void method_12507(AxisAlignedBB var0, Color var1, Color var2, Color var3, Color var4, boolean var5, boolean var6) {
      if (var5) {
         method_12517(var0, var1, var2, var3, var4, true, false);
         method_12520(var0, var1, var2, var3, var4, true, false);
         method_12512(var0, var1, var2, var3, var4, true, false);
         method_12505(var0, var1, var2, var3, var4, true, false);
         method_12503(var0, var1, var2, var3, var4, true, false);
         method_12519(var0, var1, var2, var3, var4, true, false);
      }

      if (var6) {
         method_12517(var0, var1, var2, var3, var4, false, true);
         method_12520(var0, var1, var2, var3, var4, false, true);
         method_12512(var0, var1, var2, var3, var4, false, true);
         method_12505(var0, var1, var2, var3, var4, false, true);
         method_12503(var0, var1, var2, var3, var4, false, true);
         method_12519(var0, var1, var2, var3, var4, false, true);
      }
   }

   public static void method_12517(AxisAlignedBB var0, Color var1, Color var2, Color var3, Color var4, boolean var5, boolean var6) {
      if (var5) {
         field_0011.begin(7, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.a, var0.e, var0.f).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.e, var0.f).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.e, var0.c).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.e, var0.c).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0002.draw();
      }

      if (var6) {
         field_0011.begin(2, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.a, var0.e, var0.f).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.e, var0.f).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.e, var0.c).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.e, var0.c).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0002.draw();
      }
   }

   public static void method_12503(AxisAlignedBB var0, Color var1, Color var2, Color var3, Color var4, boolean var5, boolean var6) {
      if (var5) {
         field_0011.begin(7, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.a, var0.e, var0.f).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.f).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.b, var0.f).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.e, var0.f).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0002.draw();
      }

      if (var6) {
         field_0011.begin(2, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.d, var0.e, var0.f).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.e, var0.f).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.f).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.b, var0.f).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.e, var0.f).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0002.draw();
      }
   }

   public static void method_12505(AxisAlignedBB var0, Color var1, Color var2, Color var3, Color var4, boolean var5, boolean var6) {
      if (var5) {
         field_0011.begin(7, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.d, var0.e, var0.c).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.e, var0.f).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.b, var0.f).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.b, var0.c).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0002.draw();
      }

      if (var6) {
         field_0011.begin(2, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.d, var0.e, var0.c).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.e, var0.f).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.b, var0.f).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.b, var0.c).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0002.draw();
      }
   }

   public static void method_12510(AxisAlignedBB var0, EnumFacing var1, int var2, int var3, int var4, int var5, boolean var6, boolean var7) {
      if (var1 == null) {
         method_12507(var0, new Color(var2, true), new Color(var3, true), new Color(var4, true), new Color(var5, true), var6, var7);
      } else {
         method_12511(var0, var1, new Color(var2, true), new Color(var3, true), new Color(var4, true), new Color(var5, true), var6, var7);
      }
   }

   public static void method_12520(AxisAlignedBB var0, Color var1, Color var2, Color var3, Color var4, boolean var5, boolean var6) {
      if (var5) {
         field_0011.begin(7, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.d, var0.b, var0.f).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.f).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.c).color(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.b, var0.c).color(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha()).endVertex();
         field_0002.draw();
      }

      if (var6) {
         field_0011.begin(2, DefaultVertexFormats.POSITION_COLOR);
         field_0011.pos(var0.d, var0.b, var0.f).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.f).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0011.pos(var0.a, var0.b, var0.c).color(var3.getRed(), var3.getGreen(), var3.getBlue(), var3.getAlpha()).endVertex();
         field_0011.pos(var0.d, var0.b, var0.c).color(var4.getRed(), var4.getGreen(), var4.getBlue(), var4.getAlpha()).endVertex();
         field_0002.draw();
      }
   }

   public static void method_12518(AxisAlignedBB var0) {
      Tessellator var1 = Tessellator.getInstance();
      WorldRenderer var2 = var1.getWorldRenderer();
      var2.begin(3, DefaultVertexFormats.POSITION);
      var2.pos(var0.a, var0.b, var0.c).endVertex();
      var2.pos(var0.d, var0.b, var0.c).endVertex();
      var2.pos(var0.d, var0.b, var0.f).endVertex();
      var2.pos(var0.a, var0.b, var0.f).endVertex();
      var2.pos(var0.a, var0.b, var0.c).endVertex();
      var1.draw();
      var2.begin(3, DefaultVertexFormats.POSITION);
      var2.pos(var0.a, var0.e, var0.c).endVertex();
      var2.pos(var0.d, var0.e, var0.c).endVertex();
      var2.pos(var0.d, var0.e, var0.f).endVertex();
      var2.pos(var0.a, var0.e, var0.f).endVertex();
      var2.pos(var0.a, var0.e, var0.c).endVertex();
      var1.draw();
      var2.begin(1, DefaultVertexFormats.POSITION);
      var2.pos(var0.a, var0.b, var0.c).endVertex();
      var2.pos(var0.a, var0.e, var0.c).endVertex();
      var2.pos(var0.d, var0.b, var0.c).endVertex();
      var2.pos(var0.d, var0.e, var0.c).endVertex();
      var2.pos(var0.d, var0.b, var0.f).endVertex();
      var2.pos(var0.d, var0.e, var0.f).endVertex();
      var2.pos(var0.a, var0.b, var0.f).endVertex();
      var2.pos(var0.a, var0.e, var0.f).endVertex();
      var1.draw();
   }

   public BlockOverlayModule() {
      super("Block Overlay");
      this.setDefaultState(true);
      new Setting(this, "label").setValue("General Options");
      this.field_0007 = new Setting(this, "Outline").setValue("Full").acceptedValues("OFF", "Full");
      this.field_0017 = new Setting(this, "Overlay").setValue("OFF").acceptedValues("OFF", "Full");
      this.field_0008 = new Setting(this, "Line Width")
         .setValue(2.0F)
         .setMinMax(1.0F, 10.0F)
         .method_08894(() -> !Boolean.valueOf(this.field_0007.getValue().equals("OFF")));
      this.field_0001 = new Setting(this, "Gap").setValue(1.0F).setMinMax(1.0F, 10.0F);
      this.field_0014 = new Setting(this, "Render without Depth").setValue(false);
      this.field_0004 = new Setting(this, "Hide Plants").setValue(false);
      new Setting(this, "label")
         .setValue("Color Options")
         .method_08894(() -> !Boolean.valueOf(this.field_0007.getValue().equals("OFF")) || !Boolean.valueOf(this.field_0017.getValue().equals("OFF")));
      this.field_0005 = new Setting(this, "Outline Color")
         .setValue(1711276032)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> !Boolean.valueOf(this.field_0007.getValue().equals("OFF")));
      this.field_0010 = new Setting(this, "Outline 2 Color")
         .setValue(1711276032)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> !Boolean.valueOf(this.field_0007.getValue().equals("OFF")));
      this.field_0016 = new Setting(this, "Overlay Color")
         .setValue(1056964608)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> !Boolean.valueOf(this.field_0017.getValue().equals("OFF")));
      this.field_0006 = new Setting(this, "Overlay 2 Color")
         .setValue(1056964608)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> !Boolean.valueOf(this.field_0017.getValue().equals("OFF")));
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/overlay.png"), 36, 40);
      this.method_28821("Replace the Vanilla outline with a customizable outline and overlay.");
      this.method_28829("aycy");
   }

   public void method_12508(EntityPlayer var1, MovingObjectPosition var2, int var3, float var4, WorldClient var5) {
      if (var3 == 0 && var2.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.color(1.0F, 0.0F, 0.0F, 0.4F);
         GL11.glLineWidth(2.0F);
         GlStateManager.disableTexture2D();
         if (Config.isShaders()) {
            Shaders.disableTexture2D();
         }

         GlStateManager.depthMask(false);
         float var6 = 0.002F;
         BlockPos var7 = var2.getBlockPos();
         Block var8 = var5.getBlockState(var7).getBlock();
         if (var8.getMaterial() != Material.air && var5.af().contains(var7)) {
            var8.setBlockBoundsBasedOnState(var5, var7);
            double var9 = var1.P + (var1.s - var1.P) * var4;
            double var11 = var1.Q + (var1.t - var1.Q) * var4;
            double var13 = var1.R + (var1.u - var1.R) * var4;
            AxisAlignedBB var15 = var8.getSelectedBoundingBox(var5, var7);
            Block$EnumOffsetType var16 = var8.getOffsetType();
            if (var16 != Block$EnumOffsetType.NONE) {
               var15 = BlockModelUtils.getOffsetBoundingBox(var15, var16, var7);
            }

            method_12510(
               var15.expand(0.002F, 0.002F, 0.002F).offset(-var9, -var11, -var13),
               null,
               this.field_0016.method_08901(),
               this.field_0006.method_08901(),
               this.field_0005.method_08901(),
               this.field_0010.method_08901(),
               this.field_0017.getValue().equals("Full"),
               this.field_0007.getValue().equals("Full")
            );
         }

         GlStateManager.depthMask(true);
         GlStateManager.enableTexture2D();
         if (Config.isShaders()) {
            Shaders.enableTexture2D();
         }

         GlStateManager.disableBlend();
      }
   }
}
