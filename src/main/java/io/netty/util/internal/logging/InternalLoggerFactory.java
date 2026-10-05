package io.netty.util.internal.logging;

public abstract class InternalLoggerFactory {
   public static volatile InternalLoggerFactory defaultFactory = newDefaultFactory(InternalLoggerFactory.class.getName());

   public static InternalLogger getInstance(Class<?> var0) {
      return getInstance(var0.getName());
   }

   public abstract InternalLogger newInstance(String var1);

   public static InternalLoggerFactory newDefaultFactory(String var0) {
      Object var1;
      try {
         var1 = new Slf4JLoggerFactory(true);
         ((InternalLoggerFactory)var1).newInstance(var0).debug("Using SLF4J as the default logging framework");
      } catch (Throwable var5) {
         try {
            var1 = new Log4JLoggerFactory();
            ((InternalLoggerFactory)var1).newInstance(var0).debug("Using Log4J as the default logging framework");
         } catch (Throwable var4) {
            var1 = new JdkLoggerFactory();
            ((InternalLoggerFactory)var1).newInstance(var0).debug("Using java.util.logging as the default logging framework");
         }
      }

      return (InternalLoggerFactory)var1;
   }

   public static InternalLogger getInstance(String var0) {
      return getDefaultFactory().newInstance(var0);
   }

   public static void setDefaultFactory(InternalLoggerFactory var0) {
      if (var0 == null) {
         throw new NullPointerException("defaultFactory");
      } else {
         defaultFactory = var0;
      }
   }

   public static InternalLoggerFactory getDefaultFactory() {
      return defaultFactory;
   }
}
