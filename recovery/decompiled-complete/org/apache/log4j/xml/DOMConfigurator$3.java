package org.apache.log4j.xml;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import net.minecraft.client.gui.achievement.GuiStats$Stats;
import net.minecraft.client.model.ModelBlaze;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import recovered.unidentified.UnidentifiedClass3499;

public class DOMConfigurator$3 implements DOMConfigurator$ParseAction {
   public DOMConfigurator this$0;
   public InputStream val$inputStream;
   public ModelBlaze field_0001;
   public UnidentifiedClass3499 field_0003;
   public GuiStats$Stats field_0000;

   public String toString() {
      return "input stream [" + this.val$inputStream.toString() + "]";
   }

   public Document parse(DocumentBuilder var1) {
      InputSource var2 = new InputSource(this.val$inputStream);
      var2.setSystemId("dummy://log4j.dtd");
      return var1.parse(var2);
   }

   public DOMConfigurator$3(DOMConfigurator var1, InputStream var2) {
      this.this$0 = var1;
      this.val$inputStream = var2;
      super();
   }
}
