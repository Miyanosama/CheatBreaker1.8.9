package recovered.unidentified;

import com.cheatbreaker.client.ui.overlay.element.DraggableElement;
import io.netty.channel.SucceededChannelFuture;
import java.util.Comparator;
import net.minecraft.src.Config;
import net.minecraft.util.ClassInheritanceMultiMap$1;
import net.optifine.CustomItemProperties;

public class UnidentifiedClass3676 implements Comparator {
   public ClassInheritanceMultiMap$1 field_0001;
   public DraggableElement field_0002;
   public SucceededChannelFuture field_0000;

   @Override
   public int compare(Object var1, Object var2) {
      CustomItemProperties var3 = (CustomItemProperties)var1;
      CustomItemProperties var4 = (CustomItemProperties)var2;
      return var3.weight != var4.weight
         ? var4.weight - var3.weight
         : (!Config.equals(var3.basePath, var4.basePath) ? var3.basePath.compareTo(var4.basePath) : var3.name.compareTo(var4.name));
   }
}
