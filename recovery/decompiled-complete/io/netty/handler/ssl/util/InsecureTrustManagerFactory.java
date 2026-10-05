package io.netty.handler.ssl.util;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.KeyStore;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import recovered.unidentified.UnidentifiedClass3810;

public class InsecureTrustManagerFactory extends SimpleTrustManagerFactory {
   public UnidentifiedClass3810 __junk1214916597591941779;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(InsecureTrustManagerFactory.class);
   public static TrustManagerFactory INSTANCE = new InsecureTrustManagerFactory();
   public static TrustManager tm = new InsecureTrustManagerFactory$1();

   @Override
   public void engineInit(KeyStore var1) {
   }

   @Override
   public void engineInit(ManagerFactoryParameters var1) {
   }

   @Override
   public TrustManager[] engineGetTrustManagers() {
      return new TrustManager[]{tm};
   }
}
