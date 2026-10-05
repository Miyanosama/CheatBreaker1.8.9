package javazoom.jl.decoder;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.item.crafting.ShapelessRecipes;

public class Bitstream implements BitstreamErrors {
   public EntityAIRestrictSun __junk62526938201048203;
   public int wordpointer;
   public ShapelessRecipes __junk1430860015329872400;
   public int[] bitmask;
   public byte[] frame_bytes;
   public static byte INITIAL_SYNC = 0;
   public boolean firstframe;
   public byte[] syncbuf;
   public PushbackInputStream source;
   public boolean single_ch_mode;
   public byte[] rawid3v2;
   public Header header;
   public int[] framebuffer = new int[433];
   public Crc16[] crc;
   public int syncword;
   public int framesize;
   public int bitindex;
   public int header_pos;
   public static byte STRICT_SYNC = 1;
   public static int BUFFER_INT_SIZE;

   public int readBytes(byte[] var1, int var2, int var3) {
      int var4 = 0;

      try {
         while (var3 > 0) {
            int var5 = this.source.read(var1, var2, var3);
            if (var5 == -1) {
               break;
            }

            var4 += var5;
            var2 += var5;
            var3 -= var5;
         }

         return var4;
      } catch (IOException var6) {
         throw this.newBitstreamException(258, var6);
      }
   }

   public Header readNextFrame() {
      if (this.framesize == -1) {
         this.nextFrame();
      }

      return this.header;
   }

   public void loadID3v2(InputStream var1) {
      int var2 = -1;

      try {
         var1.mark(10);
         var2 = this.readID3v2Header(var1);
         this.header_pos = var2;
      } catch (IOException var14) {
      } finally {
         try {
            var1.reset();
         } catch (IOException var12) {
         }
      }

      try {
         if (var2 > 0) {
            this.rawid3v2 = new byte[var2];
            var1.read(this.rawid3v2, 0, this.rawid3v2.length);
         }
      } catch (IOException var13) {
      }
   }

   public boolean isSyncMark(int var1, int var2, int var3) {
      boolean var4 = false;
      if (var2 == INITIAL_SYNC) {
         var4 = (var1 & -2097152) == -2097152;
      } else {
         var4 = (var1 & -521216) == var3 && (var1 & 192) == 192 == this.single_ch_mode;
      }

      if (var4) {
         var4 = (var1 >>> 10 & 3) != 3;
      }

      if (var4) {
         var4 = (var1 >>> 17 & 3) != 0;
      }

      if (var4) {
         var4 = (var1 >>> 19 & 3) != 1;
      }

      return var4;
   }

   public int readBits(int var1) {
      return this.get_bits(var1);
   }

   public int readCheckedBits(int var1) {
      return this.get_bits(var1);
   }

   public void nextFrame() {
      this.header.read_header(this, this.crc);
   }

   public int readID3v2Header(InputStream var1) {
      byte[] var2 = new byte[4];
      int var3 = -10;
      var1.read(var2, 0, 3);
      if (var2[0] == 73 && var2[1] == 68 && var2[2] == 51) {
         var1.read(var2, 0, 3);
         byte var4 = var2[0];
         byte var5 = var2[1];
         var1.read(var2, 0, 4);
         var3 = (var2[0] << 21) + (var2[1] << 14) + (var2[2] << 7) + var2[3];
      }

      return var3 + 10;
   }

   public int header_pos() {
      return this.header_pos;
   }

   public void set_syncword(int var1) {
      this.syncword = var1 & -193;
      this.single_ch_mode = (var1 & 192) == 192;
   }

   public Header readFrame() {
      Header var1 = null;

      try {
         var1 = this.readNextFrame();
         if (this.firstframe) {
            var1.parseVBR(this.frame_bytes);
            this.firstframe = false;
         }
      } catch (BitstreamException var5) {
         if (var5.getErrorCode() == 261) {
            try {
               this.closeFrame();
               var1 = this.readNextFrame();
            } catch (BitstreamException var4) {
               if (var4.getErrorCode() != 260) {
                  throw this.newBitstreamException(var4.getErrorCode(), var4);
               }
            }
         } else if (var5.getErrorCode() != 260) {
            throw this.newBitstreamException(var5.getErrorCode(), var5);
         }
      }

      return var1;
   }

   public void unreadFrame() {
      if (this.wordpointer == -1 && this.bitindex == -1 && this.framesize > 0) {
         try {
            this.source.unread(this.frame_bytes, 0, this.framesize);
         } catch (IOException var2) {
            throw this.newBitstreamException(258);
         }
      }
   }

   public void parse_frame() {
      int var1 = 0;
      byte[] var2 = this.frame_bytes;
      int var3 = this.framesize;

      for (byte var4 = 0; var4 < var3; var4 += 4) {
         boolean var5 = false;
         byte var6 = 0;
         byte var7 = 0;
         byte var8 = 0;
         byte var9 = 0;
         var6 = var2[var4];
         if (var4 + 1 < var3) {
            var7 = var2[var4 + 1];
         }

         if (var4 + 2 < var3) {
            var8 = var2[var4 + 2];
         }

         if (var4 + 3 < var3) {
            var9 = var2[var4 + 3];
         }

         this.framebuffer[var1++] = var6 << 24 & 0xFF000000 | var7 << 16 & 0xFF0000 | var8 << 8 & 0xFF00 | var9 & 255;
      }

      this.wordpointer = 0;
      this.bitindex = 0;
   }

   public boolean isSyncCurrentPosition(int var1) {
      int var2 = this.readBytes(this.syncbuf, 0, 4);
      int var3 = this.syncbuf[0] << 24 & 0xFF000000 | this.syncbuf[1] << 16 & 0xFF0000 | this.syncbuf[2] << 8 & 0xFF00 | this.syncbuf[3] << 0 & 0xFF;

      try {
         this.source.unread(this.syncbuf, 0, var2);
      } catch (IOException var5) {
      }

      boolean var4 = false;
      switch (var2) {
         case 0:
            var4 = true;
            break;
         case 4:
            var4 = this.isSyncMark(var3, var1, this.syncword);
      }

      return var4;
   }

   public int readFully(byte[] var1, int var2, int var3) {
      int var4 = 0;

      try {
         while (var3 > 0) {
            int var5 = this.source.read(var1, var2, var3);
            if (var5 == -1) {
               while (var3-- > 0) {
                  var1[var2++] = 0;
               }
               break;
            }

            var4 += var5;
            var2 += var5;
            var3 -= var5;
         }

         return var4;
      } catch (IOException var6) {
         throw this.newBitstreamException(258, var6);
      }
   }

   public InputStream getRawID3v2() {
      return this.rawid3v2 == null ? null : new ByteArrayInputStream(this.rawid3v2);
   }

   public Bitstream(InputStream var1) {
      this.frame_bytes = new byte[1732];
      this.header_pos = 0;
      this.bitmask = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071};
      this.header = new Header();
      this.syncbuf = new byte[4];
      this.crc = new Crc16[1];
      this.rawid3v2 = null;
      this.firstframe = true;
      if (var1 == null) {
         throw new NullPointerException("in");
      } else {
         BufferedInputStream var2 = new BufferedInputStream(var1);
         this.loadID3v2(var2);
         this.firstframe = true;
         this.source = new PushbackInputStream(var2, 1732);
         this.closeFrame();
      }
   }

   public int read_frame_data(int var1) {
      int var2 = 0;
      var2 = this.readFully(this.frame_bytes, 0, var1);
      this.framesize = var1;
      this.wordpointer = -1;
      this.bitindex = -1;
      return var2;
   }

   public int get_bits(int var1) {
      int var2 = 0;
      int var3 = this.bitindex + var1;
      if (this.wordpointer < 0) {
         this.wordpointer = 0;
      }

      if (var3 <= 32) {
         var2 = this.framebuffer[this.wordpointer] >>> 32 - var3 & this.bitmask[var1];
         if ((this.bitindex += var1) == 32) {
            this.bitindex = 0;
            this.wordpointer++;
         }

         return var2;
      } else {
         int var4 = this.framebuffer[this.wordpointer] & 65535;
         this.wordpointer++;
         int var5 = this.framebuffer[this.wordpointer] & -65536;
         var2 = var4 << 16 & -65536 | var5 >>> 16 & 65535;
         var2 >>>= 48 - var3;
         var2 &= this.bitmask[var1];
         this.bitindex = var3 - 32;
         return var2;
      }
   }

   public BitstreamException newBitstreamException(int var1, Throwable var2) {
      return new BitstreamException(var1, var2);
   }

   public BitstreamException newBitstreamException(int var1) {
      return new BitstreamException(var1, null);
   }

   public void close() {
      try {
         this.source.close();
      } catch (IOException var2) {
         throw this.newBitstreamException(258, var2);
      }
   }

   public void closeFrame() {
      this.framesize = -1;
      this.wordpointer = -1;
      this.bitindex = -1;
   }

   public int syncHeader(byte var1) {
      int var4 = this.readBytes(this.syncbuf, 0, 3);
      if (var4 != 3) {
         throw this.newBitstreamException(260, null);
      } else {
         int var3 = this.syncbuf[0] << 16 & 0xFF0000 | this.syncbuf[1] << 8 & 0xFF00 | this.syncbuf[2] << 0 & 0xFF;

         boolean var2;
         do {
            var3 <<= 8;
            if (this.readBytes(this.syncbuf, 3, 1) != 1) {
               throw this.newBitstreamException(260, null);
            }

            var3 |= this.syncbuf[3] & 255;
            var2 = this.isSyncMark(var3, var1, this.syncword);
         } while (!var2);

         return var3;
      }
   }
}
