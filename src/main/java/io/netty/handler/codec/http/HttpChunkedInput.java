package io.netty.handler.codec.http;

import com.cheatbreaker.client.module.type.keystrokes.KeystrokesModule;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedInput;
import net.minecraft.entity.monster.EntityGhast;
import org.apache.log4j.net.TelnetAppender;
import org.java_websocket.extensions.CompressionExtension;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer$EnumSwitch;

public class HttpChunkedInput implements ChunkedInput<HttpContent> {
   public boolean sentLastChunk;
   public ChunkedInput<ByteBuf> input;
   public LastHttpContent lastHttpContent;

   public HttpChunkedInput(ChunkedInput<ByteBuf> var1, LastHttpContent var2) {
      this.input = var1;
      this.lastHttpContent = var2;
   }

   public HttpContent readChunk(ChannelHandlerContext var1) throws java.lang.Exception {
      if (this.input.isEndOfInput()) {
         if (this.sentLastChunk) {
            return null;
         } else {
            this.sentLastChunk = true;
            return this.lastHttpContent;
         }
      } else {
         ByteBuf var2 = this.input.readChunk(var1);
         return new DefaultHttpContent(var2);
      }
   }

   public HttpChunkedInput(ChunkedInput<ByteBuf> var1) {
      this.input = var1;
      this.lastHttpContent = LastHttpContent.EMPTY_LAST_CONTENT;
   }

   @Override
   public void close() throws java.lang.Exception {
      this.input.close();
   }

   @Override
   public boolean isEndOfInput() throws java.lang.Exception {
      return this.input.isEndOfInput() ? this.sentLastChunk : false;
   }
}
