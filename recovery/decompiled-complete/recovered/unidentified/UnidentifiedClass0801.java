package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.module.type.armourstatus.ArmourStatusModule;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureOceanMonument$StartMonument;
import net.optifine.util.CounterInt;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass0801 extends AbstractModulesGuiElement {
   public StructureOceanMonument$StartMonument field_0001;
   public List<Integer> field_0002;
   public CounterInt field_0000;
   public GuiSnooper field_0003;
   public String field_0004;

   public UnidentifiedClass0801(List<Integer> var1, String var2, float var3) {
      super(var3);
      this.height = 220;
      this.field_0002 = var1;
      this.field_0004 = var2;
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      CheatBreaker.getInstance()
         .field_0068
         .drawString(
            this.field_0004.toUpperCase(),
            this.x + 10,
            this.y + 2,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
         );
      Minecraft var4 = Minecraft.getMinecraft();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(32826);
      RenderHelper.enableStandardItemLighting();
      RenderHelper.enableGUIStandardItemLighting();
      int var5 = 0;
      int var6 = 0;

      for (Block var8 : Block.blockRegistry) {
         Item var9 = Item.getItemFromBlock(var8);
         if (var9 != null) {
            if (var5 >= 15) {
               var5 = 0;
               var6++;
            }

            int var10 = this.x + 12 + var5 * 20;
            int var11 = this.y + 14 + var6 * 20;
            boolean var12 = var1 > (var10 - 2) * this.scale
               && var1 < (var10 + 18) * this.scale
               && var2 > (var11 - 2 + this.yOffset) * this.scale
               && var2 < (var11 + 18 + this.yOffset) * this.scale;
            if (this.field_0002.contains(Item.getIdFromItem(var9))) {
               Gui.a(var10 - 2, var11 - 2, var10 + 18, var11 + 18, 2130771712);
            } else if (var12) {
               Gui.a(var10 - 2, var11 - 2, var10 + 18, var11 + 18, 1325400319);
            }

            ArmourStatusModule.renderItem.renderItemAndEffectIntoGUI(new ItemStack(var9), var10, var11);
            var5++;
         }
      }

      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(32826);
      GL11.glDisable(3042);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      try {
         int var4 = 0;
         int var5 = 0;

         for (Block var7 : Block.blockRegistry) {
            Item var8 = Item.getItemFromBlock(var7);
            if (var8 != null) {
               if (var4 >= 15) {
                  var4 = 0;
                  var5++;
               }

               int var9 = this.x + 12 + var4 * 20;
               int var10 = this.y + 14 + var5 * 20;
               boolean var11 = var1 > (var9 - 2) * this.scale
                  && var1 < (var9 + 18) * this.scale
                  && var2 > (var10 - 2 + this.yOffset) * this.scale
                  && var2 < (var10 + 18 + this.yOffset) * this.scale;
               if (var11 && var3 == 0) {
                  int var12 = Item.getIdFromItem(var8);
                  if (this.field_0002.contains(var12)) {
                     this.field_0002.removeIf(var1x -> var1x == var12);
                  } else {
                     this.field_0002.add(var12);
                  }

                  if (CheatBreaker.getInstance().getModuleManager().xray.isEnabled()) {
                     Minecraft.getMinecraft().renderGlobal.loadRenderers();
                  }

                  Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
               }

               var4++;
            }
         }
      } catch (Exception var13) {
         var13.printStackTrace();
      }
   }
}
