package net.optifine.http;

import io.netty.util.internal.StringUtil;
import net.minecraft.block.BlockSnow;
import net.minecraft.client.entity.AbstractClientPlayer;
import recovered.unidentified.UnidentifiedClass1316;

public class HttpPipelineRequest {
   public UnidentifiedClass1316 field_0003;
   public boolean closed;
   public HttpRequest httpRequest = null;
   public BlockSnow field_0004;
   public StringUtil field_0000;
   public AbstractClientPlayer field_0001;
   public HttpListener httpListener = null;

   public HttpListener getHttpListener() {
      return this.httpListener;
   }

   public boolean isClosed() {
      return this.closed;
   }

   public void setClosed(boolean var1) {
      this.closed = var1;
   }

   public HttpRequest getHttpRequest() {
      return this.httpRequest;
   }

   public HttpPipelineRequest(HttpRequest var1, HttpListener var2) {
      this.closed = false;
      this.httpRequest = var1;
      this.httpListener = var2;
   }
}
