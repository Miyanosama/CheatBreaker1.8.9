package com.cheatbreaker.client.util.server;

import net.minecraft.client.gui.GuiScreenCustomizePresets$ListPreset;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.EntitySelectors$4;

public enum ServerRestrictionAction {
   field_0005,
   field_0000,
   field_0006;
   // $VF: synthetic field
   public static ServerRestrictionAction[] field_0003 = new ServerRestrictionAction[]{
      ServerRestrictionAction.field_0006, ServerRestrictionAction.field_0000, ServerRestrictionAction.field_0005
   };
   public GuiScreenCustomizePresets$ListPreset field_0002;
   public EntitySelectors$4 field_0004;
   public MobSpawnerBaseLogic field_0001;

   public static ServerRestrictionAction method_06239(String var0) {
      return Enum.valueOf(ServerRestrictionAction.class, var0);
   }
}
