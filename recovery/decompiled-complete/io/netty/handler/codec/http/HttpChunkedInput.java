package io.netty.handler.codec.http;

import com.cheatbreaker.client.module.type.keystrokes.KeystrokesModule;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedInput;
import net.minecraft.client.util.JsonException$1;
import net.minecraft.entity.monster.EntityGhast$GhastMoveHelper;
import org.apache.log4j.net.TelnetAppender;
import org.java_websocket.extensions.CompressionExtension;
import recovered.unidentified.UnidentifiedClass4343;

public class HttpChunkedInput implements ChunkedInput<HttpContent> {
   public boolean sentLastChunk;
   public ChunkedInput<ByteBuf> input;
   public EntityGhast$GhastMoveHelper __junk7852751851741013864;
   public TelnetAppender __junk4354651161275128984;
   public JsonException$1 __junk1551419156355483956;
   public LastHttpContent lastHttpContent;
   public KeystrokesModule __junk4025233218771749305;
   public UnidentifiedClass4343 __junk6395563511714167270;
   public CompressionExtension __junk7756328610925025271;

   public HttpChunkedInput(ChunkedInput<ByteBuf> var1, LastHttpContent var2) {
      this.input = var1;
      this.lastHttpContent = var2;
   }

   public HttpContent readChunk(ChannelHandlerContext var1) {
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
   public void close() {
      this.input.close();
   }

   @Override
   public boolean isEndOfInput() {
      return this.input.isEndOfInput() ? this.sentLastChunk : false;
   }
}
