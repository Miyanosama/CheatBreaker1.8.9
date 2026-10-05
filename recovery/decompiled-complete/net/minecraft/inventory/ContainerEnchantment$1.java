package net.minecraft.inventory;

import io.netty.channel.epoll.EpollDatagramChannel$EpollDatagramChannelUnsafe;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$LogBrokerMonitorWindowAdaptor;

public class ContainerEnchantment$1 extends InventoryBasic {
   public LogBrokerMonitor$LogBrokerMonitorWindowAdaptor field_0001;
   public EpollDatagramChannel$EpollDatagramChannelUnsafe field_0002;

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public void markDirty() {
      super.markDirty();
      this.field_70484_a.onCraftMatrixChanged(this);
   }

   public ContainerEnchantment$1(ContainerEnchantment var1, String var2, boolean var3, int var4) {
      this.field_70484_a = var1;
      super(var2, var3, var4);
   }
}
