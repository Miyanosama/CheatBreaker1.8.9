package net.optifine.util;

import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.util.AbstractReferenceCounted;
import java.util.Iterator;
import net.minecraft.entity.monster.EntityIronGolem$AINearestAttackableTargetNonCreeper$1;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeRenderer;

public class LinkedList$1 implements Iterator<LinkedList$Node<T>> {
   public LinkedList$Node<T> node;
   public WriteTimeoutHandler field_0002;
   public C0EPacketClickWindow field_0004;
   public AbstractReferenceCounted field_0000;
   public CategoryNodeRenderer field_0001;
   public EntityIronGolem$AINearestAttackableTargetNonCreeper$1 field_0006;

   @Override
   public boolean hasNext() {
      return this.node != null;
   }

   public LinkedList$Node<T> next() {
      LinkedList$Node var1 = this.node;
      if (this.node != null) {
         this.node = LinkedList$Node.access$400(this.node);
      }

      return var1;
   }

   public LinkedList$1(LinkedList var1) {
      this.this$0 = var1;
      super();
      this.node = this.this$0.getFirst();
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("remove");
   }
}
