package com.cheatbreaker.client.ui.module;

import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.module.SomeRandomAssEnum;

public class ModuleResizeSnapshot {
   public float recoveredField3330;
   public float recoveredField3331;
   public int recoveredField3332;
   public AbstractModule recoveredField3333;
   public CBModulesGui recoveredField3334;
   public int recoveredField3335;
   public float recoveredField3336;
   public CBGuiAnchor recoveredField3337;
   public float recoveredField3338;
   public float recoveredField3339;
   public SomeRandomAssEnum recoveredField3340;

   public ModuleResizeSnapshot(CBModulesGui var1, AbstractModule var2, SomeRandomAssEnum var3, int var4, int var5) {
      this.recoveredField3334 = var1;
      this.recoveredField3333 = var2;
      this.recoveredField3338 = var2.getXTranslation();
      this.recoveredField3331 = var2.getYTranslation();
      this.recoveredField3336 = var2.recoveredField3889 * var2.method_28770();
      this.recoveredField3339 = var2.recoveredField3894 * var2.method_28770();
      this.recoveredField3335 = var4;
      this.recoveredField3332 = var5;
      this.recoveredField3340 = var3;
      this.recoveredField3330 = (Float)var2.recoveredField3895.getValue();
      this.recoveredField3337 = var2.getGuiAnchor();
   }
}
