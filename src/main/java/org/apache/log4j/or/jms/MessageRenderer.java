package org.apache.log4j.or.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.or.ObjectRenderer;

public class MessageRenderer implements ObjectRenderer {
   public String doRender(Object var1) {
      if (var1 instanceof Message) {
         StringBuffer var2 = new StringBuffer();
         Message var3 = (Message)var1;

         try {
            var2.append("DeliveryMode=");
            switch (var3.getJMSDeliveryMode()) {
               case 1:
                  var2.append("NON_PERSISTENT");
                  break;
               case 2:
                  var2.append("PERSISTENT");
                  break;
               default:
                  var2.append("UNKNOWN");
            }

            var2.append(", CorrelationID=");
            var2.append(var3.getJMSCorrelationID());
            var2.append(", Destination=");
            var2.append(var3.getJMSDestination());
            var2.append(", Expiration=");
            var2.append(var3.getJMSExpiration());
            var2.append(", MessageID=");
            var2.append(var3.getJMSMessageID());
            var2.append(", Priority=");
            var2.append(var3.getJMSPriority());
            var2.append(", Redelivered=");
            var2.append(var3.getJMSRedelivered());
            var2.append(", ReplyTo=");
            var2.append(var3.getJMSReplyTo());
            var2.append(", Timestamp=");
            var2.append(var3.getJMSTimestamp());
            var2.append(", Type=");
            var2.append(var3.getJMSType());
         } catch (JMSException var5) {
            LogLog.error("Could not parse Message.", var5);
         }

         return var2.toString();
      } else {
         return var1.toString();
      }
   }
}
