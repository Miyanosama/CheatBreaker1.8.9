package io.netty.util.internal.logging;

import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import org.slf4j.Logger;

public class Slf4JLogger extends AbstractInternalLogger {
   public transient Logger logger;
   public static final long serialVersionUID = 108038972685130825L;

   @Override
   public void debug(String var1, Object var2, Object var3) {
      this.logger.debug(var1, var2, var3);
   }

   @Override
   public void info(String var1, Object... var2) {
      this.logger.info(var1, var2);
   }

   @Override
   public void debug(String var1) {
      this.logger.method_02650(var1);
   }

   @Override
   public boolean isTraceEnabled() {
      return this.logger.isTraceEnabled();
   }

   @Override
   public void info(String var1, Object var2, Object var3) {
      this.logger.info(var1, var2, var3);
   }

   @Override
   public void trace(String var1, Object... var2) {
      this.logger.trace(var1, var2);
   }

   @Override
   public void trace(String var1) {
      this.logger.trace(var1);
   }

   @Override
   public boolean isWarnEnabled() {
      return this.logger.method_02637();
   }

   @Override
   public void trace(String var1, Throwable var2) {
      this.logger.trace(var1, var2);
   }

   @Override
   public void error(String var1, Object var2, Object var3) {
      this.logger.error(var1, var2, var3);
   }

   @Override
   public boolean isDebugEnabled() {
      return this.logger.method_02649();
   }

   @Override
   public void warn(String var1, Object var2, Object var3) {
      this.logger.warn(var1, var2, var3);
   }

   @Override
   public void debug(String var1, Object... var2) {
      this.logger.debug(var1, var2);
   }

   @Override
   public void trace(String var1, Object var2, Object var3) {
      this.logger.trace(var1, var2, var3);
   }

   @Override
   public void info(String var1, Object var2) {
      this.logger.info(var1, var2);
   }

   @Override
   public void error(String var1) {
      this.logger.error(var1);
   }

   @Override
   public void warn(String var1, Object var2) {
      this.logger.warn(var1, var2);
   }

   @Override
   public void trace(String var1, Object var2) {
      this.logger.trace(var1, var2);
   }

   @Override
   public void warn(String var1, Object... var2) {
      this.logger.warn(var1, var2);
   }

   @Override
   public boolean isErrorEnabled() {
      return this.logger.method_02622();
   }

   @Override
   public void warn(String var1) {
      this.logger.warn(var1);
   }

   @Override
   public void error(String var1, Object... var2) {
      this.logger.error(var1, var2);
   }

   public Slf4JLogger(Logger var1) {
      super(var1.getName());
      this.logger = var1;
   }

   @Override
   public void warn(String var1, Throwable var2) {
      this.logger.warn(var1, var2);
   }

   @Override
   public boolean isInfoEnabled() {
      return this.logger.method_02608();
   }

   @Override
   public void debug(String var1, Throwable var2) {
      this.logger.debug(var1, var2);
   }

   @Override
   public void error(String var1, Throwable var2) {
      this.logger.error(var1, var2);
   }

   @Override
   public void info(String var1, Throwable var2) {
      this.logger.info(var1, var2);
   }

   @Override
   public void debug(String var1, Object var2) {
      this.logger.debug(var1, var2);
   }

   @Override
   public void error(String var1, Object var2) {
      this.logger.error(var1, var2);
   }

   @Override
   public void info(String var1) {
      this.logger.info(var1);
   }
}
