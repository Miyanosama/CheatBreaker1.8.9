package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.GuiMemoryErrorScreen;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.command.server.CommandSetBlock;
import net.minecraft.item.ItemSnowball;
import org.apache.log4j.ConsoleAppender$SystemOutStream;

public class HttpMethod implements Comparable<HttpMethod> {
   public static HttpMethod DELETE = new HttpMethod("DELETE", true);
   public static HttpMethod POST = new HttpMethod("POST", true);
   public ModelZombieVillager __junk2255466668501721189;
   public String name;
   public ItemSnowball __junk2329359042916194879;
   public static HttpMethod CONNECT = new HttpMethod("CONNECT", true);
   public GuiMemoryErrorScreen __junk4842903620624071887;
   public static HttpMethod PUT = new HttpMethod("PUT", true);
   public byte[] bytes;
   public static HttpMethod HEAD = new HttpMethod("HEAD", true);
   public CommandSetBlock __junk8555205284003605286;
   public static Map<String, HttpMethod> methodMap = new HashMap<>();
   public static HttpMethod GET = new HttpMethod("GET", true);
   public static HttpMethod PATCH = new HttpMethod("PATCH", true);
   public static HttpMethod OPTIONS = new HttpMethod("OPTIONS", true);
   public ConsoleAppender$SystemOutStream __junk5018753113724869014;
   public static HttpMethod TRACE = new HttpMethod("TRACE", true);

   public HttpMethod(String var1) {
      this(var1, false);
   }

   public HttpMethod(String var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         var1 = var1.trim();
         if (var1.isEmpty()) {
            throw new IllegalArgumentException("empty name");
         } else {
            for (int var3 = 0; var3 < var1.length(); var3++) {
               if (Character.isISOControl(var1.charAt(var3)) || Character.isWhitespace(var1.charAt(var3))) {
                  throw new IllegalArgumentException("invalid character in name");
               }
            }

            this.name = var1;
            if (var2) {
               this.bytes = var1.getBytes(CharsetUtil.US_ASCII);
            } else {
               this.bytes = null;
            }
         }
      }
   }

   public String name() {
      return this.name;
   }

   public static HttpMethod valueOf(String var0) {
      if (var0 == null) {
         throw new NullPointerException("name");
      } else {
         var0 = var0.trim();
         if (var0.isEmpty()) {
            throw new IllegalArgumentException("empty name");
         } else {
            HttpMethod var1 = methodMap.get(var0);
            return var1 != null ? var1 : new HttpMethod(var0);
         }
      }
   }

   static {
      methodMap.put(OPTIONS.toString(), OPTIONS);
      methodMap.put(GET.toString(), GET);
      methodMap.put(HEAD.toString(), HEAD);
      methodMap.put(POST.toString(), POST);
      methodMap.put(PUT.toString(), PUT);
      methodMap.put(PATCH.toString(), PATCH);
      methodMap.put(DELETE.toString(), DELETE);
      methodMap.put(TRACE.toString(), TRACE);
      methodMap.put(CONNECT.toString(), CONNECT);
   }

   public int compareTo(HttpMethod var1) {
      return this.name().compareTo(var1.name());
   }

   @Override
   public String toString() {
      return this.name();
   }

   public void encode(ByteBuf var1) {
      if (this.bytes == null) {
         HttpHeaders.encodeAscii0(this.name, var1);
      } else {
         var1.writeBytes(this.bytes);
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof HttpMethod)) {
         return false;
      } else {
         HttpMethod var2 = (HttpMethod)var1;
         return this.name().equals(var2.name());
      }
   }

   @Override
   public int hashCode() {
      return this.name().hashCode();
   }
}
