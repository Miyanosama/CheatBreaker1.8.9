package net.minecraft.client.particle;

import io.netty.buffer.ByteBufUtil$1;
import net.minecraft.client.renderer.BlockModelShapes$2;
import net.minecraft.client.renderer.WorldVertexBufferUploader$1;
import net.minecraft.entity.passive.EntitySheep$1;
import net.minecraft.world.World;

public class EntityCritFX$Factory implements IParticleFactory {
   public EntitySheep$1 field_0001;
   public ByteBufUtil$1 field_0003;
   public BlockModelShapes$2 field_0000;
   public WorldVertexBufferUploader$1 field_0002;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityCritFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
