package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.server.integrated.IntegratedServer$1;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$1;

public class WorldGenCactus extends WorldGenerator {
   public IntegratedServer$1 field_0000;
   public StructureNetherBridgePieces$1 field_0001;

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      for (int var4 = 0; var4 < 10; var4++) {
         BlockPos var5 = var3.add(var2.nextInt(8) - var2.nextInt(8), var2.nextInt(4) - var2.nextInt(4), var2.nextInt(8) - var2.nextInt(8));
         if (var1.isAirBlock(var5)) {
            int var6 = 1 + var2.nextInt(var2.nextInt(3) + 1);

            for (int var7 = 0; var7 < var6; var7++) {
               if (Blocks.cactus.canBlockStay(var1, var5)) {
                  var1.a(var5.up(var7), Blocks.cactus.getDefaultState(), 2);
               }
            }
         }
      }

      return true;
   }
}
