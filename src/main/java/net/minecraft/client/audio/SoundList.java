package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import java.util.List;

public class SoundList {
   public boolean replaceExisting;
   public SoundCategory category;
   public List<SoundList.SoundEntry> soundList = Lists.newArrayList();

   public boolean canReplaceExisting() {
      return this.replaceExisting;
   }

   public void setReplaceExisting(boolean var1) {
      this.replaceExisting = var1;
   }

   public List<SoundList.SoundEntry> getSoundList() {
      return this.soundList;
   }

   public SoundCategory getSoundCategory() {
      return this.category;
   }

   public void setSoundCategory(SoundCategory var1) {
      this.category = var1;
   }

   public static class SoundEntry {
      public float pitch;
      public int weight;
      public float volume = 1.0F;
      public SoundList.SoundEntry.Type type;
      public boolean streaming;
      public String name;

      public SoundEntry() {
         this.pitch = 1.0F;
         this.weight = 1;
         this.type = SoundList.SoundEntry.Type.FILE;
         this.streaming = false;
      }

      public String getSoundEntryName() {
         return this.name;
      }

      public void setSoundEntryName(String var1) {
         this.name = var1;
      }

      public int getSoundEntryWeight() {
         return this.weight;
      }

      public boolean isStreaming() {
         return this.streaming;
      }

      public void setSoundEntryPitch(float var1) {
         this.pitch = var1;
      }

      public SoundList.SoundEntry.Type getSoundEntryType() {
         return this.type;
      }

      public float getSoundEntryPitch() {
         return this.pitch;
      }

      public void setSoundEntryType(SoundList.SoundEntry.Type var1) {
         this.type = var1;
      }

      public void setSoundEntryWeight(int var1) {
         this.weight = var1;
      }

      public void setStreaming(boolean var1) {
         this.streaming = var1;
      }

      public float getSoundEntryVolume() {
         return this.volume;
      }

      public void setSoundEntryVolume(float var1) {
         this.volume = var1;
      }

      public static enum Type {
         FILE("file"),
         SOUND_EVENT("event");

         // $VF: synthetic field
         public static SoundList.SoundEntry.Type[] $VALUES = new SoundList.SoundEntry.Type[]{
            SoundList.SoundEntry.Type.FILE, SoundList.SoundEntry.Type.SOUND_EVENT
         };
         public String field_148583_c;

         Type(String var3) {
            this.field_148583_c = var3;
         }

         public static SoundList.SoundEntry.Type getType(String var0) {
            for (SoundList.SoundEntry.Type var4 : values()) {
               if (var4.field_148583_c.equals(var0)) {
                  return var4;
               }
            }

            return null;
         }
      }
   }
}
