package junit.swingui;

import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe$1;
import java.util.Vector;
import junit.runner.Sorter$Swapper;
import net.minecraft.client.network.NetHandlerPlayClient$3$1$1;

public class TestSelector$ParallelSwapper implements Sorter$Swapper {
   public Vector fOther;
   public EpollSocketChannel$EpollSocketUnsafe$1 field_0003;
   public TestSelector this$0;
   public NetHandlerPlayClient$3$1$1 field_0002;

   public void swap(Vector var1, int var2, int var3) {
      Object var4 = var1.elementAt(var2);
      var1.setElementAt(var1.elementAt(var3), var2);
      var1.setElementAt(var4, var3);
      Object var5 = this.fOther.elementAt(var2);
      this.fOther.setElementAt(this.fOther.elementAt(var3), var2);
      this.fOther.setElementAt(var5, var3);
   }

   public TestSelector$ParallelSwapper(TestSelector var1, Vector var2) {
      this.this$0 = var1;
      this.fOther = var2;
   }
}
