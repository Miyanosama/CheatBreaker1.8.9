package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockSilverfish$EnumType;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.entity.EntityFlying;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.WorldChunkManager;

public class StructureStrongholdPieces$Stones extends StructureComponent$BlockSelector {
   public EntityFlying field_0001;
   public BlockDynamicLiquid field_0002;
   public GuiMultiplayer field_0000;
   public WorldChunkManager field_0003;

   public StructureStrongholdPieces$Stones() {
   }

   @Override
   public void selectBlocks(Random var1, int var2, int var3, int var4, boolean var5) {
      if (var5) {
         float var6 = var1.nextFloat();
         if (var6 < 0.2F) {
            this.a = Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CRACKED_META);
         } else if (var6 < 0.5F) {
            this.a = Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.MOSSY_META);
         } else if (var6 < 0.55F) {
            this.a = Blocks.monster_egg.getStateFromMeta(BlockSilverfish$EnumType.STONEBRICK.getMetadata());
         } else {
            this.a = Blocks.stonebrick.getDefaultState();
         }
      } else {
         this.a = Blocks.air.getDefaultState();
      }
   }
}
