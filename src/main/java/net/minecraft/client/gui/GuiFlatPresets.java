package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.FlatLayerInfo;
import org.lwjgl.input.Keyboard;

public class GuiFlatPresets extends GuiScreen {
   public GuiFlatPresets.ListSlot field_146435_s;
   public GuiButton field_146434_t;
   public String presetsTitle;
   public GuiTextField field_146433_u;
   public GuiCreateFlatWorld parentScreen;
   public String presetsShare;
   public String field_146436_r;
   public static List<GuiFlatPresets.LayerItem> FLAT_WORLD_PRESETS = Lists.newArrayList();

   public GuiFlatPresets(GuiCreateFlatWorld var1) {
      this.parentScreen = var1;
   }

   @Override
   public void updateScreen() {
      this.field_146433_u.updateCursorCounter();
      super.updateScreen();
   }

   public static void func_146421_a(String var0, Item var1, BiomeGenBase var2, List<String> var3, FlatLayerInfo... var4) {
      func_175354_a(var0, var1, 0, var2, var3, var4);
   }

   public static void func_146425_a(String var0, Item var1, BiomeGenBase var2, FlatLayerInfo... var3) {
      func_175354_a(var0, var1, 0, var2, (List<String>)null, var3);
   }

   public boolean func_146430_p() {
      return this.field_146435_s.field_148175_k > -1 && this.field_146435_s.field_148175_k < FLAT_WORLD_PRESETS.size()
         || this.field_146433_u.getText().length() > 1;
   }

   @Override
   public void initGui() {
      this.n.clear();
      Keyboard.enableRepeatEvents(true);
      this.presetsTitle = I18n.format("createWorld.customize.presets.title");
      this.presetsShare = I18n.format("createWorld.customize.presets.share");
      this.field_146436_r = I18n.format("createWorld.customize.presets.list");
      this.field_146433_u = new GuiTextField(2, this.q, 50, 40, this.l - 100, 20);
      this.field_146435_s = new GuiFlatPresets.ListSlot();
      this.field_146433_u.setMaxStringLength(1230);
      this.field_146433_u.setText(this.parentScreen.func_146384_e());
      this.n.add(this.field_146434_t = new GuiButton(0, this.l / 2 - 155, this.m - 28, 150, 20, I18n.format("createWorld.customize.presets.select")));
      this.n.add(new GuiButton(1, this.l / 2 + 5, this.m - 28, 150, 20, I18n.format("gui.cancel")));
      this.func_146426_g();
   }

   static {
      func_146421_a(
         "Classic Flat",
         Item.getItemFromBlock(Blocks.grass),
         BiomeGenBase.plains,
         Arrays.asList("village"),
         new FlatLayerInfo(1, Blocks.grass),
         new FlatLayerInfo(2, Blocks.dirt),
         new FlatLayerInfo(1, Blocks.bedrock)
      );
      func_146421_a(
         "Tunnelers' Dream",
         Item.getItemFromBlock(Blocks.stone),
         BiomeGenBase.extremeHills,
         Arrays.asList("biome_1", "dungeon", "decoration", "stronghold", "mineshaft"),
         new FlatLayerInfo(1, Blocks.grass),
         new FlatLayerInfo(5, Blocks.dirt),
         new FlatLayerInfo(230, Blocks.stone),
         new FlatLayerInfo(1, Blocks.bedrock)
      );
      func_146421_a(
         "Water World",
         Items.water_bucket,
         BiomeGenBase.deepOcean,
         Arrays.asList("biome_1", "oceanmonument"),
         new FlatLayerInfo(90, Blocks.water),
         new FlatLayerInfo(5, Blocks.sand),
         new FlatLayerInfo(5, Blocks.dirt),
         new FlatLayerInfo(5, Blocks.stone),
         new FlatLayerInfo(1, Blocks.bedrock)
      );
      func_175354_a(
         "Overworld",
         Item.getItemFromBlock(Blocks.tallgrass),
         BlockTallGrass.EnumType.GRASS.getMeta(),
         BiomeGenBase.plains,
         Arrays.asList("village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon", "lake", "lava_lake"),
         new FlatLayerInfo(1, Blocks.grass),
         new FlatLayerInfo(3, Blocks.dirt),
         new FlatLayerInfo(59, Blocks.stone),
         new FlatLayerInfo(1, Blocks.bedrock)
      );
      func_146421_a(
         "Snowy Kingdom",
         Item.getItemFromBlock(Blocks.snow_layer),
         BiomeGenBase.icePlains,
         Arrays.asList("village", "biome_1"),
         new FlatLayerInfo(1, Blocks.snow_layer),
         new FlatLayerInfo(1, Blocks.grass),
         new FlatLayerInfo(3, Blocks.dirt),
         new FlatLayerInfo(59, Blocks.stone),
         new FlatLayerInfo(1, Blocks.bedrock)
      );
      func_146421_a(
         "Bottomless Pit",
         Items.feather,
         BiomeGenBase.plains,
         Arrays.asList("village", "biome_1"),
         new FlatLayerInfo(1, Blocks.grass),
         new FlatLayerInfo(3, Blocks.dirt),
         new FlatLayerInfo(2, Blocks.cobblestone)
      );
      func_146421_a(
         "Desert",
         Item.getItemFromBlock(Blocks.sand),
         BiomeGenBase.desert,
         Arrays.asList("village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon"),
         new FlatLayerInfo(8, Blocks.sand),
         new FlatLayerInfo(52, Blocks.sandstone),
         new FlatLayerInfo(3, Blocks.stone),
         new FlatLayerInfo(1, Blocks.bedrock)
      );
      func_146425_a(
         "Redstone Ready",
         Items.redstone,
         BiomeGenBase.desert,
         new FlatLayerInfo(52, Blocks.sandstone),
         new FlatLayerInfo(3, Blocks.stone),
         new FlatLayerInfo(1, Blocks.bedrock)
      );
   }

   public static void func_175354_a(String var0, Item var1, int var2, BiomeGenBase var3, List<String> var4, FlatLayerInfo... var5) {
      FlatGeneratorInfo var6 = new FlatGeneratorInfo();

      for (int var7 = var5.length - 1; var7 >= 0; var7--) {
         var6.getFlatLayers().add(var5[var7]);
      }

      var6.setBiome(var3.az);
      var6.func_82645_d();
      if (var4 != null) {
         for (String var8 : var4) {
            var6.getWorldFeatures().put(var8, Maps.newHashMap());
         }
      }

      FLAT_WORLD_PRESETS.add(new GuiFlatPresets.LayerItem(var1, var2, var0, var6.toString()));
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      if (!this.field_146433_u.textboxKeyTyped(var1, var2)) {
         super.keyTyped(var1, var2);
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.field_146435_s.a(var1, var2, var3);
      this.drawCenteredString(this.q, this.presetsTitle, this.l / 2, 8, 16777215);
      this.drawString(this.q, this.presetsShare, 50, 30, 10526880);
      this.drawString(this.q, this.field_146436_r, 50, 70, 10526880);
      this.field_146433_u.drawTextBox();
      super.drawScreen(var1, var2, var3);
   }

   public void func_146426_g() {
      boolean var1 = this.func_146430_p();
      this.field_146434_t.l = var1;
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.k == 0 && this.func_146430_p()) {
         this.parentScreen.func_146383_a(this.field_146433_u.getText());
         this.j.displayGuiScreen(this.parentScreen);
      } else if (var1.k == 1) {
         this.j.displayGuiScreen(this.parentScreen);
      }
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      this.field_146433_u.mouseClicked(var1, var2, var3);
      super.mouseClicked(var1, var2, var3);
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      this.field_146435_s.handleMouseInput();
   }

   public static class LayerItem {
      public String field_148233_c;
      public int field_179037_b;
      public Item field_148234_a;
      public String field_148232_b;

      public LayerItem(Item var1, int var2, String var3, String var4) {
         this.field_148234_a = var1;
         this.field_179037_b = var2;
         this.field_148232_b = var3;
         this.field_148233_c = var4;
      }
   }

   public class ListSlot extends GuiSlot {
      public int field_148175_k = -1;

      public void func_148173_e(int var1, int var2) {
         this.func_148171_c(var1, var2, 0, 0);
      }

      @Override
      public int getSize() {
         return GuiFlatPresets.FLAT_WORLD_PRESETS.size();
      }

      public ListSlot() {
         super(GuiFlatPresets.this.j, GuiFlatPresets.this.l, GuiFlatPresets.this.m, 80, GuiFlatPresets.this.m - 37, 24);
      }

      public void func_178054_a(int var1, int var2, Item var3, int var4) {
         this.func_148173_e(var1 + 1, var2 + 1);
         GlStateManager.enableRescaleNormal();
         RenderHelper.enableGUIStandardItemLighting();
         GuiFlatPresets.this.k.renderItemIntoGUI(new ItemStack(var3, 1, var4), var1 + 2, var2 + 2);
         RenderHelper.disableStandardItemLighting();
         GlStateManager.disableRescaleNormal();
      }

      @Override
      public void elementClicked(int var1, boolean var2, int var3, int var4) {
         this.field_148175_k = var1;
         GuiFlatPresets.this.func_146426_g();
         GuiFlatPresets.this.field_146433_u.setText(GuiFlatPresets.FLAT_WORLD_PRESETS.get(GuiFlatPresets.this.field_146435_s.field_148175_k).field_148233_c);
      }

      public void func_148171_c(int var1, int var2, int var3, int var4) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.a.getTextureManager().bindTexture(Gui.statIcons);
         float var5 = 0.0078125F;
         float var6 = 0.0078125F;
         byte var7 = 18;
         byte var8 = 18;
         Tessellator var9 = Tessellator.getInstance();
         WorldRenderer var10 = var9.getWorldRenderer();
         var10.begin(7, DefaultVertexFormats.POSITION_TEX);
         var10.pos(var1 + 0, var2 + 18, GuiFlatPresets.recoveredField2942).tex((var3 + 0) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
         var10.pos(var1 + 18, var2 + 18, GuiFlatPresets.recoveredField2942).tex((var3 + 18) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
         var10.pos(var1 + 18, var2 + 0, GuiFlatPresets.recoveredField2942).tex((var3 + 18) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
         var10.pos(var1 + 0, var2 + 0, GuiFlatPresets.recoveredField2942).tex((var3 + 0) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
         var9.draw();
      }

      @Override
      public void drawBackground() {
      }

      @Override
      public boolean isSelected(int var1) {
         return var1 == this.field_148175_k;
      }

      @Override
      public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
         GuiFlatPresets.LayerItem var7 = GuiFlatPresets.FLAT_WORLD_PRESETS.get(var1);
         this.func_178054_a(var2, var3, var7.field_148234_a, var7.field_179037_b);
         GuiFlatPresets.this.q.drawString(var7.field_148232_b, var2 + 18 + 5, var3 + 6, 16777215);
      }
   }
}
