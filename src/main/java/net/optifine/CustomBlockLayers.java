package net.optifine;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.src.Config;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.shaders.BlockAliases;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;

public class CustomBlockLayers {
   public static EnumWorldBlockLayer[] renderLayers = null;
   public static boolean active = false;

   public static void readLayer(String var0, EnumWorldBlockLayer var1, Properties var2, List<EnumWorldBlockLayer> var3) {
      String var4 = "layer." + var0;
      String var5 = var2.getProperty(var4);
      if (var5 != null) {
         ConnectedParser var6 = new ConnectedParser("CustomBlockLayers");
         MatchBlock[] var7 = var6.parseMatchBlocks(var5);
         if (var7 != null) {
            for (int var8 = 0; var8 < var7.length; var8++) {
               MatchBlock var9 = var7[var8];
               int var10 = var9.getBlockId();
               if (var10 > 0) {
                  while (var3.size() < var10 + 1) {
                     var3.add(null);
                  }

                  if (var3.get(var10) != null) {
                     Config.warn("CustomBlockLayers: Block layer is already set, block: " + var10 + ", layer: " + var0);
                  }

                  var3.set(var10, var1);
               }
            }
         }
      }
   }

   public static void update() {
      renderLayers = null;
      active = false;
      ArrayList var0 = new ArrayList();
      String var1 = "optifine/block.properties";
      Properties var2 = ResUtils.readProperties(var1, "CustomBlockLayers");
      if (var2 != null) {
         readLayers(var1, var2, var0);
      }

      if (Config.isShaders()) {
         PropertiesOrdered var3 = BlockAliases.getBlockLayerPropertes();
         if (var3 != null) {
            String var4 = "shaders/block.properties";
            readLayers(var4, var3, var0);
         }
      }

      if (!var0.isEmpty()) {
         renderLayers = (net.minecraft.util.EnumWorldBlockLayer[])var0.toArray(new EnumWorldBlockLayer[var0.size()]);
         active = true;
      }
   }

   public static void readLayers(String var0, Properties var1, List<EnumWorldBlockLayer> var2) {
      Config.dbg("CustomBlockLayers: " + var0);
      readLayer("solid", EnumWorldBlockLayer.SOLID, var1, var2);
      readLayer("cutout", EnumWorldBlockLayer.CUTOUT, var1, var2);
      readLayer("cutout_mipped", EnumWorldBlockLayer.CUTOUT_MIPPED, var1, var2);
      readLayer("translucent", EnumWorldBlockLayer.TRANSLUCENT, var1, var2);
   }

   public static EnumWorldBlockLayer getRenderLayer(IBlockState var0) {
      if (renderLayers == null) {
         return null;
      } else if (var0.getBlock().isOpaqueCube()) {
         return null;
      } else if (!(var0 instanceof BlockStateBase)) {
         return null;
      } else {
         BlockStateBase var1 = (BlockStateBase)var0;
         int var2 = var1.getBlockId();
         return var2 > 0 && var2 < renderLayers.length ? renderLayers[var2] : null;
      }
   }

   public static boolean isActive() {
      return active;
   }
}
