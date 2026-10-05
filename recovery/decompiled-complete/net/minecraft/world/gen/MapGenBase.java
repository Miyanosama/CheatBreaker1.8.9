package net.minecraft.world.gen;

import com.cheatbreaker.client.module.ModuleRule;
import java.util.Random;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.network.play.server.S38PacketPlayerListItem$AddPlayerData;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class MapGenBase {
   public World c;
   public S38PacketPlayerListItem$AddPlayerData field_0005;
   public Random b;
   public ModuleRule field_0004;
   public EnumEnchantmentType field_0000;
   public int range = 8;

   public MapGenBase() {
      this.b = new Random();
   }

   public void recursiveGenerate(World var1, int var2, int var3, int var4, int var5, ChunkPrimer var6) {
   }

   public void generate(IChunkProvider var1, World var2, int var3, int var4, ChunkPrimer var5) {
      int var6 = this.range;
      this.c = var2;
      this.b.setSeed(var2.J());
      long var7 = this.b.nextLong();
      long var9 = this.b.nextLong();

      for (int var11 = var3 - var6; var11 <= var3 + var6; var11++) {
         for (int var12 = var4 - var6; var12 <= var4 + var6; var12++) {
            long var13 = var11 * var7;
            long var15 = var12 * var9;
            this.b.setSeed(var13 ^ var15 ^ var2.J());
            this.recursiveGenerate(var2, var11, var12, var3, var4, var5);
         }
      }
   }
}
