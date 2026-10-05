package io.netty.handler.codec.serialization;

import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.StreamCorruptedException;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.BlockWallSign$1;

public class ObjectDecoderInputStream extends InputStream implements ObjectInput {
   public ClassResolver classResolver;
   public DataInputStream in;
   public int maxObjectSize;
   public BlockCarrot __junk2193435060410165859;
   public BlockWallSign$1 __junk62375964288115282;
   public WebSocketServerHandshakerFactory __junk5036907113117098346;

   @Override
   public int readUnsignedByte() {
      return this.in.readUnsignedByte();
   }

   @Override
   public long skip(long var1) {
      return this.in.skip(var1);
   }

   @Override
   public void mark(int var1) {
      this.in.mark(var1);
   }

   public ObjectDecoderInputStream(InputStream var1, int var2) {
      this(var1, null, var2);
   }

   public ObjectDecoderInputStream(InputStream var1, ClassLoader var2) {
      this(var1, var2, 1048576);
   }

   @Override
   public int read(byte[] var1) {
      return this.in.read(var1);
   }

   @Override
   public Object readObject() {
      int var1 = this.readInt();
      if (var1 <= 0) {
         throw new StreamCorruptedException("invalid data length: " + var1);
      } else if (var1 > this.maxObjectSize) {
         throw new StreamCorruptedException("data length too big: " + var1 + " (max: " + this.maxObjectSize + ')');
      } else {
         return new CompactObjectInputStream(this.in, this.classResolver).readObject();
      }
   }

   @Override
   public boolean markSupported() {
      return this.in.markSupported();
   }

   @Override
   public boolean readBoolean() {
      return this.in.readBoolean();
   }

   @Override
   public double readDouble() {
      return this.in.readDouble();
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      return this.in.read(var1, var2, var3);
   }

   @Override
   public void close() {
      this.in.close();
   }

   public ObjectDecoderInputStream(InputStream var1) {
      this(var1, null);
   }

   public ObjectDecoderInputStream(InputStream var1, ClassLoader var2, int var3) {
      if (var1 == null) {
         throw new NullPointerException("in");
      } else if (var3 <= 0) {
         throw new IllegalArgumentException("maxObjectSize: " + var3);
      } else {
         if (var1 instanceof DataInputStream) {
            this.in = (DataInputStream)var1;
         } else {
            this.in = new DataInputStream(var1);
         }

         this.classResolver = ClassResolvers.weakCachingResolver(var2);
         this.maxObjectSize = var3;
      }
   }

   @Override
   public short readShort() {
      return this.in.readShort();
   }

   @Override
   public String readUTF() {
      return this.in.readUTF();
   }

   @Override
   public int readUnsignedShort() {
      return this.in.readUnsignedShort();
   }

   @Override
   public void readFully(byte[] var1, int var2, int var3) {
      this.in.readFully(var1, var2, var3);
   }

   @Override
   public int read() {
      return this.in.read();
   }

   @Override
   public String readLine() {
      return this.in.readLine();
   }

   @Override
   public int skipBytes(int var1) {
      return this.in.skipBytes(var1);
   }

   @Override
   public void readFully(byte[] var1) {
      this.in.readFully(var1);
   }

   @Override
   public char readChar() {
      return this.in.readChar();
   }

   @Override
   public float readFloat() {
      return this.in.readFloat();
   }

   @Override
   public byte readByte() {
      return this.in.readByte();
   }

   @Override
   public void reset() {
      this.in.reset();
   }

   @Override
   public int available() {
      return this.in.available();
   }

   @Override
   public int readInt() {
      return this.in.readInt();
   }

   @Override
   public long readLong() {
      return this.in.readLong();
   }
}
