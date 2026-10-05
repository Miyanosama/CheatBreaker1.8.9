package org.apache.log4j.xml;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DOMConfigurator$3 implements DOMConfigurator.ParseAction {
   // $VF: synthetic field
   public DOMConfigurator this$0;
   // $VF: synthetic field
   public InputStream val$inputStream;

   public String toString() {
      return "input stream [" + this.val$inputStream.toString() + "]";
   }

   public Document parse(DocumentBuilder var1) throws org.xml.sax.SAXException, java.io.IOException {
      InputSource var2 = new InputSource(this.val$inputStream);
      var2.setSystemId("dummy://log4j.dtd");
      return var1.parse(var2);
   }

   public DOMConfigurator$3(DOMConfigurator var1, InputStream var2) {
      this.this$0 = var1;
      this.val$inputStream = var2;
   }
}
