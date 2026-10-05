package net.minecraft.block;

import net.minecraft.item.ItemSeedFood;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;

public class Block$3 extends Block$SoundType {
   public S3DPacketDisplayScoreboard field_0000;
   public ItemSeedFood field_0001;

   @Override
   public String getPlaceSound() {
      return "random.anvil_land";
   }

   @Override
   public String getBreakSound() {
      return "dig.stone";
   }

   public Block$3(String var1, float var2, float var3) {
      super(var1, var2, var3);
   }
}
