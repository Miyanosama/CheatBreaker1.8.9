package io.netty.handler.codec.http.multipart;

import com.cheatbreaker.client.module.type.armourstatus.ArmourStatusModule;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.ai.EntityAITempt;
import org.apache.log4j.pattern.NameAbbreviator;
import junit.framework.ComparisonCompactor;
import net.minecraft.entity.ai.EntityAIOcelotAttack;

public class HttpPostBodyUtil {
   public static final int chunkSize = 8096;
   public static final String CONTENT_DISPOSITION = "Content-Disposition";
   public static final String FILE = "file";
   public static final String NAME = "name";
   public static final String DEFAULT_TEXT_CONTENT_TYPE = "text/plain";
   public static final String DEFAULT_BINARY_CONTENT_TYPE = "application/octet-stream";
   public static final String ATTACHMENT = "attachment";
   public static final String FORM_DATA = "form-data";
   public static final String FILENAME = "filename";
   public static final String MULTIPART_MIXED = "multipart/mixed";
   public static Charset ISO_8859_1 = CharsetUtil.ISO_8859_1;
   public static Charset US_ASCII = CharsetUtil.US_ASCII;

   public static int findNonWhitespace(String var0, int var1) {
      int var2 = var1;

      while (var2 < var0.length() && Character.isWhitespace(var0.charAt(var2))) {
         var2++;
      }

      return var2;
   }

   public static int findWhitespace(String var0, int var1) {
      int var2 = var1;

      while (var2 < var0.length() && !Character.isWhitespace(var0.charAt(var2))) {
         var2++;
      }

      return var2;
   }

   public static int findEndOfString(String var0) {
      int var1 = var0.length();

      while (var1 > 0 && Character.isWhitespace(var0.charAt(var1 - 1))) {
         var1--;
      }

      return var1;
   }

   public static class SeekAheadNoBackArrayException extends Exception {
      public static final long serialVersionUID = -630418804938699495L;
   }

   public static class SeekAheadOptimize {
      public int readerIndex;
      public int origPos;
      public int pos;
      public byte[] bytes;
      public ByteBuf buffer;
      public int limit;

      public SeekAheadOptimize(ByteBuf var1) throws io.netty.handler.codec.http.multipart.HttpPostBodyUtil.SeekAheadNoBackArrayException {
         if (!var1.hasArray()) {
            throw new HttpPostBodyUtil.SeekAheadNoBackArrayException();
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

   public static enum TransferEncodingMechanism {
      BIT7("7bit"),
      BIT8("8bit"),
      BINARY("binary");

      public String value;

      TransferEncodingMechanism(String var3) {
         this.value = var3;
      }

      TransferEncodingMechanism() {
         this.value = this.name();
      }

      @Override
      public String toString() {
         return this.value;
      }

      public String value() {
         return this.value;
      }
   }
}
