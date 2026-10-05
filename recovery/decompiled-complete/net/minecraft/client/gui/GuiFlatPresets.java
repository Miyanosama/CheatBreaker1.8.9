package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.embedded.EmbeddedChannel$DefaultUnsafe;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.BlockTallGrass$EnumType;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.FlatLayerInfo;
import org.apache.log4j.helpers.QuietWriter;
import org.lwjgl.input.Keyboard;
import recovered.unidentified.UnidentifiedClass1163;

public class GuiFlatPresets extends GuiScreen {
   public QuietWriter field_0005;
   public GuiFlatPresets$ListSlot field_146435_s;
   public GuiButton field_146434_t;
   public String presetsTitle;
   public ChunkProviderFlat field_0001;
   public EmbeddedChannel$DefaultUnsafe field_0002;
   public GuiTextField field_146433_u;
   public UnidentifiedClass1163 field_0007;
   public GuiCreateFlatWorld parentScreen;
   public String presetsShare;
   public String field_146436_r;
   public static List<GuiFlatPresets$LayerItem> FLAT_WORLD_PRESETS = Lists.newArrayList();

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
      this.field_146435_s = new GuiFlatPresets$ListSlot(this);
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
         BlockTallGrass$EnumType.GRASS.getMeta(),
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

      FLAT_WORLD_PRESETS.add(new GuiFlatPresets$LayerItem(var1, var2, var0, var6.toString()));
   }

   @Override
   public void keyTyped(char var1, int var2) {
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
   public void actionPerformed(GuiButton var1) {
      if (var1.k == 0 && this.func_146430_p()) {
         this.parentScreen.func_146383_a(this.field_146433_u.getText());
         this.j.displayGuiScreen(this.parentScreen);
      } else if (var1.k == 1) {
         this.j.displayGuiScreen(this.parentScreen);
      }
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      this.field_146433_u.mouseClicked(var1, var2, var3);
      super.mouseClicked(var1, var2, var3);
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      this.field_146435_s.handleMouseInput();
   }
}
