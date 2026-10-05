package net.minecraft.network;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.ChannelOutboundBuffer$2;
import io.netty.channel.nio.NioEventLoopGroup;
import junit.swingui.TestSuitePanel$TestTreeCellRenderer;
import net.minecraft.entity.monster.IMob$1;
import net.minecraft.util.LazyLoadBase;
import net.optifine.util.MathUtilsTest$1;
import org.java_websocket.enums.CloseHandshakeType;
import recovered.unidentified.UnidentifiedClass1290;
import recovered.unidentified.UnidentifiedClass1812;

public class NetworkSystem$1 extends LazyLoadBase<NioEventLoopGroup> {
   public CloseHandshakeType field_0003;
   public IMob$1 field_0005;
   public UnidentifiedClass1812 field_0002;
   public TestSuitePanel$TestTreeCellRenderer field_0004;
   public MathUtilsTest$1 field_0000;
   public UnidentifiedClass1290 field_0001;
   public ChannelOutboundBuffer$2 field_0006;

   public NioEventLoopGroup load() {
      return new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Server IO #%d").setDaemon(true).build());
   }
}
