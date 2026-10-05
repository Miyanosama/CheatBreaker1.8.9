package io.netty.handler.ssl.util;

import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.util.friend.FriendsManager;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.log4j.CategoryKey;
import com.cheatbreaker.client.event.type.PlayerModelRenderEvent;

public class InsecureTrustManagerFactory extends SimpleTrustManagerFactory {
   public static InternalLogger logger = InternalLoggerFactory.getInstance(InsecureTrustManagerFactory.class);
   public static TrustManagerFactory INSTANCE = new InsecureTrustManagerFactory();
   public static TrustManager tm = new X509TrustManager() {

      @Override
      public void checkServerTrusted(X509Certificate[] var1, String var2) {
         InsecureTrustManagerFactory.logger.debug("Accepting a server certificate: " + var1[0].getSubjectDN());
      }

      @Override
      public X509Certificate[] getAcceptedIssuers() {
         return EmptyArrays.EMPTY_X509_CERTIFICATES;
      }

      @Override
      public void checkClientTrusted(X509Certificate[] var1, String var2) {
         InsecureTrustManagerFactory.logger.debug("Accepting a client certificate: " + var1[0].getSubjectDN());
      }
   };

   @Override
   public void engineInit(KeyStore var1) throws java.lang.Exception {
   }

   @Override
   public void engineInit(ManagerFactoryParameters var1) throws java.lang.Exception {
   }

   @Override
   public TrustManager[] engineGetTrustManagers() {
      return new TrustManager[]{tm};
   }
}
