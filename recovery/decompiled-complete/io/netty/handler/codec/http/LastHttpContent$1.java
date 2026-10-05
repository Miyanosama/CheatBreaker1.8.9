package io.netty.handler.codec.http;

import com.cheatbreaker.client.ui.mainmenu.AccountLoginButton;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$1;
import net.minecraft.entity.monster.EntityGhast$GhastMoveHelper;

public class LastHttpContent$1 implements LastHttpContent {
   public WebSocket08FrameDecoder$1 __junk8191522694960798745;
   public AdaptiveRecvByteBufAllocator __junk2702039398441405510;
   public EntityGhast$GhastMoveHelper __junk6039134724114162806;
   public AccountLoginButton __junk6749846429179834406;

   @Override
   public HttpHeaders trailingHeaders() {
      return HttpHeaders.EMPTY_HEADERS;
   }

   @Override
   public boolean release() {
      return false;
   }

   @Override
   public void setDecoderResult(DecoderResult var1) {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public int refCnt() {
      return 1;
   }

   @Override
   public LastHttpContent copy() {
      return EMPTY_LAST_CONTENT;
   }

   @Override
   public String toString() {
      return "EmptyLastHttpContent";
   }

   public LastHttpContent duplicate() {
      return this;
   }

   @Override
   public DecoderResult getDecoderResult() {
      return DecoderResult.SUCCESS;
   }

   @Override
   public ByteBuf content() {
      return Unpooled.EMPTY_BUFFER;
   }

   @Override
   public boolean release(int var1) {
      return false;
   }

   @Override
   public LastHttpContent retain(int var1) {
      return this;
   }

   @Override
   public LastHttpContent retain() {
      return this;
   }
}
