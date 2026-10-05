package io.netty.util;

import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.server.management.ItemInWorldManager;

public class Recycler$WeakOrderQueue$Link extends AtomicInteger {
   public ItemInWorldManager __junk3872551102104547323;
   public int readIndex;
   public Recycler$WeakOrderQueue$Link next;
   public Recycler$DefaultHandle[] elements = new Recycler$DefaultHandle[16];

   public Recycler$WeakOrderQueue$Link() {
   }
}
