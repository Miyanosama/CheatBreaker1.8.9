package org.java_websocket.util;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;

public class Base64 {
   public static final byte recoveredField2554 = 61;
   public static final byte recoveredField2555 = -5;
   public static final int recoveredField2556 = 1;
   public static final byte recoveredField2557 = 10;
   public static final int recoveredField2558 = 0;
   public static final int recoveredField2559 = 76;
   public static final int recoveredField2560 = 32;
   public static final String recoveredField2561 = "US-ASCII";
   public static final int recoveredField2562 = 16;
   public static final int recoveredField2563 = 2;
   public static final int recoveredField2564 = 8;
   public static final boolean $assertionsDisabled = !Base64.class.desiredAssertionStatus();
   public static byte[] _STANDARD_ALPHABET = new byte[]{
      65,
      66,
      67,
      68,
      69,
      70,
      71,
      72,
      73,
      74,
      75,
      76,
      77,
      78,
      79,
      80,
      81,
      82,
      83,
      84,
      85,
      86,
      87,
      88,
      89,
      90,
      97,
      98,
      99,
      100,
      101,
      102,
      103,
      104,
      105,
      106,
      107,
      108,
      109,
      110,
      111,
      112,
      113,
      114,
      115,
      116,
      117,
      118,
      119,
      120,
      121,
      122,
      48,
      49,
      50,
      51,
      52,
      53,
      54,
      55,
      56,
      57,
      43,
      47
   };
   public static byte[] _STANDARD_DECODABET = new byte[]{
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -5,
      -5,
      -9,
      -9,
      -5,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -5,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      62,
      -9,
      -9,
      -9,
      63,
      52,
      53,
      54,
      55,
      56,
      57,
      58,
      59,
      60,
      61,
      -9,
      -9,
      -9,
      -1,
      -9,
      -9,
      -9,
      0,
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      17,
      18,
      19,
      20,
      21,
      22,
      23,
      24,
      25,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      26,
      27,
      28,
      29,
      30,
      31,
      32,
      33,
      34,
      35,
      36,
      37,
      38,
      39,
      40,
      41,
      42,
      43,
      44,
      45,
      46,
      47,
      48,
      49,
      50,
      51,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9
   };
   public static byte[] _URL_SAFE_ALPHABET = new byte[]{
      65,
      66,
      67,
      68,
      69,
      70,
      71,
      72,
      73,
      74,
      75,
      76,
      77,
      78,
      79,
      80,
      81,
      82,
      83,
      84,
      85,
      86,
      87,
      88,
      89,
      90,
      97,
      98,
      99,
      100,
      101,
      102,
      103,
      104,
      105,
      106,
      107,
      108,
      109,
      110,
      111,
      112,
      113,
      114,
      115,
      116,
      117,
      118,
      119,
      120,
      121,
      122,
      48,
      49,
      50,
      51,
      52,
      53,
      54,
      55,
      56,
      57,
      45,
      95
   };
   public static byte[] _URL_SAFE_DECODABET = new byte[]{
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -5,
      -5,
      -9,
      -9,
      -5,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -5,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      62,
      -9,
      -9,
      52,
      53,
      54,
      55,
      56,
      57,
      58,
      59,
      60,
      61,
      -9,
      -9,
      -9,
      -1,
      -9,
      -9,
      -9,
      0,
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      17,
      18,
      19,
      20,
      21,
      22,
      23,
      24,
      25,
      -9,
      -9,
      -9,
      -9,
      63,
      -9,
      26,
      27,
      28,
      29,
      30,
      31,
      32,
      33,
      34,
      35,
      36,
      37,
      38,
      39,
      40,
      41,
      42,
      43,
      44,
      45,
      46,
      47,
      48,
      49,
      50,
      51,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9
   };
   public static byte[] _ORDERED_ALPHABET = new byte[]{
      45,
      48,
      49,
      50,
      51,
      52,
      53,
      54,
      55,
      56,
      57,
      65,
      66,
      67,
      68,
      69,
      70,
      71,
      72,
      73,
      74,
      75,
      76,
      77,
      78,
      79,
      80,
      81,
      82,
      83,
      84,
      85,
      86,
      87,
      88,
      89,
      90,
      95,
      97,
      98,
      99,
      100,
      101,
      102,
      103,
      104,
      105,
      106,
      107,
      108,
      109,
      110,
      111,
      112,
      113,
      114,
      115,
      116,
      117,
      118,
      119,
      120,
      121,
      122
   };
   public static byte[] _ORDERED_DECODABET = new byte[]{
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -5,
      -5,
      -9,
      -9,
      -5,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -5,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      0,
      -9,
      -9,
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      -9,
      -9,
      -9,
      -1,
      -9,
      -9,
      -9,
      11,
      12,
      13,
      14,
      15,
      16,
      17,
      18,
      19,
      20,
      21,
      22,
      23,
      24,
      25,
      26,
      27,
      28,
      29,
      30,
      31,
      32,
      33,
      34,
      35,
      36,
      -9,
      -9,
      -9,
      -9,
      37,
      -9,
      38,
      39,
      40,
      41,
      42,
      43,
      44,
      45,
      46,
      47,
      48,
      49,
      50,
      51,
      52,
      53,
      54,
      55,
      56,
      57,
      58,
      59,
      60,
      61,
      62,
      63,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9,
      -9
   };

   public static byte[] encodeBytesToBytes(byte[] var0, int var1, int var2, int var3) throws java.io.IOException {
      if (var0 == null) {
         throw new IllegalArgumentException("Cannot serialize a null array.");
      } else if (var1 < 0) {
         throw new IllegalArgumentException("Cannot have negative offset: " + var1);
      } else if (var2 < 0) {
         throw new IllegalArgumentException("Cannot have length offset: " + var2);
      } else if (var1 + var2 > var0.length) {
         throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", var1, var2, var0.length));
      } else if ((var3 & 2) != 0) {
         ByteArrayOutputStream var25 = null;
         GZIPOutputStream var26 = null;
         Base64.OutputStream var27 = null;

         try {
            var25 = new ByteArrayOutputStream();
            var27 = new Base64.OutputStream(var25, 1 | var3);
            var26 = new GZIPOutputStream(var27);
            var26.write(var0, var1, var2);
            var26.close();
         } catch (IOException var23) {
            throw var23;
         } finally {
            try {
               if (var26 != null) {
                  var26.close();
               }
            } catch (Exception var22) {
            }

            try {
               if (var27 != null) {
                  var27.close();
               }
            } catch (Exception var21) {
            }

            try {
               if (var25 != null) {
                  var25.close();
               }
            } catch (Exception var20) {
            }
         }

         return var25.toByteArray();
      } else {
         boolean var4 = (var3 & 8) != 0;
         int var5 = var2 / 3 * 4 + (var2 % 3 > 0 ? 4 : 0);
         if (var4) {
            var5 += var5 / 76;
         }

         byte[] var6 = new byte[var5];
         int var7 = 0;
         int var8 = 0;
         int var9 = var2 - 2;

         for (int var10 = 0; var7 < var9; var8 += 4) {
            encode3to4(var0, var7 + var1, 3, var6, var8, var3);
            var10 += 4;
            if (var4 && var10 >= 76) {
               var6[var8 + 4] = 10;
               var8++;
               var10 = 0;
            }

            var7 += 3;
         }

         if (var7 < var2) {
            encode3to4(var0, var7 + var1, var2 - var7, var6, var8, var3);
            var8 += 4;
         }

         if (var8 <= var6.length - 1) {
            byte[] var11 = new byte[var8];
            System.arraycopy(var6, 0, var11, 0, var8);
            return var11;
         } else {
            return var6;
         }
      }
   }

   public static byte[] getAlphabet(int var0) {
      if ((var0 & 16) == 16) {
         return _URL_SAFE_ALPHABET;
      } else {
         return (var0 & 32) == 32 ? _ORDERED_ALPHABET : _STANDARD_ALPHABET;
      }
   }

   public static byte[] encode3to4(byte[] var0, byte[] var1, int var2, int var3) {
      encode3to4(var1, 0, var2, var0, 0, var3);
      return var0;
   }

   public static String encodeBytes(byte[] var0, int var1, int var2, int var3) throws java.io.IOException {
      byte[] var4 = encodeBytesToBytes(var0, var1, var2, var3);

      try {
         return new String(var4, "US-ASCII");
      } catch (UnsupportedEncodingException var6) {
         return new String(var4);
      }
   }

   public static byte[] getDecodabet(int var0) {
      if ((var0 & 16) == 16) {
         return _URL_SAFE_DECODABET;
      } else {
         return (var0 & 32) == 32 ? _ORDERED_DECODABET : _STANDARD_DECODABET;
      }
   }

   public static String encodeBytes(byte[] var0) {
      String var1 = null;

      try {
         var1 = encodeBytes(var0, 0, var0.length, 0);
      } catch (IOException var3) {
         if (!$assertionsDisabled) {
            throw new AssertionError(var3.getMessage());
         }
      }

      if (!$assertionsDisabled && var1 == null) {
         throw new AssertionError();
      } else {
         return var1;
      }
   }

   public static int decode4to3(byte[] var0, int var1, byte[] var2, int var3, int var4) {
      if (var0 == null) {
         throw new IllegalArgumentException("Source array was null.");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Destination array was null.");
      } else if (var1 < 0 || var1 + 3 >= var0.length) {
         throw new IllegalArgumentException(
            String.format("Source array with length %d cannot have offset of %d and still process four bytes.", var0.length, var1)
         );
      } else if (var3 >= 0 && var3 + 2 < var2.length) {
         byte[] var5 = getDecodabet(var4);
         if (var0[var1 + 2] == 61) {
            int var8 = (var5[var0[var1]] & 255) << 18 | (var5[var0[var1 + 1]] & 255) << 12;
            var2[var3] = (byte)(var8 >>> 16);
            return 1;
         } else if (var0[var1 + 3] == 61) {
            int var7 = (var5[var0[var1]] & 255) << 18 | (var5[var0[var1 + 1]] & 255) << 12 | (var5[var0[var1 + 2]] & 255) << 6;
            var2[var3] = (byte)(var7 >>> 16);
            var2[var3 + 1] = (byte)(var7 >>> 8);
            return 2;
         } else {
            int var6 = (var5[var0[var1]] & 255) << 18 | (var5[var0[var1 + 1]] & 255) << 12 | (var5[var0[var1 + 2]] & 255) << 6 | var5[var0[var1 + 3]] & 255;
            var2[var3] = (byte)(var6 >> 16);
            var2[var3 + 1] = (byte)(var6 >> 8);
            var2[var3 + 2] = (byte)var6;
            return 3;
         }
      } else {
         throw new IllegalArgumentException(
            String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", var2.length, var3)
         );
      }
   }

   public static byte[] encode3to4(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5) {
      byte[] var6 = getAlphabet(var5);
      int var7 = (var2 > 0 ? var0[var1] << 24 >>> 8 : 0) | (var2 > 1 ? var0[var1 + 1] << 24 >>> 16 : 0) | (var2 > 2 ? var0[var1 + 2] << 24 >>> 24 : 0);
      switch (var2) {
         case 1:
            var3[var4] = var6[var7 >>> 18];
            var3[var4 + 1] = var6[var7 >>> 12 & 63];
            var3[var4 + 2] = 61;
            var3[var4 + 3] = 61;
            return var3;
         case 2:
            var3[var4] = var6[var7 >>> 18];
            var3[var4 + 1] = var6[var7 >>> 12 & 63];
            var3[var4 + 2] = var6[var7 >>> 6 & 63];
            var3[var4 + 3] = 61;
            return var3;
         case 3:
            var3[var4] = var6[var7 >>> 18];
            var3[var4 + 1] = var6[var7 >>> 12 & 63];
            var3[var4 + 2] = var6[var7 >>> 6 & 63];
            var3[var4 + 3] = var6[var7 & 63];
            return var3;
         default:
            return var3;
      }
   }

   public static class OutputStream extends FilterOutputStream {
      public int options;
      public byte[] buffer;
      public boolean suspendEncoding;
      public byte[] decodabet;
      public int position;
      public byte[] b4;
      public int lineLength;
      public int bufferLength;
      public boolean breakLines;
      public boolean encode;

      @Override
      public void write(byte[] var1, int var2, int var3) throws java.io.IOException {
         if (this.suspendEncoding) {
            this.out.write(var1, var2, var3);
         } else {
            for (int var4 = 0; var4 < var3; var4++) {
               this.write(var1[var2 + var4]);
            }
         }
      }

      public OutputStream(java.io.OutputStream var1, int var2) {
         super(var1);
         this.breakLines = (var2 & 8) != 0;
         this.encode = (var2 & 1) != 0;
         this.bufferLength = this.encode ? 3 : 4;
         this.buffer = new byte[this.bufferLength];
         this.position = 0;
         this.lineLength = 0;
         this.suspendEncoding = false;
         this.b4 = new byte[4];
         this.options = var2;
         this.decodabet = Base64.getDecodabet(var2);
      }

      public void flushBase64() throws java.io.IOException {
         if (this.position > 0) {
            if (!this.encode) {
               throw new IOException("Base64 input not properly padded.");
            }

            this.out.write(Base64.encode3to4(this.b4, this.buffer, this.position, this.options));
            this.position = 0;
         }
      }

      @Override
      public void write(int var1) throws java.io.IOException {
         if (this.suspendEncoding) {
            this.out.write(var1);
         } else {
            if (this.encode) {
               this.buffer[this.position++] = (byte)var1;
               if (this.position >= this.bufferLength) {
                  this.out.write(Base64.encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
                  this.lineLength += 4;
                  if (this.breakLines && this.lineLength >= 76) {
                     this.out.write(10);
                     this.lineLength = 0;
                  }

                  this.position = 0;
               }
            } else if (this.decodabet[var1 & 127] > -5) {
               this.buffer[this.position++] = (byte)var1;
               if (this.position >= this.bufferLength) {
                  int var2 = Base64.decode4to3(this.buffer, 0, this.b4, 0, this.options);
                  this.out.write(this.b4, 0, var2);
                  this.position = 0;
               }
            } else if (this.decodabet[var1 & 127] != -5) {
               throw new IOException("Invalid character in Base64 data.");
            }
         }
      }

      public OutputStream(java.io.OutputStream var1) {
         this(var1, 1);
      }

      @Override
      public void close() throws java.io.IOException {
         this.flushBase64();
         super.close();
         this.buffer = null;
         this.out = null;
      }
   }
}
