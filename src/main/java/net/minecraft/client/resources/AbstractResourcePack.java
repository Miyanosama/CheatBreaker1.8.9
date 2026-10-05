package net.minecraft.client.resources;

import com.google.common.base.Charsets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractResourcePack implements IResourcePack {
   public File a;
   public static final int recoveredField2293 = 64;
   public static Logger resourceLog = LogManager.getLogger();

   public void c(String var1) {
      resourceLog.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", var1, this.a);
   }

   @Override
   public boolean resourceExists(ResourceLocation var1) {
      return this.hasResourceName(locationToName(var1));
   }

   public static String locationToName(ResourceLocation var0) {
      return String.format("%s/%s/%s", "assets", var0.getResourceDomain(), var0.getResourcePath());
   }

   public BufferedImage method_27593(BufferedImage var1) {
      if (var1 == null) {
         return null;
      } else if (var1.getWidth() <= 64 && var1.getHeight() <= 64) {
         resourceLog.info("[Icon Scaler] Retaining pack icon scale at " + var1.getWidth());
         return var1;
      } else {
         resourceLog.info("[Icon Scaler] Scaling resource pack icon from " + var1.getWidth() + " to " + 64);
         BufferedImage var2 = new BufferedImage(64, 64, 2);
         Graphics var3 = var2.getGraphics();
         var3.drawImage(var1, 0, 0, 64, 64, null);
         var3.dispose();
         return var2;
      }
   }

   public AbstractResourcePack(File var1) {
      this.a = var1;
   }

   public abstract InputStream getInputStreamByName(String var1) throws java.io.IOException ;

   public abstract boolean hasResourceName(String var1);

   @Override
   public InputStream getInputStream(ResourceLocation var1) throws java.io.IOException {
      return this.getInputStreamByName(locationToName(var1));
   }

   @Override
   public <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer var1, String var2) throws java.io.IOException {
      return readMetadata(var1, this.getInputStreamByName("pack.mcmeta"), var2);
   }

   public static String getRelativeName(File var0, File var1) {
      return var0.toURI().relativize(var1.toURI()).getPath();
   }

   public static <T extends IMetadataSection> T readMetadata(IMetadataSerializer var0, InputStream var1, String var2) {
      Object var3 = null;
      BufferedReader var4 = null;

      try {
         var4 = new BufferedReader(new InputStreamReader(var1, Charsets.UTF_8));
         var3 = new JsonParser().parse(var4).getAsJsonObject();
      } catch (RuntimeException var9) {
         throw new JsonParseException(var9);
      } finally {
         IOUtils.closeQuietly((Reader)var4);
      }

      return var0.parseMetadataSection(var2, (JsonObject)var3);
   }

   @Override
   public BufferedImage getPackImage() throws java.io.IOException {
      try {
         return this.method_27593(TextureUtil.readBufferedImage(this.getInputStreamByName("pack.png")));
      } catch (IOException var2) {
         return Config.getDefaultResourcePack().getPackImage();
      }
   }

   @Override
   public String getPackName() {
      return this.a.getName();
   }
}
