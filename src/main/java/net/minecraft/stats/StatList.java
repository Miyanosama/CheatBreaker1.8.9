package net.minecraft.stats;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ResourceLocation;

public class StatList {
   public static Map<String, StatBase> oneShotStats = Maps.newHashMap();
   public static List<StatBase> allStats = Lists.newArrayList();
   public static List<StatBase> generalStats = Lists.newArrayList();
   public static List<StatCrafting> itemStats = Lists.newArrayList();
   public static List<StatCrafting> objectMineStats = Lists.newArrayList();
   public static StatBase leaveGameStat = new StatBasic("stat.leaveGame", new ChatComponentTranslation("stat.leaveGame")).initIndependentStat().registerStat();
   public static StatBase minutesPlayedStat = new StatBasic("stat.playOneMinute", new ChatComponentTranslation("stat.playOneMinute"), StatBase.timeStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase timeSinceDeathStat = new StatBasic("stat.timeSinceDeath", new ChatComponentTranslation("stat.timeSinceDeath"), StatBase.timeStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceWalkedStat = new StatBasic("stat.walkOneCm", new ChatComponentTranslation("stat.walkOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceCrouchedStat = new StatBasic("stat.crouchOneCm", new ChatComponentTranslation("stat.crouchOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceSprintedStat = new StatBasic("stat.sprintOneCm", new ChatComponentTranslation("stat.sprintOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceSwumStat = new StatBasic("stat.swimOneCm", new ChatComponentTranslation("stat.swimOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceFallenStat = new StatBasic("stat.fallOneCm", new ChatComponentTranslation("stat.fallOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceClimbedStat = new StatBasic("stat.climbOneCm", new ChatComponentTranslation("stat.climbOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceFlownStat = new StatBasic("stat.flyOneCm", new ChatComponentTranslation("stat.flyOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceDoveStat = new StatBasic("stat.diveOneCm", new ChatComponentTranslation("stat.diveOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceByMinecartStat = new StatBasic(
         "stat.minecartOneCm", new ChatComponentTranslation("stat.minecartOneCm"), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceByBoatStat = new StatBasic("stat.boatOneCm", new ChatComponentTranslation("stat.boatOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceByPigStat = new StatBasic("stat.pigOneCm", new ChatComponentTranslation("stat.pigOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceByHorseStat = new StatBasic("stat.horseOneCm", new ChatComponentTranslation("stat.horseOneCm"), StatBase.distanceStatType)
      .initIndependentStat()
      .registerStat();
   public static StatBase jumpStat = new StatBasic("stat.jump", new ChatComponentTranslation("stat.jump")).initIndependentStat().registerStat();
   public static StatBase dropStat = new StatBasic("stat.drop", new ChatComponentTranslation("stat.drop")).initIndependentStat().registerStat();
   public static StatBase damageDealtStat = new StatBasic("stat.damageDealt", new ChatComponentTranslation("stat.damageDealt"), StatBase.field_111202_k)
      .registerStat();
   public static StatBase damageTakenStat = new StatBasic("stat.damageTaken", new ChatComponentTranslation("stat.damageTaken"), StatBase.field_111202_k)
      .registerStat();
   public static StatBase deathsStat = new StatBasic("stat.deaths", new ChatComponentTranslation("stat.deaths")).registerStat();
   public static StatBase mobKillsStat = new StatBasic("stat.mobKills", new ChatComponentTranslation("stat.mobKills")).registerStat();
   public static StatBase animalsBredStat = new StatBasic("stat.animalsBred", new ChatComponentTranslation("stat.animalsBred")).registerStat();
   public static StatBase playerKillsStat = new StatBasic("stat.playerKills", new ChatComponentTranslation("stat.playerKills")).registerStat();
   public static StatBase fishCaughtStat = new StatBasic("stat.fishCaught", new ChatComponentTranslation("stat.fishCaught")).registerStat();
   public static StatBase junkFishedStat = new StatBasic("stat.junkFished", new ChatComponentTranslation("stat.junkFished")).registerStat();
   public static StatBase treasureFishedStat = new StatBasic("stat.treasureFished", new ChatComponentTranslation("stat.treasureFished")).registerStat();
   public static StatBase timesTalkedToVillagerStat = new StatBasic("stat.talkedToVillager", new ChatComponentTranslation("stat.talkedToVillager"))
      .registerStat();
   public static StatBase timesTradedWithVillagerStat = new StatBasic("stat.tradedWithVillager", new ChatComponentTranslation("stat.tradedWithVillager"))
      .registerStat();
   public static StatBase field_181724_H = new StatBasic("stat.cakeSlicesEaten", new ChatComponentTranslation("stat.cakeSlicesEaten")).registerStat();
   public static StatBase field_181725_I = new StatBasic("stat.cauldronFilled", new ChatComponentTranslation("stat.cauldronFilled")).registerStat();
   public static StatBase field_181726_J = new StatBasic("stat.cauldronUsed", new ChatComponentTranslation("stat.cauldronUsed")).registerStat();
   public static StatBase field_181727_K = new StatBasic("stat.armorCleaned", new ChatComponentTranslation("stat.armorCleaned")).registerStat();
   public static StatBase field_181728_L = new StatBasic("stat.bannerCleaned", new ChatComponentTranslation("stat.bannerCleaned")).registerStat();
   public static StatBase field_181729_M = new StatBasic("stat.brewingstandInteraction", new ChatComponentTranslation("stat.brewingstandInteraction"))
      .registerStat();
   public static StatBase field_181730_N = new StatBasic("stat.beaconInteraction", new ChatComponentTranslation("stat.beaconInteraction")).registerStat();
   public static StatBase field_181731_O = new StatBasic("stat.dropperInspected", new ChatComponentTranslation("stat.dropperInspected")).registerStat();
   public static StatBase field_181732_P = new StatBasic("stat.hopperInspected", new ChatComponentTranslation("stat.hopperInspected")).registerStat();
   public static StatBase field_181733_Q = new StatBasic("stat.dispenserInspected", new ChatComponentTranslation("stat.dispenserInspected")).registerStat();
   public static StatBase field_181734_R = new StatBasic("stat.noteblockPlayed", new ChatComponentTranslation("stat.noteblockPlayed")).registerStat();
   public static StatBase field_181735_S = new StatBasic("stat.noteblockTuned", new ChatComponentTranslation("stat.noteblockTuned")).registerStat();
   public static StatBase field_181736_T = new StatBasic("stat.flowerPotted", new ChatComponentTranslation("stat.flowerPotted")).registerStat();
   public static StatBase field_181737_U = new StatBasic("stat.trappedChestTriggered", new ChatComponentTranslation("stat.trappedChestTriggered"))
      .registerStat();
   public static StatBase field_181738_V = new StatBasic("stat.enderchestOpened", new ChatComponentTranslation("stat.enderchestOpened")).registerStat();
   public static StatBase field_181739_W = new StatBasic("stat.itemEnchanted", new ChatComponentTranslation("stat.itemEnchanted")).registerStat();
   public static StatBase field_181740_X = new StatBasic("stat.recordPlayed", new ChatComponentTranslation("stat.recordPlayed")).registerStat();
   public static StatBase field_181741_Y = new StatBasic("stat.furnaceInteraction", new ChatComponentTranslation("stat.furnaceInteraction")).registerStat();
   public static StatBase field_181742_Z = new StatBasic("stat.craftingTableInteraction", new ChatComponentTranslation("stat.workbenchInteraction"))
      .registerStat();
   public static StatBase field_181723_aa = new StatBasic("stat.chestOpened", new ChatComponentTranslation("stat.chestOpened")).registerStat();
   public static StatBase[] mineBlockStatArray = new StatBase[4096];
   public static StatBase[] objectCraftStats = new StatBase[32000];
   public static StatBase[] objectUseStats = new StatBase[32000];
   public static StatBase[] objectBreakStats = new StatBase[32000];

   public static void mergeStatBases(StatBase[] var0, Block var1, Block var2) {
      int var3 = Block.getIdFromBlock(var1);
      int var4 = Block.getIdFromBlock(var2);
      if (var0[var3] != null && var0[var4] == null) {
         var0[var4] = var0[var3];
      } else {
         allStats.remove(var0[var3]);
         objectMineStats.remove(var0[var3]);
         generalStats.remove(var0[var3]);
         var0[var3] = var0[var4];
      }
   }

   public static void initCraftableStats() {
      HashSet var0 = Sets.newHashSet();

      for (IRecipe var2 : CraftingManager.getInstance().getRecipeList()) {
         if (var2.getRecipeOutput() != null) {
            var0.add(var2.getRecipeOutput().getItem());
         }
      }

      for (ItemStack var7 : FurnaceRecipes.instance().getSmeltingList().values()) {
         var0.add(var7.getItem());
      }

      for (Item var8 : (Iterable<Item>)(Iterable<?>)(var0)) {
         if (var8 != null) {
            int var3 = Item.getIdFromItem(var8);
            String var4 = func_180204_a(var8);
            if (var4 != null) {
               objectCraftStats[var3] = new StatCrafting(
                     "stat.craftItem.", var4, new ChatComponentTranslation("stat.craftItem", new ItemStack(var8).getChatComponent()), var8
                  )
                  .registerStat();
            }
         }
      }

      replaceAllSimilarBlocks(objectCraftStats);
   }

   public static StatBase getStatEntityKilledBy(EntityList.EntityEggInfo var0) {
      String var1 = EntityList.getStringFromID(var0.spawnedID);
      return var1 == null
         ? null
         : new StatBase(
               "stat.entityKilledBy." + var1, new ChatComponentTranslation("stat.entityKilledBy", new ChatComponentTranslation("entity." + var1 + ".name"))
            )
            .registerStat();
   }

   public static String func_180204_a(Item var0) {
      ResourceLocation var1 = Item.itemRegistry.getNameForObject(var0);
      return var1 != null ? var1.toString().replace(':', '.') : null;
   }

   public static void replaceAllSimilarBlocks(StatBase[] var0) {
      mergeStatBases(var0, Blocks.water, Blocks.flowing_water);
      mergeStatBases(var0, Blocks.lava, Blocks.flowing_lava);
      mergeStatBases(var0, Blocks.lit_pumpkin, Blocks.pumpkin);
      mergeStatBases(var0, Blocks.lit_furnace, Blocks.furnace);
      mergeStatBases(var0, Blocks.lit_redstone_ore, Blocks.redstone_ore);
      mergeStatBases(var0, Blocks.powered_repeater, Blocks.unpowered_repeater);
      mergeStatBases(var0, Blocks.powered_comparator, Blocks.unpowered_comparator);
      mergeStatBases(var0, Blocks.redstone_torch, Blocks.unlit_redstone_torch);
      mergeStatBases(var0, Blocks.lit_redstone_lamp, Blocks.redstone_lamp);
      mergeStatBases(var0, Blocks.double_stone_slab, Blocks.stone_slab);
      mergeStatBases(var0, Blocks.double_wooden_slab, Blocks.wooden_slab);
      mergeStatBases(var0, Blocks.double_stone_slab2, Blocks.stone_slab2);
      mergeStatBases(var0, Blocks.grass, Blocks.dirt);
      mergeStatBases(var0, Blocks.farmland, Blocks.dirt);
   }

   public static StatBase getOneShotStat(String var0) {
      return oneShotStats.get(var0);
   }

   public static void init() {
      initMiningStats();
      initStats();
      initItemDepleteStats();
      initCraftableStats();
      AchievementList.init();
      EntityList.func_151514_a();
   }

   public static void initItemDepleteStats() {
      for (Item var1 : Item.itemRegistry) {
         if (var1 != null) {
            int var2 = Item.getIdFromItem(var1);
            String var3 = func_180204_a(var1);
            if (var3 != null && var1.isDamageable()) {
               objectBreakStats[var2] = new StatCrafting(
                     "stat.breakItem.", var3, new ChatComponentTranslation("stat.breakItem", new ItemStack(var1).getChatComponent()), var1
                  )
                  .registerStat();
            }
         }
      }

      replaceAllSimilarBlocks(objectBreakStats);
   }

   public static StatBase getStatKillEntity(EntityList.EntityEggInfo var0) {
      String var1 = EntityList.getStringFromID(var0.spawnedID);
      return var1 == null
         ? null
         : new StatBase("stat.killEntity." + var1, new ChatComponentTranslation("stat.entityKill", new ChatComponentTranslation("entity." + var1 + ".name")))
            .registerStat();
   }

   public static void initStats() {
      for (Item var1 : Item.itemRegistry) {
         if (var1 != null) {
            int var2 = Item.getIdFromItem(var1);
            String var3 = func_180204_a(var1);
            if (var3 != null) {
               objectUseStats[var2] = new StatCrafting(
                     "stat.useItem.", var3, new ChatComponentTranslation("stat.useItem", new ItemStack(var1).getChatComponent()), var1
                  )
                  .registerStat();
               if (!(var1 instanceof ItemBlock)) {
                  itemStats.add((StatCrafting)objectUseStats[var2]);
               }
            }
         }
      }

      replaceAllSimilarBlocks(objectUseStats);
   }

   public static void initMiningStats() {
      for (Block var1 : Block.blockRegistry) {
         Item var2 = Item.getItemFromBlock(var1);
         if (var2 != null) {
            int var3 = Block.getIdFromBlock(var1);
            String var4 = func_180204_a(var2);
            if (var4 != null && var1.getEnableStats()) {
               mineBlockStatArray[var3] = new StatCrafting(
                     "stat.mineBlock.", var4, new ChatComponentTranslation("stat.mineBlock", new ItemStack(var1).getChatComponent()), var2
                  )
                  .registerStat();
               objectMineStats.add((StatCrafting)mineBlockStatArray[var3]);
            }
         }
      }

      replaceAllSimilarBlocks(mineBlockStatArray);
   }
}
