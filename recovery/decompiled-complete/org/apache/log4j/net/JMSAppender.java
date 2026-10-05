package org.apache.log4j.net;

import java.util.Properties;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import net.minecraft.block.BlockButton;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class JMSAppender extends AppenderSkeleton {
   public String tcfBindingName;
   public TopicConnection topicConnection;
   public boolean locationInfo;
   public TopicPublisher topicPublisher;
   public BlockButton field_0006;
   public String securityPrincipalName;
   public String urlPkgPrefixes;
   public String initialContextFactoryName;
   public String securityCredentials;
   public String userName;
   public String password;
   public TopicSession topicSession;
   public String providerURL;
   public String topicBindingName;

   public void setURLPkgPrefixes(String var1) {
      this.urlPkgPrefixes = var1;
   }

   public void setSecurityCredentials(String var1) {
      this.securityCredentials = var1;
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public void activateOptions() {
      try {
         LogLog.debug("Getting initial context.");
         InitialContext var2;
         if (this.initialContextFactoryName != null) {
            Properties var3 = new Properties();
            var3.put("java.naming.factory.initial", this.initialContextFactoryName);
            if (this.providerURL != null) {
               var3.put("java.naming.provider.url", this.providerURL);
            } else {
               LogLog.warn("You have set InitialContextFactoryName option but not the ProviderURL. This is likely to cause problems.");
            }

            if (this.urlPkgPrefixes != null) {
               var3.put("java.naming.factory.url.pkgs", this.urlPkgPrefixes);
            }

            if (this.securityPrincipalName != null) {
               var3.put("java.naming.security.principal", this.securityPrincipalName);
               if (this.securityCredentials != null) {
                  var3.put("java.naming.security.credentials", this.securityCredentials);
               } else {
                  LogLog.warn("You have set SecurityPrincipalName option but not the SecurityCredentials. This is likely to cause problems.");
               }
            }

            var2 = new InitialContext(var3);
         } else {
            var2 = new InitialContext();
         }

         LogLog.debug("Looking up [" + this.tcfBindingName + "]");
         TopicConnectionFactory var1 = (TopicConnectionFactory)this.lookup(var2, this.tcfBindingName);
         LogLog.debug("About to create TopicConnection.");
         if (this.userName != null) {
            this.topicConnection = var1.createTopicConnection(this.userName, this.password);
         } else {
            this.topicConnection = var1.createTopicConnection();
         }

         LogLog.debug("Creating TopicSession, non-transactional, in AUTO_ACKNOWLEDGE mode.");
         this.topicSession = this.topicConnection.createTopicSession(false, 1);
         LogLog.debug("Looking up topic name [" + this.topicBindingName + "].");
         Topic var7 = (Topic)this.lookup(var2, this.topicBindingName);
         LogLog.debug("Creating TopicPublisher.");
         this.topicPublisher = this.topicSession.createPublisher(var7);
         LogLog.debug("Starting TopicConnection.");
         this.topicConnection.start();
         var2.close();
      } catch (JMSException var4) {
         this.errorHandler.error("Error while activating options for appender named [" + this.name + "].", var4, 0);
      } catch (NamingException var5) {
         this.errorHandler.error("Error while activating options for appender named [" + this.name + "].", var5, 0);
      } catch (RuntimeException var6) {
         this.errorHandler.error("Error while activating options for appender named [" + this.name + "].", var6, 0);
      }
   }

   public String getInitialContextFactoryName() {
      return this.initialContextFactoryName;
   }

   public void setPassword(String var1) {
      this.password = var1;
   }

   public void setSecurityPrincipalName(String var1) {
      this.securityPrincipalName = var1;
   }

   public void setTopicConnectionFactoryBindingName(String var1) {
      this.tcfBindingName = var1;
   }

   public void setInitialContextFactoryName(String var1) {
      this.initialContextFactoryName = var1;
   }

   public String getURLPkgPrefixes() {
      return this.urlPkgPrefixes;
   }

   public synchronized void close() {
      if (!this.closed) {
         LogLog.debug("Closing appender [" + this.name + "].");
         this.closed = true;

         try {
            if (this.topicSession != null) {
               this.topicSession.close();
            }

            if (this.topicConnection != null) {
               this.topicConnection.close();
            }
         } catch (JMSException var2) {
            LogLog.error("Error while closing JMSAppender [" + this.name + "].", var2);
         } catch (RuntimeException var3) {
            LogLog.error("Error while closing JMSAppender [" + this.name + "].", var3);
         }

         this.topicPublisher = null;
         this.topicSession = null;
         this.topicConnection = null;
      }
   }

   public TopicSession getTopicSession() {
      return this.topicSession;
   }

   public void setTopicBindingName(String var1) {
      this.topicBindingName = var1;
   }

   public boolean checkEntryConditions() {
      String var1 = null;
      if (this.topicConnection == null) {
         var1 = "No TopicConnection";
      } else if (this.topicSession == null) {
         var1 = "No TopicSession";
      } else if (this.topicPublisher == null) {
         var1 = "No TopicPublisher";
      }

      if (var1 != null) {
         this.errorHandler.error(var1 + " for JMSAppender named [" + this.name + "].");
         return false;
      } else {
         return true;
      }
   }

   public String getTopicConnectionFactoryBindingName() {
      return this.tcfBindingName;
   }

   public TopicPublisher getTopicPublisher() {
      return this.topicPublisher;
   }

   public String getUserName() {
      return this.userName;
   }

   public TopicConnection getTopicConnection() {
      return this.topicConnection;
   }

   public void setUserName(String var1) {
      this.userName = var1;
   }

   public Object lookup(Context var1, String var2) {
      try {
         return var1.lookup(var2);
      } catch (NameNotFoundException var4) {
         LogLog.error("Could not find name [" + var2 + "].");
         throw var4;
      }
   }

   public String getSecurityCredentials() {
      return this.securityCredentials;
   }

   public String getTopicBindingName() {
      return this.topicBindingName;
   }

   public String getSecurityPrincipalName() {
      return this.securityPrincipalName;
   }

   public boolean requiresLayout() {
      return false;
   }

   public String getPassword() {
      return this.password;
   }

   public void append(LoggingEvent var1) {
      if (this.checkEntryConditions()) {
         try {
            ObjectMessage var2 = this.topicSession.createObjectMessage();
            if (this.locationInfo) {
               var1.getLocationInformation();
            }

            var2.setObject(var1);
            this.topicPublisher.publish(var2);
         } catch (JMSException var3) {
            this.errorHandler.error("Could not publish message in JMSAppender [" + this.name + "].", var3, 0);
         } catch (RuntimeException var4) {
            this.errorHandler.error("Could not publish message in JMSAppender [" + this.name + "].", var4, 0);
         }
      }
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public void setProviderURL(String var1) {
      this.providerURL = var1;
   }

   public String getProviderURL() {
      return this.providerURL;
   }
}
