package net.minecraft.client.gui;

import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.internal.logging.FormattingTuple;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.server.S08PacketPlayerPosLook$EnumFlags;
import org.apache.commons.lang3.ArrayUtils;

public class GuiKeyBindingList extends GuiListExtended {
   public TextureCompass field_0003;
   public FormattingTuple field_0006;
   public int maxListLabelWidth = 0;
   public GuiListExtended$IGuiListEntry[] listEntries;
   public GuiControls field_0000;
   public Minecraft field_0001;
   public HttpPostRequestDecoder field_0007;
   public S08PacketPlayerPosLook$EnumFlags field_0004;

   public GuiKeyBindingList(GuiControls var1, Minecraft var2) {
      super(var2, var1.l, var1.m, 63, var1.m - 32, 20);
      this.field_0000 = var1;
      this.field_0001 = var2;
      KeyBinding[] var3 = (KeyBinding[])ArrayUtils.clone(var2.gameSettings.keyBindings);
      this.listEntries = new GuiListExtended$IGuiListEntry[var3.length + KeyBinding.getKeybinds().size()];
      Arrays.sort(var3);
      int var4 = 0;
      String var5 = null;

      for (KeyBinding var9 : var3) {
         String var10 = var9.getKeyCategory();
         if (!var10.equals(var5)) {
            var5 = var10;
            this.listEntries[var4++] = new GuiKeyBindingList$CategoryEntry(this, var10);
         }

         int var11 = var2.fontRendererObj.getStringWidth(I18n.format(var9.getKeyDescription()));
         if (var11 > this.maxListLabelWidth) {
            this.maxListLabelWidth = var11;
         }

         this.listEntries[var4++] = new GuiKeyBindingList$KeyEntry(this, var9, null);
      }
   }

   @Override
   public int getScrollBarX() {
      return super.getScrollBarX() + 15;
   }

   @Override
   public int v_() {
      return super.v_() + 32;
   }

   @Override
   public GuiListExtended$IGuiListEntry getListEntry(int var1) {
      return this.listEntries[var1];
   }

   @Override
   public int getSize() {
      return this.listEntries.length;
   }
}
