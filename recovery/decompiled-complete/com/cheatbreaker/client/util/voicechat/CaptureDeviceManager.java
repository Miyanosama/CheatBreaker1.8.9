package com.cheatbreaker.client.util.voicechat;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.network.messages.Message;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler$1;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.Mixer.Info;
import net.minecraft.client.Minecraft;
import recovered.unidentified.UnidentifiedClass0491;

public class CaptureDeviceManager {
   public HttpContentDecompressor field_0001;
   public List<UnidentifiedClass0491> field_0002 = new ArrayList<>();
   public WebSocketServerProtocolHandler$1 field_0000;

   public String[] method_26701() {
      String[] var1 = new String[this.field_0002.size()];
      int var2 = 0;

      for (UnidentifiedClass0491 var4 : this.field_0002) {
         var1[var2] = var4.method_03577().replace("Primary Sound Capture Driver", "Default");
         var2++;
      }

      return var1;
   }

   public void method_26704() {
      Info[] var1 = AudioSystem.getMixerInfo();

      for (Info var5 : var1) {
         javax.sound.sampled.Line.Info[] var6 = AudioSystem.getMixer(var5).getTargetLineInfo();
         if (var6.length >= 1 && var6[0].getLineClass().equals(TargetDataLine.class) && var5 != null) {
            Message.g(new String[]{var5.getDescription()}, new String[]{var5.getName()});
         }
      }
   }

   public List<UnidentifiedClass0491> method_26700() {
      return this.field_0002;
   }

   public void method_26702(String var1) {
      this.method_26703(var1, 1.0F);
   }

   public UnidentifiedClass0491 method_26705(String var1) {
      for (UnidentifiedClass0491 var3 : this.field_0002) {
         if (var3.method_03577().equalsIgnoreCase(var1)) {
            return var3;
         }
      }

      return null;
   }

   public void method_26703(String var1, float var2) {
      if (!(Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0096.getValue()) {
         Minecraft.getMinecraft().getSoundHandler().sndManager.method_02907(var1, var2);
      }
   }
}
