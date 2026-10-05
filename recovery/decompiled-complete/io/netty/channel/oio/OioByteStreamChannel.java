package io.netty.channel.oio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.FileRegion;
import io.netty.util.internal.logging.InternalLogLevel;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.WritableByteChannel;
import net.minecraft.network.PingResponseHandler;
import net.minecraft.network.ServerStatusResponse$PlayerCountData;

public abstract class OioByteStreamChannel extends AbstractOioByteChannel {
   public static InputStream CLOSED_IN = new OioByteStreamChannel$1();
   public ServerStatusResponse$PlayerCountData __junk3405008124643068406;
   public InputStream is;
   public InternalLogLevel __junk8152403330035593903;
   public WritableByteChannel outChannel;
   public OutputStream os;
   public PingResponseHandler __junk6328118237387734515;
   public static OutputStream CLOSED_OUT = new OioByteStreamChannel$2();

   @Override
   public void doClose() {
      InputStream var1 = this.is;
      OutputStream var2 = this.os;
      this.is = CLOSED_IN;
      this.os = CLOSED_OUT;

      try {
         if (var1 != null) {
            var1.close();
         }
      } finally {
         if (var2 != null) {
            var2.close();
         }
      }
   }

   public OioByteStreamChannel(Channel var1) {
      super(var1);
   }

   public static void checkEOF(FileRegion var0) {
      if (var0.transfered() < var0.count()) {
         throw new EOFException("Expected to be able to write " + var0.count() + " bytes, " + "but only wrote " + var0.transfered());
      }
   }

   public void activate(InputStream var1, OutputStream var2) {
      if (this.is != null) {
         throw new IllegalStateException("input was set already");
      } else if (this.os != null) {
         throw new IllegalStateException("output was set already");
      } else if (var1 == null) {
         throw new NullPointerException("is");
      } else if (var2 == null) {
         throw new NullPointerException("os");
      } else {
         this.is = var1;
         this.os = var2;
      }
   }

   @Override
   public int available() {
      try {
         return this.is.available();
      } catch (IOException var2) {
         return 0;
      }
   }

   @Override
   public boolean isActive() {
      InputStream var1 = this.is;
      if (var1 != null && var1 != CLOSED_IN) {
         OutputStream var2 = this.os;
         return var2 != null && var2 != CLOSED_OUT;
      } else {
         return false;
      }
   }

   @Override
   public int doReadBytes(ByteBuf var1) {
      int var2 = Math.max(1, Math.min(this.available(), var1.maxWritableBytes()));
      return var1.writeBytes(this.is, var2);
   }

   @Override
   public void doWriteBytes(ByteBuf var1) {
      OutputStream var2 = this.os;
      if (var2 == null) {
         throw new NotYetConnectedException();
      } else {
         var1.readBytes(var2, var1.readableBytes());
      }
   }

   @Override
   public void doWriteFileRegion(FileRegion var1) {
      OutputStream var2 = this.os;
      if (var2 == null) {
         throw new NotYetConnectedException();
      } else {
         if (this.outChannel == null) {
            this.outChannel = Channels.newChannel(var2);
         }

         long var3 = 7049655276522937880L & 1086341379L;

         do {
            long var5 = var1.transferTo(this.outChannel, var3);
            if (var5 == (-1L & -1L)) {
               checkEOF(var1);
               return;
            }

            var3 += var5;
         } while (var3 < var1.count());
      }
   }
}
