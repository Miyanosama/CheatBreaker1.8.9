package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.mainmenu.NewsMenu;
import net.minecraft.block.BlockHugeMushroom$1;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.command.server.CommandSummon;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomBlockLayers;

public class UnidentifiedClass1381 extends AbstractElement {
   public String field_0003;
   public CustomBlockLayers field_0006;
   public String field_0002;
   public CommandSummon field_0005;
   public String field_0000;
   public ColorFade field_0001 = new ColorFade(520093696, 1056964608);
   public UnidentifiedClass3556 field_0007;
   public BlockHugeMushroom$1 field_0004;

   public UnidentifiedClass1381(String var1, String var2, String var3) {
      this.field_0000 = var1;
      this.field_0003 = var2;
      this.field_0002 = var3;
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.field_0001.method_25066(this.a_(var1, var2) && var3).getRGB());
      String var4 = UnidentifiedClass0882.method_05899(this.field_0002).method_29697(60).method_29703(false).method_29693();
      String[] var5 = var4.split("\n");
      int var6 = 2;
      float var7 = CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F;
      CheatBreaker.getInstance().playRegular14px.drawString(EnumChatFormatting.BOLD + this.field_0000, 1.0F + this.x, this.y, -1);
      CheatBreaker.getInstance()
         .field_0063
         .drawString(EnumChatFormatting.ITALIC + "Posted by " + EnumChatFormatting.BOLD + this.field_0003, 1.0F + this.x, this.y + var7, -1);

      for (String var11 : var5) {
         if (var6 < 3) {
            if (var6 == 2) {
               var11 = var11 + "...";
            }

            float var10002 = 1.0F + this.x;
            CheatBreaker.getInstance().playRegular14px.drawString(var11, var10002, this.y + var6 * var7 - 1.0F, -1);
         }

         var6++;
      }
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (this.a_(var1, var2)) {
         this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.mc.displayGuiScreen(new NewsMenu(this.field_0000, this.field_0003, this.field_0002));
      }

      return false;
   }
}
