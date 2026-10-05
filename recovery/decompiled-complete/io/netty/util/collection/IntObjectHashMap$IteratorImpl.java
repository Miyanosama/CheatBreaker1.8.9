package io.netty.util.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.optifine.GlErrors;

public class IntObjectHashMap$IteratorImpl implements IntObjectMap$Entry<V>, Iterator<IntObjectMap$Entry<V>> {
   public ModelResourceLocation __junk7084283397741197810;
   public int nextIndex;
   public int prevIndex;
   public int entryIndex;
   public GlErrors __junk5399177767320177089;

   public void scanNext() {
      while (++this.nextIndex != IntObjectHashMap.access$100(this.this$0).length && IntObjectHashMap.access$100(this.this$0)[this.nextIndex] == null) {
      }
   }

   @Override
   public void setValue(V var1) {
      IntObjectHashMap.access$100(this.this$0)[this.entryIndex] = IntObjectHashMap.access$500(var1);
   }

   public IntObjectHashMap$IteratorImpl(IntObjectHashMap var1) {
      this.this$0 = var1;
      super();
      this.prevIndex = -1;
      this.nextIndex = -1;
      this.entryIndex = -1;
   }

   @Override
   public boolean hasNext() {
      if (this.nextIndex == -1) {
         this.scanNext();
      }

      return this.nextIndex < IntObjectHashMap.access$200(this.this$0).length;
   }

   @Override
   public V value() {
      return (V)IntObjectHashMap.access$400(IntObjectHashMap.access$100(this.this$0)[this.entryIndex]);
   }

   @Override
   public void remove() {
      if (this.prevIndex < 0) {
         throw new IllegalStateException("next must be called before each remove.");
      } else {
         IntObjectHashMap.access$300(this.this$0, this.prevIndex);
         this.prevIndex = -1;
      }
   }

   @Override
   public int key() {
      return IntObjectHashMap.access$200(this.this$0)[this.entryIndex];
   }

   public IntObjectMap$Entry<V> next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         this.prevIndex = this.nextIndex;
         this.scanNext();
         this.entryIndex = this.prevIndex;
         return this;
      }
   }
}
