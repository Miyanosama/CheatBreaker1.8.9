package org.apache.log4j.lf5.viewer.categoryexplorer;

import com.cheatbreaker.client.ui.mainmenu.LegacyMainMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.client.renderer.entity.RenderDragon;
import recovered.unidentified.UnidentifiedClass0609;

public class CategoryNodeEditor$6 implements ActionListener {
   public CategoryNode val$node;
   public CategoryNodeEditor this$0;
   public UnidentifiedClass0609 field_0001;
   public LegacyMainMenu field_0003;
   public RenderDragon field_0000;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.expandDescendants(this.val$node);
   }

   public CategoryNodeEditor$6(CategoryNodeEditor var1, CategoryNode var2) {
      this.this$0 = var1;
      this.val$node = var2;
      super();
   }
}
