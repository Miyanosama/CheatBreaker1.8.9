package net.optifine.render;

import io.netty.util.internal.chmv8.ForkJoinPool$EmptyTask;
import net.optifine.config.RangeInt;
import net.optifine.shaders.config.ShaderParser;
import net.optifine.util.LinkedList$Node;

public class VboRange {
   public ForkJoinPool$EmptyTask field_0003;
   public int position = -1;
   public RangeInt field_0002;
   public ShaderParser field_0004;
   public LinkedList$Node<VboRange> node;
   public int size = 0;

   public VboRange() {
      this.node = new LinkedList$Node<>(this);
   }

   public void setSize(int var1) {
      this.size = var1;
   }

   public VboRange getPrev() {
      LinkedList$Node var1 = this.node.getPrev();
      return var1 == null ? null : (VboRange)var1.getItem();
   }

   public VboRange getNext() {
      LinkedList$Node var1 = this.node.getNext();
      return var1 == null ? null : (VboRange)var1.getItem();
   }

   public int getPositionNext() {
      return this.position + this.size;
   }

   public LinkedList$Node<VboRange> getNode() {
      return this.node;
   }

   public int getSize() {
      return this.size;
   }

   public void setPosition(int var1) {
      this.position = var1;
   }

   public int getPosition() {
      return this.position;
   }

   @Override
   public String toString() {
      return "" + this.position + "/" + this.size + "/" + (this.position + this.size);
   }
}
