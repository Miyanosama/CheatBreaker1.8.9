package net.minecraft.block;

import net.minecraft.network.play.server.S0EPacketSpawnObject;
import org.json.CookieList;

public class Block$1 extends Block$SoundType {
   public S0EPacketSpawnObject field_0000;
   public CookieList field_0001;

   @Override
   public String getBreakSound() {
      return "dig.glass";
   }

   public Block$1(String var1, float var2, float var3) {
      super(var1, var2, var3);
   }

   @Override
   public String getPlaceSound() {
      return "step.stone";
   }
}
