package net.minecraft.server.management;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.netty.util.internal.MpscLinkedQueueTailRef;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import net.minecraft.client.renderer.entity.RenderBat;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.AsyncAppender$DiscardSummary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserList<K, V extends UserListEntry<K>> {
   public MpscLinkedQueueTailRef field_0004;
   public Gson gson;
   public AsyncAppender$DiscardSummary field_0001;
   public boolean lanServer;
   public Map<String, V> values = Maps.newHashMap();
   public static ParameterizedType saveFileFormat = new UserList$1();
   public File saveFile;
   public RenderBat field_0008;
   public static Logger logger = LogManager.getLogger();

   public UserList(File var1) {
      this.lanServer = true;
      this.saveFile = var1;
      GsonBuilder var2 = new GsonBuilder().setPrettyPrinting();
      var2.registerTypeHierarchyAdapter(UserListEntry.class, new UserList$Serializer(this, null));
      this.gson = var2.create();
   }

   public Map<String, V> getValues() {
      return this.values;
   }

   public UserListEntry<K> createEntry(JsonObject var1) {
      return new UserListEntry<>(null, var1);
   }

   public boolean isLanServer() {
      return this.lanServer;
   }

   public V getEntry(K var1) {
      this.removeExpired();
      return this.values.get(this.getObjectKey((K)var1));
   }

   public String[] getKeys() {
      return this.values.keySet().toArray(new String[this.values.size()]);
   }

   public void writeChanges() {
      Collection var1 = this.values.values();
      String var2 = this.gson.toJson(var1);
      BufferedWriter var3 = null;

      try {
         var3 = Files.newWriter(this.saveFile, Charsets.UTF_8);
         var3.write(var2);
      } finally {
         IOUtils.closeQuietly(var3);
      }
   }

   public void setLanServer(boolean var1) {
      this.lanServer = var1;
   }

   public void removeExpired() {
      ArrayList var1 = Lists.newArrayList();

      for (UserListEntry var3 : this.values.values()) {
         if (var3.hasBanExpired()) {
            var1.add(var3.getValue());
         }
      }

      for (Object var5 : var1) {
         this.values.remove(var5);
      }
   }

   public String getObjectKey(K var1) {
      return var1.toString();
   }

   public void addEntry(V var1) {
      this.values.put(this.getObjectKey((K)var1.getValue()), (V)var1);

      try {
         this.writeChanges();
      } catch (IOException var3) {
         logger.warn("Could not save the list after adding a user.", var3);
      }
   }

   public boolean hasEntry(K var1) {
      return this.values.containsKey(this.getObjectKey((K)var1));
   }

   public void removeEntry(K var1) {
      this.values.remove(this.getObjectKey((K)var1));

      try {
         this.writeChanges();
      } catch (IOException var3) {
         logger.warn("Could not save the list after removing a user.", var3);
      }
   }
}
