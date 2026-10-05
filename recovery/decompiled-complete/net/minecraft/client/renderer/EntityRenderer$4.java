package net.minecraft.client.renderer;

import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import io.netty.util.concurrent.FastThreadLocal;
import java.util.concurrent.Callable;
import net.minecraft.client.gui.ScaledResolution;
import net.optifine.entity.model.ModelAdapterHeadHumanoid;
import net.optifine.http.HttpPipeline;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor;

public class EntityRenderer$4 implements Callable<String> {
   public CBFontRenderer field_0003;
   public HttpPipeline field_0002;
   public CategoryNodeEditor field_0004;
   public FastThreadLocal field_0001;
   public ModelAdapterHeadHumanoid field_0006;

   public EntityRenderer$4(EntityRenderer var1, ScaledResolution var2) {
      this.this$0 = var1;
      this.val$scaledresolution = var2;
      super();
   }

   public String call() {
      return String.format(
         "Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d",
         this.val$scaledresolution.getScaledWidth(),
         this.val$scaledresolution.getScaledHeight(),
         EntityRenderer.access$000(this.this$0).displayWidth,
         EntityRenderer.access$000(this.this$0).displayHeight,
         this.val$scaledresolution.getScaleFactor()
      );
   }
}
