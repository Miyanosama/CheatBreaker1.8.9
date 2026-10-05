package io.netty.channel;

import junit.swingui.TestTreeModel;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.world.gen.feature.WorldGenWaterlily;

public class ChannelMetadata {
   public boolean hasDisconnect;

   public ChannelMetadata(boolean var1) {
      this.hasDisconnect = var1;
   }

   public boolean hasDisconnect() {
      return this.hasDisconnect;
   }
}
