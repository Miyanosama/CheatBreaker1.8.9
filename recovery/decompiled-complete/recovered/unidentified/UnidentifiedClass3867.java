package recovered.unidentified;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javazoom.jl.decoder.LayerIIIDecoder$Sftable;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityExplodeFX$Factory;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.optifine.shaders.IteratorRenderChunks;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.MacroState;

public class UnidentifiedClass3867 {
   public MacroState field_0002;
   public WorldGenFlowers field_0004;
   public LayerIIIDecoder$Sftable field_0001;
   public EntityFireworkRocket field_0003;
   public EntityExplodeFX$Factory field_0000;

   public static Iterator<RenderChunk> method_23428(WorldClient var0, double var1, Entity var3, int var4, ViewFrustum var5) {
      float var6 = Shaders.getShadowRenderDistance();
      if (var6 > 0.0F && var6 < (var4 - 1) * 16) {
         int var18 = MathHelper.ceiling_float_int(var6 / 16.0F) + 1;
         float var8 = var0.getCelestialAngleRadians((float)var1);
         float var9 = Shaders.sunPathRotation * MathHelper.field_0008;
         float var10 = var8 > MathHelper.field_0000 && var8 < 3.0F * MathHelper.field_0000 ? var8 + MathHelper.PI : var8;
         float var11 = -MathHelper.sin(var10);
         float var12 = MathHelper.cos(var10) * MathHelper.cos(var9);
         float var13 = -MathHelper.cos(var10) * MathHelper.sin(var9);
         BlockPos var14 = new BlockPos(MathHelper.floor_double(var3.s) >> 4, MathHelper.floor_double(var3.t) >> 4, MathHelper.floor_double(var3.u) >> 4);
         BlockPos var15 = var14.add((double)(-var11 * var18), (double)(-var12 * var18), (double)(-var13 * var18));
         BlockPos var16 = var14.add((double)(var11 * var4), (double)(var12 * var4), (double)(var13 * var4));
         return new IteratorRenderChunks(var5, var15, var16, var18, var18);
      } else {
         List var7 = Arrays.asList(var5.renderChunks);
         return var7.iterator();
      }
   }
}
