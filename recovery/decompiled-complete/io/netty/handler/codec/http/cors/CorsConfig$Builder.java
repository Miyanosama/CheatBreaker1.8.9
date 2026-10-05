package io.netty.handler.codec.http.cors;

import io.netty.handler.codec.http.HttpMethod;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.log4j.spi.Filter;

public class CorsConfig$Builder {
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
   public Filter __junk25731425769793583;
   public boolean allowCredentials;
   public Map<CharSequence, Callable<?>> preflightHeaders;

   public CorsConfig$Builder allowedRequestMethods(HttpMethod... var1) {
      this.requestMethods.addAll(Arrays.asList(var1));
      return this;
   }

   public CorsConfig$Builder allowCredentials() {
      this.allowCredentials = true;
      return this;
   }

   public CorsConfig$Builder disable() {
      this.enabled = false;
      return this;
   }

   public CorsConfig$Builder maxAge(long var1) {
      this.maxAge = var1;
      return this;
   }

   public CorsConfig$Builder(String... var1) {
      this.exposeHeaders = new HashSet<>();
      this.requestMethods = new HashSet<>();
      this.requestHeaders = new HashSet<>();
      this.preflightHeaders = new HashMap<>();
      this.origins = new LinkedHashSet<>(Arrays.asList(var1));
      this.anyOrigin = false;
   }

   public <T> CorsConfig$Builder preflightResponseHeader(CharSequence var1, Iterable<T> var2) {
      this.preflightHeaders.put(var1, new CorsConfig$ConstantValueGenerator(var2, null));
      return this;
   }

   public CorsConfig$Builder shortCurcuit() {
      this.shortCurcuit = true;
      return this;
   }

   public CorsConfig$Builder noPreflightResponseHeaders() {
      this.noPreflightHeaders = true;
      return this;
   }

   public CorsConfig$Builder() {
      this.exposeHeaders = new HashSet<>();
      this.requestMethods = new HashSet<>();
      this.requestHeaders = new HashSet<>();
      this.preflightHeaders = new HashMap<>();
      this.anyOrigin = true;
      this.origins = Collections.emptySet();
   }

   public CorsConfig$Builder exposeHeaders(String... var1) {
      this.exposeHeaders.addAll(Arrays.asList(var1));
      return this;
   }

   public <T> CorsConfig$Builder preflightResponseHeader(String var1, Callable<T> var2) {
      this.preflightHeaders.put(var1, var2);
      return this;
   }

   public CorsConfig$Builder allowNullOrigin() {
      this.allowNullOrigin = true;
      return this;
   }

   public CorsConfig$Builder preflightResponseHeader(CharSequence var1, Object... var2) {
      if (var2.length == 1) {
         this.preflightHeaders.put(var1, new CorsConfig$ConstantValueGenerator(var2[0], null));
      } else {
         this.preflightResponseHeader(var1, Arrays.asList(var2));
      }

      return this;
   }

   public CorsConfig build() {
      if (this.preflightHeaders.isEmpty() && !this.noPreflightHeaders) {
         this.preflightHeaders.put("Date", new CorsConfig$DateValueGenerator());
         this.preflightHeaders.put("Content-Length", new CorsConfig$ConstantValueGenerator("0", null));
      }

      return new CorsConfig(this, null);
   }

   public CorsConfig$Builder allowedRequestHeaders(String... var1) {
      this.requestHeaders.addAll(Arrays.asList(var1));
      return this;
   }
}
