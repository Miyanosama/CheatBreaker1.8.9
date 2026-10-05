package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpResponseStatus;
import javax.vecmath.Tuple3i;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.entity.ai.EntityAIDoorInteract;
import net.minecraft.entity.ai.EntityAILookAtVillager;

public class RtspResponseDecoder extends RtspObjectDecoder {
   public static HttpResponseStatus UNKNOWN_STATUS = new HttpResponseStatus(999, "Unknown");

   public RtspResponseDecoder() {
   }

   @Override
   public HttpMessage createMessage(String[] var1) throws java.lang.Exception {
      return new DefaultHttpResponse(RtspVersions.valueOf(var1[0]), new HttpResponseStatus(Integer.parseInt(var1[1]), var1[2]), this.validateHeaders);
   }

   public RtspResponseDecoder(int var1, int var2, int var3) {
      super(var1, var2, var3);
   }

   @Override
   public boolean isDecodingRequest() {
      return false;
   }

   public RtspResponseDecoder(int var1, int var2, int var3, boolean var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   public HttpMessage createInvalidMessage() {
      return new DefaultHttpResponse(RtspVersions.RTSP_1_0, UNKNOWN_STATUS, this.validateHeaders);
   }
}
