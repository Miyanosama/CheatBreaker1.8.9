package org.apache.log4j;

import java.util.Vector;
import net.minecraft.client.resources.SkinManager;
import net.optifine.model.ModelUtils;

public class ProvisionNode extends Vector {
   public static long field_0001;
   public SkinManager field_0002;
   public ModelUtils field_0000;

   public ProvisionNode(Logger var1) {
      this.addElement(var1);
   }
}
