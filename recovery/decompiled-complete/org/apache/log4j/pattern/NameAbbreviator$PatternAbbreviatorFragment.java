package org.apache.log4j.pattern;

import net.minecraft.block.BlockWall$EnumType;
import net.minecraft.world.gen.ChunkProviderDebug;

public class NameAbbreviator$PatternAbbreviatorFragment {
   public int charCount;
   public char ellipsis;
   public BlockWall$EnumType field_0000;
   public ChunkProviderDebug field_0002;

   public NameAbbreviator$PatternAbbreviatorFragment(int var1, char var2) {
      this.charCount = var1;
      this.ellipsis = var2;
   }

   public int abbreviate(StringBuffer var1, int var2) {
      int var3 = var1.toString().indexOf(".", var2);
      if (var3 != -1) {
         if (var3 - var2 > this.charCount) {
            var1.delete(var2 + this.charCount, var3);
            var3 = var2 + this.charCount;
            if (this.ellipsis != 0) {
               var1.insert(var3, this.ellipsis);
               var3++;
            }
         }

         var3++;
      }

      return var3;
   }
}
