package org.apache.log4j.lf5.viewer.categoryexplorer;

import io.netty.channel.udt.nio.NioUdtProvider;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceEntriesToLongTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryNodeEditor$5 implements ActionListener {
   public CategoryNode val$node;
   public NioUdtProvider field_0003;
   public ConcurrentHashMapV8$MapReduceEntriesToLongTask field_0000;
   public CategoryNodeEditor this$0;

   public CategoryNodeEditor$5(CategoryNodeEditor var1, CategoryNode var2) {
      this.this$0 = var1;
      this.val$node = var2;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0._categoryModel.setDescendantSelection(this.val$node, false);
   }
}
