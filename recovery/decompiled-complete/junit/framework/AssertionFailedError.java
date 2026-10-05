package junit.framework;

import io.netty.channel.group.DefaultChannelGroupFuture;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.entity.projectile.EntitySmallFireball;

public class AssertionFailedError extends Error {
   public DefaultChannelGroupFuture field_0001;
   public static long field_0003;
   public EntitySmallFireball field_0000;
   public BlockLeavesBase field_0002;

   public AssertionFailedError() {
   }

   public AssertionFailedError(String var1) {
      super(var1);
   }
}
