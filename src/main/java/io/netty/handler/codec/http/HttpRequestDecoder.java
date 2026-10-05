package io.netty.handler.codec.http;

import io.netty.util.concurrent.FailedFuture;
import net.minecraft.event.HoverEvent;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.tileentity.TileEntityDropper;
import com.cheatbreaker.client.ui.element.type.IconNumericSliderElement;

public class HttpRequestDecoder extends HttpObjectDecoder {

   public HttpRequestDecoder(int var1, int var2, int var3) {
      super(var1, var2, var3, true);
   }

   @Override
   public HttpMessage createInvalidMessage() {
      return new DefaultHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, "/bad-request", this.validateHeaders);
   }

   @Override
   public HttpMessage createMessage(String[] var1) throws java.lang.Exception {
      return new DefaultHttpRequest(HttpVersion.valueOf(var1[2]), HttpMethod.valueOf(var1[0]), var1[1], this.validateHeaders);
   }

   public HttpRequestDecoder() {
   }

   @Override
   public boolean isDecodingRequest() {
      return true;
   }

   public HttpRequestDecoder(int var1, int var2, int var3, boolean var4) {
      super(var1, var2, var3, true, var4);
   }
}
