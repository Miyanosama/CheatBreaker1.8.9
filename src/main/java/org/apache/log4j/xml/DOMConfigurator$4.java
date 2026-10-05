package org.apache.log4j.xml;

import java.io.Reader;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DOMConfigurator$4 implements DOMConfigurator.ParseAction {
   // $VF: synthetic field
   public DOMConfigurator this$0;
   // $VF: synthetic field
   public Reader val$reader;

   public Document parse(DocumentBuilder var1) throws org.xml.sax.SAXException, java.io.IOException {
      InputSource var2 = new InputSource(this.val$reader);
      var2.setSystemId("dummy://log4j.dtd");
      return var1.parse(var2);
   }

   public String toString() {
      return "reader [" + this.val$reader.toString() + "]";
   }

   public DOMConfigurator$4(DOMConfigurator var1, Reader var2) {
      this.this$0 = var1;
      this.val$reader = var2;
   }
}
