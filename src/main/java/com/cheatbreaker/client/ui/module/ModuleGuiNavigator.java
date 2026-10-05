package com.cheatbreaker.client.ui.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.ModuleManager;
import com.cheatbreaker.client.ui.element.module.ModuleListElement;
import com.cheatbreaker.client.ui.element.module.ModulePreviewElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import net.minecraft.client.Minecraft;

public class ModuleGuiNavigator {
   public void method_04442() {
      CBModulesGui var1 = new CBModulesGui();
      Minecraft.getMinecraft().displayGuiScreen(var1);
      var1.currentScrollableElement = var1.recoveredField793;
      ((ModuleListElement)CBModulesGui.instance.recoveredField793).recoveredField1358 = true;
      CBModulesGui.instance.recoveredField793.recoveredField3012 = CheatBreaker.getInstance().getModuleManager().recoveredField1724;
   }

   public void method_04444(AbstractModule var1) {
      ((ModuleListElement)CBModulesGui.instance.recoveredField793).recoveredField1358 = false;
      ((ModuleListElement)CBModulesGui.instance.recoveredField793).scrollable = ModulePreviewElement.recoveredField3798.recoveredField3795;
      ((ModuleListElement)CBModulesGui.instance.recoveredField793).module = var1;
      CBModulesGui.instance.recoveredField793.recoveredField3012 = CheatBreaker.getInstance().getModuleManager().recoveredField1724;
      CBModulesGui.instance.currentScrollableElement = CBModulesGui.instance.recoveredField793;
   }

   public void method_04443(String var1) {
      ModuleManager var2 = CheatBreaker.getInstance().getModuleManager();
      var2.keyStrokes.initialize();
      CBModulesGui var3 = new CBModulesGui();
      Minecraft.getMinecraft().displayGuiScreen(var3);
      var3.currentScrollableElement = var3.recoveredField801;
      this.method_04444(var2.method_21669(var1));
   }
}
