package net.optifine.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.optifine.render.VboRange;

public class LinkedListTest {
   public static void main(String[] var0) throws java.lang.Exception {
      LinkedList var1 = new LinkedList();
      ArrayList var2 = new ArrayList();
      ArrayList var3 = new ArrayList();
      Random var4 = new Random();
      byte var5 = 100;

      for (int var6 = 0; var6 < var5; var6++) {
         VboRange var7 = new VboRange();
         var7.setPosition(var6);
         var2.add(var7);
      }

      for (int var11 = 0; var11 < 100000; var11++) {
         checkLists(var2, var3, var5);
         checkLinkedList(var1, var3.size());
         if (var11 % 5 == 0) {
            dbgLinkedList(var1);
         }

         if (var4.nextBoolean()) {
            if (!var2.isEmpty()) {
               VboRange var12 = (VboRange)var2.get(var4.nextInt(var2.size()));
               LinkedList.Node var8 = var12.getNode();
               if (var4.nextBoolean()) {
                  var1.addFirst(var8);
                  dbg("Add first: " + var12.getPosition());
               } else if (var4.nextBoolean()) {
                  var1.addLast(var8);
                  dbg("Add last: " + var12.getPosition());
               } else {
                  if (var3.isEmpty()) {
                     continue;
                  }

                  VboRange var9 = (VboRange)var3.get(var4.nextInt(var3.size()));
                  LinkedList.Node var10 = var9.getNode();
                  var1.addAfter(var10, var8);
                  dbg("Add after: " + var9.getPosition() + ", " + var12.getPosition());
               }

               var2.remove(var12);
               var3.add(var12);
            }
         } else if (!var3.isEmpty()) {
            VboRange var13 = (VboRange)var3.get(var4.nextInt(var3.size()));
            LinkedList.Node var14 = var13.getNode();
            var1.remove(var14);
            dbg("Remove: " + var13.getPosition());
            var3.remove(var13);
            var2.add(var13);
         }
      }
   }

   public static void dbgLinkedList(LinkedList<VboRange> var0) {
      StringBuffer var1 = new StringBuffer();
      var0.iterator().forEachRemaining(var1x -> {
         VboRange var2 = (VboRange)var1x.getItem();
         if (var1.length() > 0) {
            var1.append(", ");
         }

         var1.append(var2.getPosition());
      });
      dbg("List: " + var1);
   }

   public static void dbg(String var0) {
      System.out.println(var0);
   }

   public static void checkLists(List<VboRange> var0, List<VboRange> var1, int var2) {
      int var3 = var0.size() + var1.size();
      if (var3 != var2) {
         throw new RuntimeException("Total size: " + var3);
      }
   }

   public static void checkLinkedList(LinkedList<VboRange> var0, int var1) {
      if (var0.getSize() != var1) {
         throw new RuntimeException("Wrong size, linked: " + var0.getSize() + ", used: " + var1);
      } else {
         int var2 = 0;

         for (LinkedList.Node var3 = var0.getFirst(); var3 != null; var3 = var3.getNext()) {
            var2++;
         }

         if (var0.getSize() != var2) {
            throw new RuntimeException("Wrong count, linked: " + var0.getSize() + ", count: " + var2);
         } else {
            int var5 = 0;

            for (LinkedList.Node var4 = var0.getLast(); var4 != null; var4 = var4.getPrev()) {
               var5++;
            }

            if (var0.getSize() != var5) {
               throw new RuntimeException("Wrong count back, linked: " + var0.getSize() + ", count: " + var5);
            }
         }
      }
   }
}
