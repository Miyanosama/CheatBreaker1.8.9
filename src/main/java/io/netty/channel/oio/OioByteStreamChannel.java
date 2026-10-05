package io.netty.channel.oio;

import com.cheatbreaker.client.util.server.ServerRestrictionManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.FileRegion;
import io.netty.util.internal.logging.InternalLogLevel;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.WritableByteChannel;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.network.PingResponseHandler;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import org.apache.log4j.chainsaw.ControlPanel$6;
import com.cheatbreaker.client.event.type.ScoreboardDrawEvent;

public abstract class OioByteStreamChannel extends AbstractOioByteChannel {
   public static InputStream CLOSED_IN = new InputStream() {

      @Override
      public int read() {
         return -1;
      }
   };
   public InputStream is;
   public WritableByteChannel outChannel;
   public OutputStream os;
   public static OutputStream CLOSED_OUT = new OutputStream() {

      @Override
      public void write(int var1) throws java.io.IOException {
         throw new ClosedChannelException();
      }
   };

   @Override
   public void doClose() throws java.lang.Exception {
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

   public static void checkEOF(FileRegion var0) throws java.io.IOException {
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
   public int doReadBytes(ByteBuf var1) throws java.lang.Exception {
      int var2 = Math.max(1, Math.min(this.available(), var1.maxWritableBytes()));
      return var1.writeBytes(this.is, var2);
   }

   @Override
   public void doWriteBytes(ByteBuf var1) throws java.lang.Exception {
      OutputStream var2 = this.os;
      if (var2 == null) {
         throw new NotYetConnectedException();
      } else {
         var1.readBytes(var2, var1.readableBytes());
      }
   }

   @Override
   public void doWriteFileRegion(FileRegion var1) throws java.lang.Exception {
      OutputStream var2 = this.os;
      if (var2 == null) {
         throw new NotYetConnectedException();
      } else {
         if (this.outChannel == null) {
            this.outChannel = Channels.newChannel(var2);
         }

         long var3 = 0L;

         do {
            long var5 = var1.transferTo(this.outChannel, var3);
            if (var5 == -1L) {
               checkEOF(var1);
               return;
            }

            var3 += var5;
         } while (var3 < var1.count());
      }
   }
}
