package org.apache.log4j;

public class BasicConfigurator {
   public static void configure() {
      Logger var0 = Logger.getRootLogger();
      var0.addAppender(new ConsoleAppender(new PatternLayout("%r [%t] %p %c %x - %m%n")));
   }

   public static void configure(Appender var0) {
      Logger var1 = Logger.getRootLogger();
      var1.addAppender(var0);
   }

   public static void resetConfiguration() {
      LogManager.resetConfiguration();
   }
}
