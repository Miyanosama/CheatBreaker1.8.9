package net.minecraft.block;

import com.cheatbreaker.client.ui.overlay.element.ElementListElement;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelShapes$5;
import net.minecraft.client.renderer.entity.RenderSquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import recovered.unidentified.UnidentifiedClass3697;

public class BlockDoublePlant extends BlockBush implements IGrowable {
   public RenderSquid field_0003;
   public BlockModelShapes$5 field_0001;
   public static PropertyEnum<BlockDoublePlant$EnumPlantType> VARIANT = PropertyEnum.create("variant", BlockDoublePlant$EnumPlantType.class);
   public static PropertyEnum<EnumFacing> FACING = BlockDirectional.O;
   public static PropertyEnum<BlockDoublePlant$EnumBlockHalf> HALF = PropertyEnum.create("half", BlockDoublePlant$EnumBlockHalf.class);
   public EnchantmentProtection field_0007;
   public ElementListElement field_0005;
   public UnidentifiedClass3697 field_0000;

   @Override
   public boolean canUseBonemeal(World var1, Random var2, BlockPos var3, IBlockState var4) {
      return true;
   }

   @Override
   public int colorMultiplier(IBlockAccess var1, BlockPos var2, int var3) {
      BlockDoublePlant$EnumPlantType var4 = this.getVariant(var1, var2);
      return var4 != BlockDoublePlant$EnumPlantType.GRASS && var4 != BlockDoublePlant$EnumPlantType.FERN
         ? 16777215
         : BiomeColorHelper.getGrassColorAtPos(var1, var2);
   }

   public BlockDoublePlant$EnumPlantType getVariant(IBlockAccess var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      if (var3.getBlock() == this) {
         var3 = this.getActualState(var3, var1, var2);
         return var3.getValue(VARIANT);
      } else {
         return BlockDoublePlant$EnumPlantType.FERN;
      }
   }

   @Override
   public void onBlockHarvested(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4) {
      if (var3.getValue(HALF) == BlockDoublePlant$EnumBlockHalf.UPPER) {
         if (var1.getBlockState(var2.down()).getBlock() == this) {
            if (!var4.bA.isCreativeMode) {
               IBlockState var5 = var1.getBlockState(var2.down());
               BlockDoublePlant$EnumPlantType var6 = var5.getValue(VARIANT);
               if (var6 != BlockDoublePlant$EnumPlantType.FERN && var6 != BlockDoublePlant$EnumPlantType.GRASS) {
                  var1.destroyBlock(var2.down(), true);
               } else if (!var1.D) {
                  if (var4.getCurrentEquippedItem() != null && var4.getCurrentEquippedItem().getItem() == Items.shears) {
                     this.onHarvest(var1, var2, var5, var4);
                     var1.setBlockToAir(var2.down());
                  } else {
                     var1.destroyBlock(var2.down(), true);
                  }
               } else {
                  var1.setBlockToAir(var2.down());
               }
            } else {
               var1.setBlockToAir(var2.down());
            }
         }
      } else if (var4.bA.isCreativeMode && var1.getBlockState(var2.up()).getBlock() == this) {
         var1.a(var2.up(), Blocks.air.getDefaultState(), 2);
      }

      super.onBlockHarvested(var1, var2, var3, var4);
   }

   @Override
   public boolean canGrow(World var1, BlockPos var2, IBlockState var3, boolean var4) {
      BlockDoublePlant$EnumPlantType var5 = this.getVariant(var1, var2);
      return var5 != BlockDoublePlant$EnumPlantType.GRASS && var5 != BlockDoublePlant$EnumPlantType.FERN;
   }

   @Override
   public void harvestBlock(World var1, EntityPlayer var2, BlockPos var3, IBlockState var4, TileEntity var5) {
      if (var1.D
         || var2.getCurrentEquippedItem() == null
         || var2.getCurrentEquippedItem().getItem() != Items.shears
         || var4.getValue(HALF) != BlockDoublePlant$EnumBlockHalf.LOWER
         || !this.onHarvest(var1, var3, var4, var2)) {
         super.harvestBlock(var1, var2, var3, var4, var5);
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, HALF, VARIANT, FACING);
   }

   public boolean onHarvest(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4) {
      BlockDoublePlant$EnumPlantType var5 = var3.getValue(VARIANT);
      if (var5 != BlockDoublePlant$EnumPlantType.FERN && var5 != BlockDoublePlant$EnumPlantType.GRASS) {
         return false;
      } else {
         var4.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
         int var6 = (var5 == BlockDoublePlant$EnumPlantType.GRASS ? BlockTallGrass$EnumType.GRASS : BlockTallGrass$EnumType.FERN).getMeta();
         a(var1, var2, new ItemStack(Blocks.tallgrass, 2, var6));
         return true;
      }
   }

   @Override
   public Block$EnumOffsetType getOffsetType() {
      return Block$EnumOffsetType.XZ;
   }

   @Override
   public boolean canBlockStay(World var1, BlockPos var2, IBlockState var3) {
      if (var3.getValue(HALF) == BlockDoublePlant$EnumBlockHalf.UPPER) {
         return var1.getBlockState(var2.down()).getBlock() == this;
      } else {
         IBlockState var4 = var1.getBlockState(var2.up());
         return var4.getBlock() == this && super.canBlockStay(var1, var2, var4);
      }
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(HALF) != BlockDoublePlant$EnumBlockHalf.UPPER && var1.getValue(VARIANT) != BlockDoublePlant$EnumPlantType.GRASS
         ? var1.getValue(VARIANT).getMeta()
         : 0;
   }

   @Override
   public boolean isReplaceable(World var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      if (var3.getBlock() != this) {
         return true;
      } else {
         BlockDoublePlant$EnumPlantType var4 = this.getActualState(var3, var1, var2).getValue(VARIANT);
         return var4 == BlockDoublePlant$EnumPlantType.FERN || var4 == BlockDoublePlant$EnumPlantType.GRASS;
      }
   }

   public BlockDoublePlant() {
      super(Material.vine);
      this.setDefaultState(
         this.M
            .getBaseState()
            .withProperty(VARIANT, BlockDoublePlant$EnumPlantType.SUNFLOWER)
            .withProperty(HALF, BlockDoublePlant$EnumBlockHalf.LOWER)
            .withProperty(FACING, EnumFacing.NORTH)
      );
      this.setHardness(0.0F);
      this.setStepSound(h);
      this.setUnlocalizedName("doublePlant");
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return (var1 & 8) > 0
         ? this.getDefaultState().withProperty(HALF, BlockDoublePlant$EnumBlockHalf.UPPER)
         : this.getDefaultState()
            .withProperty(HALF, BlockDoublePlant$EnumBlockHalf.LOWER)
            .withProperty(VARIANT, BlockDoublePlant$EnumPlantType.byMetadata(var1 & 7));
   }

   @Override
   public int getDamageValue(World var1, BlockPos var2) {
      return this.getVariant(var1, var2).getMeta();
   }

   public void placeAt(World var1, BlockPos var2, BlockDoublePlant$EnumPlantType var3, int var4) {
      var1.a(var2, this.getDefaultState().withProperty(HALF, BlockDoublePlant$EnumBlockHalf.LOWER).withProperty(VARIANT, var3), var4);
      var1.a(var2.up(), this.getDefaultState().withProperty(HALF, BlockDoublePlant$EnumBlockHalf.UPPER), var4);
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return super.canPlaceBlockAt(var1, var2) && var1.isAirBlock(var2.up());
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      if (var1.getValue(HALF) == BlockDoublePlant$EnumBlockHalf.UPPER) {
         IBlockState var4 = var2.getBlockState(var3.down());
         if (var4.getBlock() == this) {
            var1 = var1.withProperty(VARIANT, var4.getValue(VARIANT));
         }
      }

      return var1;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(HALF) == BlockDoublePlant$EnumBlockHalf.UPPER ? 8 | var1.getValue(FACING).getHorizontalIndex() : var1.getValue(VARIANT).getMeta();
   }

   @Override
   public void checkAndDropBlock(World var1, BlockPos var2, IBlockState var3) {
      if (!this.canBlockStay(var1, var2, var3)) {
         boolean var4 = var3.getValue(HALF) == BlockDoublePlant$EnumBlockHalf.UPPER;
         BlockPos var5 = var4 ? var2 : var2.up();
         BlockPos var6 = var4 ? var2.down() : var2;
         Object var7 = var4 ? this : var1.getBlockState(var5).getBlock();
         Object var8 = var4 ? var1.getBlockState(var6).getBlock() : this;
         if (var7 == this) {
            var1.a(var5, Blocks.air.getDefaultState(), 2);
         }

         if (var8 == this) {
            var1.a(var6, Blocks.air.getDefaultState(), 3);
            if (!var4) {
               this.dropBlockAsItem(var1, var6, var3, 0);
            }
         }
      }
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockDoublePlant$EnumPlantType var7 : BlockDoublePlant$EnumPlantType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMeta()));
      }
   }

   @Override
   public void grow(World var1, Random var2, BlockPos var3, IBlockState var4) {
      a(var1, var3, new ItemStack(this, 1, this.getVariant(var1, var3).getMeta()));
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      if (var1.getValue(HALF) == BlockDoublePlant$EnumBlockHalf.UPPER) {
         return null;
      } else {
         BlockDoublePlant$EnumPlantType var4 = var1.getValue(VARIANT);
         return var4 == BlockDoublePlant$EnumPlantType.FERN
            ? null
            : (var4 == BlockDoublePlant$EnumPlantType.GRASS ? (var2.nextInt(8) == 0 ? Items.wheat_seeds : null) : Item.getItemFromBlock(this));
      }
   }

   @Override
   public void onBlockPlacedBy(World var1, BlockPos var2, IBlockState var3, EntityLivingBase var4, ItemStack var5) {
      var1.a(var2.up(), this.getDefaultState().withProperty(HALF, BlockDoublePlant$EnumBlockHalf.UPPER), 2);
   }
}
