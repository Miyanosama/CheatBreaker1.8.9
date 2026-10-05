package net.minecraft.client.audio;

public class SoundEventAccessor implements ISoundEventAccessor<SoundPoolEntry> {
   public int recoveredField1335;
   public SoundPoolEntry recoveredField1336;

   public SoundEventAccessor(SoundPoolEntry var1, int var2) {
      this.recoveredField1336 = var1;
      this.recoveredField1335 = var2;
   }

   @Override
   public int getWeight() {
      return this.recoveredField1335;
   }

   public SoundPoolEntry cloneEntry() {
      return new SoundPoolEntry(this.recoveredField1336);
   }
}
