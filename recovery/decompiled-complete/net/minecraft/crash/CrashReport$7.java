package net.minecraft.crash;

import io.netty.handler.ssl.NotSslRecordException;
import java.util.concurrent.Callable;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityVillager$EmeraldForItems;
import net.minecraft.world.gen.layer.IntCache;
import net.optifine.entity.model.anim.RenderEntityParameterBool$1;
import net.optifine.render.VboRange;
import org.apache.log4j.PropertyConfigurator;

public class CrashReport$7 implements Callable<String> {
   public NotSslRecordException field_0003;
   public RenderEntityParameterBool$1 field_0005;
   public VboRange field_0004;
   public EntityVillager$EmeraldForItems field_0000;
   public PropertyConfigurator field_0001;
   public EntityCreeper field_0006;

   public CrashReport$7(CrashReport var1) {
      this.this$0 = var1;
      super();
   }

   public String call() {
      return IntCache.getCacheSizes();
   }
}
