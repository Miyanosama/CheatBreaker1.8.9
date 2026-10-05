package org.apache.log4j.chainsaw;

import com.cheatbreaker.client.websocket.client.WSPacketClientFriendRemove;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceValuesToDoubleTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import net.minecraft.crash.CrashReportCategory$7;
import net.minecraft.entity.passive.EntityHorse$1;
import org.apache.log4j.Priority;

public class ControlPanel$1 implements ActionListener {
   public ControlPanel this$0;
   public MyTableModel val$aModel;
   public CrashReportCategory$7 field_0002;
   public WSPacketClientFriendRemove field_0004;
   public ConcurrentHashMapV8$MapReduceValuesToDoubleTask field_0000;
   public EntityHorse$1 field_0001;
   public JComboBox val$priorities;

   public ControlPanel$1(ControlPanel var1, MyTableModel var2, JComboBox var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$priorities = var3;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.val$aModel.setPriorityFilter((Priority)this.val$priorities.getSelectedItem());
   }
}
