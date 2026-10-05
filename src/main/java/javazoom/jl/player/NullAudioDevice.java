package javazoom.jl.player;

import net.minecraft.client.stream.IStream;
import net.optifine.DynamicLights;
import net.optifine.util.RenderChunkUtils;

public class NullAudioDevice extends AudioDeviceBase {

   @Override
   public int getPosition() {
      return 0;
   }
}
