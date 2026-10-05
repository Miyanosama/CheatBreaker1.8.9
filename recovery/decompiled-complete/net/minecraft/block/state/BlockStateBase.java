package net.minecraft.block.state;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import io.netty.handler.codec.http.HttpObjectDecoder$LineParser;
import io.netty.handler.codec.spdy.SpdyHttpCodec;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSponge;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.ResourceLocation;

public abstract class BlockStateBase implements IBlockState {
   public int blockId = -1;
   public int blockStateId = -1;
   public static Function<Entry<IProperty, Comparable>, String> MAP_ENTRY_TO_STRING = new BlockStateBase$1();
   public SpdyHttpCodec field_0006;
   public int metadata = -1;
   public HttpObjectDecoder$LineParser field_0008;
   public ResourceLocation blockLocation = null;
   public BlockSponge field_0004;
   public static Joiner COMMA_JOINER = Joiner.on(',');

   public int getBlockStateId() {
      if (this.blockStateId < 0) {
         this.blockStateId = Block.getStateId(this);
      }

      return this.blockStateId;
   }

   public int getBlockId() {
      if (this.blockId < 0) {
         this.blockId = Block.getIdFromBlock(this.getBlock());
      }

      return this.blockId;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(Block.blockRegistry.getNameForObject(this.getBlock()));
      if (!this.getProperties().isEmpty()) {
         var1.append("[");
         COMMA_JOINER.appendTo(var1, Iterables.transform(this.getProperties().entrySet(), MAP_ENTRY_TO_STRING));
         var1.append("]");
      }

      return var1.toString();
   }

   public ResourceLocation getBlockLocation() {
      if (this.blockLocation == null) {
         this.blockLocation = Block.blockRegistry.getNameForObject(this.getBlock());
      }

      return this.blockLocation;
   }

   public int getMetadata() {
      if (this.metadata < 0) {
         this.metadata = this.getBlock().getMetaFromState(this);
      }

      return this.metadata;
   }

   public static <T> T cyclePropertyValue(Collection<T> var0, T var1) {
      Iterator var2 = var0.iterator();

      while (var2.hasNext()) {
         if (var2.next().equals(var1)) {
            if (var2.hasNext()) {
               return (T)var2.next();
            }

            return (T)var0.iterator().next();
         }
      }

      return (T)var2.next();
   }

   public ImmutableTable<IProperty<?>, Comparable<?>, IBlockState> getPropertyValueTable() {
      return null;
   }

   @Override
   public <T extends Comparable<T>> IBlockState cycleProperty(IProperty<T> var1) {
      return this.withProperty(var1, cyclePropertyValue(var1.getAllowedValues(), this.getValue(var1)));
   }
}
