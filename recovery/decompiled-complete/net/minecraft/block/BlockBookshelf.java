package net.minecraft.block;

import com.cheatbreaker.client.ui.util.HudUtil;
import io.netty.handler.codec.base64.Base64Dialect;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer$4;

public class BlockBookshelf extends Block {
   public HudUtil field_0002;
   public MinecraftServer$4 field_0003;
   public EntityAIRestrictOpenDoor field_0000;
   public Base64Dialect field_0001;

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Items.book;
   }

   public BlockBookshelf() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public int quantityDropped(Random var1) {
      return 3;
   }
}
