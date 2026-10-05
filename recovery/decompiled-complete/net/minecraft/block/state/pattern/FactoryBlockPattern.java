package net.minecraft.block.state.pattern;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.state.BlockWorldState;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class FactoryBlockPattern {
   public Map<Character, Predicate<BlockWorldState>> symbolMap;
   public int field_0004;
   public static Joiner COMMA_JOIN = Joiner.on(",");
   public int field_0003;
   public List<String[]> depth = Lists.newArrayList();

   public FactoryBlockPattern where(char var1, Predicate<BlockWorldState> var2) {
      this.symbolMap.put(var1, var2);
      return this;
   }

   public FactoryBlockPattern aisle(String... var1) {
      if (!ArrayUtils.isEmpty(var1) && !StringUtils.isEmpty(var1[0])) {
         if (this.depth.isEmpty()) {
            this.field_0003 = var1.length;
            this.field_0004 = var1[0].length();
         }

         if (var1.length != this.field_0003) {
            throw new IllegalArgumentException("Expected aisle with height of " + this.field_0003 + ", but was given one with a height of " + var1.length + ")");
         } else {
            for (String var5 : var1) {
               if (var5.length() != this.field_0004) {
                  throw new IllegalArgumentException(
                     "Not all rows in the given aisle are the correct width (expected " + this.field_0004 + ", found one with " + var5.length() + ")"
                  );
               }

               for (char var9 : var5.toCharArray()) {
                  if (!this.symbolMap.containsKey(var9)) {
                     this.symbolMap.put(var9, (Predicate<BlockWorldState>)null);
                  }
               }
            }

            this.depth.add(var1);
            return this;
         }
      } else {
         throw new IllegalArgumentException("Empty pattern for aisle");
      }
   }

   public BlockPattern build() {
      return new BlockPattern(this.makePredicateArray());
   }

   public FactoryBlockPattern() {
      this.symbolMap = Maps.newHashMap();
      this.symbolMap.put(' ', Predicates.alwaysTrue());
   }

   public Predicate<BlockWorldState>[][][] makePredicateArray() {
      this.checkMissingPredicates();
      Predicate[][][] var1 = (Predicate[][][])Array.newInstance(Predicate.class, this.depth.size(), this.field_0003, this.field_0004);

      for (int var2 = 0; var2 < this.depth.size(); var2++) {
         for (int var3 = 0; var3 < this.field_0003; var3++) {
            for (int var4 = 0; var4 < this.field_0004; var4++) {
               var1[var2][var3][var4] = this.symbolMap.get(this.depth.get(var2)[var3].charAt(var4));
            }
         }
      }

      return var1;
   }

   public static FactoryBlockPattern start() {
      return new FactoryBlockPattern();
   }

   public void checkMissingPredicates() {
      ArrayList var1 = Lists.newArrayList();

      for (Entry var3 : this.symbolMap.entrySet()) {
         if (var3.getValue() == null) {
            var1.add(var3.getKey());
         }
      }

      if (!var1.isEmpty()) {
         throw new IllegalStateException("Predicates for character(s) " + COMMA_JOIN.join(var1) + " are missing");
      }
   }
}
