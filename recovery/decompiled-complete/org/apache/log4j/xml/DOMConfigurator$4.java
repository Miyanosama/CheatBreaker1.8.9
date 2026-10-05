package org.apache.log4j.xml;

import java.io.Reader;
import javax.xml.parsers.DocumentBuilder;
import net.minecraft.util.ChatComponentScore;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import recovered.unidentified.UnidentifiedClass4855;

public class DOMConfigurator$4 implements DOMConfigurator$ParseAction {
   public DOMConfigurator this$0;
   public Reader val$reader;
   public ChatComponentScore field_0000;
   public UnidentifiedClass4855 field_0002;

   public Document parse(DocumentBuilder var1) {
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
      super();
   }
}
