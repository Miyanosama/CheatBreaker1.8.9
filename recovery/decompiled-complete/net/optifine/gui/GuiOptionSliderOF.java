package net.optifine.gui;

import io.netty.handler.codec.http.HttpResponseEncoder;
import net.minecraft.client.audio.SoundManager$SoundSystemStarterThread;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.server.management.PlayerProfileCache$Serializer;
import recovered.unidentified.UnidentifiedClass0557;

public class GuiOptionSliderOF extends GuiOptionSlider implements IOptionControl {
   public GameSettings$Options option = null;
   public SoundManager$SoundSystemStarterThread field_0001;
   public UnidentifiedClass0557 field_0002;
   public PlayerProfileCache$Serializer field_0000;
   public GuiEditSign field_0005;
   public HttpResponseEncoder field_0003;

   @Override
   public GameSettings$Options getOption() {
      return this.option;
   }

   public GuiOptionSliderOF(int var1, int var2, int var3, GameSettings$Options var4) {
      super(var1, var2, var3, var4);
      this.option = var4;
   }
}
