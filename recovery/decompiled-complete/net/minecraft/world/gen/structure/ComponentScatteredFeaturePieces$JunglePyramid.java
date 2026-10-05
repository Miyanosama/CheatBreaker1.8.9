package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import io.netty.handler.codec.spdy.DefaultSpdySettingsFrame;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockTripWire;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSnowball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.optifine.ClearWater;

public class ComponentScatteredFeaturePieces$JunglePyramid extends ComponentScatteredFeaturePieces$Feature {
   public DefaultSpdySettingsFrame field_0005;
   public boolean placedTrap1;
   public boolean placedMainChest;
   public static List<WeightedRandomChestContent> field_175815_j = Lists.newArrayList(
      new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.arrow, 0, 2, 7, 30)}
   );
   public static ComponentScatteredFeaturePieces$JunglePyramid$Stones junglePyramidsRandomScatteredStones = new ComponentScatteredFeaturePieces$JunglePyramid$Stones(
      null
   );
   public ItemSnowball field_0006;
   public ClearWater field_0008;
   public boolean placedTrap2;
   public static List<WeightedRandomChestContent> field_175816_i = Lists.newArrayList(
      new WeightedRandomChestContent[]{
         new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
         new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
         new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15),
         new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2),
         new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20),
         new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16),
         new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3),
         new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1),
         new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1),
         new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)
      }
   );
   public boolean placedHiddenChest;

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      super.readStructureFromNBT(var1);
      this.placedMainChest = var1.getBoolean("placedMainChest");
      this.placedHiddenChest = var1.getBoolean("placedHiddenChest");
      this.placedTrap1 = var1.getBoolean("placedTrap1");
      this.placedTrap2 = var1.getBoolean("placedTrap2");
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (!this.a(var1, var3, 0)) {
         return false;
      } else {
         int var4 = this.a(Blocks.stone_stairs, 3);
         int var5 = this.a(Blocks.stone_stairs, 2);
         int var6 = this.a(Blocks.stone_stairs, 0);
         int var7 = this.a(Blocks.stone_stairs, 1);
         this.a(var1, var3, 0, -4, 0, this.a - 1, 0, this.c - 1, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 2, 1, 2, 9, 2, 2, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 2, 1, 12, 9, 2, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 2, 1, 3, 2, 2, 11, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 9, 1, 3, 9, 2, 11, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 1, 3, 1, 10, 6, 1, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 1, 3, 13, 10, 6, 13, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 1, 3, 2, 1, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 10, 3, 2, 10, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 2, 3, 2, 9, 3, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 2, 6, 2, 9, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 3, 7, 3, 8, 7, 11, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 4, 8, 4, 7, 8, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithAir(var1, var3, 3, 1, 3, 8, 2, 11);
         this.fillWithAir(var1, var3, 4, 3, 6, 7, 3, 9);
         this.fillWithAir(var1, var3, 2, 4, 2, 9, 5, 12);
         this.fillWithAir(var1, var3, 4, 6, 5, 7, 6, 9);
         this.fillWithAir(var1, var3, 5, 7, 6, 6, 7, 8);
         this.fillWithAir(var1, var3, 5, 1, 2, 6, 2, 2);
         this.fillWithAir(var1, var3, 5, 2, 12, 6, 2, 12);
         this.fillWithAir(var1, var3, 5, 5, 1, 6, 5, 1);
         this.fillWithAir(var1, var3, 5, 5, 13, 6, 5, 13);
         this.a(var1, Blocks.air.getDefaultState(), 1, 5, 5, var3);
         this.a(var1, Blocks.air.getDefaultState(), 10, 5, 5, var3);
         this.a(var1, Blocks.air.getDefaultState(), 1, 5, 9, var3);
         this.a(var1, Blocks.air.getDefaultState(), 10, 5, 9, var3);

         for (byte var8 = 0; var8 <= 14; var8 += 14) {
            this.a(var1, var3, 2, 4, var8, 2, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 4, 4, var8, 4, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 7, 4, var8, 7, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 9, 4, var8, 9, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
         }

         this.a(var1, var3, 5, 6, 0, 6, 6, 0, false, var2, junglePyramidsRandomScatteredStones);

         for (byte var10 = 0; var10 <= 11; var10 += 11) {
            for (byte var9 = 2; var9 <= 12; var9 += 2) {
               this.a(var1, var3, var10, 4, var9, var10, 5, var9, false, var2, junglePyramidsRandomScatteredStones);
            }

            this.a(var1, var3, var10, 6, 5, var10, 6, 5, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, var10, 6, 9, var10, 6, 9, false, var2, junglePyramidsRandomScatteredStones);
         }

         this.a(var1, var3, 2, 7, 2, 2, 9, 2, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 9, 7, 2, 9, 9, 2, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 2, 7, 12, 2, 9, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 9, 7, 12, 9, 9, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 4, 9, 4, 4, 9, 4, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 7, 9, 4, 7, 9, 4, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 4, 9, 10, 4, 9, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 7, 9, 10, 7, 9, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 5, 9, 7, 6, 9, 7, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 5, 9, 6, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 6, 9, 6, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var5), 5, 9, 8, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var5), 6, 9, 8, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 4, 0, 0, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 5, 0, 0, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 6, 0, 0, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 7, 0, 0, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 4, 1, 8, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 4, 2, 9, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 4, 3, 10, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 7, 1, 8, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 7, 2, 9, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 7, 3, 10, var3);
         this.a(var1, var3, 4, 1, 9, 4, 1, 9, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 7, 1, 9, 7, 1, 9, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 4, 1, 10, 7, 2, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 5, 4, 5, 6, 4, 5, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var6), 4, 4, 5, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(var7), 7, 4, 5, var3);

         for (int var11 = 0; var11 < 4; var11++) {
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var5), 5, 0 - var11, 6 + var11, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var5), 6, 0 - var11, 6 + var11, var3);
            this.fillWithAir(var1, var3, 5, 0 - var11, 7 + var11, 6, 0 - var11, 9 + var11);
         }

         this.fillWithAir(var1, var3, 1, -3, 12, 10, -1, 13);
         this.fillWithAir(var1, var3, 1, -3, 1, 3, -1, 13);
         this.fillWithAir(var1, var3, 1, -3, 1, 9, -1, 5);

         for (byte var12 = 1; var12 <= 13; var12 += 2) {
            this.a(var1, var3, 1, -3, var12, 1, -2, var12, false, var2, junglePyramidsRandomScatteredStones);
         }

         for (byte var13 = 2; var13 <= 12; var13 += 2) {
            this.a(var1, var3, 1, -1, var13, 3, -1, var13, false, var2, junglePyramidsRandomScatteredStones);
         }

         this.a(var1, var3, 2, -2, 1, 5, -2, 1, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 7, -2, 1, 9, -2, 1, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 6, -3, 1, 6, -3, 1, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 6, -1, 1, 6, -1, 1, false, var2, junglePyramidsRandomScatteredStones);
         this.a(
            var1,
            Blocks.tripwire_hook
               .getStateFromMeta(this.a(Blocks.tripwire_hook, EnumFacing.EAST.getHorizontalIndex()))
               .withProperty(BlockTripWireHook.ATTACHED, true),
            1,
            -3,
            8,
            var3
         );
         this.a(
            var1,
            Blocks.tripwire_hook
               .getStateFromMeta(this.a(Blocks.tripwire_hook, EnumFacing.WEST.getHorizontalIndex()))
               .withProperty(BlockTripWireHook.ATTACHED, true),
            4,
            -3,
            8,
            var3
         );
         this.a(var1, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, true), 2, -3, 8, var3);
         this.a(var1, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, true), 3, -3, 8, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 7, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 6, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 5, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 4, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 3, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 2, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 1, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 4, -3, 1, var3);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 3, -3, 1, var3);
         if (!this.placedTrap1) {
            this.placedTrap1 = this.generateDispenserContents(var1, var3, var2, 3, -2, 1, EnumFacing.NORTH.getIndex(), field_175815_j, 2);
         }

         this.a(var1, Blocks.vine.getStateFromMeta(15), 3, -2, 2, var3);
         this.a(
            var1,
            Blocks.tripwire_hook
               .getStateFromMeta(this.a(Blocks.tripwire_hook, EnumFacing.NORTH.getHorizontalIndex()))
               .withProperty(BlockTripWireHook.ATTACHED, true),
            7,
            -3,
            1,
            var3
         );
         this.a(
            var1,
            Blocks.tripwire_hook
               .getStateFromMeta(this.a(Blocks.tripwire_hook, EnumFacing.SOUTH.getHorizontalIndex()))
               .withProperty(BlockTripWireHook.ATTACHED, true),
            7,
            -3,
            5,
            var3
         );
         this.a(var1, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, true), 7, -3, 2, var3);
         this.a(var1, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, true), 7, -3, 3, var3);
         this.a(var1, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, true), 7, -3, 4, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 8, -3, 6, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 9, -3, 6, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 9, -3, 5, var3);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 9, -3, 4, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 9, -2, 4, var3);
         if (!this.placedTrap2) {
            this.placedTrap2 = this.generateDispenserContents(var1, var3, var2, 9, -2, 3, EnumFacing.WEST.getIndex(), field_175815_j, 2);
         }

         this.a(var1, Blocks.vine.getStateFromMeta(15), 8, -1, 3, var3);
         this.a(var1, Blocks.vine.getStateFromMeta(15), 8, -2, 3, var3);
         if (!this.placedMainChest) {
            this.placedMainChest = this.generateChestContents(
               var1, var3, var2, 8, -3, 3, WeightedRandomChestContent.func_177629_a(field_175816_i, Items.enchanted_book.getRandom(var2)), 2 + var2.nextInt(5)
            );
         }

         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 9, -3, 2, var3);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 8, -3, 1, var3);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 4, -3, 5, var3);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 5, -2, 5, var3);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 5, -1, 5, var3);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 6, -3, 5, var3);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 7, -2, 5, var3);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 7, -1, 5, var3);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 8, -3, 5, var3);
         this.a(var1, var3, 9, -1, 1, 9, -1, 5, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithAir(var1, var3, 8, -3, 8, 10, -1, 10);
         this.a(var1, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 8, -2, 11, var3);
         this.a(var1, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 9, -2, 11, var3);
         this.a(var1, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 10, -2, 11, var3);
         this.a(
            var1,
            Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(this.a(Blocks.lever, EnumFacing.NORTH.getIndex())))),
            8,
            -2,
            12,
            var3
         );
         this.a(
            var1,
            Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(this.a(Blocks.lever, EnumFacing.NORTH.getIndex())))),
            9,
            -2,
            12,
            var3
         );
         this.a(
            var1,
            Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(this.a(Blocks.lever, EnumFacing.NORTH.getIndex())))),
            10,
            -2,
            12,
            var3
         );
         this.a(var1, var3, 8, -3, 8, 8, -3, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, var3, 10, -3, 8, 10, -3, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 10, -2, 9, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 8, -2, 9, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 8, -2, 10, var3);
         this.a(var1, Blocks.redstone_wire.getDefaultState(), 10, -1, 9, var3);
         this.a(var1, Blocks.sticky_piston.getStateFromMeta(EnumFacing.UP.getIndex()), 9, -2, 8, var3);
         this.a(var1, Blocks.sticky_piston.getStateFromMeta(this.a(Blocks.sticky_piston, EnumFacing.WEST.getIndex())), 10, -2, 8, var3);
         this.a(var1, Blocks.sticky_piston.getStateFromMeta(this.a(Blocks.sticky_piston, EnumFacing.WEST.getIndex())), 10, -1, 8, var3);
         this.a(var1, Blocks.unpowered_repeater.getStateFromMeta(this.a(Blocks.unpowered_repeater, EnumFacing.NORTH.getHorizontalIndex())), 10, -2, 10, var3);
         if (!this.placedHiddenChest) {
            this.placedHiddenChest = this.generateChestContents(
               var1, var3, var2, 9, -3, 10, WeightedRandomChestContent.func_177629_a(field_175816_i, Items.enchanted_book.getRandom(var2)), 2 + var2.nextInt(5)
            );
         }

         return true;
      }
   }

   public ComponentScatteredFeaturePieces$JunglePyramid() {
   }

   public ComponentScatteredFeaturePieces$JunglePyramid(Random var1, int var2, int var3) {
      super(var1, var2, 64, var3, 12, 10, 15);
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      super.writeStructureToNBT(var1);
      var1.setBoolean("placedMainChest", this.placedMainChest);
      var1.setBoolean("placedHiddenChest", this.placedHiddenChest);
      var1.setBoolean("placedTrap1", this.placedTrap1);
      var1.setBoolean("placedTrap2", this.placedTrap2);
   }
}
