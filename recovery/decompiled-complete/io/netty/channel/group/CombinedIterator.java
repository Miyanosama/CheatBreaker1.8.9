package io.netty.channel.group;

import io.netty.handler.codec.PrematureChannelClosureException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.minecraft.block.Block;
import net.minecraft.pathfinding.PathEntity;
import org.apache.log4j.or.sax.AttributesRenderer;

public class CombinedIterator<E> implements Iterator<E> {
   public Block __junk2375577182182552544;
   public Iterator<E> i1;
   public Iterator<E> i2;
   public Iterator<E> currentIterator;
   public PathEntity __junk96036908368847476;
   public PrematureChannelClosureException __junk1144201109124805468;
   public AttributesRenderer __junk4426695852139135419;

   public CombinedIterator(Iterator<E> var1, Iterator<E> var2) {
      if (var1 == null) {
         throw new NullPointerException("i1");
      } else if (var2 == null) {
         throw new NullPointerException("i2");
      } else {
         this.i1 = var1;
         this.i2 = var2;
         this.currentIterator = var1;
      }
   }

   @Override
   public boolean hasNext() {
      while (!this.currentIterator.hasNext()) {
         if (this.currentIterator != this.i1) {
            return false;
         }

         this.currentIterator = this.i2;
      }

      return true;
   }

   @Override
   public E next() {
      while (true) {
         try {
            return this.currentIterator.next();
         } catch (NoSuchElementException var2) {
            if (this.currentIterator != this.i1) {
               throw var2;
            }

            this.currentIterator = this.i2;
         }
      }
   }

   @Override
   public void remove() {
      this.currentIterator.remove();
   }
}
