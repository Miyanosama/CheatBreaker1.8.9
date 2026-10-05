package io.netty.util.internal;

import com.cheatbreaker.client.nethandler.shared.PacketAddWaypoint;
import java.util.Iterator;
import java.util.NoSuchElementException;
import junit.textui.ResultPrinter;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.network.PacketThreadUtil$1;
import net.minecraft.stats.StatCrafting;
import org.apache.log4j.PatternLayout;

public class MpscLinkedQueue$1 implements Iterator<E> {
   public CrashReportCategory __junk6182771587600533266;
   public StatCrafting __junk1786881823194470624;
   public MpscLinkedQueueNode<E> node;
   public PatternLayout __junk1092550629583917821;
   public PacketAddWaypoint __junk6552833793976573687;
   public ResultPrinter __junk7452504448188887594;
   public PacketThreadUtil$1 __junk6774214736784785911;

   @Override
   public E next() {
      MpscLinkedQueueNode var1 = this.node;
      if (var1 == null) {
         throw new NoSuchElementException();
      } else {
         Object var2 = var1.value();
         this.node = var1.next();
         return (E)var2;
      }
   }

   @Override
   public boolean hasNext() {
      return this.node != null;
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException();
   }

   public MpscLinkedQueue$1(MpscLinkedQueue var1) {
      this.this$0 = var1;
      super();
      this.node = MpscLinkedQueue.access$000(this.this$0);
   }
}
