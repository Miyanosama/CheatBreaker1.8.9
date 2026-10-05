package net.minecraft.client.particle;

import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker08;
import io.netty.handler.ssl.util.SimpleTrustManagerFactory$2;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class Barrier$Factory implements IParticleFactory {
   public SimpleTrustManagerFactory$2 field_0001;
   public ChunkCompileTaskGenerator field_0003;
   public BlockFaceUV field_0000;
   public WebSocketClientHandshaker08 field_0002;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new Barrier(var2, var3, var5, var7, Item.getItemFromBlock(Blocks.barrier));
   }
}
