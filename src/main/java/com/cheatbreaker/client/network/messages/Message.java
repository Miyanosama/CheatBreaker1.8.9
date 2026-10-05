package com.cheatbreaker.client.network.messages;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.nethandler.client.PacketClientVoice;
import com.cheatbreaker.client.network.CustomPayloadSender;
import com.cheatbreaker.client.util.cbagent.CBAgentResources;
import com.google.gson.Gson;
import io.netty.util.internal.IntegerHolder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.BlockRedSandstone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.Main;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.event.HoverEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import com.cheatbreaker.client.util.voicechat.CaptureDevice;
import com.cheatbreaker.client.ui.mainmenu.LegacyCosmeticsMenu;
import recovered.unidentified.UnidentifiedInterface0987;
import recovered.unidentified.UnidentifiedInterface4702;

public class Message {
   public static byte[] a;
   public static byte[] b;
   public String action;

   public static native void m(float var0);

   public static native void b(String var0);

   public static native void k();

   public static native void s(int var0, boolean var1);

   public static void o(String var0) {
      Minecraft var1 = Minecraft.getMinecraft();
      if (var1 != null && var1.ingameGUI != null && var1.ingameGUI.getChatGUI() != null) {
         Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(var0));
      }
   }

   public static native void h(String var0);

   public static native void a(String[] var0);

   public static void d() {
      String var0 = "";
      String var1 = "";
      String var2 = "";
      String var3 = "";
      String var4 = "";
      String var5 = "";
      String var6 = "";

      for (Method var10 : Minecraft.class.getMethods()) {
         if (var10.getReturnType() == Minecraft.class) {
            var0 = var10.getName() + ":()L" + Minecraft.class.getCanonicalName().replaceAll("\\.", "/") + ";";
         } else if (var10.getReturnType() == NetHandlerPlayClient.class) {
            var1 = var10.getName() + ":()L" + NetHandlerPlayClient.class.getCanonicalName().replaceAll("\\.", "/") + ";";
         }
      }

      for (Method var23 : NetHandlerPlayClient.class.getMethods()) {
         if (var23.getParameterTypes().length == 1 && var23.getParameterTypes()[0] == Packet.class) {
            var2 = var23.getName() + ":(L" + Packet.class.getCanonicalName().replaceAll("\\.", "/") + ";)V";
         }
      }

      for (Field var24 : Entity.class.getFields()) {
         if (var24.getType() == float.class) {
            var3 = var24.getName() + ":F";
            break;
         }
      }

      for (Method var25 : Main.class.getMethods()) {
         if (var25.getParameterTypes().length == 1 && var25.getParameterTypes()[0] == String[].class) {
            var4 = var25.getName() + ":([Ljava/lang/String;)V";
         }
      }

      for (Method var26 : CBAgentResources.class.getMethods()) {
         if (var26.isAnnotationPresent(UnidentifiedInterface4702.class)) {
            var5 = var26.getName() + ":(Ljava/lang/String;)[B";
         }

         if (var26.isAnnotationPresent(UnidentifiedInterface0987.class)) {
            var6 = var26.getName() + ":(Ljava/lang/String;)Z";
         }
      }

      a(
         new String[]{
            r(CustomPayloadSender.class.getName()),
            r(S3FPacketCustomPayload.class.getName()),
            r(Minecraft.class.getName()),
            r(NetHandlerPlayClient.class.getName()),
            r(Entity.class.getName()),
            r(CBAgentResources.class.getName()),
            r(Main.class.getName()),
            var0,
            var1,
            var2,
            var3,
            var4,
            var5,
            var6
         }
      );
   }

   public static native void f(String var0, byte[] var1);

   public static void z(boolean var0, com.cheatbreaker.client.nethandler.Packet var1) {
      ChatComponentText var2 = new ChatComponentText(
         EnumChatFormatting.GRAY + (var0 ? "Received: " : "Sent: ") + EnumChatFormatting.WHITE + var1.getClass().getSimpleName()
      );
      var2.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(new Gson().toJson(var1))));
      var2.method_07469(true);
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var2);
   }

   public static void r(byte[] var0) {
      b = var0;
      Minecraft var1 = Minecraft.getMinecraft();
      if (var1.theWorld != null
         && var1.getNetHandler().getNetworkManager().method_25557().isOpen()
         && CheatBreaker.getInstance().getNetHandler().method_11430()) {
      }
   }

   public String getAction() {
      return this.action;
   }

   public Message(String var1) {
      this.action = var1;
   }

   public static String r(String var0) {
      return var0.replaceAll("\\.", "/");
   }

   public static native void e(boolean var0);

   public static void g(String[] var0, String[] var1) {
      for (int var2 = 0; var2 < var0.length; var2++) {
         CheatBreaker.getInstance().recoveredField1579.info(CheatBreaker.getInstance().recoveredField1553 + "Added mic option: " + var1[var2]);
         CheatBreaker.getInstance().method_19741().method_26700().add(new CaptureDevice(var0[var2], var1[var2]));
      }
   }

   public static void j(byte[] var0) {
      CheatBreaker.getInstance().getNetHandler().sendPacketToQueue(new PacketClientVoice(var0));
   }

   public static native void c(String var0, String var1, String var2);

   public static native byte[] i();

   public static void n() {
      Minecraft.getMinecraft().gameSettings.saveOptions();
      CheatBreaker.getInstance().configManager.method_25109();
      CheatBreaker.getInstance().getAssetsWebSocket().close();
      System.exit(0);
   }

   public static native void l(float var0);
}
