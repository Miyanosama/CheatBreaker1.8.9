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

public class BlockNewLog
extends BlockLog {
    public static PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class, new Predicate<BlockPlanks.EnumType>(){

        public boolean apply(BlockPlanks.EnumType enumType) {
            return enumType.getMetadata() >= 4;
        }
    });

    @Override
    public IBlockState getStateFromMeta(int n) {
        IBlockState iBlockState = this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata((n & 3) + 4));
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
    public int getMetaFromState(IBlockState iBlockState) {
        int n = 0;
        n |= iBlockState.getValue(VARIANT).getMetadata() - 4;
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

    public BlockNewLog() {
        this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(a, BlockLog.EnumAxis.Y));
    }

    @Override
    public int damageDropped(IBlockState iBlockState) {
        return iBlockState.getValue(VARIANT).getMetadata() - 4;
    }

    @Override
    public ItemStack createStackedBlock(IBlockState iBlockState) {
        return new ItemStack(Item.getItemFromBlock(this), 1, iBlockState.getValue(VARIANT).getMetadata() - 4);
    }

    @Override
    public MapColor getMapColor(IBlockState iBlockState) {
        BlockPlanks.EnumType enumType = iBlockState.getValue(VARIANT);
        switch ((BlockLog.EnumAxis)iBlockState.getValue(a)) {
            default: {
                switch (enumType) {
                    default: {
                        return MapColor.stoneColor;
                    }
                    case DARK_OAK: 
                }
                return BlockPlanks.EnumType.DARK_OAK.getMapColor();
            }
            case Y: 
        }
        return enumType.getMapColor();
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> list) {
        list.add(new ItemStack(item, 1, BlockPlanks.EnumType.ACACIA.getMetadata() - 4));
        list.add(new ItemStack(item, 1, BlockPlanks.EnumType.DARK_OAK.getMetadata() - 4));
    }

    @Override
    public BlockState createBlockState() {
        return new BlockState(this, VARIANT, a);
    }
}

