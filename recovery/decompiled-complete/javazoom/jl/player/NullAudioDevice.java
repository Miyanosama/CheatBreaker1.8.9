package javazoom.jl.player;

import net.minecraft.client.stream.IStream$AuthFailureReason;
import net.optifine.DynamicLights;
import net.optifine.util.RenderChunkUtils;

public class NullAudioDevice extends AudioDeviceBase {
   public IStream$AuthFailureReason __junk8912650663076325377;
   public DynamicLights __junk313462264485709325;
   public RenderChunkUtils __junk6022681648381145409;

   @Override
   public int getPosition() {
      return 0;
   }
}
