package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.ClientResourceManager;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0878;

public class CosmeticsMenu extends MainMenuBase {
   public PropertyBool field_0004;
   public List<UnidentifiedClass0878> field_0003 = new ArrayList<>();
   public ResourceLocation field_0002;
   public int field_0001;
   public GradientTextButton field_0000;
   public ResourceLocation field_0005 = new ResourceLocation("client/icons/left.png");

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      super.onMouseClicked(var1, var2, var3);
      if (this.field_0000.a_(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new MainMenu());
      } else {
         if (this.field_0003.size() > 5) {
            boolean var5 = var1 > this.getScaledWidth() / 2.0F - 40.0F
               && var1 < this.getScaledWidth() / 2.0F - 1.0F
               && var2 > this.getScaledHeight() / 2.0F + 80.0F
               && var2 < this.getScaledHeight() / 2.0F + 100.0F;
            boolean var4 = var1 > this.getScaledWidth() / 2.0F + 1.0F
               && var1 < this.getScaledWidth() / 2.0F + 40.0F
               && var2 > this.getScaledHeight() / 2.0F + 80.0F
               && var2 < this.getScaledHeight() / 2.0F + 100.0F;
            if (this.field_0001 > 0 && var5) {
               this.field_0001--;
               Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            } else if (var4 && this.field_0001 + 1 < this.field_0003.size() / 5.0F) {
               this.field_0001++;
               Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            }
         }

         int var7 = 0;

         for (UnidentifiedClass0878 var6 : this.field_0003) {
            var7++;
            if (var7 - 1 >= this.field_0001 * 5 && var7 - 1 < (this.field_0001 + 1) * 5) {
               var6.handleMouseClick((int)var1, (int)var2, var3);
            }
         }
      }
   }

   public CosmeticsMenu() {
      this.field_0002 = new ResourceLocation("client/icons/right.png");
      this.field_0000 = new GradientTextButton("BACK");
      this.field_0001 = 0;

      for (ClientResourceManager var2 : CheatBreaker.getInstance().method_19791().method_27046()) {
         this.field_0003.add(new UnidentifiedClass0878(var2, 1.0F));
      }
   }

   @Override
   public void drawMenu(float var1, float var2) {
      super.drawMenu(var1, var2);
      if (!CheatBreaker.getInstance().getAssetsWebSocket().isOpen()) {
         CheatBreaker.getInstance()
            .field_0036
            .method_03191("Unable to connect to the server.", this.getScaledWidth() / 2.0F, this.getScaledHeight() / 2.0F - 10.0F, -1);
         CheatBreaker.getInstance().field_0036.method_03191("Please try again later.", this.getScaledWidth() / 2.0F, this.getScaledHeight() / 2.0F + 4.0F, -1);
         this.field_0000.setElementSize(this.getScaledWidth() / 2.0F - 30.0F, this.getScaledHeight() / 2.0F + 28.0F, 60.0F, 12.0F);
         this.field_0000.drawElement(var1, var2, true);
      } else {
         Gui.drawRect(
            this.getScaledWidth() / 2.0F - 80.0F,
            this.getScaledHeight() / 2.0F - 78.0F,
            this.getScaledWidth() / 2.0F + 80.0F,
            this.getScaledHeight() / 2.0F + 100.0F,
            788529152
         );
         this.field_0000.setElementSize(this.getScaledWidth() / 2.0F - 30.0F, this.getScaledHeight() / 2.0F + 105.0F, 60.0F, 12.0F);
         this.field_0000.drawElement(var1, var2, true);
         if (this.field_0003.isEmpty()) {
            CheatBreaker.getInstance()
               .field_0036
               .drawCenteredString("You don't own any cosmetics.", this.getScaledWidth() / 2.0F, this.getScaledHeight() / 2.0F + 4.0F, -6381922);
         } else {
            CheatBreaker.getInstance()
               .field_0039
               .drawCenteredString("Cosmetics (" + this.field_0003.size() + ")", this.getScaledWidth() / 2.0F, this.getScaledHeight() / 2.0F - 90.0F, -1);
            int var3 = 0;
            float var4 = 0.0F;

            for (UnidentifiedClass0878 var6 : this.field_0003) {
               var3++;
               if (var3 - 1 >= this.field_0001 * 5 && var3 - 1 < (this.field_0001 + 1) * 5) {
                  var6.setDimensions((int)this.getScaledWidth() / 2 - 76, (int)(this.getScaledHeight() / 2.0F - 72.0F + var4), 152, var6.getHeight());
                  var6.handleDrawElement((int)var1, (int)var2, 1.0F);
                  var4 += var6.getHeight();
               }
            }

            if (this.field_0003.size() > 5) {
               boolean var7 = var1 > this.getScaledWidth() / 2.0F - 40.0F
                  && var1 < this.getScaledWidth() / 2.0F - 1.0F
                  && var2 > this.getScaledHeight() / 2.0F + 80.0F
                  && var2 < this.getScaledHeight() / 2.0F + 100.0F;
               GL11.glColor4f(0.0F, 0.0F, 0.0F, var7 ? 0.45F : 0.25F);
               RenderUtil.drawIcon(this.field_0005, 4.0F, this.getScaledWidth() / 2.0F - 10.0F, this.getScaledHeight() / 2.0F + 84.0F);
               boolean var8 = var1 > this.getScaledWidth() / 2.0F + 1.0F
                  && var1 < this.getScaledWidth() / 2.0F + 40.0F
                  && var2 > this.getScaledHeight() / 2.0F + 80.0F
                  && var2 < this.getScaledHeight() / 2.0F + 100.0F;
               GL11.glColor4f(0.0F, 0.0F, 0.0F, var8 ? 0.45F : 0.25F);
               RenderUtil.drawIcon(this.field_0002, 4.0F, this.getScaledWidth() / 2.0F + 10.0F, this.getScaledHeight() / 2.0F + 84.0F);
            }
         }
      }
   }
}
