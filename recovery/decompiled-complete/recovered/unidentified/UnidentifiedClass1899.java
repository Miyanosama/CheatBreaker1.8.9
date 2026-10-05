package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.ClientResourceManager;
import io.netty.handler.timeout.IdleState;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$BulkTask;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass1899 extends GuiMainMenu {
   public ResourceLocation field_0003;
   public ConcurrentHashMapV8$BulkTask field_0005;
   public IdleState field_0002;
   public ResourceLocation field_0004;
   public int field_0000;
   public List field_0001 = new ArrayList();

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      if (!CheatBreaker.getInstance().getAssetsWebSocket().isOpen()) {
         float var10002 = this.l / 2.0F;
         CheatBreaker.getInstance().field_0036.method_03191("Unable to connect to the server.", var10002, this.m / 2.0F - 10.0F, -1);
         var10002 = this.l / 2.0F;
         CheatBreaker.getInstance().field_0036.method_03191("Please try again later.", var10002, this.m / 2.0F + 4.0F, -1);
      } else {
         RenderUtil.method_22054(this.l / 2.0F - 80.0F, this.m / 2.0F - 78.0F, this.l / 2.0F + 80.0F, this.m / 2.0F + 100.0F, 14.0, -1342177281);
         if (this.field_0001.isEmpty()) {
            float var12 = this.l / 2.0F;
            CheatBreaker.getInstance().field_0036.drawCenteredString("You don't own any cosmetics.", var12, this.m / 2.0F + 4.0F, -6381922);
         } else {
            CheatBreaker.getInstance().field_0039.drawCenteredString("Cosmetics (" + this.field_0001.size() + ")", this.l / 2.0F, this.m / 2.0F - 90.0F, -1);
            int var4 = 0;
            float var5 = 0.0F;

            for (Object var7 : this.field_0001) {
               UnidentifiedClass0878 var8 = (UnidentifiedClass0878)var7;
               var4++;
               if (var4 - 1 >= this.field_0000 * 5 && var4 - 1 < (this.field_0000 + 1) * 5) {
                  var8.setDimensions(this.l / 2 - 76, (int)(this.m / 2 - 72 + var5), 152, var8.getHeight());
                  var8.handleDrawElement(var1, var2, var3);
                  var5 += var8.getHeight();
               }
            }

            if (this.field_0001.size() > 5) {
               boolean var9 = var1 > this.l / 2 - 40 && var1 < this.l / 2 - 1 && var2 > this.m / 2 + 80 && var2 < this.m / 2 + 100;
               GL11.glColor4f(0.0F, 0.0F, 0.0F, var9 ? 0.45F : 0.25F);
               RenderUtil.drawIcon(this.field_0004, 4.0F, this.l / 2 - 10, this.m / 2 + 84);
               boolean var10 = var1 > this.l / 2 + 1 && var1 < this.l / 2 + 40 && var2 > this.m / 2 + 80 && var2 < this.m / 2 + 100;
               GL11.glColor4f(0.0F, 0.0F, 0.0F, var10 ? 0.45F : 0.25F);
               RenderUtil.drawIcon(this.field_0003, 4.0F, this.l / 2 + 10, this.m / 2 + 84);
            }
         }
      }
   }

   public UnidentifiedClass1899() {
      this.field_0004 = new ResourceLocation("client/icons/left.png");
      this.field_0003 = new ResourceLocation("client/icons/right.png");
      this.field_0000 = 0;

      for (ClientResourceManager var2 : CheatBreaker.getInstance().method_19791().method_27046()) {
         this.field_0001.add(new UnidentifiedClass0878(var2, 1.0F));
      }
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      if (this.field_0001.size() > 5) {
         boolean var4 = var1 > this.l / 2 - 40 && var1 < this.l / 2 - 1 && var2 > this.m / 2 + 80 && var2 < this.m / 2 + 100;
         boolean var5 = var1 > this.l / 2 + 1 && var1 < this.l / 2 + 40 && var2 > this.m / 2 + 80 && var2 < this.m / 2 + 100;
         if (this.field_0000 > 0 && var4) {
            this.field_0000--;
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         } else if (var5 && this.field_0000 + 1 < this.field_0001.size() / 5.0F) {
            this.field_0000++;
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         }
      }

      int var7 = 0;

      for (Object var6 : this.field_0001) {
         var7++;
         if (var7 - 1 >= this.field_0000 * 5 && var7 - 1 < (this.field_0000 + 1) * 5) {
            ((UnidentifiedClass0878)var6).handleMouseClick(var1, var2, var3);
         }
      }
   }
}
