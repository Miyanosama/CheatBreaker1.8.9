package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.stats.StatBase$2;
import org.apache.log4j.xml.Log4jEntityResolver;
import org.java_websocket.extensions.permessage_deflate.PerMessageDeflateExtension;

public class CategoryNodeEditor$8 implements ActionListener {
   public CategoryNodeEditor this$0;
   public Log4jEntityResolver field_0003;
   public StatBase$2 field_0000;
   public PerMessageDeflateExtension field_0002;

   public void actionPerformed(ActionEvent var1) {
      while (this.this$0.removeUnusedNodes() > 0) {
      }
   }

   public CategoryNodeEditor$8(CategoryNodeEditor var1) {
      this.this$0 = var1;
      super();
   }
}
