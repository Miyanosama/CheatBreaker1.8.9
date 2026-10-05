package net.minecraft.client.resources;

import com.google.common.collect.Lists;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FallbackResourceManager implements IResourceManager {
   public IMetadataSerializer frmMetadataSerializer;
   public static Logger logger = LogManager.getLogger();
   public List<IResourcePack> resourcePacks = Lists.newArrayList();

   public void addResourcePack(IResourcePack var1) {
      this.resourcePacks.add(var1);
   }

   public InputStream getInputStream(ResourceLocation var1, IResourcePack var2) throws java.io.IOException {
      InputStream var3 = var2.getInputStream(var1);
      return (InputStream)(logger.isDebugEnabled() ? new FallbackResourceManager.InputStreamLeakedResourceLogger(var3, var1, var2.getPackName()) : var3);
   }

   @Override
   public IResource getResource(ResourceLocation var1) throws java.io.IOException {
      IResourcePack var2 = null;
      ResourceLocation var3 = getLocationMcmeta(var1);

      for (int var4 = this.resourcePacks.size() - 1; var4 >= 0; var4--) {
         IResourcePack var5 = this.resourcePacks.get(var4);
         if (var2 == null && var5.resourceExists(var3)) {
            var2 = var5;
         }

         if (var5.resourceExists(var1)) {
            InputStream var6 = null;
            if (var2 != null) {
               var6 = this.getInputStream(var3, var2);
            }

            return new SimpleResource(var5.getPackName(), var1, this.getInputStream(var1, var5), var6, this.frmMetadataSerializer);
         }
      }

      throw new FileNotFoundException(var1.toString());
   }

   @Override
   public List<IResource> getAllResources(ResourceLocation var1) throws java.io.IOException {
      ArrayList var2 = Lists.newArrayList();
      ResourceLocation var3 = getLocationMcmeta(var1);

      for (IResourcePack var5 : this.resourcePacks) {
         if (var5.resourceExists(var1)) {
            InputStream var6 = var5.resourceExists(var3) ? this.getInputStream(var3, var5) : null;
            var2.add(new SimpleResource(var5.getPackName(), var1, this.getInputStream(var1, var5), var6, this.frmMetadataSerializer));
         }
      }

      if (var2.isEmpty()) {
         throw new FileNotFoundException(var1.toString());
      } else {
         return var2;
      }
   }

   public static ResourceLocation getLocationMcmeta(ResourceLocation var0) {
      return new ResourceLocation(var0.getResourceDomain(), var0.getResourcePath() + ".mcmeta");
   }

   public FallbackResourceManager(IMetadataSerializer var1) {
      this.frmMetadataSerializer = var1;
   }

   @Override
   public Set<String> getResourceDomains() {
      return null;
   }

   public static class InputStreamLeakedResourceLogger extends InputStream {
      public boolean isClosed = false;
      public InputStream inputStream;
      public String message;

      @Override
      public int read() throws java.io.IOException {
         return this.inputStream.read();
      }

      public InputStreamLeakedResourceLogger(InputStream var1, ResourceLocation var2, String var3) {
         this.inputStream = var1;
         ByteArrayOutputStream var4 = new ByteArrayOutputStream();
         new Exception().printStackTrace(new PrintStream(var4));
         this.message = "Leaked resource: '" + var2 + "' loaded from pack: '" + var3 + "'\n" + var4.toString();
      }

      @Override
      public void finalize() throws java.lang.Throwable {
         if (!this.isClosed) {
            FallbackResourceManager.logger.warn(this.message);
         }

         super.finalize();
      }

      @Override
      public void close() throws java.io.IOException {
         this.inputStream.close();
         this.isClosed = true;
      }
   }
}
