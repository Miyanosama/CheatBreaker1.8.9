package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.element.IconButtonElement;
import com.cheatbreaker.client.util.ClientResourceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.cheatbreaker.client.ui.overlay.element.InputFieldElement;
import org.lwjgl.input.Keyboard;
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
   private net.minecraft.client.gui.GuiScreen returnScreen;
   private boolean previewOrderChanged = true;

   void updateSearchResults() {
      String query = searchField.getText().trim().replace('_', ' ').toLowerCase(Locale.ROOT);
      if (query.equals(searchQuery)) return;
      searchQuery = query;
      recoveredField61.clear();
      previewOrder.clear();
      for (CosmeticSelectionElement element : allCosmetics) {
         String name = element.recoveredField2291.method_20858().replace('_', ' ').toLowerCase(Locale.ROOT);
         if ((selectedType == null || element.recoveredField2291.method_20848() == selectedType) && name.contains(query)) {
            recoveredField61.add(element);
            previewOrder.add(element.recoveredField2291);
         }
      }
      recoveredField63 = 0;
      previewOrderChanged = true;
   }

   @Override
   public void renderSkybox(int mouseX, int mouseY, float partialTicks) {
      if (returnScreen == null) super.renderSkybox(mouseX, mouseY, partialTicks);
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
   public void a_() {
      searchField.method_06028(false);
      Keyboard.enableRepeatEvents(false);
      CheatBreaker.getInstance().method_19791().getPreviewCache().prepareModelCape(null);
      CheatBreaker.getInstance().method_19791().getPreviewCache()
         .setDisplayOrder(CheatBreaker.getInstance().method_19791().getLocalCosmetics().getCosmetics());
      super.a_();
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
      if (key == Keyboard.KEY_ESCAPE && returnScreen != null) {
         j.displayGuiScreen(returnScreen);
         return;
      }
      super.keyTyped(character, key);
   }

   private com.cheatbreaker.client.util.cosmetic.CosmeticType selectedType;
   private final GradientTextButton wingsButton = new GradientTextButton("WINGS");
   private final GradientTextButton capesButton = new GradientTextButton("CAPES");
   private final IconButtonElement previousPageButton = new IconButtonElement(recoveredField65);
   private final IconButtonElement nextPageButton = new IconButtonElement(new ResourceLocation("client/icons/right.png"));
   private final CosmeticPlayerPreview playerPreview = new CosmeticPlayerPreview();
   private boolean dragging;
   private float lastDragX;
   private float yaw = 155F;
   static final int PAGE_SIZE = 10;

   private float contentScale() {
      return Math.min(1F, Math.min((getScaledWidth() - 24F) / 500F, (getScaledHeight() - 85F) / 235F));
   }

   private float localX(float x) { return (x - getScaledWidth() / 2F) / contentScale() + 250F; }
   private float localY(float y) { return (y - getScaledHeight() / 2F) / contentScale() + 95F; }

   private void layout() {
      recoveredField64.setElementSize(220F, 220F, 60F, 12F);
      searchField.setElementSize(15F, 179F, 140F, 15F);
      wingsButton.setElementSize(370F, 183F, 50F, 12F);
      capesButton.setElementSize(425F, 183F, 50F, 12F);
      previousPageButton.setElementSize(235F, 179F, 15F, 15F);
      nextPageButton.setElementSize(260F, 179F, 15F, 15F);
      int first = recoveredField63 * PAGE_SIZE;
      for (int i = first; i < Math.min(first + PAGE_SIZE, recoveredField61.size()); i++) {
         int slot = i - first;
         CosmeticSelectionElement element = recoveredField61.get(i);
         element.setDimensions(15 + (slot / 5) * 160, 15 + (slot % 5) * 30, 150, 30);
      }
   }

   private static boolean inside(float x, float y, float left, float top, float width, float height) {
      return x >= left && x < left + width && y >= top && y < top + height;
   }

   private void sound() {
      j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1F));
   }

   @Override
   public void onMouseClicked(float x, float y, int button) {
      if (returnScreen == null) super.onMouseClicked(x, y, button);
      if (j.currentScreen != this) return;
      x = localX(x);
      y = localY(y);
      layout();
      searchField.handleElementMouseClicked(x, y, button, true);
      updateSearchResults();
      if (button != 0 || searchField.a_(x, y)) return;
      if (recoveredField64.a_(x, y)) {
         sound();
         j.displayGuiScreen(returnScreen != null ? returnScreen : new MainMenu());
         return;
      }
      if (wingsButton.a_(x, y) || capesButton.a_(x, y)) {
         selectedType = wingsButton.a_(x, y)
            ? com.cheatbreaker.client.util.cosmetic.CosmeticType.WINGS
            : com.cheatbreaker.client.util.cosmetic.CosmeticType.CAPE;
         searchQuery = null;
         updateSearchResults();
         sound();
         return;
      }
      if (previousPageButton.a_(x, y) && recoveredField63 > 0) {
         recoveredField63--;
         sound();
      } else if (nextPageButton.a_(x, y) && (recoveredField63 + 1) * PAGE_SIZE < recoveredField61.size()) {
         recoveredField63++;
         sound();
      } else if (inside(x, y, 345, 0, 155, 165)) {
         dragging = true;
         lastDragX = x;
      } else {
         int first = recoveredField63 * PAGE_SIZE;
         for (int i = first; i < Math.min(first + PAGE_SIZE, recoveredField61.size()); i++) {
            recoveredField61.get(i).handleMouseClick((int)x, (int)y, button);
         }
      }
   }

   @Override
   public void onMouseReleased(float x, float y, int button) {
      if (button == 0) dragging = false;
   }

   public CosmeticsMenu(net.minecraft.client.gui.GuiScreen returnScreen) {
      this();
      this.returnScreen = returnScreen;
   }

   public CosmeticsMenu() {
      this.recoveredField415 = java.util.Arrays.asList(this.recoveredField410, this.recoveredField401);
      this.recoveredField62 = new ResourceLocation("client/icons/right.png");
      this.recoveredField64 = new GradientTextButton("BACK");
      this.recoveredField63 = 0;

      for (ClientResourceManager var2 : CheatBreaker.getInstance().method_19791().getLocalCosmetics().getCosmetics()) {
         this.allCosmetics.add(new CosmeticSelectionElement(var2, 1.0F));
      }
      searchField = new InputFieldElement(CheatBreaker.getInstance().robotoRegular13px,
         "Search cosmetics...", 0x40808080, -1);
      searchField.trimToLength(128);
      selectedType = com.cheatbreaker.client.util.cosmetic.CosmeticType.WINGS;
      updateSearchResults();
   }

   @Override
   public void drawMenu(float mouseX, float mouseY) {
      if (previewOrderChanged) {
         CheatBreaker.getInstance().method_19791().getPreviewCache().setDisplayOrder(previewOrder, PAGE_SIZE);
         previewOrderChanged = false;
      }
      CheatBreaker.getInstance().method_19791().getPreviewCache().prepareModelCape(
         CheatBreaker.getInstance().method_19791().getLocalCosmetics().getEquipped(com.cheatbreaker.client.util.cosmetic.CosmeticType.CAPE));
      CheatBreaker.getInstance().method_19791().getPreviewCache().beginFrame(recoveredField63);
      if (returnScreen == null) super.drawMenu(mouseX, mouseY);
      float x = localX(mouseX), y = localY(mouseY);
      if (dragging) {
         yaw = (yaw + (x - lastDragX) * 1.5F) % 360F;
         lastDragX = x;
      }
      layout();
      GL11.glPushMatrix();
      GL11.glTranslatef(getScaledWidth() / 2F, getScaledHeight() / 2F, 0F);
      float scale = contentScale();
      GL11.glScalef(scale, scale, scale);
      GL11.glTranslatef(-250F, -95F, 0F);
      // Match the native NewsMenu/ChangelogMenu panel and separator style.
      Gui.drawRect(0F, 0F, 335F, 205F, 788529152);
      Gui.drawRect(345F, 0F, 500F, 205F, 788529152);
      int first = recoveredField63 * PAGE_SIZE;
      for (int i = first; i < Math.min(first + PAGE_SIZE, recoveredField61.size()); i++) {
         recoveredField61.get(i).handleDrawElement((int)x, (int)y, 1F);
      }
      if (recoveredField61.isEmpty()) {
         CheatBreaker.getInstance().robotoRegular13px.drawCenteredString("No matching cosmetics.", 167.5F, 80F, 0xFFBBBBBB);
      }
      Gui.drawRect(15F, 170F, 320F, 170.5F, 452984831);
      searchField.drawElement(x, y, true);
      previousPageButton.drawElement(x, y, recoveredField63 > 0);
      nextPageButton.drawElement(x, y, (recoveredField63 + 1) * PAGE_SIZE < recoveredField61.size());
      playerPreview.draw(422.5F, 56F, 67F, yaw);
      CheatBreaker.getInstance().robotoRegular13px.drawCenteredString("Hint: Click to drag.", 422.5F, 168F, 0xFFCCCCCC);
      wingsButton.drawElement(x, y, true);
      capesButton.drawElement(x, y, true);
      recoveredField64.drawElement(x, y, true);
      GL11.glPopMatrix();
   }

}
