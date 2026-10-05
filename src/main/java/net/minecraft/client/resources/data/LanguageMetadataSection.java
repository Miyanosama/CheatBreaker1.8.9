package net.minecraft.client.resources.data;

import java.util.Collection;
import net.minecraft.client.resources.Language;

public class LanguageMetadataSection implements IMetadataSection {
   public Collection<Language> languages;

   public LanguageMetadataSection(Collection<Language> var1) {
      this.languages = var1;
   }

   public Collection<Language> getLanguages() {
      return this.languages;
   }
}
