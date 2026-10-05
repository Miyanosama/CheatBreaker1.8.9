package org.apache.log4j.xml;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DOMConfigurator$5 implements DOMConfigurator.ParseAction {
   // $VF: synthetic field
   public DOMConfigurator this$0;
   // $VF: synthetic field
   public InputSource val$inputSource;

   public String toString() {
      return "input source [" + this.val$inputSource.toString() + "]";
   }

   public Document parse(DocumentBuilder var1) throws org.xml.sax.SAXException, java.io.IOException {
      return var1.parse(this.val$inputSource);
   }

   public DOMConfigurator$5(DOMConfigurator var1, InputSource var2) {
      this.this$0 = var1;
      this.val$inputSource = var2;
   }
}
