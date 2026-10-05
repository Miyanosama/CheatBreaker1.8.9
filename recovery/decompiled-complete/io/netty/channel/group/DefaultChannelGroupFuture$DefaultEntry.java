package io.netty.channel.group;

import io.netty.handler.timeout.IdleStateHandler$AllIdleTimeoutTask;
import java.util.Map.Entry;
import net.minecraft.client.renderer.WorldRenderer$2;

public class DefaultChannelGroupFuture$DefaultEntry<K, V> implements Entry<K, V> {
   public IdleStateHandler$AllIdleTimeoutTask __junk5805168467541463293;
   public V value;
   public K key;
   public WorldRenderer$2 __junk6665659999312767266;

   @Override
   public V getValue() {
      return this.value;
   }

   @Override
   public K getKey() {
      return this.key;
   }

   public DefaultChannelGroupFuture$DefaultEntry(K var1, V var2) {
      this.key = (K)var1;
      this.value = (V)var2;
   }

   @Override
   public V setValue(V var1) {
      throw new UnsupportedOperationException("read-only");
   }
}
