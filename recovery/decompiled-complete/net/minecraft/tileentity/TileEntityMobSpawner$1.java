package net.minecraft.tileentity;

import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.init.Blocks;
import net.minecraft.network.NettyCompressionDecoder;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TileEntityMobSpawner$1 extends MobSpawnerBaseLogic {
   public BlockPart field_0001;
   public NettyCompressionDecoder field_0003;
   public LayerSheepWool field_0000;

   @Override
   public void func_98267_a(int var1) {
      this.field_150825_a.b.addBlockEvent(this.field_150825_a.c, Blocks.mob_spawner, var1, 0);
   }

   @Override
   public BlockPos getSpawnerPosition() {
      return this.field_150825_a.c;
   }

   @Override
   public void setRandomEntity(MobSpawnerBaseLogic$WeightedRandomMinecart var1) {
      super.setRandomEntity(var1);
      if (this.getSpawnerWorld() != null) {
         this.getSpawnerWorld().h(this.field_150825_a.c);
      }
   }

   public TileEntityMobSpawner$1(TileEntityMobSpawner var1) {
      this.field_150825_a = var1;
      super();
   }

   @Override
   public World getSpawnerWorld() {
      return this.field_150825_a.b;
   }
}
