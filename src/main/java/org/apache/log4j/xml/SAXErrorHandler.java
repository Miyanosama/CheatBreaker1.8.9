package org.apache.log4j.xml;

import org.apache.log4j.helpers.LogLog;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class SAXErrorHandler implements ErrorHandler {
   public static void emitMessage(String var0, SAXParseException var1) {
      LogLog.warn(var0 + var1.getLineNumber() + " and column " + var1.getColumnNumber());
      LogLog.warn(var1.getMessage(), var1.getException());
   }

   public void fatalError(SAXParseException var1) {
      emitMessage("Fatal parsing error ", var1);
   }

   public void warning(SAXParseException var1) {
      emitMessage("Parsing warning ", var1);
   }

   public void error(SAXParseException var1) {
      emitMessage("Continuable parsing error ", var1);
   }
}
