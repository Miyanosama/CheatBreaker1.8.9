package com.cheatbreaker.client.ui.module;

import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.client.gui.GuiLockIconButton;
import net.minecraft.client.gui.MapItemRenderer$1;
import net.optifine.util.MathUtils;
import recovered.unidentified.UnidentifiedClass0000;

public class CBModulePosition {
   public AbstractModule module;
   public MathUtils field_0005;
   public float x;
   public float y;
   public UnidentifiedClass0000 field_0000;
   public GuiLockIconButton field_0001;
   public MapItemRenderer$1 field_0006;

   public CBModulePosition(AbstractModule var1, float var2, float var3) {
      this.module = var1;
      this.x = var2;
      this.y = var3;
   }
}
