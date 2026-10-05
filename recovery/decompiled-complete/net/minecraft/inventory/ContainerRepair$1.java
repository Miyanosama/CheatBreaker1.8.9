package net.minecraft.inventory;

import junit.swingui.TestHierarchyRunView$1;
import net.minecraft.client.gui.GuiSnooper$List;
import net.minecraft.enchantment.EnchantmentFireAspect;

public class ContainerRepair$1 extends InventoryBasic {
   public GuiSnooper$List field_0001;
   public TestHierarchyRunView$1 field_0003;
   public EnchantmentFireAspect field_0000;

   @Override
   public void markDirty() {
      super.markDirty();
      this.field_135010_a.onCraftMatrixChanged(this);
   }

   public ContainerRepair$1(ContainerRepair var1, String var2, boolean var3, int var4) {
      this.field_135010_a = var1;
      super(var2, var3, var4);
   }
}
