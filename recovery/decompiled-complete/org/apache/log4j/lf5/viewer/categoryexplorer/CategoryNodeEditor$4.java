package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.particle.EntityHugeExplodeFX;
import net.minecraft.world.storage.WorldInfo$6;

public class CategoryNodeEditor$4 implements ActionListener {
   public EntityHugeExplodeFX field_0002;
   public WorldInfo$6 field_0004;
   public CategoryNode val$node;
   public CategoryNodeEditor this$0;
   public ModelSign field_0000;

   public CategoryNodeEditor$4(CategoryNodeEditor var1, CategoryNode var2) {
      this.this$0 = var1;
      this.val$node = var2;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0._categoryModel.setDescendantSelection(this.val$node, true);
   }
}
