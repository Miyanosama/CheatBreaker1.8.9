package io.netty.util.internal.logging;

import net.minecraft.world.gen.feature.WorldGenForest;
import org.apache.commons.logging.Log;
import org.apache.log4j.pattern.MessagePatternConverter;

public class CommonsLogger extends AbstractInternalLogger {
   public static long serialVersionUID;
   public transient Log logger;
   public MessagePatternConverter __junk2040704172480070747;
   public WorldGenForest __junk5705692182452055019;

   @Override
   public void debug(String var1, Object var2, Object var3) {
      if (this.logger.isDebugEnabled()) {
         FormattingTuple var4 = MessageFormatter.format(var1, var2, var3);
         this.logger.debug(var4.getMessage(), var4.getThrowable());
      }
   }

   @Override
   public void warn(String var1, Object var2) {
      if (this.logger.isWarnEnabled()) {
         FormattingTuple var3 = MessageFormatter.format(var1, var2);
         this.logger.warn(var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void info(String var1) {
      this.logger.info(var1);
   }

   @Override
   public void trace(String var1, Object... var2) {
      if (this.logger.isTraceEnabled()) {
         FormattingTuple var3 = MessageFormatter.arrayFormat(var1, var2);
         this.logger.trace(var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void warn(String var1, Throwable var2) {
      this.logger.warn(var1, var2);
   }

   @Override
   public void warn(String var1, Object... var2) {
      if (this.logger.isWarnEnabled()) {
         FormattingTuple var3 = MessageFormatter.arrayFormat(var1, var2);
         this.logger.warn(var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public boolean isInfoEnabled() {
      return this.logger.isInfoEnabled();
   }

   @Override
   public void error(String var1, Object var2) {
      if (this.logger.isErrorEnabled()) {
         FormattingTuple var3 = MessageFormatter.format(var1, var2);
         this.logger.error(var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void info(String var1, Object var2) {
      if (this.logger.isInfoEnabled()) {
         FormattingTuple var3 = MessageFormatter.format(var1, var2);
         this.logger.info(var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void error(String var1) {
      this.logger.error(var1);
   }

   @Override
   public void info(String var1, Object... var2) {
      if (this.logger.isInfoEnabled()) {
         FormattingTuple var3 = MessageFormatter.arrayFormat(var1, var2);
         this.logger.info(var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void warn(String var1) {
      this.logger.warn(var1);
   }

   @Override
   public void trace(String var1, Throwable var2) {
      this.logger.trace(var1, var2);
   }

   @Override
   public void debug(String var1, Object... var2) {
      if (this.logger.isDebugEnabled()) {
         FormattingTuple var3 = MessageFormatter.arrayFormat(var1, var2);
         this.logger.debug(var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void debug(String var1, Object var2) {
      if (this.logger.isDebugEnabled()) {
         FormattingTuple var3 = MessageFormatter.format(var1, var2);
         this.logger.debug(var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void trace(String var1, Object var2, Object var3) {
      if (this.logger.isTraceEnabled()) {
         FormattingTuple var4 = MessageFormatter.format(var1, var2, var3);
         this.logger.trace(var4.getMessage(), var4.getThrowable());
      }
   }

   public CommonsLogger(Log var1, String var2) {
      super(var2);
      if (var1 == null) {
         throw new NullPointerException("logger");
      } else {
         this.logger = var1;
      }
   }

   @Override
   public void error(String var1, Object... var2) {
      if (this.logger.isErrorEnabled()) {
         FormattingTuple var3 = MessageFormatter.arrayFormat(var1, var2);
         this.logger.error(var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public boolean isErrorEnabled() {
      return this.logger.isErrorEnabled();
   }

   @Override
   public boolean isDebugEnabled() {
      return this.logger.isDebugEnabled();
   }

   @Override
   public void warn(String var1, Object var2, Object var3) {
      if (this.logger.isWarnEnabled()) {
         FormattingTuple var4 = MessageFormatter.format(var1, var2, var3);
         this.logger.warn(var4.getMessage(), var4.getThrowable());
      }
   }

   @Override
   public void info(String var1, Throwable var2) {
      this.logger.info(var1, var2);
   }

   @Override
   public void error(String var1, Object var2, Object var3) {
      if (this.logger.isErrorEnabled()) {
         FormattingTuple var4 = MessageFormatter.format(var1, var2, var3);
         this.logger.error(var4.getMessage(), var4.getThrowable());
      }
   }

   @Override
   public void info(String var1, Object var2, Object var3) {
      if (this.logger.isInfoEnabled()) {
         FormattingTuple var4 = MessageFormatter.format(var1, var2, var3);
         this.logger.info(var4.getMessage(), var4.getThrowable());
      }
   }

   @Override
   public void debug(String var1) {
      this.logger.debug(var1);
   }

   @Override
   public void debug(String var1, Throwable var2) {
      this.logger.debug(var1, var2);
   }

   @Override
   public void trace(String var1, Object var2) {
      if (this.logger.isTraceEnabled()) {
         FormattingTuple var3 = MessageFormatter.format(var1, var2);
         this.logger.trace(var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public boolean isWarnEnabled() {
      return this.logger.isWarnEnabled();
   }

   @Override
   public boolean isTraceEnabled() {
      return this.logger.isTraceEnabled();
   }

   @Override
   public void trace(String var1) {
      this.logger.trace(var1);
   }

   @Override
   public void error(String var1, Throwable var2) {
      this.logger.error(var1, var2);
   }
}
