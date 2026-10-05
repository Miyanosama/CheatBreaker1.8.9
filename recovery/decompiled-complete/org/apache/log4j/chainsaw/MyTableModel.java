package org.apache.log4j.chainsaw;

import io.netty.handler.codec.compression.Crc32c;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.table.AbstractTableModel;
import net.minecraft.client.renderer.entity.RenderRabbit;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.helpers.BoundedFIFO;

public class MyTableModel extends AbstractTableModel {
   public String mThreadFilter;
   public Priority mPriorityFilter;
   public static Logger LOG = Logger.getLogger(
      MyTableModel.class$org$apache$log4j$chainsaw$MyTableModel == null
         ? (MyTableModel.class$org$apache$log4j$chainsaw$MyTableModel = class$("org.apache.log4j.chainsaw.MyTableModel"))
         : MyTableModel.class$org$apache$log4j$chainsaw$MyTableModel
   );
   public EventDetails[] mFilteredEvents;
   public Crc32c field_0003;
   public String mNDCFilter;
   public static Class class$org$apache$log4j$chainsaw$MyTableModel;
   public String mCategoryFilter;
   public static Class class$java$lang$Boolean;
   public static EventDetails[] EMPTY_LIST = new EventDetails[0];
   public List mPendingEvents;
   public static Class class$java$lang$Object;
   public RenderRabbit field_0012;
   public BoundedFIFO field_0007;
   public static Comparator MY_COMP = new MyTableModel$1();
   public Object mLock = new Object();
   public SortedSet mAllEvents = new TreeSet(MY_COMP);
   public static DateFormat DATE_FORMATTER = DateFormat.getDateTimeInstance(3, 2);
   public String mMessageFilter;
   public static String[] COL_NAMES = new String[]{"Time", "Priority", "Trace", "Category", "NDC", "Message"};
   public boolean mPaused;

   public void setThreadFilter(String var1) {
      synchronized (this.mLock) {
         this.mThreadFilter = var1.trim();
         this.updateFilteredEvents(false);
      }
   }

   public void setNDCFilter(String var1) {
      synchronized (this.mLock) {
         this.mNDCFilter = var1.trim();
         this.updateFilteredEvents(false);
      }
   }

   public static List access$200(MyTableModel var0) {
      return var0.mPendingEvents;
   }

   public String getColumnName(int var1) {
      return COL_NAMES[var1];
   }

   public static void access$500(MyTableModel var0, boolean var1) {
      var0.updateFilteredEvents(var1);
   }

   public void toggle() {
      synchronized (this.mLock) {
         this.mPaused = !this.mPaused;
      }
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public Object getValueAt(int var1, int var2) {
      synchronized (this.mLock) {
         EventDetails var4 = this.mFilteredEvents[var1];
         if (var2 == 0) {
            return DATE_FORMATTER.format(new Date(var4.getTimeStamp()));
         } else if (var2 == 1) {
            return var4.getPriority();
         } else if (var2 == 2) {
            return var4.getThrowableStrRep() == null ? Boolean.FALSE : Boolean.TRUE;
         } else if (var2 == 3) {
            return var4.getCategoryName();
         } else {
            return var2 == 4 ? var4.getNDC() : var4.getMessage();
         }
      }
   }

   public Class getColumnClass(int var1) {
      return var1 == 2
         ? (class$java$lang$Boolean == null ? (class$java$lang$Boolean = class$("java.lang.Boolean")) : class$java$lang$Boolean)
         : (class$java$lang$Object == null ? (class$java$lang$Object = class$("java.lang.Object")) : class$java$lang$Object);
   }

   public MyTableModel() {
      this.mFilteredEvents = EMPTY_LIST;
      this.mPendingEvents = new ArrayList();
      this.mPaused = false;
      this.mThreadFilter = "";
      this.mMessageFilter = "";
      this.mNDCFilter = "";
      this.mCategoryFilter = "";
      this.mPriorityFilter = Priority.DEBUG;
      Thread var1 = new Thread(new MyTableModel$Processor(this, null));
      var1.setDaemon(true);
      var1.start();
   }

   public static SortedSet access$300(MyTableModel var0) {
      return var0.mAllEvents;
   }

   public int getRowCount() {
      synchronized (this.mLock) {
         return this.mFilteredEvents.length;
      }
   }

   public int getColumnCount() {
      return COL_NAMES.length;
   }

   public void setPriorityFilter(Priority var1) {
      synchronized (this.mLock) {
         this.mPriorityFilter = var1;
         this.updateFilteredEvents(false);
      }
   }

   public EventDetails getEventDetails(int var1) {
      synchronized (this.mLock) {
         return this.mFilteredEvents[var1];
      }
   }

   public void updateFilteredEvents(boolean var1) {
      long var2 = System.currentTimeMillis();
      ArrayList var4 = new ArrayList();
      int var5 = this.mAllEvents.size();

      for (EventDetails var7 : this.mAllEvents) {
         if (this.matchFilter(var7)) {
            var4.add(var7);
         }
      }

      EventDetails var10 = this.mFilteredEvents.length == 0 ? null : this.mFilteredEvents[0];
      this.mFilteredEvents = var4.toArray(EMPTY_LIST);
      if (var1 && var10 != null) {
         int var8 = var4.indexOf(var10);
         if (var8 < 1) {
            LOG.warn("In strange state");
            this.fireTableDataChanged();
         } else {
            this.fireTableRowsInserted(0, var8 - 1);
         }
      } else {
         this.fireTableDataChanged();
      }

      long var11 = System.currentTimeMillis();
      LOG.debug("Total time [ms]: " + (var11 - var2) + " in update, size: " + var5);
   }

   public void setMessageFilter(String var1) {
      synchronized (this.mLock) {
         this.mMessageFilter = var1.trim();
         this.updateFilteredEvents(false);
      }
   }

   public void setCategoryFilter(String var1) {
      synchronized (this.mLock) {
         this.mCategoryFilter = var1.trim();
         this.updateFilteredEvents(false);
      }
   }

   public static boolean access$400(MyTableModel var0, EventDetails var1) {
      return var0.matchFilter(var1);
   }

   public void addEvent(EventDetails var1) {
      synchronized (this.mLock) {
         this.mPendingEvents.add(var1);
      }
   }

   public static Object access$000(MyTableModel var0) {
      return var0.mLock;
   }

   public void clear() {
      synchronized (this.mLock) {
         this.mAllEvents.clear();
         this.mFilteredEvents = new EventDetails[0];
         this.mPendingEvents.clear();
         this.fireTableDataChanged();
      }
   }

   public boolean isPaused() {
      synchronized (this.mLock) {
         return this.mPaused;
      }
   }

   public static boolean access$100(MyTableModel var0) {
      return var0.mPaused;
   }

   public boolean matchFilter(EventDetails var1) {
      if (var1.getPriority().isGreaterOrEqual(this.mPriorityFilter)
         && var1.getThreadName().indexOf(this.mThreadFilter) >= 0
         && var1.getCategoryName().indexOf(this.mCategoryFilter) >= 0
         && (this.mNDCFilter.length() == 0 || var1.getNDC() != null && var1.getNDC().indexOf(this.mNDCFilter) >= 0)) {
         String var2 = var1.getMessage();
         return var2 == null ? this.mMessageFilter.length() == 0 : var2.indexOf(this.mMessageFilter) >= 0;
      } else {
         return false;
      }
   }
}
