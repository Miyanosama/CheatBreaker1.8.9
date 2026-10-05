package io.netty.buffer;

import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.util.ResourceLeak;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import net.minecraft.client.gui.GuiChat;
import net.optifine.config.RangeInt;

public abstract class AbstractByteBufAllocator implements ByteBufAllocator {
   public boolean directByDefault;
   public static final int DEFAULT_INITIAL_CAPACITY = 256;
   public static final int DEFAULT_MAX_COMPONENTS = 16;
   public ByteBuf emptyBuf;

   @Override
   public ByteBuf heapBuffer(int var1) {
      return this.heapBuffer(var1, Integer.MAX_VALUE);
   }

   public static ByteBuf toLeakAwareBuffer(ByteBuf var0) {
      switch (ResourceLeakDetector.getLevel()) {
         case SIMPLE:
            ResourceLeak var2 = AbstractByteBuf.leakDetector.open((ByteBuf)var0);
            if (var2 != null) {
               var0 = new SimpleLeakAwareByteBuf((ByteBuf)var0, var2);
            }
            break;
         case ADVANCED:
         case PARANOID:
            ResourceLeak var1 = AbstractByteBuf.leakDetector.open((ByteBuf)var0);
            if (var1 != null) {
               var0 = new AdvancedLeakAwareByteBuf((ByteBuf)var0, var1);
            }
      }

      return (ByteBuf)var0;
   }

   public AbstractByteBufAllocator(boolean var1) {
      this.directByDefault = var1 && PlatformDependent.hasUnsafe();
      this.emptyBuf = new EmptyByteBuf(this);
   }

   @Override
   public CompositeByteBuf compositeDirectBuffer() {
      return this.compositeDirectBuffer(16);
   }

   @Override
   public CompositeByteBuf compositeBuffer() {
      return this.directByDefault ? this.compositeDirectBuffer() : this.compositeHeapBuffer();
   }

   @Override
   public CompositeByteBuf compositeHeapBuffer(int var1) {
      return new CompositeByteBuf(this, false, var1);
   }

   @Override
   public ByteBuf heapBuffer(int var1, int var2) {
      if (var1 == 0 && var2 == 0) {
         return this.emptyBuf;
      } else {
         validate(var1, var2);
         return this.newHeapBuffer(var1, var2);
      }
   }

   @Override
   public ByteBuf ioBuffer(int var1, int var2) {
      return PlatformDependent.hasUnsafe() ? this.directBuffer(var1, var2) : this.heapBuffer(var1, var2);
   }

   public static void validate(int var0, int var1) {
      if (var0 < 0) {
         throw new IllegalArgumentException("initialCapacity: " + var0 + " (expectd: 0+)");
      } else if (var0 > var1) {
         throw new IllegalArgumentException(String.format("initialCapacity: %d (expected: not greater than maxCapacity(%d)", var0, var1));
      }
   }

   @Override
   public CompositeByteBuf compositeBuffer(int var1) {
      return this.directByDefault ? this.compositeDirectBuffer(var1) : this.compositeHeapBuffer(var1);
   }

   @Override
   public CompositeByteBuf compositeDirectBuffer(int var1) {
      return new CompositeByteBuf(this, true, var1);
   }

   @Override
   public ByteBuf ioBuffer(int var1) {
      return PlatformDependent.hasUnsafe() ? this.directBuffer(var1) : this.heapBuffer(var1);
   }

   @Override
   public ByteBuf heapBuffer() {
      return this.heapBuffer(256, Integer.MAX_VALUE);
   }

   @Override
   public ByteBuf buffer() {
      return this.directByDefault ? this.directBuffer() : this.heapBuffer();
   }

   public AbstractByteBufAllocator() {
      this(false);
   }

   @Override
   public CompositeByteBuf compositeHeapBuffer() {
      return this.compositeHeapBuffer(16);
   }

   @Override
   public ByteBuf directBuffer() {
      return this.directBuffer(256, Integer.MAX_VALUE);
   }

   @Override
   public ByteBuf buffer(int var1) {
      return this.directByDefault ? this.directBuffer(var1) : this.heapBuffer(var1);
   }

   @Override
   public ByteBuf directBuffer(int var1) {
      return this.directBuffer(var1, Integer.MAX_VALUE);
   }

   public abstract ByteBuf newHeapBuffer(int var1, int var2);

   @Override
   public ByteBuf directBuffer(int var1, int var2) {
      if (var1 == 0 && var2 == 0) {
         return this.emptyBuf;
      } else {
         validate(var1, var2);
         return this.newDirectBuffer(var1, var2);
      }
   }

   @Override
   public ByteBuf buffer(int var1, int var2) {
      return this.directByDefault ? this.directBuffer(var1, var2) : this.heapBuffer(var1, var2);
   }

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this) + "(directByDefault: " + this.directByDefault + ')';
   }

   public abstract ByteBuf newDirectBuffer(int var1, int var2);

   @Override
   public ByteBuf ioBuffer() {
      return PlatformDependent.hasUnsafe() ? this.directBuffer(256) : this.heapBuffer(256);
   }
}
