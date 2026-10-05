package net.minecraft.client.resources.model;

import com.google.common.collect.ComparisonChain;
import io.netty.buffer.AbstractByteBufAllocator;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandom$Item;

public class WeightedBakedModel$MyWeighedRandomItem extends WeightedRandom$Item implements Comparable<WeightedBakedModel$MyWeighedRandomItem> {
   public IBakedModel model;
   public AbstractByteBufAllocator field_0001;

   public int compareTo(WeightedBakedModel$MyWeighedRandomItem var1) {
      return ComparisonChain.start().compare(var1.a, this.a).compare(this.getCountQuads(), var1.getCountQuads()).result();
   }

   public WeightedBakedModel$MyWeighedRandomItem(IBakedModel var1, int var2) {
      super(var2);
      this.model = var1;
   }

   public int getCountQuads() {
      int var1 = this.model.getGeneralQuads().size();

      for (EnumFacing var5 : EnumFacing.values()) {
         var1 += this.model.getFaceQuads(var5).size();
      }

      return var1;
   }

   @Override
   public String toString() {
      return "MyWeighedRandomItem{weight=" + this.a + ", model=" + this.model + '}';
   }
}
