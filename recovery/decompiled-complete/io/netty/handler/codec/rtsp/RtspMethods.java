package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.HttpMethod;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.ai.EntityAIOpenDoor;

public class RtspMethods {
   public static HttpMethod DESCRIBE = new HttpMethod("DESCRIBE");
   public static HttpMethod PAUSE = new HttpMethod("PAUSE");
   public static HttpMethod SETUP = new HttpMethod("SETUP");
   public static HttpMethod PLAY = new HttpMethod("PLAY");
   public EntityAIOpenDoor __junk1198968097259437033;
   public static HttpMethod ANNOUNCE = new HttpMethod("ANNOUNCE");
   public static HttpMethod TEARDOWN = new HttpMethod("TEARDOWN");
   public static HttpMethod SET_PARAMETER = new HttpMethod("SET_PARAMETER");
   public static Map<String, HttpMethod> methodMap = new HashMap<>();
   public static HttpMethod OPTIONS = HttpMethod.OPTIONS;
   public static HttpMethod RECORD = new HttpMethod("RECORD");
   public static HttpMethod REDIRECT = new HttpMethod("REDIRECT");
   public static HttpMethod GET_PARAMETER = new HttpMethod("GET_PARAMETER");

   public static HttpMethod valueOf(String var0) {
      if (var0 == null) {
         throw new NullPointerException("name");
      } else {
         var0 = var0.trim().toUpperCase();
         if (var0.isEmpty()) {
            throw new IllegalArgumentException("empty name");
         } else {
            HttpMethod var1 = methodMap.get(var0);
            return var1 != null ? var1 : new HttpMethod(var0);
         }
      }
   }

   static {
      methodMap.put(DESCRIBE.toString(), DESCRIBE);
      methodMap.put(ANNOUNCE.toString(), ANNOUNCE);
      methodMap.put(GET_PARAMETER.toString(), GET_PARAMETER);
      methodMap.put(OPTIONS.toString(), OPTIONS);
      methodMap.put(PAUSE.toString(), PAUSE);
      methodMap.put(PLAY.toString(), PLAY);
      methodMap.put(RECORD.toString(), RECORD);
      methodMap.put(REDIRECT.toString(), REDIRECT);
      methodMap.put(SETUP.toString(), SETUP);
      methodMap.put(SET_PARAMETER.toString(), SET_PARAMETER);
      methodMap.put(TEARDOWN.toString(), TEARDOWN);
   }
}
