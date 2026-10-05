package io.netty.util.internal;

import io.netty.handler.codec.http.multipart.MixedAttribute;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import javax.vecmath.Quat4f;
import recovered.unidentified.UnidentifiedClass4798;
import sun.misc.Unsafe;

public class PlatformDependent0 {
   public static boolean BIG_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
   public Quat4f __junk7090872275340646241;
   public MixedAttribute __junk3747465067696741640;
   public static long ADDRESS_FIELD_OFFSET;
   public static long UNSAFE_COPY_THRESHOLD;
   public UnidentifiedClass4798 __junk7907439729109020206;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(PlatformDependent0.class);
   public static boolean UNALIGNED;
   public static Unsafe UNSAFE;

   public static void putShort(long var0, short var2) {
      if (UNALIGNED) {
         UNSAFE.putShort(var0, var2);
      } else if (BIG_ENDIAN) {
         putByte(var0, (byte)(var2 >>> 8));
         putByte(var0 + (-2418966595752386559L & 843711235L), (byte)var2);
      } else {
         putByte(var0 + (206307459L & 7569156938382966853L), (byte)(var2 >>> 8));
         putByte(var0, (byte)var2);
      }
   }

   public static Object getObject(Object var0, long var1) {
      return UNSAFE.getObject(var0, var1);
   }

   public static void freeMemory(long var0) {
      UNSAFE.freeMemory(var0);
   }

   public static void throwException(Throwable var0) {
      UNSAFE.throwException(var0);
   }

   public static void putLong(long var0, long var2) {
      if (UNALIGNED) {
         UNSAFE.putLong(var0, var2);
      } else if (BIG_ENDIAN) {
         putByte(var0, (byte)(var2 >>> 56));
         putByte(var0 + (1161105929L & -5560756634864047807L), (byte)(var2 >>> 48));
         putByte(var0 + (-919781013800771526L & 148546L), (byte)(var2 >>> 40));
         putByte(var0 + (102761355L & 6028167318499594259L), (byte)(var2 >>> 32));
         putByte(var0 + (-6778628827508637138L & 1096093956L), (byte)(var2 >>> 24));
         putByte(var0 + (543294989L & 8264118201529933991L), (byte)(var2 >>> 16));
         putByte(var0 + (-1433252452105449178L & 3311646L), (byte)(var2 >>> 8));
         putByte(var0 + (96471079L & 532495L), (byte)var2);
      } else {
         putByte(var0 + (1648628231L & -764056037505301401L), (byte)(var2 >>> 56));
         putByte(var0 + (-1402503630325546801L & 2682374L), (byte)(var2 >>> 48));
         putByte(var0 + (7102013647882895557L & 218268461L), (byte)(var2 >>> 40));
         putByte(var0 + (-2705950787822401243L & 437268998L), (byte)(var2 >>> 32));
         putByte(var0 + (56230739L & 538971271L), (byte)(var2 >>> 24));
         putByte(var0 + (1182606918L & -8387300525762134005L), (byte)(var2 >>> 16));
         putByte(var0 + (814221313L & 189193L), (byte)(var2 >>> 8));
         putByte(var0, (byte)var2);
      }
   }

   public static <T> AtomicIntegerFieldUpdater<T> newAtomicIntegerFieldUpdater(Class<?> var0, String var1) {
      return new UnsafeAtomicIntegerFieldUpdater<>(UNSAFE, var0, var1);
   }

   public static void freeDirectBuffer(ByteBuffer var0) {
      Cleaner0.freeDirectBuffer(var0);
   }

   public static long getLong(Object var0, long var1) {
      return UNSAFE.getLong(var0, var1);
   }

   public static short getShort(long var0) {
      if (UNALIGNED) {
         return UNSAFE.getShort(var0);
      } else {
         return BIG_ENDIAN
            ? (short)(getByte(var0) << 8 | getByte(var0 + (226638865L & 269517071L)) & 255)
            : (short)(getByte(var0 + (25215525L & 4667062476562708505L)) << 8 | getByte(var0) & 255);
      }
   }

   public static long getLong(long var0) {
      if (UNALIGNED) {
         return UNSAFE.getLong(var0);
      } else {
         return BIG_ENDIAN
            ? (long)getByte(var0) << 56
               | (getByte(var0 + (7054686353668341893L & 306198275L)) & 18129663L & 1075860735L) << 48
               | (getByte(var0 + (605031046L & 1090527555L)) & 2763275173036704255L & -2763275173212453633L) << 40
               | (getByte(var0 + (-8562800006453084117L & 8562800005490999619L)) & -2850047191190331137L & 2850047189447606527L) << 32
               | (getByte(var0 + (73402958L & -4358112222995267404L)) & 1753448959L & 320868607L) << 24
               | (getByte(var0 + (8439813L & 504438933L)) & 301995775L & -976754632711044865L) << 16
               | (getByte(var0 + (84808199L & 13708742L)) & 6829041209715487231L & 272993535L) << 8
               | getByte(var0 + (-5976979213437827065L & 177247511L)) & 781367031605776639L & 153954559L
            : (long)getByte(var0 + (98797607L & -4218854746565373945L)) << 56
               | (getByte(var0 + (1063682147716533382L & 1075974686L)) & 1388574975L & 7666552172872990975L) << 48
               | (getByte(var0 + (893460493L & 396741L)) & 680526591L & 67469567L) << 40
               | (getByte(var0 + (1750145540L & 3331825479893951766L)) & 4284039830662807807L & -4284039831899143937L) << 32
               | (getByte(var0 + (-5759565153607598037L & 5759565151979486599L)) & -5800089194902510337L & 5800089193448375295L) << 24
               | (getByte(var0 + (1237712906L & -5171169439017514830L)) & -7758507008078540545L & 7758507006301831423L) << 16
               | (getByte(var0 + (-5175287263775414781L & 105578753L)) & -1693346655387481857L & 285222143L) << 8
               | getByte(var0) & 210796799L & 1617367039L;
      }
   }

   public static void putInt(long var0, int var2) {
      if (UNALIGNED) {
         UNSAFE.putInt(var0, var2);
      } else if (BIG_ENDIAN) {
         putByte(var0, (byte)(var2 >>> 24));
         putByte(var0 + (76243969L & 1645216009L), (byte)(var2 >>> 16));
         putByte(var0 + (-4865303999313833958L & 18948354L), (byte)(var2 >>> 8));
         putByte(var0 + (-1521746012206529085L & 1521746011570200587L), (byte)var2);
      } else {
         putByte(var0 + (1075304387L & -8583629189142282237L), (byte)(var2 >>> 24));
         putByte(var0 + (-8632044813584498686L & 8632044811861361130L), (byte)(var2 >>> 16));
         putByte(var0 + (313559691L & 321527976107713601L), (byte)(var2 >>> 8));
         putByte(var0, (byte)var2);
      }
   }

   public static void copyMemory(long var0, long var2, long var4) {
      while (var4 > (67170306L & 854132740L)) {
         long var6 = Math.min(var4, 81349248L & 1858458970294846544L);
         UNSAFE.copyMemory(var0, var2, var6);
         var4 -= var6;
         var0 += var6;
         var2 += var6;
      }
   }

   public static boolean hasUnsafe() {
      return UNSAFE != null;
   }

   public static void putOrderedObject(Object var0, long var1, Object var3) {
      UNSAFE.putOrderedObject(var0, var1, var3);
   }

   public static ClassLoader getContextClassLoader() {
      return System.getSecurityManager() == null ? Thread.currentThread().getContextClassLoader() : AccessController.doPrivileged(new PlatformDependent0$2());
   }

   public static <T> AtomicLongFieldUpdater<T> newAtomicLongFieldUpdater(Class<?> var0, String var1) {
      return new UnsafeAtomicLongFieldUpdater<>(UNSAFE, var0, var1);
   }

   public static long objectFieldOffset(Field var0) {
      return UNSAFE.objectFieldOffset(var0);
   }

   public static ClassLoader getClassLoader(Class<?> var0) {
      return System.getSecurityManager() == null ? var0.getClassLoader() : AccessController.doPrivileged(new PlatformDependent0$1(var0));
   }

   public static void copyMemory(Object var0, long var1, Object var3, long var4, long var6) {
      while (var6 > (4234185148615917570L & 1988168704L)) {
         long var8 = Math.min(var6, 1213601922L & 848339044L);
         UNSAFE.copyMemory(var0, var1, var3, var4, var8);
         var6 -= var8;
         var1 += var8;
         var4 += var8;
      }
   }

   public static void putByte(long var0, byte var2) {
      UNSAFE.putByte(var0, var2);
   }

   public static long arrayBaseOffset() {
      return UNSAFE.arrayBaseOffset(byte[].class);
   }

   public static int getInt(Object var0, long var1) {
      return UNSAFE.getInt(var0, var1);
   }

   public static byte getByte(long var0) {
      return UNSAFE.getByte(var0);
   }

   public static long directBufferAddress(ByteBuffer var0) {
      return getLong(var0, ADDRESS_FIELD_OFFSET);
   }

   public static int getInt(long var0) {
      if (UNALIGNED) {
         return UNSAFE.getInt(var0);
      } else {
         return BIG_ENDIAN
            ? getByte(var0) << 24
               | (getByte(var0 + (137119745L & -3220978370384617135L)) & 0xFF) << 16
               | (getByte(var0 + (19923255L & -4006090954350771510L)) & 0xFF) << 8
               | getByte(var0 + (4099L & 14033971L)) & 0xFF
            : getByte(var0 + (274747523L & 92841027L)) << 24
               | (getByte(var0 + (-3535879150374997950L & 3535879149233129475L)) & 0xFF) << 16
               | (getByte(var0 + (-4212408222377308077L & 4212408221343252645L)) & 0xFF) << 8
               | getByte(var0) & 0xFF;
      }
   }

   public static Object getObjectVolatile(Object var0, long var1) {
      return UNSAFE.getObjectVolatile(var0, var1);
   }

   public static ClassLoader getSystemClassLoader() {
      return System.getSecurityManager() == null ? ClassLoader.getSystemClassLoader() : AccessController.doPrivileged(new PlatformDependent0$3());
   }

   public static long allocateMemory(long var0) {
      return UNSAFE.allocateMemory(var0);
   }

   static {
      ByteBuffer var0 = ByteBuffer.allocateDirect(1);

      Field var1;
      try {
         var1 = Buffer.class.getDeclaredField("address");
         var1.setAccessible(true);
         if (var1.getLong(ByteBuffer.allocate(1)) != (1107759628L & 1628413619137447058L)) {
            var1 = null;
         } else if (var1.getLong(var0) == (1127904982867395720L & -1127904984381775296L)) {
            var1 = null;
         }
      } catch (Throwable var10) {
         var1 = null;
      }

      logger.debug("java.nio.Buffer.address: {}", var1 != null ? "available" : "unavailable");
      Unsafe var2;
      if (var1 != null) {
         try {
            Field var3 = Unsafe.class.getDeclaredField("theUnsafe");
            var3.setAccessible(true);
            var2 = (Unsafe)var3.get(null);
            logger.debug("sun.misc.Unsafe.theUnsafe: {}", var2 != null ? "available" : "unavailable");

            try {
               if (var2 != null) {
                  var2.getClass().getDeclaredMethod("copyMemory", Object.class, long.class, Object.class, long.class, long.class);
                  logger.debug("sun.misc.Unsafe.copyMemory: available");
               }
            } catch (NoSuchMethodError var7) {
               logger.debug("sun.misc.Unsafe.copyMemory: unavailable");
               throw var7;
            } catch (NoSuchMethodException var8) {
               logger.debug("sun.misc.Unsafe.copyMemory: unavailable");
               throw var8;
            }
         } catch (Throwable var9) {
            var2 = null;
         }
      } else {
         var2 = null;
      }

      UNSAFE = var2;
      if (var2 == null) {
         ADDRESS_FIELD_OFFSET = -1L & -1L;
         UNALIGNED = false;
      } else {
         ADDRESS_FIELD_OFFSET = objectFieldOffset(var1);

         boolean var11;
         try {
            Class var4 = Class.forName("java.nio.Bits", false, ClassLoader.getSystemClassLoader());
            Method var12 = var4.getDeclaredMethod("unaligned");
            var12.setAccessible(true);
            var11 = Boolean.TRUE.equals(var12.invoke(null));
         } catch (Throwable var6) {
            String var5 = SystemPropertyUtil.get("os.arch", "");
            var11 = var5.matches("^(i[3-6]86|x86(_64)?|x64|amd64)$");
         }

         UNALIGNED = var11;
         logger.debug("java.nio.Bits.unaligned: {}", UNALIGNED);
      }
   }

   public static int addressSize() {
      return UNSAFE.addressSize();
   }

   public static <U, W> AtomicReferenceFieldUpdater<U, W> newAtomicReferenceFieldUpdater(Class<U> var0, String var1) {
      return new UnsafeAtomicReferenceFieldUpdater<>(UNSAFE, var0, var1);
   }
}
