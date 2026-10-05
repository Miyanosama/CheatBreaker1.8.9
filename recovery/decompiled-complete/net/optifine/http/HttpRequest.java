package net.optifine.http;

import io.netty.handler.codec.spdy.DefaultSpdyHeadersFrame;
import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.enchantment.EnchantmentHelper$ModifierDamage;
import net.minecraft.nbt.JsonToNBT$Compound;
import net.minecraft.network.NetworkManager$1;

public class HttpRequest {
   public String method;
   public int redirects;
   public NetworkManager$1 field_0007;
   public static String field_0013;
   public DefaultSpdyHeadersFrame field_0002;
   public static String field_0003;
   public String host = null;
   public Proxy proxy;
   public byte[] body;
   public String file;
   public String http;
   public static String field_0009;
   public int port = 0;
   public JsonToNBT$Compound field_0006;
   public EnchantmentHelper$ModifierDamage field_0012;
   public static String field_0014;
   public static String field_0000;
   public Map<String, String> headers;

   public String getFile() {
      return this.file;
   }

   public String getHttp() {
      return this.http;
   }

   public String getHost() {
      return this.host;
   }

   public HttpRequest(String var1, int var2, Proxy var3, String var4, String var5, String var6, Map<String, String> var7, byte[] var8) {
      this.proxy = Proxy.NO_PROXY;
      this.method = null;
      this.file = null;
      this.http = null;
      this.headers = new LinkedHashMap<>();
      this.body = null;
      this.redirects = 0;
      this.host = var1;
      this.port = var2;
      this.proxy = var3;
      this.method = var4;
      this.file = var5;
      this.http = var6;
      this.headers = var7;
      this.body = var8;
   }

   public Map<String, String> getHeaders() {
      return this.headers;
   }

   public String getMethod() {
      return this.method;
   }

   public int getPort() {
      return this.port;
   }

   public byte[] getBody() {
      return this.body;
   }

   public void setRedirects(int var1) {
      this.redirects = var1;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public int getRedirects() {
      return this.redirects;
   }
}
