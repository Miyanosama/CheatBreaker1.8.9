package io.netty.buffer;

import com.cheatbreaker.client.util.DiscordPresenceManager;
import io.netty.util.internal.StringUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.entity.ai.EntityMoveHelper;
import org.davidmoten.text.utils.WordWrap;

public class PoolChunkList<T> {
   public PoolArena<T> arena;
   public int maxUsage;
   public static final boolean $assertionsDisabled = !PoolChunkList.class.desiredAssertionStatus();
   public PoolChunkList<T> prevList;
   public PoolChunkList<T> nextList;
   public int minUsage;
   public PoolChunk<T> head;

   public PoolChunkList(PoolArena<T> var1, PoolChunkList<T> var2, int var3, int var4) {
      this.arena = var1;
      this.nextList = var2;
      this.minUsage = var3;
      this.maxUsage = var4;
   }

   public void add(PoolChunk<T> var1) {
      if (var1.usage() >= this.maxUsage) {
         this.nextList.add(var1);
      } else {
         var1.parent = this;
         if (this.head == null) {
            this.head = var1;
            var1.prev = null;
            var1.next = null;
         } else {
            var1.prev = null;
            var1.next = this.head;
            this.head.prev = var1;
            this.head = var1;
         }
      }
   }

   public void free(PoolChunk<T> var1, long var2) {
      var1.free(var2);
      if (var1.usage() < this.minUsage) {
         this.remove(var1);
         if (this.prevList == null) {
            if (!$assertionsDisabled && var1.usage() != 0) {
               throw new AssertionError();
            }

            this.arena.destroyChunk(var1);
         } else {
            this.prevList.add(var1);
         }
      }
   }

   public boolean allocate(PooledByteBuf<T> var1, int var2, int var3) {
      if (this.head == null) {
         return false;
      } else {
         PoolChunk var4 = this.head;

         do {
            long var5 = var4.allocate(var3);
            if (var5 >= 0L) {
               var4.initBuf(var1, var5, var2);
               if (var4.usage() >= this.maxUsage) {
                  this.remove(var4);
                  this.nextList.add(var4);
               }

               return true;
            }

            var4 = var4.next;
         } while (var4 != null);

         return false;
      }
   }

   public void remove(PoolChunk<T> var1) {
      if (var1 == this.head) {
         this.head = var1.next;
         if (this.head != null) {
            this.head.prev = null;
         }
      } else {
         PoolChunk var2 = var1.next;
         var1.prev.next = var2;
         if (var2 != null) {
            var2.prev = var1.prev;
         }
      }
   }

   @Override
   public String toString() {
      if (this.head == null) {
         return "none";
      } else {
         StringBuilder var1 = new StringBuilder();
         PoolChunk var2 = this.head;

         while (true) {
            var1.append(var2);
            var2 = var2.next;
            if (var2 == null) {
               return var1.toString();
            }

            var1.append(StringUtil.NEWLINE);
         }
      }
   }
}
