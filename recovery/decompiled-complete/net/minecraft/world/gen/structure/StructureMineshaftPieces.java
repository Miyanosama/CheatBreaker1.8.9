package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockLog$EnumAxis;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.optifine.entity.model.ModelAdapterMooshroom;
import recovered.unidentified.UnidentifiedClass5108;

public class StructureMineshaftPieces {
   public UnidentifiedClass5108 field_0001;
   public static List<WeightedRandomChestContent> CHEST_CONTENT_WEIGHT_LIST = Lists.newArrayList(
      new WeightedRandomChestContent[]{
         new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
         new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
         new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5),
         new WeightedRandomChestContent(Items.dye, EnumDyeColor.BLUE.getDyeDamage(), 4, 9, 5),
         new WeightedRandomChestContent(Items.diamond, 0, 1, 2, 3),
         new WeightedRandomChestContent(Items.coal, 0, 3, 8, 10),
         new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15),
         new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 1),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.rail), 0, 4, 8, 1),
         new WeightedRandomChestContent(Items.melon_seeds, 0, 2, 4, 10),
         new WeightedRandomChestContent(Items.pumpkin_seeds, 0, 2, 4, 10),
         new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3),
         new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1)
      }
   );
   public ModelAdapterMooshroom field_0000;
   public BlockLog$EnumAxis field_0002;

   public static void registerStructurePieces() {
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces$Corridor.class, "MSCorridor");
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces$Cross.class, "MSCrossing");
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces$Room.class, "MSRoom");
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces$Stairs.class, "MSStairs");
   }

   public static StructureComponent func_175892_a(List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6) {
      int var7 = var1.nextInt(100);
      if (var7 >= 80) {
         StructureBoundingBox var8 = StructureMineshaftPieces$Cross.func_175813_a(var0, var1, var2, var3, var4, var5);
         if (var8 != null) {
            return new StructureMineshaftPieces$Cross(var6, var1, var8, var5);
         }
      } else if (var7 >= 70) {
         StructureBoundingBox var9 = StructureMineshaftPieces$Stairs.func_175812_a(var0, var1, var2, var3, var4, var5);
         if (var9 != null) {
            return new StructureMineshaftPieces$Stairs(var6, var1, var9, var5);
         }
      } else {
         StructureBoundingBox var10 = StructureMineshaftPieces$Corridor.func_175814_a(var0, var1, var2, var3, var4, var5);
         if (var10 != null) {
            return new StructureMineshaftPieces$Corridor(var6, var1, var10, var5);
         }
      }

      return null;
   }

   public static StructureComponent func_175890_b(
      StructureComponent var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      if (var7 > 8) {
         return null;
      } else if (Math.abs(var3 - var0.getBoundingBox().minX) <= 80 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 80) {
         StructureComponent var8 = func_175892_a(var1, var2, var3, var4, var5, var6, var7 + 1);
         if (var8 != null) {
            var1.add(var8);
            var8.buildComponent(var0, var1, var2);
         }

         return var8;
      } else {
         return null;
      }
   }
}
