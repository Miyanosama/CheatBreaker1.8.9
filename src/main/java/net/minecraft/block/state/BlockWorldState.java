package net.minecraft.block.state;

import com.google.common.base.Predicate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockWorldState {
   public World world;
   public TileEntity tileEntity;
   public boolean tileEntityInitialized;
   public IBlockState state;
   public BlockPos pos;
   public boolean field_181628_c;

   public IBlockState getBlockState() {
      if (this.state == null && (this.field_181628_c || this.world.e(this.pos))) {
         this.state = this.world.getBlockState(this.pos);
      }

      return this.state;
   }

   public TileEntity getTileEntity() {
      if (this.tileEntity == null && !this.tileEntityInitialized) {
         this.tileEntity = this.world.getTileEntity(this.pos);
         this.tileEntityInitialized = true;
      }

      return this.tileEntity;
   }

   public BlockPos getPos() {
      return this.pos;
   }

   public BlockWorldState(World var1, BlockPos var2, boolean var3) {
      this.world = var1;
      this.pos = var2;
      this.field_181628_c = var3;
   }

   public static Predicate<BlockWorldState> hasState(final Predicate<IBlockState> var0) {
      return new Predicate<BlockWorldState>() {
         public boolean apply(BlockWorldState var1) {
            return var1 != null && var0.apply(var1.getBlockState());
         }
      };
   }
}
