package recovered.unidentified;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.net.Proxy;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item$8;
import net.minecraft.util.Session;
import org.java_websocket.util.Base64;

public class UnidentifiedClass3516 extends Thread {
   public String field_0003;
   public String field_0005;
   public boolean field_0002 = false;
   public Minecraft field_0004 = Minecraft.getMinecraft();
   public Item$8 field_0000;
   public Base64 field_0001;
   public boolean field_0006;

   public boolean method_21762() {
      return this.field_0006;
   }

   public void method_21763(String var1) {
      this.field_0003 = var1;
   }

   public void method_21766(boolean var1) {
      this.field_0006 = var1;
   }

   public boolean method_21768() {
      return this.field_0002;
   }

   public UnidentifiedClass3516() {
      this.field_0006 = false;
   }

   public void method_21770(boolean var1) {
      this.field_0002 = var1;
   }

   public UnidentifiedClass3516(String var1) {
      super("Alt Login Thread");
      this.field_0003 = var1.split(":")[0];
      this.field_0005 = var1.split(":")[1];
      this.field_0006 = false;
   }

   public String method_21761() {
      return this.field_0005;
   }

   public Minecraft method_21767() {
      return this.field_0004;
   }

   public UnidentifiedClass3516(String var1, String var2) {
      super("Alt Login Thread");
      this.field_0003 = var1;
      this.field_0005 = var2;
      this.field_0006 = false;
   }

   public void method_21769(String var1) {
      this.field_0005 = var1;
   }

   public void method_21765(Minecraft var1) {
      this.field_0004 = var1;
   }

   @Override
   public void run() {
      if (this.field_0005 != null && !this.field_0005.equals("")) {
         Session var1 = this.method_21764(this.field_0003, this.field_0005);
         if (var1 == null) {
            this.field_0006 = false;
         } else {
            this.field_0004.method_20375(var1);
            this.field_0006 = true;
         }
      } else {
         this.field_0004.method_20375(new Session(this.field_0003, "", "", "mojang"));
         this.field_0006 = true;
      }
   }

   public String method_21760() {
      return this.field_0003;
   }

   public Session method_21764(String var1, String var2) {
      YggdrasilAuthenticationService var3 = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
      YggdrasilUserAuthentication var4 = (YggdrasilUserAuthentication)var3.createUserAuthentication(Agent.MINECRAFT);
      var4.setUsername(var1);
      var4.setPassword(var2);

      try {
         var4.logIn();
         this.field_0006 = true;
         return new Session(var4.getSelectedProfile().getName(), var4.getSelectedProfile().getId().toString(), var4.getAuthenticatedToken(), "mojang");
      } catch (AuthenticationException var6) {
         var6.printStackTrace();
         return null;
      }
   }
}
