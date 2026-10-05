package com.cheatbreaker.client.emote;

import com.cheatbreaker.client.event.type.EmoteMouseInputEvent;

import com.cheatbreaker.client.ui.selection.EmoteSelectionGui;

import com.cheatbreaker.client.websocket.server.WSPacketEmote;

import com.cheatbreaker.client.event.type.KeyPressEvent;

import com.cheatbreaker.client.event.EventPhase;

import com.cheatbreaker.client.event.type.PlayerModelRenderEvent;

import com.cheatbreaker.client.emote.type.HandsUpEmote;

import com.cheatbreaker.client.emote.type.FlossEmote;

import com.cheatbreaker.client.emote.type.TPoseEmote;

import com.cheatbreaker.client.emote.type.WaveEmote;

import com.cheatbreaker.client.emote.type.SuperFacepalmEmote;

import com.cheatbreaker.client.emote.type.ShrugEmote;

import com.cheatbreaker.client.emote.type.NarutoRunEmote;

import com.cheatbreaker.client.emote.type.FacepalmEmote;

import com.cheatbreaker.client.emote.type.DabEmote;

import com.cheatbreaker.client.emote.Emote;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.TickEvent;
import com.google.common.collect.ImmutableBiMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.player.EntityPlayer;

public class EmoteManager {
   public static ImmutableBiMap<Object, Object> recoveredField2040 = ImmutableBiMap.<Object, Object>builder()
      .put(0, WaveEmote.class)
      .put(1, HandsUpEmote.class)
      .put(2, FlossEmote.class)
      .put(3, DabEmote.class)
      .put(4, TPoseEmote.class)
      .put(5, ShrugEmote.class)
      .put(6, FacepalmEmote.class)
      .put(7, NarutoRunEmote.class)
      .put(8, SuperFacepalmEmote.class)
      .build();
   public boolean recoveredField2041;
   public boolean recoveredField2042;
   public Map<UUID, Emote> recoveredField2043;
   public Map<UUID, ModelPlayer> recoveredField2044;
   public Emote recoveredField2045;
   public List<Integer> recoveredField2046 = new ArrayList<>();

   public EmoteManager() {
      this.recoveredField2043 = new ConcurrentHashMap<>();
      this.recoveredField2044 = new ConcurrentHashMap<>();
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::method_01375);
      CheatBreaker.getInstance().method_19817().method_21938(PlayerModelRenderEvent.class, this::method_01378);
      CheatBreaker.getInstance().method_19817().method_21938(EmoteMouseInputEvent.class, var1 -> {
         if (var1.method_21080() == 0 && var1.method_21079()) {
            this.method_01379(Minecraft.getMinecraft().thePlayer);
         }
      });
      CheatBreaker.getInstance()
         .method_19817()
         .method_21938(
            KeyPressEvent.class,
            var1 -> {
               if (Minecraft.getMinecraft().currentScreen == null
                  && !this.recoveredField2046.isEmpty()
                  && var1.method_05523() == CheatBreaker.getInstance().getGlobalSettings().recoveredField493.getKeyCode()) {
                  Minecraft.getMinecraft().displayGuiScreen(new EmoteSelectionGui(var1.method_05523()));
               }
            }
         );
   }

   public Emote method_01371() {
      return this.recoveredField2045;
   }

   public Emote method_01372(int var1) {
      if (!recoveredField2040.containsKey(var1)) {
         return null;
      } else {
         try {
            return (Emote)((Class)recoveredField2040.get(var1)).newInstance();
         } catch (Exception var3) {
            var3.printStackTrace();
            return null;
         }
      }
   }

   public void method_01378(PlayerModelRenderEvent var1) {
      Emote var2 = this.recoveredField2043.get(var1.method_23155().aK());
      if (var2 != null) {
         if (var1.method_23156() == EventPhase.START) {
            var2.method_00244(var1.method_23155(), var1.method_23158(), var1.method_23157());
         } else {
            var2.method_00243(var1.method_23155(), var1.method_23157());
         }
      }
   }

   public void method_01379(AbstractClientPlayer var1) {
      if (this.recoveredField2043.containsKey(var1.aK())) {
         this.recoveredField2045 = null;
         Emote var2 = this.recoveredField2043.get(var1.aK());
         var2.method_02054(var1);
         this.recoveredField2043.remove(var1.aK());
      }
   }

   public Map<UUID, ModelPlayer> method_01383() {
      return this.recoveredField2044;
   }

   public List<Integer> method_01369() {
      return this.recoveredField2046;
   }

   public void method_01373(Emote var1) {
      if (this.method_01370(var1)) {
         if (this.recoveredField2041) {
            this.recoveredField2041 = false;
            this.recoveredField2042 = true;
         }

         this.recoveredField2045 = var1;
         this.method_01379(Minecraft.getMinecraft().thePlayer);
         int var2 = (Integer)recoveredField2040.inverse().get(var1.getClass());
         CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new WSPacketEmote(Minecraft.getMinecraft().thePlayer.aK(), var2));
      }
   }

   public void method_01381(boolean var1) {
      this.recoveredField2041 = var1;
   }

   public boolean method_01382() {
      return this.recoveredField2042;
   }

   public boolean method_01367() {
      return this.recoveredField2041;
   }

   public void method_01380(AbstractClientPlayer var1, Emote var2) {
      if (var1.aK().equals(Minecraft.getMinecraft().thePlayer.aK())) {
         if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 || this.recoveredField2042) {
            Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
            this.recoveredField2041 = true;
         }

         this.recoveredField2042 = false;
      }

      this.recoveredField2045 = var2;
      this.recoveredField2043.putIfAbsent(var1.aK(), var2);
   }

   public void method_01385(boolean var1) {
      this.recoveredField2042 = var1;
   }

   public void method_01384(Emote var1) {
      this.recoveredField2045 = var1;
   }

   public boolean method_01370(Emote var1) {
      return this.recoveredField2046.contains(recoveredField2040.inverse().get(var1));
   }

   public Map<UUID, Emote> method_01368() {
      return this.recoveredField2043;
   }

   public void method_01375(TickEvent var1) {
      if (!this.recoveredField2043.isEmpty()) {
         ArrayList var2 = new ArrayList();
         this.recoveredField2043.forEach((var2x, var3) -> {
            if (Minecraft.getMinecraft().theWorld != null) {
               EntityPlayer var4 = Minecraft.getMinecraft().theWorld.getPlayerEntityByUUID(var2x);
               if (var3.method_02055()) {
                  this.recoveredField2045 = null;
                  this.recoveredField2043.remove(var4.aK());
                  var3.method_02054((AbstractClientPlayer)var4);
                  ModelPlayer var5 = this.method_01383().get(var2x);
                  if (var5 == null || var5.bipedCape == null) {
                     return;
                  }

                  var5.bipedCape.rotateAngleZ = 0.0F;
                  this.method_01383().remove(var2x);
                  var2.add(var2x);
               }
            }
         });
         var2.forEach(this.recoveredField2043::remove);
      }
   }
}
