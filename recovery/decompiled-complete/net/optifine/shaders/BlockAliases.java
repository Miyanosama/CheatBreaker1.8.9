package net.optifine.shaders;

import io.netty.channel.sctp.DefaultSctpChannelConfig;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;

public class BlockAliases {
   public static PropertiesOrdered blockLayerPropertes = null;
   public ByteArrayDecoder field_0004;
   public static boolean updateOnResourcesReloaded;
   public static BlockAlias[][] blockAliases = (BlockAlias[][])null;
   public DefaultSctpChannelConfig field_0000;

   public static BlockAlias[][] toArrays(List<List<BlockAlias>> var0) {
      BlockAlias[][] var1 = new BlockAlias[var0.size()][];

      for (int var2 = 0; var2 < var1.length; var2++) {
         List var3 = (List)var0.get(var2);
         if (var3 != null) {
            var1[var2] = var3.toArray(new BlockAlias[var3.size()]);
         }
      }

      return var1;
   }

   public static int getBlockAliasId(int var0, int var1) {
      if (blockAliases == null) {
         return var0;
      } else if (var0 >= 0 && var0 < blockAliases.length) {
         BlockAlias[] var2 = blockAliases[var0];
         if (var2 == null) {
            return var0;
         } else {
            for (int var3 = 0; var3 < var2.length; var3++) {
               BlockAlias var4 = var2[var3];
               if (var4.matches(var0, var1)) {
                  return var4.getBlockAliasId();
               }
            }

            return var0;
         }
      } else {
         return var0;
      }
   }

   public static void loadModBlockAliases(List<List<BlockAlias>> var0) {
      String[] var1 = ReflectorForge.getForgeModIds();

      for (int var2 = 0; var2 < var1.length; var2++) {
         String var3 = var1[var2];

         try {
            ResourceLocation var4 = new ResourceLocation(var3, "shaders/block.properties");
            InputStream var5 = Config.getResourceStream(var4);
            loadBlockAliases(var5, var4.toString(), var0);
         } catch (IOException var6) {
         }
      }
   }

   public static void loadBlockAliases(InputStream var0, String var1, List<List<BlockAlias>> var2) {
      if (var0 != null) {
         try {
            var0 = MacroProcessor.process(var0, var1);
            PropertiesOrdered var3 = new PropertiesOrdered();
            var3.load(var0);
            var0.close();
            Config.dbg("[Shaders] Parsing block mappings: " + var1);
            ConnectedParser var4 = new ConnectedParser("Shaders");

            for (Object var6 : var3.keySet()) {
               String var7 = (String)var6;
               String var8 = var3.getProperty(var7);
               if (var7.startsWith("layer.")) {
                  if (blockLayerPropertes == null) {
                     blockLayerPropertes = new PropertiesOrdered();
                  }

                  blockLayerPropertes.put(var7, var8);
               } else {
                  String var9 = "block.";
                  if (!var7.startsWith(var9)) {
                     Config.warn("[Shaders] Invalid block ID: " + var7);
                  } else {
                     String var10 = StrUtils.removePrefix(var7, var9);
                     int var11 = Config.parseInt(var10, -1);
                     if (var11 < 0) {
                        Config.warn("[Shaders] Invalid block ID: " + var7);
                     } else {
                        MatchBlock[] var12 = var4.parseMatchBlocks(var8);
                        if (var12 != null && var12.length >= 1) {
                           BlockAlias var13 = new BlockAlias(var11, var12);
                           addToList(var2, var13);
                        } else {
                           Config.warn("[Shaders] Invalid block ID mapping: " + var7 + "=" + var8);
                        }
                     }
                  }
               }
            }
         } catch (IOException var14) {
            Config.warn("[Shaders] Error reading: " + var1);
         }
      }
   }

   public static PropertiesOrdered getBlockLayerPropertes() {
      return blockLayerPropertes;
   }

   public static void update(IShaderPack var0) {
      method_07078();
      if (var0 != null) {
         if (Reflector.Loader_getActiveModList.exists() && Minecraft.getMinecraft().getResourcePackRepository() == null) {
            Config.dbg("[Shaders] Delayed loading of block mappings after resources are loaded");
            updateOnResourcesReloaded = true;
         } else {
            ArrayList var1 = new ArrayList();
            String var2 = "/shaders/block.properties";
            InputStream var3 = var0.getResourceAsStream(var2);
            if (var3 != null) {
               loadBlockAliases(var3, var2, var1);
            }

            loadModBlockAliases(var1);
            if (var1.size() > 0) {
               blockAliases = toArrays(var1);
            }
         }
      }
   }

   public static void addToList(List<List<BlockAlias>> var0, BlockAlias var1) {
      int[] var2 = var1.getMatchBlockIds();

      for (int var3 = 0; var3 < var2.length; var3++) {
         int var4 = var2[var3];

         while (var4 >= var0.size()) {
            var0.add(null);
         }

         Object var5 = (List)var0.get(var4);
         if (var5 == null) {
            var5 = new ArrayList();
            var0.set(var4, var5);
         }

         BlockAlias var6 = new BlockAlias(var1.getBlockAliasId(), var1.getMatchBlocks(var4));
         var5.add(var6);
      }
   }

   public static void resourcesReloaded() {
      if (updateOnResourcesReloaded) {
         updateOnResourcesReloaded = false;
         update(Shaders.getShaderPack());
      }
   }

   public static void method_07078() {
      blockAliases = (BlockAlias[][])null;
      blockLayerPropertes = null;
   }
}
