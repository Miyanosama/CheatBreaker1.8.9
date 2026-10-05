package junit.awtui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.SystemColor;

public class ProgressBar extends Canvas {
   public int fProgressX;
   public int fTotal;
   public int fProgress;
   public boolean fError = false;

   public void reset() {
      this.fProgressX = 1;
      this.fProgress = 0;
      this.fError = false;
      this.paint(this.getGraphics());
   }

   public void step(boolean var1) {
      this.fProgress++;
      int var2 = this.fProgressX;
      this.fProgressX = this.scale(this.fProgress);
      if (!this.fError && !var1) {
         this.fError = true;
         var2 = 1;
      }

      this.paintStep(var2, this.fProgressX);
   }

   public int scale(int var1) {
      return this.fTotal > 0 ? Math.max(1, var1 * (this.getBounds().width - 1) / this.fTotal) : var1;
   }

   public void paint(Graphics var1) {
      this.paintBackground(var1);
      this.paintStatus(var1);
   }

   public void setBounds(int var1, int var2, int var3, int var4) {
      super.setBounds(var1, var2, var3, var4);
      this.fProgressX = this.scale(this.fProgress);
   }

   public ProgressBar() {
      this.fTotal = 0;
      this.fProgress = 0;
      this.fProgressX = 0;
      this.setSize(20, 30);
   }

   public void paintStatus(Graphics var1) {
      var1.setColor(this.getStatusColor());
      Rectangle var2 = new Rectangle(0, 0, this.fProgressX, this.getBounds().height);
      var1.fillRect(1, 1, var2.width - 1, var2.height - 2);
   }

   public void paintStep(int var1, int var2) {
      this.repaint(var1, 1, var2 - var1, this.getBounds().height - 2);
   }

   public Color getStatusColor() {
      return this.fError ? Color.red : Color.green;
   }

   public void paintBackground(Graphics var1) {
      var1.setColor(SystemColor.control);
      Rectangle var2 = this.getBounds();
      var1.fillRect(0, 0, var2.width, var2.height);
      var1.setColor(Color.darkGray);
      var1.drawLine(0, 0, var2.width - 1, 0);
      var1.drawLine(0, 0, 0, var2.height - 1);
      var1.setColor(Color.white);
      var1.drawLine(var2.width - 1, 0, var2.width - 1, var2.height - 1);
      var1.drawLine(0, var2.height - 1, var2.width - 1, var2.height - 1);
   }

   public void start(int var1) {
      this.fTotal = var1;
      this.reset();
   }
}
