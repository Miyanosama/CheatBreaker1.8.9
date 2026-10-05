package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import net.minecraft.block.BlockDoubleStoneSlabNew;
import net.minecraft.realms.DisconnectedRealmsScreen;
import net.optifine.config.RangeListInt;
import org.apache.log4j.Logger;

public class ExitAction extends AbstractAction {
   public RangeListInt field_0003;
   public DisconnectedRealmsScreen field_0005;
   public BlockDoubleStoneSlabNew field_0002;
   public static Logger LOG = Logger.getLogger(
      ExitAction.class$org$apache$log4j$chainsaw$ExitAction == null
         ? (ExitAction.class$org$apache$log4j$chainsaw$ExitAction = class$("org.apache.log4j.chainsaw.ExitAction"))
         : ExitAction.class$org$apache$log4j$chainsaw$ExitAction
   );
   public static Class class$org$apache$log4j$chainsaw$ExitAction;
   public static ExitAction INSTANCE = new ExitAction();

   public void actionPerformed(ActionEvent var1) {
      LOG.info("shutting down");
      System.exit(0);
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }
}
