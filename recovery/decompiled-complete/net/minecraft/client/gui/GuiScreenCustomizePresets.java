package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item$10;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.ChunkProviderSettings$Factory;
import org.lwjgl.input.Keyboard;

public class GuiScreenCustomizePresets extends GuiScreen {
   public String field_175313_s;
   public GuiEditSign field_0007;
   public GuiButton field_175316_h;
   public String field_175315_a = "Customize World Presets";
   public GuiTextField field_175317_i;
   public GuiScreenCustomizePresets$ListPreset field_175311_g;
   public Item$10 field_0008;
   public static List<GuiScreenCustomizePresets$Info> field_175310_f = Lists.newArrayList();
   public GuiCustomizeWorldScreen field_175314_r;
   public String field_175312_t;

   @Override
   public void initGui() {
      this.n.clear();
      Keyboard.enableRepeatEvents(true);
      this.field_175315_a = I18n.format("createWorld.customize.custom.presets.title");
      this.field_175313_s = I18n.format("createWorld.customize.presets.share");
      this.field_175312_t = I18n.format("createWorld.customize.presets.list");
      this.field_175317_i = new GuiTextField(2, this.q, 50, 40, this.l - 100, 20);
      this.field_175311_g = new GuiScreenCustomizePresets$ListPreset(this);
      this.field_175317_i.setMaxStringLength(2000);
      this.field_175317_i.setText(this.field_175314_r.func_175323_a());
      this.n.add(this.field_175316_h = new GuiButton(0, this.l / 2 - 102, this.m - 27, 100, 20, I18n.format("createWorld.customize.presets.select")));
      this.n.add(new GuiButton(1, this.l / 2 + 3, this.m - 27, 100, 20, I18n.format("gui.cancel")));
      this.func_175304_a();
   }

   @Override
   public void keyTyped(char var1, int var2) {
      if (!this.field_175317_i.textboxKeyTyped(var1, var2)) {
         super.keyTyped(var1, var2);
      }
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      this.field_175311_g.handleMouseInput();
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      switch (var1.k) {
         case 0:
            this.field_175314_r.func_175324_a(this.field_175317_i.getText());
            this.j.displayGuiScreen(this.field_175314_r);
            break;
         case 1:
            this.j.displayGuiScreen(this.field_175314_r);
      }
   }

   public GuiScreenCustomizePresets(GuiCustomizeWorldScreen var1) {
      this.field_175314_r = var1;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.field_175311_g.a(var1, var2, var3);
      this.drawCenteredString(this.q, this.field_175315_a, this.l / 2, 8, 16777215);
      this.drawString(this.q, this.field_175313_s, 50, 30, 10526880);
      this.drawString(this.q, this.field_175312_t, 50, 70, 10526880);
      this.field_175317_i.drawTextBox();
      super.drawScreen(var1, var2, var3);
   }

   public void func_175304_a() {
      this.field_175316_h.l = this.func_175305_g();
   }

   static {
      ChunkProviderSettings$Factory var0 = ChunkProviderSettings$Factory.jsonToFactory(
         "{ \"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":5000.0, \"mainNoiseScaleY\":1000.0, \"mainNoiseScaleZ\":5000.0, \"baseSize\":8.5, \"stretchY\":8.0, \"biomeDepthWeight\":2.0, \"biomeDepthOffset\":0.5, \"biomeScaleWeight\":2.0, \"biomeScaleOffset\":0.375, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":255 }"
      );
      ResourceLocation var1 = new ResourceLocation("textures/gui/presets/water.png");
      field_175310_f.add(new GuiScreenCustomizePresets$Info(I18n.format("createWorld.customize.custom.preset.waterWorld"), var1, var0));
      var0 = ChunkProviderSettings$Factory.jsonToFactory(
         "{\"coordinateScale\":3000.0, \"heightScale\":6000.0, \"upperLimitScale\":250.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":10.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }"
      );
      var1 = new ResourceLocation("textures/gui/presets/isles.png");
      field_175310_f.add(new GuiScreenCustomizePresets$Info(I18n.format("createWorld.customize.custom.preset.isleLand"), var1, var0));
      var0 = ChunkProviderSettings$Factory.jsonToFactory(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":5000.0, \"mainNoiseScaleY\":1000.0, \"mainNoiseScaleZ\":5000.0, \"baseSize\":8.5, \"stretchY\":5.0, \"biomeDepthWeight\":2.0, \"biomeDepthOffset\":1.0, \"biomeScaleWeight\":4.0, \"biomeScaleOffset\":1.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }"
      );
      var1 = new ResourceLocation("textures/gui/presets/delight.png");
      field_175310_f.add(new GuiScreenCustomizePresets$Info(I18n.format("createWorld.customize.custom.preset.caveDelight"), var1, var0));
      var0 = ChunkProviderSettings$Factory.jsonToFactory(
         "{\"coordinateScale\":738.41864, \"heightScale\":157.69133, \"upperLimitScale\":801.4267, \"lowerLimitScale\":1254.1643, \"depthNoiseScaleX\":374.93652, \"depthNoiseScaleZ\":288.65228, \"depthNoiseScaleExponent\":1.2092624, \"mainNoiseScaleX\":1355.9908, \"mainNoiseScaleY\":745.5343, \"mainNoiseScaleZ\":1183.464, \"baseSize\":1.8758626, \"stretchY\":1.7137525, \"biomeDepthWeight\":1.7553768, \"biomeDepthOffset\":3.4701107, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":2.535211, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }"
      );
      var1 = new ResourceLocation("textures/gui/presets/madness.png");
      field_175310_f.add(new GuiScreenCustomizePresets$Info(I18n.format("createWorld.customize.custom.preset.mountains"), var1, var0));
      var0 = ChunkProviderSettings$Factory.jsonToFactory(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":1000.0, \"mainNoiseScaleY\":3000.0, \"mainNoiseScaleZ\":1000.0, \"baseSize\":8.5, \"stretchY\":10.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":20 }"
      );
      var1 = new ResourceLocation("textures/gui/presets/drought.png");
      field_175310_f.add(new GuiScreenCustomizePresets$Info(I18n.format("createWorld.customize.custom.preset.drought"), var1, var0));
      var0 = ChunkProviderSettings$Factory.jsonToFactory(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":2.0, \"lowerLimitScale\":64.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":12.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":6 }"
      );
      var1 = new ResourceLocation("textures/gui/presets/chaos.png");
      field_175310_f.add(new GuiScreenCustomizePresets$Info(I18n.format("createWorld.customize.custom.preset.caveChaos"), var1, var0));
      var0 = ChunkProviderSettings$Factory.jsonToFactory(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":12.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":true, \"seaLevel\":40 }"
      );
      var1 = new ResourceLocation("textures/gui/presets/luck.png");
      field_175310_f.add(new GuiScreenCustomizePresets$Info(I18n.format("createWorld.customize.custom.preset.goodLuck"), var1, var0));
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      this.field_175317_i.mouseClicked(var1, var2, var3);
      super.mouseClicked(var1, var2, var3);
   }

   public boolean func_175305_g() {
      return this.field_175311_g.field_178053_u > -1 && this.field_175311_g.field_178053_u < field_175310_f.size()
         || this.field_175317_i.getText().length() > 1;
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void updateScreen() {
      this.field_175317_i.updateCursorCounter();
      super.updateScreen();
   }
}
