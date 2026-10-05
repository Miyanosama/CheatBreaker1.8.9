package org.apache.log4j.lf5.viewer;

import io.netty.handler.codec.http.cors.CorsConfig$1;
import javax.swing.JComponent;
import net.minecraft.client.player.inventory.ContainerLocalMenu;
import net.minecraft.world.chunk.storage.RegionFile;

public class LF5SwingUtils$1 implements Runnable {
   public RegionFile field_0001;
   public ContainerLocalMenu field_0003;
   public CorsConfig$1 field_0000;
   public JComponent val$component;

   public LF5SwingUtils$1(JComponent var1) {
      this.val$component = var1;
      super();
   }

   public void run() {
      this.val$component.repaint();
   }
}
