package net.optifine.gui;

import junit.swingui.TestRunner$12;
import net.minecraft.block.BlockNewLog;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.settings.GameSettings$Options;
import org.apache.log4j.jmx.HierarchyDynamicMBean;

public class GuiOptionButtonOF extends GuiOptionButton implements IOptionControl {
   public HierarchyDynamicMBean field_0003;
   public BlockNewLog field_0001;
   public TestRunner$12 field_0002;
   public GameSettings$Options option = null;

   public GuiOptionButtonOF(int var1, int var2, int var3, GameSettings$Options var4, String var5) {
      super(var1, var2, var3, var4, var5);
      this.option = var4;
   }

   @Override
   public GameSettings$Options getOption() {
      return this.option;
   }
}
