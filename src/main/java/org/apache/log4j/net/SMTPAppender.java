package org.apache.log4j.net;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CyclicBuffer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.spi.TriggeringEventEvaluator;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.xml.UnrecognizedElementHandler;
import org.w3c.dom.Element;

public class SMTPAppender extends AppenderSkeleton implements UnrecognizedElementHandler {
   public int smtpPort = -1;
   public CyclicBuffer cb;
   public String bcc;
   public static Class class$org$apache$log4j$spi$TriggeringEventEvaluator;
   public String smtpHost;
   public String smtpProtocol;
   public String to;
   public boolean smtpDebug = false;
   public Message msg;
   public String smtpUsername;
   public boolean locationInfo;
   public String smtpPassword;
   public boolean sendOnClose;
   public String cc;
   public int bufferSize = 512;
   public String from;
   public TriggeringEventEvaluator evaluator;
   public String subject;
   public String replyTo;

   public void activateOptions() {
      Session var1 = this.createSession();
      this.msg = new MimeMessage(var1);

      try {
         this.addressMessage(this.msg);
         if (this.subject != null) {
            try {
               this.msg.setSubject(MimeUtility.encodeText(this.subject, "UTF-8", null));
            } catch (UnsupportedEncodingException var3) {
               LogLog.error("Unable to encode SMTP subject", var3);
            }
         }
      } catch (MessagingException var4) {
         LogLog.error("Could not activate SMTPAppender options.", var4);
      }

      if (this.evaluator instanceof OptionHandler) {
         ((OptionHandler)this.evaluator).activateOptions();
      }
   }

   public boolean getSendOnClose() {
      return this.sendOnClose;
   }

   public void setSMTPPort(int var1) {
      this.smtpPort = var1;
   }

   public int getSMTPPort() {
      return this.smtpPort;
   }

   public InternetAddress getAddress(String var1) {
      try {
         return new InternetAddress(var1);
      } catch (AddressException var3) {
         this.errorHandler.error("Could not parse address [" + var1 + "].", var3, 6);
         return null;
      }
   }

   public boolean parseUnrecognizedElement(Element var1, Properties var2) throws java.lang.Exception {
      if ("triggeringPolicy".equals(var1.getNodeName())) {
         Object var3 = DOMConfigurator.parseElement(
            var1,
            var2,
            class$org$apache$log4j$spi$TriggeringEventEvaluator == null
               ? (class$org$apache$log4j$spi$TriggeringEventEvaluator = class$("org.apache.log4j.spi.TriggeringEventEvaluator"))
               : class$org$apache$log4j$spi$TriggeringEventEvaluator
         );
         if (var3 instanceof TriggeringEventEvaluator) {
            this.setEvaluator((TriggeringEventEvaluator)var3);
         }

         return true;
      } else {
         return false;
      }
   }

   public String getReplyTo() {
      return this.replyTo;
   }

   public TriggeringEventEvaluator getEvaluator() {
      return this.evaluator;
   }

   public void setTo(String var1) {
      this.to = var1;
   }

   public void addressMessage(Message var1) throws javax.mail.MessagingException {
      if (this.from != null) {
         var1.setFrom(this.getAddress(this.from));
      } else {
         var1.setFrom();
      }

      if (this.replyTo != null && this.replyTo.length() > 0) {
         var1.setReplyTo(this.parseAddress(this.replyTo));
      }

      if (this.to != null && this.to.length() > 0) {
         var1.setRecipients(RecipientType.TO, this.parseAddress(this.to));
      }

      if (this.cc != null && this.cc.length() > 0) {
         var1.setRecipients(RecipientType.CC, this.parseAddress(this.cc));
      }

      if (this.bcc != null && this.bcc.length() > 0) {
         var1.setRecipients(RecipientType.BCC, this.parseAddress(this.bcc));
      }
   }

   public void setFrom(String var1) {
      this.from = var1;
   }

   public boolean getSMTPDebug() {
      return this.smtpDebug;
   }

   public String getSMTPUsername() {
      return this.smtpUsername;
   }

   // $VF: synthetic method
   public static String access$100(SMTPAppender var0) {
      return var0.smtpPassword;
   }

   public void setCc(String var1) {
      this.cc = var1;
   }

   public void setReplyTo(String var1) {
      this.replyTo = var1;
   }

   public String getSMTPProtocol() {
      return this.smtpProtocol;
   }

   public String getSubject() {
      return this.subject;
   }

   public Session createSession() {
      Properties var1 = null;

      try {
         var1 = new Properties(System.getProperties());
      } catch (SecurityException var5) {
         var1 = new Properties();
      }

      String var2 = "mail.smtp";
      if (this.smtpProtocol != null) {
         var1.put("mail.transport.protocol", this.smtpProtocol);
         var2 = "mail." + this.smtpProtocol;
      }

      if (this.smtpHost != null) {
         var1.put(var2 + ".host", this.smtpHost);
      }

      if (this.smtpPort > 0) {
         var1.put(var2 + ".port", String.valueOf(this.smtpPort));
      }

      SMTPAppender$1 var3 = null;
      if (this.smtpPassword != null && this.smtpUsername != null) {
         var1.put(var2 + ".auth", "true");
         var3 = new SMTPAppender$1(this);
      }

      Session var4 = Session.getInstance(var1, var3);
      if (this.smtpProtocol != null) {
         var4.setProtocolForAddress("rfc822", this.smtpProtocol);
      }

      if (this.smtpDebug) {
         var4.setDebug(this.smtpDebug);
      }

      return var4;
   }

   public void setSMTPProtocol(String var1) {
      this.smtpProtocol = var1;
   }

   public String getEvaluatorClass() {
      return this.evaluator == null ? null : this.evaluator.getClass().getName();
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public void sendBuffer() {
      try {
         String var1 = this.formatBody();
         boolean var2 = true;

         for (int var3 = 0; var3 < var1.length() && var2; var3++) {
            var2 = var1.charAt(var3) <= 127;
         }

         MimeBodyPart var10;
         if (var2) {
            var10 = new MimeBodyPart();
            var10.setContent(var1, this.layout.getContentType());
         } else {
            try {
               ByteArrayOutputStream var4 = new ByteArrayOutputStream();
               OutputStreamWriter var12 = new OutputStreamWriter(MimeUtility.encode(var4, "quoted-printable"), "UTF-8");
               var12.write(var1);
               var12.close();
               InternetHeaders var13 = new InternetHeaders();
               var13.setHeader("Content-Type", this.layout.getContentType() + "; charset=UTF-8");
               var13.setHeader("Content-Transfer-Encoding", "quoted-printable");
               var10 = new MimeBodyPart(var13, var4.toByteArray());
            } catch (Exception var7) {
               StringBuffer var5 = new StringBuffer(var1);

               for (int var6 = 0; var6 < var5.length(); var6++) {
                  if (var5.charAt(var6) >= 128) {
                     var5.setCharAt(var6, '?');
                  }
               }

               var10 = new MimeBodyPart();
               var10.setContent(var5.toString(), this.layout.getContentType());
            }
         }

         MimeMultipart var11 = new MimeMultipart();
         var11.addBodyPart(var10);
         this.msg.setContent(var11);
         this.msg.setSentDate(new Date());
         Transport.send(this.msg);
      } catch (MessagingException var8) {
         LogLog.error("Error occured while sending e-mail notification.", var8);
      } catch (RuntimeException var9) {
         LogLog.error("Error occured while sending e-mail notification.", var9);
      }
   }

   public String getTo() {
      return this.to;
   }

   public String formatBody() {
      StringBuffer var1 = new StringBuffer();
      String var2 = this.layout.E_();
      if (var2 != null) {
         var1.append(var2);
      }

      int var3 = this.cb.length();

      for (int var4 = 0; var4 < var3; var4++) {
         LoggingEvent var5 = this.cb.get();
         var1.append(this.layout.format(var5));
         if (this.layout.ignoresThrowable()) {
            String[] var6 = var5.getThrowableStrRep();
            if (var6 != null) {
               for (int var7 = 0; var7 < var6.length; var7++) {
                  var1.append(var6[var7]);
                  var1.append(Layout.LINE_SEP);
               }
            }
         }
      }

      var2 = this.layout.getFooter();
      if (var2 != null) {
         var1.append(var2);
      }

      return var1.toString();
   }

   public void setBcc(String var1) {
      this.bcc = var1;
   }

   public SMTPAppender(TriggeringEventEvaluator var1) {
      this.locationInfo = false;
      this.sendOnClose = false;
      this.cb = new CyclicBuffer(this.bufferSize);
      this.evaluator = var1;
   }

   public synchronized void close() {
      this.closed = true;
      if (this.sendOnClose && this.cb.length() > 0) {
         this.sendBuffer();
      }
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public void setSendOnClose(boolean var1) {
      this.sendOnClose = var1;
   }

   public void setSubject(String var1) {
      this.subject = var1;
   }

   public String getFrom() {
      return this.from;
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public void append(LoggingEvent var1) {
      if (this.checkEntryConditions()) {
         var1.getThreadName();
         var1.getNDC();
         var1.getMDCCopy();
         if (this.locationInfo) {
            var1.getLocationInformation();
         }

         var1.getRenderedMessage();
         var1.getThrowableStrRep();
         this.cb.add(var1);
         if (this.evaluator.isTriggeringEvent(var1)) {
            this.sendBuffer();
         }
      }
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public boolean requiresLayout() {
      return true;
   }

   public String getSMTPPassword() {
      return this.smtpPassword;
   }

   // $VF: synthetic method
   public static String access$000(SMTPAppender var0) {
      return var0.smtpUsername;
   }

   public SMTPAppender() {
      this(new DefaultEvaluator());
   }

   public void setSMTPPassword(String var1) {
      this.smtpPassword = var1;
   }

   public String getBcc() {
      return this.bcc;
   }

   public String getCc() {
      return this.cc;
   }

   public void setEvaluatorClass(String var1) {
      this.evaluator = (TriggeringEventEvaluator)OptionConverter.instantiateByClassName(
         var1,
         class$org$apache$log4j$spi$TriggeringEventEvaluator == null
            ? (class$org$apache$log4j$spi$TriggeringEventEvaluator = class$("org.apache.log4j.spi.TriggeringEventEvaluator"))
            : class$org$apache$log4j$spi$TriggeringEventEvaluator,
         this.evaluator
      );
   }

   public void setSMTPDebug(boolean var1) {
      this.smtpDebug = var1;
   }

   public void setSMTPUsername(String var1) {
      this.smtpUsername = var1;
   }

   public void setEvaluator(TriggeringEventEvaluator var1) {
      if (var1 == null) {
         throw new NullPointerException("trigger");
      } else {
         this.evaluator = var1;
      }
   }

   public InternetAddress[] parseAddress(String var1) {
      try {
         return InternetAddress.parse(var1, true);
      } catch (AddressException var3) {
         this.errorHandler.error("Could not parse address [" + var1 + "].", var3, 6);
         return null;
      }
   }

   public boolean checkEntryConditions() {
      if (this.msg == null) {
         this.errorHandler.error("Message object not configured.");
         return false;
      } else if (this.evaluator == null) {
         this.errorHandler.error("No TriggeringEventEvaluator is set for appender [" + this.name + "].");
         return false;
      } else if (this.layout == null) {
         this.errorHandler.error("No layout set for appender named [" + this.name + "].");
         return false;
      } else {
         return true;
      }
   }

   public String getSMTPHost() {
      return this.smtpHost;
   }

   public void setSMTPHost(String var1) {
      this.smtpHost = var1;
   }

   public void setBufferSize(int var1) {
      this.bufferSize = var1;
      this.cb.resize(var1);
   }
}
