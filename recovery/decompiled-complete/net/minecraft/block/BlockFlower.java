package net.minecraft.block;

import io.netty.handler.codec.http.ClientCookieEncoder;
import java.util.List;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.layers.LayerWolfCollar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentArrowInfinite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class BlockFlower extends BlockBush {
   public PropertyEnum<BlockFlower$EnumFlowerType> type;
   public LayerWolfCollar field_0000;
   public EnchantmentArrowInfinite field_0001;
   public ClientCookieEncoder field_0003;

   public BlockFlower() {
      this.setDefaultState(
         this.M
            .getBaseState()
            .withProperty(
               this.getTypeProperty(),
               this.getBlockType() == BlockFlower$EnumFlowerColor.RED ? BlockFlower$EnumFlowerType.POPPY : BlockFlower$EnumFlowerType.DANDELION
            )
      );
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(this.getTypeProperty(), BlockFlower$EnumFlowerType.getType(this.getBlockType(), var1));
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, this.getTypeProperty());
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(this.getTypeProperty()).getMeta();
   }

   public abstract BlockFlower$EnumFlowerColor getBlockType();

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockFlower$EnumFlowerType var7 : BlockFlower$EnumFlowerType.getTypes(this.getBlockType())) {
         var3.add(new ItemStack(var1, 1, var7.getMeta()));
      }
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(this.getTypeProperty()).getMeta();
   }

   @Override
   public Block$EnumOffsetType getOffsetType() {
      return Block$EnumOffsetType.XZ;
   }

   public IProperty<BlockFlower$EnumFlowerType> getTypeProperty() {
      if (this.type == null) {
         this.type = PropertyEnum.create("type", BlockFlower$EnumFlowerType.class, new BlockFlower$1(this));
      }

      return this.type;
   }
}
