package net.minecraft.world;

import io.netty.util.Recycler$WeakOrderQueue;
import net.minecraft.entity.monster.EntitySlime$AISlimeHop;
import net.minecraft.entity.player.PlayerCapabilities;

public enum WorldSettings$GameType {
   SURVIVAL(0, "survival"),
   CREATIVE(1, "creative"),
   ADVENTURE(2, "adventure"),
   NOT_SET(-1, ""),
   SPECTATOR(3, "spectator");
   public String name;
   public EntitySlime$AISlimeHop field_0006;
   public Recycler$WeakOrderQueue field_0001;
   // $VF: synthetic field
   public static WorldSettings$GameType[] $VALUES = new WorldSettings$GameType[]{NOT_SET, SURVIVAL, CREATIVE, ADVENTURE, WorldSettings$GameType.SPECTATOR};
   public int id;

   public boolean isCreative() {
      return this == CREATIVE;
   }

   public boolean isSurvivalOrAdventure() {
      return this == SURVIVAL || this == ADVENTURE;
   }

   public String getName() {
      return this.name;
   }

   public static WorldSettings$GameType getByID(int var0) {
      for (WorldSettings$GameType var4 : values()) {
         if (var4.id == var0) {
            return var4;
         }
      }

      return SURVIVAL;
   }

   public void configurePlayerCapabilities(PlayerCapabilities var1) {
      if (this == CREATIVE) {
         var1.allowFlying = true;
         var1.isCreativeMode = true;
         var1.disableDamage = true;
      } else if (this == SPECTATOR) {
         var1.allowFlying = true;
         var1.isCreativeMode = false;
         var1.disableDamage = true;
         var1.isFlying = true;
      } else {
         var1.allowFlying = false;
         var1.isCreativeMode = false;
         var1.disableDamage = false;
         var1.isFlying = false;
      }

      var1.allowEdit = !this.isAdventure();
   }

   public static WorldSettings$GameType getByName(String var0) {
      for (WorldSettings$GameType var4 : values()) {
         if (var4.name.equals(var0)) {
            return var4;
         }
      }

      return SURVIVAL;
   }

   public WorldSettings$GameType(int var3, String var4) {
      this.id = var3;
      this.name = var4;
   }

   public int getID() {
      return this.id;
   }

   public boolean isAdventure() {
      return this == ADVENTURE || this == SPECTATOR;
   }
}
