package com.cheatbreaker.client.module.type;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.creativetab.CreativeTabs$6;
import net.minecraft.network.status.server.S01PacketPong;
import org.apache.log4j.lf5.viewer.LogTableRowRenderer;
import recovered.unidentified.UnidentifiedClass4439;

public class MemoryUsageModule extends NumberHudModule {
   public S01PacketPong field_0001;
   public LogTableRowRenderer field_0000;
   public CreativeTabs$6 field_0002;
   public ModelSpider field_0003;

   @Override
   public String method_00167() {
      long var1 = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
         * (553910380L & 8121541472962038004L)
         / Runtime.getRuntime().maxMemory();
      return this.method_09815(this.field_0003, (int)var1, this.field_0004.method_08912()) ? null : var1 + "%";
   }

   @Override
   public String method_21178() {
      return "%LABEL%: %VALUE%";
   }

   @Override
   public UnidentifiedClass4439 method_00168() {
      return new UnidentifiedClass4439(0, 50, 100);
   }

   @Override
   public String method_00166() {
      return "Mem";
   }

   public MemoryUsageModule() {
      super("Memory Usage", "[Mem: 19%]");
      this.method_28821("Displays how much memory the game is using.");
   }

   @Override
   public String method_00164() {
      return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) * (1850212453L & 6205288347761487972L) / Runtime.getRuntime().maxMemory()
         + "%";
   }
}
