package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import io.netty.channel.local.LocalEventLoop;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$NotEnoughDataDecoderException;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.Proxy;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.MapPopulator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import org.lwjgl.opengl.GL11;

public class AccountLoginButton extends GuiButton {
   public LocalEventLoop field_0003;
   public MapPopulator field_0006;
   public Map<String, Object> field_0002;
   public String field_0005;
   public String field_0000;
   public ResourceLocation field_0001;
   public boolean field_0007 = true;
   public HttpPostRequestDecoder$NotEnoughDataDecoderException field_0004;

   public boolean method_28341() {
      if (((String)this.field_0002.get("uuid")).equalsIgnoreCase(Minecraft.getMinecraft().getSession().getPlayerID())) {
         return false;
      } else {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));

         for (Session var2 : CheatBreaker.getInstance().method_19770()) {
            if (var2.getProfile().getId().toString().replaceAll("-", "").equalsIgnoreCase(((String)this.field_0002.get("uuid")).replaceAll("-", ""))) {
               Minecraft.getMinecraft().method_20375(var2);
               return true;
            }
         }

         YggdrasilAuthenticationService var21 = new YggdrasilAuthenticationService(Proxy.NO_PROXY, this.field_0000);
         YggdrasilUserAuthentication var3 = (YggdrasilUserAuthentication)var21.createUserAuthentication(Agent.MINECRAFT);
         var3.loadFromStorage(this.field_0002);

         Session var4;
         try {
            var3.logIn();
            var4 = new Session(var3.getSelectedProfile().getName(), var3.getSelectedProfile().getId().toString(), var3.getAuthenticatedToken(), "mojang");
         } catch (AuthenticationException var19) {
            var19.printStackTrace();
            return false;
         }

         File var5 = new File(Minecraft.getMinecraft().mcDataDir + File.separator + "launcher_accounts.json");
         if (var5.exists() && !var3.getAuthenticatedToken().equals(this.field_0002.get("accessToken"))) {
            try {
               FileReader var6 = new FileReader(var5);
               JsonParser var7 = new JsonParser();
               JsonElement var8 = var7.parse(var6);
               Entry var9 = null;

               for (Entry var11 : var8.getAsJsonObject().entrySet()) {
                  if (((String)var11.getKey()).equalsIgnoreCase("authenticationDatabase")) {
                     for (Entry var13 : ((JsonElement)var11.getValue()).getAsJsonObject().entrySet()) {
                        for (Entry var15 : ((JsonElement)var13.getValue()).getAsJsonObject().entrySet()) {
                           if (((String)var15.getKey()).equalsIgnoreCase("profiles")) {
                              for (Entry var17 : ((JsonElement)var15.getValue()).getAsJsonObject().entrySet()) {
                                 if (((String)var17.getKey()).replaceAll("-", "").equalsIgnoreCase(var4.getPlayerID().replaceAll("-", ""))) {
                                    var9 = var13;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (var9 != null) {
                  this.field_0002.put("accessToken", var3.getAuthenticatedToken());
                  ((JsonElement)var9.getValue()).getAsJsonObject().remove("accessToken");
                  ((JsonElement)var9.getValue()).getAsJsonObject().addProperty("accessToken", var3.getAuthenticatedToken());
                  System.out.println("Updated accessToken and logged user in.");
               }

               Gson var22 = new GsonBuilder().setPrettyPrinting().create();

               try {
                  DataOutputStream var23 = new DataOutputStream(new FileOutputStream(var5));
                  var23.writeBytes(var22.toJson(var8).replace("\n", "\r\n"));
                  var23.flush();
                  var23.close();
               } catch (Exception var18) {
                  var18.printStackTrace();
                  return false;
               }
            } catch (Exception var20) {
               var20.printStackTrace();
               return false;
            }
         }

         Minecraft.getMinecraft().method_20375(var4);
         return true;
      }
   }

   @Override
   public void drawButton(Minecraft var1, int var2, int var3) {
      if (this.m) {
         FontRenderer var4 = var1.fontRendererObj;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = var2 >= this.h && var3 >= this.i && var2 < this.h + this.f && var3 < this.i + this.height;
         int var5 = this.getHoverState(this.hovered);
         this.getClass();
         Gui.a(this.h, this.i, this.h + this.f, this.i + this.height, this.hovered ? -15395563 : -14540254);
         this.mouseDragged(var1, var2, var3);
         int var6 = -1;
         if (!this.l) {
            var6 = -986896;
         } else if (this.hovered) {
            var6 = -3092272;
         }

         if (this.field_0005.length() > 9) {
            float var7 = this.h + this.f / 2 + 12;
            float var8 = this.i + this.height / 2 - 4;
            CheatBreaker.getInstance().playRegular14px.drawCenteredString(this.field_0005, var7, var8, var6);
         } else {
            float var9 = this.h + this.f / 2 + 12;
            float var10 = this.i + this.height / 2 - 5;
            CheatBreaker.getInstance().field_0036.drawCenteredString(this.field_0005, var9, var10, var6);
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.drawIcon(this.field_0001, 7.0F, this.h + 10, this.i + 5);
      }
   }

   public AccountLoginButton(int var1, Map var2, int var3, int var4, int var5, int var6) {
      super(var1, var3, var4, var5, var6, (String)var2.get("displayName"));
      this.field_0005 = (String)var2.get("displayName");
      this.field_0000 = (String)var2.get("clientToken");
      this.field_0002 = var2;
      this.field_0001 = CheatBreaker.getInstance().method_19810(this.field_0005);
   }
}
