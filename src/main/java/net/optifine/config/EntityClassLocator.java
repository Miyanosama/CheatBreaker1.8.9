package net.optifine.config;

import net.minecraft.util.ResourceLocation;
import net.optifine.util.EntityUtils;

public class EntityClassLocator implements IObjectLocator {
   @Override
   public Object getObject(ResourceLocation var1) {
      return EntityUtils.getEntityClassByName(var1.getResourcePath());
   }
}
