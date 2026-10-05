package org.apache.log4j.lf5.viewer;

import javax.swing.table.DefaultTableModel;
import net.minecraft.client.particle.MobAppearance$Factory;
import org.slf4j.helpers.Util$1;

public class LogTableModel extends DefaultTableModel {
   public static long field_0001;
   public MobAppearance$Factory field_0002;
   public Util$1 field_0000;

   public boolean isCellEditable(int var1, int var2) {
      return false;
   }

   public LogTableModel(Object[] var1, int var2) {
      super(var1, var2);
   }
}
