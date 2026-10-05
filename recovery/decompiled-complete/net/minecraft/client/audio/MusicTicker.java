package net.minecraft.client.audio;

import com.cheatbreaker.client.util.server.ServerMappingLoader;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import org.slf4j.helpers.BasicMDCAdapter;

public class MusicTicker implements ITickable {
   public ISound currentMusic;
   public Random rand = new Random();
   public ServerMappingLoader field_0002;
   public RenderBiped field_0004;
   public Minecraft mc;
   public int timeUntilNextMusic = 100;
   public BasicMDCAdapter field_0006;

   @Override
   public void update() {
      MusicTicker$MusicType var1 = this.mc.getAmbientMusicType();
      if (this.currentMusic != null) {
         if (!var1.getMusicLocation().equals(this.currentMusic.getSoundLocation())) {
            this.mc.getSoundHandler().stopSound(this.currentMusic);
            this.timeUntilNextMusic = MathHelper.getRandomIntegerInRange(this.rand, 0, var1.getMinDelay() / 2);
         }

         if (!this.mc.getSoundHandler().isSoundPlaying(this.currentMusic)) {
            this.currentMusic = null;
            this.timeUntilNextMusic = Math.min(MathHelper.getRandomIntegerInRange(this.rand, var1.getMinDelay(), var1.getMaxDelay()), this.timeUntilNextMusic);
         }
      }

      if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0) {
         this.func_181558_a(var1);
      }
   }

   public MusicTicker(Minecraft var1) {
      this.mc = var1;
   }

   public void func_181558_a(MusicTicker$MusicType var1) {
      this.currentMusic = PositionedSoundRecord.create(var1.getMusicLocation());
      this.mc.getSoundHandler().playSound(this.currentMusic);
      this.timeUntilNextMusic = Integer.MAX_VALUE;
   }

   public void func_181557_a() {
      if (this.currentMusic != null) {
         this.mc.getSoundHandler().stopSound(this.currentMusic);
         this.currentMusic = null;
         this.timeUntilNextMusic = 0;
      }
   }
}
