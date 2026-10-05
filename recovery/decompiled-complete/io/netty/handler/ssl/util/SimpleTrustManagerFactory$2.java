package io.netty.handler.ssl.util;

import io.netty.util.concurrent.FastThreadLocal;
import net.minecraft.block.BlockMycelium;
import net.minecraft.client.renderer.entity.RenderFireball;
import org.apache.log4j.varia.Roller;

public class SimpleTrustManagerFactory$2 extends FastThreadLocal<SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi> {
   public RenderFireball __junk8588823792474684397;
   public Roller __junk3999802819925829289;
   public BlockMycelium __junk8392952929602096105;

   public SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi initialValue() {
      return new SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi();
   }
}
