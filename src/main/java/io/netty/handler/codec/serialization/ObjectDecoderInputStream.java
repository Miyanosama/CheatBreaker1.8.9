package io.netty.handler.codec.serialization;

import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.StreamCorruptedException;
import net.minecraft.block.BlockCarrot;

public class ObjectDecoderInputStream extends InputStream implements ObjectInput {
   public ClassResolver classResolver;
   public DataInputStream in;
   public int maxObjectSize;

   @Override
   public int readUnsignedByte() throws java.io.IOException {
      return this.in.readUnsignedByte();
   }

   @Override
   public long skip(long var1) throws java.io.IOException {
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
   public int read(byte[] var1) throws java.io.IOException {
      return this.in.read(var1);
   }

   @Override
   public Object readObject() throws java.lang.ClassNotFoundException, java.io.IOException {
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
   public boolean readBoolean() throws java.io.IOException {
      return this.in.readBoolean();
   }

   @Override
   public double readDouble() throws java.io.IOException {
      return this.in.readDouble();
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws java.io.IOException {
      return this.in.read(var1, var2, var3);
   }

   @Override
   public void close() throws java.io.IOException {
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
   public short readShort() throws java.io.IOException {
      return this.in.readShort();
   }

   @Override
   public String readUTF() throws java.io.IOException {
      return this.in.readUTF();
   }

   @Override
   public int readUnsignedShort() throws java.io.IOException {
      return this.in.readUnsignedShort();
   }

   @Override
   public void readFully(byte[] var1, int var2, int var3) throws java.io.IOException {
      this.in.readFully(var1, var2, var3);
   }

   @Override
   public int read() throws java.io.IOException {
      return this.in.read();
   }

   @Override
   public String readLine() throws java.io.IOException {
      return this.in.readLine();
   }

   @Override
   public int skipBytes(int var1) throws java.io.IOException {
      return this.in.skipBytes(var1);
   }

   @Override
   public void readFully(byte[] var1) throws java.io.IOException {
      this.in.readFully(var1);
   }

   @Override
   public char readChar() throws java.io.IOException {
      return this.in.readChar();
   }

   @Override
   public float readFloat() throws java.io.IOException {
      return this.in.readFloat();
   }

   @Override
   public byte readByte() throws java.io.IOException {
      return this.in.readByte();
   }

   @Override
   public void reset() throws java.io.IOException {
      this.in.reset();
   }

   @Override
   public int available() throws java.io.IOException {
      return this.in.available();
   }

   @Override
   public int readInt() throws java.io.IOException {
      return this.in.readInt();
   }

   @Override
   public long readLong() throws java.io.IOException {
      return this.in.readLong();
   }
}
