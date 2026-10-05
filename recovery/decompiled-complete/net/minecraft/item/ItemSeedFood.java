package net.minecraft.item;

import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import io.netty.channel.sctp.oio.OioSctpChannel$OioSctpChannelConfig;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import recovered.unidentified.UnidentifiedClass1534;
import recovered.unidentified.UnidentifiedClass4752;

public class ItemSeedFood extends ItemFood {
   public UnidentifiedClass1534 field_0002;
   public ScrollableElement field_0005;
   public Block soilId;
   public WorldGenTaiga1 field_0007;
   public Block crops;
   public OioSctpChannel$OioSctpChannelConfig field_0000;
   public DynamicTexture field_0003;
   public UnidentifiedClass4752 field_0004;

   public ItemSeedFood(int var1, float var2, Block var3, Block var4) {
      super(var1, var2, false);
      this.crops = var3;
      this.soilId = var4;
   }

   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var5 != EnumFacing.UP) {
         return false;
      } else if (!var2.canPlayerEdit(var4.a(var5), var5, var1)) {
         return false;
      } else if (var3.getBlockState(var4).getBlock() == this.soilId && var3.isAirBlock(var4.up())) {
         var3.setBlockState(var4.up(), this.crops.getDefaultState());
         var1.stackSize--;
         return true;
      } else {
         return false;
      }
   }
}
