package org.apache.log4j;

import net.minecraft.block.BlockDoublePlant$EnumBlockHalf;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import org.apache.log4j.pattern.PropertiesPatternConverter;

public class CategoryKey {
   public DefaultThrowableRenderer field_0003;
   public MapGenNetherBridge field_0005;
   public PropertiesPatternConverter field_0002;
   public int hashCache;
   public String name;
   public static Class class$org$apache$log4j$CategoryKey;
   public BlockDoublePlant$EnumBlockHalf field_0006;

   public int hashCode() {
      return this.hashCache;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return var1 != null
               && (
                     class$org$apache$log4j$CategoryKey == null
                        ? (class$org$apache$log4j$CategoryKey = class$("org.apache.log4j.CategoryKey"))
                        : class$org$apache$log4j$CategoryKey
                  )
                  == var1.getClass()
            ? this.name.equals(((CategoryKey)var1).name)
            : false;
      }
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public CategoryKey(String var1) {
      this.name = var1;
      this.hashCache = var1.hashCode();
   }
}
