package net.minecraft.block;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$EntryIterator;
import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.stats.StatBase$4;
import net.optifine.reflect.Reflector$1;

public class BlockHalfWoodSlab extends BlockWoodSlab {
   public Reflector$1 field_0002;
   public StatBase$4 field_0003;
   public Scoreboard field_0000;
   public LayerCreeperCharge field_0001;
   public ConcurrentHashMapV8$EntryIterator field_0004;

   @Override
   public boolean isDouble() {
      return false;
   }
}
