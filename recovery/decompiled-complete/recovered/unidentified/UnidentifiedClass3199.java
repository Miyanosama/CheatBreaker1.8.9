package recovered.unidentified;

import io.netty.util.collection.IntObjectHashMap;
import java.util.Comparator;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$Cross;
import org.apache.log4j.pattern.DatePatternConverter;
import org.lwjgl.opengl.DisplayMode;

public class UnidentifiedClass3199 implements Comparator {
   public StructureMineshaftPieces$Cross field_0002;
   public UnidentifiedClass4985 field_0004;
   public IntObjectHashMap field_0001;
   public CraftingManager field_0003;
   public DatePatternConverter field_0000;

   @Override
   public int compare(Object var1, Object var2) {
      DisplayMode var3 = (DisplayMode)var1;
      DisplayMode var4 = (DisplayMode)var2;
      return var3.getWidth() != var4.getWidth()
         ? var3.getWidth() - var4.getWidth()
         : (
            var3.getHeight() != var4.getHeight()
               ? var3.getHeight() - var4.getHeight()
               : (
                  var3.getBitsPerPixel() != var4.getBitsPerPixel()
                     ? var3.getBitsPerPixel() - var4.getBitsPerPixel()
                     : (var3.getFrequency() != var4.getFrequency() ? var3.getFrequency() - var4.getFrequency() : 0)
               )
         );
   }
}
