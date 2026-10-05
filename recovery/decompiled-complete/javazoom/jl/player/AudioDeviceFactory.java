package javazoom.jl.player;

import io.netty.channel.socket.InternetProtocolFamily;
import net.minecraft.entity.monster.EntityEnderman$AIPlaceBlock;
import net.minecraft.world.biome.BiomeGenDesert;
import org.apache.log4j.jmx.LoggerDynamicMBean;

public abstract class AudioDeviceFactory {
   public LoggerDynamicMBean __junk1659165085690135378;
   public BiomeGenDesert __junk5946289569089580250;
   public InternetProtocolFamily __junk3345725568726309795;
   public EntityEnderman$AIPlaceBlock __junk7451779881760820245;

   public AudioDevice instantiate(ClassLoader var1, String var2) {
      Object var3 = null;
      Class var4 = null;
      if (var1 == null) {
         var4 = Class.forName(var2);
      } else {
         var4 = var1.loadClass(var2);
      }

      Object var5 = var4.newInstance();
      return (AudioDevice)var5;
   }

   public abstract AudioDevice createAudioDevice();
}
