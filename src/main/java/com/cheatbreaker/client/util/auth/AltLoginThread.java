package com.cheatbreaker.client.util.auth;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.net.Proxy;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class AltLoginThread extends Thread {
   public String recoveredField3450;
   public String recoveredField3451;
   public boolean recoveredField3452 = false;
   public Minecraft recoveredField3453 = Minecraft.getMinecraft();
   public boolean recoveredField3454;

   public boolean method_21762() {
      return this.recoveredField3454;
   }

   public void method_21763(String var1) {
      this.recoveredField3450 = var1;
   }

   public void method_21766(boolean var1) {
      this.recoveredField3454 = var1;
   }

   public boolean method_21768() {
      return this.recoveredField3452;
   }

   public AltLoginThread() {
      this.recoveredField3454 = false;
   }

   public void method_21770(boolean var1) {
      this.recoveredField3452 = var1;
   }

   public AltLoginThread(String var1) {
      super("Alt Login Thread");
      this.recoveredField3450 = var1.split(":")[0];
      this.recoveredField3451 = var1.split(":")[1];
      this.recoveredField3454 = false;
   }

   public String method_21761() {
      return this.recoveredField3451;
   }

   public Minecraft method_21767() {
      return this.recoveredField3453;
   }

   public AltLoginThread(String var1, String var2) {
      super("Alt Login Thread");
      this.recoveredField3450 = var1;
      this.recoveredField3451 = var2;
      this.recoveredField3454 = false;
   }

   public void method_21769(String var1) {
      this.recoveredField3451 = var1;
   }

   public void method_21765(Minecraft var1) {
      this.recoveredField3453 = var1;
   }

   @Override
   public void run() {
      if (this.recoveredField3451 != null && !this.recoveredField3451.equals("")) {
         Session var1 = this.method_21764(this.recoveredField3450, this.recoveredField3451);
         if (var1 == null) {
            this.recoveredField3454 = false;
         } else {
            this.recoveredField3453.method_20375(var1);
            this.recoveredField3454 = true;
         }
      } else {
         this.recoveredField3453.method_20375(new Session(this.recoveredField3450, "", "", "mojang"));
         this.recoveredField3454 = true;
      }
   }

   public String method_21760() {
      return this.recoveredField3450;
   }

   public Session method_21764(String var1, String var2) {
      YggdrasilAuthenticationService var3 = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
      YggdrasilUserAuthentication var4 = (YggdrasilUserAuthentication)var3.createUserAuthentication(Agent.MINECRAFT);
      var4.setUsername(var1);
      var4.setPassword(var2);

      try {
         var4.logIn();
         this.recoveredField3454 = true;
         return new Session(var4.getSelectedProfile().getName(), var4.getSelectedProfile().getId().toString(), var4.getAuthenticatedToken(), "mojang");
      } catch (AuthenticationException var6) {
         var6.printStackTrace();
         return null;
      }
   }
}
