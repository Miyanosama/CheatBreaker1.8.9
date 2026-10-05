package com.cheatbreaker.client.util.voicechat;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class VoiceChat {
   public ResourceLocation recoveredField3915;
   public CheatBreaker cheatbreaker;
   public boolean recoveredField3916;
   public Minecraft minecraft = Minecraft.getMinecraft();
   public Map<VoiceUser, Object> recoveredField3917;

   public void renderHeadAndName(String var1, String var2, float var3, float var4, boolean var5) {
      if (var5) {
         RenderUtil.method_22057(var3, var4, var3 + 110.0F, var4 + 18.0F, -11493284, -10176146, -11164318);
      } else {
         RenderUtil.method_22057(var3, var4, var3 + 110.0F, var4 + 18.0F, -1356454362, -1355664846, -1356191190);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      ResourceLocation var6 = CheatBreaker.getInstance().method_19810(var1);
      RenderUtil.drawIcon(var6, 7.0F, var3 + 2.0F, var4 + 2.0F);
      this.cheatbreaker.recoveredField1548.drawString(var1, var3 + 22.0F, var4 + 4.0F, -1);
   }

   public void onRender(GuiDrawEvent var1) {
      if (this.cheatbreaker.getNetHandler().method_11476()
         && this.cheatbreaker.getNetHandler().getVoiceChannels() != null
         && (!this.recoveredField3917.isEmpty() || this.recoveredField3916)) {
         float var2 = 20.0F;
         float var3 = (float)var1.getResolution().getScaledWidth_double() - 120.0F;
         float[] var4 = new float[]{10.0F};
         if (this.recoveredField3916) {
            this.renderHeadAndName(this.minecraft.thePlayer.z_(), this.minecraft.getSession().getPlayerID(), var3, var4[0], true);
            var4[0] += var2;
         }

         this.recoveredField3917.forEach((var4x, var5) -> {
            this.renderHeadAndName(var4x.getUsername(), var4x.getUUID().toString(), var3, var4[0], false);
            var4[0] += var2;
         });
      }
   }

   public VoiceChat() {
      this.cheatbreaker = CheatBreaker.getInstance();
      this.recoveredField3915 = new ResourceLocation("client/icons/microphone-64.png");
      this.recoveredField3917 = new HashMap<>();
      CheatBreaker.getInstance().method_19817().method_21938(GuiDrawEvent.class, this::onRender);
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::onTick);
   }

   public void addUserToSpoken(UUID var1) {
      VoiceUser var2 = this.cheatbreaker.getNetHandler().getVoiceUser(var1);
      if (var2 != null) {
         this.recoveredField3917.put(var2, System.currentTimeMillis() + 250L);
      }
   }

   public void onTick(TickEvent var1) {
      if (!this.recoveredField3917.isEmpty()) {
         ArrayList var2 = new ArrayList();

         for (Entry var4 : this.recoveredField3917.entrySet()) {
            if (System.currentTimeMillis() - (Long)var4.getValue() >= 0L) {
               var2.add(var4.getKey());
            }
         }

         var2.forEach(var1x -> {
            Long var2x = (Long)this.recoveredField3917.remove(var1x);
         });
      }
   }

   public boolean isKeyDown(int var1) {
      return var1 != 0 && (var1 < 0 ? Mouse.isButtonDown(var1 + 100) : Keyboard.isKeyDown(var1));
   }
}
