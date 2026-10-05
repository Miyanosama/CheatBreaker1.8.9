package javazoom.jl.player;

import io.netty.util.concurrent.ImmediateExecutor;
import javazoom.jl.decoder.JavaLayerException;
import net.minecraft.client.resources.GrassColorReloadListener;
import net.optifine.config.RangeInt;
import net.optifine.entity.model.CustomModelRegistry;
import recovered.unidentified.UnidentifiedClass0499;

public class JavaSoundAudioDeviceFactory extends AudioDeviceFactory {
   public ImmediateExecutor __junk8877327619467079458;
   public UnidentifiedClass0499 __junk3351662082889481785;
   public AudioDeviceFactory __junk7333951610096238743;
   public static String DEVICE_CLASS_NAME;
   public RangeInt __junk8689887653942763832;
   public boolean tested = false;
   public CustomModelRegistry __junk2145555755344525553;
   public GrassColorReloadListener __junk8478148705622730834;

   public JavaSoundAudioDevice createAudioDeviceImpl() {
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
   public synchronized AudioDevice createAudioDevice() {
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

   public void testAudioDevice() {
      JavaSoundAudioDevice var1 = this.createAudioDeviceImpl();
      var1.test();
   }
}
