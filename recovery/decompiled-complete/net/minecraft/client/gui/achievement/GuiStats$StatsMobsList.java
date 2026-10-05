package net.minecraft.client.gui.achievement;

import com.google.common.collect.Lists;
import io.netty.buffer.AbstractReferenceCountedByteBuf;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.socks.SocksCmdResponse;
import io.netty.util.Recycler$WeakOrderQueue;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.particle.EntityAuraFX$HappyVillagerFactory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList$EntityEggInfo;

public class GuiStats$StatsMobsList extends GuiSlot {
   public Recycler$WeakOrderQueue field_0003;
   public AbstractReferenceCountedByteBuf field_0005;
   public MarshallingEncoder field_0002;
   public SocksCmdResponse field_0004;
   public EntityAuraFX$HappyVillagerFactory field_0001;
   public List<EntityList$EntityEggInfo> field_148222_l;

   @Override
   public int getContentHeight() {
      return this.getSize() * GuiStats.access$1700(this.field_148223_k).FONT_HEIGHT * 4;
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
   }

   @Override
   public int getSize() {
      return this.field_148222_l.size();
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      EntityList$EntityEggInfo var7 = this.field_148222_l.get(var1);
      String var8 = I18n.format("entity." + EntityList.getStringFromID(var7.spawnedID) + ".name");
      int var9 = GuiStats.access$100(this.field_148223_k).a(var7.field_151512_d);
      int var10 = GuiStats.access$100(this.field_148223_k).a(var7.field_151513_e);
      String var11 = I18n.format("stat.entityKills", var9, var8);
      String var12 = I18n.format("stat.entityKilledBy", var8, var10);
      if (var9 == 0) {
         var11 = I18n.format("stat.entityKills.none", var8);
      }

      if (var10 == 0) {
         var12 = I18n.format("stat.entityKilledBy.none", var8);
      }

      this.field_148223_k.drawString(GuiStats.access$1800(this.field_148223_k), var8, var2 + 2 - 10, var3 + 1, 16777215);
      this.field_148223_k
         .drawString(
            GuiStats.access$1900(this.field_148223_k),
            var11,
            var2 + 2,
            var3 + 1 + GuiStats.access$2000(this.field_148223_k).FONT_HEIGHT,
            var9 == 0 ? 6316128 : 9474192
         );
      this.field_148223_k
         .drawString(
            GuiStats.access$2100(this.field_148223_k),
            var12,
            var2 + 2,
            var3 + 1 + GuiStats.access$2200(this.field_148223_k).FONT_HEIGHT * 2,
            var10 == 0 ? 6316128 : 9474192
         );
   }

   @Override
   public boolean isSelected(int var1) {
      return false;
   }

   @Override
   public void drawBackground() {
      this.field_148223_k.drawDefaultBackground();
   }

   public GuiStats$StatsMobsList(GuiStats var1, Minecraft var2) {
      this.field_148223_k = var1;
      super(var2, var1.l, var1.m, 32, var1.m - 64, GuiStats.access$1600(var1).FONT_HEIGHT * 4);
      this.field_148222_l = Lists.newArrayList();
      this.setShowSelectionBox(false);

      for (EntityList$EntityEggInfo var4 : EntityList.entityEggs.values()) {
         if (GuiStats.access$100(var1).a(var4.field_151512_d) > 0 || GuiStats.access$100(var1).a(var4.field_151513_e) > 0) {
            this.field_148222_l.add(var4);
         }
      }
   }
}
