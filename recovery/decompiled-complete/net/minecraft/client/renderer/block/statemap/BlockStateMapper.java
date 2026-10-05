package net.minecraft.client.renderer.block.statemap;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.optifine.entity.model.anim.ModelVariableType$1;
import recovered.unidentified.UnidentifiedClass3204;

public class BlockStateMapper {
   public ModelVariableType$1 field_0001;
   public Set<Block> setBuiltInBlocks;
   public Map<Block, IStateMapper> blockStateMap = Maps.newIdentityHashMap();
   public UnidentifiedClass3204 field_0002;

   public void registerBuiltInBlocks(Block... var1) {
      Collections.addAll(this.setBuiltInBlocks, var1);
   }

   public BlockStateMapper() {
      this.setBuiltInBlocks = Sets.newIdentityHashSet();
   }

   public Map<IBlockState, ModelResourceLocation> putAllStateModelLocations() {
      IdentityHashMap var1 = Maps.newIdentityHashMap();

      for (Block var3 : Block.blockRegistry) {
         if (!this.setBuiltInBlocks.contains(var3)) {
            var1.putAll(((IStateMapper)Objects.firstNonNull(this.blockStateMap.get(var3), new DefaultStateMapper())).putStateModelLocations(var3));
         }
      }

      return var1;
   }

   public void registerBlockStateMapper(Block var1, IStateMapper var2) {
      this.blockStateMap.put(var1, var2);
   }
}
