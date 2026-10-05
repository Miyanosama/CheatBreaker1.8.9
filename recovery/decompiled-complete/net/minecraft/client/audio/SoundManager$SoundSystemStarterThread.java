package net.minecraft.client.audio;

import net.minecraft.client.stream.TwitchStream$1$1;
import org.apache.log4j.spi.RootLogger;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.Source;

public class SoundManager$SoundSystemStarterThread extends SoundSystem {
   public TwitchStream$1$1 field_0002;
   public RootLogger field_0000;

   public SoundManager$SoundSystemStarterThread(SoundManager var1) {
      this.field_148591_a = var1;
      super();
   }

   public boolean playing(String var1) {
      synchronized (SoundSystemConfig.THREAD_SYNC) {
         if (this.soundLibrary == null) {
            return false;
         } else {
            Source var3 = (Source)this.soundLibrary.getSources().get(var1);
            return var3 == null ? false : var3.playing() || var3.paused() || var3.preLoad;
         }
      }
   }
}
