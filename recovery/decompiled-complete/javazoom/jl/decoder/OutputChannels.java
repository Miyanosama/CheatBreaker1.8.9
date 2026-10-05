package javazoom.jl.decoder;

import net.minecraft.client.particle.EntityBlockDustFX$Factory;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.optifine.util.FrameEvent;
import org.apache.log4j.jmx.AppenderDynamicMBean;
import recovered.unidentified.UnidentifiedClass4798;

public class OutputChannels {
   public static OutputChannels DOWNMIX = new OutputChannels(3);
   public static OutputChannels LEFT = new OutputChannels(1);
   public int outputChannels;
   public static int LEFT_CHANNEL;
   public RenderSpider __junk4092948154604880344;
   public static int DOWNMIX_CHANNELS;
   public EntityBlockDustFX$Factory __junk658561731401847077;
   public static OutputChannels RIGHT = new OutputChannels(2);
   public UnidentifiedClass4798 __junk1783393874282560465;
   public static int RIGHT_CHANNEL;
   public static int BOTH_CHANNELS;
   public static OutputChannels BOTH = new OutputChannels(0);
   public FrameEvent __junk9197879445094984119;
   public AppenderDynamicMBean __junk1054237859095039091;

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
