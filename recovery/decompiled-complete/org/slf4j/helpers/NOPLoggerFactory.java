package org.slf4j.helpers;

import net.minecraft.client.renderer.GlStateManager$BlendState;
import net.minecraft.world.WorldSettings$GameType;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import recovered.unidentified.UnidentifiedClass4511;

public class NOPLoggerFactory implements ILoggerFactory {
   public GlStateManager$BlendState field_0001;
   public UnidentifiedClass4511 field_0002;
   public WorldSettings$GameType field_0000;

   @Override
   public Logger getLogger(String var1) {
      return NOPLogger.NOP_LOGGER;
   }
}
