package org.apache.log4j.lf5.viewer.categoryexplorer;

import com.cheatbreaker.client.ui.overlay.friend.FriendRequest;
import io.netty.channel.local.LocalEventLoopGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryNodeEditor$7 implements ActionListener {
   public LocalEventLoopGroup field_0001;
   public CategoryNode val$node;
   public CategoryNodeEditor this$0;
   public FriendRequest field_0002;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.collapseDescendants(this.val$node);
   }

   public CategoryNodeEditor$7(CategoryNodeEditor var1, CategoryNode var2) {
      this.this$0 = var1;
      this.val$node = var2;
      super();
   }
}
