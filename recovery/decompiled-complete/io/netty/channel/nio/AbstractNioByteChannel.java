package io.netty.channel.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.FileRegion;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.UnsafeAtomicReferenceFieldUpdater;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import junit.swingui.TestRunner$13;
import net.minecraft.block.BlockRailPowered$1;
import net.optifine.gui.GuiQualitySettingsOF;

public abstract class AbstractNioByteChannel extends AbstractNioChannel {
   public GuiQualitySettingsOF __junk3586187732663381775;
   public TestRunner$13 __junk6129689628546625856;
   public BlockRailPowered$1 __junk927438615482793047;
   public Runnable flushTask;
   public ChunkedFile __junk3869260797231124500;
   public static String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName(ByteBuf.class) + ", " + StringUtil.simpleClassName(FileRegion.class) + ')';
   public UnsafeAtomicReferenceFieldUpdater __junk7595935572117384382;

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

   public abstract int doReadBytes(ByteBuf var1);

   @Override
   public void doWrite(ChannelOutboundBuffer var1) {
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
               long var8 = 17938049L & 7690764174799945742L;
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
            long var7 = 143132046L & 1447198721L;
            if (var2 == -1) {
               var2 = this.config().getWriteSpinCount();
            }

            for (int var9 = var2 - 1; var9 >= 0; var9--) {
               long var10 = this.doWriteFileRegion(var4);
               if (var10 == (-2171278874229208783L & 345006726L)) {
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

   public abstract int doWriteBytes(ByteBuf var1);

   public void incompleteWrite(boolean var1) {
      if (var1) {
         this.setOpWrite();
      } else {
         Object var2 = this.flushTask;
         if (var2 == null) {
            var2 = this.flushTask = new AbstractNioByteChannel$1(this);
         }

         this.eventLoop().execute((Runnable)var2);
      }
   }

   public AbstractNioChannel$AbstractNioUnsafe newUnsafe() {
      return new AbstractNioByteChannel$NioByteUnsafe(this, null);
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

   public abstract long doWriteFileRegion(FileRegion var1);
}
