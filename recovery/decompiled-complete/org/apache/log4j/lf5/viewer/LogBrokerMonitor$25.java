package org.apache.log4j.lf5.viewer;

import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;

public class LogBrokerMonitor$25 implements ActionListener {
   public LogBrokerMonitor this$0;
   public ItemTransformVec3f field_0002;
   public CBFontRenderer field_0000;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.findSearchText();
   }

   public LogBrokerMonitor$25(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
