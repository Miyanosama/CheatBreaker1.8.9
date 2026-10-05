package net.minecraft.client.particle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.log4j.pattern.CachedDateFormat;

public class EntityRainFX extends EntityFX {
   public CachedDateFormat field_0001;
   public EnumPacketDirection field_0002;
   public GuiSnooper field_0000;

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      this.w = this.w - this.i;
      this.d(this.v, this.w, this.x);
      this.v *= 0.98F;
      this.w *= 0.98F;
      this.x *= 0.98F;
      if (this.g-- <= 0) {
         this.setDead();
      }

      if (this.C) {
         if (Math.random() < 0.5) {
            this.setDead();
         }

         this.v *= 0.7F;
         this.x *= 0.7F;
      }

      BlockPos var1 = new BlockPos(this);
      IBlockState var2 = this.o.getBlockState(var1);
      Block var3 = var2.getBlock();
      var3.setBlockBoundsBasedOnState(this.o, var1);
      Material var4 = var2.getBlock().getMaterial();
      if (var4.isLiquid() || var4.isSolid()) {
         double var5 = 0.0;
         if (var2.getBlock() instanceof BlockLiquid) {
            var5 = 1.0F - BlockLiquid.getLiquidHeightPercent(var2.getValue(BlockLiquid.b));
         } else {
            var5 = var3.getBlockBoundsMaxY();
         }

         double var7 = MathHelper.floor_double(this.t) + var5;
         if (this.t < var7) {
            this.setDead();
         }
      }
   }

   public EntityRainFX(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
      this.v *= 0.3F;
      this.w = Math.random() * 0.2F + 0.1F;
      this.x *= 0.3F;
      this.ar = 1.0F;
      this.as = 1.0F;
      this.at = 1.0F;
      this.k(19 + this.V.nextInt(4));
      this.setSize(0.01F, 0.01F);
      this.i = 0.06F;
      this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2));
   }
}
