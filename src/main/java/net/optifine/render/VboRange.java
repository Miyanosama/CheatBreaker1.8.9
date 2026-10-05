package net.optifine.render;

import net.optifine.util.LinkedList;

public class VboRange {
   public int position = -1;
   public LinkedList.Node<VboRange> node;
   public int size = 0;

   public VboRange() {
      this.node = new LinkedList.Node<>(this);
   }

   public void setSize(int var1) {
      this.size = var1;
   }

   public VboRange getPrev() {
      LinkedList.Node var1 = this.node.getPrev();
      return var1 == null ? null : (VboRange)var1.getItem();
   }

   public VboRange getNext() {
      LinkedList.Node var1 = this.node.getNext();
      return var1 == null ? null : (VboRange)var1.getItem();
   }

   public int getPositionNext() {
      return this.position + this.size;
   }

   public LinkedList.Node<VboRange> getNode() {
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
