package org.apache.log4j.lf5;

import io.netty.util.internal.logging.MessageFormatter;
import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.material.MaterialPortal;
import recovered.unidentified.UnidentifiedClass0491;
import recovered.unidentified.UnidentifiedClass0806;

public class LogLevel implements Serializable {
   public static Map _logLevelColorMap;
   public static LogLevel WARN = new LogLevel("WARN", 2);
   public static LogLevel WARNING = new LogLevel("WARNING", 2);
   public MessageFormatter field_0017;
   public static Map _logLevelMap = new HashMap();
   public static LogLevel FINEST = new LogLevel("FINEST", 7);
   public static LogLevel[] _jdk14Levels = new LogLevel[]{LogLevel.SEVERE, WARNING, LogLevel.INFO, LogLevel.CONFIG, LogLevel.FINE, LogLevel.FINER, FINEST};
   public static LogLevel ERROR = new LogLevel("ERROR", 1);
   public static LogLevel[] _log4JLevels = new LogLevel[]{LogLevel.FATAL, ERROR, WARN, LogLevel.INFO, LogLevel.DEBUG};
   public MaterialPortal field_0022;
   public static LogLevel SEVERE = new LogLevel("SEVERE", 1);
   public static LogLevel FINER = new LogLevel("FINER", 6);
   public static LogLevel CONFIG = new LogLevel("CONFIG", 4);
   public static LogLevel[] _allDefaultLevels = new LogLevel[]{
      LogLevel.FATAL, ERROR, WARN, LogLevel.INFO, LogLevel.DEBUG, SEVERE, WARNING, CONFIG, LogLevel.FINE, FINER, FINEST
   };
   public int _precedence;
   public static LogLevel INFO = new LogLevel("INFO", 3);
   public static LogLevel FINE = new LogLevel("FINE", 5);
   public UnidentifiedClass0806 field_0008;
   public String _label;
   public static LogLevel DEBUG = new LogLevel("DEBUG", 4);
   public static Map _registeredLogLevelMap = new HashMap();
   public static LogLevel FATAL = new LogLevel("FATAL", 0);
   public UnidentifiedClass0491 field_0003;

   public LogLevel(String var1, int var2) {
      this._label = var1;
      this._precedence = var2;
   }

   public static void resetLogLevelColorMap() {
      _logLevelColorMap.clear();

      for (int var0 = 0; var0 < _allDefaultLevels.length; var0++) {
         _logLevelColorMap.put(_allDefaultLevels[var0], Color.black);
      }
   }

   public String toString() {
      return this._label;
   }

   public static void register(List var0) {
      if (var0 != null) {
         Iterator var1 = var0.iterator();

         while (var1.hasNext()) {
            register((LogLevel)var1.next());
         }
      }
   }

   public void setLogLevelColorMap(LogLevel var1, Color var2) {
      _logLevelColorMap.remove(var1);
      if (var2 == null) {
         var2 = Color.black;
      }

      _logLevelColorMap.put(var1, var2);
   }

   public static List getJdk14Levels() {
      return Arrays.asList(_jdk14Levels);
   }

   public static Map getLogLevelColorMap() {
      return _logLevelColorMap;
   }

   public static List getLog4JLevels() {
      return Arrays.asList(_log4JLevels);
   }

   public static LogLevel valueOf(String var0) {
      LogLevel var1 = null;
      if (var0 != null) {
         var0 = var0.trim().toUpperCase();
         var1 = (LogLevel)_logLevelMap.get(var0);
      }

      if (var1 == null && _registeredLogLevelMap.size() > 0) {
         var1 = (LogLevel)_registeredLogLevelMap.get(var0);
      }

      if (var1 == null) {
         StringBuffer var2 = new StringBuffer();
         var2.append("Error while trying to parse (" + var0 + ") into");
         var2.append(" a LogLevel.");
         throw new LogLevelFormatException(var2.toString());
      } else {
         return var1;
      }
   }

   public int hashCode() {
      return this._label.hashCode();
   }

   public int getPrecedence() {
      return this._precedence;
   }

   public boolean encompasses(LogLevel var1) {
      return var1.getPrecedence() <= this.getPrecedence();
   }

   public static void register(LogLevel[] var0) {
      if (var0 != null) {
         for (int var1 = 0; var1 < var0.length; var1++) {
            register(var0[var1]);
         }
      }
   }

   public static LogLevel register(LogLevel var0) {
      if (var0 == null) {
         return null;
      } else {
         return _logLevelMap.get(var0.getLabel()) == null ? _registeredLogLevelMap.put(var0.getLabel(), var0) : null;
      }
   }

   public static List getAllDefaultLevels() {
      return Arrays.asList(_allDefaultLevels);
   }

   public boolean equals(Object var1) {
      boolean var2 = false;
      if (var1 instanceof LogLevel && this.getPrecedence() == ((LogLevel)var1).getPrecedence()) {
         var2 = true;
      }

      return var2;
   }

   public String getLabel() {
      return this._label;
   }

   static {
      for (int var0 = 0; var0 < _allDefaultLevels.length; var0++) {
         _logLevelMap.put(_allDefaultLevels[var0].getLabel(), _allDefaultLevels[var0]);
      }

      _logLevelColorMap = new HashMap();

      for (int var1 = 0; var1 < _allDefaultLevels.length; var1++) {
         _logLevelColorMap.put(_allDefaultLevels[var1], Color.black);
      }
   }
}
