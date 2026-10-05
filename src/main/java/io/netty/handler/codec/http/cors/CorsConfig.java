package io.netty.handler.codec.http.cors;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.internal.StringUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import net.minecraft.event.ClickEvent;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.world.biome.BiomeGenEnd;
import org.apache.log4j.spi.Filter;

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
   public boolean anyOrigin;
   public Set<HttpMethod> allowedRequestMethods;

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

   public static CorsConfig.Builder withOrigins(String... var0) {
      return new CorsConfig.Builder(var0);
   }

   public static CorsConfig.Builder withAnyOrigin() {
      return new CorsConfig.Builder();
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

   public static CorsConfig.Builder withOrigin(String var0) {
      return "*".equals(var0) ? new CorsConfig.Builder() : new CorsConfig.Builder(var0);
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

   public CorsConfig(CorsConfig.Builder var1) {
      this.origins = new LinkedHashSet<>(var1.origins);
      this.anyOrigin = var1.anyOrigin;
      this.enabled = var1.enabled;
      this.exposeHeaders = var1.exposeHeaders;
      this.allowCredentials = var1.allowCredentials;
      this.maxAge = var1.maxAge;
      this.allowedRequestMethods = var1.requestMethods;
      this.allowedRequestHeaders = var1.requestHeaders;
      this.allowNullOrigin = var1.allowNullOrigin;
      this.preflightHeaders = var1.preflightHeaders;
      this.shortCurcuit = var1.shortCurcuit;
   }

   public static class Builder {
      public long maxAge;
      public boolean shortCurcuit;
      public boolean noPreflightHeaders;
      public boolean anyOrigin;
      public boolean allowNullOrigin;
      public Set<String> origins;
      public Set<String> requestHeaders;
      public Set<String> exposeHeaders;
      public boolean enabled = true;
      public Set<HttpMethod> requestMethods;
      public boolean allowCredentials;
      public Map<CharSequence, Callable<?>> preflightHeaders;

      public CorsConfig.Builder allowedRequestMethods(HttpMethod... var1) {
         this.requestMethods.addAll(Arrays.asList(var1));
         return this;
      }

      public CorsConfig.Builder allowCredentials() {
         this.allowCredentials = true;
         return this;
      }

      public CorsConfig.Builder disable() {
         this.enabled = false;
         return this;
      }

      public CorsConfig.Builder maxAge(long var1) {
         this.maxAge = var1;
         return this;
      }

      public Builder(String... var1) {
         this.exposeHeaders = new HashSet<>();
         this.requestMethods = new HashSet<>();
         this.requestHeaders = new HashSet<>();
         this.preflightHeaders = new HashMap<>();
         this.origins = new LinkedHashSet<>(Arrays.asList(var1));
         this.anyOrigin = false;
      }

      public <T> CorsConfig.Builder preflightResponseHeader(CharSequence var1, Iterable<T> var2) {
         this.preflightHeaders.put(var1, new CorsConfig.ConstantValueGenerator(var2));
         return this;
      }

      public CorsConfig.Builder shortCurcuit() {
         this.shortCurcuit = true;
         return this;
      }

      public CorsConfig.Builder noPreflightResponseHeaders() {
         this.noPreflightHeaders = true;
         return this;
      }

      public Builder() {
         this.exposeHeaders = new HashSet<>();
         this.requestMethods = new HashSet<>();
         this.requestHeaders = new HashSet<>();
         this.preflightHeaders = new HashMap<>();
         this.anyOrigin = true;
         this.origins = Collections.emptySet();
      }

      public CorsConfig.Builder exposeHeaders(String... var1) {
         this.exposeHeaders.addAll(Arrays.asList(var1));
         return this;
      }

      public <T> CorsConfig.Builder preflightResponseHeader(String var1, Callable<T> var2) {
         this.preflightHeaders.put(var1, var2);
         return this;
      }

      public CorsConfig.Builder allowNullOrigin() {
         this.allowNullOrigin = true;
         return this;
      }

      public CorsConfig.Builder preflightResponseHeader(CharSequence var1, Object... var2) {
         if (var2.length == 1) {
            this.preflightHeaders.put(var1, new CorsConfig.ConstantValueGenerator(var2[0]));
         } else {
            this.preflightResponseHeader(var1, Arrays.asList(var2));
         }

         return this;
      }

      public CorsConfig build() {
         if (this.preflightHeaders.isEmpty() && !this.noPreflightHeaders) {
            this.preflightHeaders.put("Date", new CorsConfig.DateValueGenerator());
            this.preflightHeaders.put("Content-Length", new CorsConfig.ConstantValueGenerator("0"));
         }

         return new CorsConfig(this);
      }

      public CorsConfig.Builder allowedRequestHeaders(String... var1) {
         this.requestHeaders.addAll(Arrays.asList(var1));
         return this;
      }
   }

   public static final class ConstantValueGenerator implements Callable<Object> {
      public Object value;

      public ConstantValueGenerator(Object var1) {
         if (var1 == null) {
            throw new IllegalArgumentException("value must not be null");
         } else {
            this.value = var1;
         }
      }

      @Override
      public Object call() {
         return this.value;
      }
   }

   public static final class DateValueGenerator implements Callable<Date> {

      public Date call() {
         return new Date();
      }
   }
}
