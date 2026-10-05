package org.apache.log4j.lf5.viewer;

import io.netty.handler.codec.MessageToMessageCodec$1;
import io.netty.handler.timeout.IdleState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import recovered.unidentified.UnidentifiedClass0611;

public class LogBrokerMonitor$12 implements ActionListener {
   public UnidentifiedClass0611 field_0002;
   public C01PacketEncryptionResponse field_0004;
   public LogBrokerMonitor field_0001;
   public IdleState field_0003;
   public MessageToMessageCodec$1 field_0000;

   public void actionPerformed(ActionEvent var1) {
      this.field_0001._table.getFilteredLogTableModel().refresh();
      this.field_0001.updateStatusLabel();
   }

   public LogBrokerMonitor$12(LogBrokerMonitor var1) {
      this.field_0001 = var1;
      super();
   }
}
