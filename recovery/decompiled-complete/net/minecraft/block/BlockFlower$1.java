package net.minecraft.block;

import com.google.common.base.Predicate;
import io.netty.util.concurrent.DefaultPromise$2;

public class BlockFlower$1 implements Predicate<BlockFlower$EnumFlowerType> {
   public DefaultPromise$2 field_0000;

   public boolean apply(BlockFlower$EnumFlowerType var1) {
      return var1.getBlockType() == this.field_180355_a.getBlockType();
   }

   public BlockFlower$1(BlockFlower var1) {
      this.field_180355_a = var1;
      super();
   }
}
