package com.cheatbreaker.client.ui.module;

import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulePosition;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.util.ArrayList;
import java.util.List;

public class ModuleGroupPositionSnapshot {
   public List<Float> recoveredField1003;
   public List<Object> recoveredField1004;
   public CBModulesGui recoveredField1005;
   public List<Float> recoveredField1006;
   public List<AbstractModule> recoveredField1007;
   public List<CBGuiAnchor> recoveredField1008;

   public ModuleGroupPositionSnapshot(CBModulesGui var1, List<CBModulePosition> var2) {
      this.recoveredField1005 = var1;
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      ArrayList var6 = new ArrayList();
      ArrayList var7 = new ArrayList();

      for (CBModulePosition var9 : var2) {
         if (var9.module.getGuiAnchor() != null) {
            var3.add(var9.module);
            var4.add(var9.module.getGuiAnchor());
            var5.add(var9.module.getXTranslation());
            var6.add(var9.module.getYTranslation());
            var7.add(var9.module.method_28770());
         }
      }

      this.recoveredField1007 = var3;
      this.recoveredField1008 = var4;
      this.recoveredField1006 = var5;
      this.recoveredField1003 = var6;
      this.recoveredField1004 = var7;
   }
}
