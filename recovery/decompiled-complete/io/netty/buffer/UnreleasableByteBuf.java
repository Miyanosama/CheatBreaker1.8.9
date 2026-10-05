package io.netty.buffer;

import java.nio.ByteOrder;
import net.minecraft.stats.Achievement;

public class UnreleasableByteBuf extends WrappedByteBuf {
   public SwappedByteBuf swappedBuf;
   public Achievement __junk4686741797774225564;

   @Override
   public ByteBuf retain() {
      return this;
   }

   @Override
   public ByteBuf retain(int var1) {
      return this;
   }

   @Override
   public boolean release(int var1) {
      return false;
   }

   public UnreleasableByteBuf(ByteBuf var1) {
      super(var1);
   }

   @Override
   public ByteBuf order(ByteOrder var1) {
      if (var1 == null) {
         throw new NullPointerException("endianness");
      } else if (var1 == this.order()) {
         return this;
      } else {
         SwappedByteBuf var2 = this.swappedBuf;
         if (var2 == null) {
            this.swappedBuf = var2 = new SwappedByteBuf(this);
         }

         return var2;
      }
   }

   @Override
   public boolean release() {
      return false;
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      return new UnreleasableByteBuf(this.buf.slice(var1, var2));
   }

   @Override
   public ByteBuf duplicate() {
      return new UnreleasableByteBuf(this.buf.duplicate());
   }

   @Override
   public ByteBuf readSlice(int var1) {
      return new UnreleasableByteBuf(this.buf.readSlice(var1));
   }

   @Override
   public ByteBuf slice() {
      return new UnreleasableByteBuf(this.buf.slice());
   }
}
