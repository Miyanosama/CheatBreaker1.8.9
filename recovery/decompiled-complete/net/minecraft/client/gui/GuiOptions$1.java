package net.minecraft.client.gui;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.resources.FoliageColorReloadListener;
import net.optifine.player.PlayerConfigurationParser;

public class GuiOptions$1 extends GuiButton {
   public PlayerConfigurationParser field_0001;
   public FoliageColorReloadListener field_0002;

   @Override
   public void playPressSound(SoundHandler var1) {
      SoundEventAccessorComposite var2 = var1.getRandomSoundFromCategories(
         SoundCategory.ANIMALS, SoundCategory.BLOCKS, SoundCategory.MOBS, SoundCategory.PLAYERS, SoundCategory.WEATHER
      );
      if (var2 != null) {
         var1.playSound(PositionedSoundRecord.create(var2.getSoundEventLocation(), 0.5F));
      }
   }

   public GuiOptions$1(GuiOptions var1, int var2, int var3, int var4, int var5, int var6, String var7) {
      this.field_146130_o = var1;
      super(var2, var3, var4, var5, var6, var7);
   }
}
