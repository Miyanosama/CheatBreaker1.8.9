package net.minecraft.world.gen.feature;

import io.netty.channel.oio.AbstractOioChannel;
import java.util.Random;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.layers.LayerWitherAura;
import net.minecraft.entity.monster.EntitySpider$AISpiderAttack;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity$1;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.optifine.shaders.ProgramStage;

public class WorldGenGlowStone1 extends WorldGenerator {
   public LayerWitherAura field_0003;
   public ProgramStage field_0005;
   public TileEntity$1 field_0002;
   public AbstractOioChannel field_0004;
   public EntitySpider$AISpiderAttack field_0000;
   public BlockSilverfish field_0001;

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      if (!var1.isAirBlock(var3)) {
         return false;
      } else if (var1.getBlockState(var3.up()).getBlock() != Blocks.netherrack) {
         return false;
      } else {
         var1.a(var3, Blocks.glowstone.getDefaultState(), 2);

         for (int var4 = 0; var4 < 1500; var4++) {
            BlockPos var5 = var3.add(var2.nextInt(8) - var2.nextInt(8), -var2.nextInt(12), var2.nextInt(8) - var2.nextInt(8));
            if (var1.getBlockState(var5).getBlock().getMaterial() == Material.air) {
               int var6 = 0;

               for (EnumFacing var10 : EnumFacing.values()) {
                  if (var1.getBlockState(var5.a(var10)).getBlock() == Blocks.glowstone) {
                     var6++;
                  }

                  if (var6 > 1) {
                     break;
                  }
               }

               if (var6 == 1) {
                  var1.a(var5, Blocks.glowstone.getDefaultState(), 2);
               }
            }
         }

         return true;
      }
   }
}
