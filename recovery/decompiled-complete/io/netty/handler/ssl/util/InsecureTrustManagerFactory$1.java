package io.netty.handler.ssl.util;

import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.util.friend.FriendsManager;
import io.netty.util.internal.EmptyArrays;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import org.apache.log4j.CategoryKey;

public class InsecureTrustManagerFactory$1 implements X509TrustManager {
   public CategoryKey __junk5507865015740785479;
   public FriendsManager __junk6691692580524750963;
   public GuiDrawEvent __junk9037852085336575255;

   @Override
   public void checkServerTrusted(X509Certificate[] var1, String var2) {
      InsecureTrustManagerFactory.access$000().debug("Accepting a server certificate: " + var1[0].getSubjectDN());
   }

   @Override
   public X509Certificate[] getAcceptedIssuers() {
      return EmptyArrays.EMPTY_X509_CERTIFICATES;
   }

   @Override
   public void checkClientTrusted(X509Certificate[] var1, String var2) {
      InsecureTrustManagerFactory.access$000().debug("Accepting a client certificate: " + var1[0].getSubjectDN());
   }
}
