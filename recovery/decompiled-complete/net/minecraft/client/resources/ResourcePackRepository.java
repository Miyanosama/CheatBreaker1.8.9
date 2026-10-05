package net.minecraft.client.resources;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.HttpUtil;
import net.minecraft.world.gen.feature.WorldGenIcePath;
import net.optifine.shaders.Shaders$1;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourcePackRepository {
   public IResourcePack resourcePackInstance;
   public File dirResourcepacks;
   public ReentrantLock lock = new ReentrantLock();
   public IMetadataSerializer rprMetadataSerializer;
   public List<ResourcePackRepository$Entry> repositoryEntries;
   public List<ResourcePackRepository$Entry> repositoryEntriesAll = Lists.newArrayList();
   public File dirServerResourcepacks;
   public static Logger logger = LogManager.getLogger();
   public IResourcePack rprDefaultResourcePack;
   public Shaders$1 field_0012;
   public WorldGenIcePath field_0000;
   public ListenableFuture<Object> downloadingPacks;
   public static FileFilter resourcePackFilter = new ResourcePackRepository$1();

   public List<File> getResourcePackFiles() {
      return this.dirResourcepacks.isDirectory() ? Arrays.asList(this.dirResourcepacks.listFiles(resourcePackFilter)) : Collections.emptyList();
   }

   public ListenableFuture<Object> downloadResourcePack(String var1, String var2) {
      String var3;
      if (var2.matches("^[a-f0-9]{40}$")) {
         var3 = var2;
      } else {
         var3 = "legacy";
      }

      File var4 = new File(this.dirServerResourcepacks, var3);
      this.lock.lock();

      try {
         this.clearResourcePack();
         if (var4.exists() && var2.length() == 40) {
            try {
               String var5 = Hashing.sha1().hashBytes(Files.toByteArray(var4)).toString();
               if (var5.equals(var2)) {
                  return this.setResourcePackInstance(var4);
               }

               logger.warn("File " + var4 + " had wrong hash (expected " + var2 + ", found " + var5 + "). Deleting it.");
               FileUtils.deleteQuietly(var4);
            } catch (IOException var15) {
               logger.warn("File " + var4 + " couldn't be hashed. Deleting it.", var15);
               FileUtils.deleteQuietly(var4);
            }
         }

         this.deleteOldServerResourcesPacks();
         GuiScreenWorking var17 = new GuiScreenWorking();
         Map var6 = Minecraft.getSessionInfo();
         Minecraft var7 = Minecraft.getMinecraft();
         Futures.getUnchecked(var7.addScheduledTask(new ResourcePackRepository$2(this, var7, var17)));
         SettableFuture var8 = SettableFuture.create();
         this.downloadingPacks = HttpUtil.downloadResourcePack(var4, var1, var6, 52428800, var17, var7.getProxy());
         Futures.addCallback(this.downloadingPacks, new ResourcePackRepository$3(this, var4, var8));
         return this.downloadingPacks;
      } finally {
         this.lock.unlock();
      }
   }

   public void fixDirResourcepacks() {
      if (this.dirResourcepacks.exists()) {
         if (!this.dirResourcepacks.isDirectory() && (!this.dirResourcepacks.delete() || !this.dirResourcepacks.mkdirs())) {
            logger.warn("Unable to recreate resourcepack folder, it exists but is not a directory: " + this.dirResourcepacks);
         }
      } else if (!this.dirResourcepacks.mkdirs()) {
         logger.warn("Unable to create resourcepack folder: " + this.dirResourcepacks);
      }
   }

   public ResourcePackRepository(File var1, File var2, IResourcePack var3, IMetadataSerializer var4, GameSettings var5) {
      this.repositoryEntries = Lists.newArrayList();
      this.dirResourcepacks = var1;
      this.dirServerResourcepacks = var2;
      this.rprDefaultResourcePack = var3;
      this.rprMetadataSerializer = var4;
      this.fixDirResourcepacks();
      this.updateRepositoryEntriesAll();
      Iterator var6 = var5.resourcePacks.iterator();

      while (var6.hasNext()) {
         String var7 = (String)var6.next();

         for (ResourcePackRepository$Entry var9 : this.repositoryEntriesAll) {
            if (var9.getResourcePackName().equals(var7)) {
               if (var9.func_183027_f() == 1 || var5.incompatibleResourcePacks.contains(var9.getResourcePackName())) {
                  this.repositoryEntries.add(var9);
                  break;
               }

               var6.remove();
               logger.warn("Removed selected resource pack {} because it's no longer compatible", new Object[]{var9.getResourcePackName()});
            }
         }
      }
   }

   public ListenableFuture<Object> setResourcePackInstance(File var1) {
      this.resourcePackInstance = new FileResourcePack(var1);
      return Minecraft.getMinecraft().scheduleResourcesRefresh();
   }

   public List<ResourcePackRepository$Entry> getRepositoryEntriesAll() {
      return ImmutableList.copyOf(this.repositoryEntriesAll);
   }

   public void deleteOldServerResourcesPacks() {
      ArrayList var1 = Lists.newArrayList(FileUtils.listFiles(this.dirServerResourcepacks, TrueFileFilter.TRUE, (IOFileFilter)null));
      Collections.sort(var1, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
      int var2 = 0;

      for (File var4 : var1) {
         if (var2++ >= 10) {
            logger.info("Deleting old server resource pack " + var4.getName());
            FileUtils.deleteQuietly(var4);
         }
      }
   }

   public IResourcePack getResourcePackInstance() {
      return this.resourcePackInstance;
   }

   public void updateRepositoryEntriesAll() {
      ArrayList var1 = Lists.newArrayList();

      for (File var3 : this.getResourcePackFiles()) {
         ResourcePackRepository$Entry var4 = new ResourcePackRepository$Entry(this, var3, null);
         if (!this.repositoryEntriesAll.contains(var4)) {
            try {
               var4.updateResourcePack();
               var1.add(var4);
            } catch (Exception var6) {
               var1.remove(var4);
            }
         } else {
            int var5 = this.repositoryEntriesAll.indexOf(var4);
            if (var5 > -1 && var5 < this.repositoryEntriesAll.size()) {
               var1.add(this.repositoryEntriesAll.get(var5));
            }
         }
      }

      this.repositoryEntriesAll.removeAll(var1);

      for (ResourcePackRepository$Entry var8 : this.repositoryEntriesAll) {
         var8.closeResourcePack();
      }

      this.repositoryEntriesAll = var1;
   }

   public void clearResourcePack() {
      this.lock.lock();

      try {
         if (this.downloadingPacks != null) {
            this.downloadingPacks.cancel(true);
         }

         this.downloadingPacks = null;
         if (this.resourcePackInstance != null) {
            this.resourcePackInstance = null;
            Minecraft.getMinecraft().scheduleResourcesRefresh();
         }
      } finally {
         this.lock.unlock();
      }
   }

   public void setRepositories(List<ResourcePackRepository$Entry> var1) {
      this.repositoryEntries.clear();
      this.repositoryEntries.addAll(var1);
   }

   public File getDirResourcepacks() {
      return this.dirResourcepacks;
   }

   public List<ResourcePackRepository$Entry> getRepositoryEntries() {
      return ImmutableList.copyOf(this.repositoryEntries);
   }
}
