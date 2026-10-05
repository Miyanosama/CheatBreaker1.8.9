package org.apache.log4j.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.vecmath.TexCoord3f;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.command.server.CommandListBans;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.xml.DOMConfigurator;

public class JMSSink implements MessageListener {
   public CommandListBans field_0002;
   public BlockRedstoneTorch field_0004;
   public static Class class$org$apache$log4j$net$JMSSink;
   public TexCoord3f field_0003;
   public static Logger logger = Logger.getLogger(
      class$org$apache$log4j$net$JMSSink == null
         ? (class$org$apache$log4j$net$JMSSink = class$("org.apache.log4j.net.JMSSink"))
         : class$org$apache$log4j$net$JMSSink
   );

   public static Object lookup(Context var0, String var1) {
      try {
         return var0.lookup(var1);
      } catch (NameNotFoundException var3) {
         logger.error("Could not find name [" + var1 + "].");
         throw var3;
      }
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public static void usage(String var0) {
      System.err.println(var0);
      System.err
         .println(
            "Usage: java "
               + (class$org$apache$log4j$net$JMSSink == null
                     ? (class$org$apache$log4j$net$JMSSink = class$("org.apache.log4j.net.JMSSink"))
                     : class$org$apache$log4j$net$JMSSink)
                  .getName()
               + " TopicConnectionFactoryBindingName TopicBindingName username password configFile"
         );
      System.exit(1);
   }

   public static void main(String[] var0) {
      if (var0.length != 5) {
         usage("Wrong number of arguments.");
      }

      String var1 = var0[0];
      String var2 = var0[1];
      String var3 = var0[2];
      String var4 = var0[3];
      String var5 = var0[4];
      if (var5.endsWith(".xml")) {
         DOMConfigurator.configure(var5);
      } else {
         PropertyConfigurator.configure(var5);
      }

      new JMSSink(var1, var2, var3, var4);
      BufferedReader var6 = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Type \"exit\" to quit JMSSink.");

      String var7;
      do {
         var7 = var6.readLine();
      } while (!var7.equalsIgnoreCase("exit"));

      System.out.println("Exiting. Kill the application if it does not exit due to daemon threads.");
   }

   public JMSSink(String var1, String var2, String var3, String var4) {
      try {
         InitialContext var5 = new InitialContext();
         TopicConnectionFactory var6 = (TopicConnectionFactory)lookup(var5, var1);
         TopicConnection var7 = var6.createTopicConnection(var3, var4);
         var7.start();
         TopicSession var8 = var7.createTopicSession(false, 1);
         Topic var9 = (Topic)var5.lookup(var2);
         TopicSubscriber var10 = var8.createSubscriber(var9);
         var10.setMessageListener(this);
      } catch (JMSException var11) {
         logger.error("Could not read JMS message.", var11);
      } catch (NamingException var12) {
         logger.error("Could not read JMS message.", var12);
      } catch (RuntimeException var13) {
         logger.error("Could not read JMS message.", var13);
      }
   }

   public void onMessage(Message var1) {
      try {
         if (var1 instanceof ObjectMessage) {
            ObjectMessage var4 = (ObjectMessage)var1;
            LoggingEvent var2 = (LoggingEvent)var4.getObject();
            Logger var3 = Logger.getLogger(var2.getLoggerName());
            var3.callAppenders(var2);
         } else {
            logger.warn("Received message is of type " + var1.getJMSType() + ", was expecting ObjectMessage.");
         }
      } catch (JMSException var5) {
         logger.error("Exception thrown while processing incoming message.", var5);
      }
   }
}
