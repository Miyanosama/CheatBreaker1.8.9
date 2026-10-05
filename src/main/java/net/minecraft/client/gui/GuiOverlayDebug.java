package net.minecraft.client.gui;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.optifine.SmartAnimations;
import net.optifine.TextureAnimations;
import net.optifine.reflect.Reflector;
import net.optifine.util.MemoryMonitor;
import net.optifine.util.NativeMemory;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.GuiOverlayDebug$1;

public class GuiOverlayDebug extends Gui {
   public List<String> recoveredField1311;
   public FontRenderer recoveredField1312;
   public long recoveredField1313;
   public String debugOF = null;
   public long recoveredField1314;
   public List<String> recoveredField1315 = null;
   public Minecraft mc;

   public void renderDebugInfoLeft() {
      List var1 = this.recoveredField1315;
      if (var1 == null || System.currentTimeMillis() > this.recoveredField1314) {
         var1 = this.call();
         this.recoveredField1315 = var1;
         this.recoveredField1314 = System.currentTimeMillis() + 100L;
      }

      for (int var2 = 0; var2 < var1.size(); var2++) {
         String var3 = (String)var1.get(var2);
         if (!Strings.isNullOrEmpty(var3)) {
            int var4 = this.recoveredField1312.FONT_HEIGHT;
            int var5 = this.recoveredField1312.getStringWidth(var3);
            byte var6 = 2;
            int var7 = 2 + var4 * var2;
            a(1, var7 - 1, 2 + var5 + 1, var7 + var4 - 1, -1873784752);
            this.recoveredField1312.drawString(var3, 2, var7, 14737632);
         }
      }
   }

   public List<String> getDebugInfoRight() {
      long var1 = Runtime.getRuntime().maxMemory();
      long var3 = Runtime.getRuntime().totalMemory();
      long var5 = Runtime.getRuntime().freeMemory();
      long var7 = var3 - var5;
      ArrayList var9 = Lists.newArrayList(
         String.format("Java: %s %dbit", System.getProperty("java.version"), this.mc.method_20351() ? 64 : 32),
         String.format("Mem: % 2d%% %03d/%03dMB", var7 * 100L / var1, bytesToMb(var7), bytesToMb(var1)),
         String.format("Allocated: % 2d%% %03dMB", var3 * 100L / var1, bytesToMb(var3)),
         "",
         String.format("CPU: %s", OpenGlHelper.getCpu()),
         "",
         String.format("Display: %dx%d (%s)", Display.getWidth(), Display.getHeight(), GL11.glGetString(7936)),
         GL11.glGetString(7937),
         GL11.glGetString(7938)
      );
      long var10 = NativeMemory.method_29547();
      long var12 = NativeMemory.method_29544();
      String var14 = "Native: " + bytesToMb(var10) + "/" + bytesToMb(var12) + "MB";
      var9.add(4, var14);
      var9.set(5, "GC: " + MemoryMonitor.getAllocationRateMb() + "MB/s");
      if (Reflector.FMLCommonHandler_getBrandings.exists()) {
         Object var15 = Reflector.call(Reflector.FMLCommonHandler_instance);
         var9.add("");
         var9.addAll((Collection)Reflector.call(var15, Reflector.FMLCommonHandler_getBrandings, false));
      }

      if (this.isReducedDebug()) {
         return var9;
      } else {
         if (this.mc.objectMouseOver != null
            && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
            && this.mc.objectMouseOver.getBlockPos() != null) {
            BlockPos var20 = this.mc.objectMouseOver.getBlockPos();
            IBlockState var16 = this.mc.theWorld.getBlockState(var20);
            if (this.mc.theWorld.x_() != WorldType.DEBUG_WORLD) {
               var16 = var16.getBlock().getActualState(var16, this.mc.theWorld, var20);
            }

            var9.add("");
            var9.add(String.valueOf(Block.blockRegistry.getNameForObject(var16.getBlock())));

            for (Entry var18 : var16.getProperties().entrySet()) {
               java.lang.String var19 = ((Comparable)var18.getValue()).toString();
               if (var18.getValue() == Boolean.TRUE) {
                  var19 = EnumChatFormatting.GREEN + var19;
               } else if (var18.getValue() == Boolean.FALSE) {
                  var19 = EnumChatFormatting.RED + var19;
               }

               var9.add(((IProperty)var18.getKey()).getName() + ": " + var19);
            }
         }

         return var9;
      }
   }

   public static long bytesToMb(long var0) {
      return var0 / 1024L / 1024L;
   }

   public void renderDebugInfoRight(ScaledResolution var1) {
      List var2 = this.recoveredField1311;
      if (var2 == null || System.currentTimeMillis() > this.recoveredField1313) {
         var2 = this.getDebugInfoRight();
         this.recoveredField1311 = var2;
         this.recoveredField1313 = System.currentTimeMillis() + 100L;
      }

      for (int var3 = 0; var3 < var2.size(); var3++) {
         String var4 = (String)var2.get(var3);
         if (!Strings.isNullOrEmpty(var4)) {
            int var5 = this.recoveredField1312.FONT_HEIGHT;
            int var6 = this.recoveredField1312.getStringWidth(var4);
            int var7 = var1.getScaledWidth() - 2 - var6;
            int var8 = 2 + var5 * var3;
            a(var7 - 1, var8 - 1, var7 + var6 + 1, var8 + var5 - 1, -1873784752);
            this.recoveredField1312.drawString(var4, var7, var8, 14737632);
         }
      }
   }

   public boolean isReducedDebug() {
      return this.mc.thePlayer.hasReducedDebug() || this.mc.gameSettings.reducedDebugInfo;
   }

   public List<String> call() {
      BlockPos var1 = new BlockPos(this.mc.getRenderViewEntity().s, this.mc.getRenderViewEntity().getEntityBoundingBox().b, this.mc.getRenderViewEntity().u);
      if (this.mc.debug != this.debugOF) {
         StringBuffer var2 = new StringBuffer(this.mc.debug);
         int var3 = Config.getFpsMin();
         int var4 = this.mc.debug.indexOf(" fps ");
         if (var4 >= 0) {
            var2.insert(var4, "/" + var3);
         }

         if (Config.isSmoothFps()) {
            var2.append(" sf");
         }

         if (Config.isFastRender()) {
            var2.append(" fr");
         }

         if (Config.isAnisotropicFiltering()) {
            var2.append(" af");
         }

         if (Config.isAntialiasing()) {
            var2.append(" aa");
         }

         if (Config.isRenderRegions()) {
            var2.append(" reg");
         }

         if (Config.isShaders()) {
            var2.append(" sh");
         }

         this.mc.debug = var2.toString();
         this.debugOF = this.mc.debug;
      }

      StringBuilder var13 = new StringBuilder();
      TextureMap var14 = Config.getTextureMap();
      var13.append(", A: ");
      if (SmartAnimations.isActive()) {
         var13.append(var14.method_22996() + TextureAnimations.getCountAnimationsActive());
         var13.append("/");
      }

      var13.append(var14.getCountAnimations() + TextureAnimations.getCountAnimations());
      String var15 = var13.toString();
      if (this.isReducedDebug()) {
         return Lists.newArrayList(
            "Minecraft 1.8.9 (" + this.mc.getVersion() + "/" + ClientBrandRetriever.getClientModName() + ")",
            this.mc.debug,
            this.mc.renderGlobal.getDebugInfoRenders(),
            this.mc.renderGlobal.getDebugInfoEntities(),
            "P: " + this.mc.effectRenderer.getStatistics() + ". T: " + this.mc.theWorld.getDebugLoadedEntities() + var15,
            this.mc.theWorld.getProviderName(),
            "",
            String.format("Chunk-relative: %d %d %d", var1.getX() & 15, var1.getY() & 15, var1.getZ() & 15)
         );
      } else {
         Entity var5 = this.mc.getRenderViewEntity();
         EnumFacing var6 = var5.getHorizontalFacing();
         String var7 = "Invalid";
         switch (GuiOverlayDebug$1.recoveredField2795[var6.ordinal()]) {
            case 1:
               var7 = "Towards negative Z";
               break;
            case 2:
               var7 = "Towards positive Z";
               break;
            case 3:
               var7 = "Towards negative X";
               break;
            case 4:
               var7 = "Towards positive X";
         }

         ArrayList var8 = Lists.newArrayList(
            "Minecraft 1.8.9 (" + this.mc.getVersion() + "/" + ClientBrandRetriever.getClientModName() + ")",
            this.mc.debug,
            this.mc.renderGlobal.getDebugInfoRenders(),
            this.mc.renderGlobal.getDebugInfoEntities(),
            "P: " + this.mc.effectRenderer.getStatistics() + ". T: " + this.mc.theWorld.getDebugLoadedEntities() + var15,
            this.mc.theWorld.getProviderName(),
            "",
            String.format(
               "XYZ: %.3f / %.5f / %.3f",
               this.mc.getRenderViewEntity().s,
               this.mc.getRenderViewEntity().getEntityBoundingBox().b,
               this.mc.getRenderViewEntity().u
            ),
            String.format("Block: %d %d %d", var1.getX(), var1.getY(), var1.getZ()),
            String.format(
               "Chunk: %d %d %d in %d %d %d", var1.getX() & 15, var1.getY() & 15, var1.getZ() & 15, var1.getX() >> 4, var1.getY() >> 4, var1.getZ() >> 4
            ),
            String.format("Facing: %s (%s) (%.1f / %.1f)", var6, var7, MathHelper.wrapAngleTo180_float(var5.y), MathHelper.wrapAngleTo180_float(var5.z))
         );
         if (this.mc.theWorld != null && this.mc.theWorld.e(var1)) {
            Chunk var9 = this.mc.theWorld.getChunkFromBlockCoords(var1);
            var8.add("Biome: " + var9.getBiome(var1, this.mc.theWorld.getWorldChunkManager()).ah);
            var8.add(
               "Light: "
                  + var9.getLightSubtracted(var1, 0)
                  + " ("
                  + var9.getLightFor(EnumSkyBlock.SKY, var1)
                  + " sky, "
                  + var9.getLightFor(EnumSkyBlock.BLOCK, var1)
                  + " block)"
            );
            DifficultyInstance var10 = this.mc.theWorld.E(var1);
            if (this.mc.isIntegratedServerRunning() && this.mc.getIntegratedServer() != null) {
               EntityPlayerMP var11 = this.mc.getIntegratedServer().getConfigurationManager().getPlayerByUUID(this.mc.thePlayer.aK());
               if (var11 != null) {
                  DifficultyInstance var12 = this.mc.getIntegratedServer().getDifficultyAsync(var11.o, new BlockPos(var11));
                  if (var12 != null) {
                     var10 = var12;
                  }
               }
            }

            var8.add(String.format("Local Difficulty: %.2f (Day %d)", var10.getAdditionalDifficulty(), this.mc.theWorld.L() / 24000L));
         }

         if (this.mc.entityRenderer != null && this.mc.entityRenderer.isShaderActive()) {
            var8.add("Shader: " + this.mc.entityRenderer.getShaderGroup().getShaderGroupName());
         }

         if (this.mc.objectMouseOver != null
            && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
            && this.mc.objectMouseOver.getBlockPos() != null) {
            BlockPos var16 = this.mc.objectMouseOver.getBlockPos();
            var8.add(String.format("Looking at: %d %d %d", var16.getX(), var16.getY(), var16.getZ()));
         }

         return var8;
      }
   }

   public int blendColors(int var1, int var2, float var3) {
      int var4 = var1 >> 24 & 0xFF;
      int var5 = var1 >> 16 & 0xFF;
      int var6 = var1 >> 8 & 0xFF;
      int var7 = var1 & 0xFF;
      int var8 = var2 >> 24 & 0xFF;
      int var9 = var2 >> 16 & 0xFF;
      int var10 = var2 >> 8 & 0xFF;
      int var11 = var2 & 0xFF;
      int var12 = MathHelper.clamp_int((int)(var4 + (var8 - var4) * var3), 0, 255);
      int var13 = MathHelper.clamp_int((int)(var5 + (var9 - var5) * var3), 0, 255);
      int var14 = MathHelper.clamp_int((int)(var6 + (var10 - var6) * var3), 0, 255);
      int var15 = MathHelper.clamp_int((int)(var7 + (var11 - var7) * var3), 0, 255);
      return var12 << 24 | var13 << 16 | var14 << 8 | var15;
   }

   public void renderLagometer() {
   }

   public int getFrameColor(int var1, int var2, int var3, int var4) {
      return var1 < var3 ? this.blendColors(-16711936, -256, (float)var1 / var3) : this.blendColors(-256, -65536, (float)(var1 - var3) / (var4 - var3));
   }

   public GuiOverlayDebug(Minecraft var1) {
      this.recoveredField1311 = null;
      this.recoveredField1314 = 0L;
      this.recoveredField1313 = 0L;
      this.mc = var1;
      this.recoveredField1312 = var1.fontRendererObj;
   }

   public void renderDebugInfo(ScaledResolution var1) {
      this.mc.mcProfiler.startSection("debug");
      GlStateManager.pushMatrix();
      this.renderDebugInfoLeft();
      this.renderDebugInfoRight(var1);
      GlStateManager.popMatrix();
      if (this.mc.gameSettings.showLagometer) {
         this.renderLagometer();
      }

      this.mc.mcProfiler.endSection();
   }
}
