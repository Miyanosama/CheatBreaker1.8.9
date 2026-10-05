package net.minecraft.client.resources;

import com.google.common.base.Function;
import io.netty.handler.ssl.util.ThreadLocalInsecureRandom;
import javazoom.jl.decoder.LayerIIIDecoder$III_side_info_t;
import net.minecraft.client.renderer.BlockModelShapes$1;
import org.apache.log4j.chainsaw.ControlPanel;

public class SimpleReloadableResourceManager$1 implements Function<IResourcePack, String> {
   public BlockModelShapes$1 field_0004;
   public LayerIIIDecoder$III_side_info_t field_0001;
   public ThreadLocalInsecureRandom field_0003;
   public ControlPanel field_0000;

   public SimpleReloadableResourceManager$1(SimpleReloadableResourceManager var1) {
      this.field_0002 = var1;
      super();
   }

   public String apply(IResourcePack var1) {
      return var1.getPackName();
   }
}
