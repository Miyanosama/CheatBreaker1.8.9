package org.apache.log4j.pattern;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ValuesView;
import java.util.ArrayList;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.client.stream.ChatController$ChatState;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;

public abstract class NameAbbreviator {
   public ConcurrentHashMapV8$ValuesView field_0003;
   public static NameAbbreviator DEFAULT = new NameAbbreviator$NOPAbbreviator();
   public ChatController$ChatState field_0002;
   public MapGenScatteredFeature field_0004;
   public TileEntityDispenser field_0000;
   public TileEntityMobSpawnerRenderer field_0001;

   public static NameAbbreviator getAbbreviator(String var0) {
      if (var0.length() > 0) {
         String var1 = var0.trim();
         if (var1.length() == 0) {
            return DEFAULT;
         } else {
            int var2 = 0;
            if (var1.length() > 0) {
               if (var1.charAt(0) == '-') {
                  var2++;
               }

               while (var2 < var1.length() && var1.charAt(var2) >= '0' && var1.charAt(var2) <= '9') {
                  var2++;
               }
            }

            if (var2 == var1.length()) {
               int var8 = Integer.parseInt(var1);
               return (NameAbbreviator)(var8 >= 0 ? new NameAbbreviator$MaxElementAbbreviator(var8) : new NameAbbreviator$DropElementAbbreviator(-var8));
            } else {
               ArrayList var3 = new ArrayList(5);

               for (int var9 = 0; var9 < var1.length() && var9 >= 0; var9++) {
                  int var7 = var9;
                  int var5;
                  if (var1.charAt(var9) == '*') {
                     var5 = Integer.MAX_VALUE;
                     var7 = var9 + 1;
                  } else if (var1.charAt(var9) >= '0' && var1.charAt(var9) <= '9') {
                     var5 = var1.charAt(var9) - '0';
                     var7 = var9 + 1;
                  } else {
                     var5 = 0;
                  }

                  char var4 = 0;
                  if (var7 < var1.length()) {
                     var4 = var1.charAt(var7);
                     if (var4 == '.') {
                        var4 = 0;
                     }
                  }

                  var3.add(new NameAbbreviator$PatternAbbreviatorFragment(var5, var4));
                  var9 = var1.indexOf(".", var9);
                  if (var9 == -1) {
                     break;
                  }
               }

               return new NameAbbreviator$PatternAbbreviator(var3);
            }
         }
      } else {
         return DEFAULT;
      }
   }

   public abstract void abbreviate(int var1, StringBuffer var2);

   public static NameAbbreviator getDefaultAbbreviator() {
      return DEFAULT;
   }
}
