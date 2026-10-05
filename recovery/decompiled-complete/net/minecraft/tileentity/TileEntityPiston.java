package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import io.netty.handler.codec.http.multipart.DiskAttribute;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.WeightedRandom$Item;
import recovered.unidentified.UnidentifiedClass4617;

public class TileEntityPiston extends TileEntity implements ITickable {
   public float progress;
   public DiskAttribute field_0008;
   public EnumFacing pistonFacing;
   public boolean shouldHeadBeRendered;
   public boolean extending;
   public List<Entity> field_174933_k = Lists.newArrayList();
   public float lastProgress;
   public IBlockState pistonState;
   public WeightedRandom$Item field_0000;

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.pistonState = Block.getBlockById(var1.getInteger("blockId")).getStateFromMeta(var1.getInteger("blockData"));
      this.pistonFacing = EnumFacing.getFront(var1.getInteger("facing"));
      this.lastProgress = this.progress = var1.getFloat("progress");
      this.extending = var1.getBoolean("extending");
   }

   public void launchWithSlimeBlock(float var1, float var2) {
      if (this.extending) {
         var1 = 1.0F - var1;
      } else {
         var1--;
      }

      AxisAlignedBB var3 = Blocks.piston_extension.getBoundingBox(this.b, this.c, this.pistonState, var1, this.pistonFacing);
      if (var3 != null) {
         List var4 = this.b.getEntitiesWithinAABBExcludingEntity((Entity)null, var3);
         if (!var4.isEmpty()) {
            this.field_174933_k.addAll(var4);

            for (Entity var6 : this.field_174933_k) {
               if (this.pistonState.getBlock() == Blocks.slime_block && this.extending) {
                  switch (UnidentifiedClass4617.field_0003[this.pistonFacing.getAxis().ordinal()]) {
                     case 1:
                        var6.v = this.pistonFacing.getFrontOffsetX();
                        break;
                     case 2:
                        var6.w = this.pistonFacing.getFrontOffsetY();
                        break;
                     case 3:
                        var6.x = this.pistonFacing.getFrontOffsetZ();
                  }
               } else {
                  var6.d(var2 * this.pistonFacing.getFrontOffsetX(), var2 * this.pistonFacing.getFrontOffsetY(), var2 * this.pistonFacing.getFrontOffsetZ());
               }
            }

            this.field_174933_k.clear();
         }
      }
   }

   public float method_05316(float var1) {
      return this.extending
         ? (this.getProgress(var1) - 1.0F) * this.pistonFacing.getFrontOffsetY()
         : (1.0F - this.getProgress(var1)) * this.pistonFacing.getFrontOffsetY();
   }

   public TileEntityPiston(IBlockState var1, EnumFacing var2, boolean var3, boolean var4) {
      this.pistonState = var1;
      this.pistonFacing = var2;
      this.extending = var3;
      this.shouldHeadBeRendered = var4;
   }

   @Override
   public int u() {
      return 0;
   }

   public float method_05321(float var1) {
      return this.extending
         ? (this.getProgress(var1) - 1.0F) * this.pistonFacing.getFrontOffsetX()
         : (1.0F - this.getProgress(var1)) * this.pistonFacing.getFrontOffsetX();
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setInteger("blockId", Block.getIdFromBlock(this.pistonState.getBlock()));
      var1.setInteger("blockData", this.pistonState.getBlock().getMetaFromState(this.pistonState));
      var1.setInteger("facing", this.pistonFacing.getIndex());
      var1.setFloat("progress", this.lastProgress);
      var1.setBoolean("extending", this.extending);
   }

   @Override
   public void update() {
      this.lastProgress = this.progress;
      if (this.lastProgress >= 1.0F) {
         this.launchWithSlimeBlock(1.0F, 0.25F);
         this.b.removeTileEntity(this.c);
         this.invalidate();
         if (this.b.getBlockState(this.c).getBlock() == Blocks.piston_extension) {
            this.b.a(this.c, this.pistonState, 3);
            this.b.notifyBlockOfStateChange(this.c, this.pistonState.getBlock());
         }
      } else {
         this.progress += 0.5F;
         if (this.progress >= 1.0F) {
            this.progress = 1.0F;
         }

         if (this.extending) {
            this.launchWithSlimeBlock(this.progress, this.progress - this.lastProgress + 0.0625F);
         }
      }
   }

   public EnumFacing getFacing() {
      return this.pistonFacing;
   }

   public void clearPistonTileEntity() {
      if (this.lastProgress < 1.0F && this.b != null) {
         this.lastProgress = this.progress = 1.0F;
         this.b.removeTileEntity(this.c);
         this.invalidate();
         if (this.b.getBlockState(this.c).getBlock() == Blocks.piston_extension) {
            this.b.a(this.c, this.pistonState, 3);
            this.b.notifyBlockOfStateChange(this.c, this.pistonState.getBlock());
         }
      }
   }

   public boolean shouldPistonHeadBeRendered() {
      return this.shouldHeadBeRendered;
   }

   public float method_05315(float var1) {
      return this.extending
         ? (this.getProgress(var1) - 1.0F) * this.pistonFacing.getFrontOffsetZ()
         : (1.0F - this.getProgress(var1)) * this.pistonFacing.getFrontOffsetZ();
   }

   public boolean isExtending() {
      return this.extending;
   }

   public TileEntityPiston() {
   }

   public IBlockState getPistonState() {
      return this.pistonState;
   }

   public float getProgress(float var1) {
      if (var1 > 1.0F) {
         var1 = 1.0F;
      }

      return this.lastProgress + (this.progress - this.lastProgress) * var1;
   }
}
