package io.netty.handler.codec.spdy;

import java.io.Serializable;
import java.util.Comparator;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.item.Item$7;

public class SpdySession$StreamComparator implements Serializable, Comparator<Integer> {
   public Item$7 __junk7339519098318445184;
   public static long serialVersionUID;
   public EnchantmentDurability __junk9197139545932472716;

   public SpdySession$StreamComparator(SpdySession var1) {
      this.this$0 = var1;
      super();
   }

   public int compare(Integer var1, Integer var2) {
      SpdySession$StreamState var3 = (SpdySession$StreamState)SpdySession.access$000(this.this$0).get(var1);
      SpdySession$StreamState var4 = (SpdySession$StreamState)SpdySession.access$000(this.this$0).get(var2);
      int var5 = var3.getPriority() - var4.getPriority();
      return var5 != 0 ? var5 : var1 - var2;
   }
}
