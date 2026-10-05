package net.minecraft.command;

import com.google.common.base.Predicate;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$1;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.multiplayer.WorldClient$2;
import net.minecraft.entity.Entity;
import net.minecraft.world.biome.BiomeCache$Block;

public class PlayerSelector$10 implements Predicate<Entity> {
   public BiomeCache$Block field_0003;
   public SoundHandler field_0005;
   public WorldClient$2 field_0004;
   public ConcurrentHashMapV8$1 field_0001;

   public PlayerSelector$10(String var1, boolean var2) {
      this.field_179602_a = var1;
      this.field_179601_b = var2;
      super();
   }

   public boolean apply(Entity var1) {
      return var1.z_().equals(this.field_179602_a) != this.field_179601_b;
   }
}
