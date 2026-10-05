package net.minecraft.client.gui;

import com.cheatbreaker.client.module.type.HypixelModule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.settings.GameSettings$Options;

public class GuiLanguage$List extends GuiSlot {
   public Map<String, Language> languageMap;
   public List<String> langCodeList;
   public HypixelModule field_0000;

   @Override
   public boolean isSelected(int var1) {
      return this.langCodeList.get(var1).equals(GuiLanguage.access$000(this.field_148178_k).getCurrentLanguage().getLanguageCode());
   }

   @Override
   public void drawBackground() {
      this.field_148178_k.drawDefaultBackground();
   }

   public GuiLanguage$List(GuiLanguage var1, Minecraft var2) {
      this.field_148178_k = var1;
      super(var2, var1.l, var1.m, 32, var1.m - 65 + 4, 18);
      this.langCodeList = Lists.newArrayList();
      this.languageMap = Maps.newHashMap();

      for (Language var4 : GuiLanguage.access$000(var1).getLanguages()) {
         this.languageMap.put(var4.getLanguageCode(), var4);
         this.langCodeList.add(var4.getLanguageCode());
      }
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      Language var5 = this.languageMap.get(this.langCodeList.get(var1));
      GuiLanguage.access$000(this.field_148178_k).setCurrentLanguage(var5);
      GuiLanguage.access$100(this.field_148178_k).language = var5.getLanguageCode();
      this.a.refreshResources();
      this.field_148178_k
         .q
         .setUnicodeFlag(GuiLanguage.access$000(this.field_148178_k).isCurrentLocaleUnicode() || GuiLanguage.access$100(this.field_148178_k).forceUnicodeFont);
      this.field_148178_k.q.setBidiFlag(GuiLanguage.access$000(this.field_148178_k).isCurrentLanguageBidirectional());
      GuiLanguage.access$200(this.field_148178_k).j = I18n.format("gui.done");
      GuiLanguage.access$300(this.field_148178_k).j = GuiLanguage.access$100(this.field_148178_k).getKeyBinding(GameSettings$Options.FORCE_UNICODE_FONT);
      GuiLanguage.access$100(this.field_148178_k).saveOptions();
   }

   @Override
   public int getContentHeight() {
      return this.getSize() * 18;
   }

   @Override
   public int getSize() {
      return this.langCodeList.size();
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_148178_k.q.setBidiFlag(true);
      this.field_148178_k
         .drawCenteredString(this.field_148178_k.q, this.languageMap.get(this.langCodeList.get(var1)).toString(), this.b / 2, var3 + 1, 16777215);
      this.field_148178_k.q.setBidiFlag(GuiLanguage.access$000(this.field_148178_k).getCurrentLanguage().isBidirectional());
   }
}
