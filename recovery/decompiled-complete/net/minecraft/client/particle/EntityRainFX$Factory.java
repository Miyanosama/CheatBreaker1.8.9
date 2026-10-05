package net.minecraft.client.particle;

import io.netty.handler.ssl.SslContext$1;
import io.netty.handler.stream.ChunkedWriteHandler$3;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachKeyTask;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.realms.RealmsClickableScrolledSelectionList;
import net.minecraft.world.World;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;

public class EntityRainFX$Factory implements IParticleFactory {
   public RealmsClickableScrolledSelectionList field_0003;
   public SslContext$1 field_0005;
   public PropertyBool field_0002;
   public GenLayerRemoveTooMuchOcean field_0004;
   public ChunkedWriteHandler$3 field_0000;
   public ConcurrentHashMapV8$ForEachKeyTask field_0001;
   public RenderSilverfish field_0006;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityRainFX(var2, var3, var5, var7);
   }
}
