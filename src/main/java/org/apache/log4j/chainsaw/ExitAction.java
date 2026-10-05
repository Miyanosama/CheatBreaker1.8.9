package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;

public class ExitAction extends AbstractAction {
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

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }
}
