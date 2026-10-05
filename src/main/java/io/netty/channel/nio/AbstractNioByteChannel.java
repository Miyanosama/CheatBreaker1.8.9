package io.netty.channel.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.FileRegion;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.UnsafeAtomicReferenceFieldUpdater;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import junit.swingui.TestRunner$13;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.optifine.gui.GuiQualitySettingsOF;

public abstract class AbstractNioByteChannel extends AbstractNioChannel {
   public Runnable flushTask;
   public static String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName(ByteBuf.class) + ", " + StringUtil.simpleClassName(FileRegion.class) + ')';

   @Override
   public Object filterOutboundMessage(Object var1) {
      if (var1 instanceof ByteBuf) {
         ByteBuf var2 = (ByteBuf)var1;
         return var2.isDirect() ? var1 : this.newDirectBuffer(var2);
      } else if (var1 instanceof FileRegion) {
         return var1;
      } else {
         throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(var1) + EXPECTED_TYPES);
      }
   }

   public void setOpWrite() {
      SelectionKey var1 = this.selectionKey();
      if (var1.isValid()) {
         int var2 = var1.interestOps();
         if ((var2 & 4) == 0) {
            var1.interestOps(var2 | 4);
         }
      }
   }

   public AbstractNioByteChannel(Channel var1, SelectableChannel var2) {
      super(var1, var2, 1);
   }

   public abstract int doReadBytes(ByteBuf var1) throws java.lang.Exception ;

   @Override
   public void doWrite(ChannelOutboundBuffer var1) throws java.lang.Exception {
      int var2 = -1;

      while (true) {
         Object var3 = var1.current();
         if (var3 == null) {
            this.clearOpWrite();
            break;
         }

         if (var3 instanceof ByteBuf) {
            ByteBuf var12 = (ByteBuf)var3;
            int var13 = var12.readableBytes();
            if (var13 == 0) {
               var1.remove();
            } else {
               boolean var14 = false;
               boolean var15 = false;
               long var8 = 0L;
               if (var2 == -1) {
                  var2 = this.config().getWriteSpinCount();
               }

               for (int var16 = var2 - 1; var16 >= 0; var16--) {
                  int var11 = this.doWriteBytes(var12);
                  if (var11 == 0) {
                     var14 = true;
                     break;
                  }

                  var8 += var11;
                  if (!var12.isReadable()) {
                     var15 = true;
                     break;
                  }
               }

               var1.progress(var8);
               if (!var15) {
                  this.incompleteWrite(var14);
                  break;
               }

               var1.remove();
            }
         } else {
            if (!(var3 instanceof FileRegion)) {
               throw new Error();
            }

            FileRegion var4 = (FileRegion)var3;
            boolean var5 = false;
            boolean var6 = false;
            long var7 = 0L;
            if (var2 == -1) {
               var2 = this.config().getWriteSpinCount();
            }

            for (int var9 = var2 - 1; var9 >= 0; var9--) {
               long var10 = this.doWriteFileRegion(var4);
               if (var10 == 0L) {
                  var5 = true;
                  break;
               }

               var7 += var10;
               if (var4.transfered() >= var4.count()) {
                  var6 = true;
                  break;
               }
            }

            var1.progress(var7);
            if (!var6) {
               this.incompleteWrite(var5);
               break;
            }

            var1.remove();
         }
      }
   }

   public abstract int doWriteBytes(ByteBuf var1) throws java.lang.Exception ;

   public void incompleteWrite(boolean var1) {
      if (var1) {
         this.setOpWrite();
      } else {
         Runnable var2 = this.flushTask;
         if (var2 == null) {
            var2 = this.flushTask = new Runnable() {

               @Override
               public void run() {
                  AbstractNioByteChannel.this.flush();
               }
            };
         }

         this.eventLoop().execute(var2);
      }
   }

   public AbstractNioChannel.AbstractNioUnsafe newUnsafe() {
      return new AbstractNioByteChannel.NioByteUnsafe();
   }

   public void clearOpWrite() {
      SelectionKey var1 = this.selectionKey();
      if (var1.isValid()) {
         int var2 = var1.interestOps();
         if ((var2 & 4) != 0) {
            var1.interestOps(var2 & -5);
         }
      }
   }

   public abstract long doWriteFileRegion(FileRegion var1) throws java.lang.Exception ;

   public final class NioByteUnsafe extends AbstractNioChannel.AbstractNioUnsafe {
      public RecvByteBufAllocator.Handle allocHandle;

      public NioByteUnsafe() {
      }

      @Override
      public void read() {
         ChannelConfig var1 = AbstractNioByteChannel.this.config();
         if (!var1.isAutoRead() && !AbstractNioByteChannel.this.isReadPending()) {
            this.removeReadOp();
         } else {
            ChannelPipeline var2 = AbstractNioByteChannel.this.pipeline();
            ByteBufAllocator var3 = var1.getAllocator();
            int var4 = var1.getMaxMessagesPerRead();
            RecvByteBufAllocator.Handle var5 = this.allocHandle;
            if (var5 == null) {
               this.allocHandle = var5 = var1.getRecvByteBufAllocator().newHandle();
            }

            ByteBuf var6 = null;
            int var7 = 0;
            boolean var8 = false;

            try {
               int var9 = 0;
               boolean var10 = false;

               int var11;
               int var12;
               do {
                  var6 = var5.allocate(var3);
                  var11 = var6.writableBytes();
                  var12 = AbstractNioByteChannel.this.doReadBytes(var6);
                  if (var12 <= 0) {
                     var6.release();
                     var8 = var12 < 0;
                     break;
                  }

                  if (!var10) {
                     var10 = true;
                     AbstractNioByteChannel.this.setReadPending(false);
                  }

                  var2.fireChannelRead(var6);
                  var6 = null;
                  if (var9 >= Integer.MAX_VALUE - var12) {
                     var9 = Integer.MAX_VALUE;
                     break;
                  }

                  var9 += var12;
               } while (var1.isAutoRead() && var12 >= var11 && ++var7 < var4);

               var2.fireChannelReadComplete();
               var5.record(var9);
               if (var8) {
                  this.closeOnRead(var2);
                  var8 = false;
               }
            } catch (Throwable var16) {
               this.handleReadException(var2, var6, var16, var8);
            } finally {
               if (!var1.isAutoRead() && !AbstractNioByteChannel.this.isReadPending()) {
                  this.removeReadOp();
               }
            }
         }
      }

      public void handleReadException(ChannelPipeline var1, ByteBuf var2, Throwable var3, boolean var4) {
         if (var2 != null) {
            if (var2.isReadable()) {
               AbstractNioByteChannel.this.setReadPending(false);
               var1.fireChannelRead(var2);
            } else {
               var2.release();
            }
         }

         var1.fireChannelReadComplete();
         var1.fireExceptionCaught(var3);
         if (var4 || var3 instanceof IOException) {
            this.closeOnRead(var1);
         }
      }

      public void closeOnRead(ChannelPipeline var1) {
         SelectionKey var2 = AbstractNioByteChannel.this.selectionKey();
         AbstractNioByteChannel.this.setInputShutdown();
         if (AbstractNioByteChannel.this.isOpen()) {
            if (Boolean.TRUE.equals(AbstractNioByteChannel.this.config().getOption(ChannelOption.ALLOW_HALF_CLOSURE))) {
               var2.interestOps(var2.interestOps() & ~AbstractNioByteChannel.this.readInterestOp);
               var1.fireUserEventTriggered(ChannelInputShutdownEvent.INSTANCE);
            } else {
               this.close(this.voidPromise());
            }
         }
      }
   }
}
