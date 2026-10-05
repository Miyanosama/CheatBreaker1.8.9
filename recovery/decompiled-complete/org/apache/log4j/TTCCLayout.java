package org.apache.log4j;

import net.minecraft.command.server.CommandSummon;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleXRoom;
import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.spi.LoggingEvent;
import recovered.unidentified.UnidentifiedClass1812;

public class TTCCLayout extends DateLayout {
   public UnidentifiedClass1812 field_0003;
   public EntityItemFrame field_0006;
   public StringBuffer buf;
   public CommandSummon field_0005;
   public boolean threadPrinting = true;
   public boolean categoryPrefixing = true;
   public StructureOceanMonumentPieces$DoubleXRoom field_0007;
   public boolean contextPrinting = true;

   public void setContextPrinting(boolean var1) {
      this.contextPrinting = var1;
   }

   public void setCategoryPrefixing(boolean var1) {
      this.categoryPrefixing = var1;
   }

   public String format(LoggingEvent var1) {
      this.buf.setLength(0);
      this.dateFormat(this.buf, var1);
      if (this.threadPrinting) {
         this.buf.append('[');
         this.buf.append(var1.getThreadName());
         this.buf.append("] ");
      }

      this.buf.append(var1.getLevel().toString());
      this.buf.append(' ');
      if (this.categoryPrefixing) {
         this.buf.append(var1.getLoggerName());
         this.buf.append(' ');
      }

      if (this.contextPrinting) {
         String var2 = var1.getNDC();
         if (var2 != null) {
            this.buf.append(var2);
            this.buf.append(' ');
         }
      }

      this.buf.append("- ");
      this.buf.append(var1.getRenderedMessage());
      this.buf.append(LINE_SEP);
      return this.buf.toString();
   }

   public boolean getThreadPrinting() {
      return this.threadPrinting;
   }

   public TTCCLayout() {
      this.buf = new StringBuffer(256);
      this.setDateFormat("RELATIVE", null);
   }

   public boolean getCategoryPrefixing() {
      return this.categoryPrefixing;
   }

   public boolean ignoresThrowable() {
      return true;
   }

   public void setThreadPrinting(boolean var1) {
      this.threadPrinting = var1;
   }

   public TTCCLayout(String var1) {
      this.buf = new StringBuffer(256);
      this.setDateFormat(var1);
   }

   public boolean getContextPrinting() {
      return this.contextPrinting;
   }
}
