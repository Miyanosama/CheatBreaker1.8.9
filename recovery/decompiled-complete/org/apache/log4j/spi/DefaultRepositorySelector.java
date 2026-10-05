package org.apache.log4j.spi;

import net.minecraft.client.model.ModelEnderman;

public class DefaultRepositorySelector implements RepositorySelector {
   public ModelEnderman field_0000;
   public LoggerRepository repository;

   public LoggerRepository getLoggerRepository() {
      return this.repository;
   }

   public DefaultRepositorySelector(LoggerRepository var1) {
      this.repository = var1;
   }
}
