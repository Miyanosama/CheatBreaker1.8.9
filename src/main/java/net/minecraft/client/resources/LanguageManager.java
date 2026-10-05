package net.minecraft.client.resources;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.util.StringTranslate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageManager implements IResourceManagerReloadListener {
   public static Logger logger = LogManager.getLogger();
   public IMetadataSerializer theMetadataSerializer;
   public String currentLanguage;
   public Map<String, Language> languageMap = Maps.newHashMap();
   public static Locale currentLocale = new Locale();

   public void parseLanguageMetadata(List<IResourcePack> var1) {
      this.languageMap.clear();

      for (IResourcePack var3 : var1) {
         try {
            LanguageMetadataSection var4 = var3.getPackMetadata(this.theMetadataSerializer, "language");
            if (var4 != null) {
               for (Language var6 : var4.getLanguages()) {
                  if (!this.languageMap.containsKey(var6.getLanguageCode())) {
                     this.languageMap.put(var6.getLanguageCode(), var6);
                  }
               }
            }
         } catch (RuntimeException var7) {
            logger.warn("Unable to parse metadata section of resourcepack: " + var3.getPackName(), var7);
         } catch (IOException var8) {
            logger.warn("Unable to parse metadata section of resourcepack: " + var3.getPackName(), var8);
         }
      }
   }

   public boolean isCurrentLanguageBidirectional() {
      return this.getCurrentLanguage() != null && this.getCurrentLanguage().isBidirectional();
   }

   public boolean isCurrentLocaleUnicode() {
      return currentLocale.isUnicode();
   }

   public void setCurrentLanguage(Language var1) {
      this.currentLanguage = var1.getLanguageCode();
   }

   public SortedSet<Language> getLanguages() {
      return Sets.newTreeSet(this.languageMap.values());
   }

   public Language getCurrentLanguage() {
      return this.languageMap.containsKey(this.currentLanguage) ? this.languageMap.get(this.currentLanguage) : this.languageMap.get("en_US");
   }

   public LanguageManager(IMetadataSerializer var1, String var2) {
      this.theMetadataSerializer = var1;
      this.currentLanguage = var2;
      I18n.setLocale(currentLocale);
   }

   @Override
   public void onResourceManagerReload(IResourceManager var1) {
      ArrayList var2 = Lists.newArrayList("en_US");
      if (!"en_US".equals(this.currentLanguage)) {
         var2.add(this.currentLanguage);
      }

      currentLocale.loadLocaleDataFiles(var1, var2);
      StringTranslate.replaceWith(currentLocale.properties);
   }
}
