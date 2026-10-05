package io.netty.handler.logging;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogLevel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class LoggingHandler extends ChannelDuplexHandler {
   public static String[] BYTEPADDING = new String[16];
   public LogLevel level;
   public static LogLevel DEFAULT_LEVEL = LogLevel.DEBUG;
   public ChunkProviderGenerate __junk8385900828058561843;
   public InternalLogger logger;
   public static String[] BYTE2HEX = new String[256];
   public static char[] BYTE2CHAR = new char[256];
   public InternalLogLevel internalLevel;
   public static String[] HEXPADDING = new String[16];
   public static String NEWLINE = StringUtil.NEWLINE;

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "INACTIVE"));
      }

      super.channelInactive(var1);
   }

   @Override
   public void disconnect(ChannelHandlerContext var1, ChannelPromise var2) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "DISCONNECT()"));
      }

      super.disconnect(var1, var2);
   }

   @Override
   public void userEventTriggered(ChannelHandlerContext var1, Object var2) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "USER_EVENT: " + var2));
      }

      super.userEventTriggered(var1, var2);
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      this.logMessage(var1, "WRITE", var2);
      var1.write(var2, var3);
   }

   @Override
   public void bind(ChannelHandlerContext var1, SocketAddress var2, ChannelPromise var3) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "BIND(" + var2 + ')'));
      }

      super.bind(var1, var2, var3);
   }

   public String format(ChannelHandlerContext var1, String var2) {
      String var3 = var1.channel().toString();
      StringBuilder var4 = new StringBuilder(var3.length() + var2.length() + 1);
      var4.append(var3);
      var4.append(' ');
      var4.append(var2);
      return var4.toString();
   }

   public LoggingHandler(Class<?> var1) {
      this(var1, DEFAULT_LEVEL);
   }

   public LoggingHandler(Class<?> var1, LogLevel var2) {
      if (var1 == null) {
         throw new NullPointerException("clazz");
      } else if (var2 == null) {
         throw new NullPointerException("level");
      } else {
         this.logger = InternalLoggerFactory.getInstance(var1);
         this.level = var2;
         this.internalLevel = var2.toInternalLevel();
      }
   }

   @Override
   public void connect(ChannelHandlerContext var1, SocketAddress var2, SocketAddress var3, ChannelPromise var4) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "CONNECT(" + var2 + ", " + var3 + ')'));
      }

      super.connect(var1, var2, var3, var4);
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "EXCEPTION: " + var2), var2);
      }

      super.exceptionCaught(var1, var2);
   }

   public LoggingHandler(LogLevel var1) {
      if (var1 == null) {
         throw new NullPointerException("level");
      } else {
         this.logger = InternalLoggerFactory.getInstance(this.getClass());
         this.level = var1;
         this.internalLevel = var1.toInternalLevel();
      }
   }

   @Override
   public void channelUnregistered(ChannelHandlerContext var1) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "UNREGISTERED"));
      }

      super.channelUnregistered(var1);
   }

   public String formatMessage(String var1, Object var2) {
      if (var2 instanceof ByteBuf) {
         return this.formatByteBuf(var1, (ByteBuf)var2);
      } else {
         return var2 instanceof ByteBufHolder ? this.formatByteBufHolder(var1, (ByteBufHolder)var2) : this.formatNonByteBuf(var1, var2);
      }
   }

   public LoggingHandler() {
      this(DEFAULT_LEVEL);
   }

   public String formatNonByteBuf(String var1, Object var2) {
      return var1 + ": " + var2;
   }

   @Override
   public void channelActive(ChannelHandlerContext var1) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "ACTIVE"));
      }

      super.channelActive(var1);
   }

   static {
      for (int var0 = 0; var0 < BYTE2HEX.length; var0++) {
         BYTE2HEX[var0] = ' ' + StringUtil.byteToHexStringPadded(var0);
      }

      for (int var4 = 0; var4 < HEXPADDING.length; var4++) {
         int var1 = HEXPADDING.length - var4;
         StringBuilder var2 = new StringBuilder(var1 * 3);

         for (int var3 = 0; var3 < var1; var3++) {
            var2.append("   ");
         }

         HEXPADDING[var4] = var2.toString();
      }

      for (int var5 = 0; var5 < BYTEPADDING.length; var5++) {
         int var7 = BYTEPADDING.length - var5;
         StringBuilder var8 = new StringBuilder(var7);

         for (int var9 = 0; var9 < var7; var9++) {
            var8.append(' ');
         }

         BYTEPADDING[var5] = var8.toString();
      }

      for (int var6 = 0; var6 < BYTE2CHAR.length; var6++) {
         if (var6 > 31 && var6 < 127) {
            BYTE2CHAR[var6] = (char)var6;
         } else {
            BYTE2CHAR[var6] = '.';
         }
      }
   }

   @Override
   public void channelRegistered(ChannelHandlerContext var1) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "REGISTERED"));
      }

      super.channelRegistered(var1);
   }

   public LoggingHandler(String var1, LogLevel var2) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else if (var2 == null) {
         throw new NullPointerException("level");
      } else {
         this.logger = InternalLoggerFactory.getInstance(var1);
         this.level = var2;
         this.internalLevel = var2.toInternalLevel();
      }
   }

   @Override
   public void close(ChannelHandlerContext var1, ChannelPromise var2) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "CLOSE()"));
      }

      super.close(var1, var2);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      this.logMessage(var1, "RECEIVED", var2);
      var1.fireChannelRead(var2);
   }

   public LogLevel level() {
      return this.level;
   }

   @Override
   public void deregister(ChannelHandlerContext var1, ChannelPromise var2) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "DEREGISTER()"));
      }

      super.deregister(var1, var2);
   }

   @Override
   public void flush(ChannelHandlerContext var1) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, "FLUSH"));
      }

      var1.flush();
   }

   public String formatByteBufHolder(String var1, ByteBufHolder var2) {
      return this.formatByteBuf(var1, var2.content());
   }

   public LoggingHandler(String var1) {
      this(var1, DEFAULT_LEVEL);
   }

   public String formatByteBuf(String var1, ByteBuf var2) {
      int var3 = var2.readableBytes();
      int var4 = var3 / 16 + (var3 % 15 == 0 ? 0 : 1) + 4;
      StringBuilder var5 = new StringBuilder(var4 * 80 + var1.length() + 16);
      var5.append(var1).append('(').append(var3).append('B').append(')');
      var5.append(
         NEWLINE
            + "         +-------------------------------------------------+"
            + NEWLINE
            + "         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |"
            + NEWLINE
            + "+--------+-------------------------------------------------+----------------+"
      );
      int var6 = var2.readerIndex();
      int var7 = var2.writerIndex();

      int var8;
      for (var8 = var6; var8 < var7; var8++) {
         int var9 = var8 - var6;
         int var10 = var9 & 15;
         if (var10 == 0) {
            var5.append(NEWLINE);
            var5.append(Long.toHexString(var9 & -557418782868373505L & 4294967295L | -6573438660836704048L & 6573438663460685832L));
            var5.setCharAt(var5.length() - 9, '|');
            var5.append('|');
         }

         var5.append(BYTE2HEX[var2.getUnsignedByte(var8)]);
         if (var10 == 15) {
            var5.append(" |");

            for (int var11 = var8 - 15; var11 <= var8; var11++) {
               var5.append(BYTE2CHAR[var2.getUnsignedByte(var11)]);
            }

            var5.append('|');
         }
      }

      if ((var8 - var6 & 15) != 0) {
         int var12 = var3 & 15;
         var5.append(HEXPADDING[var12]);
         var5.append(" |");

         for (int var13 = var8 - var12; var13 < var8; var13++) {
            var5.append(BYTE2CHAR[var2.getUnsignedByte(var13)]);
         }

         var5.append(BYTEPADDING[var12]);
         var5.append('|');
      }

      var5.append(NEWLINE + "+--------+-------------------------------------------------+----------------+");
      return var5.toString();
   }

   public void logMessage(ChannelHandlerContext var1, String var2, Object var3) {
      if (this.logger.isEnabled(this.internalLevel)) {
         this.logger.log(this.internalLevel, this.format(var1, this.formatMessage(var2, var3)));
      }
   }
}
