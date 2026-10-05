package org.apache.log4j.varia;

import java.io.InputStream;
import java.net.URL;
import net.minecraft.client.Minecraft$3;
import net.minecraft.client.gui.GuiResourcePackSelected;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.chainsaw.ExitAction;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class ReloadingPropertyConfigurator implements Configurator {
   public GuiResourcePackSelected field_0001;
   public ExitAction field_0003;
   public PropertyConfigurator delegate = new PropertyConfigurator();
   public Minecraft$3 field_0002;

   public void doConfigure(InputStream var1, LoggerRepository var2) {
   }

   public void doConfigure(URL var1, LoggerRepository var2) {
   }
}
