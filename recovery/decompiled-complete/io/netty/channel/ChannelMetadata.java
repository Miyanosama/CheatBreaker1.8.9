package io.netty.channel;

import junit.swingui.TestTreeModel;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.world.gen.feature.WorldGenWaterlily;

public class ChannelMetadata {
   public boolean hasDisconnect;
   public WorldGenWaterlily __junk2252386022077442486;
   public TestTreeModel __junk5276612831167445709;
   public GuiRepair __junk3902275518312692020;

   public ChannelMetadata(boolean var1) {
      this.hasDisconnect = var1;
   }

   public boolean hasDisconnect() {
      return this.hasDisconnect;
   }
}
