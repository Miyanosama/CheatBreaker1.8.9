package io.netty.handler.ssl.util;

import io.netty.util.concurrent.FastThreadLocal;
import java.security.KeyStore;
import java.security.Provider;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import net.minecraft.util.ObjectIntIdentityMap;

public abstract class SimpleTrustManagerFactory extends TrustManagerFactory {
   public static FastThreadLocal<SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi> CURRENT_SPI = new SimpleTrustManagerFactory$2();
   public static Provider PROVIDER = new SimpleTrustManagerFactory$1("", 0.0, "");
   public ObjectIntIdentityMap __junk1044657916926577920;

   public SimpleTrustManagerFactory() {
      this("");
   }

   public abstract void engineInit(ManagerFactoryParameters var1);

   public abstract TrustManager[] engineGetTrustManagers();

   public SimpleTrustManagerFactory(String var1) {
      super(CURRENT_SPI.get(), PROVIDER, var1);
      CURRENT_SPI.get().init(this);
      CURRENT_SPI.remove();
      if (var1 == null) {
         throw new NullPointerException("name");
      }
   }

   public abstract void engineInit(KeyStore var1);
}
