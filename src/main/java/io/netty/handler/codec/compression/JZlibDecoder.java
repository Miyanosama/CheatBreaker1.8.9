package io.netty.handler.codec.compression;

import com.cheatbreaker.client.ui.element.profile.ProfilesListElement;
import com.jcraft.jzlib.Inflater;
import com.jcraft.jzlib.JZlib;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.local.LocalChannelRegistry;
import io.netty.channel.socket.DefaultDatagramChannelConfig;
import io.netty.util.internal.AppendableCharSequence;
import java.util.List;

public class JZlibDecoder extends ZlibDecoder {
   public volatile boolean finished;
   public byte[] dictionary;
   public Inflater z = new Inflater();

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      if (this.finished) {
         var2.skipBytes(var2.readableBytes());
      } else if (var2.isReadable()) {
         try {
            int var4 = var2.readableBytes();
            this.z.avail_in = var4;
            if (var2.hasArray()) {
               this.z.next_in = var2.array();
               this.z.next_in_index = var2.arrayOffset() + var2.readerIndex();
            } else {
               byte[] var5 = new byte[var4];
               var2.getBytes(var2.readerIndex(), var5);
               this.z.next_in = var5;
               this.z.next_in_index = 0;
            }

            int var19 = this.z.next_in_index;
            int var6 = var4 << 1;
            ByteBuf var7 = var1.alloc().heapBuffer(var6);

            try {
               while (true) {
                  this.z.avail_out = var6;
                  var7.ensureWritable(var6);
                  this.z.next_out = var7.array();
                  this.z.next_out_index = var7.arrayOffset() + var7.writerIndex();
                  int var8 = this.z.next_out_index;
                  int var9 = this.z.inflate(2);
                  int var10 = this.z.next_out_index - var8;
                  if (var10 > 0) {
                     var7.writerIndex(var7.writerIndex() + var10);
                  }

                  switch (var9) {
                     case -5:
                        if (this.z.avail_in <= 0) {
                           return;
                        }
                        break;
                     case -4:
                     case -3:
                     case -2:
                     case -1:
                     default:
                        ZlibUtil.fail(this.z, "decompression failure", var9);
                     case 0:
                        break;
                     case 1:
                        this.finished = true;
                        this.z.inflateEnd();
                        return;
                     case 2:
                        if (this.dictionary == null) {
                           ZlibUtil.fail(this.z, "decompression failure", var9);
                        } else {
                           var9 = this.z.inflateSetDictionary(this.dictionary, this.dictionary.length);
                           if (var9 != 0) {
                              ZlibUtil.fail(this.z, "failed to set the dictionary", var9);
                           }
                        }
                  }
               }
            } finally {
               var2.skipBytes(this.z.next_in_index - var19);
               if (var7.isReadable()) {
                  var3.add(var7);
               } else {
                  var7.release();
               }
            }
         } finally {
            this.z.next_in = null;
            this.z.next_out = null;
         }
      }
   }

   public JZlibDecoder(ZlibWrapper var1) {
      if (var1 == null) {
         throw new NullPointerException("wrapper");
      } else {
         int var2 = this.z.init(ZlibUtil.convertWrapperType(var1));
         if (var2 != 0) {
            ZlibUtil.fail(this.z, "initialization failure", var2);
         }
      }
   }

   public JZlibDecoder(byte[] var1) {
      if (var1 == null) {
         throw new NullPointerException("dictionary");
      } else {
         this.dictionary = var1;
         int var2 = this.z.inflateInit(JZlib.W_ZLIB);
         if (var2 != 0) {
            ZlibUtil.fail(this.z, "initialization failure", var2);
         }
      }
   }

   @Override
   public boolean isClosed() {
      return this.finished;
   }

   public JZlibDecoder() {
      this(ZlibWrapper.ZLIB);
   }
}
