package net.minecraft.client.particle;

import io.netty.channel.socket.nio.NioServerSocketChannel$1;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher$3;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.World;

public class EntityExplodeFX$Factory implements IParticleFactory {
   public NioServerSocketChannel$1 field_0001;
   public ChunkRenderDispatcher$3 field_0002;
   public RenderManager field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityExplodeFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
