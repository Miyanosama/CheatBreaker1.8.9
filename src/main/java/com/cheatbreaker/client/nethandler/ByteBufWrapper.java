package com.cheatbreaker.client.nethandler;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ByteBufWrapper {
   public ByteBuf recoveredField3501;

   public int readVarInt() {
      int var1 = 0;
      int var2 = 0;

      byte var3;
      do {
         var3 = this.recoveredField3501.readByte();
         var1 |= (var3 & 127) << var2++ * 7;
         if (var2 > 5) {
            throw new RuntimeException("VarInt too big");
         }
      } while ((var3 & 128) == 128);

      return var1;
   }

   public <T> void writeOptional(T var1, Consumer<T> var2) {
      this.recoveredField3501.writeBoolean(var1 != null);
      if (var1 != null) {
         var2.accept(var1);
      }
   }

   public String readString() {
      int var1 = this.readVarInt();
      byte[] var2 = new byte[var1];
      this.recoveredField3501.readBytes(var2);
      return new String(var2, Charsets.UTF_8);
   }

   public UUID readUUID() {
      long var1 = this.recoveredField3501.readLong();
      long var3 = this.recoveredField3501.readLong();
      return new UUID(var1, var3);
   }

   public void writeString(String var1) {
      byte[] var2 = var1.getBytes(Charsets.UTF_8);
      this.writeVarInt(var2.length);
      this.recoveredField3501.writeBytes(var2);
   }

   public ByteBufWrapper(ByteBuf var1) {
      this.recoveredField3501 = var1;
   }

   public ByteBuf buf() {
      return this.recoveredField3501;
   }

   public void writeUUID(UUID var1) {
      this.recoveredField3501.writeLong(var1.getMostSignificantBits());
      this.recoveredField3501.writeLong(var1.getLeastSignificantBits());
   }

   public <T> T readOptional(Supplier<T> var1) {
      boolean var2 = this.recoveredField3501.readBoolean();
      return (T)(var2 ? var1.get() : null);
   }

   public void writeVarInt(int var1) {
      while ((var1 & -128) != 0) {
         this.recoveredField3501.writeByte(var1 & 127 | 128);
         var1 >>>= 7;
      }

      this.recoveredField3501.writeByte(var1);
   }
}
