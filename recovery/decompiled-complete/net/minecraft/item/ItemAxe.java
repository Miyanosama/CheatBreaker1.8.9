package net.minecraft.item;

import com.cheatbreaker.client.CheatBreaker$1;
import com.google.common.collect.Sets;
import io.netty.channel.group.ChannelMatchers$InvertMatcher;
import io.netty.handler.codec.spdy.DefaultSpdyHeaders$HeaderEntry;
import java.util.Set;
import javazoom.jl.decoder.LayerIIDecoder$SubbandLayer2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.resources.model.WeightedBakedModel$MyWeighedRandomItem;
import net.minecraft.init.Blocks;

public class ItemAxe extends ItemTool {
   public static Set<Block> EFFECTIVE_ON = Sets.newHashSet(
      new Block[]{
         Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder
      }
   );
   public LayerIIDecoder$SubbandLayer2 field_0004;
   public CheatBreaker$1 field_0005;
   public WeightedBakedModel$MyWeighedRandomItem field_0006;
   public ChannelMatchers$InvertMatcher field_0001;
   public DefaultSpdyHeaders$HeaderEntry field_0000;
   public GuiScreenServerList field_0003;

   public ItemAxe(Item$ToolMaterial var1) {
      super(3.0F, var1, EFFECTIVE_ON);
   }

   @Override
   public float getStrVsBlock(ItemStack var1, Block var2) {
      return var2.getMaterial() != Material.wood && var2.getMaterial() != Material.plants && var2.getMaterial() != Material.vine
         ? super.getStrVsBlock(var1, var2)
         : this.efficiencyOnProperMaterial;
   }
}
