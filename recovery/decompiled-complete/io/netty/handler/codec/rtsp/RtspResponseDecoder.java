package io.netty.handler.codec.rtsp;

import io.netty.channel.ChannelFutureListener$1;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$1;
import javax.vecmath.Tuple3i;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.entity.ai.EntityAIDoorInteract;
import net.minecraft.entity.ai.EntityAILookAtVillager;

public class RtspResponseDecoder extends RtspObjectDecoder {
   public BlockStateBase __junk656526778435988603;
   public static HttpResponseStatus UNKNOWN_STATUS = new HttpResponseStatus(999, "Unknown");
   public HttpPostRequestEncoder$1 __junk8466445079756227568;
   public ChannelFutureListener$1 __junk5714046561832033741;
   public EntityAILookAtVillager __junk940313320262287937;
   public Tuple3i __junk3651671743079658267;
   public EntityAIDoorInteract __junk2946836588768565900;

   public RtspResponseDecoder() {
   }

   @Override
   public HttpMessage createMessage(String[] var1) {
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
