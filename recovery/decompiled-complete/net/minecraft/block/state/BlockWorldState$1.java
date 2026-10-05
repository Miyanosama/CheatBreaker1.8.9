package net.minecraft.block.state;

import com.cheatbreaker.client.module.type.HitboxesModule;
import com.google.common.base.Predicate;
import io.netty.channel.epoll.Native$NativeInetAddress;
import junit.textui.ResultPrinter;

public class BlockWorldState$1 implements Predicate<BlockWorldState> {
   public Native$NativeInetAddress field_0001;
   public HitboxesModule field_0000;
   public ResultPrinter field_0002;

   public BlockWorldState$1(Predicate var1) {
      this.field_177504_a = var1;
      super();
   }

   public boolean apply(BlockWorldState var1) {
      return var1 != null && this.field_177504_a.apply(var1.getBlockState());
   }
}
