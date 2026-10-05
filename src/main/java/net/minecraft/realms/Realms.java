package net.minecraft.realms;

import com.google.common.util.concurrent.ListenableFuture;
import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import java.net.Proxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.Session;
import net.minecraft.world.WorldSettings;

public class Realms {
   public static String method_24130() {
      return Minecraft.getMinecraft().getSession().getPlayerID();
   }

   public static String method_24141() {
      Session var0 = Minecraft.getMinecraft().getSession();
      return var0 == null ? null : var0.getUsername();
   }

   public static void setConnectedToRealms(boolean var0) {
      Minecraft.getMinecraft().setConnectedToRealms(var0);
   }

   public static int creativeId() {
      return WorldSettings.GameType.CREATIVE.getID();
   }

   public static void setScreen(RealmsScreen var0) {
      Minecraft.getMinecraft().displayGuiScreen(var0.getProxy());
   }

   public static int spectatorId() {
      return WorldSettings.GameType.SPECTATOR.getID();
   }

   public static String method_24125() {
      return Minecraft.getMinecraft().getSession().getSessionID();
   }

   public static int survivalId() {
      return WorldSettings.GameType.SURVIVAL.getID();
   }

   public static long currentTimeMillis() {
      return Minecraft.getSystemTime();
   }

   public static String method_24137() {
      Session var0 = Minecraft.getMinecraft().getSession();
      return var0 == null ? null : var0.getSessionID();
   }

   public static String getGameDirectoryPath() {
      return Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
   }

   public static ListenableFuture<Object> downloadResourcePack(String var0, String var1) {
      return Minecraft.getMinecraft().getResourcePackRepository().downloadResourcePack(var0, var1);
   }

   public static String uuidToName(String var0) {
      return Minecraft.getMinecraft()
         .getSessionService()
         .fillProfileProperties(new GameProfile(UUIDTypeAdapter.fromString(var0), (String)null), false)
         .getName();
   }

   public static void clearResourcePack() {
      Minecraft.getMinecraft().getResourcePackRepository().clearResourcePack();
   }

   public static int adventureId() {
      return WorldSettings.GameType.ADVENTURE.getID();
   }

   public static Proxy getProxy() {
      return Minecraft.getMinecraft().getProxy();
   }

   public static String method_24136() {
      return Minecraft.getMinecraft().getSession().getUsername();
   }

   public static boolean inTitleScreen() {
      return Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu;
   }

   public static boolean getRealmsNotificationsEnabled() {
      return Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(GameSettings.Options.REALMS_NOTIFICATIONS);
   }

   public static boolean isTouchScreen() {
      return Minecraft.getMinecraft().gameSettings.touchscreen;
   }
}
