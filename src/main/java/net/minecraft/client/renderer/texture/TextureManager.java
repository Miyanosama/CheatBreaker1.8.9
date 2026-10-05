package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomGuis;
import net.optifine.EmissiveTextures;
import net.optifine.RandomEntities;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureManager implements IResourceManagerReloadListener, ITickable {
   public List<ITickable> listTickables;
   public IResourceManager theResourceManager;
   public ITextureObject boundTexture;
   public Map<ResourceLocation, ITextureObject> mapTextureObjects = Maps.newHashMap();
   public static Logger logger = LogManager.getLogger();
   public ResourceLocation boundTextureLocation;
   public Map<String, Integer> mapTextureCounters;

   public void bindTexture(ResourceLocation var1) {
      if (Config.isRandomEntities()) {
         var1 = RandomEntities.getTextureLocation(var1);
      }

      if (Config.isCustomGuis()) {
         var1 = CustomGuis.getTextureLocation(var1);
      }

      Object var2 = this.mapTextureObjects.get(var1);
      if (EmissiveTextures.isActive()) {
         var2 = EmissiveTextures.getEmissiveTexture((ITextureObject)var2, this.mapTextureObjects);
      }

      if (var2 == null) {
         var2 = new SimpleTexture(var1);
         this.loadTexture(var1, (ITextureObject)var2);
      }

      if (Config.isShaders()) {
         ShadersTex.bindTexture((ITextureObject)var2);
      } else {
         TextureUtil.bindTexture(((ITextureObject)var2).getGlTextureId());
      }

      this.boundTexture = (ITextureObject)var2;
      this.boundTextureLocation = var1;
   }

   @Override
   public void onResourceManagerReload(IResourceManager var1) {
      Config.dbg("*** Reloading textures ***");
      Config.log("Resource packs: " + Config.getResourcePackNames());
      Iterator var2 = this.mapTextureObjects.keySet().iterator();

      while (var2.hasNext()) {
         ResourceLocation var3 = (ResourceLocation)var2.next();
         String var4 = var3.getResourcePath();
         if (var4.startsWith("mcpatcher/") || var4.startsWith("optifine/") || EmissiveTextures.isEmissive(var3)) {
            ITextureObject var5 = this.mapTextureObjects.get(var3);
            if (var5 instanceof AbstractTexture) {
               AbstractTexture var6 = (AbstractTexture)var5;
               var6.deleteGlTexture();
            }

            var2.remove();
         }
      }

      EmissiveTextures.update();

      for (Object var8 : new HashSet<>(this.mapTextureObjects.entrySet())) {
         Entry var9 = (Entry)var8;
         this.loadTexture((ResourceLocation)var9.getKey(), (ITextureObject)var9.getValue());
      }
   }

   public TextureManager(IResourceManager var1) {
      this.listTickables = Lists.newArrayList();
      this.mapTextureCounters = Maps.newHashMap();
      this.theResourceManager = var1;
   }

   public void deleteTexture(ResourceLocation var1) {
      ITextureObject var2 = this.getTexture(var1);
      if (var2 != null) {
         this.mapTextureObjects.remove(var1);
         TextureUtil.deleteTexture(var2.getGlTextureId());
      }
   }

   public ResourceLocation getDynamicTextureLocation(String var1, DynamicTexture var2) {
      if (var1.equals("logo")) {
         var2 = Config.getMojangLogoTexture(var2);
      }

      Integer var3 = this.mapTextureCounters.get(var1);
      if (var3 == null) {
         var3 = 1;
      } else {
         var3 = var3 + 1;
      }

      this.mapTextureCounters.put(var1, var3);
      ResourceLocation var4 = new ResourceLocation(String.format("dynamic/%s_%d", var1, var3));
      this.loadTexture(var4, var2);
      return var4;
   }

   public void reloadBannerTextures() {
      for (Object var2 : new HashSet<>(this.mapTextureObjects.entrySet())) {
         Entry var3 = (Entry)var2;
         ResourceLocation var4 = (ResourceLocation)var3.getKey();
         ITextureObject var5 = (ITextureObject)var3.getValue();
         if (var5 instanceof LayeredColorMaskTexture) {
            this.loadTexture(var4, var5);
         }
      }
   }

   public ITextureObject getTexture(ResourceLocation var1) {
      return this.mapTextureObjects.get(var1);
   }

   public boolean loadTexture(ResourceLocation var1, ITextureObject var2) {
      try {
         var2.loadTexture(this.theResourceManager);
      } catch (IOException var7) {
         logger.warn("Failed to load texture: " + var1, var7);
         DynamicTexture var9 = TextureUtil.missingTexture;
         this.mapTextureObjects.put(var1, var9);
         return false;
      } catch (Throwable var8) {
         CrashReport var5 = CrashReport.makeCrashReport(var8, "Registering texture");
         CrashReportCategory var6 = var5.makeCategory("Resource location being registered");
         var6.addCrashSection("Resource location", var1);
         var6.addCrashSectionCallable("Texture object class", () -> var2.getClass().getName());
         throw new ReportedException(var5);
      }

      this.mapTextureObjects.put(var1, var2);
      return true;
   }

   public boolean loadTickableTexture(ResourceLocation var1, ITickableTextureObject var2) {
      if (this.loadTexture(var1, var2)) {
         this.listTickables.add(var2);
         return true;
      } else {
         return false;
      }
   }

   public ResourceLocation getBoundTextureLocation() {
      return this.boundTextureLocation;
   }

   @Override
   public void tick() {
      for (ITickable var2 : this.listTickables) {
         var2.tick();
      }
   }

   public ITextureObject getBoundTexture() {
      return this.boundTexture;
   }
}
