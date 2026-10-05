package com.cheatbreaker.client.module.staff;

import com.cheatbreaker.client.config.Setting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.gui.ServerSelectionList;

public class XRayModule extends StaffModule {
   public List<Integer> staffModuleEnabled = new ArrayList<>();
   public Setting opacity;
   public ServerSelectionList field_0001;

   public boolean method_01554(int var1, boolean var2) {
      return !this.isEnabled() ? var2 : this.staffModuleEnabled.contains(var1);
   }

   @Override
   public void removeAllEvents() {
      super.removeAllEvents();
      this.minecraft.renderGlobal.loadRenderers();
   }

   public XRayModule() {
      super("xray");
      this.method_28828(true);
      Collections.addAll(this.staffModuleEnabled, 14, 15, 16, 15, 56, 129, 52);
      this.opacity = new Setting(this, "Opacity").setValue(45).setMinMax(15, 255);
   }

   @Override
   public void addAllEvents() {
      super.addAllEvents();
      this.minecraft.renderGlobal.loadRenderers();
   }

   public List<Integer> method_01553() {
      return this.staffModuleEnabled;
   }
}
