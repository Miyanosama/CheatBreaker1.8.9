package io.netty.channel;

import io.netty.handler.codec.compression.JdkZlibDecoder$GzipState;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.optifine.entity.model.ModelAdapterBook;

public class AbstractChannelHandlerContext$WriteAndFlushTask extends AbstractChannelHandlerContext$AbstractWriteTask {
   public ModelAdapterBook __junk8485365075972180641;
   public GuiScreenResourcePacks __junk2476557488190462842;
   public JdkZlibDecoder$GzipState __junk8297950340883376774;
   public static Recycler<AbstractChannelHandlerContext$WriteAndFlushTask> RECYCLER = new AbstractChannelHandlerContext$WriteAndFlushTask$1();

   public AbstractChannelHandlerContext$WriteAndFlushTask(Recycler$Handle var1) {
      super(var1, null);
   }

   @Override
   public void recycle(Recycler$Handle var1) {
      RECYCLER.recycle(this, var1);
   }

   @Override
   public void write(AbstractChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      super.write(var1, var2, var3);
      AbstractChannelHandlerContext.access$1600(var1);
   }

   public static AbstractChannelHandlerContext$WriteAndFlushTask newInstance(AbstractChannelHandlerContext var0, Object var1, int var2, ChannelPromise var3) {
      AbstractChannelHandlerContext$WriteAndFlushTask var4 = RECYCLER.get();
      init(var4, var0, var1, var2, var3);
      return var4;
   }
}
