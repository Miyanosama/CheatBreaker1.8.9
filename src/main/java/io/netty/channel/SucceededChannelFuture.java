package io.netty.channel;

import io.netty.util.concurrent.EventExecutor;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.world.chunk.NibbleArray;

public class SucceededChannelFuture extends CompleteChannelFuture {

   @Override
   public boolean isSuccess() {
      return true;
   }

   public SucceededChannelFuture(Channel var1, EventExecutor var2) {
      super(var1, var2);
   }

   @Override
   public Throwable cause() {
      return null;
   }
}
