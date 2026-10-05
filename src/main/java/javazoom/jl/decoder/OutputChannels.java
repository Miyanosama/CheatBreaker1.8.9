package javazoom.jl.decoder;

import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.optifine.util.FrameEvent;
import org.apache.log4j.jmx.AppenderDynamicMBean;
import com.cheatbreaker.client.emote.type.FlossEmote;

public class OutputChannels {
   public static final int LEFT_CHANNEL = 1;
   public static final int DOWNMIX_CHANNELS = 3;
   public int outputChannels;
   public static final int RIGHT_CHANNEL = 2;
   public static final int BOTH_CHANNELS = 0;
   public static OutputChannels LEFT = new OutputChannels(1);
   public static OutputChannels RIGHT = new OutputChannels(2);
   public static OutputChannels BOTH = new OutputChannels(0);
   public static OutputChannels DOWNMIX = new OutputChannels(3);

   @Override
   public boolean equals(Object var1) {
      boolean var2 = false;
      if (var1 instanceof OutputChannels) {
         OutputChannels var3 = (OutputChannels)var1;
         var2 = var3.outputChannels == this.outputChannels;
      }

      return var2;
   }

   @Override
   public int hashCode() {
      return this.outputChannels;
   }

   public int getChannelsOutputCode() {
      return this.outputChannels;
   }

   public static OutputChannels fromInt(int var0) {
      switch (var0) {
         case 0:
            return BOTH;
         case 1:
            return LEFT;
         case 2:
            return RIGHT;
         case 3:
            return DOWNMIX;
         default:
            throw new IllegalArgumentException("Invalid channel code: " + var0);
      }
   }

   public int getChannelCount() {
      return this.outputChannels == 0 ? 2 : 1;
   }

   public OutputChannels(int var1) {
      this.outputChannels = var1;
      if (var1 < 0 || var1 > 3) {
         throw new IllegalArgumentException("channels");
      }
   }
}
