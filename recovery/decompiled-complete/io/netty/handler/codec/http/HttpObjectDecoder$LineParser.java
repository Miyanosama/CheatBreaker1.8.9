package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.netty.channel.DefaultMessageSizeEstimator$HandleImpl;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.http.multipart.CaseIgnoringComparator;
import io.netty.util.internal.AppendableCharSequence;
import javax.vecmath.Vector2f;
import net.minecraft.util.EntitySelectors$1;
import net.optifine.util.TileEntityUtils;
import org.slf4j.event.Level;

public class HttpObjectDecoder$LineParser implements ByteBufProcessor {
   public CaseIgnoringComparator __junk2032875739039521729;
   public AppendableCharSequence seq;
   public EntitySelectors$1 __junk1421400885475438629;
   public Level __junk8187478926703214320;
   public TileEntityUtils __junk5128481484321908647;
   public Vector2f __junk5341810402039060629;
   public DefaultMessageSizeEstimator$HandleImpl __junk7719914947536162109;
   public int size;

   public HttpObjectDecoder$LineParser(HttpObjectDecoder var1, AppendableCharSequence var2) {
      this.this$0 = var1;
      super();
      this.seq = var2;
   }

   @Override
   public boolean process(byte var1) {
      char var2 = (char)var1;
      if (var2 == '\r') {
         return true;
      } else if (var2 == '\n') {
         return false;
      } else if (this.size >= HttpObjectDecoder.access$200(this.this$0)) {
         throw new TooLongFrameException("An HTTP line is larger than " + HttpObjectDecoder.access$200(this.this$0) + " bytes.");
      } else {
         this.size++;
         this.seq.append(var2);
         return true;
      }
   }

   public AppendableCharSequence parse(ByteBuf var1) {
      this.seq.reset();
      this.size = 0;
      int var2 = var1.forEachByte(this);
      var1.readerIndex(var2 + 1);
      return this.seq;
   }
}
