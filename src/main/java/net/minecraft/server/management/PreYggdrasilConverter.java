package net.minecraft.server.management;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PreYggdrasilConverter {
   public static Logger LOGGER = LogManager.getLogger();
   public static File OLD_IPBAN_FILE = new File("banned-ips.txt");
   public static File OLD_PLAYERBAN_FILE = new File("banned-players.txt");
   public static File OLD_OPS_FILE = new File("ops.txt");
   public static File OLD_WHITELIST_FILE = new File("white-list.txt");

   public static void lookupNames(MinecraftServer var0, Collection<String> var1, ProfileLookupCallback var2) {
      String[] var3 = Iterators.toArray(Iterators.filter(var1.iterator(), new Predicate<String>() {
         public boolean apply(String var1) {
            return !StringUtils.isNullOrEmpty(var1);
         }
      }), String.class);
      if (var0.isServerInOnlineMode()) {
         var0.getGameProfileRepository().findProfilesByNames(var3, Agent.MINECRAFT, var2);
      } else {
         for (String var7 : var3) {
            UUID var8 = EntityPlayer.getUUID(new GameProfile((UUID)null, var7));
            GameProfile var9 = new GameProfile(var8, var7);
            var2.onProfileLookupSucceeded(var9);
         }
      }
   }

   public static String getStringUUIDFromName(String var0) {
      if (!StringUtils.isNullOrEmpty(var0) && var0.length() <= 16) {
         final MinecraftServer var1 = MinecraftServer.getServer();
         GameProfile var2 = var1.getPlayerProfileCache().getGameProfileForUsername(var0);
         if (var2 != null && var2.getId() != null) {
            return var2.getId().toString();
         } else if (!var1.isSinglePlayer() && var1.isServerInOnlineMode()) {
            final ArrayList var3 = Lists.newArrayList();
            ProfileLookupCallback var4 = new ProfileLookupCallback() {
               @Override
               public void onProfileLookupSucceeded(GameProfile var1x) {
                  var1.getPlayerProfileCache().addEntry(var1x);
                  var3.add(var1x);
               }

               @Override
               public void onProfileLookupFailed(GameProfile var1x, Exception var2x) {
                  PreYggdrasilConverter.LOGGER.warn("Could not lookup user whitelist entry for " + var1x.getName(), var2x);
               }
            };
            lookupNames(var1, Lists.newArrayList(var0), var4);
            return var3.size() > 0 && ((GameProfile)var3.get(0)).getId() != null ? ((GameProfile)var3.get(0)).getId().toString() : "";
         } else {
            return EntityPlayer.getUUID(new GameProfile((UUID)null, var0)).toString();
         }
      } else {
         return var0;
      }
   }
}
