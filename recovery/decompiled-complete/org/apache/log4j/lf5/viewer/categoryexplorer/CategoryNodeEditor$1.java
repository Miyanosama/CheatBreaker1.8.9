package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.client.particle.EntitySpellParticleFX$AmbientMobFactory;
import net.minecraft.command.CommandWeather;
import net.minecraft.entity.ai.EntityAIDoorInteract;

public class CategoryNodeEditor$1 implements ActionListener {
   public CategoryNodeEditor this$0;
   public EntitySpellParticleFX$AmbientMobFactory field_0003;
   public CommandWeather field_0000;
   public EntityAIDoorInteract field_0002;

   public CategoryNodeEditor$1(CategoryNodeEditor var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0._categoryModel.update(this.this$0._lastEditedNode, this.this$0._checkBox.isSelected());
      this.this$0.stopCellEditing();
   }
}
