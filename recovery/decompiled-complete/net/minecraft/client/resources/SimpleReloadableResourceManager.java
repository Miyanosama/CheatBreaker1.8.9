package net.minecraft.client.resources;

import com.cheatbreaker.client.CheatBreaker;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.MobAppearance;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import recovered.unidentified.UnidentifiedClass0089;

public class SimpleReloadableResourceManager implements IReloadableResourceManager {
   public MobAppearance field_0003;
   public UnidentifiedClass0089 field_0006;
   public static Joiner joinerResourcePacks = Joiner.on(", ");
   public Set<String> setResourceDomains;
   public List<IResourceManagerReloadListener> reloadListeners;
   public Map<String, FallbackResourceManager> domainResourceManagers = Maps.newHashMap();
   public static Logger logger = LogManager.getLogger();
   public IMetadataSerializer rmMetadataSerializer;

   public void notifyReloadListeners() {
      for (IResourceManagerReloadListener var2 : this.reloadListeners) {
         var2.onResourceManagerReload(this);
      }
   }

   public void reloadResourcePack(IResourcePack var1) {
      for (String var3 : var1.getResourceDomains()) {
         this.setResourceDomains.add(var3);
         FallbackResourceManager var4 = this.domainResourceManagers.get(var3);
         if (var4 == null) {
            var4 = new FallbackResourceManager(this.rmMetadataSerializer);
            this.domainResourceManagers.put(var3, var4);
         }

         var4.addResourcePack(var1);
      }
   }

   @Override
   public void reloadResources(List<IResourcePack> var1) {
      this.clearResources();
      logger.info("Reloading ResourceManager: " + joinerResourcePacks.join(Iterables.transform(var1, new SimpleReloadableResourceManager$1(this))));

      for (IResourcePack var3 : var1) {
         this.reloadResourcePack(var3);
      }

      method_01482();
      this.notifyReloadListeners();
   }

   @Override
   public Set<String> getResourceDomains() {
      return this.setResourceDomains;
   }

   public static IResourcePack method_01476() {
      IResourcePack[] var0 = Config.getResourcePacks();
      if (var0.length <= 0) {
         return Config.getDefaultResourcePack();
      } else {
         IResourcePack[] var1 = new IResourcePack[var0.length];

         for (int var2 = 0; var2 < var0.length; var2++) {
            var1[var2] = var0[var2];
         }

         String var3 = Config.arrayToString((Object[])var1);
         return var1[var1.length - 1];
      }
   }

   @Override
   public List<IResource> getAllResources(ResourceLocation var1) {
      IResourceManager var2 = this.domainResourceManagers.get(var1.getResourceDomain());
      if (var2 != null) {
         return var2.getAllResources(var1);
      } else {
         throw new FileNotFoundException(var1.toString());
      }
   }

   @Override
   public IResource getResource(ResourceLocation var1) {
      IResourceManager var2 = this.domainResourceManagers.get(var1.getResourceDomain());
      if (var2 != null) {
         return var2.getResource(var1);
      } else {
         throw new FileNotFoundException(var1.toString());
      }
   }

   @Override
   public void registerReloadListener(IResourceManagerReloadListener var1) {
      this.reloadListeners.add(var1);
      var1.onResourceManagerReload(this);
   }

   public static void method_01482() {
      if (CheatBreaker.getInstance() != null) {
         DynamicTexture var0 = null;

         try {
            var0 = new DynamicTexture(method_01476().getPackImage());
         } catch (IOException var2) {
            var2.printStackTrace();
         }

         CheatBreaker.field_0058 = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("texturepackicon", var0);
      }
   }

   public void clearResources() {
      this.domainResourceManagers.clear();
      this.setResourceDomains.clear();
   }

   public SimpleReloadableResourceManager(IMetadataSerializer var1) {
      this.reloadListeners = Lists.newArrayList();
      this.setResourceDomains = Sets.newLinkedHashSet();
      this.rmMetadataSerializer = var1;
   }
}
