package recovered.unidentified;

import io.netty.channel.epoll.EpollSocketChannel;
import javazoom.jl.converter.jlc$jlcArgs;
import net.minecraft.realms.RealmsScrolledSelectionList;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class UnidentifiedClass0943 {
   public EpollSocketChannel field_0001;
   public RealmsScrolledSelectionList field_0003;
   public jlc$jlcArgs field_0002;

   static {
      try {
         field_0000[EnumFacing.WEST.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0000[EnumFacing.EAST.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0000[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0000[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
