package org.apache.log4j.varia;

import java.io.InputStream;
import java.net.URL;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class ReloadingPropertyConfigurator implements Configurator {
   public PropertyConfigurator delegate = new PropertyConfigurator();

   public void doConfigure(InputStream var1, LoggerRepository var2) {
   }

   public void doConfigure(URL var1, LoggerRepository var2) {
   }
}
