package org.apache.log4j.or.sax;

import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.init.Bootstrap$7;
import net.minecraft.world.chunk.storage.AnvilSaveConverter$1;
import org.apache.log4j.or.ObjectRenderer;
import org.xml.sax.Attributes;

public class AttributesRenderer implements ObjectRenderer {
   public AnvilSaveConverter$1 field_0001;
   public Stitcher field_0002;
   public Bootstrap$7 field_0000;

   public String doRender(Object var1) {
      if (var1 instanceof Attributes) {
         StringBuffer var2 = new StringBuffer();
         Attributes var3 = (Attributes)var1;
         int var4 = var3.getLength();
         boolean var5 = true;

         for (int var6 = 0; var6 < var4; var6++) {
            if (var5) {
               var5 = false;
            } else {
               var2.append(", ");
            }

            var2.append(var3.getQName(var6));
            var2.append('=');
            var2.append(var3.getValue(var6));
         }

         return var2.toString();
      } else {
         try {
            return var1.toString();
         } catch (Exception var7) {
            return var7.toString();
         }
      }
   }
}
