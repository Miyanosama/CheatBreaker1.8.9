package net.minecraft.block;

import com.cheatbreaker.client.module.type.MotionBlurModule;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityBanner$EnumBannerPattern;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.world.World;
import org.apache.log4j.jmx.Agent;

public class BlockPortal$Size {
   public EnumFacing$Axis axis;
   public World world;
   public TileEntityBanner$EnumBannerPattern field_0004;
   public int field_150868_h;
   public MotionBlurModule field_0001;
   public EnumFacing field_150866_c;
   public EnumFacing field_0009;
   public BlockPos field_150861_f;
   public Agent field_0003;
   public int field_150862_g;
   public int field_150864_e = 0;

   public void func_150859_c() {
      for (int var1 = 0; var1 < this.field_150868_h; var1++) {
         BlockPos var2 = this.field_150861_f.a(this.field_150866_c, var1);

         for (int var3 = 0; var3 < this.field_150862_g; var3++) {
            this.world.a(var2.up(var3), Blocks.portal.getDefaultState().withProperty(BlockPortal.AXIS, this.axis), 2);
         }
      }
   }

   public int func_181100_a() {
      return this.field_150862_g;
   }

   public int func_181101_b() {
      return this.field_150868_h;
   }

   public int method_25932() {
      label56:
      for (this.field_150862_g = 0; this.field_150862_g < 21; this.field_150862_g++) {
         for (int var1 = 0; var1 < this.field_150868_h; var1++) {
            BlockPos var2 = this.field_150861_f.a(this.field_150866_c, var1).up(this.field_150862_g);
            Block var3 = this.world.getBlockState(var2).getBlock();
            if (!this.func_150857_a(var3)) {
               break label56;
            }

            if (var3 == Blocks.portal) {
               this.field_150864_e++;
            }

            if (var1 == 0) {
               var3 = this.world.getBlockState(var2.a(this.field_0009)).getBlock();
               if (var3 != Blocks.obsidian) {
                  break label56;
               }
            } else if (var1 == this.field_150868_h - 1) {
               var3 = this.world.getBlockState(var2.a(this.field_150866_c)).getBlock();
               if (var3 != Blocks.obsidian) {
                  break label56;
               }
            }
         }
      }

      for (int var4 = 0; var4 < this.field_150868_h; var4++) {
         if (this.world.getBlockState(this.field_150861_f.a(this.field_150866_c, var4).up(this.field_150862_g)).getBlock() != Blocks.obsidian) {
            this.field_150862_g = 0;
            break;
         }
      }

      if (this.field_150862_g <= 21 && this.field_150862_g >= 3) {
         return this.field_150862_g;
      } else {
         this.field_150861_f = null;
         this.field_150868_h = 0;
         this.field_150862_g = 0;
         return 0;
      }
   }

   public BlockPortal$Size(World var1, BlockPos var2, EnumFacing$Axis var3) {
      this.world = var1;
      this.axis = var3;
      if (var3 == EnumFacing$Axis.X) {
         this.field_0009 = EnumFacing.EAST;
         this.field_150866_c = EnumFacing.WEST;
      } else {
         this.field_0009 = EnumFacing.NORTH;
         this.field_150866_c = EnumFacing.SOUTH;
      }

      BlockPos var4 = var2;

      while (var2.getY() > var4.getY() - 21 && var2.getY() > 0 && this.func_150857_a(var1.getBlockState(var2.down()).getBlock())) {
         var2 = var2.down();
      }

      int var5 = this.func_180120_a(var2, this.field_0009) - 1;
      if (var5 >= 0) {
         this.field_150861_f = var2.a(this.field_0009, var5);
         this.field_150868_h = this.func_180120_a(this.field_150861_f, this.field_150866_c);
         if (this.field_150868_h < 2 || this.field_150868_h > 21) {
            this.field_150861_f = null;
            this.field_150868_h = 0;
         }
      }

      if (this.field_150861_f != null) {
         this.field_150862_g = this.method_25932();
      }
   }

   public boolean func_150860_b() {
      return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
   }

   public boolean func_150857_a(Block var1) {
      return var1.J == Material.air || var1 == Blocks.fire || var1 == Blocks.portal;
   }

   public int func_180120_a(BlockPos var1, EnumFacing var2) {
      int var3;
      for (var3 = 0; var3 < 22; var3++) {
         BlockPos var4 = var1.a(var2, var3);
         if (!this.func_150857_a(this.world.getBlockState(var4).getBlock()) || this.world.getBlockState(var4.down()).getBlock() != Blocks.obsidian) {
            break;
         }
      }

      Block var5 = this.world.getBlockState(var1.a(var2, var3)).getBlock();
      return var5 == Blocks.obsidian ? var3 : 0;
   }
}
