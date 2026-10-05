package org.apache.log4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import net.minecraft.client.renderer.GlStateManager$BlendState;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class JDBCAppender extends AppenderSkeleton implements Appender {
   public Connection connection;
   public ArrayList buffer;
   public String sqlStatement;
   public int bufferSize;
   public GlStateManager$BlendState field_0003;
   public boolean locationInfo;
   public ArrayList removes;
   public String databaseURL = "jdbc:odbc:myDB";
   public String databasePassword;
   public String databaseUser = "me";

   public String getURL() {
      return this.databaseURL;
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public void closeConnection(Connection var1) {
   }

   public boolean requiresLayout() {
      return true;
   }

   public String getUser() {
      return this.databaseUser;
   }

   public void close() {
      this.flushBuffer();

      try {
         if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
         }
      } catch (SQLException var2) {
         this.errorHandler.error("Error closing connection", var2, 0);
      }

      this.closed = true;
   }

   public void execute(String var1) {
      Connection var2 = null;
      Statement var3 = null;

      try {
         var2 = this.getConnection();
         var3 = var2.createStatement();
         var3.executeUpdate(var1);
      } finally {
         if (var3 != null) {
            var3.close();
         }

         this.closeConnection(var2);
      }
   }

   public void flushBuffer() {
      this.removes.ensureCapacity(this.buffer.size());

      for (LoggingEvent var2 : this.buffer) {
         try {
            String var3 = this.getLogStatement(var2);
            this.execute(var3);
         } catch (SQLException var7) {
            this.errorHandler.error("Failed to excute sql", var7, 2);
         } finally {
            this.removes.add(var2);
         }
      }

      this.buffer.removeAll(this.removes);
      this.removes.clear();
   }

   public String getLogStatement(LoggingEvent var1) {
      return this.getLayout().format(var1);
   }

   public void finalize() {
      this.close();
   }

   public JDBCAppender() {
      this.databasePassword = "mypassword";
      this.connection = null;
      this.sqlStatement = "";
      this.bufferSize = 1;
      this.locationInfo = false;
      this.buffer = new ArrayList(this.bufferSize);
      this.removes = new ArrayList(this.bufferSize);
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public void setPassword(String var1) {
      this.databasePassword = var1;
   }

   public void setUser(String var1) {
      this.databaseUser = var1;
   }

   public void setSql(String var1) {
      this.sqlStatement = var1;
      if (this.getLayout() == null) {
         this.setLayout(new PatternLayout(var1));
      } else {
         ((PatternLayout)this.getLayout()).setConversionPattern(var1);
      }
   }

   public String getSql() {
      return this.sqlStatement;
   }

   public Connection getConnection() {
      if (!DriverManager.getDrivers().hasMoreElements()) {
         this.setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
      }

      if (this.connection == null) {
         this.connection = DriverManager.getConnection(this.databaseURL, this.databaseUser, this.databasePassword);
      }

      return this.connection;
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public void setURL(String var1) {
      this.databaseURL = var1;
   }

   public void append(LoggingEvent var1) {
      var1.getNDC();
      var1.getThreadName();
      var1.getMDCCopy();
      if (this.locationInfo) {
         var1.getLocationInformation();
      }

      var1.getRenderedMessage();
      var1.getThrowableStrRep();
      this.buffer.add(var1);
      if (this.buffer.size() >= this.bufferSize) {
         this.flushBuffer();
      }
   }

   public void setBufferSize(int var1) {
      this.bufferSize = var1;
      this.buffer.ensureCapacity(this.bufferSize);
      this.removes.ensureCapacity(this.bufferSize);
   }

   public String getPassword() {
      return this.databasePassword;
   }

   public void setDriver(String var1) {
      try {
         Class.forName(var1);
      } catch (Exception var3) {
         this.errorHandler.error("Failed to load driver", var3, 0);
      }
   }
}
