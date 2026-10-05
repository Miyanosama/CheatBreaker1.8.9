package net.optifine.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorClass;
import net.optifine.reflect.ReflectorField;

public class ChunkUtils {
   public static ReflectorClass chunkClass = new ReflectorClass(Chunk.class);
   public static ReflectorField fieldHasEntities = findFieldHasEntities();
   public static ReflectorField fieldPrecipitationHeightMap = new ReflectorField(chunkClass, int[].class, 0);

   public static ReflectorField findFieldHasEntities() {
      try {
         Chunk var0 = new Chunk((World)null, 0, 0);
         ArrayList var1 = new ArrayList();
         ArrayList var2 = new ArrayList();
         Field[] var3 = Chunk.class.getDeclaredFields();

         for (int var4 = 0; var4 < var3.length; var4++) {
            Field var5 = var3[var4];
            if (var5.getType() == boolean.class) {
               var5.setAccessible(true);
               var1.add(var5);
               var2.add(var5.get(var0));
            }
         }

         var0.setHasEntities(false);
         ArrayList var13 = new ArrayList();

         for (Object var6 : var1) {
            Field var7 = (Field)var6;
            var13.add(var7.get(var0));
         }

         var0.setHasEntities(true);
         ArrayList var15 = new ArrayList();

         for (Object var18 : var1) {
            Field var8 = (Field)var18;
            var15.add(var8.get(var0));
         }

         ArrayList var17 = new ArrayList();

         for (int var19 = 0; var19 < var1.size(); var19++) {
            Field var21 = (Field)var1.get(var19);
            Boolean var9 = (Boolean)var13.get(var19);
            Boolean var10 = (Boolean)var15.get(var19);
            if (!var9 && var10) {
               var17.add(var21);
               Boolean var11 = (Boolean)var2.get(var19);
               var21.set(var0, var11);
            }
         }

         if (var17.size() == 1) {
            Field var20 = (Field)var17.get(0);
            return new ReflectorField(var20);
         }
      } catch (Exception var12) {
         Config.warn(var12.getClass().getName() + " " + var12.getMessage());
      }

      Config.warn("Error finding Chunk.hasEntities");
      return new ReflectorField(new ReflectorClass(Chunk.class), "hasEntities");
   }

   public static boolean hasEntities(Chunk var0) {
      return Reflector.getFieldValueBoolean(var0, fieldHasEntities, true);
   }

   public static int getPrecipitationHeight(Chunk var0, BlockPos var1) {
      int[] var2 = (int[])Reflector.getFieldValue(var0, fieldPrecipitationHeightMap);
      if (var2 != null && var2.length == 256) {
         int var3 = var1.getX() & 15;
         int var4 = var1.getZ() & 15;
         int var5 = var3 | var4 << 4;
         int var6 = var2[var5];
         if (var6 >= 0) {
            return var6;
         } else {
            BlockPos var7 = var0.getPrecipitationHeight(var1);
            return var7.getY();
         }
      } else {
         return -1;
      }
   }
}
