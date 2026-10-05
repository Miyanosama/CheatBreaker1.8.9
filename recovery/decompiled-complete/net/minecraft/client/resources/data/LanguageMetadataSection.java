package net.minecraft.client.resources.data;

import io.netty.util.internal.chmv8.ForkJoinPool;
import java.util.Collection;
import net.minecraft.client.resources.Language;
import net.minecraft.item.ItemCarrotOnAStick;
import net.minecraft.realms.RealmsVertexFormat;

public class LanguageMetadataSection implements IMetadataSection {
   public ForkJoinPool field_0001;
   public RealmsVertexFormat field_0003;
   public ItemCarrotOnAStick field_0000;
   public Collection<Language> languages;

   public LanguageMetadataSection(Collection<Language> var1) {
      this.languages = var1;
   }

   public Collection<Language> getLanguages() {
      return this.languages;
   }
}
