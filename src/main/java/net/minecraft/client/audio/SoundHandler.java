package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoundHandler implements IResourceManagerReloadListener, ITickable {
   public SoundManager sndManager;
   public static Logger logger = LogManager.getLogger();
   public static Gson GSON = new GsonBuilder().registerTypeAdapter(SoundList.class, new SoundListSerializer()).create();
   public SoundRegistry sndRegistry = new SoundRegistry();
   public static ParameterizedType TYPE = new ParameterizedType() {
      @Override
      public Type[] getActualTypeArguments() {
         return new Type[]{String.class, SoundList.class};
      }

      @Override
      public Type getOwnerType() {
         return null;
      }

      @Override
      public Type getRawType() {
         return Map.class;
      }
   };
   public IResourceManager mcResourceManager;
   public static SoundPoolEntry missing_sound = new SoundPoolEntry(new ResourceLocation("meta:missing_sound"), 0.0, 0.0, false);

   public void method_27826() {
      this.sndManager.unloadSoundSystem();
   }

   @Override
   public void update() {
      this.sndManager.updateAllSounds();
   }

   public SoundEventAccessorComposite getSound(ResourceLocation var1) {
      return this.sndRegistry.getObject(var1);
   }

   public void setSoundLevel(SoundCategory var1, float var2) {
      if (var1 == SoundCategory.MASTER && var2 <= 0.0F) {
         this.stopSounds();
      }

      this.sndManager.setSoundCategoryVolume(var1, var2);
   }

   public void method_27814() {
      this.sndManager.pauseAllSounds();
   }

   public Map<String, SoundList> getSoundMap(InputStream var1) {
      Map var2;
      try {
         var2 = GSON.fromJson(new InputStreamReader(var1), TYPE);
      } finally {
         IOUtils.closeQuietly(var1);
      }

      return var2;
   }

   public void loadSoundResource(ResourceLocation var1, SoundList var2) {
      boolean var3 = !this.sndRegistry.containsKey(var1);
      SoundEventAccessorComposite var4;
      if (!var3 && !var2.canReplaceExisting()) {
         var4 = this.sndRegistry.getObject(var1);
      } else {
         if (!var3) {
            logger.debug("Replaced sound event location {}", var1);
         }

         var4 = new SoundEventAccessorComposite(var1, 1.0, 1.0, var2.getSoundCategory());
         this.sndRegistry.registerSound(var4);
      }

      for (final SoundList.SoundEntry var6 : var2.getSoundList()) {
         String var7 = var6.getSoundEntryName();
         ResourceLocation var8 = new ResourceLocation(var7);
         final String var9 = var7.contains(":") ? var8.getResourceDomain() : var1.getResourceDomain();
         Object var10;
         switch (var6.getSoundEntryType()) {
            case FILE:
               ResourceLocation var11 = new ResourceLocation(var9, "sounds/" + var8.getResourcePath() + ".ogg");
               InputStream var12 = null;

               try {
                  var12 = this.mcResourceManager.getResource(var11).getInputStream();
               } catch (FileNotFoundException var18) {
                  logger.warn("File {} does not exist, cannot add it to event {}", var11, var1);
                  continue;
               } catch (IOException var19) {
                  logger.warn("Could not load sound file " + var11 + ", cannot add it to event " + var1, var19);
                  continue;
               } finally {
                  IOUtils.closeQuietly(var12);
               }

               var10 = new SoundEventAccessor(
                  new SoundPoolEntry(var11, var6.getSoundEntryPitch(), var6.getSoundEntryVolume(), var6.isStreaming()), var6.getSoundEntryWeight()
               );
               break;
            case SOUND_EVENT:
               var10 = new ISoundEventAccessor<SoundPoolEntry>() {
                  public ResourceLocation field_148726_a = new ResourceLocation(var9, var6.getSoundEntryName());

                  @Override
                  public int getWeight() {
                     SoundEventAccessorComposite var1x = SoundHandler.this.sndRegistry.getObject(this.field_148726_a);
                     return var1x == null ? 0 : var1x.getWeight();
                  }

                  public SoundPoolEntry cloneEntry() {
                     SoundEventAccessorComposite var1x = SoundHandler.this.sndRegistry.getObject(this.field_148726_a);
                     return var1x == null ? SoundHandler.missing_sound : var1x.cloneEntry();
                  }
               };
               break;
            default:
               throw new IllegalStateException("IN YOU FACE");
         }

         var4.addSoundToEventPool((ISoundEventAccessor<SoundPoolEntry>)var10);
      }
   }

   public void stopSounds() {
      this.sndManager.stopAllSounds();
   }

   public void playDelayedSound(ISound var1, int var2) {
      this.sndManager.playDelayedSound(var1, var2);
   }

   public SoundHandler(IResourceManager var1, GameSettings var2) {
      this.mcResourceManager = var1;
      this.sndManager = new SoundManager(this, var2);
   }

   public boolean isSoundPlaying(ISound var1) {
      return this.sndManager.isSoundPlaying(var1);
   }

   public void resumeSounds() {
      this.sndManager.resumeAllSounds();
   }

   public void stopSound(ISound var1) {
      this.sndManager.stopSound(var1);
   }

   public void playSound(ISound var1) {
      this.sndManager.playSound(var1);
   }

   public SoundEventAccessorComposite getRandomSoundFromCategories(SoundCategory... var1) {
      ArrayList var2 = Lists.newArrayList();

      for (ResourceLocation var4 : this.sndRegistry.getKeys()) {
         SoundEventAccessorComposite var5 = this.sndRegistry.getObject(var4);
         if (ArrayUtils.contains(var1, var5.getSoundCategory())) {
            var2.add(var5);
         }
      }

      return var2.isEmpty() ? null : (SoundEventAccessorComposite)var2.get(new Random().nextInt(var2.size()));
   }

   @Override
   public void onResourceManagerReload(IResourceManager var1) {
      this.sndManager.reloadSoundSystem();
      this.sndRegistry.clearMap();

      for (String var3 : var1.getResourceDomains()) {
         try {
            for (IResource var5 : var1.getAllResources(new ResourceLocation(var3, "sounds.json"))) {
               try {
                  Map var6 = this.getSoundMap(var5.getInputStream());

                  for (Entry var8 : (Iterable<Entry>)(Iterable<?>)(var6.entrySet())) {
                     this.loadSoundResource(new ResourceLocation(var3, (String)var8.getKey()), (SoundList)var8.getValue());
                  }
               } catch (RuntimeException var9) {
                  logger.warn("Invalid sounds.json", var9);
               }
            }
         } catch (IOException var10) {
         }
      }
   }

   public void setListener(EntityPlayer var1, float var2) {
      this.sndManager.setListener(var1, var2);
   }
}
