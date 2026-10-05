package net.minecraft.client.resources;

import java.util.Map;
import net.minecraft.world.World$4;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$6;

public class I18n {
   public CategoryNodeEditor$6 field_0001;
   public static Locale i18nLocale;
   public World$4 field_0000;

   public static void setLocale(Locale var0) {
      i18nLocale = var0;
   }

   public static Map getLocaleProperties() {
      return i18nLocale.properties;
   }

   public static String format(String var0, Object... var1) {
      return i18nLocale.formatMessage(var0, var1);
   }
}
