package net.optifine.config;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemLocator implements IObjectLocator {
   @Override
   public Object getObject(ResourceLocation var1) {
      return Item.getByNameOrId(var1.toString());
   }
}
