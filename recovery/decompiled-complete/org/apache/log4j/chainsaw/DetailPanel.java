package org.apache.log4j.chainsaw;

import io.netty.util.concurrent.CompleteFuture;
import java.awt.BorderLayout;
import java.text.MessageFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.entity.passive.EntityMooshroom;
import org.apache.log4j.Logger;
import org.scijava.nativelib.NativeLibraryUtil;

public class DetailPanel extends JPanel implements ListSelectionListener {
   public static Logger LOG = Logger.getLogger(
      DetailPanel.class$org$apache$log4j$chainsaw$DetailPanel == null
         ? (DetailPanel.class$org$apache$log4j$chainsaw$DetailPanel = class$("org.apache.log4j.chainsaw.DetailPanel"))
         : DetailPanel.class$org$apache$log4j$chainsaw$DetailPanel
   );
   public CompleteFuture field_0007;
   public static Class class$org$apache$log4j$chainsaw$DetailPanel;
   public NativeLibraryUtil field_0006;
   public StitcherException field_0000;
   public static MessageFormat FORMATTER = new MessageFormat(
      "<b>Time:</b> <code>{0,time,medium}</code>&nbsp;&nbsp;<b>Priority:</b> <code>{1}</code>&nbsp;&nbsp;<b>Thread:</b> <code>{2}</code>&nbsp;&nbsp;<b>NDC:</b> <code>{3}</code><br><b>Logger:</b> <code>{4}</code><br><b>Location:</b> <code>{5}</code><br><b>Message:</b><pre>{6}</pre><b>Throwable:</b><pre>{7}</pre>"
   );
   public EntityMooshroom field_0008;
   public JEditorPane mDetails;
   public MyTableModel mModel;

   public DetailPanel(JTable var1, MyTableModel var2) {
      this.mModel = var2;
      this.setLayout(new BorderLayout());
      this.setBorder(BorderFactory.createTitledBorder("Details: "));
      this.mDetails = new JEditorPane();
      this.mDetails.setEditable(false);
      this.mDetails.setContentType("text/html");
      this.add(new JScrollPane(this.mDetails), "Center");
      ListSelectionModel var3 = var1.getSelectionModel();
      var3.addListSelectionListener(this);
   }

   public String escape(String var1) {
      if (var1 == null) {
         return null;
      } else {
         StringBuffer var2 = new StringBuffer();

         for (int var3 = 0; var3 < var1.length(); var3++) {
            char var4 = var1.charAt(var3);
            switch (var4) {
               case '"':
                  var2.append("&quot;");
                  break;
               case '&':
                  var2.append("&amp;");
                  break;
               case '<':
                  var2.append("&lt;");
                  break;
               case '>':
                  var2.append("&gt;");
                  break;
               default:
                  var2.append(var4);
            }
         }

         return var2.toString();
      }
   }

   public static String getThrowableStrRep(EventDetails var0) {
      String[] var1 = var0.getThrowableStrRep();
      if (var1 == null) {
         return null;
      } else {
         StringBuffer var2 = new StringBuffer();

         for (int var3 = 0; var3 < var1.length; var3++) {
            var2.append(var1[var3]).append("\n");
         }

         return var2.toString();
      }
   }

   public void valueChanged(ListSelectionEvent var1) {
      if (!var1.getValueIsAdjusting()) {
         ListSelectionModel var2 = (ListSelectionModel)var1.getSource();
         if (var2.isSelectionEmpty()) {
            this.mDetails.setText("Nothing selected");
         } else {
            int var3 = var2.getMinSelectionIndex();
            EventDetails var4 = this.mModel.getEventDetails(var3);
            Object[] var5 = new Object[]{
               new Date(var4.getTimeStamp()),
               var4.getPriority(),
               this.escape(var4.getThreadName()),
               this.escape(var4.getNDC()),
               this.escape(var4.getCategoryName()),
               this.escape(var4.getLocationDetails()),
               this.escape(var4.getMessage()),
               this.escape(getThrowableStrRep(var4))
            };
            this.mDetails.setText(FORMATTER.format(var5));
            this.mDetails.setCaretPosition(0);
         }
      }
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }
}
