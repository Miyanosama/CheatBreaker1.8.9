package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.ui.overlay.friend.FriendsListElement;
import io.netty.util.internal.NoOpTypeParameterMatcher;
import java.util.Map.Entry;
import net.minecraft.inventory.ContainerEnchantment$1;
import net.optifine.util.Json;

public class ConcurrentHashMapV8$EntrySpliterator<K, V>
   extends ConcurrentHashMapV8$Traverser<K, V>
   implements ConcurrentHashMapV8$ConcurrentHashMapSpliterator<Entry<K, V>> {
   public FriendsListElement __junk4586070975367392909;
   public NoOpTypeParameterMatcher __junk6882393827506163417;
   public ContainerEnchantment$1 __junk4917291613588292169;
   public Json __junk7925703154613514577;
   public long est;
   public ConcurrentHashMapV8<K, V> map;

   @Override
   public ConcurrentHashMapV8$ConcurrentHashMapSpliterator<Entry<K, V>> trySplit() {
      int var1 = this.baseIndex;
      int var2 = this.baseLimit;
      int var3;
      return (var3 = this.baseIndex + this.baseLimit >>> 1) <= var1
         ? null
         : new ConcurrentHashMapV8$EntrySpliterator<>(this.tab, this.baseSize, this.baseLimit = var3, var2, this.est >>>= 1, this.map);
   }

   @Override
   public long estimateSize() {
      return this.est;
   }

   @Override
   public void forEachRemaining(ConcurrentHashMapV8$Action<? super Entry<K, V>> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node var2;
         while ((var2 = this.advance()) != null) {
            var1.apply(new ConcurrentHashMapV8$MapEntry<>(var2.key, var2.val, this.map));
         }
      }
   }

   @Override
   public boolean tryAdvance(ConcurrentHashMapV8$Action<? super Entry<K, V>> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node var2;
         if ((var2 = this.advance()) == null) {
            return false;
         } else {
            var1.apply(new ConcurrentHashMapV8$MapEntry<>(var2.key, var2.val, this.map));
            return true;
         }
      }
   }

   public ConcurrentHashMapV8$EntrySpliterator(ConcurrentHashMapV8$Node<K, V>[] var1, int var2, int var3, int var4, long var5, ConcurrentHashMapV8<K, V> var7) {
      super(var1, var2, var3, var4);
      this.map = var7;
      this.est = var5;
   }
}
