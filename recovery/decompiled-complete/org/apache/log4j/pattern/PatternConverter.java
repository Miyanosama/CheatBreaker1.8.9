package org.apache.log4j.pattern;

import io.netty.handler.codec.http.websocketx.WebSocket07FrameEncoder;

public abstract class PatternConverter {
   public String style;
   public String name;
   public WebSocket07FrameEncoder field_0000;

   public abstract void format(Object var1, StringBuffer var2);

   public String getStyleClass(Object var1) {
      return this.style;
   }

   public String getName() {
      return this.name;
   }

   public PatternConverter(String var1, String var2) {
      this.name = var1;
      this.style = var2;
   }
}
