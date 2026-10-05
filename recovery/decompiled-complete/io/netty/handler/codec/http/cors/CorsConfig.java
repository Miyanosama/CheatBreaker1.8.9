package io.netty.handler.codec.http.cors;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.internal.StringUtil;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S41PacketServerDifficulty;

public class CorsConfig {
   public boolean allowNullOrigin;
   public long maxAge;
   public boolean allowCredentials;
   public Map<CharSequence, Callable<?>> preflightHeaders;
   public boolean shortCurcuit;
   public Set<String> exposeHeaders;
   public Set<String> allowedRequestHeaders;
   public Set<String> origins;
   public boolean enabled;
   public S41PacketServerDifficulty __junk512333865388253487;
   public boolean anyOrigin;
   public Set<HttpMethod> allowedRequestMethods;
   public S3CPacketUpdateScore __junk1030078379656322012;

   public boolean isShortCurcuit() {
      return this.shortCurcuit;
   }

   public Set<String> exposedHeaders() {
      return Collections.unmodifiableSet(this.exposeHeaders);
   }

   public boolean isAnyOriginSupported() {
      return this.anyOrigin;
   }

   public Set<HttpMethod> allowedRequestMethods() {
      return Collections.unmodifiableSet(this.allowedRequestMethods);
   }

   public static CorsConfig$Builder withOrigins(String... var0) {
      return new CorsConfig$Builder(var0);
   }

   public static CorsConfig$Builder withAnyOrigin() {
      return new CorsConfig$Builder();
   }

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this)
         + "[enabled="
         + this.enabled
         + ", origins="
         + this.origins
         + ", anyOrigin="
         + this.anyOrigin
         + ", exposedHeaders="
         + this.exposeHeaders
         + ", isCredentialsAllowed="
         + this.allowCredentials
         + ", maxAge="
         + this.maxAge
         + ", allowedRequestMethods="
         + this.allowedRequestMethods
         + ", allowedRequestHeaders="
         + this.allowedRequestHeaders
         + ", preflightHeaders="
         + this.preflightHeaders
         + ']';
   }

   public boolean isCredentialsAllowed() {
      return this.allowCredentials;
   }

   public Set<String> origins() {
      return this.origins;
   }

   public boolean isCorsSupportEnabled() {
      return this.enabled;
   }

   public boolean isNullOriginAllowed() {
      return this.allowNullOrigin;
   }

   public static CorsConfig$Builder withOrigin(String var0) {
      return "*".equals(var0) ? new CorsConfig$Builder() : new CorsConfig$Builder(var0);
   }

   public long maxAge() {
      return this.maxAge;
   }

   public HttpHeaders preflightResponseHeaders() {
      if (this.preflightHeaders.isEmpty()) {
         return HttpHeaders.EMPTY_HEADERS;
      } else {
         DefaultHttpHeaders var1 = new DefaultHttpHeaders();

         for (Entry var3 : this.preflightHeaders.entrySet()) {
            Object var4 = getValue((Callable)var3.getValue());
            if (var4 instanceof Iterable) {
               var1.add((CharSequence)var3.getKey(), (Iterable<?>)var4);
            } else {
               var1.add((CharSequence)var3.getKey(), var4);
            }
         }

         return var1;
      }
   }

   public Set<String> allowedRequestHeaders() {
      return Collections.unmodifiableSet(this.allowedRequestHeaders);
   }

   public String origin() {
      return this.origins.isEmpty() ? "*" : this.origins.iterator().next();
   }

   public static <T> T getValue(Callable<T> var0) {
      try {
         return (T)var0.call();
      } catch (Exception var2) {
         throw new IllegalStateException("Could not generate value for callable [" + var0 + ']', var2);
      }
   }

   public CorsConfig(CorsConfig$Builder var1) {
      this.origins = new LinkedHashSet<>(CorsConfig$Builder.access$000(var1));
      this.anyOrigin = CorsConfig$Builder.access$100(var1);
      this.enabled = CorsConfig$Builder.access$200(var1);
      this.exposeHeaders = CorsConfig$Builder.access$300(var1);
      this.allowCredentials = CorsConfig$Builder.access$400(var1);
      this.maxAge = CorsConfig$Builder.access$500(var1);
      this.allowedRequestMethods = CorsConfig$Builder.access$600(var1);
      this.allowedRequestHeaders = CorsConfig$Builder.access$700(var1);
      this.allowNullOrigin = CorsConfig$Builder.access$800(var1);
      this.preflightHeaders = CorsConfig$Builder.access$900(var1);
      this.shortCurcuit = CorsConfig$Builder.access$1000(var1);
   }
}
