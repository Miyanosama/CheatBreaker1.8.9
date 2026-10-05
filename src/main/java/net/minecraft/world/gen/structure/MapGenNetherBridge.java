package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenNetherBridge extends MapGenStructure {
   public List<BiomeGenBase.SpawnListEntry> spawnList = Lists.newArrayList();

   public MapGenNetherBridge() {
      this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntityBlaze.class, 10, 2, 3));
      this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 5, 4, 4));
      this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
      this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 3, 4, 4));
   }

   @Override
   public String getStructureName() {
      return "Fortress";
   }

   public List<BiomeGenBase.SpawnListEntry> getSpawnList() {
      return this.spawnList;
   }

   @Override
   public StructureStart getStructureStart(int var1, int var2) {
      return new MapGenNetherBridge.Start(this.c, this.b, var1, var2);
   }

   @Override
   public boolean canSpawnStructureAtCoords(int var1, int var2) {
      int var3 = var1 >> 4;
      int var4 = var2 >> 4;
      this.b.setSeed(var3 ^ var4 << 4 ^ this.c.J());
      this.b.nextInt();
      return this.b.nextInt(3) != 0 ? false : (var1 != (var3 << 4) + 4 + this.b.nextInt(8) ? false : var2 == (var4 << 4) + 4 + this.b.nextInt(8));
   }

   public static class Start extends StructureStart {
      public Start() {
      }

      public Start(World var1, Random var2, int var3, int var4) {
         super(var3, var4);
         StructureNetherBridgePieces.Start var5 = new StructureNetherBridgePieces.Start(var2, (var3 << 4) + 2, (var4 << 4) + 2);
         this.a.add(var5);
         var5.buildComponent(var5, this.a, var2);
         List var6 = var5.field_74967_d;

         while (!var6.isEmpty()) {
            int var7 = var2.nextInt(var6.size());
            StructureComponent var8 = (StructureComponent)var6.remove(var7);
            var8.buildComponent(var5, this.a, var2);
         }

         this.c();
         this.setRandomHeight(var1, var2, 48, 70);
      }
   }
}
