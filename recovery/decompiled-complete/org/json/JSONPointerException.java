package org.json;

import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.inventory.InventoryCrafting;

public class JSONPointerException extends JSONException {
   public EntityAIWatchClosest field_0002;
   public InventoryCrafting field_0000;
   public static long field_0001;

   public JSONPointerException(String var1) {
      super(var1);
   }

   public JSONPointerException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
