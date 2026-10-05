package net.minecraft.client.resources;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public interface IResourcePack {
   BufferedImage getPackImage() throws java.io.IOException ;

   InputStream getInputStream(ResourceLocation var1) throws java.io.IOException ;

   Set<String> getResourceDomains();

   String getPackName();

   <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer var1, String var2) throws java.io.IOException ;

   boolean resourceExists(ResourceLocation var1);
}
