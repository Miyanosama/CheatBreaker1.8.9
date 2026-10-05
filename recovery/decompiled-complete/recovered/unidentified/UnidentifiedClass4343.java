package recovered.unidentified;

import io.netty.buffer.PoolThreadCache$1;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.apache.log4j.ConsoleAppender$SystemErrStream;

// $VF: synthetic class
public class UnidentifiedClass4343 {
   public World field_0001;
   public ConsoleAppender$SystemErrStream field_0000;
   public PoolThreadCache$1 field_0002;

   static {
      try {
         field_0003[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0003[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0003[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0003[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
