package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;
import junit.swingui.FailureRunView$FailureListCellRenderer;
import net.minecraft.block.BlockClay;
import net.minecraft.block.BlockFlowerPot$1;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.client.multiplayer.WorldClient;
import net.optifine.entity.model.ModelAdapterBook;

public class LoadWorldEvent extends EventBus$Event {
   public BlockClay field_0004;
   public ModelAdapterBook field_0003;
   public FailureRunView$FailureListCellRenderer field_0001;
   public WorldClient field_0005;
   public BlockFlowerPot$1 field_0000;
   public BlockTripWireHook field_0002;

   public LoadWorldEvent(WorldClient var1) {
      this.field_0005 = var1;
   }

   public WorldClient method_29800() {
      return this.field_0005;
   }
}
