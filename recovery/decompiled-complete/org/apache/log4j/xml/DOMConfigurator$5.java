package org.apache.log4j.xml;

import javax.xml.parsers.DocumentBuilder;
import net.minecraft.client.renderer.entity.RenderMooshroom;
import net.optifine.entity.model.ModelAdapterGhast;
import org.json.JSONException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DOMConfigurator$5 implements DOMConfigurator$ParseAction {
   public DOMConfigurator this$0;
   public JSONException field_0004;
   public ModelAdapterGhast field_0001;
   public InputSource val$inputSource;
   public RenderMooshroom field_0000;

   public String toString() {
      return "input source [" + this.val$inputSource.toString() + "]";
   }

   public Document parse(DocumentBuilder var1) {
      return var1.parse(this.val$inputSource);
   }

   public DOMConfigurator$5(DOMConfigurator var1, InputSource var2) {
      this.this$0 = var1;
      this.val$inputSource = var2;
      super();
   }
}
