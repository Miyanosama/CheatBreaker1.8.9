package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.ClientResourceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.cheatbreaker.client.ui.overlay.element.InputFieldElement;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.element.type.CosmeticSelectionElement;

public class CosmeticsMenu extends MainMenuBase {
   public List<CosmeticSelectionElement> recoveredField61 = new ArrayList<>();
   public ResourceLocation recoveredField62;
   public int recoveredField63;
   public GradientTextButton recoveredField64;
   public ResourceLocation recoveredField65 = new ResourceLocation("client/icons/left.png");
   List<CosmeticSelectionElement> allCosmetics = new ArrayList<>();
   List<ClientResourceManager> previewOrder = new ArrayList<>();
   InputFieldElement searchField;
   private String searchQuery;
   private boolean previewOrderChanged = true;

   void updateSearchResults() {
      String query = searchField.getText().trim().replace('_', ' ').toLowerCase(Locale.ROOT);
      if (query.equals(searchQuery)) return;
      searchQuery = query;
      recoveredField61.clear();
      previewOrder.clear();
      for (CosmeticSelectionElement element : allCosmetics) {
         String name = element.recoveredField2291.method_20858().replace('_', ' ').toLowerCase(Locale.ROOT);
         if (name.contains(query)) {
            recoveredField61.add(element);
            previewOrder.add(element.recoveredField2291);
         }
      }
      recoveredField63 = 0;
      previewOrderChanged = true;
   }

   @Override
   public void initGui() {
      super.initGui();
      Keyboard.enableRepeatEvents(true);
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      searchField.handleElementUpdate();
   }

   @Override
   public void onGuiClosed() {
      searchField.method_06028(false);
      Keyboard.enableRepeatEvents(false);
      CheatBreaker.getInstance().method_19791().getPreviewCache()
         .setDisplayOrder(CheatBreaker.getInstance().method_19791().getLocalCosmetics().getCosmetics());
      super.onGuiClosed();
   }

   @Override
   public void keyTyped(char character, int key) throws java.io.IOException {
      if (searchField.method_06013()) {
         if (key == Keyboard.KEY_ESCAPE || key == Keyboard.KEY_RETURN || key == Keyboard.KEY_NUMPADENTER) {
            searchField.method_06028(false);
         } else {
            searchField.handleElementKeyTyped(character, key);
            updateSearchResults();
         }
         return;
      }
      super.keyTyped(character, key);
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      super.onMouseClicked(var1, var2, var3);
      if (this.j.currentScreen != this) return;
      searchField.handleElementMouseClicked(var1, var2, var3, true);
      updateSearchResults();
      if (searchField.a_(var1, var2)) return;
      if (this.recoveredField64.a_(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new MainMenu());
      } else {
         if (this.recoveredField61.size() > 5) {
            boolean var5 = var1 > this.getScaledWidth() / 2.0F + 34.0F
               && var1 < this.getScaledWidth() / 2.0F + 54.0F
               && var2 > this.getScaledHeight() / 2.0F + 80.0F
               && var2 < this.getScaledHeight() / 2.0F + 100.0F;
            boolean var4 = var1 > this.getScaledWidth() / 2.0F + 56.0F
               && var1 < this.getScaledWidth() / 2.0F + 76.0F
               && var2 > this.getScaledHeight() / 2.0F + 80.0F
               && var2 < this.getScaledHeight() / 2.0F + 100.0F;
            if (this.recoveredField63 > 0 && var5) {
               this.recoveredField63--;
               Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            } else if (var4 && this.recoveredField63 + 1 < this.recoveredField61.size() / 5.0F) {
               this.recoveredField63++;
               Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            }
         }

         int var7 = 0;

         for (CosmeticSelectionElement var6 : this.recoveredField61) {
            var7++;
            if (var7 - 1 >= this.recoveredField63 * 5 && var7 - 1 < (this.recoveredField63 + 1) * 5) {
               var6.handleMouseClick((int)var1, (int)var2, var3);
            }
         }
      }
   }

   public CosmeticsMenu() {
      this.recoveredField62 = new ResourceLocation("client/icons/right.png");
      this.recoveredField64 = new GradientTextButton("BACK");
      this.recoveredField63 = 0;

      for (ClientResourceManager var2 : CheatBreaker.getInstance().method_19791().getLocalCosmetics().getCosmetics()) {
         this.allCosmetics.add(new CosmeticSelectionElement(var2, 1.0F));
      }
      searchField = new InputFieldElement(CheatBreaker.getInstance().robotoRegular13px,
         "Search cosmetics...", 0x40808080, -1);
      searchField.trimToLength(128);
      updateSearchResults();
   }

   @Override
   public void drawMenu(float var1, float var2) {
      if (previewOrderChanged) {
         CheatBreaker.getInstance().method_19791().getPreviewCache().setDisplayOrder(previewOrder);
         previewOrderChanged = false;
      }
      CheatBreaker.getInstance().method_19791().getPreviewCache().beginFrame(this.recoveredField63);
      super.drawMenu(var1, var2);
      Gui.drawRect(
         this.getScaledWidth() / 2.0F - 80.0F,
         this.getScaledHeight() / 2.0F - 78.0F,
         this.getScaledWidth() / 2.0F + 80.0F,
         this.getScaledHeight() / 2.0F + 100.0F,
         788529152
      );
      this.recoveredField64.setElementSize(this.getScaledWidth() / 2.0F - 30.0F, this.getScaledHeight() / 2.0F + 105.0F, 60.0F, 12.0F);
      this.recoveredField64.drawElement(var1, var2, true);
      searchField.setElementSize(this.getScaledWidth() / 2.0F - 74.0F,
         this.getScaledHeight() / 2.0F + 82.0F, 104.0F, 14.0F);
      searchField.drawElement(var1, var2, true);
      if (this.recoveredField61.isEmpty()) {
         CheatBreaker.getInstance()
            .recoveredField1548
            .drawCenteredString("No matching cosmetics.", this.getScaledWidth() / 2.0F, this.getScaledHeight() / 2.0F + 4.0F, -6381922);
      } else {
         CheatBreaker.getInstance()
            .recoveredField1595
            .drawCenteredString("Cosmetics (" + this.recoveredField61.size() + ")", this.getScaledWidth() / 2.0F, this.getScaledHeight() / 2.0F - 90.0F, -1);
         int var3 = 0;
         float var4 = 0.0F;

         for (CosmeticSelectionElement var6 : this.recoveredField61) {
            var3++;
            if (var3 - 1 >= this.recoveredField63 * 5 && var3 - 1 < (this.recoveredField63 + 1) * 5) {
               var6.setDimensions((int)this.getScaledWidth() / 2 - 76, (int)(this.getScaledHeight() / 2.0F - 72.0F + var4), 152, var6.getHeight());
               var6.handleDrawElement((int)var1, (int)var2, 1.0F);
               var4 += var6.getHeight();
            }
         }

         if (this.recoveredField61.size() > 5) {
            boolean var7 = var1 > this.getScaledWidth() / 2.0F + 34.0F
               && var1 < this.getScaledWidth() / 2.0F + 54.0F
               && var2 > this.getScaledHeight() / 2.0F + 80.0F
               && var2 < this.getScaledHeight() / 2.0F + 100.0F;
            GL11.glColor4f(0.0F, 0.0F, 0.0F, var7 ? 0.45F : 0.25F);
            RenderUtil.drawIcon(this.recoveredField65, 4.0F, this.getScaledWidth() / 2.0F + 44.0F, this.getScaledHeight() / 2.0F + 84.0F);
            boolean var8 = var1 > this.getScaledWidth() / 2.0F + 56.0F
               && var1 < this.getScaledWidth() / 2.0F + 76.0F
               && var2 > this.getScaledHeight() / 2.0F + 80.0F
               && var2 < this.getScaledHeight() / 2.0F + 100.0F;
            GL11.glColor4f(0.0F, 0.0F, 0.0F, var8 ? 0.45F : 0.25F);
            RenderUtil.drawIcon(this.recoveredField62, 4.0F, this.getScaledWidth() / 2.0F + 66.0F, this.getScaledHeight() / 2.0F + 84.0F);
         }
      }

   }
}
