package net.minecraft.client.resources;

import com.google.common.collect.ImmutableSet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import net.optifine.reflect.ReflectorForge;

public class DefaultResourcePack implements IResourcePack {
   public static Set<String> defaultResourceDomains = ImmutableSet.of("minecraft", "realms");
   public Map<String, File> mapAssets;

   @Override
   public Set<String> getResourceDomains() {
      return defaultResourceDomains;
   }

   public InputStream getInputStreamAssets(ResourceLocation var1) throws java.io.IOException, java.io.FileNotFoundException {
      File var2 = this.mapAssets.get(var1.toString());
      return var2 != null && var2.isFile() ? new FileInputStream(var2) : null;
   }

   @Override
   public BufferedImage getPackImage() throws java.io.IOException {
      return TextureUtil.readBufferedImage(DefaultResourcePack.class.getResourceAsStream("/" + new ResourceLocation("pack.png").getResourcePath()));
   }

   @Override
   public <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer var1, String var2) throws java.io.IOException {
      try {
         FileInputStream var3 = new FileInputStream(this.mapAssets.get("pack.mcmeta"));
         return AbstractResourcePack.readMetadata(var1, var3, var2);
      } catch (RuntimeException var4) {
         return null;
      } catch (FileNotFoundException var5) {
         return null;
      }
   }

   @Override
   public boolean resourceExists(ResourceLocation var1) {
      return this.method_20550(var1) != null || this.mapAssets.containsKey(var1.toString());
   }

   public DefaultResourcePack(Map<String, File> var1) {
      this.mapAssets = var1;
   }

   @Override
   public String getPackName() {
      return "Default";
   }

   @Override
   public InputStream getInputStream(ResourceLocation var1) throws java.io.IOException {
      InputStream var2 = this.method_20550(var1);
      if (var2 != null) {
         return var2;
      } else {
         InputStream var3 = this.getInputStreamAssets(var1);
         if (var3 != null) {
            return var3;
         } else {
            throw new FileNotFoundException(var1.getResourcePath());
         }
      }
   }

   public InputStream method_20550(ResourceLocation var1) {
      String var2 = "/assets/" + var1.getResourceDomain() + "/" + var1.getResourcePath();
      InputStream var3 = ReflectorForge.getOptiFineResourceStream(var2);
      return var3 != null ? var3 : DefaultResourcePack.class.getResourceAsStream(var2);
   }
}
