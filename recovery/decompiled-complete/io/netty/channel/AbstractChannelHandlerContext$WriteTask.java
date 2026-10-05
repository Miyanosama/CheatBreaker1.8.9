package io.netty.channel;

import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.block.BlockOldLog$1;
import net.minecraft.item.ItemArmor$1;
import recovered.unidentified.UnidentifiedClass1908;

public class AbstractChannelHandlerContext$WriteTask extends AbstractChannelHandlerContext$AbstractWriteTask implements SingleThreadEventLoop$NonWakeupRunnable {
   public static Recycler<AbstractChannelHandlerContext$WriteTask> RECYCLER = new AbstractChannelHandlerContext$WriteTask$1();
   public BlockOldLog$1 __junk617781831558195568;
   public UnidentifiedClass1908 __junk4886911348081841251;
   public ItemArmor$1 __junk9119073651414818694;

   public AbstractChannelHandlerContext$WriteTask(Recycler$Handle var1) {
      super(var1, null);
   }

   @Override
   public void recycle(Recycler$Handle var1) {
      RECYCLER.recycle(this, var1);
   }

   public static AbstractChannelHandlerContext$WriteTask newInstance(AbstractChannelHandlerContext var0, Object var1, int var2, ChannelPromise var3) {
      AbstractChannelHandlerContext$WriteTask var4 = RECYCLER.get();
      init(var4, var0, var1, var2, var3);
      return var4;
   }
}
