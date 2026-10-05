package org.apache.log4j.pattern;

import java.util.ArrayList;
import java.util.List;

public abstract class NameAbbreviator {
   public static NameAbbreviator DEFAULT = new NameAbbreviator.NOPAbbreviator();

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
               return (NameAbbreviator)(var8 >= 0 ? new NameAbbreviator.MaxElementAbbreviator(var8) : new NameAbbreviator.DropElementAbbreviator(-var8));
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

                  var3.add(new NameAbbreviator.PatternAbbreviatorFragment(var5, var4));
                  var9 = var1.indexOf(".", var9);
                  if (var9 == -1) {
                     break;
                  }
               }

               return new NameAbbreviator.PatternAbbreviator(var3);
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

   public static class DropElementAbbreviator extends NameAbbreviator {
      public int count;

      public DropElementAbbreviator(int var1) {
         this.count = var1;
      }

      public void abbreviate(int var1, StringBuffer var2) {
         int var3 = this.count;

         for (int var4 = var2.indexOf(".", var1); var4 != -1; var4 = var2.indexOf(".", var4 + 1)) {
            if (--var3 == 0) {
               var2.delete(var1, var4 + 1);
               break;
            }
         }
      }
   }

   public static class MaxElementAbbreviator extends NameAbbreviator {
      public int count;

      public MaxElementAbbreviator(int var1) {
         this.count = var1;
      }

      public void abbreviate(int var1, StringBuffer var2) {
         int var3 = var2.length() - 1;
         String var4 = var2.toString();

         for (int var5 = this.count; var5 > 0; var5--) {
            var3 = var4.lastIndexOf(".", var3 - 1);
            if (var3 == -1 || var3 < var1) {
               return;
            }
         }

         var2.delete(var1, var3 + 1);
      }
   }

   public static class NOPAbbreviator extends NameAbbreviator {
      public void abbreviate(int var1, StringBuffer var2) {
      }
   }

   public static class PatternAbbreviator extends NameAbbreviator {
      public NameAbbreviator.PatternAbbreviatorFragment[] fragments;

      public void abbreviate(int var1, StringBuffer var2) {
         int var3 = var1;

         for (int var4 = 0; var4 < this.fragments.length - 1 && var3 < var2.length(); var4++) {
            var3 = this.fragments[var4].abbreviate(var2, var3);
         }

         NameAbbreviator.PatternAbbreviatorFragment var5 = this.fragments[this.fragments.length - 1];

         while (var3 < var2.length() && var3 >= 0) {
            var3 = var5.abbreviate(var2, var3);
         }
      }

      public PatternAbbreviator(List var1) {
         if (var1.size() == 0) {
            throw new IllegalArgumentException("fragments must have at least one element");
         } else {
            this.fragments = new NameAbbreviator.PatternAbbreviatorFragment[var1.size()];
            var1.toArray(this.fragments);
         }
      }
   }

   public static class PatternAbbreviatorFragment {
      public int charCount;
      public char ellipsis;

      public PatternAbbreviatorFragment(int var1, char var2) {
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
}
