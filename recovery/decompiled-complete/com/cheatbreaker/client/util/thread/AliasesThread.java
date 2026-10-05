package com.cheatbreaker.client.util.thread;

import com.cheatbreaker.client.ui.overlay.element.AliasesElement;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Map.Entry;
import javazoom.jl.converter.Converter;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.command.CommandSpreadPlayers;
import net.minecraft.util.EnumChatFormatting;
import org.slf4j.helpers.BasicMDCAdapter$1;

public class AliasesThread extends Thread {
   public Converter field_0003;
   public AliasesElement parent;
   public CommandSpreadPlayers field_0002;
   public ModelRenderer field_0004;
   public BasicMDCAdapter$1 field_0000;
   public DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");

   @Override
   public void run() {
      try {
         URL var1 = new URL("https://api.mojang.com/user/profiles/" + this.parent.getFriend().getPlayerId().replaceAll("-", "") + "/names");
         URLConnection var2 = var1.openConnection();
         BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.getInputStream()));
         String var4 = var3.readLine();
         JsonParser var5 = new JsonParser();
         JsonElement var6 = var5.parse("{\"Names\": " + var4 + "}");

         for (Entry var8 : var6.getAsJsonObject().entrySet()) {
            if (((String)var8.getKey()).equalsIgnoreCase("Names")) {
               for (JsonElement var10 : ((JsonElement)var8.getValue()).getAsJsonArray()) {
                  String var11 = var10.getAsJsonObject().get("name").getAsString();
                  if (var10.getAsJsonObject().has("changedToAt")) {
                     long var12 = var10.getAsJsonObject().get("changedToAt").getAsLong();
                     LocalDateTime var14 = LocalDateTime.ofInstant(Instant.ofEpochMilli(var12), ZoneId.systemDefault());
                     this.parent.getAliases().add(EnumChatFormatting.GRAY + var14.format(this.format) + EnumChatFormatting.RESET + " " + var11);
                  } else {
                     this.parent.getAliases().add(var11);
                  }
               }
            }
         }

         Collections.reverse(this.parent.getAliases());
         this.parent
            .setElementSize(
               this.parent.getX(), this.parent.getY(), this.parent.getWidth(), this.parent.getHeight() + this.parent.getAliases().size() * 10 - 10.0F
            );
      } catch (Exception var15) {
         var15.printStackTrace();
      }
   }

   public AliasesThread(AliasesElement var1) {
      this.parent = var1;
   }
}
