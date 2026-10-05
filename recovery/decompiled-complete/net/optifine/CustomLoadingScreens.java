package net.optifine;

import io.netty.handler.ssl.SslHandler$6;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.src.Config;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.AsyncAppender$DiscardSummary;

public class CustomLoadingScreens {
   public SslHandler$6 field_0001;
   public AsyncAppender$DiscardSummary field_0003;
   public static int screensMinDimensionId = 0;
   public static CustomLoadingScreen[] screens = null;

   public static Pair<CustomLoadingScreen[], Integer> parseScreens() {
      String var0 = "optifine/gui/loading/background";
      String var1 = ".png";
      String[] var2 = ResUtils.collectFiles(var0, var1);
      HashMap var3 = new HashMap();

      for (int var4 = 0; var4 < var2.length; var4++) {
         String var5 = var2[var4];
         String var6 = StrUtils.removePrefixSuffix(var5, var0, var1);
         int var7 = Config.parseInt(var6, Integer.MIN_VALUE);
         if (var7 == Integer.MIN_VALUE) {
            warn("Invalid dimension ID: " + var6 + ", path: " + var5);
         } else {
            var3.put(var7, var5);
         }
      }

      Set var15 = var3.keySet();
      Integer[] var16 = var15.toArray(new Integer[var15.size()]);
      Arrays.sort(var16);
      if (var16.length <= 0) {
         return new ImmutablePair(null, 0);
      } else {
         String var17 = "optifine/gui/loading/loading.properties";
         Properties var18 = ResUtils.readProperties(var17, "CustomLoadingScreens");
         int var8 = var16[0];
         int var9 = var16[var16.length - 1];
         int var10 = var9 - var8 + 1;
         CustomLoadingScreen[] var11 = new CustomLoadingScreen[var10];

         for (int var12 = 0; var12 < var16.length; var12++) {
            Integer var13 = var16[var12];
            String var14 = (String)var3.get(var13);
            var11[var13 - var8] = CustomLoadingScreen.parseScreen(var14, var13, var18);
         }

         return new ImmutablePair(var11, var8);
      }
   }

   public static void update() {
      screens = null;
      screensMinDimensionId = 0;
      Pair var0 = parseScreens();
      screens = (CustomLoadingScreen[])var0.getLeft();
      screensMinDimensionId = (Integer)var0.getRight();
   }

   public static CustomLoadingScreen getCustomLoadingScreen() {
      if (screens == null) {
         return null;
      } else {
         int var0 = PacketThreadUtil.lastDimensionId;
         int var1 = var0 - screensMinDimensionId;
         CustomLoadingScreen var2 = null;
         if (var1 >= 0 && var1 < screens.length) {
            var2 = screens[var1];
         }

         return var2;
      }
   }

   public static void method_20126(String var0) {
      Config.dbg("CustomLoadingScreen: " + var0);
   }

   public static void warn(String var0) {
      Config.warn("CustomLoadingScreen: " + var0);
   }
}
