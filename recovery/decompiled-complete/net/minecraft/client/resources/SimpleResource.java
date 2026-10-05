package net.minecraft.client.resources;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import net.minecraft.client.renderer.EntityRenderer$3;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.tileentity.TileEntityBanner$EnumBannerPattern;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.chainsaw.MyTableModel$1;
import org.apache.log4j.lf5.util.Resource;

public class SimpleResource implements IResource {
   public TileEntityBanner$EnumBannerPattern field_0005;
   public ResourceLocation srResourceLocation;
   public InputStream resourceInputStream;
   public IMetadataSerializer srMetadataSerializer;
   public EntityRenderer$3 field_0001;
   public InputStream mcmetaInputStream;
   public boolean mcmetaJsonChecked;
   public Map<String, IMetadataSection> mapMetadataSections = Maps.newHashMap();
   public Resource field_0003;
   public String resourcePackName;
   public MyTableModel$1 field_0000;
   public JsonObject mcmetaJson;

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof SimpleResource)) {
         return false;
      } else {
         SimpleResource var2 = (SimpleResource)var1;
         if (this.srResourceLocation != null) {
            if (!this.srResourceLocation.equals(var2.srResourceLocation)) {
               return false;
            }
         } else if (var2.srResourceLocation != null) {
            return false;
         }

         if (this.resourcePackName != null) {
            if (!this.resourcePackName.equals(var2.resourcePackName)) {
               return false;
            }
         } else if (var2.resourcePackName != null) {
            return false;
         }

         return true;
      }
   }

   public SimpleResource(String var1, ResourceLocation var2, InputStream var3, InputStream var4, IMetadataSerializer var5) {
      this.resourcePackName = var1;
      this.srResourceLocation = var2;
      this.resourceInputStream = var3;
      this.mcmetaInputStream = var4;
      this.srMetadataSerializer = var5;
   }

   @Override
   public InputStream getInputStream() {
      return this.resourceInputStream;
   }

   @Override
   public <T extends IMetadataSection> T getMetadata(String var1) {
      if (!this.hasMetadata()) {
         return null;
      } else {
         if (this.mcmetaJson == null && !this.mcmetaJsonChecked) {
            this.mcmetaJsonChecked = true;
            BufferedReader var2 = null;

            try {
               var2 = new BufferedReader(new InputStreamReader(this.mcmetaInputStream));
               this.mcmetaJson = new JsonParser().parse(var2).getAsJsonObject();
            } finally {
               IOUtils.closeQuietly(var2);
            }
         }

         IMetadataSection var6 = this.mapMetadataSections.get(var1);
         if (var6 == null) {
            var6 = this.srMetadataSerializer.parseMetadataSection(var1, this.mcmetaJson);
         }

         return (T)var6;
      }
   }

   @Override
   public String getResourcePackName() {
      return this.resourcePackName;
   }

   @Override
   public ResourceLocation getResourceLocation() {
      return this.srResourceLocation;
   }

   @Override
   public boolean hasMetadata() {
      return this.mcmetaInputStream != null;
   }

   @Override
   public int hashCode() {
      int var1 = this.resourcePackName != null ? this.resourcePackName.hashCode() : 0;
      return 31 * var1 + (this.srResourceLocation != null ? this.srResourceLocation.hashCode() : 0);
   }
}
