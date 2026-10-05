package net.minecraft.client.renderer.texture;

import com.cheatbreaker.client.ui.element.type.custom.GlobalSettingsElement;
import java.util.concurrent.Callable;
import net.minecraft.network.play.client.C14PacketTabComplete;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor;

public class TextureMap$1 implements Callable<String> {
   public GlobalSettingsElement field_0002;
   public CategoryNodeEditor field_0004;
   public C14PacketTabComplete field_0001;

   public String method_24024() {
      return this.field_0003.getIconName();
   }

   public TextureMap$1(TextureMap var1, TextureAtlasSprite var2) {
      this.field_0000 = var1;
      this.field_0003 = var2;
      super();
   }
}
