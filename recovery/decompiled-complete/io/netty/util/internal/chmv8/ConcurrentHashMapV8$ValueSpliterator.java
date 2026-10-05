package io.netty.util.internal.chmv8;

import net.minecraft.command.server.CommandAchievement;
import net.minecraft.item.ItemMultiTexture$1;
import net.minecraft.network.play.server.S42PacketCombatEvent;

public class ConcurrentHashMapV8$ValueSpliterator<K, V>
   extends ConcurrentHashMapV8$Traverser<K, V>
   implements ConcurrentHashMapV8$ConcurrentHashMapSpliterator<V> {
   public CommandAchievement __junk7948872976301104166;
   public S42PacketCombatEvent __junk8525048961758799176;
   public long est;
   public ItemMultiTexture$1 __junk2897115253172143369;

   @Override
   public long estimateSize() {
      return this.est;
   }

   public ConcurrentHashMapV8$ValueSpliterator(ConcurrentHashMapV8$Node<K, V>[] var1, int var2, int var3, int var4, long var5) {
      super(var1, var2, var3, var4);
      this.est = var5;
   }

   @Override
   public boolean tryAdvance(ConcurrentHashMapV8$Action<? super V> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node var2;
         if ((var2 = this.advance()) == null) {
            return false;
         } else {
            var1.apply(var2.val);
            return true;
         }
      }
   }

   @Override
   public ConcurrentHashMapV8$ConcurrentHashMapSpliterator<V> trySplit() {
      int var1 = this.baseIndex;
      int var2 = this.baseLimit;
      int var3;
      return (var3 = this.baseIndex + this.baseLimit >>> 1) <= var1
         ? null
         : new ConcurrentHashMapV8$ValueSpliterator<>(this.tab, this.baseSize, this.baseLimit = var3, var2, this.est >>>= 1);
   }

   @Override
   public void forEachRemaining(ConcurrentHashMapV8$Action<? super V> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node var2;
         while ((var2 = this.advance()) != null) {
            var1.apply(var2.val);
         }
      }
   }
}
