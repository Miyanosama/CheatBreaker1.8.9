package io.netty.handler.codec.serialization;

import io.netty.channel.DefaultChannelPipeline$1;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;
import net.minecraft.entity.EntityMinecartCommandBlock$1;

public class WeakReferenceMap<K, V> extends ReferenceMap<K, V> {
   public DefaultChannelPipeline$1 __junk3461498208329589806;
   public EntityMinecartCommandBlock$1 __junk4402496915723277006;

   public WeakReferenceMap(Map<K, Reference<V>> var1) {
      super(var1);
   }

   @Override
   public Reference<V> fold(V var1) {
      return new WeakReference<>((V)var1);
   }
}
