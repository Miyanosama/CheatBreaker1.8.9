package io.netty.handler.codec.marshalling;

import io.netty.buffer.PooledUnsafeDirectByteBuf;
import net.minecraft.client.renderer.block.statemap.StateMap$1;
import net.optifine.util.KeyUtils;
import org.apache.log4j.HTMLLayout;
import org.jboss.marshalling.ByteInput;

public class LimitingByteInput implements ByteInput {
   public ByteInput input;
   public long read;
   public HTMLLayout __junk8402272296801174;
   public long limit;
   public StateMap$1 __junk2777828590890280143;
   public PooledUnsafeDirectByteBuf __junk7570480448398154441;
   public KeyUtils __junk6205810457065682855;
   public static LimitingByteInput$TooBigObjectException EXCEPTION = new LimitingByteInput$TooBigObjectException();

   public int read() {
      int var1 = this.readable(1);
      if (var1 > 0) {
         int var2 = this.input.read();
         this.read += 12683373L & -8359792186507656703L;
         return var2;
      } else {
         throw EXCEPTION;
      }
   }

   public int readable(int var1) {
      return (int)Math.min((long)var1, this.limit - this.read);
   }

   public LimitingByteInput(ByteInput var1, long var2) {
      if (var2 <= (-6051760609626679807L & 6051760608679618816L)) {
         throw new IllegalArgumentException("The limit MUST be > 0");
      } else {
         this.input = var1;
         this.limit = var2;
      }
   }

   public int read(byte[] var1, int var2, int var3) {
      int var4 = this.readable(var3);
      if (var4 > 0) {
         int var5 = this.input.read(var1, var2, var4);
         this.read += var5;
         return var5;
      } else {
         throw EXCEPTION;
      }
   }

   public long skip(long var1) {
      int var3 = this.readable((int)var1);
      if (var3 > 0) {
         long var4 = this.input.skip(var3);
         this.read += var4;
         return var4;
      } else {
         throw EXCEPTION;
      }
   }

   public int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public void close() {
   }

   public int available() {
      return this.readable(this.input.available());
   }
}
