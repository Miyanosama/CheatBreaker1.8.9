package org.java_websocket;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SocketChannel;
import net.minecraft.client.stream.TwitchStream$1$1;
import net.minecraft.nbt.NBTTagLong;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$2;

public class AbstractWrappedByteChannel implements WrappedByteChannel {
   public LogBrokerMonitor$2 field_0001;
   public TwitchStream$1$1 field_0003;
   public ByteChannel channel;
   public NBTTagLong field_0002;

   public AbstractWrappedByteChannel(WrappedByteChannel var1) {
      this.channel = var1;
   }

   @Override
   public int read(ByteBuffer var1) {
      return this.channel.read(var1);
   }

   @Override
   public int readMore(ByteBuffer var1) {
      return this.channel instanceof WrappedByteChannel ? ((WrappedByteChannel)this.channel).readMore(var1) : 0;
   }

   @Override
   public void close() {
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
   public int write(ByteBuffer var1) {
      return this.channel.write(var1);
   }

   @Override
   public void writeMore() {
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
