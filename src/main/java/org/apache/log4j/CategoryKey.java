package org.apache.log4j;

public class CategoryKey {
   public int hashCache;
   public String name;
   public static Class class$org$apache$log4j$CategoryKey;

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

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public CategoryKey(String var1) {
      this.name = var1;
      this.hashCache = var1.hashCode();
   }
}
