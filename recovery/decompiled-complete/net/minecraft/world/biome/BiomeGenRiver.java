package net.minecraft.world.biome;

import net.minecraft.client.particle.EntityFirework$Factory;
import net.minecraft.inventory.InventoryBasic;
import org.json.HTTPTokener;
import org.json.JSONException;

public class BiomeGenRiver extends BiomeGenBase {
   public InventoryBasic field_0000;
   public JSONException field_0003;
   public HTTPTokener field_0002;
   public EntityFirework$Factory field_0001;

   public BiomeGenRiver(int var1) {
      super(var1);
      this.au.clear();
   }
}
