package net.minecraft.client.particle;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ItemModelGenerator$SpanFacing;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.world.World;

public class EntityBlockDustFX extends EntityDiggingFX {
   public EntitySilverfish field_0001;
   public BlockChest field_0003;
   public ItemModelGenerator$SpanFacing field_0000;
   public EntityFirework$OverlayFX field_0002;

   public EntityBlockDustFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, IBlockState var14) {
      super(var1, var2, var4, var6, var8, var10, var12, var14);
      this.v = var8;
      this.w = var10;
      this.x = var12;
   }
}
