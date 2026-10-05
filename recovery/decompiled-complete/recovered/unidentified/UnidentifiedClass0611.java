package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.ModuleManager;
import com.cheatbreaker.client.ui.element.module.ModuleListElement;
import com.cheatbreaker.client.ui.element.module.ModulePreviewElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import io.netty.handler.codec.spdy.SpdyHttpResponseStreamIdHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPageButtonList$GuiLabelEntry;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.optifine.shaders.uniform.ShaderParameterIndexed;
import org.apache.log4j.PropertyConfigurator;
import org.java_websocket.server.SSLParametersWebSocketServerFactory;

public class UnidentifiedClass0611 {
   public SpdyHttpResponseStreamIdHandler field_0003;
   public GuiPageButtonList$GuiLabelEntry field_0006;
   public InventoryCrafting field_0002;
   public WorldGenAbstractTree field_0005;
   public SSLParametersWebSocketServerFactory field_0000;
   public ShaderParameterIndexed field_0001;
   public BiomeGenBase$SpawnListEntry field_0007;
   public PropertyConfigurator field_0004;

   public void method_04442() {
      CBModulesGui var1 = new CBModulesGui();
      Minecraft.getMinecraft().displayGuiScreen(var1);
      var1.currentScrollableElement = var1.field_0029;
      ((ModuleListElement)CBModulesGui.instance.field_0029).field_0013 = true;
      CBModulesGui.instance.field_0029.field_0013 = CheatBreaker.getInstance().getModuleManager().field_0017;
   }

   public void method_04444(AbstractModule var1) {
      ((ModuleListElement)CBModulesGui.instance.field_0029).field_0013 = false;
      ((ModuleListElement)CBModulesGui.instance.field_0029).scrollable = ModulePreviewElement.field_0010.field_0005;
      ((ModuleListElement)CBModulesGui.instance.field_0029).module = var1;
      CBModulesGui.instance.field_0029.field_0013 = CheatBreaker.getInstance().getModuleManager().field_0017;
      CBModulesGui.instance.currentScrollableElement = CBModulesGui.instance.field_0029;
   }

   public void method_04443(String var1) {
      ModuleManager var2 = CheatBreaker.getInstance().getModuleManager();
      var2.keyStrokes.initialize();
      CBModulesGui var3 = new CBModulesGui();
      Minecraft.getMinecraft().displayGuiScreen(var3);
      var3.currentScrollableElement = var3.field_0017;
      this.method_04444(var2.method_21669(var1));
   }
}
