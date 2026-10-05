package io.netty.util;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.renderer.entity.RenderRabbit;
import org.apache.log4j.helpers.QuietWriter;
import recovered.unidentified.UnidentifiedClass1810;

public class Recycler$WeakOrderQueue {
   public UnidentifiedClass1810 __junk4787325445273847427;
   public WeakReference<Thread> owner;
   public RenderRabbit __junk2870240199227786760;
   public Recycler$WeakOrderQueue next;
   public QuietWriter __junk1941345712286990307;
   public int id = Recycler.access$400().getAndIncrement();
   public static int LINK_CAPACITY;
   public Recycler$WeakOrderQueue$Link tail;
   public GuiOptionButton __junk2489561607214205136;
   public Recycler$WeakOrderQueue$Link head;

   public boolean hasFinalData() {
      return Recycler$WeakOrderQueue$Link.access$1000(this.tail) != this.tail.get();
   }

   public boolean transfer(Recycler$Stack<?> var1) {
      Recycler$WeakOrderQueue$Link var2 = this.head;
      if (var2 == null) {
         return false;
      } else {
         if (Recycler$WeakOrderQueue$Link.access$1000(var2) == 16) {
            if (Recycler$WeakOrderQueue$Link.access$800(var2) == null) {
               return false;
            }

            this.head = var2 = Recycler$WeakOrderQueue$Link.access$800(var2);
         }

         int var3 = Recycler$WeakOrderQueue$Link.access$1000(var2);
         int var4 = var2.get();
         if (var3 == var4) {
            return false;
         } else {
            int var5 = var4 - var3;
            if (Recycler$Stack.access$1100(var1) + var5 > Recycler$Stack.access$1200(var1).length) {
               Recycler$Stack.access$1202(var1, Arrays.copyOf(Recycler$Stack.access$1200(var1), (Recycler$Stack.access$1100(var1) + var5) * 2));
            }

            Recycler$DefaultHandle[] var6 = Recycler$WeakOrderQueue$Link.access$900(var2);
            Recycler$DefaultHandle[] var7 = Recycler$Stack.access$1200(var1);

            int var8;
            for (var8 = Recycler$Stack.access$1100(var1); var3 < var4; var6[var3++] = null) {
               Recycler$DefaultHandle var9 = var6[var3];
               if (Recycler$DefaultHandle.access$1300(var9) == 0) {
                  Recycler$DefaultHandle.access$1302(var9, Recycler$DefaultHandle.access$700(var9));
               } else if (Recycler$DefaultHandle.access$1300(var9) != Recycler$DefaultHandle.access$700(var9)) {
                  throw new IllegalStateException("recycled already");
               }

               Recycler$DefaultHandle.access$202(var9, var1);
               var7[var8++] = var9;
            }

            Recycler$Stack.access$1102(var1, var8);
            if (var4 == 16 && Recycler$WeakOrderQueue$Link.access$800(var2) != null) {
               this.head = Recycler$WeakOrderQueue$Link.access$800(var2);
            }

            Recycler$WeakOrderQueue$Link.access$1002(var2, var4);
            return true;
         }
      }
   }

   public void add(Recycler$DefaultHandle var1) {
      Recycler$DefaultHandle.access$702(var1, this.id);
      Recycler$WeakOrderQueue$Link var2 = this.tail;
      int var3;
      if ((var3 = var2.get()) == 16) {
         this.tail = var2 = Recycler$WeakOrderQueue$Link.access$802(var2, new Recycler$WeakOrderQueue$Link(null));
         var3 = var2.get();
      }

      Recycler$WeakOrderQueue$Link.access$900(var2)[var3] = var1;
      Recycler$DefaultHandle.access$202(var1, null);
      var2.lazySet(var3 + 1);
   }

   public Recycler$WeakOrderQueue(Recycler$Stack<?> var1, Thread var2) {
      this.head = this.tail = new Recycler$WeakOrderQueue$Link(null);
      this.owner = new WeakReference<>(var2);
      synchronized (var1) {
         this.next = Recycler$Stack.access$600(var1);
         Recycler$Stack.access$602(var1, this);
      }
   }
}
