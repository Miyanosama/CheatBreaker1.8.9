package net.optifine.util;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import net.minecraft.block.state.IBlockState;
import net.minecraft.src.Config;

public class CacheObjectArray {
   public static ArrayDeque<int[]> arrays = new ArrayDeque<>();
   public static int maxCacheSize = 10;

   public static long testCloneObj(int var0, int var1) {
      long var2 = System.currentTimeMillis();
      IBlockState[] var4 = new IBlockState[var0];

      for (int var5 = 0; var5 < var1; var5++) {
         IBlockState[] var6 = (IBlockState[])var4.clone();
      }

      long var7 = System.currentTimeMillis();
      return var7 - var2;
   }

   public static long testClone(int var0, int var1) {
      long var2 = System.currentTimeMillis();
      int[] var4 = new int[var0];

      for (int var5 = 0; var5 < var1; var5++) {
         int[] var6 = (int[])var4.clone();
      }

      long var7 = System.currentTimeMillis();
      return var7 - var2;
   }

   public static synchronized int[] allocateArray(int var0) {
      int[] var1 = arrays.pollLast();
      if (var1 == null || var1.length < var0) {
         var1 = new int[var0];
      }

      return var1;
   }

   public static long testNewObjDyn(Class var0, int var1, int var2) {
      long var3 = System.currentTimeMillis();

      for (int var5 = 0; var5 < var2; var5++) {
         Object[] var6 = (Object[])Array.newInstance(var0, var1);
      }

      long var7 = System.currentTimeMillis();
      return var7 - var3;
   }

   public static long testNew(int var0, int var1) {
      long var2 = System.currentTimeMillis();

      for (int var4 = 0; var4 < var1; var4++) {
         int[] var5 = (int[])Array.newInstance(int.class, var0);
      }

      long var6 = System.currentTimeMillis();
      return var6 - var2;
   }

   public static void main(String[] var0) throws java.lang.Exception {
      short var1 = 4096;
      int var2 = 500000;
      testNew(var1, var2);
      testClone(var1, var2);
      testNewObj(var1, var2);
      testCloneObj(var1, var2);
      testNewObjDyn(IBlockState.class, var1, var2);
      long var3 = testNew(var1, var2);
      long var5 = testClone(var1, var2);
      long var7 = testNewObj(var1, var2);
      long var9 = testCloneObj(var1, var2);
      long var11 = testNewObjDyn(IBlockState.class, var1, var2);
      Config.dbg("New: " + var3);
      Config.dbg("Clone: " + var5);
      Config.dbg("NewObj: " + var7);
      Config.dbg("CloneObj: " + var9);
      Config.dbg("NewObjDyn: " + var11);
   }

   public static long testNewObj(int var0, int var1) {
      long var2 = System.currentTimeMillis();

      for (int var4 = 0; var4 < var1; var4++) {
         IBlockState[] var5 = new IBlockState[var0];
      }

      long var6 = System.currentTimeMillis();
      return var6 - var2;
   }

   public static synchronized void freeArray(int[] var0) {
      if (arrays.size() < maxCacheSize) {
         arrays.add(var0);
      }
   }
}
