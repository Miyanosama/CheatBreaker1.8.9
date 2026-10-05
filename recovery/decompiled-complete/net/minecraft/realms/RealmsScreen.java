package net.minecraft.realms;

import com.cheatbreaker.client.ui.fading.ColorFade;
import com.mojang.util.UUIDTypeAdapter;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.structure.StructureVillagePieces$Village;

public class RealmsScreen {
   public static int field_0006;
   public static int field_0016;
   public static int field_0005;
   public static int field_0009;
   public GuiScreenRealmsProxy proxy = new GuiScreenRealmsProxy(this);
   public static int field_0008;
   public ColorFade field_0012;
   public StructureVillagePieces$Village field_0015;
   public static int field_0003;
   public static int field_0007;
   public int field_0010;
   public static int field_0014;
   public Minecraft minecraft;
   public static int field_0001;
   public WorldGenHellLava field_0004;
   public static int field_0000;
   public int field_0013;

   public static RealmsButton newButton(int var0, int var1, int var2, int var3, int var4, String var5) {
      return new RealmsButton(var0, var1, var2, var3, var4, var5);
   }

   public void drawString(String var1, int var2, int var3, int var4, boolean var5) {
      this.proxy.func_154322_b(var1, var2, var3, var4, false);
   }

   public int height() {
      return this.proxy.m;
   }

   public void method_01598(RealmsButton var1) {
      this.proxy.func_154328_b(var1);
   }

   public void mouseClicked(int var1, int var2, int var3) {
   }

   public void mouseDragged(int var1, int var2, int var3, long var4) {
   }

   public GuiScreenRealmsProxy getProxy() {
      return this.proxy;
   }

   public void renderBackground() {
      this.proxy.drawDefaultBackground();
   }

   public void renderTooltip(String var1, int var2, int var3) {
      this.proxy.drawCreativeTabHoveringText(var1, var2, var3);
   }

   public void method_01583(String var1, int var2, int var3, int var4) {
      this.proxy.func_154319_c(var1, var2, var3, var4);
   }

   public RealmsEditBox newEditBox(int var1, int var2, int var3, int var4, int var5) {
      return new RealmsEditBox(var1, var2, var3, var4, var5);
   }

   public void method_01575(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.proxy.drawGradientRect(var1, var2, var3, var4, var5, var6);
   }

   public static RealmsButton newButton(int var0, int var1, int var2, String var3) {
      return new RealmsButton(var0, var1, var2, var3);
   }

   public static String getLocalizedString(String var0) {
      return I18n.format(var0);
   }

   public static void bindFace(String var0, String var1) {
      ResourceLocation var2 = AbstractClientPlayer.getLocationSkin(var1);
      if (var2 == null) {
         var2 = DefaultPlayerSkin.getDefaultSkin(UUIDTypeAdapter.fromString(var0));
      }

      AbstractClientPlayer.getDownloadImageSkin(var2, var1);
      Minecraft.getMinecraft().getTextureManager().bindTexture(var2);
   }

   public void keyPressed(char var1, int var2) {
   }

   public boolean isPauseScreen() {
      return this.proxy.b_();
   }

   public void buttonsAdd(RealmsButton var1) {
      this.proxy.func_154327_a(var1);
   }

   public List<RealmsButton> buttons() {
      return this.proxy.func_154320_j();
   }

   public void render(int var1, int var2, float var3) {
      for (int var4 = 0; var4 < this.proxy.func_154320_j().size(); var4++) {
         this.proxy.func_154320_j().get(var4).render(var1, var2);
      }
   }

   public void method_01601() {
   }

   public void renderTooltip(List<String> var1, int var2, int var3) {
      this.proxy.drawHoveringText(var1, var2, var3);
   }

   public void blit(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.proxy.drawTexturedModalRect(var1, var2, var3, var4, var5, var6);
   }

   public int fontWidth(String var1) {
      return this.proxy.func_154326_c(var1);
   }

   public void method_01593() {
   }

   public void renderBackground(int var1) {
      this.proxy.drawWorldBackground(var1);
   }

   public List<String> fontSplit(String var1, int var2) {
      return this.proxy.func_154323_a(var1, var2);
   }

   public void method_01561() {
   }

   public void buttonsClear() {
      this.proxy.func_154324_i();
   }

   public void init(Minecraft var1, int var2, int var3) {
   }

   public static String getLocalizedString(String var0, Object... var1) {
      return I18n.format(var0, var1);
   }

   public int width() {
      return this.proxy.l;
   }

   public int fontLineHeight() {
      return this.proxy.func_154329_h();
   }

   public static void bind(String var0) {
      ResourceLocation var1 = new ResourceLocation(var0);
      Minecraft.getMinecraft().getTextureManager().bindTexture(var1);
   }

   public static void blit(int var0, int var1, float var2, float var3, int var4, int var5, float var6, float var7) {
      Gui.drawModalRectWithCustomSizedTexture(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   public void mouseReleased(int var1, int var2, int var3) {
   }

   public void init() {
   }

   public void drawString(String var1, int var2, int var3, int var4) {
      this.drawString(var1, var2, var3, var4, true);
   }

   public RealmsAnvilLevelStorageSource getLevelStorageSource() {
      return new RealmsAnvilLevelStorageSource(Minecraft.getMinecraft().getSaveLoader());
   }

   public void buttonClicked(RealmsButton var1) {
   }

   public void renderTooltip(ItemStack var1, int var2, int var3) {
      this.proxy.renderToolTip(var1, var2, var3);
   }

   public void method_01562() {
   }

   public void confirmResult(boolean var1, int var2) {
   }

   public void drawCenteredString(String var1, int var2, int var3, int var4) {
      this.proxy.func_154325_a(var1, var2, var3, var4);
   }

   public static void blit(int var0, int var1, float var2, float var3, int var4, int var5, int var6, int var7, float var8, float var9) {
      Gui.drawScaledCustomSizeModalRect(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }
}
