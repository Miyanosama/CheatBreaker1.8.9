package net.optifine;

import com.cheatbreaker.client.nethandler.obj.ServerRule;
import io.netty.channel.epoll.EpollEventLoop;
import java.util.Iterator;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.BlockPos;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$5;

public class BlockPosM$1 implements Iterable {
   public ModelRotation field_0003;
   public EpollEventLoop field_0002;
   public LogBrokerMonitor$5 field_0000;
   public ServerRule field_0001;

   public BlockPosM$1(BlockPos var1, BlockPos var2) {
      this.val$posFrom = var1;
      this.val$posTo = var2;
      super();
   }

   @Override
   public Iterator iterator() {
      return new BlockPosM$1$1(this);
   }
}
