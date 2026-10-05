package recovered.unidentified;

import com.cheatbreaker.client.ui.module.CBPositionEnum;
import io.netty.util.internal.chmv8.CountedCompleter$1;
import net.minecraft.block.BlockPortal$Size;
import net.minecraft.entity.projectile.EntityArrow;
import org.slf4j.helpers.NOPLogger;

// $VF: synthetic class
public class UnidentifiedClass4502 {
   public CountedCompleter$1 field_0003;
   public NOPLogger field_0005;
   public UnidentifiedClass0105 field_0002;
   public BlockPortal$Size field_0004;
   public EntityArrow field_0000;

   static {
      try {
         field_0001[CBPositionEnum.LEFT.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0001[CBPositionEnum.CENTER.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0001[CBPositionEnum.RIGHT.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
