package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import io.netty.handler.codec.serialization.CompatibleObjectEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;
import net.optifine.util.LinkedList$1;
import recovered.unidentified.UnidentifiedClass1096;

public class GuiSnooper extends GuiScreen {
   public List<String> field_146604_g = Lists.newArrayList();
   public GuiScreen field_146608_a;
   public String field_146610_i;
   public GuiSnooper$List field_146606_s;
   public List<String> field_146609_h = Lists.newArrayList();
   public GuiButton field_146605_t;
   public UnidentifiedClass1096 field_0009;
   public String[] field_146607_r;
   public LinkedList$1 field_0003;
   public GameSettings game_settings_2;
   public CompatibleObjectEncoder field_0000;

   public GuiSnooper(GuiScreen var1, GameSettings var2) {
      this.field_146608_a = var1;
      this.game_settings_2 = var2;
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == 2) {
            this.game_settings_2.saveOptions();
            this.game_settings_2.saveOptions();
            this.j.displayGuiScreen(this.field_146608_a);
         }

         if (var1.k == 1) {
            this.game_settings_2.setOptionValue(GameSettings$Options.SNOOPER_ENABLED, 1);
            this.field_146605_t.j = this.game_settings_2.getKeyBinding(GameSettings$Options.SNOOPER_ENABLED);
         }
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.field_146606_s.a(var1, var2, var3);
      this.drawCenteredString(this.q, this.field_146610_i, this.l / 2, 8, 16777215);
      int var4 = 22;

      for (String var8 : this.field_146607_r) {
         this.drawCenteredString(this.q, var8, this.l / 2, var4, 8421504);
         var4 += this.q.FONT_HEIGHT;
      }

      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void initGui() {
      this.field_146610_i = I18n.format("options.snooper.title");
      String var1 = I18n.format("options.snooper.desc");
      ArrayList var2 = Lists.newArrayList();

      for (String var4 : this.q.listFormattedStringToWidth(var1, this.l - 30)) {
         var2.add(var4);
      }

      this.field_146607_r = var2.toArray(new String[var2.size()]);
      this.field_146604_g.clear();
      this.field_146609_h.clear();
      this.n
         .add(
            this.field_146605_t = new GuiButton(
               1, this.l / 2 - 152, this.m - 30, 150, 20, this.game_settings_2.getKeyBinding(GameSettings$Options.SNOOPER_ENABLED)
            )
         );
      this.n.add(new GuiButton(2, this.l / 2 + 2, this.m - 30, 150, 20, I18n.format("gui.done")));
      boolean var6 = this.j.getIntegratedServer() != null && this.j.getIntegratedServer().getPlayerUsageSnooper() != null;

      for (Entry var5 : new TreeMap<>(this.j.getPlayerUsageSnooper().getCurrentStats()).entrySet()) {
         this.field_146604_g.add((var6 ? "C " : "") + (String)var5.getKey());
         this.field_146609_h.add(this.q.trimStringToWidth((String)var5.getValue(), this.l - 220));
      }

      if (var6) {
         for (Entry var9 : new TreeMap<>(this.j.getIntegratedServer().getPlayerUsageSnooper().getCurrentStats()).entrySet()) {
            this.field_146604_g.add("S " + (String)var9.getKey());
            this.field_146609_h.add(this.q.trimStringToWidth((String)var9.getValue(), this.l - 220));
         }
      }

      this.field_146606_s = new GuiSnooper$List(this);
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      this.field_146606_s.handleMouseInput();
   }
}
