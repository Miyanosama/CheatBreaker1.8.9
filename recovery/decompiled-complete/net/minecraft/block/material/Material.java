package net.minecraft.block.material;

import net.minecraft.client.renderer.entity.RenderMagmaCube;
import net.minecraft.command.CommandCompare;
import net.minecraft.enchantment.EnchantmentDamage;

public class Material {
   public static Material cloth = new Material(MapColor.clothColor).setBurning();
   public static Material clay = new Material(MapColor.clayColor);
   public static Material iron = new Material(MapColor.ironColor).setRequiresTool();
   public static Material fire = new MaterialTransparent(MapColor.airColor).setNoPushMobility();
   public MapColor materialMapColor;
   public static Material carpet = new MaterialLogic(MapColor.clothColor).setBurning();
   public boolean field_0041;
   public CommandCompare field_0033;
   public static Material lava = new MaterialLiquid(MapColor.tntColor).setNoPushMobility();
   public static Material redstoneLight = new Material(MapColor.airColor).setAdventureModeExempt();
   public static Material cactus = new Material(MapColor.foliageColor).setTranslucent().setNoPushMobility();
   public static Material leaves = new Material(MapColor.foliageColor).setBurning().setTranslucent().setNoPushMobility();
   public static Material packedIce = new Material(MapColor.iceColor).setAdventureModeExempt();
   public static Material cake = new Material(MapColor.airColor).setNoPushMobility();
   public static Material glass = new Material(MapColor.airColor).setTranslucent().setAdventureModeExempt();
   public static Material wood = new Material(MapColor.woodColor).setBurning();
   public static Material portal = new MaterialPortal(MapColor.airColor).setImmovableMobility();
   public static Material tnt = new Material(MapColor.tntColor).setBurning().setTranslucent();
   public static Material gourd = new Material(MapColor.foliageColor).setNoPushMobility();
   public static Material vine = new MaterialLogic(MapColor.foliageColor).setBurning().setNoPushMobility().i();
   public static Material grass = new Material(MapColor.grassColor);
   public static Material plants = new MaterialLogic(MapColor.foliageColor).setNoPushMobility();
   public static Material web = new Material$1(MapColor.clothColor).setRequiresTool().setNoPushMobility();
   public static Material anvil = new Material(MapColor.ironColor).setRequiresTool().setImmovableMobility();
   public boolean field_0036;
   public static Material air = new MaterialTransparent(MapColor.airColor);
   public static Material piston = new Material(MapColor.stoneColor).setImmovableMobility();
   public static Material ice = new Material(MapColor.iceColor).setTranslucent().setAdventureModeExempt();
   public static Material water = new MaterialLiquid(MapColor.waterColor).setNoPushMobility();
   public static Material craftedSnow = new Material(MapColor.snowColor).setRequiresTool();
   public static Material dragonEgg = new Material(MapColor.foliageColor).setNoPushMobility();
   public static Material sponge = new Material(MapColor.yellowColor);
   public boolean field_0020;
   public static Material ground = new Material(MapColor.dirtColor);
   public int mobilityFlag;
   public boolean requiresNoTool = true;
   public static Material circuits = new MaterialLogic(MapColor.airColor).setNoPushMobility();
   public static Material rock = new Material(MapColor.stoneColor).setRequiresTool();
   public RenderMagmaCube field_0027;
   public static Material barrier = new Material(MapColor.airColor).setRequiresTool().setImmovableMobility();
   public boolean field_0024;
   public EnchantmentDamage field_0019;
   public static Material sand = new Material(MapColor.sandColor);
   public static Material coral = new Material(MapColor.foliageColor).setNoPushMobility();
   public static Material snow = new MaterialLogic(MapColor.snowColor).i().setTranslucent().setRequiresTool().setNoPushMobility();

   public Material setNoPushMobility() {
      this.mobilityFlag = 1;
      return this;
   }

   public Material setImmovableMobility() {
      this.mobilityFlag = 2;
      return this;
   }

   public Material setTranslucent() {
      this.field_0041 = true;
      return this;
   }

   public boolean getCanBurn() {
      return this.field_0024;
   }

   public Material(MapColor var1) {
      this.materialMapColor = var1;
   }

   public boolean isLiquid() {
      return false;
   }

   public boolean isOpaque() {
      return this.field_0041 ? false : this.blocksMovement();
   }

   public boolean blocksLight() {
      return true;
   }

   public Material setBurning() {
      this.field_0024 = true;
      return this;
   }

   public int getMaterialMobility() {
      return this.mobilityFlag;
   }

   public Material i() {
      this.field_0020 = true;
      return this;
   }

   public Material setAdventureModeExempt() {
      this.field_0036 = true;
      return this;
   }

   public boolean isToolNotRequired() {
      return this.requiresNoTool;
   }

   public boolean isSolid() {
      return true;
   }

   public boolean isReplaceable() {
      return this.field_0020;
   }

   public MapColor getMaterialMapColor() {
      return this.materialMapColor;
   }

   public Material setRequiresTool() {
      this.requiresNoTool = false;
      return this;
   }

   public boolean blocksMovement() {
      return true;
   }
}
