package net.minecraft.block;

import com.google.common.base.Predicate;
import io.netty.channel.nio.AbstractNioByteChannel;
import javazoom.jl.decoder.Manager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.optifine.expr.ExpressionParser$1;

public class BlockRedstoneComparator$1 implements Predicate<Entity> {
   public AbstractNioByteChannel field_0001;
   public Manager field_0003;
   public ExpressionParser$1 field_0000;

   public BlockRedstoneComparator$1(BlockRedstoneComparator var1, EnumFacing var2) {
      this.field_0004 = var1;
      this.field_0002 = var2;
      super();
   }

   public boolean method_04121(Entity var1) {
      return var1 != null && var1.getHorizontalFacing() == this.field_0002;
   }
}
