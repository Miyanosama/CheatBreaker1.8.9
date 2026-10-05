package io.netty.handler.codec.http;

import io.netty.channel.sctp.oio.OioSctpServerChannel$1;
import io.netty.util.concurrent.FailedFuture;
import net.minecraft.client.Minecraft$6;
import net.minecraft.event.HoverEvent$Action;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.tileentity.TileEntityDropper;
import recovered.unidentified.UnidentifiedClass4179;

public class HttpRequestDecoder extends HttpObjectDecoder {
   public S32PacketConfirmTransaction __junk4884303582101979937;
   public OioSctpServerChannel$1 __junk1017637096915993524;
   public Minecraft$6 __junk5571494488576338183;
   public FailedFuture __junk1965166986809392263;
   public HoverEvent$Action __junk107755163624683814;
   public TileEntityDropper __junk8713032895067055198;
   public UnidentifiedClass4179 __junk6744141545889973387;
   public S11PacketSpawnExperienceOrb __junk7399305601766848105;

   public HttpRequestDecoder(int var1, int var2, int var3) {
      super(var1, var2, var3, true);
   }

   @Override
   public HttpMessage createInvalidMessage() {
      return new DefaultHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, "/bad-request", this.validateHeaders);
   }

   @Override
   public HttpMessage createMessage(String[] var1) {
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
