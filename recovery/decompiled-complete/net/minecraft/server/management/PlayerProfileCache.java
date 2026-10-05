package net.minecraft.server.management;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.entity.monster.EntitySlime$SlimeMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.chainsaw.MyTableModel$Processor;
import recovered.unidentified.UnidentifiedClass1852;

public class PlayerProfileCache {
   public MinecraftServer mcServer;
   public Map<String, PlayerProfileCache$ProfileEntry> usernameToProfileEntryMap = Maps.newHashMap();
   public Gson gson;
   public LinkedList<GameProfile> gameProfiles;
   public EntitySlime$SlimeMoveHelper field_0001;
   public MyTableModel$Processor field_0002;
   public static ParameterizedType TYPE = new PlayerProfileCache$1();
   public Map<UUID, PlayerProfileCache$ProfileEntry> uuidToProfileEntryMap = Maps.newHashMap();
   public GuiOptionsRowList field_0003;
   public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
   public File usercacheFile;

   public PlayerProfileCache$ProfileEntry getByUUID(UUID var1) {
      PlayerProfileCache$ProfileEntry var2 = this.uuidToProfileEntryMap.get(var1);
      if (var2 != null) {
         GameProfile var3 = var2.getGameProfile();
         this.gameProfiles.remove(var3);
         this.gameProfiles.addFirst(var3);
      }

      return var2;
   }

   public GameProfile getProfileByUUID(UUID var1) {
      PlayerProfileCache$ProfileEntry var2 = this.uuidToProfileEntryMap.get(var1);
      return var2 == null ? null : var2.getGameProfile();
   }

   public List<PlayerProfileCache$ProfileEntry> getEntriesWithLimit(int var1) {
      ArrayList var2 = Lists.newArrayList();

      for (GameProfile var4 : Lists.newArrayList(Iterators.limit(this.gameProfiles.iterator(), var1))) {
         PlayerProfileCache$ProfileEntry var5 = this.getByUUID(var4.getId());
         if (var5 != null) {
            var2.add(var5);
         }
      }

      return var2;
   }

   public void load() {
      BufferedReader var1 = null;

      try {
         var1 = Files.newReader(this.usercacheFile, Charsets.UTF_8);
         List var2 = (List)this.gson.fromJson(var1, TYPE);
         this.usernameToProfileEntryMap.clear();
         this.uuidToProfileEntryMap.clear();
         this.gameProfiles.clear();

         for (PlayerProfileCache$ProfileEntry var4 : Lists.reverse(var2)) {
            if (var4 != null) {
               this.addEntry(var4.getGameProfile(), var4.getExpirationDate());
            }
         }
      } catch (FileNotFoundException var9) {
      } catch (JsonParseException var10) {
      } finally {
         IOUtils.closeQuietly(var1);
      }
   }

   public void addEntry(GameProfile var1, Date var2) {
      UUID var3 = var1.getId();
      if (var2 == null) {
         Calendar var4 = Calendar.getInstance();
         var4.setTime(new Date());
         var4.add(2, 1);
         var2 = var4.getTime();
      }

      String var7 = var1.getName().toLowerCase(Locale.ROOT);
      PlayerProfileCache$ProfileEntry var5 = new PlayerProfileCache$ProfileEntry(this, var1, var2, null);
      if (this.uuidToProfileEntryMap.containsKey(var3)) {
         PlayerProfileCache$ProfileEntry var6 = this.uuidToProfileEntryMap.get(var3);
         this.usernameToProfileEntryMap.remove(var6.getGameProfile().getName().toLowerCase(Locale.ROOT));
         this.gameProfiles.remove(var1);
      }

      this.usernameToProfileEntryMap.put(var1.getName().toLowerCase(Locale.ROOT), var5);
      this.uuidToProfileEntryMap.put(var3, var5);
      this.gameProfiles.addFirst(var1);
      this.save();
   }

   public String[] getUsernames() {
      ArrayList var1 = Lists.newArrayList(this.usernameToProfileEntryMap.keySet());
      return var1.toArray(new String[var1.size()]);
   }

   public GameProfile getGameProfileForUsername(String var1) {
      String var2 = var1.toLowerCase(Locale.ROOT);
      PlayerProfileCache$ProfileEntry var3 = this.usernameToProfileEntryMap.get(var2);
      if (var3 != null && new Date().getTime() >= PlayerProfileCache$ProfileEntry.access$200(var3).getTime()) {
         this.uuidToProfileEntryMap.remove(var3.getGameProfile().getId());
         this.usernameToProfileEntryMap.remove(var3.getGameProfile().getName().toLowerCase(Locale.ROOT));
         this.gameProfiles.remove(var3.getGameProfile());
         var3 = null;
      }

      if (var3 != null) {
         GameProfile var4 = var3.getGameProfile();
         this.gameProfiles.remove(var4);
         this.gameProfiles.addFirst(var4);
      } else {
         GameProfile var5 = getGameProfile(this.mcServer, var2);
         if (var5 != null) {
            this.addEntry(var5);
            var3 = this.usernameToProfileEntryMap.get(var2);
         }
      }

      this.save();
      return var3 == null ? null : var3.getGameProfile();
   }

   public static GameProfile getGameProfile(MinecraftServer var0, String var1) {
      GameProfile[] var2 = new GameProfile[1];
      UnidentifiedClass1852 var3 = new UnidentifiedClass1852(var2);
      var0.getGameProfileRepository().findProfilesByNames(new String[]{var1}, Agent.MINECRAFT, var3);
      if (!var0.isServerInOnlineMode() && var2[0] == null) {
         UUID var4 = EntityPlayer.getUUID(new GameProfile((UUID)null, var1));
         GameProfile var5 = new GameProfile(var4, var1);
         var3.onProfileLookupSucceeded(var5);
      }

      return var2[0];
   }

   public void addEntry(GameProfile var1) {
      this.addEntry(var1, (Date)null);
   }

   public PlayerProfileCache(MinecraftServer var1, File var2) {
      this.gameProfiles = Lists.newLinkedList();
      this.mcServer = var1;
      this.usercacheFile = var2;
      GsonBuilder var3 = new GsonBuilder();
      var3.registerTypeHierarchyAdapter(PlayerProfileCache$ProfileEntry.class, new PlayerProfileCache$Serializer(this, null));
      this.gson = var3.create();
      this.load();
   }

   public void save() {
      String var1 = this.gson.toJson(this.getEntriesWithLimit(1000));
      BufferedWriter var2 = null;

      try {
         var2 = Files.newWriter(this.usercacheFile, Charsets.UTF_8);
         var2.write(var1);
         return;
      } catch (FileNotFoundException var8) {
         return;
      } catch (IOException var9) {
      } finally {
         IOUtils.closeQuietly(var2);
      }
   }
}
