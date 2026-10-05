package net.optifine.http;

import java.util.Map;
import javazoom.jl.decoder.DecoderException;

public class HttpPipeline$1 implements HttpListener {
   public DecoderException field_0001;

   @Override
   public void finished(HttpRequest var1, HttpResponse var2) {
      synchronized (this.val$map) {
         this.val$map.put("Response", var2);
         this.val$map.notifyAll();
      }
   }

   public HttpPipeline$1(Map var1) {
      this.val$map = var1;
      super();
   }

   @Override
   public void failed(HttpRequest var1, Exception var2) {
      synchronized (this.val$map) {
         this.val$map.put("Exception", var2);
         this.val$map.notifyAll();
      }
   }
}
