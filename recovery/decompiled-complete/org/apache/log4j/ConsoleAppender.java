package org.apache.log4j;

import javax.vecmath.VecMathI18N;
import net.minecraft.client.renderer.entity.RenderHorse;
import org.apache.log4j.helpers.LogLog;

public class ConsoleAppender extends WriterAppender {
   public VecMathI18N field_0005;
   public RenderHorse field_0000;
   public static String field_0002;
   public boolean follow;
   public static String field_0001;
   public String target = "System.out";

   public void targetWarn(String var1) {
      LogLog.warn("[" + var1 + "] should be System.out or System.err.");
      LogLog.warn("Using previously set target, System.out by default.");
   }

   public void closeWriter() {
      if (this.follow) {
         super.closeWriter();
      }
   }

   public void setFollow(boolean var1) {
      this.follow = var1;
   }

   public ConsoleAppender() {
      this.follow = false;
   }

   public void activateOptions() {
      if (this.follow) {
         if (this.target.equals("System.err")) {
            this.setWriter(this.createWriter(new ConsoleAppender$SystemErrStream()));
         } else {
            this.setWriter(this.createWriter(new ConsoleAppender$SystemOutStream()));
         }
      } else if (this.target.equals("System.err")) {
         this.setWriter(this.createWriter(System.err));
      } else {
         this.setWriter(this.createWriter(System.out));
      }

      super.activateOptions();
   }

   public ConsoleAppender(Layout var1, String var2) {
      this.follow = false;
      this.setLayout(var1);
      this.setTarget(var2);
      this.activateOptions();
   }

   public boolean getFollow() {
      return this.follow;
   }

   public void setTarget(String var1) {
      String var2 = var1.trim();
      if ("System.out".equalsIgnoreCase(var2)) {
         this.target = "System.out";
      } else if ("System.err".equalsIgnoreCase(var2)) {
         this.target = "System.err";
      } else {
         this.targetWarn(var1);
      }
   }

   public ConsoleAppender(Layout var1) {
      this(var1, "System.out");
   }

   public String getTarget() {
      return this.target;
   }
}
