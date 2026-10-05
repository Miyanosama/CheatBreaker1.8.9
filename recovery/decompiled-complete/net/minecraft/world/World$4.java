package net.minecraft.world;

import java.util.concurrent.Callable;
import org.apache.log4j.lf5.viewer.LogTableColumn;
import recovered.unidentified.UnidentifiedEnum0393;

public class World$4 implements Callable<String> {
   public UnidentifiedEnum0393 field_0001;
   public LogTableColumn field_0000;

   public String call() {
      return this.field_151308_a.v.makeString();
   }

   public World$4(World var1) {
      this.field_151308_a = var1;
      super();
   }
}
