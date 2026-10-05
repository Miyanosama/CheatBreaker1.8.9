package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import io.netty.util.internal.logging.Slf4JLoggerFactory$1;
import java.util.List;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.optifine.shaders.CustomTexture;

public class MapGenNetherBridge extends MapGenStructure {
   public CustomTexture field_0002;
   public List<BiomeGenBase$SpawnListEntry> spawnList = Lists.newArrayList();
   public GuiSlot field_0000;
   public Slf4JLoggerFactory$1 field_0003;

   public MapGenNetherBridge() {
      this.spawnList.add(new BiomeGenBase$SpawnListEntry(EntityBlaze.class, 10, 2, 3));
      this.spawnList.add(new BiomeGenBase$SpawnListEntry(EntityPigZombie.class, 5, 4, 4));
      this.spawnList.add(new BiomeGenBase$SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
      this.spawnList.add(new BiomeGenBase$SpawnListEntry(EntityMagmaCube.class, 3, 4, 4));
   }

   @Override
   public String getStructureName() {
      return "Fortress";
   }

   public List<BiomeGenBase$SpawnListEntry> getSpawnList() {
      return this.spawnList;
   }

   @Override
   public StructureStart getStructureStart(int var1, int var2) {
      return new MapGenNetherBridge$Start(this.c, this.b, var1, var2);
   }

   @Override
   public boolean canSpawnStructureAtCoords(int var1, int var2) {
      int var3 = var1 >> 4;
      int var4 = var2 >> 4;
      this.b.setSeed(var3 ^ var4 << 4 ^ this.c.J());
      this.b.nextInt();
      return this.b.nextInt(3) != 0 ? false : (var1 != (var3 << 4) + 4 + this.b.nextInt(8) ? false : var2 == (var4 << 4) + 4 + this.b.nextInt(8));
   }
}
