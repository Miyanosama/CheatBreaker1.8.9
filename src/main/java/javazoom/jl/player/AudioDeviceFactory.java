package javazoom.jl.player;

import io.netty.channel.socket.InternetProtocolFamily;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.biome.BiomeGenDesert;
import org.apache.log4j.jmx.LoggerDynamicMBean;

public abstract class AudioDeviceFactory {

   public AudioDevice instantiate(ClassLoader var1, String var2) throws java.lang.ClassNotFoundException, java.lang.IllegalAccessException, java.lang.InstantiationException {
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

   public abstract AudioDevice createAudioDevice() throws javazoom.jl.decoder.JavaLayerException ;
}
