package net.minecraft.world.chunk.storage;

import com.cheatbreaker.client.websocket.shared.WSPacketFriendUpdate;
import com.google.common.collect.Maps;
import io.netty.handler.codec.rtsp.RtspHeaders$Values;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.optifine.NextTickHashSet;
import net.optifine.util.TextureUtils$2;
import recovered.unidentified.UnidentifiedClass0968;
import recovered.unidentified.UnidentifiedClass4984;

public class RegionFileCache {
   public RtspHeaders$Values field_0004;
   public BiomeGenTaiga field_0007;
   public TextureUtils$2 field_0003;
   public EntityPainting field_0006;
   public UnidentifiedClass0968 field_0000;
   public NextTickHashSet field_0001;
   public UnidentifiedClass4984 field_0008;
   public static Map<File, RegionFile> regionsByFilename = Maps.newHashMap();
   public WSPacketFriendUpdate field_0002;

   public static DataInputStream getChunkInputStream(File var0, int var1, int var2) {
      RegionFile var3 = createOrLoadRegionFile(var0, var1, var2);
      return var3.getChunkDataInputStream(var1 & 31, var2 & 31);
   }

   public static synchronized void clearRegionFileReferences() {
      for (RegionFile var1 : regionsByFilename.values()) {
         try {
            if (var1 != null) {
               var1.close();
            }
         } catch (IOException var3) {
            var3.printStackTrace();
         }
      }

      regionsByFilename.clear();
   }

   public static synchronized RegionFile createOrLoadRegionFile(File var0, int var1, int var2) {
      File var3 = new File(var0, "region");
      File var4 = new File(var3, "r." + (var1 >> 5) + "." + (var2 >> 5) + ".mca");
      RegionFile var5 = regionsByFilename.get(var4);
      if (var5 != null) {
         return var5;
      } else {
         if (!var3.exists()) {
            var3.mkdirs();
         }

         if (regionsByFilename.size() >= 256) {
            clearRegionFileReferences();
         }

         RegionFile var6 = new RegionFile(var4);
         regionsByFilename.put(var4, var6);
         return var6;
      }
   }

   public static DataOutputStream getChunkOutputStream(File var0, int var1, int var2) {
      RegionFile var3 = createOrLoadRegionFile(var0, var1, var2);
      return var3.getChunkDataOutputStream(var1 & 31, var2 & 31);
   }
}
