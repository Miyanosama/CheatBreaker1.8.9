package net.minecraft.server;

import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$Segment;
import junit.framework.TestSuite;
import net.minecraft.util.IProgressUpdate;
import recovered.unidentified.UnidentifiedClass5100;

public class MinecraftServer$1 implements IProgressUpdate {
   public CBFontRenderer field_0003;
   public TestSuite field_0005;
   public long startTime;
   public ConcurrentHashMapV8$Segment field_0000;
   public UnidentifiedClass5100 field_0001;

   @Override
   public void setLoadingProgress(int var1) {
      if (System.currentTimeMillis() - this.startTime >= (738265064L & 1100631036L)) {
         this.startTime = System.currentTimeMillis();
         MinecraftServer.access$000().info("Converting... " + var1 + "%");
      }
   }

   @Override
   public void displayLoadingString(String var1) {
   }

   @Override
   public void setDoneWorking() {
   }

   @Override
   public void resetProgressAndMessage(String var1) {
   }

   public MinecraftServer$1(MinecraftServer var1) {
      this.field_0002 = var1;
      super();
      this.startTime = System.currentTimeMillis();
   }

   @Override
   public void displaySavingString(String var1) {
   }
}
