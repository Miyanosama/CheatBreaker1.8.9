package recovered.unidentified;

import com.cheatbreaker.client.ui.mainmenu.BuildInformationMenu;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.world.World$3;
import net.minecraft.world.chunk.storage.RegionFileCache;

// $VF: synthetic class
public class UnidentifiedClass1104 {
   public World$3 field_0003;
   public RegionFileCache field_0002;
   public EntityBubbleFX field_0004;
   public UnidentifiedClass3568 field_0000;
   public BuildInformationMenu field_0001;

   static {
      try {
         field_0005[EnumFacing$Axis.Z.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0005[EnumFacing$Axis.X.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0005[EnumFacing$Axis.Y.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
