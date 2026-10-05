package com.cheatbreaker.client.util.dash;

import com.cheatbreaker.client.util.cbagent.CBAgentResources;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerHook;
import javazoom.jl.decoder.JavaLayerUtils;

public class DashHook implements JavaLayerHook {
   @Override
   public InputStream getResourceAsStream(String var1) {
      Class<JavaLayerUtils> var2 = JavaLayerUtils.class;
      java.io.InputStream var3 = var2.getResourceAsStream(var1);
      if (var3 == null) {
         String var4 = "javazoom/jl/decoder/" + var1;
         System.out.println("Retrieving: " + var4);
         if (CBAgentResources.existsBytes(var4)) {
            var3 = new ByteArrayInputStream(CBAgentResources.getBytesNative(var4));
         }
      }

      return (InputStream)var3;
   }
}
