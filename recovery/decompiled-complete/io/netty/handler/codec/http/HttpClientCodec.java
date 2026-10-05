package io.netty.handler.codec.http;

import io.netty.channel.CombinedChannelDuplexHandler;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import net.minecraft.entity.ai.EntityMinecartMobSpawner$1;
import net.minecraft.entity.player.EntityPlayerMP;

public class HttpClientCodec extends CombinedChannelDuplexHandler<HttpResponseDecoder, HttpRequestEncoder> {
   public boolean failOnMissingResponse;
   public EntityMinecartMobSpawner$1 __junk7681841932780288191;
   public Queue<HttpMethod> queue = new ArrayDeque<>();
   public AtomicLong requestResponseCounter = new AtomicLong();
   public boolean done;
   public EntityPlayerMP __junk3975473027451970349;

   public HttpClientCodec() {
      this(4096, 8192, 8192, false);
   }

   public void setSingleDecode(boolean var1) {
      this.inboundHandler().setSingleDecode(var1);
   }

   public HttpClientCodec(int var1, int var2, int var3, boolean var4) {
      this(var1, var2, var3, var4, true);
   }

   public HttpClientCodec(int var1, int var2, int var3, boolean var4, boolean var5) {
      this.init(new HttpClientCodec$Decoder(this, var1, var2, var3, var5), new HttpClientCodec$Encoder(this, null));
      this.failOnMissingResponse = var4;
   }

   public boolean isSingleDecode() {
      return this.inboundHandler().isSingleDecode();
   }

   public HttpClientCodec(int var1, int var2, int var3) {
      this(var1, var2, var3, false);
   }
}
