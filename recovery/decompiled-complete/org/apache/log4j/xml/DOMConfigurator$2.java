package org.apache.log4j.xml;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import net.minecraft.nbt.NBTTagEnd;
import org.java_websocket.exceptions.NotSendableException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DOMConfigurator$2 implements DOMConfigurator$ParseAction {
   public DOMConfigurator this$0;
   public NBTTagEnd field_0003;
   public URL val$url;
   public NotSendableException field_0002;

   public String toString() {
      return "url [" + this.val$url.toString() + "]";
   }

   public Document parse(DocumentBuilder var1) {
      URLConnection var2 = this.val$url.openConnection();
      var2.setUseCaches(false);
      InputStream var3 = var2.getInputStream();

      Document var5;
      try {
         InputSource var4 = new InputSource(var3);
         var4.setSystemId(this.val$url.toString());
         var5 = var1.parse(var4);
      } finally {
         var3.close();
      }

      return var5;
   }

   public DOMConfigurator$2(DOMConfigurator var1, URL var2) {
      this.this$0 = var1;
      this.val$url = var2;
      super();
   }
}
