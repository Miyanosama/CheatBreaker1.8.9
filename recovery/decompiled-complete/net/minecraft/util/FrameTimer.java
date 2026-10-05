package net.minecraft.util;

import io.netty.handler.timeout.ReadTimeoutHandler;
import net.minecraft.client.multiplayer.GuiConnecting$1;
import org.apache.log4j.lf5.viewer.LogTableColumn;

public class FrameTimer {
   public ReadTimeoutHandler field_0003;
   public LogTableColumn field_0005;
   public long[] frames = new long[240];
   public int index;
   public int lastIndex;
   public GuiConnecting$1 field_0001;
   public int counter;

   public int getLastIndex() {
      return this.lastIndex;
   }

   public int parseIndex(int var1) {
      return var1 % 240;
   }

   public int getIndex() {
      return this.index;
   }

   public void addFrame(long var1) {
      this.frames[this.index] = var1;
      this.index++;
      if (this.index == 240) {
         this.index = 0;
      }

      if (this.counter < 240) {
         this.lastIndex = 0;
         this.counter++;
      } else {
         this.lastIndex = this.parseIndex(this.index + 1);
      }
   }

   public long[] getFrames() {
      return this.frames;
   }

   public int getLagometerValue(long var1, int var3) {
      double var4 = var1 / 1.6666666E7;
      return (int)(var4 * var3);
   }
}
