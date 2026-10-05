package net.optifine.shaders;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.handler.codec.http.cors.CorsConfig$DateValueGenerator;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import net.minecraft.client.renderer.BlockModelRenderer$EnumNeighborInfo;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.resources.data.PackMetadataSectionSerializer;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import net.minecraft.item.EnumAction;
import org.apache.commons.io.IOUtils;

public class SimpleShaderTexture extends AbstractTexture {
   public CorsConfig$DateValueGenerator field_0002;
   public EnumAction field_0004;
   public static IMetadataSerializer METADATA_SERIALIZER = makeMetadataSerializer();
   public String texturePath;
   public BlockModelRenderer$EnumNeighborInfo field_0000;

   public SimpleShaderTexture(String var1) {
      this.texturePath = var1;
   }

   public static TextureMetadataSection loadTextureMetadataSection(String var0, TextureMetadataSection var1) {
      String var2 = var0 + ".mcmeta";
      String var3 = "texture";
      InputStream var4 = Shaders.getShaderPackResourceStream(var2);
      if (var4 != null) {
         IMetadataSerializer var5 = METADATA_SERIALIZER;
         BufferedReader var6 = new BufferedReader(new InputStreamReader(var4));

         TextureMetadataSection var10;
         try {
            JsonObject var8 = new JsonParser().parse(var6).getAsJsonObject();
            TextureMetadataSection var16 = var5.parseMetadataSection(var3, var8);
            if (var16 != null) {
               return var16;
            }

            var10 = var1;
         } catch (RuntimeException var14) {
            SMCLog.warning("Error reading metadata: " + var2);
            SMCLog.warning("" + var14.getClass().getName() + ": " + var14.getMessage());
            return var1;
         } finally {
            IOUtils.closeQuietly(var6);
            IOUtils.closeQuietly(var4);
         }

         return var10;
      } else {
         return var1;
      }
   }

   public static IMetadataSerializer makeMetadataSerializer() {
      IMetadataSerializer var0 = new IMetadataSerializer();
      var0.registerMetadataSectionType(new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
      var0.registerMetadataSectionType(new FontMetadataSectionSerializer(), FontMetadataSection.class);
      var0.registerMetadataSectionType(new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
      var0.registerMetadataSectionType(new PackMetadataSectionSerializer(), PackMetadataSection.class);
      var0.registerMetadataSectionType(new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
      return var0;
   }

   @Override
   public void loadTexture(IResourceManager var1) {
      this.deleteGlTexture();
      InputStream var2 = Shaders.getShaderPackResourceStream(this.texturePath);
      if (var2 == null) {
         throw new FileNotFoundException("Shader texture not found: " + this.texturePath);
      } else {
         try {
            BufferedImage var3 = TextureUtil.readBufferedImage(var2);
            TextureMetadataSection var4 = loadTextureMetadataSection(this.texturePath, new TextureMetadataSection(false, false, new ArrayList<>()));
            TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), var3, var4.getTextureBlur(), var4.getTextureClamp());
         } finally {
            IOUtils.closeQuietly(var2);
         }
      }
   }
}
