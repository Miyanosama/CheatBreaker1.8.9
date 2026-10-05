package org.json;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.ImageBufferDownload;
import org.apache.log4j.rewrite.MapRewritePolicy;

public class JSONPointer$Builder {
   public List<String> refTokens = new ArrayList<>();
   public MapRewritePolicy field_0002;
   public ImageBufferDownload field_0000;

   public JSONPointer$Builder append(int var1) {
      this.refTokens.add(String.valueOf(var1));
      return this;
   }

   public JSONPointer$Builder append(String var1) {
      if (var1 == null) {
         throw new NullPointerException("token cannot be null");
      } else {
         this.refTokens.add(var1);
         return this;
      }
   }

   public JSONPointer build() {
      return new JSONPointer(this.refTokens);
   }
}
