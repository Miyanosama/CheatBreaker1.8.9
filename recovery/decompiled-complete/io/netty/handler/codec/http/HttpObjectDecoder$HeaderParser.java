package io.netty.handler.codec.http;

import com.cheatbreaker.client.ui.overlay.element.ElementListElement;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.compression.JdkZlibDecoder$1;
import io.netty.util.internal.AppendableCharSequence;
import net.minecraft.client.particle.EntityHugeExplodeFX$Factory;
import net.minecraft.util.Cartesian$1;

public class HttpObjectDecoder$HeaderParser implements ByteBufProcessor {
   public EntityHugeExplodeFX$Factory __junk4359486830899650545;
   public JdkZlibDecoder$1 __junk648037261851355152;
   public Cartesian$1 __junk1758967683244241024;
   public ElementListElement __junk1745258044763973835;
   public AppendableCharSequence seq;

   @Override
   public boolean process(byte var1) {
      char var2 = (char)var1;
      HttpObjectDecoder.access$008(this.this$0);
      if (var2 == '\r') {
         return true;
      } else if (var2 == '\n') {
         return false;
      } else if (HttpObjectDecoder.access$000(this.this$0) >= HttpObjectDecoder.access$100(this.this$0)) {
         throw new TooLongFrameException("HTTP header is larger than " + HttpObjectDecoder.access$100(this.this$0) + " bytes.");
      } else {
         this.seq.append(var2);
         return true;
      }
   }

   public AppendableCharSequence parse(ByteBuf var1) {
      this.seq.reset();
      HttpObjectDecoder.access$002(this.this$0, 0);
      int var2 = var1.forEachByte(this);
      var1.readerIndex(var2 + 1);
      return this.seq;
   }

   public HttpObjectDecoder$HeaderParser(HttpObjectDecoder var1, AppendableCharSequence var2) {
      this.this$0 = var1;
      super();
      this.seq = var2;
   }
}
