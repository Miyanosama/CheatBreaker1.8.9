package net.optifine.shaders.gui;

import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.util.internal.logging.JdkLoggerFactory;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.optifine.CustomGuiProperties$EnumContainer;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerLogRecordFilter;

public class GuiSlotShaders$1 implements GuiYesNoCallback {
   public CategoryExplorerLogRecordFilter field_0005;
   public CustomGuiProperties$EnumContainer field_0004;
   public JdkLoggerFactory field_0000;
   public BinaryWebSocketFrame field_0001;

   @Override
   public void confirmClicked(boolean var1, int var2) {
      if (var1) {
         GuiSlotShaders.access$000(this.this$0, this.val$theIndex);
      }

      GuiSlotShaders.access$100(this.this$0).displayGuiScreen(this.this$0.shadersGui);
   }

   public GuiSlotShaders$1(GuiSlotShaders var1, int var2) {
      this.this$0 = var1;
      this.val$theIndex = var2;
      super();
   }
}
