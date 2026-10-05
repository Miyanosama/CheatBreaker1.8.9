package recovered.unidentified;

import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import net.optifine.shaders.uniform.ShaderParameterFloat;
import org.json.JSONArray;
import org.json.JSONObject;

public class UnidentifiedClass1610 {
   public ShaderParameterFloat field_0000;
   public ByteToMessageDecoder field_0001;

   public void method_10982() {
   }

   public static JSONArray method_10984(String var0) {
      BufferedReader var1 = new BufferedReader(new InputStreamReader(new URL(var0).openStream(), StandardCharsets.UTF_8));
      return new JSONArray(method_10983(var1));
   }

   public static InputStream method_10985(URL var0, boolean var1) {
      try {
         HttpURLConnection var2 = (HttpURLConnection)var0.openConnection();
         var2.addRequestProperty("User-Agent", var1 ? "CheatBreaker-Client" : "Mozilla/4.0");
         return var2.getInputStream();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static JSONObject method_10986(String var0) {
      BufferedReader var1 = new BufferedReader(new InputStreamReader(new URL(var0).openStream(), StandardCharsets.UTF_8));
      return new JSONObject(method_10983(var1));
   }

   public static String method_10983(Reader var0) {
      StringBuilder var2 = new StringBuilder();

      int var1;
      while ((var1 = var0.read()) != -1) {
         var2.append((char)var1);
      }

      return var2.toString();
   }
}
