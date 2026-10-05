package io.netty.buffer;

import io.netty.util.internal.PlatformDependent;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.world.storage.SaveDataMemoryStorage;

public class Unpooled {
   public static ByteBufAllocator ALLOC = UnpooledByteBufAllocator.DEFAULT;
   public static ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
   public static ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
   public static ByteBuf EMPTY_BUFFER = ALLOC.buffer(0, 0);

   public static ByteBuf wrappedBuffer(ByteBuffer... var0) {
      return wrappedBuffer(16, var0);
   }

   public static ByteBuf copyMedium(int var0) {
      ByteBuf var1 = buffer(3);
      var1.writeMedium(var0);
      return var1;
   }

   public static ByteBuf copiedBuffer(char[] var0, Charset var1) {
      if (var0 == null) {
         throw new NullPointerException("array");
      } else {
         return copiedBuffer(var0, 0, var0.length, var1);
      }
   }

   public static ByteBuf copiedBuffer(ByteBuffer... var0) {
      switch (var0.length) {
         case 0:
            return EMPTY_BUFFER;
         case 1:
            return copiedBuffer(var0[0]);
         default:
            ByteOrder var1 = null;
            int var2 = 0;

            for (ByteBuffer var6 : var0) {
               int var7 = var6.remaining();
               if (var7 > 0) {
                  if (Integer.MAX_VALUE - var2 < var7) {
                     throw new IllegalArgumentException("The total length of the specified buffers is too big.");
                  }

                  var2 += var7;
                  if (var1 != null) {
                     if (!var1.equals(var6.order())) {
                        throw new IllegalArgumentException("inconsistent byte order");
                     }
                  } else {
                     var1 = var6.order();
                  }
               }
            }

            if (var2 == 0) {
               return EMPTY_BUFFER;
            } else {
               byte[] var9 = new byte[var2];
               int var10 = 0;

               for (int var11 = 0; var10 < var0.length; var10++) {
                  ByteBuffer var12 = var0[var10];
                  int var13 = var12.remaining();
                  int var8 = var12.position();
                  var12.get(var9, var11, var13);
                  ((Buffer)var12).position(var8);
                  var11 += var13;
               }

               return wrappedBuffer(var9).order(var1);
            }
      }
   }

   public static ByteBuf copiedBuffer(ByteBuf... var0) {
      switch (var0.length) {
         case 0:
            return EMPTY_BUFFER;
         case 1:
            return copiedBuffer(var0[0]);
         default:
            ByteOrder var1 = null;
            int var2 = 0;

            for (ByteBuf var6 : var0) {
               int var7 = var6.readableBytes();
               if (var7 > 0) {
                  if (Integer.MAX_VALUE - var2 < var7) {
                     throw new IllegalArgumentException("The total length of the specified buffers is too big.");
                  }

                  var2 += var7;
                  if (var1 != null) {
                     if (!var1.equals(var6.order())) {
                        throw new IllegalArgumentException("inconsistent byte order");
                     }
                  } else {
                     var1 = var6.order();
                  }
               }
            }

            if (var2 == 0) {
               return EMPTY_BUFFER;
            } else {
               byte[] var8 = new byte[var2];
               int var9 = 0;

               for (int var10 = 0; var9 < var0.length; var9++) {
                  ByteBuf var11 = var0[var9];
                  int var12 = var11.readableBytes();
                  var11.getBytes(var11.readerIndex(), var8, var10, var12);
                  var10 += var12;
               }

               return wrappedBuffer(var8).order(var1);
            }
      }
   }

   public static ByteBuf copyLong(long var0) {
      ByteBuf var2 = buffer(8);
      var2.writeLong(var0);
      return var2;
   }

   public static ByteBuf copiedBuffer(byte[]... var0) {
      switch (var0.length) {
         case 0:
            return EMPTY_BUFFER;
         case 1:
            if (var0[0].length == 0) {
               return EMPTY_BUFFER;
            }

            return copiedBuffer(var0[0]);
         default:
            int var1 = 0;

            for (byte[] var5 : var0) {
               if (Integer.MAX_VALUE - var1 < var5.length) {
                  throw new IllegalArgumentException("The total length of the specified arrays is too big.");
               }

               var1 += var5.length;
            }

            if (var1 == 0) {
               return EMPTY_BUFFER;
            } else {
               byte[] var6 = new byte[var1];
               int var7 = 0;

               for (int var8 = 0; var7 < var0.length; var7++) {
                  byte[] var9 = var0[var7];
                  System.arraycopy(var9, 0, var6, var8, var9.length);
                  var8 += var9.length;
               }

               return wrappedBuffer(var6);
            }
      }
   }

   public static ByteBuf wrappedBuffer(ByteBuf var0) {
      return var0.isReadable() ? var0.slice() : EMPTY_BUFFER;
   }

   public static ByteBuf copiedBuffer(ByteBuf var0) {
      int var1 = var0.readableBytes();
      if (var1 > 0) {
         ByteBuf var2 = buffer(var1);
         var2.writeBytes(var0, var0.readerIndex(), var1);
         return var2;
      } else {
         return EMPTY_BUFFER;
      }
   }

   public static ByteBuf copiedBuffer(CharSequence var0, Charset var1) {
      if (var0 == null) {
         throw new NullPointerException("string");
      } else {
         return var0 instanceof CharBuffer ? copiedBuffer((CharBuffer)var0, var1) : copiedBuffer(CharBuffer.wrap(var0), var1);
      }
   }

   public static ByteBuf wrappedBuffer(byte[]... var0) {
      return wrappedBuffer(16, var0);
   }

   public static ByteBuf directBuffer(int var0, int var1) {
      return ALLOC.directBuffer(var0, var1);
   }

   public static ByteBuf copyBoolean(boolean var0) {
      ByteBuf var1 = buffer(1);
      var1.writeBoolean(var0);
      return var1;
   }

   public static ByteBuf unmodifiableBuffer(ByteBuf var0) {
      ByteOrder var1 = var0.order();
      return (ByteBuf)(var1 == BIG_ENDIAN ? new ReadOnlyByteBuf(var0) : new ReadOnlyByteBuf(var0.order(BIG_ENDIAN)).order(LITTLE_ENDIAN));
   }

   public static ByteBuf unreleasableBuffer(ByteBuf var0) {
      return new UnreleasableByteBuf(var0);
   }

   public static ByteBuf wrappedBuffer(int var0, byte[]... var1) {
      switch (var1.length) {
         case 0:
            break;
         case 1:
            if (var1[0].length != 0) {
               return wrappedBuffer(var1[0]);
            }
            break;
         default:
            ArrayList var2 = new ArrayList(var1.length);

            for (byte[] var6 : var1) {
               if (var6 == null) {
                  break;
               }

               if (var6.length > 0) {
                  var2.add(wrappedBuffer(var6));
               }
            }

            if (!var2.isEmpty()) {
               return new CompositeByteBuf(ALLOC, false, var0, var2);
            }
      }

      return EMPTY_BUFFER;
   }

   public static ByteBuf copyLong(long... var0) {
      if (var0 != null && var0.length != 0) {
         ByteBuf var1 = buffer(var0.length * 8);

         for (long var5 : var0) {
            var1.writeLong(var5);
         }

         return var1;
      } else {
         return EMPTY_BUFFER;
      }
   }

   public static ByteBuf copiedBuffer(byte[] var0, int var1, int var2) {
      if (var2 == 0) {
         return EMPTY_BUFFER;
      } else {
         byte[] var3 = new byte[var2];
         System.arraycopy(var0, var1, var3, 0, var2);
         return wrappedBuffer(var3);
      }
   }

   public static ByteBuf copyDouble(double... var0) {
      if (var0 != null && var0.length != 0) {
         ByteBuf var1 = buffer(var0.length * 8);

         for (double var5 : var0) {
            var1.writeDouble(var5);
         }

         return var1;
      } else {
         return EMPTY_BUFFER;
      }
   }

   public static ByteBuf copyShort(short... var0) {
      if (var0 != null && var0.length != 0) {
         ByteBuf var1 = buffer(var0.length * 2);

         for (short var5 : var0) {
            var1.writeShort(var5);
         }

         return var1;
      } else {
         return EMPTY_BUFFER;
      }
   }

   public static ByteBuf buffer(int var0) {
      return ALLOC.heapBuffer(var0);
   }

   public static ByteBuf copiedBuffer(char[] var0, int var1, int var2, Charset var3) {
      if (var0 == null) {
         throw new NullPointerException("array");
      } else {
         return var2 == 0 ? EMPTY_BUFFER : copiedBuffer(CharBuffer.wrap(var0, var1, var2), var3);
      }
   }

   public static ByteBuf copyInt(int... var0) {
      if (var0 != null && var0.length != 0) {
         ByteBuf var1 = buffer(var0.length * 4);

         for (int var5 : var0) {
            var1.writeInt(var5);
         }

         return var1;
      } else {
         return EMPTY_BUFFER;
      }
   }

   public static ByteBuf copyBoolean(boolean... var0) {
      if (var0 != null && var0.length != 0) {
         ByteBuf var1 = buffer(var0.length);

         for (boolean var5 : var0) {
            var1.writeBoolean(var5);
         }

         return var1;
      } else {
         return EMPTY_BUFFER;
      }
   }

   public static ByteBuf wrappedBuffer(byte[] var0, int var1, int var2) {
      if (var2 == 0) {
         return EMPTY_BUFFER;
      } else {
         return var1 == 0 && var2 == var0.length ? wrappedBuffer(var0) : wrappedBuffer(var0).slice(var1, var2);
      }
   }

   public static CompositeByteBuf compositeBuffer(int var0) {
      return new CompositeByteBuf(ALLOC, false, var0);
   }

   public static ByteBuf copyDouble(double var0) {
      ByteBuf var2 = buffer(8);
      var2.writeDouble(var0);
      return var2;
   }

   public static ByteBuf directBuffer() {
      return ALLOC.directBuffer();
   }

   public static ByteBuf copyFloat(float... var0) {
      if (var0 != null && var0.length != 0) {
         ByteBuf var1 = buffer(var0.length * 4);

         for (float var5 : var0) {
            var1.writeFloat(var5);
         }

         return var1;
      } else {
         return EMPTY_BUFFER;
      }
   }

   public static ByteBuf copyInt(int var0) {
      ByteBuf var1 = buffer(4);
      var1.writeInt(var0);
      return var1;
   }

   public static ByteBuf wrappedBuffer(byte[] var0) {
      return (ByteBuf)(var0.length == 0 ? EMPTY_BUFFER : new UnpooledHeapByteBuf(ALLOC, var0, var0.length));
   }

   public static ByteBuf wrappedBuffer(int var0, ByteBuf... var1) {
      switch (var1.length) {
         case 0:
            break;
         case 1:
            if (var1[0].isReadable()) {
               return wrappedBuffer(var1[0].order(BIG_ENDIAN));
            }
            break;
         default:
            for (ByteBuf var5 : var1) {
               if (var5.isReadable()) {
                  return new CompositeByteBuf(ALLOC, false, var0, var1);
               }
            }
      }

      return EMPTY_BUFFER;
   }

   public static ByteBuf copyShort(int var0) {
      ByteBuf var1 = buffer(2);
      var1.writeShort(var0);
      return var1;
   }

   public static ByteBuf directBuffer(int var0) {
      return ALLOC.directBuffer(var0);
   }

   public static ByteBuf copiedBuffer(ByteBuffer var0) {
      int var1 = var0.remaining();
      if (var1 == 0) {
         return EMPTY_BUFFER;
      } else {
         byte[] var2 = new byte[var1];
         int var3 = var0.position();

         try {
            var0.get(var2);
         } finally {
            ((Buffer)var0).position(var3);
         }

         return wrappedBuffer(var2).order(var0.order());
      }
   }

   public static ByteBuf copiedBuffer(CharBuffer var0, Charset var1) {
      return ByteBufUtil.encodeString0(ALLOC, true, var0, var1);
   }

   public static ByteBuf copiedBuffer(CharSequence var0, int var1, int var2, Charset var3) {
      if (var0 == null) {
         throw new NullPointerException("string");
      } else if (var2 == 0) {
         return EMPTY_BUFFER;
      } else if (var0 instanceof CharBuffer) {
         CharBuffer var4 = (CharBuffer)var0;
         if (var4.hasArray()) {
            return copiedBuffer(var4.array(), var4.arrayOffset() + var4.position() + var1, var2, var3);
         } else {
            var4 = var4.slice();
            ((Buffer)var4).limit(var2);
            ((Buffer)var4).position(var1);
            return copiedBuffer(var4, var3);
         }
      } else {
         return copiedBuffer(CharBuffer.wrap(var0, var1, var1 + var2), var3);
      }
   }

   public static CompositeByteBuf compositeBuffer() {
      return compositeBuffer(16);
   }

   public static ByteBuf copyMedium(int... var0) {
      if (var0 != null && var0.length != 0) {
         ByteBuf var1 = buffer(var0.length * 3);

         for (int var5 : var0) {
            var1.writeMedium(var5);
         }

         return var1;
      } else {
         return EMPTY_BUFFER;
      }
   }

   public static ByteBuf wrappedBuffer(ByteBuf... var0) {
      return wrappedBuffer(16, var0);
   }

   public static ByteBuf wrappedBuffer(ByteBuffer var0) {
      if (!var0.hasRemaining()) {
         return EMPTY_BUFFER;
      } else if (var0.hasArray()) {
         return wrappedBuffer(var0.array(), var0.arrayOffset() + var0.position(), var0.remaining()).order(var0.order());
      } else if (PlatformDependent.hasUnsafe()) {
         if (var0.isReadOnly()) {
            return (ByteBuf)(var0.isDirect() ? new ReadOnlyUnsafeDirectByteBuf(ALLOC, var0) : new ReadOnlyByteBufferBuf(ALLOC, var0));
         } else {
            return new UnpooledUnsafeDirectByteBuf(ALLOC, var0, var0.remaining());
         }
      } else {
         return (ByteBuf)(var0.isReadOnly() ? new ReadOnlyByteBufferBuf(ALLOC, var0) : new UnpooledDirectByteBuf(ALLOC, var0, var0.remaining()));
      }
   }

   public static ByteBuf buffer(int var0, int var1) {
      return ALLOC.heapBuffer(var0, var1);
   }

   public static ByteBuf wrappedBuffer(int var0, ByteBuffer... var1) {
      switch (var1.length) {
         case 0:
            break;
         case 1:
            if (var1[0].hasRemaining()) {
               return wrappedBuffer(var1[0].order(BIG_ENDIAN));
            }
            break;
         default:
            ArrayList var2 = new ArrayList(var1.length);

            for (ByteBuffer var6 : var1) {
               if (var6 == null) {
                  break;
               }

               if (var6.remaining() > 0) {
                  var2.add(wrappedBuffer(var6.order(BIG_ENDIAN)));
               }
            }

            if (!var2.isEmpty()) {
               return new CompositeByteBuf(ALLOC, false, var0, var2);
            }
      }

      return EMPTY_BUFFER;
   }

   public static ByteBuf copyShort(int... var0) {
      if (var0 != null && var0.length != 0) {
         ByteBuf var1 = buffer(var0.length * 2);

         for (int var5 : var0) {
            var1.writeShort(var5);
         }

         return var1;
      } else {
         return EMPTY_BUFFER;
      }
   }

   public static ByteBuf copiedBuffer(byte[] var0) {
      return var0.length == 0 ? EMPTY_BUFFER : wrappedBuffer((byte[])var0.clone());
   }

   public static ByteBuf copyFloat(float var0) {
      ByteBuf var1 = buffer(4);
      var1.writeFloat(var0);
      return var1;
   }

   public static ByteBuf buffer() {
      return ALLOC.heapBuffer();
   }
}
