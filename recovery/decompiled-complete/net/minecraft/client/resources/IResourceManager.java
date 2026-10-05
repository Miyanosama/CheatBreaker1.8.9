package net.minecraft.client.resources;

import java.util.List;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

public interface IResourceManager {
   IResource getResource(ResourceLocation var1);

   List<IResource> getAllResources(ResourceLocation var1);

   Set<String> getResourceDomains();
}
