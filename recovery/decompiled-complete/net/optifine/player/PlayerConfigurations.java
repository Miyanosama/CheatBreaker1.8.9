package net.optifine.player;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Minecraft$15;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.world.gen.structure.MapGenStronghold$Start;
import net.optifine.http.FileDownloadThread;
import net.optifine.http.HttpUtils;

public class PlayerConfigurations {
   public static boolean reloadPlayerItems = Boolean.getBoolean("player.models.reload");
   public static long timeReloadPlayerItemsMs = System.currentTimeMillis();
   public Minecraft$15 field_0001;
   public static Map mapConfigurations = null;
   public MapGenStronghold$Start field_0000;

   public static synchronized PlayerConfiguration getPlayerConfiguration(AbstractClientPlayer var0) {
      if (reloadPlayerItems && System.currentTimeMillis() > timeReloadPlayerItemsMs + (1083478968L & 557997960L)) {
         EntityPlayerSP var1 = Minecraft.getMinecraft().thePlayer;
         if (var1 != null) {
            setPlayerConfiguration(var1.getNameClear(), (PlayerConfiguration)null);
            timeReloadPlayerItemsMs = System.currentTimeMillis();
         }
      }

      String var6 = var0.getNameClear();
      if (var6 == null) {
         return null;
      } else {
         PlayerConfiguration var2 = (PlayerConfiguration)getMapConfigurations().get(var6);
         if (var2 == null) {
            var2 = new PlayerConfiguration();
            getMapConfigurations().put(var6, var2);
            PlayerConfigurationReceiver var3 = new PlayerConfigurationReceiver(var6);
            String var4 = HttpUtils.getPlayerItemsUrl() + "/users/" + var6 + ".cfg";
            FileDownloadThread var5 = new FileDownloadThread(var4, var3);
            var5.start();
         }

         return var2;
      }
   }

   public static void renderPlayerItems(ModelBiped var0, AbstractClientPlayer var1, float var2, float var3) {
      PlayerConfiguration var4 = getPlayerConfiguration(var1);
      if (var4 != null) {
         var4.renderPlayerItems(var0, var1, var2, var3);
      }
   }

   public static Map getMapConfigurations() {
      if (mapConfigurations == null) {
         mapConfigurations = new HashMap();
      }

      return mapConfigurations;
   }

   public static synchronized void setPlayerConfiguration(String var0, PlayerConfiguration var1) {
      getMapConfigurations().put(var0, var1);
   }
}
