package io.netty.handler.codec.http;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackGui;
import io.netty.channel.embedded.EmbeddedChannel;
import javazoom.jl.player.advanced.jlap$1;

public class HttpContentEncoder$Result {
   public ResourcePackGui __junk2398054652027869292;
   public EmbeddedChannel contentEncoder;
   public jlap$1 __junk6219262845669765638;
   public String targetContentEncoding;

   public HttpContentEncoder$Result(String var1, EmbeddedChannel var2) {
      if (var1 == null) {
         throw new NullPointerException("targetContentEncoding");
      } else if (var2 == null) {
         throw new NullPointerException("contentEncoder");
      } else {
         this.targetContentEncoding = var1;
         this.contentEncoder = var2;
      }
   }

   public EmbeddedChannel contentEncoder() {
      return this.contentEncoder;
   }

   public String targetContentEncoding() {
      return this.targetContentEncoding;
   }
}
