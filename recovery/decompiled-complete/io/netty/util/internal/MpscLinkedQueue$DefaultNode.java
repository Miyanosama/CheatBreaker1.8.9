package io.netty.util.internal;

import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.client.renderer.block.model.ItemModelGenerator$Span;
import org.apache.log4j.helpers.PatternParser$CategoryPatternConverter;

public class MpscLinkedQueue$DefaultNode<T> extends MpscLinkedQueueNode<T> {
   public BlockRedstoneOre __junk7187806410753027523;
   public T value;
   public ItemModelGenerator$Span __junk3641388631091186866;
   public PatternParser$CategoryPatternConverter __junk2483260630152951686;

   @Override
   public T clearMaybe() {
      Object var1 = this.value;
      this.value = null;
      return (T)var1;
   }

   public MpscLinkedQueue$DefaultNode(T var1) {
      this.value = (T)var1;
   }

   @Override
   public T value() {
      return this.value;
   }
}
