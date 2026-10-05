package net.optifine.http;

import io.netty.handler.codec.marshalling.ThreadLocalUnmarshallerProvider;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.block.BlockCake;

public class HttpResponse {
   public Map<String, String> headers;
   public ThreadLocalUnmarshallerProvider field_0006;
   public int status = 0;
   public ObjectDecoderInputStream field_0005;
   public BlockCake field_0000;
   public byte[] body;
   public String statusLine = null;
   public ProtobufDecoder field_0004;

   public Map getHeaders() {
      return this.headers;
   }

   public HttpResponse(int var1, String var2, Map var3, byte[] var4) {
      this.headers = new LinkedHashMap<>();
      this.body = null;
      this.status = var1;
      this.statusLine = var2;
      this.headers = var3;
      this.body = var4;
   }

   public int getStatus() {
      return this.status;
   }

   public byte[] getBody() {
      return this.body;
   }

   public String getStatusLine() {
      return this.statusLine;
   }

   public String getHeader(String var1) {
      return this.headers.get(var1);
   }
}
