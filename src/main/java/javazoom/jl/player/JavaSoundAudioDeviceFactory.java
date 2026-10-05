package javazoom.jl.player;

import io.netty.util.concurrent.ImmediateExecutor;
import javazoom.jl.decoder.JavaLayerException;
import net.minecraft.client.resources.GrassColorReloadListener;
import net.optifine.config.RangeInt;
import net.optifine.entity.model.CustomModelRegistry;
import junit.swingui.DefaultFailureDetailView$StackEntryRenderer;

public class JavaSoundAudioDeviceFactory extends AudioDeviceFactory {
   public static final String DEVICE_CLASS_NAME = "javazoom.jl.player.JavaSoundAudioDevice";
   public boolean tested = false;

   public JavaSoundAudioDevice createAudioDeviceImpl() throws javazoom.jl.decoder.JavaLayerException {
      ClassLoader var1 = this.getClass().getClassLoader();

      try {
         return (JavaSoundAudioDevice)this.instantiate(var1, "javazoom.jl.player.JavaSoundAudioDevice");
      } catch (Exception var3) {
         throw new JavaLayerException("Cannot create JavaSound device", var3);
      } catch (LinkageError var4) {
         throw new JavaLayerException("Cannot create JavaSound device", var4);
      }
   }

   @Override
   public synchronized AudioDevice createAudioDevice() throws javazoom.jl.decoder.JavaLayerException {
      if (!this.tested) {
         this.testAudioDevice();
         this.tested = true;
      }

      try {
         return this.createAudioDeviceImpl();
      } catch (Exception var2) {
         throw new JavaLayerException("unable to create JavaSound device: " + var2);
      } catch (LinkageError var3) {
         throw new JavaLayerException("unable to create JavaSound device: " + var3);
      }
   }

   public void testAudioDevice() throws javazoom.jl.decoder.JavaLayerException {
      JavaSoundAudioDevice var1 = this.createAudioDeviceImpl();
      var1.test();
   }
}
