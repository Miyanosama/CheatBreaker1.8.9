package io.netty.handler.codec.marshalling;

import com.cheatbreaker.client.ui.mainmenu.LegacyMainMenu;
import io.netty.buffer.PooledUnsafeDirectByteBuf;
import java.io.IOException;
import net.optifine.model.ModelSprite;
import net.optifine.util.KeyUtils;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.helpers.BoundedFIFO;
import org.jboss.marshalling.ByteInput;

public class LimitingByteInput implements ByteInput {
   public ByteInput input;
   public long read;
   public long limit;
   public static LimitingByteInput.TooBigObjectException EXCEPTION = new LimitingByteInput.TooBigObjectException();

   @Override
   public int read() throws java.io.IOException {
      int var1 = this.readable(1);
      if (var1 > 0) {
         int var2 = this.input.read();
         this.read++;
         return var2;
      } else {
         throw EXCEPTION;
      }
   }

   public int readable(int var1) {
      return (int)Math.min((long)var1, this.limit - this.read);
   }

   public LimitingByteInput(ByteInput var1, long var2) {
      if (var2 <= 0L) {
         throw new IllegalArgumentException("The limit MUST be > 0");
      } else {
         this.input = var1;
         this.limit = var2;
      }
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws java.io.IOException {
      int var4 = this.readable(var3);
      if (var4 > 0) {
         int var5 = this.input.read(var1, var2, var4);
         this.read += var5;
         return var5;
      } else {
         throw EXCEPTION;
      }
   }

   @Override
   public long skip(long var1) throws java.io.IOException {
      int var3 = this.readable((int)var1);
      if (var3 > 0) {
         long var4 = this.input.skip(var3);
         this.read += var4;
         return var4;
      } else {
         throw EXCEPTION;
      }
   }

   @Override
   public int read(byte[] var1) throws java.io.IOException {
      return this.read(var1, 0, var1.length);
   }

   @Override
   public void close() throws java.io.IOException {
   }

   @Override
   public int available() throws java.io.IOException {
      return this.readable(this.input.available());
   }

   public static final class TooBigObjectException extends IOException {
      public static final long serialVersionUID = 1L;
   }
}
