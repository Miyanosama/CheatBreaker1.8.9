package org.apache.log4j.pattern;

import java.util.List;
import net.minecraft.network.play.server.S22PacketMultiBlockChange$BlockUpdateData;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.util.NativeMemory;

public class NameAbbreviator$PatternAbbreviator extends NameAbbreviator {
   public NameAbbreviator$PatternAbbreviatorFragment[] fragments;
   public S22PacketMultiBlockChange$BlockUpdateData field_0001;
   public NativeMemory field_0000;
   public CustomEntityModels field_0003;

   public void abbreviate(int var1, StringBuffer var2) {
      int var3 = var1;

      for (int var4 = 0; var4 < this.fragments.length - 1 && var3 < var2.length(); var4++) {
         var3 = this.fragments[var4].abbreviate(var2, var3);
      }

      NameAbbreviator$PatternAbbreviatorFragment var5 = this.fragments[this.fragments.length - 1];

      while (var3 < var2.length() && var3 >= 0) {
         var3 = var5.abbreviate(var2, var3);
      }
   }

   public NameAbbreviator$PatternAbbreviator(List var1) {
      if (var1.size() == 0) {
         throw new IllegalArgumentException("fragments must have at least one element");
      } else {
         this.fragments = new NameAbbreviator$PatternAbbreviatorFragment[var1.size()];
         var1.toArray(this.fragments);
      }
   }
}
