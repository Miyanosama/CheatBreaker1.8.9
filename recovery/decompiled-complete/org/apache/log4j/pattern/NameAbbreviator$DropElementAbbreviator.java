package org.apache.log4j.pattern;

import io.netty.handler.codec.http.HttpHeaderDateFormat;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$SearchKeysTask;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.client.gui.GuiScreenCustomizePresets;
import net.minecraft.client.renderer.texture.TextureClock;
import net.minecraft.server.integrated.IntegratedServerCommandManager;

public class NameAbbreviator$DropElementAbbreviator extends NameAbbreviator {
   public IntegratedServerCommandManager field_0005;
   public TextureClock field_0004;
   public ConcurrentHashMapV8$SearchKeysTask field_0001;
   public PropertyInteger field_0006;
   public int count;
   public HttpHeaderDateFormat field_0002;
   public GuiScreenCustomizePresets field_0003;

   public NameAbbreviator$DropElementAbbreviator(int var1) {
      this.count = var1;
   }

   public void abbreviate(int var1, StringBuffer var2) {
      int var3 = this.count;

      for (int var4 = var2.indexOf(".", var1); var4 != -1; var4 = var2.indexOf(".", var4 + 1)) {
         if (--var3 == 0) {
            var2.delete(var1, var4 + 1);
            break;
         }
      }
   }
}
