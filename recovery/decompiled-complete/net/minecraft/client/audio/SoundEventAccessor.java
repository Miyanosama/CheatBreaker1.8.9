package net.minecraft.client.audio;

import io.netty.handler.traffic.TrafficCounter;

public class SoundEventAccessor implements ISoundEventAccessor<SoundPoolEntry> {
   public TrafficCounter field_0001;
   public int field_0002;
   public SoundPoolEntry field_0000;

   public SoundEventAccessor(SoundPoolEntry var1, int var2) {
      this.field_0000 = var1;
      this.field_0002 = var2;
   }

   @Override
   public int getWeight() {
      return this.field_0002;
   }

   public SoundPoolEntry method_25115() {
      return new SoundPoolEntry(this.field_0000);
   }
}
