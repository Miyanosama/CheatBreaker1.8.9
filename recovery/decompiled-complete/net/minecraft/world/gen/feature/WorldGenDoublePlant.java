package net.minecraft.world.gen.feature;

import io.netty.channel.PendingWriteQueue$PendingWrite;
import java.util.Random;
import net.minecraft.block.BlockDoublePlant$EnumPlantType;
import net.minecraft.client.particle.MobAppearance;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.enchantment.EnchantmentKnockback;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenDoublePlant extends WorldGenerator {
   public EnchantmentKnockback field_0002;
   public PendingWriteQueue$PendingWrite field_0004;
   public PlayerNotFoundException field_0001;
   public BlockDoublePlant$EnumPlantType field_150549_a;
   public MobAppearance field_0000;

   public void setPlantType(BlockDoublePlant$EnumPlantType var1) {
      this.field_150549_a = var1;
   }

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      boolean var4 = false;

      for (int var5 = 0; var5 < 64; var5++) {
         BlockPos var6 = var3.add(var2.nextInt(8) - var2.nextInt(8), var2.nextInt(4) - var2.nextInt(4), var2.nextInt(8) - var2.nextInt(8));
         if (var1.isAirBlock(var6) && (!var1.t.getHasNoSky() || var6.getY() < 254) && Blocks.double_plant.canPlaceBlockAt(var1, var6)) {
            Blocks.double_plant.placeAt(var1, var6, this.field_150549_a, 2);
            var4 = true;
         }
      }

      return var4;
   }
}
