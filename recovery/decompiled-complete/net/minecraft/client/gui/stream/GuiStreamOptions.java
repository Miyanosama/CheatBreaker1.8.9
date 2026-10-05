package net.minecraft.client.gui.stream;

import com.cheatbreaker.client.module.type.cooldowns.CooldownsModule;
import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.util.EnumChatFormatting;
import net.optifine.shaders.config.ShaderMacro;

public class GuiStreamOptions extends GuiScreen {
   public static GameSettings$Options[] field_152316_f = new GameSettings$Options[]{
      GameSettings$Options.STREAM_CHAT_ENABLED, GameSettings$Options.STREAM_CHAT_USER_FILTER
   };
   public ShaderMacro field_0009;
   public GuiScreen parentScreen;
   public boolean field_152315_t = false;
   public TwitchStream field_0001;
   public int field_152314_s;
   public CooldownsModule field_0010;
   public String field_152319_i;
   public static GameSettings$Options[] field_152312_a = new GameSettings$Options[]{
      GameSettings$Options.STREAM_BYTES_PER_PIXEL,
      GameSettings$Options.STREAM_FPS,
      GameSettings$Options.STREAM_KBPS,
      GameSettings$Options.STREAM_SEND_METADATA,
      GameSettings$Options.STREAM_VOLUME_MIC,
      GameSettings$Options.STREAM_VOLUME_SYSTEM,
      GameSettings$Options.STREAM_MIC_TOGGLE_BEHAVIOR,
      GameSettings$Options.STREAM_COMPRESSION
   };
   public WebSocket08FrameDecoder field_0011;
   public GameSettings field_152318_h;
   public String field_152313_r;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.field_152319_i, this.l / 2, 20, 16777215);
      this.drawCenteredString(this.q, this.field_152313_r, this.l / 2, this.field_152314_s, 16777215);
      if (this.field_152315_t) {
         this.drawCenteredString(this.q, EnumChatFormatting.RED + I18n.format("options.stream.changes"), this.l / 2, 20 + this.q.FONT_HEIGHT, 16777215);
      }

      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void initGui() {
      int var1 = 0;
      this.field_152319_i = I18n.format("options.stream.title");
      this.field_152313_r = I18n.format("options.stream.chat.title");

      for (GameSettings$Options var5 : field_152312_a) {
         if (var5.getEnumFloat()) {
            this.n.add(new GuiOptionSlider(var5.returnEnumOrdinal(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 + 24 * (var1 >> 1), var5));
         } else {
            this.n
               .add(
                  new GuiOptionButton(
                     var5.returnEnumOrdinal(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 + 24 * (var1 >> 1), var5, this.field_152318_h.getKeyBinding(var5)
                  )
               );
         }

         var1++;
      }

      if (var1 % 2 == 1) {
         var1++;
      }

      this.field_152314_s = this.m / 6 + 24 * (var1 >> 1) + 6;
      var1 += 2;

      for (GameSettings$Options var11 : field_152316_f) {
         if (var11.getEnumFloat()) {
            this.n.add(new GuiOptionSlider(var11.returnEnumOrdinal(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 + 24 * (var1 >> 1), var11));
         } else {
            this.n
               .add(
                  new GuiOptionButton(
                     var11.returnEnumOrdinal(),
                     this.l / 2 - 155 + var1 % 2 * 160,
                     this.m / 6 + 24 * (var1 >> 1),
                     var11,
                     this.field_152318_h.getKeyBinding(var11)
                  )
               );
         }

         var1++;
      }

      this.n.add(new GuiButton(200, this.l / 2 - 155, this.m / 6 + 168, 150, 20, I18n.format("gui.done")));
      GuiButton var8 = new GuiButton(201, this.l / 2 + 5, this.m / 6 + 168, 150, 20, I18n.format("options.stream.ingestSelection"));
      var8.l = this.j.getTwitchStream().method_02394() && this.j.getTwitchStream().func_152925_v().length > 0 || this.j.getTwitchStream().func_152908_z();
      this.n.add(var8);
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k < 100 && var1 instanceof GuiOptionButton) {
            GameSettings$Options var2 = ((GuiOptionButton)var1).returnEnumOptions();
            this.field_152318_h.setOptionValue(var2, 1);
            var1.j = this.field_152318_h.getKeyBinding(GameSettings$Options.getEnumOptions(var1.k));
            if (this.j.getTwitchStream().isBroadcasting()
               && var2 != GameSettings$Options.STREAM_CHAT_ENABLED
               && var2 != GameSettings$Options.STREAM_CHAT_USER_FILTER) {
               this.field_152315_t = true;
            }
         } else if (var1 instanceof GuiOptionSlider) {
            if (var1.k == GameSettings$Options.STREAM_VOLUME_MIC.returnEnumOrdinal()) {
               this.j.getTwitchStream().updateStreamVolume();
            } else if (var1.k == GameSettings$Options.STREAM_VOLUME_SYSTEM.returnEnumOrdinal()) {
               this.j.getTwitchStream().updateStreamVolume();
            } else if (this.j.getTwitchStream().isBroadcasting()) {
               this.field_152315_t = true;
            }
         }

         if (var1.k == 200) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(this.parentScreen);
         } else if (var1.k == 201) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(new GuiIngestServers(this));
         }
      }
   }

   public GuiStreamOptions(GuiScreen var1, GameSettings var2) {
      this.parentScreen = var1;
      this.field_152318_h = var2;
   }
}
