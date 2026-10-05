package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;
import net.minecraft.block.BlockTallGrass$EnumType;
import net.minecraft.crash.CrashReport$7;
import net.optifine.gui.GuiScreenOF;

public class PluginMessageEvent extends EventBus$Event {
   public BlockTallGrass$EnumType field_0003;
   public CrashReport$7 field_0002;
   public GuiScreenOF field_0001;
   public byte[] field_0004;
   public String field_0000;

   public String method_26236() {
      return this.field_0000;
   }

   public byte[] method_26237() {
      return this.field_0004;
   }

   public PluginMessageEvent(String var1, byte[] var2) {
      this.field_0000 = var1;
      this.field_0004 = var2;
   }
}
