package net.minecraft.client.audio;

import com.cheatbreaker.client.ui.overlay.element.RadioElement;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.util.ResourceLocation;

public class SoundEventAccessorComposite implements ISoundEventAccessor<SoundPoolEntry> {
   public double eventPitch;
   public ResourceLocation soundLocation;
   public RadioElement field_0002;
   public SoundCategory category;
   public double eventVolume;
   public Random rnd;
   public List<ISoundEventAccessor<SoundPoolEntry>> soundPool = Lists.newArrayList();

   public SoundEventAccessorComposite(ResourceLocation var1, double var2, double var4, SoundCategory var6) {
      this.rnd = new Random();
      this.soundLocation = var1;
      this.eventVolume = var4;
      this.eventPitch = var2;
      this.category = var6;
   }

   public SoundCategory getSoundCategory() {
      return this.category;
   }

   @Override
   public int getWeight() {
      int var1 = 0;

      for (ISoundEventAccessor var3 : this.soundPool) {
         var1 += var3.getWeight();
      }

      return var1;
   }

   public SoundPoolEntry cloneEntry() {
      int var1 = this.getWeight();
      if (!this.soundPool.isEmpty() && var1 != 0) {
         int var2 = this.rnd.nextInt(var1);

         for (ISoundEventAccessor var4 : this.soundPool) {
            var2 -= var4.getWeight();
            if (var2 < 0) {
               SoundPoolEntry var5 = (SoundPoolEntry)var4.cloneEntry();
               var5.setPitch(var5.getPitch() * this.eventPitch);
               var5.setVolume(var5.getVolume() * this.eventVolume);
               return var5;
            }
         }

         return SoundHandler.missing_sound;
      } else {
         return SoundHandler.missing_sound;
      }
   }

   public ResourceLocation getSoundEventLocation() {
      return this.soundLocation;
   }

   public void addSoundToEventPool(ISoundEventAccessor<SoundPoolEntry> var1) {
      this.soundPool.add(var1);
   }
}
