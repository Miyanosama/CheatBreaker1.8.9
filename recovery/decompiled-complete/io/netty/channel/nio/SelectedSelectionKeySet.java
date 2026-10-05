package io.netty.channel.nio;

import io.netty.handler.codec.marshalling.ContextBoundUnmarshallerProvider;
import io.netty.handler.codec.spdy.SpdyHttpHeaders;
import java.nio.channels.SelectionKey;
import java.util.AbstractSet;
import java.util.Iterator;
import net.minecraft.client.renderer.GlStateManager$ColorLogicState;
import net.minecraft.command.server.CommandPardonPlayer;
import net.optifine.RandomEntityProperties;
import net.optifine.shaders.uniform.CustomUniform;

public class SelectedSelectionKeySet extends AbstractSet<SelectionKey> {
   public CustomUniform __junk6378139869514145929;
   public SelectionKey[] keysA;
   public CommandPardonPlayer __junk2270760444374113578;
   public int keysBSize;
   public GlStateManager$ColorLogicState __junk243722031177948593;
   public ContextBoundUnmarshallerProvider __junk4047918012928967816;
   public RandomEntityProperties __junk6991270589961026464;
   public boolean isA = true;
   public SelectionKey[] keysB;
   public int keysASize;
   public SpdyHttpHeaders __junk4862425393303032830;

   public SelectionKey[] flip() {
      if (this.isA) {
         this.isA = false;
         this.keysA[this.keysASize] = null;
         this.keysBSize = 0;
         return this.keysA;
      } else {
         this.isA = true;
         this.keysB[this.keysBSize] = null;
         this.keysASize = 0;
         return this.keysB;
      }
   }

   @Override
   public Iterator<SelectionKey> iterator() {
      throw new UnsupportedOperationException();
   }

   @Override
   public int size() {
      return this.isA ? this.keysASize : this.keysBSize;
   }

   public SelectedSelectionKeySet() {
      this.keysA = new SelectionKey[1024];
      this.keysB = (SelectionKey[])this.keysA.clone();
   }

   @Override
   public boolean contains(Object var1) {
      return false;
   }

   public void doubleCapacityA() {
      SelectionKey[] var1 = new SelectionKey[this.keysA.length << 1];
      System.arraycopy(this.keysA, 0, var1, 0, this.keysASize);
      this.keysA = var1;
   }

   public void doubleCapacityB() {
      SelectionKey[] var1 = new SelectionKey[this.keysB.length << 1];
      System.arraycopy(this.keysB, 0, var1, 0, this.keysBSize);
      this.keysB = var1;
   }

   @Override
   public boolean remove(Object var1) {
      return false;
   }

   public boolean add(SelectionKey var1) {
      if (var1 == null) {
         return false;
      } else {
         if (this.isA) {
            int var2 = this.keysASize;
            this.keysA[var2++] = var1;
            this.keysASize = var2;
            if (var2 == this.keysA.length) {
               this.doubleCapacityA();
            }
         } else {
            int var4 = this.keysBSize;
            this.keysB[var4++] = var1;
            this.keysBSize = var4;
            if (var4 == this.keysB.length) {
               this.doubleCapacityB();
            }
         }

         return true;
      }
   }
}
