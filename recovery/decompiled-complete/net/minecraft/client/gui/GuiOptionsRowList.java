package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.util.Vector3d;

public class GuiOptionsRowList extends GuiListExtended {
   public IdleStateHandler field_0001;
   public Vector3d field_0003;
   public List<GuiOptionsRowList$Row> field_148184_k = Lists.newArrayList();
   public C01PacketEncryptionResponse field_0002;

   public GuiOptionsRowList$Row getListEntry(int var1) {
      return this.field_148184_k.get(var1);
   }

   @Override
   public int getScrollBarX() {
      return super.getScrollBarX() + 32;
   }

   public GuiButton func_148182_a(Minecraft var1, int var2, int var3, GameSettings$Options var4) {
      if (var4 == null) {
         return null;
      } else {
         int var5 = var4.returnEnumOrdinal();
         return (GuiButton)(var4.getEnumFloat()
            ? new GuiOptionSlider(var5, var2, var3, var4)
            : new GuiOptionButton(var5, var2, var3, var4, var1.gameSettings.getKeyBinding(var4)));
      }
   }

   @Override
   public int getSize() {
      return this.field_148184_k.size();
   }

   @Override
   public int v_() {
      return 400;
   }

   public GuiOptionsRowList(Minecraft var1, int var2, int var3, int var4, int var5, int var6, GameSettings$Options... var7) {
      super(var1, var2, var3, var4, var5, var6);
      this.k = false;

      for (byte var8 = 0; var8 < var7.length; var8 += 2) {
         GameSettings$Options var9 = var7[var8];
         GameSettings$Options var10 = var8 < var7.length - 1 ? var7[var8 + 1] : null;
         GuiButton var11 = this.func_148182_a(var1, var2 / 2 - 155, 0, var9);
         GuiButton var12 = this.func_148182_a(var1, var2 / 2 - 155 + 160, 0, var10);
         this.field_148184_k.add(new GuiOptionsRowList$Row(var11, var12));
      }
   }
}
