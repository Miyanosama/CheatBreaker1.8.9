package org.scijava.nativelib;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.network.play.server.S21PacketChunkData$Extracted;
import net.optifine.entity.model.anim.RenderResolverEntity;

public class MxSysInfo {
   public BlockBasePressurePlate field_0001;
   public S21PacketChunkData$Extracted field_0003;
   public RenderResolverEntity field_0000;
   public EnchantmentThorns field_0002;

   public static String method_21014() {
      String var0 = System.getProperty("os.arch");
      String var1 = System.getProperty("os.name");
      String var2 = "unknown";
      if ("Linux".equals(var1)) {
         try {
            String var3 = new File("/lib/libc.so.6").getCanonicalPath();
            Matcher var4 = Pattern.compile(".*/libc-(\\d+)\\.(\\d+)\\..*").matcher(var3);
            if (!var4.matches()) {
               throw new IOException("libc symlink contains unexpected destination: " + var3);
            }

            File var5 = new File("/usr/lib/libstdc++.so.6");
            if (!var5.exists()) {
               var5 = new File("/usr/lib/libstdc++.so.5");
            }

            String var6 = var5.getCanonicalPath();
            Matcher var7 = Pattern.compile(".*/libstdc\\+\\+\\.so\\.(\\d+)\\.0\\.(\\d+)").matcher(var6);
            if (!var7.matches()) {
               throw new IOException("libstdc++ symlink contains unexpected destination: " + var6);
            }

            String var8;
            if ("5".equals(var7.group(1))) {
               var8 = "5";
            } else if ("6".equals(var7.group(1))) {
               int var9 = Integer.parseInt(var7.group(2));
               if (var9 < 9) {
                  var8 = "6";
               } else {
                  var8 = "6" + var7.group(2);
               }
            } else {
               var8 = var7.group(1) + var7.group(2);
            }

            var2 = "c" + var4.group(1) + var4.group(2) + "cxx" + var8;
         } catch (IOException var13) {
            var2 = "unknown";
         } finally {
            ;
         }
      }

      return var0 + "-" + var1 + "-" + var2;
   }

   public static String method_21015() {
      String var0 = System.getProperty("mx.sysinfo");
      return var0 != null ? var0 : method_21014();
   }
}
