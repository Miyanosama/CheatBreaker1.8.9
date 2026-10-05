package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSkull extends BlockContainer {
   public BlockPattern witherPattern;
   public static PropertyDirection FACING = PropertyDirection.create("facing");
   public BlockPattern witherBasePattern;
   public static PropertyBool NODROP = PropertyBool.create("nodrop");
   public static Predicate<BlockWorldState> IS_WITHER_SKELETON = new Predicate<BlockWorldState>() {
      public boolean apply(BlockWorldState var1) {
         return var1.getBlockState() != null
            && var1.getBlockState().getBlock() == Blocks.skull
            && var1.getTileEntity() instanceof TileEntitySkull
            && ((TileEntitySkull)var1.getTileEntity()).getSkullType() == 1;
      }
   };

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Items.skull;
   }

   @Override
   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntitySkull();
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal("tile.skull.skeleton.name");
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING, NODROP);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      switch (var1.getBlockState(var2).getValue(FACING)) {
         case UP:
         default:
            this.a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
            break;
         case NORTH:
            this.a(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
            break;
         case SOUTH:
            this.a(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
            break;
         case WEST:
            this.a(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
            break;
         case EAST:
            this.a(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Items.skull;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getDamageValue(World var1, BlockPos var2) {
      TileEntity var3 = var1.getTileEntity(var2);
      return var3 instanceof TileEntitySkull ? ((TileEntitySkull)var3).getSkullType() : super.getDamageValue(var1, var2);
   }

   public BlockPattern getWitherPattern() {
      if (this.witherPattern == null) {
         this.witherPattern = FactoryBlockPattern.start()
            .aisle("^^^", "###", "~#~")
            .where('#', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.soul_sand)))
            .where('^', IS_WITHER_SKELETON)
            .where('~', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.air)))
            .build();
      }

      return this.witherPattern;
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
   }

   public BlockSkull() {
      super(Material.circuits);
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(NODROP, false));
      this.a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
   }

   @Override
   public void onBlockHarvested(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4) {
      if (var4.bA.isCreativeMode) {
         var3 = var3.withProperty(NODROP, true);
         var1.a(var2, var3, 4);
      }

      super.onBlockHarvested(var1, var2, var3, var4);
   }

   public void checkWitherSpawn(World var1, BlockPos var2, TileEntitySkull var3) {
      if (var3.getSkullType() == 1 && var2.getY() >= 2 && var1.getDifficulty() != EnumDifficulty.PEACEFUL && !var1.D) {
         BlockPattern var4 = this.getWitherPattern();
         BlockPattern.PatternHelper var5 = var4.match(var1, var2);
         if (var5 != null) {
            for (int var6 = 0; var6 < 3; var6++) {
               BlockWorldState var7 = var5.translateOffset(var6, 0, 0);
               var1.a(var7.getPos(), var7.getBlockState().withProperty(NODROP, true), 2);
            }

            for (int var12 = 0; var12 < var4.getPalmLength(); var12++) {
               for (int var14 = 0; var14 < var4.getThumbLength(); var14++) {
                  BlockWorldState var8 = var5.translateOffset(var12, var14, 0);
                  var1.a(var8.getPos(), Blocks.air.getDefaultState(), 2);
               }
            }

            BlockPos var13 = var5.translateOffset(1, 0, 0).getPos();
            EntityWither var15 = new EntityWither(var1);
            BlockPos var16 = var5.translateOffset(1, 2, 0).getPos();
            var15.a_(var16.getX() + 0.5, var16.getY() + 0.55, var16.getZ() + 0.5, var5.getFinger().getAxis() == EnumFacing.Axis.X ? 0.0F : 90.0F, 0.0F);
            var15.aI = var5.getFinger().getAxis() == EnumFacing.Axis.X ? 0.0F : 90.0F;
            var15.func_82206_m();

            for (EntityPlayer var10 : var1.getEntitiesWithinAABB(EntityPlayer.class, var15.getEntityBoundingBox().expand(50.0, 50.0, 50.0))) {
               var10.triggerAchievement(AchievementList.recoveredField323);
            }

            var1.spawnEntityInWorld(var15);

            for (int var17 = 0; var17 < 120; var17++) {
               var1.spawnParticle(
                  EnumParticleTypes.SNOWBALL,
                  var13.getX() + var1.s.nextDouble(),
                  var13.getY() - 2 + var1.s.nextDouble() * 3.9,
                  var13.getZ() + var1.s.nextDouble(),
                  0.0,
                  0.0,
                  0.0
               );
            }

            for (int var18 = 0; var18 < var4.getPalmLength(); var18++) {
               for (int var19 = 0; var19 < var4.getThumbLength(); var19++) {
                  BlockWorldState var11 = var5.translateOffset(var18, var19, 0);
                  var1.notifyNeighborsRespectDebug(var11.getPos(), Blocks.air);
               }
            }
         }
      }
   }

   public BlockPattern getWitherBasePattern() {
      if (this.witherBasePattern == null) {
         this.witherBasePattern = FactoryBlockPattern.start()
            .aisle("   ", "###", "~#~")
            .where('#', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.soul_sand)))
            .where('~', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.air)))
            .build();
      }

      return this.witherBasePattern;
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      return this.getDefaultState().withProperty(FACING, var8.getHorizontalFacing()).withProperty(NODROP, false);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getCollisionBoundingBox(var1, var2, var3);
   }

   public boolean canDispenserPlace(World var1, BlockPos var2, ItemStack var3) {
      return var3.getMetadata() == 1 && var2.getY() >= 2 && var1.getDifficulty() != EnumDifficulty.PEACEFUL && !var1.D
         ? this.getWitherBasePattern().match(var1, var2) != null
         : false;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(FACING).getIndex();
      if (var1.getValue(NODROP)) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(var1 & 7)).withProperty(NODROP, (var1 & 8) > 0);
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      if (!var1.D) {
         if (!var3.getValue(NODROP)) {
            TileEntity var4 = var1.getTileEntity(var2);
            if (var4 instanceof TileEntitySkull) {
               TileEntitySkull var5 = (TileEntitySkull)var4;
               ItemStack var6 = new ItemStack(Items.skull, 1, this.getDamageValue(var1, var2));
               if (var5.getSkullType() == 3 && var5.getPlayerProfile() != null) {
                  var6.setTagCompound(new NBTTagCompound());
                  NBTTagCompound var7 = new NBTTagCompound();
                  NBTUtil.writeGameProfile(var7, var5.getPlayerProfile());
                  var6.getTagCompound().setTag("SkullOwner", var7);
               }

               a(var1, var2, var6);
            }
         }

         super.breakBlock(var1, var2, var3);
      }
   }
}
