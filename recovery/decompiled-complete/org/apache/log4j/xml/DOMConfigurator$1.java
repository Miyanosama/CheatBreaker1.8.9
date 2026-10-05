package org.apache.log4j.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class DOMConfigurator$1 implements DOMConfigurator$ParseAction {
   public String val$filename;
   public DOMConfigurator this$0;

   public DOMConfigurator$1(DOMConfigurator var1, String var2) {
      this.this$0 = var1;
      this.val$filename = var2;
      super();
   }

   public String toString() {
      return "file [" + this.val$filename + "]";
   }

   public Document parse(DocumentBuilder var1) {
      return var1.parse(new File(this.val$filename));
   }
}
