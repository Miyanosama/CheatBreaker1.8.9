package net.minecraft.client.resources;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javazoom.jl.player.advanced.PlaybackListener;
import net.minecraft.client.renderer.GlStateManager$FogState;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;

public class FileResourcePack extends AbstractResourcePack implements Closeable {
   public GlStateManager$FogState field_0000;
   public ZipFile resourcePackZipFile;
   public S32PacketConfirmTransaction field_0002;
   public static Splitter entryNameSplitter = Splitter.on('/').omitEmptyStrings().limit(3);
   public PlaybackListener field_0004;

   public ZipFile getResourcePackZipFile() {
      if (this.resourcePackZipFile == null) {
         this.resourcePackZipFile = new ZipFile(this.a);
      }

      return this.resourcePackZipFile;
   }

   @Override
   public void finalize() {
      this.close();
      super.finalize();
   }

   @Override
   public InputStream getInputStreamByName(String var1) {
      ZipFile var2 = this.getResourcePackZipFile();
      ZipEntry var3 = var2.getEntry(var1);
      if (var3 == null) {
         throw new ResourcePackFileNotFoundException(this.a, var1);
      } else {
         return var2.getInputStream(var3);
      }
   }

   public FileResourcePack(File var1) {
      super(var1);
   }

   @Override
   public void close() {
      if (this.resourcePackZipFile != null) {
         this.resourcePackZipFile.close();
         this.resourcePackZipFile = null;
      }
   }

   @Override
   public Set<String> getResourceDomains() {
      ZipFile var1;
      try {
         var1 = this.getResourcePackZipFile();
      } catch (IOException var8) {
         return Collections.emptySet();
      }

      Enumeration var2 = var1.entries();
      HashSet var3 = Sets.newHashSet();

      while (var2.hasMoreElements()) {
         ZipEntry var4 = (ZipEntry)var2.nextElement();
         String var5 = var4.getName();
         if (var5.startsWith("assets/")) {
            ArrayList var6 = Lists.newArrayList(entryNameSplitter.split(var5));
            if (var6.size() > 1) {
               String var7 = (String)var6.get(1);
               if (!var7.equals(var7.toLowerCase())) {
                  this.c(var7);
               } else {
                  var3.add(var7);
               }
            }
         }
      }

      return var3;
   }

   @Override
   public boolean hasResourceName(String var1) {
      try {
         return this.getResourcePackZipFile().getEntry(var1) != null;
      } catch (IOException var3) {
         return false;
      }
   }
}
