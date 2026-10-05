package net.optifine.texture;

import io.netty.buffer.PooledHeapByteBuf;
import io.netty.buffer.ReadOnlyByteBufferBuf;
import org.apache.log4j.chainsaw.ControlPanel$2;
import org.apache.log4j.pattern.NameAbbreviator$MaxElementAbbreviator;

public enum PixelFormat {
   RG_INTEGER(33320),
   RED(6403),
   RG(33319),
   RGB(6407),
   BGR(32992),
   BGR_INTEGER(36250),
   BGRA(32993),
   RGBA_INTEGER(36249),
   BGRA_INTEGER(36251),
   RED_INTEGER(36244),
   RGB_INTEGER(36248),
   RGBA(6408);
   public NameAbbreviator$MaxElementAbbreviator field_0008;
   // $VF: synthetic field
   public static PixelFormat[] $VALUES = new PixelFormat[]{
      PixelFormat.RED,
      PixelFormat.RG,
      PixelFormat.RGB,
      PixelFormat.BGR,
      PixelFormat.RGBA,
      PixelFormat.BGRA,
      PixelFormat.RED_INTEGER,
      PixelFormat.RG_INTEGER,
      PixelFormat.RGB_INTEGER,
      PixelFormat.BGR_INTEGER,
      PixelFormat.RGBA_INTEGER,
      PixelFormat.BGRA_INTEGER
   };
   public ControlPanel$2 field_0013;
   public ReadOnlyByteBufferBuf field_0011;
   public PooledHeapByteBuf field_0001;
   public int id;

   public int getId() {
      return this.id;
   }

   public PixelFormat(int var3) {
      this.id = var3;
   }
}
