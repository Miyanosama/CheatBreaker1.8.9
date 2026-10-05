package net.minecraft.block.state.pattern;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.NetworkManager$4;
import net.minecraft.network.play.server.S41PacketServerDifficulty;

public class BlockStateHelper implements Predicate<IBlockState> {
   public NetworkManager$4 field_0001;
   public Map<IProperty, Predicate> propertyPredicates = Maps.newHashMap();
   public S41PacketServerDifficulty field_0000;
   public BlockState blockstate;

   public boolean apply(IBlockState var1) {
      if (var1 != null && var1.getBlock().equals(this.blockstate.getBlock())) {
         for (Entry var3 : this.propertyPredicates.entrySet()) {
            Comparable var4 = var1.getValue((IProperty<Comparable>)var3.getKey());
            if (!((Predicate)var3.getValue()).apply(var4)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public <V extends Comparable<V>> BlockStateHelper where(IProperty<V> var1, Predicate<? extends V> var2) {
      if (!this.blockstate.getProperties().contains(var1)) {
         throw new IllegalArgumentException(this.blockstate + " cannot support property " + var1);
      } else {
         this.propertyPredicates.put(var1, var2);
         return this;
      }
   }

   public static BlockStateHelper forBlock(Block var0) {
      return new BlockStateHelper(var0.P());
   }

   public BlockStateHelper(BlockState var1) {
      this.blockstate = var1;
   }
}
