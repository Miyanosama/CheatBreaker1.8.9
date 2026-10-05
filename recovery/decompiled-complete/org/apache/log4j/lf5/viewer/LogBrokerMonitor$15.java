package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer$EnumStatus;
import net.minecraft.server.management.BanList;
import net.optifine.util.CounterInt;

public class LogBrokerMonitor$15 implements ActionListener {
   public BanList field_0001;
   public EntityPlayer$EnumStatus field_0003;
   public CounterInt field_0000;
   public LogBrokerMonitor this$0;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.selectAllLogTableColumns(false);
      List var2 = this.this$0.updateView();
      this.this$0._table.setView(var2);
   }

   public LogBrokerMonitor$15(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
