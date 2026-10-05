package net.minecraft.client.renderer.chunk;

import io.netty.handler.codec.http.multipart.InterfaceHttpData$HttpDataType;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.resources.SkinManager$3$1;
import net.minecraft.util.BlockPos;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;

public class VboChunkFactory implements IRenderChunkFactory {
   public InterfaceHttpData$HttpDataType field_0001;
   public MinecraftException field_0002;
   public SkinManager$3$1 field_0000;

   @Override
   public RenderChunk makeRenderChunk(World var1, RenderGlobal var2, BlockPos var3, int var4) {
      return new RenderChunk(var1, var2, var3, var4);
   }
}
