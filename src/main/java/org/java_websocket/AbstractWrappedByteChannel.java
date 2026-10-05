package org.java_websocket;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SocketChannel;

public class AbstractWrappedByteChannel implements WrappedByteChannel {
   public ByteChannel channel;

   public AbstractWrappedByteChannel(WrappedByteChannel var1) {
      this.channel = var1;
   }

   @Override
   public int read(ByteBuffer var1) throws java.io.IOException {
      return this.channel.read(var1);
   }

   @Override
   public int readMore(ByteBuffer var1) throws java.io.IOException {
      return this.channel instanceof WrappedByteChannel ? ((WrappedByteChannel)this.channel).readMore(var1) : 0;
   }

   @Override
   public void close() throws java.io.IOException {
      this.channel.close();
   }

   @Override
   public boolean isNeedRead() {
      return this.channel instanceof WrappedByteChannel && ((WrappedByteChannel)this.channel).isNeedRead();
   }

   public AbstractWrappedByteChannel(ByteChannel var1) {
      this.channel = var1;
   }

   @Override
   public boolean isOpen() {
      return this.channel.isOpen();
   }

   @Override
   public int write(ByteBuffer var1) throws java.io.IOException {
      return this.channel.write(var1);
   }

   @Override
   public void writeMore() throws java.io.IOException {
      if (this.channel instanceof WrappedByteChannel) {
         ((WrappedByteChannel)this.channel).writeMore();
      }
   }

   @Override
   public boolean isBlocking() {
      if (this.channel instanceof SocketChannel) {
         return ((SocketChannel)this.channel).isBlocking();
      } else {
         return this.channel instanceof WrappedByteChannel ? ((WrappedByteChannel)this.channel).isBlocking() : false;
      }
   }

   @Override
   public boolean isNeedWrite() {
      return this.channel instanceof WrappedByteChannel && ((WrappedByteChannel)this.channel).isNeedWrite();
   }
}
