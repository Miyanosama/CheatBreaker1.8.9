package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.block.NetherBrickMapColorBlock;
import net.minecraft.block.HardenedClayMapColorBlock;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockYellowFlower;
import net.minecraft.block.NetherrackMapColorBlock;
import net.minecraft.block.BlockRedFlower;
import net.minecraft.block.BlockPotato;

public class Block {
   public boolean needsRandomTick;
   public static ResourceLocation AIR_ID = new ResourceLocation("air");
   public static RegistryNamespacedDefaultedByKey<ResourceLocation, Block> blockRegistry = new RegistryNamespacedDefaultedByKey<>(AIR_ID);
   public boolean translucent;
   public BlockState M;
   public int lightValue;
   public IBlockState defaultBlockState;
   public float blockHardness;
   public double E;
   public static ObjectIntIdentityMap<IBlockState> BLOCK_STATE_IDS = new ObjectIntIdentityMap<>();
   public static Block.SoundType soundTypeStone = new Block.SoundType("stone", 1.0F, 1.0F);
   public static Block.SoundType f = new Block.SoundType("wood", 1.0F, 1.0F);
   public boolean fullBlock;
   public double C;
   public boolean useNeighborBrightness;
   public static Block.SoundType soundTypeGravel = new Block.SoundType("gravel", 1.0F, 1.0F);
   public Material J;
   public Block.SoundType stepSound;
   public static Block.SoundType h = new Block.SoundType("grass", 1.0F, 1.0F);
   public static Block.SoundType i = new Block.SoundType("stone", 1.0F, 1.0F);
   public static Block.SoundType soundTypeMetal = new Block.SoundType("stone", 1.0F, 1.5F);
   public float blockParticleGravity;
   public static Block.SoundType soundTypeGlass = new Block.SoundType("stone", 1.0F, 1.0F) {
      @Override
      public String getBreakSound() {
         return "dig.glass";
      }

      @Override
      public String getPlaceSound() {
         return "step.stone";
      }
   };
   public String unlocalizedName;
   public double B;
   public double D;
   public static Block.SoundType soundTypeCloth = new Block.SoundType("cloth", 1.0F, 1.0F);
   public static Block.SoundType soundTypeSand = new Block.SoundType("sand", 1.0F, 1.0F);
   public MapColor blockMapColor;
   public float blockResistance;
   public static Block.SoundType soundTypeSnow = new Block.SoundType("snow", 1.0F, 1.0F);
   public double G;
   public float L;
   public static Block.SoundType soundTypeLadder = new Block.SoundType("ladder", 1.0F, 1.0F) {
      @Override
      public String getBreakSound() {
         return "dig.wood";
      }
   };
   public boolean A;
   public boolean enableStats = true;
   public static Block.SoundType soundTypeAnvil = new Block.SoundType("anvil", 0.3F, 1.0F) {
      @Override
      public String getPlaceSound() {
         return "random.anvil_land";
      }

      @Override
      public String getBreakSound() {
         return "dig.stone";
      }
   };
   public double F;
   public static Block.SoundType SLIME_SOUND = new Block.SoundType("slime", 1.0F, 1.0F) {
      @Override
      public String getBreakSound() {
         return "mob.slime.big";
      }

      @Override
      public String getStepSound() {
         return "mob.slime.small";
      }

      @Override
      public String getPlaceSound() {
         return "mob.slime.big";
      }
   };
   public int lightOpacity;
   public CreativeTabs displayOnCreativeTab;

   public int getMetaFromState(IBlockState var1) {
      if (var1 != null && !var1.getPropertyNames().isEmpty()) {
         throw new IllegalArgumentException("Don't know how to convert " + var1 + " back into data...");
      } else {
         return 0;
      }
   }

   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
   }

   public Block.EnumOffsetType getOffsetType() {
      return Block.EnumOffsetType.NONE;
   }

   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return var1.getBlockState(var2).getBlock().J.isReplaceable();
   }

   public Block setTickRandomly(boolean var1) {
      this.needsRandomTick = var1;
      return this;
   }

   public boolean isVecInsideXZBounds(Vec3 var1) {
      return var1 == null ? false : var1.xCoord >= this.B && var1.xCoord <= this.E && var1.zCoord >= this.D && var1.zCoord <= this.G;
   }

   public boolean canSilkHarvest() {
      return this.isFullCube() && !this.A;
   }

   public Block setBlockUnbreakable() {
      this.setHardness(-1.0F);
      return this;
   }

   public static void registerBlocks() {
      registerBlock(0, AIR_ID, new BlockAir().setUnlocalizedName("air"));
      registerBlock(1, "stone", new BlockStone().setHardness(1.5F).setResistance(10.0F).setStepSound(i).setUnlocalizedName("stone"));
      registerBlock(2, "grass", new BlockGrass().setHardness(0.6F).setStepSound(h).setUnlocalizedName("grass"));
      registerBlock(3, "dirt", new BlockDirt().setHardness(0.5F).setStepSound(soundTypeGravel).setUnlocalizedName("dirt"));
      Block var0 = new Block(Material.rock)
         .setHardness(2.0F)
         .setResistance(10.0F)
         .setStepSound(i)
         .setUnlocalizedName("stonebrick")
         .setCreativeTab(CreativeTabs.tabBlock);
      registerBlock(4, "cobblestone", var0);
      Block var1 = new BlockPlanks().setHardness(2.0F).setResistance(5.0F).setStepSound(f).setUnlocalizedName("wood");
      registerBlock(5, "planks", var1);
      registerBlock(6, "sapling", new BlockSapling().setHardness(0.0F).setStepSound(h).setUnlocalizedName("sapling"));
      registerBlock(
         7,
         "bedrock",
         new Block(Material.rock)
            .setBlockUnbreakable()
            .setResistance(6000000.0F)
            .setStepSound(i)
            .setUnlocalizedName("bedrock")
            .disableStats()
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(
         8, "flowing_water", new BlockDynamicLiquid(Material.water).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats()
      );
      registerBlock(9, "water", new BlockStaticLiquid(Material.water).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats());
      registerBlock(10, "flowing_lava", new BlockDynamicLiquid(Material.lava).setHardness(100.0F).setLightLevel(1.0F).setUnlocalizedName("lava").disableStats());
      registerBlock(11, "lava", new BlockStaticLiquid(Material.lava).setHardness(100.0F).setLightLevel(1.0F).setUnlocalizedName("lava").disableStats());
      registerBlock(12, "sand", new BlockSand().setHardness(0.5F).setStepSound(soundTypeSand).setUnlocalizedName("sand"));
      registerBlock(13, "gravel", new BlockGravel().setHardness(0.6F).setStepSound(soundTypeGravel).setUnlocalizedName("gravel"));
      registerBlock(14, "gold_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(i).setUnlocalizedName("oreGold"));
      registerBlock(15, "iron_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(i).setUnlocalizedName("oreIron"));
      registerBlock(16, "coal_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(i).setUnlocalizedName("oreCoal"));
      registerBlock(17, "log", new BlockOldLog().setUnlocalizedName("log"));
      registerBlock(18, "leaves", new BlockOldLeaf().setUnlocalizedName("leaves"));
      registerBlock(19, "sponge", new BlockSponge().setHardness(0.6F).setStepSound(h).setUnlocalizedName("sponge"));
      registerBlock(20, "glass", new BlockGlass(Material.glass, false).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("glass"));
      registerBlock(21, "lapis_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(i).setUnlocalizedName("oreLapis"));
      registerBlock(
         22,
         "lapis_block",
         new Block(Material.iron, MapColor.lapisColor)
            .setHardness(3.0F)
            .setResistance(5.0F)
            .setStepSound(i)
            .setUnlocalizedName("blockLapis")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(23, "dispenser", new BlockDispenser().setHardness(3.5F).setStepSound(i).setUnlocalizedName("dispenser"));
      Block var2 = new BlockSandStone().setStepSound(i).setHardness(0.8F).setUnlocalizedName("sandStone");
      registerBlock(24, "sandstone", var2);
      registerBlock(25, "noteblock", new BlockNote().setHardness(0.8F).setUnlocalizedName("musicBlock"));
      registerBlock(26, "bed", new BlockBed().setStepSound(f).setHardness(0.2F).setUnlocalizedName("bed").disableStats());
      registerBlock(27, "golden_rail", new BlockRailPowered().setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("goldenRail"));
      registerBlock(28, "detector_rail", new BlockRailDetector().setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("detectorRail"));
      registerBlock(29, "sticky_piston", new BlockPistonBase(true).setUnlocalizedName("pistonStickyBase"));
      registerBlock(30, "web", new BlockWeb().setLightOpacity(1).setHardness(4.0F).setUnlocalizedName("web"));
      registerBlock(31, "tallgrass", new BlockTallGrass().setHardness(0.0F).setStepSound(h).setUnlocalizedName("tallgrass"));
      registerBlock(32, "deadbush", new BlockDeadBush().setHardness(0.0F).setStepSound(h).setUnlocalizedName("deadbush"));
      registerBlock(33, "piston", new BlockPistonBase(false).setUnlocalizedName("pistonBase"));
      registerBlock(34, "piston_head", new BlockPistonExtension().setUnlocalizedName("pistonBase"));
      registerBlock(35, "wool", new BlockColored(Material.cloth).setHardness(0.8F).setStepSound(soundTypeCloth).setUnlocalizedName("cloth"));
      registerBlock(36, "piston_extension", new BlockPistonMoving());
      registerBlock(37, "yellow_flower", new BlockYellowFlower().setHardness(0.0F).setStepSound(h).setUnlocalizedName("flower1"));
      registerBlock(38, "red_flower", new BlockRedFlower().setHardness(0.0F).setStepSound(h).setUnlocalizedName("flower2"));
      Block var3 = new BlockMushroom().setHardness(0.0F).setStepSound(h).setLightLevel(0.125F).setUnlocalizedName("mushroom");
      registerBlock(39, "brown_mushroom", var3);
      Block var4 = new BlockMushroom().setHardness(0.0F).setStepSound(h).setUnlocalizedName("mushroom");
      registerBlock(40, "red_mushroom", var4);
      registerBlock(
         41,
         "gold_block",
         new Block(Material.iron, MapColor.goldColor)
            .setHardness(3.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypeMetal)
            .setUnlocalizedName("blockGold")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(
         42,
         "iron_block",
         new Block(Material.iron, MapColor.ironColor)
            .setHardness(5.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypeMetal)
            .setUnlocalizedName("blockIron")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(43, "double_stone_slab", new BlockDoubleStoneSlab().setHardness(2.0F).setResistance(10.0F).setStepSound(i).setUnlocalizedName("stoneSlab"));
      registerBlock(44, "stone_slab", new BlockHalfStoneSlab().setHardness(2.0F).setResistance(10.0F).setStepSound(i).setUnlocalizedName("stoneSlab"));
      Block var5 = new Block(Material.rock, MapColor.redColor)
         .setHardness(2.0F)
         .setResistance(10.0F)
         .setStepSound(i)
         .setUnlocalizedName("brick")
         .setCreativeTab(CreativeTabs.tabBlock);
      registerBlock(45, "brick_block", var5);
      registerBlock(46, "tnt", new BlockTNT().setHardness(0.0F).setStepSound(h).setUnlocalizedName("tnt"));
      registerBlock(47, "bookshelf", new BlockBookshelf().setHardness(1.5F).setStepSound(f).setUnlocalizedName("bookshelf"));
      registerBlock(
         48,
         "mossy_cobblestone",
         new Block(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(i).setUnlocalizedName("stoneMoss").setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(49, "obsidian", new BlockObsidian().setHardness(50.0F).setResistance(2000.0F).setStepSound(i).setUnlocalizedName("obsidian"));
      registerBlock(50, "torch", new BlockTorch().setHardness(0.0F).setLightLevel(0.9375F).setStepSound(f).setUnlocalizedName("torch"));
      registerBlock(51, "fire", new BlockFire().setHardness(0.0F).setLightLevel(1.0F).setStepSound(soundTypeCloth).setUnlocalizedName("fire").disableStats());
      registerBlock(52, "mob_spawner", new BlockMobSpawner().setHardness(5.0F).setStepSound(soundTypeMetal).setUnlocalizedName("mobSpawner").disableStats());
      registerBlock(
         53, "oak_stairs", new BlockStairs(var1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK)).setUnlocalizedName("stairsWood")
      );
      registerBlock(54, "chest", new BlockChest(0).setHardness(2.5F).setStepSound(f).setUnlocalizedName("chest"));
      registerBlock(
         55, "redstone_wire", new BlockRedstoneWire().setHardness(0.0F).setStepSound(soundTypeStone).setUnlocalizedName("redstoneDust").disableStats()
      );
      registerBlock(56, "diamond_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(i).setUnlocalizedName("oreDiamond"));
      registerBlock(
         57,
         "diamond_block",
         new Block(Material.iron, MapColor.diamondColor)
            .setHardness(5.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypeMetal)
            .setUnlocalizedName("blockDiamond")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(58, "crafting_table", new BlockWorkbench().setHardness(2.5F).setStepSound(f).setUnlocalizedName("workbench"));
      registerBlock(59, "wheat", new BlockCrops().setUnlocalizedName("crops"));
      Block var6 = new BlockFarmland().setHardness(0.6F).setStepSound(soundTypeGravel).setUnlocalizedName("farmland");
      registerBlock(60, "farmland", var6);
      registerBlock(
         61, "furnace", new BlockFurnace(false).setHardness(3.5F).setStepSound(i).setUnlocalizedName("furnace").setCreativeTab(CreativeTabs.tabDecorations)
      );
      registerBlock(62, "lit_furnace", new BlockFurnace(true).setHardness(3.5F).setStepSound(i).setLightLevel(0.875F).setUnlocalizedName("furnace"));
      registerBlock(63, "standing_sign", new BlockStandingSign().setHardness(1.0F).setStepSound(f).setUnlocalizedName("sign").disableStats());
      registerBlock(64, "wooden_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(f).setUnlocalizedName("doorOak").disableStats());
      registerBlock(65, "ladder", new BlockLadder().setHardness(0.4F).setStepSound(soundTypeLadder).setUnlocalizedName("ladder"));
      registerBlock(66, "rail", new BlockRail().setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("rail"));
      registerBlock(67, "stone_stairs", new BlockStairs(var0.getDefaultState()).setUnlocalizedName("stairsStone"));
      registerBlock(68, "wall_sign", new BlockWallSign().setHardness(1.0F).setStepSound(f).setUnlocalizedName("sign").disableStats());
      registerBlock(69, "lever", new BlockLever().setHardness(0.5F).setStepSound(f).setUnlocalizedName("lever"));
      registerBlock(
         70,
         "stone_pressure_plate",
         new BlockPressurePlate(Material.rock, BlockPressurePlate.Sensitivity.MOBS).setHardness(0.5F).setStepSound(i).setUnlocalizedName("pressurePlateStone")
      );
      registerBlock(71, "iron_door", new BlockDoor(Material.iron).setHardness(5.0F).setStepSound(soundTypeMetal).setUnlocalizedName("doorIron").disableStats());
      registerBlock(
         72,
         "wooden_pressure_plate",
         new BlockPressurePlate(Material.wood, BlockPressurePlate.Sensitivity.EVERYTHING)
            .setHardness(0.5F)
            .setStepSound(f)
            .setUnlocalizedName("pressurePlateWood")
      );
      registerBlock(
         73,
         "redstone_ore",
         new BlockRedstoneOre(false)
            .setHardness(3.0F)
            .setResistance(5.0F)
            .setStepSound(i)
            .setUnlocalizedName("oreRedstone")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(
         74,
         "lit_redstone_ore",
         new BlockRedstoneOre(true).setLightLevel(0.625F).setHardness(3.0F).setResistance(5.0F).setStepSound(i).setUnlocalizedName("oreRedstone")
      );
      registerBlock(75, "unlit_redstone_torch", new BlockRedstoneTorch(false).setHardness(0.0F).setStepSound(f).setUnlocalizedName("notGate"));
      registerBlock(
         76,
         "redstone_torch",
         new BlockRedstoneTorch(true)
            .setHardness(0.0F)
            .setLightLevel(0.5F)
            .setStepSound(f)
            .setUnlocalizedName("notGate")
            .setCreativeTab(CreativeTabs.tabRedstone)
      );
      registerBlock(77, "stone_button", new BlockButtonStone().setHardness(0.5F).setStepSound(i).setUnlocalizedName("button"));
      registerBlock(78, "snow_layer", new BlockSnow().setHardness(0.1F).setStepSound(soundTypeSnow).setUnlocalizedName("snow").setLightOpacity(0));
      registerBlock(79, "ice", new BlockIce().setHardness(0.5F).setLightOpacity(3).setStepSound(soundTypeGlass).setUnlocalizedName("ice"));
      registerBlock(80, "snow", new BlockSnowBlock().setHardness(0.2F).setStepSound(soundTypeSnow).setUnlocalizedName("snow"));
      registerBlock(81, "cactus", new BlockCactus().setHardness(0.4F).setStepSound(soundTypeCloth).setUnlocalizedName("cactus"));
      registerBlock(82, "clay", new BlockClay().setHardness(0.6F).setStepSound(soundTypeGravel).setUnlocalizedName("clay"));
      registerBlock(83, "reeds", new BlockReed().setHardness(0.0F).setStepSound(h).setUnlocalizedName("reeds").disableStats());
      registerBlock(84, "jukebox", new BlockJukebox().setHardness(2.0F).setResistance(10.0F).setStepSound(i).setUnlocalizedName("jukebox"));
      registerBlock(
         85,
         "fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.OAK.getMapColor())
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setStepSound(f)
            .setUnlocalizedName("fence")
      );
      Block var7 = new BlockPumpkin().setHardness(1.0F).setStepSound(f).setUnlocalizedName("pumpkin");
      registerBlock(86, "pumpkin", var7);
      registerBlock(87, "netherrack", new NetherrackMapColorBlock().setHardness(0.4F).setStepSound(i).setUnlocalizedName("hellrock"));
      registerBlock(88, "soul_sand", new BlockSoulSand().setHardness(0.5F).setStepSound(soundTypeSand).setUnlocalizedName("hellsand"));
      registerBlock(
         89, "glowstone", new BlockGlowstone(Material.glass).setHardness(0.3F).setStepSound(soundTypeGlass).setLightLevel(1.0F).setUnlocalizedName("lightgem")
      );
      registerBlock(90, "portal", new BlockPortal().setHardness(-1.0F).setStepSound(soundTypeGlass).setLightLevel(0.75F).setUnlocalizedName("portal"));
      registerBlock(91, "lit_pumpkin", new BlockPumpkin().setHardness(1.0F).setStepSound(f).setLightLevel(1.0F).setUnlocalizedName("litpumpkin"));
      registerBlock(92, "cake", new BlockCake().setHardness(0.5F).setStepSound(soundTypeCloth).setUnlocalizedName("cake").disableStats());
      registerBlock(93, "unpowered_repeater", new BlockRedstoneRepeater(false).setHardness(0.0F).setStepSound(f).setUnlocalizedName("diode").disableStats());
      registerBlock(94, "powered_repeater", new BlockRedstoneRepeater(true).setHardness(0.0F).setStepSound(f).setUnlocalizedName("diode").disableStats());
      registerBlock(
         95, "stained_glass", new BlockStainedGlass(Material.glass).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("stainedGlass")
      );
      registerBlock(96, "trapdoor", new BlockTrapDoor(Material.wood).setHardness(3.0F).setStepSound(f).setUnlocalizedName("trapdoor").disableStats());
      registerBlock(97, "monster_egg", new BlockSilverfish().setHardness(0.75F).setUnlocalizedName("monsterStoneEgg"));
      Block var8 = new BlockStoneBrick().setHardness(1.5F).setResistance(10.0F).setStepSound(i).setUnlocalizedName("stonebricksmooth");
      registerBlock(98, "stonebrick", var8);
      registerBlock(
         99,
         "brown_mushroom_block",
         new BlockHugeMushroom(Material.wood, MapColor.dirtColor, var3).setHardness(0.2F).setStepSound(f).setUnlocalizedName("mushroom")
      );
      registerBlock(
         100,
         "red_mushroom_block",
         new BlockHugeMushroom(Material.wood, MapColor.redColor, var4).setHardness(0.2F).setStepSound(f).setUnlocalizedName("mushroom")
      );
      registerBlock(
         101,
         "iron_bars",
         new BlockPane(Material.iron, true).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setUnlocalizedName("fenceIron")
      );
      registerBlock(102, "glass_pane", new BlockPane(Material.glass, false).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("thinGlass"));
      Block var9 = new BlockMelon().setHardness(1.0F).setStepSound(f).setUnlocalizedName("melon");
      registerBlock(103, "melon_block", var9);
      registerBlock(104, "pumpkin_stem", new BlockStem(var7).setHardness(0.0F).setStepSound(f).setUnlocalizedName("pumpkinStem"));
      registerBlock(105, "melon_stem", new BlockStem(var9).setHardness(0.0F).setStepSound(f).setUnlocalizedName("pumpkinStem"));
      registerBlock(106, "vine", new BlockVine().setHardness(0.2F).setStepSound(h).setUnlocalizedName("vine"));
      registerBlock(
         107, "fence_gate", new BlockFenceGate(BlockPlanks.EnumType.OAK).setHardness(2.0F).setResistance(5.0F).setStepSound(f).setUnlocalizedName("fenceGate")
      );
      registerBlock(108, "brick_stairs", new BlockStairs(var5.getDefaultState()).setUnlocalizedName("stairsBrick"));
      registerBlock(
         109,
         "stone_brick_stairs",
         new BlockStairs(var8.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT))
            .setUnlocalizedName("stairsStoneBrickSmooth")
      );
      registerBlock(110, "mycelium", new BlockMycelium().setHardness(0.6F).setStepSound(h).setUnlocalizedName("mycel"));
      registerBlock(111, "waterlily", new BlockLilyPad().setHardness(0.0F).setStepSound(h).setUnlocalizedName("waterlily"));
      Block var10 = new NetherBrickMapColorBlock()
         .setHardness(2.0F)
         .setResistance(10.0F)
         .setStepSound(i)
         .setUnlocalizedName("netherBrick")
         .setCreativeTab(CreativeTabs.tabBlock);
      registerBlock(112, "nether_brick", var10);
      registerBlock(
         113,
         "nether_brick_fence",
         new BlockFence(Material.rock, MapColor.netherrackColor).setHardness(2.0F).setResistance(10.0F).setStepSound(i).setUnlocalizedName("netherFence")
      );
      registerBlock(114, "nether_brick_stairs", new BlockStairs(var10.getDefaultState()).setUnlocalizedName("stairsNetherBrick"));
      registerBlock(115, "nether_wart", new BlockNetherWart().setUnlocalizedName("netherStalk"));
      registerBlock(116, "enchanting_table", new BlockEnchantmentTable().setHardness(5.0F).setResistance(2000.0F).setUnlocalizedName("enchantmentTable"));
      registerBlock(117, "brewing_stand", new BlockBrewingStand().setHardness(0.5F).setLightLevel(0.125F).setUnlocalizedName("brewingStand"));
      registerBlock(118, "cauldron", new BlockCauldron().setHardness(2.0F).setUnlocalizedName("cauldron"));
      registerBlock(119, "end_portal", new BlockEndPortal(Material.portal).setHardness(-1.0F).setResistance(6000000.0F));
      registerBlock(
         120,
         "end_portal_frame",
         new BlockEndPortalFrame()
            .setStepSound(soundTypeGlass)
            .setLightLevel(0.125F)
            .setHardness(-1.0F)
            .setUnlocalizedName("endPortalFrame")
            .setResistance(6000000.0F)
            .setCreativeTab(CreativeTabs.tabDecorations)
      );
      registerBlock(
         121,
         "end_stone",
         new Block(Material.rock, MapColor.sandColor)
            .setHardness(3.0F)
            .setResistance(15.0F)
            .setStepSound(i)
            .setUnlocalizedName("whiteStone")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(
         122, "dragon_egg", new BlockDragonEgg().setHardness(3.0F).setResistance(15.0F).setStepSound(i).setLightLevel(0.125F).setUnlocalizedName("dragonEgg")
      );
      registerBlock(
         123,
         "redstone_lamp",
         new BlockRedstoneLight(false)
            .setHardness(0.3F)
            .setStepSound(soundTypeGlass)
            .setUnlocalizedName("redstoneLight")
            .setCreativeTab(CreativeTabs.tabRedstone)
      );
      registerBlock(124, "lit_redstone_lamp", new BlockRedstoneLight(true).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("redstoneLight"));
      registerBlock(125, "double_wooden_slab", new BlockDoubleWoodSlab().setHardness(2.0F).setResistance(5.0F).setStepSound(f).setUnlocalizedName("woodSlab"));
      registerBlock(126, "wooden_slab", new BlockHalfWoodSlab().setHardness(2.0F).setResistance(5.0F).setStepSound(f).setUnlocalizedName("woodSlab"));
      registerBlock(127, "cocoa", new BlockCocoa().setHardness(0.2F).setResistance(5.0F).setStepSound(f).setUnlocalizedName("cocoa"));
      registerBlock(
         128,
         "sandstone_stairs",
         new BlockStairs(var2.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH)).setUnlocalizedName("stairsSandStone")
      );
      registerBlock(129, "emerald_ore", new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(i).setUnlocalizedName("oreEmerald"));
      registerBlock(
         130,
         "ender_chest",
         new BlockEnderChest().setHardness(22.5F).setResistance(1000.0F).setStepSound(i).setUnlocalizedName("enderChest").setLightLevel(0.5F)
      );
      registerBlock(131, "tripwire_hook", new BlockTripWireHook().setUnlocalizedName("tripWireSource"));
      registerBlock(132, "tripwire", new BlockTripWire().setUnlocalizedName("tripWire"));
      registerBlock(
         133,
         "emerald_block",
         new Block(Material.iron, MapColor.emeraldColor)
            .setHardness(5.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypeMetal)
            .setUnlocalizedName("blockEmerald")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(
         134,
         "spruce_stairs",
         new BlockStairs(var1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE)).setUnlocalizedName("stairsWoodSpruce")
      );
      registerBlock(
         135,
         "birch_stairs",
         new BlockStairs(var1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH)).setUnlocalizedName("stairsWoodBirch")
      );
      registerBlock(
         136,
         "jungle_stairs",
         new BlockStairs(var1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE)).setUnlocalizedName("stairsWoodJungle")
      );
      registerBlock(137, "command_block", new BlockCommandBlock().setBlockUnbreakable().setResistance(6000000.0F).setUnlocalizedName("commandBlock"));
      registerBlock(138, "beacon", new BlockBeacon().setUnlocalizedName("beacon").setLightLevel(1.0F));
      registerBlock(139, "cobblestone_wall", new BlockWall(var0).setUnlocalizedName("cobbleWall"));
      registerBlock(140, "flower_pot", new BlockFlowerPot().setHardness(0.0F).setStepSound(soundTypeStone).setUnlocalizedName("flowerPot"));
      registerBlock(141, "carrots", new BlockCarrot().setUnlocalizedName("carrots"));
      registerBlock(142, "potatoes", new BlockPotato().setUnlocalizedName("potatoes"));
      registerBlock(143, "wooden_button", new BlockButtonWood().setHardness(0.5F).setStepSound(f).setUnlocalizedName("button"));
      registerBlock(144, "skull", new BlockSkull().setHardness(1.0F).setStepSound(i).setUnlocalizedName("skull"));
      registerBlock(145, "anvil", new BlockAnvil().setHardness(5.0F).setStepSound(soundTypeAnvil).setResistance(2000.0F).setUnlocalizedName("anvil"));
      registerBlock(146, "trapped_chest", new BlockChest(1).setHardness(2.5F).setStepSound(f).setUnlocalizedName("chestTrap"));
      registerBlock(
         147,
         "light_weighted_pressure_plate",
         new BlockPressurePlateWeighted(Material.iron, 15, MapColor.goldColor).setHardness(0.5F).setStepSound(f).setUnlocalizedName("weightedPlate_light")
      );
      registerBlock(
         148,
         "heavy_weighted_pressure_plate",
         new BlockPressurePlateWeighted(Material.iron, 150).setHardness(0.5F).setStepSound(f).setUnlocalizedName("weightedPlate_heavy")
      );
      registerBlock(
         149, "unpowered_comparator", new BlockRedstoneComparator(false).setHardness(0.0F).setStepSound(f).setUnlocalizedName("comparator").disableStats()
      );
      registerBlock(
         150,
         "powered_comparator",
         new BlockRedstoneComparator(true).setHardness(0.0F).setLightLevel(0.625F).setStepSound(f).setUnlocalizedName("comparator").disableStats()
      );
      registerBlock(151, "daylight_detector", new BlockDaylightDetector(false));
      registerBlock(
         152,
         "redstone_block",
         new BlockCompressedPowered(Material.iron, MapColor.tntColor)
            .setHardness(5.0F)
            .setResistance(10.0F)
            .setStepSound(soundTypeMetal)
            .setUnlocalizedName("blockRedstone")
            .setCreativeTab(CreativeTabs.tabRedstone)
      );
      registerBlock(
         153, "quartz_ore", new BlockOre(MapColor.netherrackColor).setHardness(3.0F).setResistance(5.0F).setStepSound(i).setUnlocalizedName("netherquartz")
      );
      registerBlock(154, "hopper", new BlockHopper().setHardness(3.0F).setResistance(8.0F).setStepSound(soundTypeMetal).setUnlocalizedName("hopper"));
      Block var11 = new BlockQuartz().setStepSound(i).setHardness(0.8F).setUnlocalizedName("quartzBlock");
      registerBlock(155, "quartz_block", var11);
      registerBlock(
         156,
         "quartz_stairs",
         new BlockStairs(var11.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.DEFAULT)).setUnlocalizedName("stairsQuartz")
      );
      registerBlock(157, "activator_rail", new BlockRailPowered().setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("activatorRail"));
      registerBlock(158, "dropper", new BlockDropper().setHardness(3.5F).setStepSound(i).setUnlocalizedName("dropper"));
      registerBlock(
         159,
         "stained_hardened_clay",
         new BlockColored(Material.rock).setHardness(1.25F).setResistance(7.0F).setStepSound(i).setUnlocalizedName("clayHardenedStained")
      );
      registerBlock(
         160, "stained_glass_pane", new BlockStainedGlassPane().setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("thinStainedGlass")
      );
      registerBlock(161, "leaves2", new BlockNewLeaf().setUnlocalizedName("leaves"));
      registerBlock(162, "log2", new BlockNewLog().setUnlocalizedName("log"));
      registerBlock(
         163,
         "acacia_stairs",
         new BlockStairs(var1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA)).setUnlocalizedName("stairsWoodAcacia")
      );
      registerBlock(
         164,
         "dark_oak_stairs",
         new BlockStairs(var1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK)).setUnlocalizedName("stairsWoodDarkOak")
      );
      registerBlock(165, "slime", new BlockSlime().setUnlocalizedName("slime").setStepSound(SLIME_SOUND));
      registerBlock(166, "barrier", new BlockBarrier().setUnlocalizedName("barrier"));
      registerBlock(
         167,
         "iron_trapdoor",
         new BlockTrapDoor(Material.iron).setHardness(5.0F).setStepSound(soundTypeMetal).setUnlocalizedName("ironTrapdoor").disableStats()
      );
      registerBlock(168, "prismarine", new BlockPrismarine().setHardness(1.5F).setResistance(10.0F).setStepSound(i).setUnlocalizedName("prismarine"));
      registerBlock(
         169,
         "sea_lantern",
         new BlockSeaLantern(Material.glass).setHardness(0.3F).setStepSound(soundTypeGlass).setLightLevel(1.0F).setUnlocalizedName("seaLantern")
      );
      registerBlock(170, "hay_block", new BlockHay().setHardness(0.5F).setStepSound(h).setUnlocalizedName("hayBlock").setCreativeTab(CreativeTabs.tabBlock));
      registerBlock(171, "carpet", new BlockCarpet().setHardness(0.1F).setStepSound(soundTypeCloth).setUnlocalizedName("woolCarpet").setLightOpacity(0));
      registerBlock(172, "hardened_clay", new HardenedClayMapColorBlock().setHardness(1.25F).setResistance(7.0F).setStepSound(i).setUnlocalizedName("clayHardened"));
      registerBlock(
         173,
         "coal_block",
         new Block(Material.rock, MapColor.blackColor)
            .setHardness(5.0F)
            .setResistance(10.0F)
            .setStepSound(i)
            .setUnlocalizedName("blockCoal")
            .setCreativeTab(CreativeTabs.tabBlock)
      );
      registerBlock(174, "packed_ice", new BlockPackedIce().setHardness(0.5F).setStepSound(soundTypeGlass).setUnlocalizedName("icePacked"));
      registerBlock(175, "double_plant", new BlockDoublePlant());
      registerBlock(176, "standing_banner", new BlockBanner.BlockBannerStanding().setHardness(1.0F).setStepSound(f).setUnlocalizedName("banner").disableStats());
      registerBlock(177, "wall_banner", new BlockBanner.BlockBannerHanging().setHardness(1.0F).setStepSound(f).setUnlocalizedName("banner").disableStats());
      registerBlock(178, "daylight_detector_inverted", new BlockDaylightDetector(true));
      Block var12 = new BlockRedSandstone().setStepSound(i).setHardness(0.8F).setUnlocalizedName("redSandStone");
      registerBlock(179, "red_sandstone", var12);
      registerBlock(
         180,
         "red_sandstone_stairs",
         new BlockStairs(var12.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.SMOOTH))
            .setUnlocalizedName("stairsRedSandStone")
      );
      registerBlock(
         181, "double_stone_slab2", new BlockDoubleStoneSlabNew().setHardness(2.0F).setResistance(10.0F).setStepSound(i).setUnlocalizedName("stoneSlab2")
      );
      registerBlock(182, "stone_slab2", new BlockHalfStoneSlabNew().setHardness(2.0F).setResistance(10.0F).setStepSound(i).setUnlocalizedName("stoneSlab2"));
      registerBlock(
         183,
         "spruce_fence_gate",
         new BlockFenceGate(BlockPlanks.EnumType.SPRUCE).setHardness(2.0F).setResistance(5.0F).setStepSound(f).setUnlocalizedName("spruceFenceGate")
      );
      registerBlock(
         184,
         "birch_fence_gate",
         new BlockFenceGate(BlockPlanks.EnumType.BIRCH).setHardness(2.0F).setResistance(5.0F).setStepSound(f).setUnlocalizedName("birchFenceGate")
      );
      registerBlock(
         185,
         "jungle_fence_gate",
         new BlockFenceGate(BlockPlanks.EnumType.JUNGLE).setHardness(2.0F).setResistance(5.0F).setStepSound(f).setUnlocalizedName("jungleFenceGate")
      );
      registerBlock(
         186,
         "dark_oak_fence_gate",
         new BlockFenceGate(BlockPlanks.EnumType.DARK_OAK).setHardness(2.0F).setResistance(5.0F).setStepSound(f).setUnlocalizedName("darkOakFenceGate")
      );
      registerBlock(
         187,
         "acacia_fence_gate",
         new BlockFenceGate(BlockPlanks.EnumType.ACACIA).setHardness(2.0F).setResistance(5.0F).setStepSound(f).setUnlocalizedName("acaciaFenceGate")
      );
      registerBlock(
         188,
         "spruce_fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.SPRUCE.getMapColor())
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setStepSound(f)
            .setUnlocalizedName("spruceFence")
      );
      registerBlock(
         189,
         "birch_fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.BIRCH.getMapColor())
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setStepSound(f)
            .setUnlocalizedName("birchFence")
      );
      registerBlock(
         190,
         "jungle_fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.JUNGLE.getMapColor())
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setStepSound(f)
            .setUnlocalizedName("jungleFence")
      );
      registerBlock(
         191,
         "dark_oak_fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.DARK_OAK.getMapColor())
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setStepSound(f)
            .setUnlocalizedName("darkOakFence")
      );
      registerBlock(
         192,
         "acacia_fence",
         new BlockFence(Material.wood, BlockPlanks.EnumType.ACACIA.getMapColor())
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setStepSound(f)
            .setUnlocalizedName("acaciaFence")
      );
      registerBlock(193, "spruce_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(f).setUnlocalizedName("doorSpruce").disableStats());
      registerBlock(194, "birch_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(f).setUnlocalizedName("doorBirch").disableStats());
      registerBlock(195, "jungle_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(f).setUnlocalizedName("doorJungle").disableStats());
      registerBlock(196, "acacia_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(f).setUnlocalizedName("doorAcacia").disableStats());
      registerBlock(197, "dark_oak_door", new BlockDoor(Material.wood).setHardness(3.0F).setStepSound(f).setUnlocalizedName("doorDarkOak").disableStats());
      blockRegistry.validateKey();

      for (Block var14 : blockRegistry) {
         if (var14.J == Material.air) {
            var14.useNeighborBrightness = false;
         } else {
            boolean var15 = false;
            boolean var16 = var14 instanceof BlockStairs;
            boolean var17 = var14 instanceof BlockSlab;
            boolean var18 = var14 == var6;
            boolean var19 = var14.translucent;
            boolean var20 = var14.lightOpacity == 0;
            if (var16 || var17 || var18 || var19 || var20) {
               var15 = true;
            }

            var14.useNeighborBrightness = var15;
         }
      }

      for (Block var22 : blockRegistry) {
         for (IBlockState var24 : var22.P().getValidStates()) {
            int var25 = blockRegistry.getIDForObject(var22) << 4 | var22.getMetaFromState(var24);
            BLOCK_STATE_IDS.put(var24, var25);
         }
      }
   }

   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Item.getItemFromBlock(this);
   }

   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      return this.getStateFromMeta(var7);
   }

   public static Block getBlockFromName(String var0) {
      ResourceLocation var1 = new ResourceLocation(var0);
      if (blockRegistry.containsKey(var1)) {
         return blockRegistry.getObject(var1);
      } else {
         try {
            return blockRegistry.getObjectById(Integer.parseInt(var0));
         } catch (NumberFormatException var3) {
            return null;
         }
      }
   }

   public static IBlockState getStateById(int var0) {
      int var1 = var0 & 4095;
      int var2 = var0 >> 12 & 15;
      return getBlockById(var1).getStateFromMeta(var2);
   }

   public boolean onBlockEventReceived(World var1, BlockPos var2, IBlockState var3, int var4, int var5) {
      return false;
   }

   public Block setHardness(float var1) {
      this.blockHardness = var1;
      if (this.blockResistance < var1 * 5.0F) {
         this.blockResistance = var1 * 5.0F;
      }

      return this;
   }

   public int getLightOpacity() {
      return this.lightOpacity;
   }

   public boolean canReplace(World var1, BlockPos var2, EnumFacing var3, ItemStack var4) {
      return this.canPlaceBlockOnSide(var1, var2, var3);
   }

   public float getAmbientOcclusionLightValue() {
      return this.isBlockNormalCube() ? 0.2F : 1.0F;
   }

   public boolean getTickRandomly() {
      return this.needsRandomTick;
   }

   public AxisAlignedBB getSelectedBoundingBox(World var1, BlockPos var2) {
      return new AxisAlignedBB(
         var2.getX() + this.B, var2.getY() + this.C, var2.getZ() + this.D, var2.getX() + this.E, var2.getY() + this.F, var2.getZ() + this.G
      );
   }

   public Block setLightOpacity(int var1) {
      this.lightOpacity = var1;
      return this;
   }

   public Material getMaterial() {
      return this.J;
   }

   public double getBlockBoundsMaxX() {
      return this.E;
   }

   public float getPlayerRelativeBlockHardness(EntityPlayer var1, World var2, BlockPos var3) {
      float var4 = this.getBlockHardness(var2, var3);
      return var4 < 0.0F
         ? 0.0F
         : (!var1.canHarvestBlock(this) ? var1.getToolDigEfficiency(this) / var4 / 100.0F : var1.getToolDigEfficiency(this) / var4 / 30.0F);
   }

   public boolean getEnableStats() {
      return this.enableStats;
   }

   public int getComparatorInputOverride(World var1, BlockPos var2) {
      return 0;
   }

   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
   }

   public double getBlockBoundsMinY() {
      return this.C;
   }

   public float getBlockHardness(World var1, BlockPos var2) {
      return this.blockHardness;
   }

   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
   }

   public int damageDropped(IBlockState var1) {
      return 0;
   }

   public boolean isCollidable() {
      return true;
   }

   public double getBlockBoundsMinZ() {
      return this.D;
   }

   public MapColor getMapColor(IBlockState var1) {
      return this.blockMapColor;
   }

   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.SOLID;
   }

   public Block setStepSound(Block.SoundType var1) {
      this.stepSound = var1;
      return this;
   }

   public IBlockState getStateForEntityRender(IBlockState var1) {
      return var1;
   }

   public void setDefaultState(IBlockState var1) {
      this.defaultBlockState = var1;
   }

   public BlockState P() {
      return this.M;
   }

   public void onBlockClicked(World var1, BlockPos var2, EntityPlayer var3) {
   }

   public int colorMultiplier(IBlockAccess var1, BlockPos var2, int var3) {
      return 16777215;
   }

   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState();
   }

   public boolean canProvidePower() {
      return false;
   }

   public float getExplosionResistance(Entity var1) {
      return this.blockResistance / 5.0F;
   }

   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return new AxisAlignedBB(
         var2.getX() + this.B, var2.getY() + this.C, var2.getZ() + this.D, var2.getX() + this.E, var2.getY() + this.F, var2.getZ() + this.G
      );
   }

   public Block setLightLevel(float var1) {
      this.lightValue = (int)(15.0F * var1);
      return this;
   }

   public Block(Material var1) {
      this(var1, var1.getMaterialMapColor());
   }

   public static void registerBlock(int var0, String var1, Block var2) {
      registerBlock(var0, new ResourceLocation(var1), var2);
   }

   public void onBlockHarvested(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4) {
   }

   public int quantityDroppedWithBonus(int var1, Random var2) {
      return this.quantityDropped(var2);
   }

   public void a(float var1, float var2, float var3, float var4, float var5, float var6) {
      this.B = var1;
      this.C = var2;
      this.D = var3;
      this.E = var4;
      this.F = var5;
      this.G = var6;
   }

   public BlockState createBlockState() {
      return new BlockState(this);
   }

   public void onEntityCollidedWithBlock(World var1, BlockPos var2, IBlockState var3, Entity var4) {
   }

   public double getBlockBoundsMinX() {
      return this.B;
   }

   public boolean isFlowerPot() {
      return false;
   }

   public int getMixedBrightnessForBlock(IBlockAccess var1, BlockPos var2) {
      Block var3 = var1.getBlockState(var2).getBlock();
      int var4 = var1.getCombinedLight(var2, var3.getLightValue());
      if (var4 == 0 && var3 instanceof BlockSlab) {
         var2 = var2.down();
         var3 = var1.getBlockState(var2).getBlock();
         return var1.getCombinedLight(var2, var3.getLightValue());
      } else {
         return var4;
      }
   }

   public void addCollisionBoxesToList(World var1, BlockPos var2, IBlockState var3, AxisAlignedBB var4, List<AxisAlignedBB> var5, Entity var6) {
      AxisAlignedBB var7 = this.getCollisionBoundingBox(var1, var2, var3);
      if (var7 != null && var4.intersectsWith(var7)) {
         var5.add(var7);
      }
   }

   public Block setUnlocalizedName(String var1) {
      this.unlocalizedName = var1;
      return this;
   }

   public int getMobilityFlag() {
      return this.J.getMaterialMobility();
   }

   public boolean isAssociatedBlock(Block var1) {
      return this == var1;
   }

   public boolean isNormalCube() {
      return this.J.isOpaque() && this.isFullCube() && !this.canProvidePower();
   }

   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
   }

   public boolean isFullBlock() {
      return this.fullBlock;
   }

   public void onLanded(World var1, Entity var2) {
      var2.w = 0.0;
   }

   public void b(World var1, BlockPos var2, int var3) {
      if (!var1.D) {
         while (var3 > 0) {
            int var4 = EntityXPOrb.getXPSplit(var3);
            var3 -= var4;
            var1.spawnEntityInWorld(new EntityXPOrb(var1, var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5, var4));
         }
      }
   }

   public void harvestBlock(World var1, EntityPlayer var2, BlockPos var3, IBlockState var4, TileEntity var5) {
      var2.triggerAchievement(StatList.mineBlockStatArray[getIdFromBlock(this)]);
      var2.addExhaustion(0.025F);
      if (this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier(var2)) {
         ItemStack var7 = this.createStackedBlock(var4);
         if (var7 != null) {
            a(var1, var3, var7);
         }
      } else {
         int var6 = EnchantmentHelper.getFortuneModifier(var2);
         this.dropBlockAsItem(var1, var3, var4, var6);
      }
   }

   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      return var1;
   }

   public boolean getUseNeighborBrightness() {
      return this.useNeighborBrightness;
   }

   public CreativeTabs getCreativeTabToDisplayOn() {
      return this.displayOnCreativeTab;
   }

   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
      if (!var1.D) {
         int var6 = this.quantityDroppedWithBonus(var5, var1.s);

         for (int var7 = 0; var7 < var6; var7++) {
            if (var1.s.nextFloat() <= var4) {
               Item var8 = this.getItemDropped(var3, var1.s, var5);
               if (var8 != null) {
                  a(var1, var2, new ItemStack(var8, 1, this.damageDropped(var3)));
               }
            }
         }
      }
   }

   @Override
   public String toString() {
      return "Block{" + blockRegistry.getNameForObject(this) + "}";
   }

   public void onBlockPlacedBy(World var1, BlockPos var2, IBlockState var3, EntityLivingBase var4, ItemStack var5) {
   }

   public Vec3 modifyAcceleration(World var1, BlockPos var2, Entity var3, Vec3 var4) {
      return var4;
   }

   public void randomTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      this.updateTick(var1, var2, var3, var4);
   }

   public static void a(World var0, BlockPos var1, ItemStack var2) {
      if (!var0.D && var0.Q().getBoolean("doTileDrops")) {
         float var3 = 0.5F;
         double var4 = var0.s.nextFloat() * var3 + (1.0F - var3) * 0.5;
         double var6 = var0.s.nextFloat() * var3 + (1.0F - var3) * 0.5;
         double var8 = var0.s.nextFloat() * var3 + (1.0F - var3) * 0.5;
         EntityItem var10 = new EntityItem(var0, var1.getX() + var4, var1.getY() + var6, var1.getZ() + var8, var2);
         var10.setDefaultPickupDelay();
         var0.spawnEntityInWorld(var10);
      }
   }

   public IBlockState getDefaultState() {
      return this.defaultBlockState;
   }

   public int getWeakPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return 0;
   }

   public static boolean isEqualTo(Block var0, Block var1) {
      return var0 != null && var1 != null ? (var0 == var1 ? true : var0.isAssociatedBlock(var1)) : false;
   }

   public int tickRate(World var1) {
      return 10;
   }

   public boolean requiresUpdates() {
      return true;
   }

   public boolean canSpawnInBlock() {
      return !this.J.isSolid() && !this.J.isLiquid();
   }

   public MovingObjectPosition collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4) {
      this.setBlockBoundsBasedOnState(var1, var2);
      var3 = var3.addVector(-var2.getX(), -var2.getY(), -var2.getZ());
      var4 = var4.addVector(-var2.getX(), -var2.getY(), -var2.getZ());
      Vec3 var5 = var3.getIntermediateWithXValue(var4, this.B);
      Vec3 var6 = var3.getIntermediateWithXValue(var4, this.E);
      Vec3 var7 = var3.getIntermediateWithYValue(var4, this.C);
      Vec3 var8 = var3.getIntermediateWithYValue(var4, this.F);
      Vec3 var9 = var3.getIntermediateWithZValue(var4, this.D);
      Vec3 var10 = var3.getIntermediateWithZValue(var4, this.G);
      if (!this.isVecInsideYZBounds(var5)) {
         var5 = null;
      }

      if (!this.isVecInsideYZBounds(var6)) {
         var6 = null;
      }

      if (!this.isVecInsideXZBounds(var7)) {
         var7 = null;
      }

      if (!this.isVecInsideXZBounds(var8)) {
         var8 = null;
      }

      if (!this.isVecInsideXYBounds(var9)) {
         var9 = null;
      }

      if (!this.isVecInsideXYBounds(var10)) {
         var10 = null;
      }

      Vec3 var11 = null;
      if (var5 != null && (var11 == null || var3.squareDistanceTo(var5) < var3.squareDistanceTo(var11))) {
         var11 = var5;
      }

      if (var6 != null && (var11 == null || var3.squareDistanceTo(var6) < var3.squareDistanceTo(var11))) {
         var11 = var6;
      }

      if (var7 != null && (var11 == null || var3.squareDistanceTo(var7) < var3.squareDistanceTo(var11))) {
         var11 = var7;
      }

      if (var8 != null && (var11 == null || var3.squareDistanceTo(var8) < var3.squareDistanceTo(var11))) {
         var11 = var8;
      }

      if (var9 != null && (var11 == null || var3.squareDistanceTo(var9) < var3.squareDistanceTo(var11))) {
         var11 = var9;
      }

      if (var10 != null && (var11 == null || var3.squareDistanceTo(var10) < var3.squareDistanceTo(var11))) {
         var11 = var10;
      }

      if (var11 == null) {
         return null;
      } else {
         EnumFacing var12 = null;
         if (var11 == var5) {
            var12 = EnumFacing.WEST;
         }

         if (var11 == var6) {
            var12 = EnumFacing.EAST;
         }

         if (var11 == var7) {
            var12 = EnumFacing.DOWN;
         }

         if (var11 == var8) {
            var12 = EnumFacing.UP;
         }

         if (var11 == var9) {
            var12 = EnumFacing.NORTH;
         }

         if (var11 == var10) {
            var12 = EnumFacing.SOUTH;
         }

         return new MovingObjectPosition(var11.addVector(var2.getX(), var2.getY(), var2.getZ()), var12, var2);
      }
   }

   public boolean isVisuallyOpaque() {
      return this.J.blocksMovement() && this.isFullCube();
   }

   public void onBlockDestroyedByExplosion(World var1, BlockPos var2, Explosion var3) {
   }

   public boolean isTranslucent() {
      return this.translucent;
   }

   public double getBlockBoundsMaxZ() {
      return this.G;
   }

   public int getStrongPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return 0;
   }

   public boolean isPassable(IBlockAccess var1, BlockPos var2) {
      return !this.J.blocksMovement();
   }

   public Block disableStats() {
      this.enableStats = false;
      return this;
   }

   public String getUnlocalizedName() {
      return "tile." + this.unlocalizedName;
   }

   public int getRenderType() {
      return 3;
   }

   public Block setCreativeTab(CreativeTabs var1) {
      this.displayOnCreativeTab = var1;
      return this;
   }

   public ItemStack createStackedBlock(IBlockState var1) {
      int var2 = 0;
      Item var3 = Item.getItemFromBlock(this);
      if (var3 != null && var3.getHasSubtypes()) {
         var2 = this.getMetaFromState(var1);
      }

      return new ItemStack(var3, 1, var2);
   }

   public boolean canDropFromExplosion(Explosion var1) {
      return true;
   }

   public void fillWithRain(World var1, BlockPos var2) {
   }

   public Block setResistance(float var1) {
      this.blockResistance = var1 * 3.0F;
      return this;
   }

   public static Block getBlockFromItem(Item var0) {
      return var0 instanceof ItemBlock ? ((ItemBlock)var0).getBlock() : null;
   }

   public boolean hasTileEntity() {
      return this.A;
   }

   public boolean isFullCube() {
      return true;
   }

   public void onBlockDestroyedByPlayer(World var1, BlockPos var2, IBlockState var3) {
   }

   public boolean isReplaceable(World var1, BlockPos var2) {
      return false;
   }

   public boolean canPlaceBlockOnSide(World var1, BlockPos var2, EnumFacing var3) {
      return this.canPlaceBlockAt(var1, var2);
   }

   public int quantityDropped(Random var1) {
      return 1;
   }

   public Block(Material var1, MapColor var2) {
      this.stepSound = soundTypeStone;
      this.blockParticleGravity = 1.0F;
      this.L = 0.6F;
      this.J = var1;
      this.blockMapColor = var2;
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.fullBlock = this.isOpaqueCube();
      this.lightOpacity = this.isOpaqueCube() ? 255 : 0;
      this.translucent = !var1.blocksLight();
      this.M = this.createBlockState();
      this.setDefaultState(this.M.getBaseState());
   }

   public Item getItem(World var1, BlockPos var2) {
      return Item.getItemFromBlock(this);
   }

   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
   }

   public int getRenderColor(IBlockState var1) {
      return 16777215;
   }

   public boolean isBlockNormalCube() {
      return this.J.blocksMovement() && this.isFullCube();
   }

   public static Block getBlockById(int var0) {
      return blockRegistry.getObjectById(var0);
   }

   public void randomDisplayTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
   }

   public void dropBlockAsItem(World var1, BlockPos var2, IBlockState var3, int var4) {
      this.dropBlockAsItemWithChance(var1, var2, var3, 1.0F, var4);
   }

   public boolean isOpaqueCube() {
      return true;
   }

   public int getLightValue() {
      return this.lightValue;
   }

   public void onFallenUpon(World var1, BlockPos var2, Entity var3, float var4) {
      var3.fall(var4, 1.0F);
   }

   public boolean hasComparatorInputOverride() {
      return false;
   }

   public static void registerBlock(int var0, ResourceLocation var1, Block var2) {
      blockRegistry.register(var0, var1, var2);
   }

   public void onEntityCollidedWithBlock(World var1, BlockPos var2, Entity var3) {
   }

   public boolean canCollideCheck(IBlockState var1, boolean var2) {
      return this.isCollidable();
   }

   public static int getStateId(IBlockState var0) {
      Block var1 = var0.getBlock();
      return getIdFromBlock(var1) + (var1.getMetaFromState(var0) << 12);
   }

   public boolean isVecInsideYZBounds(Vec3 var1) {
      return var1 == null ? false : var1.yCoord >= this.C && var1.yCoord <= this.F && var1.zCoord >= this.D && var1.zCoord <= this.G;
   }

   public boolean isBlockSolid(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      return var1.getBlockState(var2).getBlock().getMaterial().isSolid();
   }

   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      return false;
   }

   public static int getIdFromBlock(Block var0) {
      return blockRegistry.getIDForObject(var0);
   }

   public int getDamageValue(World var1, BlockPos var2) {
      return this.damageDropped(var1.getBlockState(var2));
   }

   public double getBlockBoundsMaxY() {
      return this.F;
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      return var3 == EnumFacing.DOWN && this.C > 0.0
         ? true
         : (
            var3 == EnumFacing.UP && this.F < 1.0
               ? true
               : (
                  var3 == EnumFacing.NORTH && this.D > 0.0
                     ? true
                     : (
                        var3 == EnumFacing.SOUTH && this.G < 1.0
                           ? true
                           : (
                              var3 == EnumFacing.WEST && this.B > 0.0
                                 ? true
                                 : (var3 == EnumFacing.EAST && this.E < 1.0 ? true : !var1.getBlockState(var2).getBlock().isOpaqueCube())
                           )
                     )
               )
         );
   }

   public boolean isVecInsideXYBounds(Vec3 var1) {
      return var1 == null ? false : var1.xCoord >= this.B && var1.xCoord <= this.E && var1.yCoord >= this.C && var1.yCoord <= this.F;
   }

   public void setBlockBoundsForItemRender() {
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(var1, 1, 0));
   }

   public int getBlockColor() {
      return 16777215;
   }

   public int colorMultiplier(IBlockAccess var1, BlockPos var2) {
      return this.colorMultiplier(var1, var2, 0);
   }

   public static enum EnumOffsetType {
      NONE,
      XZ,
      XYZ;
      // $VF: synthetic field
      public static Block.EnumOffsetType[] $VALUES = new Block.EnumOffsetType[]{NONE, XZ, Block.EnumOffsetType.XYZ};
   }

   public static class SoundType {
      public float volume;
      public String soundName;
      public float frequency;

      public float getFrequency() {
         return this.frequency;
      }

      public String getStepSound() {
         return "step." + this.soundName;
      }

      public String getBreakSound() {
         return "dig." + this.soundName;
      }

      public SoundType(String var1, float var2, float var3) {
         this.soundName = var1;
         this.volume = var2;
         this.frequency = var3;
      }

      public String getPlaceSound() {
         return this.getBreakSound();
      }

      public float getVolume() {
         return this.volume;
      }
   }
}
