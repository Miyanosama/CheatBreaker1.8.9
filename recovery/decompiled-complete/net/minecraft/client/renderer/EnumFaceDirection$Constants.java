package net.minecraft.client.renderer;

import com.cheatbreaker.client.event.type.LoadWorldEvent;
import com.cheatbreaker.client.nethandler.NetHandler;
import io.netty.handler.codec.spdy.SpdyFrameCodec;
import junit.swingui.CounterPanel;
import net.minecraft.server.management.UserList$1;
import net.minecraft.util.EnumFacing;

public class EnumFaceDirection$Constants {
   public static int WEST_INDEX = EnumFacing.WEST.getIndex();
   public NetHandler field_0008;
   public LoadWorldEvent field_0004;
   public UserList$1 field_0007;
   public CounterPanel field_0001;
   public static int UP_INDEX = EnumFacing.UP.getIndex();
   public SpdyFrameCodec field_0009;
   public static int NORTH_INDEX = EnumFacing.NORTH.getIndex();
   public static int SOUTH_INDEX = EnumFacing.SOUTH.getIndex();
   public static int DOWN_INDEX = EnumFacing.DOWN.getIndex();
   public static int EAST_INDEX = EnumFacing.EAST.getIndex();
}
