package org.apache.log4j.lf5.viewer.categoryexplorer;

import io.netty.buffer.ByteBufProcessor$8;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.ai.EntityAITempt;

public class CategoryElement {
   public GuiFurnace field_0001;
   public EntityAITempt field_0003;
   public String _categoryTitle;
   public ByteBufProcessor$8 field_0002;

   public CategoryElement(String var1) {
      this._categoryTitle = var1;
   }

   public String getTitle() {
      return this._categoryTitle;
   }

   public void setTitle(String var1) {
      this._categoryTitle = var1;
   }

   public CategoryElement() {
   }
}
