package net.minecraft.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Map;
import javazoom.jl.decoder.Obuffer;
import net.minecraft.network.NetHandlerPlayServer;

public enum EnumParticleTypes {
   DRIP_WATER("dripWater", 18, false),
   SNOWBALL("snowballpoof", 31, false),
   WATER_DROP("droplet", 39, false),
   WATER_WAKE("wake", 6, false),
   MOB_APPEARANCE("mobappearance", 41, true),
   EXPLOSION_HUGE("hugeexplosion", 2, true),
   SLIME("slime", 33, false),
   SUSPENDED("suspended", 7, false),
   BLOCK_DUST("blockdust_", 38, false, 1),
   FIREWORKS_SPARK("fireworksSpark", 3, false),
   BARRIER("barrier", 35, false),
   FLAME("flame", 26, false),
   SUSPENDED_DEPTH("depthsuspend", 8, false),
   SMOKE_LARGE("largesmoke", 12, false),
   ENCHANTMENT_TABLE("enchantmenttable", 25, false),
   NOTE("note", 23, false),
   TOWN_AURA("townaura", 22, false),
   SPELL("spell", 13, false),
   REDSTONE("reddust", 30, false),
   PORTAL("portal", 24, false),
   LAVA("lava", 27, false),
   field_0032("take", 40, false),
   WATER_BUBBLE("bubble", 4, false),
   VILLAGER_HAPPY("happyVillager", 21, false),
   SPELL_INSTANT("instantSpell", 14, false),
   VILLAGER_ANGRY("angryVillager", 20, false),
   BLOCK_CRACK("blockcrack_", 37, false, 1),
   SPELL_WITCH("witchMagic", 17, false),
   SMOKE_NORMAL("smoke", 11, false),
   CRIT_MAGIC("magicCrit", 10, false),
   CLOUD("cloud", 29, false),
   HEART("heart", 34, false),
   WATER_SPLASH("splash", 5, false),
   CRIT("crit", 9, false),
   SPELL_MOB_AMBIENT("mobSpellAmbient", 16, false),
   SPELL_MOB("mobSpell", 15, false),
   SNOW_SHOVEL("snowshovel", 32, false),
   DRIP_LAVA("dripLava", 19, false),
   EXPLOSION_NORMAL("explode", 0, true),
   FOOTSTEP("footstep", 28, false),
   EXPLOSION_LARGE("largeexplode", 1, true),
   ITEM_CRACK("iconcrack_", 36, false, 2);
   // $VF: synthetic field
   public static EnumParticleTypes[] field_0047 = new EnumParticleTypes[]{
      EnumParticleTypes.EXPLOSION_NORMAL,
      EnumParticleTypes.EXPLOSION_LARGE,
      EXPLOSION_HUGE,
      EnumParticleTypes.FIREWORKS_SPARK,
      EnumParticleTypes.WATER_BUBBLE,
      EnumParticleTypes.WATER_SPLASH,
      WATER_WAKE,
      EnumParticleTypes.SUSPENDED,
      EnumParticleTypes.SUSPENDED_DEPTH,
      EnumParticleTypes.CRIT,
      EnumParticleTypes.CRIT_MAGIC,
      EnumParticleTypes.SMOKE_NORMAL,
      EnumParticleTypes.SMOKE_LARGE,
      EnumParticleTypes.SPELL,
      EnumParticleTypes.SPELL_INSTANT,
      EnumParticleTypes.SPELL_MOB,
      EnumParticleTypes.SPELL_MOB_AMBIENT,
      EnumParticleTypes.SPELL_WITCH,
      DRIP_WATER,
      EnumParticleTypes.DRIP_LAVA,
      EnumParticleTypes.VILLAGER_ANGRY,
      EnumParticleTypes.VILLAGER_HAPPY,
      EnumParticleTypes.TOWN_AURA,
      EnumParticleTypes.NOTE,
      EnumParticleTypes.PORTAL,
      EnumParticleTypes.ENCHANTMENT_TABLE,
      EnumParticleTypes.FLAME,
      EnumParticleTypes.LAVA,
      EnumParticleTypes.FOOTSTEP,
      EnumParticleTypes.CLOUD,
      EnumParticleTypes.REDSTONE,
      SNOWBALL,
      EnumParticleTypes.SNOW_SHOVEL,
      EnumParticleTypes.SLIME,
      EnumParticleTypes.HEART,
      EnumParticleTypes.BARRIER,
      EnumParticleTypes.ITEM_CRACK,
      EnumParticleTypes.BLOCK_CRACK,
      EnumParticleTypes.BLOCK_DUST,
      WATER_DROP,
      EnumParticleTypes.field_0032,
      MOB_APPEARANCE
   };
   public NetHandlerPlayServer field_0038;
   public static Map<Integer, EnumParticleTypes> PARTICLES = Maps.newHashMap();
   public static String[] PARTICLE_NAMES;
   public int argumentCount;
   public int particleID;
   public String particleName;
   public Obuffer field_0033;
   public boolean shouldIgnoreRange;

   static {
      ArrayList var0 = Lists.newArrayList();

      for (EnumParticleTypes var4 : values()) {
         PARTICLES.put(var4.getParticleID(), var4);
         if (!var4.getParticleName().endsWith("_")) {
            var0.add(var4.getParticleName());
         }
      }

      PARTICLE_NAMES = var0.toArray(new String[var0.size()]);
   }

   public EnumParticleTypes(String var3, int var4, boolean var5) {
      this(var3, var4, var5, 0);
   }

   public int getParticleID() {
      return this.particleID;
   }

   public boolean getShouldIgnoreRange() {
      return this.shouldIgnoreRange;
   }

   public static EnumParticleTypes getParticleFromId(int var0) {
      return PARTICLES.get(var0);
   }

   public int getArgumentCount() {
      return this.argumentCount;
   }

   public static String[] getParticleNames() {
      return PARTICLE_NAMES;
   }

   public String getParticleName() {
      return this.particleName;
   }

   public EnumParticleTypes(String var3, int var4, boolean var5, int var6) {
      this.particleName = var3;
      this.particleID = var4;
      this.shouldIgnoreRange = var5;
      this.argumentCount = var6;
   }

   public boolean hasArguments() {
      return this.argumentCount > 0;
   }
}
