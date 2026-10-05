package org.apache.log4j.lf5.viewer;

import io.netty.bootstrap.ServerBootstrap$ServerBootstrapAcceptor$2;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$WrappedHttpRequest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.textui.TestRunner;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.util.IChatComponent$Serializer;
import org.apache.log4j.lf5.LogRecord;

public class LogBrokerMonitor$29 implements ActionListener {
   public TestRunner field_0003;
   public EntityAgeable field_0005;
   public ServerBootstrap$ServerBootstrapAcceptor$2 field_0002;
   public HttpPostRequestEncoder$WrappedHttpRequest field_0004;
   public LogBrokerMonitor this$0;
   public IChatComponent$Serializer field_0001;

   public LogBrokerMonitor$29(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0._table.clearLogRecords();
      this.this$0._categoryExplorerTree.getExplorerModel().resetAllNodeCounts();
      this.this$0.updateStatusLabel();
      this.this$0.clearDetailTextArea();
      LogRecord.resetSequenceNumber();
   }
}
