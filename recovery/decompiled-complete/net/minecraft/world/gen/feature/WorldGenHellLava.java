package net.minecraft.world.gen.feature;

import io.netty.buffer.CompositeByteBuf$Component;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.optifine.entity.model.ModelAdapterOcelot;

public class WorldGenHellLava extends WorldGenerator {
   public ModelAdapterOcelot field_0002;
   public CompositeByteBuf$Component field_0004;
   public boolean field_94524_b;
   public GuiConfirmOpenLink field_0003;
   public Block field_150553_a;

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      if (var1.getBlockState(var3.up()).getBlock() != Blocks.netherrack) {
         return false;
      } else if (var1.getBlockState(var3).getBlock().getMaterial() != Material.air && var1.getBlockState(var3).getBlock() != Blocks.netherrack) {
         return false;
      } else {
         int var4 = 0;
         if (var1.getBlockState(var3.west()).getBlock() == Blocks.netherrack) {
            var4++;
         }

         if (var1.getBlockState(var3.east()).getBlock() == Blocks.netherrack) {
            var4++;
         }

         if (var1.getBlockState(var3.north()).getBlock() == Blocks.netherrack) {
            var4++;
         }

         if (var1.getBlockState(var3.south()).getBlock() == Blocks.netherrack) {
            var4++;
         }

         if (var1.getBlockState(var3.down()).getBlock() == Blocks.netherrack) {
            var4++;
         }

         int var5 = 0;
         if (var1.isAirBlock(var3.west())) {
            var5++;
         }

         if (var1.isAirBlock(var3.east())) {
            var5++;
         }

         if (var1.isAirBlock(var3.north())) {
            var5++;
         }

         if (var1.isAirBlock(var3.south())) {
            var5++;
         }

         if (var1.isAirBlock(var3.down())) {
            var5++;
         }

         if (!this.field_94524_b && var4 == 4 && var5 == 1 || var4 == 5) {
            var1.a(var3, this.field_150553_a.getDefaultState(), 2);
            var1.forceBlockUpdateTick(this.field_150553_a, var3, var2);
         }

         return true;
      }
   }

   public WorldGenHellLava(Block var1, boolean var2) {
      this.field_150553_a = var1;
      this.field_94524_b = var2;
   }
}
