package io.netty.handler.codec.http.multipart;

import com.cheatbreaker.client.module.type.armourstatus.ArmourStatusModule;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.socks.SocksCmdRequestDecoder$1;
import net.minecraft.client.renderer.OpenGlHelper;
import recovered.unidentified.UnidentifiedClass3843;

public class HttpPostBodyUtil$SeekAheadOptimize {
   public int readerIndex;
   public OpenGlHelper __junk6779300930245659435;
   public int origPos;
   public int pos;
   public SocksCmdRequestDecoder$1 __junk3961388403198914640;
   public UnidentifiedClass3843 __junk7957684463744733766;
   public byte[] bytes;
   public ByteBuf buffer;
   public int limit;
   public ArmourStatusModule __junk1867080698346834816;

   public HttpPostBodyUtil$SeekAheadOptimize(ByteBuf var1) {
      if (!var1.hasArray()) {
         throw new HttpPostBodyUtil$SeekAheadNoBackArrayException();
      } else {
         this.buffer = var1;
         this.bytes = var1.array();
         this.readerIndex = var1.readerIndex();
         this.origPos = this.pos = var1.arrayOffset() + this.readerIndex;
         this.limit = var1.arrayOffset() + var1.writerIndex();
      }
   }

   public void clear() {
      this.buffer = null;
      this.bytes = null;
      this.limit = 0;
      this.pos = 0;
      this.readerIndex = 0;
   }

   public void setReadPosition(int var1) {
      this.pos -= var1;
      this.readerIndex = this.getReadPosition(this.pos);
      this.buffer.readerIndex(this.readerIndex);
   }

   public int getReadPosition(int var1) {
      return var1 - this.origPos + this.readerIndex;
   }
}
