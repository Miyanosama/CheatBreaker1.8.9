package io.netty.handler.codec.serialization;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockAnvil;
import net.minecraft.network.play.server.S2EPacketCloseWindow;

public class SoftReferenceMap<K, V> extends ReferenceMap<K, V> {

   public SoftReferenceMap(Map<K, Reference<V>> var1) {
      super(var1);
   }

   @Override
   public Reference<V> fold(V var1) {
      return new SoftReference<>((V)var1);
   }
}
