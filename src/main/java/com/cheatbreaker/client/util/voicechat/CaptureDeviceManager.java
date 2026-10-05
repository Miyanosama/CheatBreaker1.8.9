package com.cheatbreaker.client.util.voicechat;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.network.messages.Message;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.Mixer.Info;
import net.minecraft.client.Minecraft;
import com.cheatbreaker.client.util.voicechat.CaptureDevice;

public class CaptureDeviceManager {
   public List<CaptureDevice> recoveredField84 = new ArrayList<>();

   public String[] method_26701() {
      String[] var1 = new String[this.recoveredField84.size()];
      int var2 = 0;

      for (CaptureDevice var4 : this.recoveredField84) {
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

   public List<CaptureDevice> method_26700() {
      return this.recoveredField84;
   }

   public void method_26702(String var1) {
      this.method_26703(var1, 1.0F);
   }

   public CaptureDevice method_26705(String var1) {
      for (CaptureDevice var3 : this.recoveredField84) {
         if (var3.method_03577().equalsIgnoreCase(var1)) {
            return var3;
         }
      }

      return null;
   }

   public void method_26703(String var1, float var2) {
      if (!(Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField549.getValue()) {
         Minecraft.getMinecraft().getSoundHandler().sndManager.method_02907(var1, var2);
      }
   }
}
