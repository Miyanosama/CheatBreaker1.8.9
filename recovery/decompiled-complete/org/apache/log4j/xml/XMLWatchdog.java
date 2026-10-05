package org.apache.log4j.xml;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.GlStateManager$PolygonOffsetState;
import net.minecraft.inventory.SlotMerchantResult;
import net.optifine.TextureAnimationFrame;
import net.optifine.shaders.ShadersTex;
import org.apache.log4j.LogManager;
import org.apache.log4j.helpers.FileWatchdog;

public class XMLWatchdog extends FileWatchdog {
   public GuiChat field_0001;
   public ShadersTex field_0002;
   public GlStateManager$PolygonOffsetState field_0003;
   public SlotMerchantResult field_0000;
   public TextureAnimationFrame field_0004;

   public void doOnChange() {
      new DOMConfigurator().doConfigure(this.filename, LogManager.getLoggerRepository());
   }

   public XMLWatchdog(String var1) {
      super(var1);
   }
}
