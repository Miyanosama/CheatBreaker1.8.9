package net.minecraft.world;

import com.cheatbreaker.client.module.type.HypixelModule;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import recovered.unidentified.UnidentifiedClass0499;
import recovered.unidentified.UnidentifiedClass1104;

public class WorldProviderSurface extends WorldProvider {
   public S39PacketPlayerAbilities field_0003;
   public UnidentifiedClass0499 field_0002;
   public BlockPlanks field_0004;
   public BlockTripWireHook field_0005;
   public UnidentifiedClass1104 field_0000;
   public HypixelModule field_0001;

   @Override
   public String getDimensionName() {
      return "Overworld";
   }

   @Override
   public String getInternalNameSuffix() {
      return "";
   }
}
