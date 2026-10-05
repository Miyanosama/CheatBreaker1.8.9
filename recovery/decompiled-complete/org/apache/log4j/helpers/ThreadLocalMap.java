package org.apache.log4j.helpers;

import java.util.Hashtable;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.nbt.JsonToNBT$List;

public class ThreadLocalMap extends InheritableThreadLocal {
   public JsonToNBT$List field_0000;
   public EntityIronGolem field_0001;

   public Object childValue(Object var1) {
      Hashtable var2 = (Hashtable)var1;
      return var2 != null ? var2.clone() : null;
   }
}
