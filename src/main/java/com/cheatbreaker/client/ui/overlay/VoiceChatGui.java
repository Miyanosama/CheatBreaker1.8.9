package com.cheatbreaker.client.ui.overlay;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.nethandler.client.PacketVoiceChannelSwitch;
import com.cheatbreaker.client.nethandler.client.PacketVoiceMute;
import com.cheatbreaker.client.ui.AbstractGui;
import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.voicechat.VoiceChannel;
import com.cheatbreaker.client.util.voicechat.VoiceUser;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class VoiceChatGui extends AbstractGui {
   public GradientTextButton recoveredField885;
   public ResourceLocation speakerImage;
   public GradientTextButton recoveredField886;
   public ResourceLocation mutedSpeakerImage;
   public static CheatBreaker cheatBreaker = CheatBreaker.getInstance();
   public List<GradientTextButton> someRandomAssButtons;
   public VoiceChannel voiceChannel = null;
   public ResourceLocation headphonesImage = new ResourceLocation("client/icons/headphones.png");
   public ResourceLocation microphoneImage;

   @Override
   public void initGui() {
      this.method_11296();
      if (cheatBreaker.getNetHandler().method_11476() && cheatBreaker.getNetHandler().getVoiceChannels() != null) {
         this.voiceChannel = cheatBreaker.getNetHandler().getVoiceChannel();
         boolean var1 = cheatBreaker.getNetHandler().getUuidList().contains(this.j.thePlayer.getGameProfile().getId());
         this.recoveredField885 = new GradientTextButton("Join Channel");
         this.recoveredField886 = new GradientTextButton(var1 ? "Un-deafen" : "Deafen");
         this.someRandomAssButtons = new ArrayList<>();
         float var2 = 16.0F;
         float var3 = this.getScaledWidth() / 8.0F;
         float var4 = this.getScaledHeight() / 2.0F - 8.0F - var2 * cheatBreaker.getNetHandler().getVoiceChannels().size() / 2.0F;
         int var5 = 0;

         for (VoiceChannel var7 : cheatBreaker.getNetHandler().getVoiceChannels()) {
            GradientTextButton var8 = new GradientTextButton(var7.method_03528());
            this.someRandomAssButtons.add(var8);
            var8.setElementSize(var3, var4 + 12.0F + var2 * var5, 110.0F, 12.0F);
            if (this.voiceChannel == var7) {
               var8.method_25132();
            }

            var5++;
         }
      }
   }

   public VoiceChatGui() {
      this.speakerImage = new ResourceLocation("client/icons/speaker.png");
      this.mutedSpeakerImage = new ResourceLocation("client/icons/speaker-mute.png");
      this.microphoneImage = new ResourceLocation("client/icons/microphone-64.png");
   }

   public void method_12623(float var1, float var2, float var3, float var4) {
      float var5 = 14.0F;
      float var6 = this.voiceChannel.getUsers().size() * var5;
      var4 -= var6 / 2.0F;
      int var7 = 0;

      for (VoiceUser var9 : this.voiceChannel.getUsers()) {
         float var10 = var4 + var7 * var5;
         boolean var11 = var1 > var3 + 158.0F && var1 < var3 + 184.0F && var2 > var10 && var2 < var10 + var5;
         if (!var9.getUUID().equals(this.j.thePlayer.aK()) && var11) {
            this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            cheatBreaker.getNetHandler().sendPacketToQueue(new PacketVoiceChannelSwitch(var9.getUUID()));
            if (!cheatBreaker.getNetHandler().getUuidList().removeIf(var1x -> var1x.equals(var9.getUUID()))) {
               cheatBreaker.getNetHandler().getUuidList().add(var9.getUUID());
            }
         }

         var7++;
      }
   }

   public void method_12630(float var1, float var2, float var3, float var4) {
      float var5 = 14.0F;
      float var6 = this.voiceChannel.getUsers().size() * var5;
      float var16;
      cheatBreaker.recoveredField1595.drawString(this.voiceChannel.method_03528(), var3, (var16 = var4 - var6 / 2.0F) - 14.0F, -1);
      if (!this.method_12629()) {
         this.recoveredField885.setElementSize(var3 + 125.0F, var16 - 14.0F, 50.0F, 12.0F);
         this.recoveredField885.drawElement(var1, var2, true);
      }

      Gui.drawRect(var3, var16, var3 + 175.0F, var16 + var6, -1626337264);
      int var7 = 0;
      ArrayList<VoiceUser> var8 = Lists.newArrayList(this.voiceChannel.getUsers());
      var8.sort((var1x, var2x) -> {
         if (this.voiceChannel.method_03527(var1x.getUUID()) && !this.voiceChannel.method_03527(var2x.getUUID())) {
            return -1;
         } else {
            return !this.voiceChannel.method_03527(var1x.getUUID()) && this.voiceChannel.method_03527(var2x.getUUID()) ? 1 : 0;
         }
      });

      for (VoiceUser var10 : (Iterable<VoiceUser>)(Iterable<?>)(var8)) {
         boolean var11 = this.voiceChannel.method_03527(var10.getUUID());
         boolean var12 = cheatBreaker.getNetHandler().getUuidList().contains(var10.getUUID());
         float var13 = var16 + var7 * var5;
         boolean var14 = var1 > var3 + 158.0F && var1 < var3 + 184.0F && var2 > var13 && var2 < var13 + var5;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         if (!var11) {
            RenderUtil.method_22064(this.headphonesImage, var3 + 4.0F, var13 + 3.0F, 8.0F, 8.0F);
         } else {
            RenderUtil.method_22064(this.microphoneImage, var3 + 4.0F, var13 + 3.0F, 8.0F, 8.0F);
         }

         float var15 = var3 + 10.0F;
         if (!var10.getUUID().equals(this.j.thePlayer.aK())) {
            if (var12) {
               GL11.glColor4f(1.0F, 0.1F, 0.1F, var14 ? 1.0F : 0.6F);
               RenderUtil.method_22064(this.mutedSpeakerImage, var3 + 162.0F, var13 + 3.0F, 8.0F, 8.0F);
            } else {
               GL11.glColor4f(1.0F, 1.0F, 1.0F, var14 ? 1.0F : 0.6F);
               RenderUtil.method_22064(this.speakerImage, var3 + 162.0F, var13 + 3.0F, 8.0F, 8.0F);
            }
         }

         cheatBreaker.recoveredField1589.drawString(var10.getUsername().toUpperCase(), var15 + 6.0F, var13 + 2.0F, var11 ? -1 : 1879048191);
         var7++;
      }
   }

   @Override
   public void onMouseReleased(float var1, float var2, int var3) {
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      if (this.someRandomAssButtons != null) {
         for (GradientTextButton var6 : this.someRandomAssButtons) {
            VoiceChannel var5;
            if (var6.a_(var1, var2) && this.voiceChannel != (var5 = this.method_12627(var6.method_25134()))) {
               for (GradientTextButton var8 : this.someRandomAssButtons) {
                  if (this.voiceChannel != cheatBreaker.getNetHandler().getVoiceChannel() && var8.method_25134().equals(this.voiceChannel.method_03528())) {
                     var8.method_25138();
                  }
               }

               this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
               this.voiceChannel = var5;
               if (this.voiceChannel != cheatBreaker.getNetHandler().getVoiceChannel()) {
                  var6.method_25133();
               }
            }
         }

         if (this.voiceChannel != null) {
            if (this.recoveredField885.a_(var1, var2)) {
               this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
               cheatBreaker.getNetHandler().sendPacketToQueue(new PacketVoiceMute(this.voiceChannel.getUUID()));

               for (GradientTextButton var12 : this.someRandomAssButtons) {
                  var12.method_25138();
               }

               for (GradientTextButton var13 : this.someRandomAssButtons) {
                  if (var13.method_25134().equals(this.voiceChannel.method_03528())) {
                     var13.method_25132();
                  }
               }
            }

            if (this.recoveredField886.a_(var1, var2)) {
               UUID var11 = this.j.thePlayer.getGameProfile().getId();
               this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
               cheatBreaker.getNetHandler().sendPacketToQueue(new PacketVoiceChannelSwitch(var11));
               if (!cheatBreaker.getNetHandler().getUuidList().removeIf(var1x -> var1x.equals(var11))) {
                  cheatBreaker.getNetHandler().getUuidList().add(var11);
               }

               this.recoveredField886
                  .method_25135(cheatBreaker.getNetHandler().getUuidList().contains(this.j.thePlayer.getGameProfile().getId()) ? "Un-deafen" : "Deafen");
            }

            this.method_12623(var1, var2, this.getScaledWidth() / 8.0F + 130.0F, this.getScaledHeight() / 2.0F);
         }
      }
   }

   @Override
   public void drawMenu(float var1, float var2) {
      this.method_11295(this.getScaledWidth(), this.getScaledHeight());
      float var3 = this.getScaledWidth() / 8.0F;
      if (cheatBreaker.getNetHandler().method_11476() && cheatBreaker.getNetHandler().getVoiceChannels() != null) {
         float var6 = 16.0F;
         float var5 = this.getScaledHeight() / 2.0F - 8.0F - var6 * cheatBreaker.getNetHandler().getVoiceChannels().size() / 2.0F;
         cheatBreaker.recoveredField1595.drawString("VOICE CHAT", var3, var5 - 4.0F, -1);
         this.recoveredField886.setElementSize(var3 + 60.0F, var5 - 4.0F, 50.0F, 12.0F);
         this.recoveredField886.drawElement(var1, var2, true);
         this.someRandomAssButtons.forEach(var3x -> {
            if (this.method_12627(var3x.method_25134()) == cheatBreaker.getNetHandler().getVoiceChannel()) {
               var3x.method_25137(var1, var2, true);
               RenderUtil.method_22064(new ResourceLocation("client/icons/microphone-64.png"), var3x.getX() + 4.0F, var3x.getY() + 2.0F, 8.0F, 8.0F);
            } else if (this.voiceChannel != null && this.voiceChannel.method_03528().equals(var3x.method_25134())) {
               var3x.method_25137(var1, var2, true);
            } else {
               var3x.drawElement(var1, var2, true);
            }
         });
         if (this.voiceChannel != null) {
            this.method_12630(var1, var2, var3 + 130.0F, this.getScaledHeight() / 2.0F);
         }
      } else {
         float var4 = this.getScaledHeight() / 2.0F - 8.0F;
         cheatBreaker.recoveredField1595.drawString("VOICE CHAT IS NOT SUPPORTED", var3, var4, -1);
      }
   }

   @Override
   public void a_() {
      this.j.entityRenderer.stopUseShader();
   }

   public VoiceChannel method_12627(String var1) {
      Iterator var3 = cheatBreaker.getNetHandler().getVoiceChannels().iterator();

      while (var3.hasNext()) {
         VoiceChannel var2;
         if ((var2 = (VoiceChannel)var3.next()).method_03528().equals(var1)) {
            return var2;
         }
      }

      return null;
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      super.keyTyped(var1, var2);
      if (var2 == 25 && recoveredField2527.method_21210()) {
         if ((Boolean)cheatBreaker.getGlobalSettings().recoveredField594.getValue()) {
            this.j.entityRenderer.stopUseShader();
         }

         this.j.displayGuiScreen(null);
         this.j.method_20340();
      }
   }

   public boolean method_12629() {
      return this.voiceChannel == cheatBreaker.getNetHandler().getVoiceChannel();
   }
}
