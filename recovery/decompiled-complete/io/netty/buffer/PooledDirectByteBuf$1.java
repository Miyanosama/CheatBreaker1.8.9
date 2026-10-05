package io.netty.buffer;

import io.netty.handler.codec.http.HttpObjectDecoder$State;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import io.netty.util.internal.NoOpTypeParameterMatcher;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.optifine.reflect.Reflector;

public class PooledDirectByteBuf$1 extends Recycler<PooledDirectByteBuf> {
   public Reflector __junk4066420178554568344;
   public HttpObjectDecoder$State __junk5021811756633790026;
   public GenLayerRareBiome __junk5181355623270534652;
   public NoOpTypeParameterMatcher __junk5408803793693345958;

   public PooledDirectByteBuf newObject(Recycler$Handle var1) {
      return new PooledDirectByteBuf(var1, 0, null);
   }
}
