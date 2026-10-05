package recovered.unidentified;

import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker00;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.PlayerSelector;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPotion;

public class UnidentifiedClass0682 extends Block {
   public ItemPotion field_0001;
   public PlayerSelector field_0002;
   public WebSocketClientHandshaker00 field_0000;

   public UnidentifiedClass0682() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return MapColor.adobeColor;
   }
}
