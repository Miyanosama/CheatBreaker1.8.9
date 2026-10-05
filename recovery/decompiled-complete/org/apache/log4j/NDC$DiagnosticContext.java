package org.apache.log4j;

import com.cheatbreaker.client.util.Vec2d;
import io.netty.util.internal.UnpaddedInternalThreadLocalMap;
import org.apache.log4j.xml.DOMConfigurator$4;

public class NDC$DiagnosticContext {
   public String message;
   public Vec2d field_0004;
   public UnpaddedInternalThreadLocalMap field_0001;
   public DOMConfigurator$4 field_0003;
   public String fullMessage;

   public NDC$DiagnosticContext(String var1, NDC$DiagnosticContext var2) {
      this.message = var1;
      if (var2 != null) {
         this.fullMessage = var2.fullMessage + ' ' + var1;
      } else {
         this.fullMessage = var1;
      }
   }
}
