package recovered.unidentified;

import io.netty.handler.codec.http.CookieHeaderNames;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;

public class UnidentifiedClass0499 extends DefaultListCellRenderer {
   public CategoryPath field_0001;
   public UnidentifiedClass1256 field_0002;
   public CookieHeaderNames field_0000;

   public Component getListCellRendererComponent(JList var1, Object var2, int var3, boolean var4, boolean var5) {
      String var6 = ((String)var2).replace('\t', ' ');
      Component var7 = super.getListCellRendererComponent(var1, var6, var3, var4, var5);
      this.setText(var6);
      this.setToolTipText(var6);
      return var7;
   }
}
