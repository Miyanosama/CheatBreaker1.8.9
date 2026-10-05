package net.minecraft.client.stream;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import junit.swingui.ProgressBar;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.JsonUtils;
import tv.twitch.AuthToken;

public class TwitchStream$1 extends Thread {
   public ProgressBar field_0000;
   public EntityMinecartTNT field_0002;

   @Override
   public void run() {
      try {
         URL var1 = new URL("https://api.twitch.tv/kraken?oauth_token=" + URLEncoder.encode(this.field_153083_a.getValue(), "UTF-8"));
         String var2 = HttpUtil.get(var1);
         JsonObject var3 = JsonUtils.getJsonObject(new JsonParser().parse(var2), "Response");
         JsonObject var4 = JsonUtils.getJsonObject(var3, "token");
         if (JsonUtils.getBoolean(var4, "valid")) {
            String var5 = JsonUtils.getString(var4, "user_name");
            TwitchStream.access$000().debug(TwitchStream.STREAM_MARKER, "Authenticated with twitch; username is {}", new Object[]{var5});
            AuthToken var6 = new AuthToken();
            var6.data = this.field_153083_a.getValue();
            TwitchStream.access$100(this.field_153084_b).func_152818_a(var5, var6);
            TwitchStream.access$200(this.field_153084_b).func_152998_c(var5);
            TwitchStream.access$200(this.field_153084_b).func_152994_a(var6);
            Runtime.getRuntime().addShutdownHook(new TwitchStream$1$1(this, "Twitch shutdown hook"));
            TwitchStream.access$100(this.field_153084_b).func_152817_A();
            TwitchStream.access$200(this.field_153084_b).func_175984_n();
         } else {
            TwitchStream.access$302(this.field_153084_b, IStream$AuthFailureReason.INVALID_TOKEN);
            TwitchStream.access$000().error(TwitchStream.STREAM_MARKER, "Given twitch access token is invalid");
         }
      } catch (IOException var7) {
         TwitchStream.access$302(this.field_153084_b, IStream$AuthFailureReason.ERROR);
         TwitchStream.access$000().error(TwitchStream.STREAM_MARKER, "Could not authenticate with twitch", var7);
      }
   }

   public TwitchStream$1(TwitchStream var1, String var2, Property var3) {
      this.field_153084_b = var1;
      this.field_153083_a = var3;
      super(var2);
   }
}
