/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 */
package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.List;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockOldLog
extends BlockLog {
    public static PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class, new Predicate<BlockPlanks.EnumType>(){

        public boolean apply(BlockPlanks.EnumType enumType) {
            return enumType.getMetadata() < 4;
        }
    });

    @Override
    public ItemStack createStackedBlock(IBlockState iBlockState) {
        return new ItemStack(Item.getItemFromBlock(this), 1, iBlockState.getValue(VARIANT).getMetadata());
    }

    @Override
    public int getMetaFromState(IBlockState iBlockState) {
        int n = 0;
        n |= iBlockState.getValue(VARIANT).getMetadata();
        switch ((BlockLog.EnumAxis)iBlockState.getValue(a)) {
            case X: {
                n |= 4;
                break;
            }
            case Z: {
                n |= 8;
                break;
            }
            case NONE: {
                n |= 0xC;
            }
        }
        return n;
    }

    public BlockOldLog() {
        this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK).withProperty(a, BlockLog.EnumAxis.Y));
    }

    @Override
    public BlockState createBlockState() {
        return new BlockState(this, VARIANT, a);
    }

    @Override
    public int damageDropped(IBlockState iBlockState) {
        return iBlockState.getValue(VARIANT).getMetadata();
    }

    @Override
    public IBlockState getStateFromMeta(int n) {
        IBlockState iBlockState = this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata((n & 3) % 4));
        switch (n & 0xC) {
            case 0: {
                iBlockState = iBlockState.withProperty(a, BlockLog.EnumAxis.Y);
                break;
            }
            case 4: {
                iBlockState = iBlockState.withProperty(a, BlockLog.EnumAxis.X);
                break;
            }
            case 8: {
                iBlockState = iBlockState.withProperty(a, BlockLog.EnumAxis.Z);
                break;
            }
            default: {
                iBlockState = iBlockState.withProperty(a, BlockLog.EnumAxis.NONE);
            }
        }
        return iBlockState;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> list) {
        list.add(new ItemStack(item, 1, BlockPlanks.EnumType.OAK.getMetadata()));
        list.add(new ItemStack(item, 1, BlockPlanks.EnumType.SPRUCE.getMetadata()));
        list.add(new ItemStack(item, 1, BlockPlanks.EnumType.BIRCH.getMetadata()));
        list.add(new ItemStack(item, 1, BlockPlanks.EnumType.JUNGLE.getMetadata()));
    }

    @Override
    public MapColor getMapColor(IBlockState iBlockState) {
        BlockPlanks.EnumType enumType = iBlockState.getValue(VARIANT);
        switch ((BlockLog.EnumAxis)iBlockState.getValue(a)) {
            default: {
                switch (enumType) {
                    default: {
                        return BlockPlanks.EnumType.SPRUCE.getMapColor();
                    }
                    case SPRUCE: {
                        return BlockPlanks.EnumType.DARK_OAK.getMapColor();
                    }
                    case BIRCH: {
                        return MapColor.quartzColor;
                    }
                    case JUNGLE: 
                }
                return BlockPlanks.EnumType.SPRUCE.getMapColor();
            }
            case Y: 
        }
        return enumType.getMapColor();
    }
}

