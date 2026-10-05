package javazoom.jl.player;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import java.util.Enumeration;
import java.util.Hashtable;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.LayerIDecoder;
import net.minecraft.block.BlockNewLeaf;

public class FactoryRegistry extends AudioDeviceFactory {
   public static FactoryRegistry instance = null;
   public Hashtable factories = new Hashtable();

   public void removeFactoryType(Class var1) {
      this.factories.remove(var1);
   }

   public static synchronized FactoryRegistry systemRegistry() {
      if (instance == null) {
         instance = new FactoryRegistry();
         instance.registerDefaultFactories();
      }

      return instance;
   }

   public void removeFactory(AudioDeviceFactory var1) {
      this.factories.remove(var1.getClass());
   }

   public void addFactory(AudioDeviceFactory var1) {
      this.factories.put(var1.getClass(), var1);
   }

   public AudioDeviceFactory[] getFactoriesPriority() {
      AudioDeviceFactory[] var1 = null;
      synchronized (this.factories) {
         int var3 = this.factories.size();
         if (var3 != 0) {
            var1 = new AudioDeviceFactory[var3];
            int var4 = 0;
            Enumeration var5 = this.factories.elements();

            while (var5.hasMoreElements()) {
               AudioDeviceFactory var6 = (AudioDeviceFactory)var5.nextElement();
               var1[var4++] = var6;
            }
         }

         return var1;
      }
   }

   public void registerDefaultFactories() {
      this.addFactory(new JavaSoundAudioDeviceFactory());
   }

   @Override
   public AudioDevice createAudioDevice() throws javazoom.jl.decoder.JavaLayerException {
      AudioDevice var1 = null;
      AudioDeviceFactory[] var2 = this.getFactoriesPriority();
      if (var2 == null) {
         throw new JavaLayerException(this + ": no factories registered");
      } else {
         JavaLayerException var3 = null;

         for (int var4 = 0; var1 == null && var4 < var2.length; var4++) {
            try {
               var1 = var2[var4].createAudioDevice();
            } catch (JavaLayerException var6) {
               var3 = var6;
            }
         }

         if (var1 == null && var3 != null) {
            throw new JavaLayerException("Cannot create AudioDevice", var3);
         } else {
            return var1;
         }
      }
   }
}
