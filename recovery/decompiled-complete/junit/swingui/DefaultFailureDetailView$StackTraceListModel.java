package junit.swingui;

import io.netty.handler.ssl.OpenSslServerContext;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.AbstractListModel;
import net.minecraft.client.gui.MapItemRenderer$Instance;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$1;

public class DefaultFailureDetailView$StackTraceListModel extends AbstractListModel {
   public StructureMineshaftPieces$1 field_0001;
   public MapItemRenderer$Instance field_0003;
   public OpenSslServerContext field_0000;
   public Vector fLines = new Vector(20);

   public void setTrace(String var1) {
      this.method_29719(var1);
      this.fireContentsChanged(this, 0, this.fLines.size());
   }

   public void method_29719(String var1) {
      this.fLines.removeAllElements();
      StringTokenizer var2 = new StringTokenizer(var1, "\n\r", false);

      while (var2.hasMoreTokens()) {
         this.fLines.addElement(var2.nextToken());
      }
   }

   public Object getElementAt(int var1) {
      return this.fLines.elementAt(var1);
   }

   public int getSize() {
      return this.fLines.size();
   }

   public void clear() {
      this.fLines.removeAllElements();
      this.fireContentsChanged(this, 0, this.fLines.size());
   }
}
