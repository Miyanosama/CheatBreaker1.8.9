package org.apache.log4j.spi;

public class DefaultRepositorySelector implements RepositorySelector {
   public LoggerRepository repository;

   public LoggerRepository getLoggerRepository() {
      return this.repository;
   }

   public DefaultRepositorySelector(LoggerRepository var1) {
      this.repository = var1;
   }
}
