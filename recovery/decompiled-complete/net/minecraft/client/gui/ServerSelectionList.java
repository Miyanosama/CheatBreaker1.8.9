package net.minecraft.client.gui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.serverlist.PinnedServerEntry;
import com.google.common.collect.Lists;
import io.netty.handler.codec.socks.SocksResponseType;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.LanServerDetector$LanServer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import org.apache.log4j.helpers.DateTimeDateFormat;

public class ServerSelectionList extends GuiListExtended {
   public List<ServerListEntryLanDetected> serverListLan;
   public int selectedSlotIndex;
   public GuiMultiplayer owner;
   public List serverListInternet = Lists.newArrayList();
   public DateTimeDateFormat field_0000;
   public EntityAIFollowParent field_0001;
   public SocksResponseType field_0007;
   public GuiListExtended$IGuiListEntry lanScanEntry;

   @Override
   public GuiListExtended$IGuiListEntry getListEntry(int var1) {
      if (var1 < this.serverListInternet.size()) {
         return (GuiListExtended$IGuiListEntry)this.serverListInternet.get(var1);
      } else {
         var1 -= this.serverListInternet.size();
         return var1 == 0 ? this.lanScanEntry : this.serverListLan.get(--var1);
      }
   }

   @Override
   public int v_() {
      return super.v_() + 85;
   }

   @Override
   public boolean isSelected(int var1) {
      return var1 == this.selectedSlotIndex;
   }

   public void func_148194_a(List<LanServerDetector$LanServer> var1) {
      this.serverListLan.clear();

      for (LanServerDetector$LanServer var3 : var1) {
         this.serverListLan.add(new ServerListEntryLanDetected(this.owner, var3));
      }
   }

   public void func_148195_a(ServerList var1) {
      this.serverListInternet.clear();

      for (int var2 = 0; var2 < var1.countServers(); var2++) {
         ServerData var3 = var1.getServerData(var2);
         if (var3.field_0001) {
            this.serverListInternet.add(new PinnedServerEntry(this.owner, var3));
         } else if (!CheatBreaker.getInstance().getGlobalSettings().method_02689().stream().anyMatch(var1x -> var1x[1].equalsIgnoreCase(var3.serverIP))) {
            this.serverListInternet.add(new ServerListEntryNormal(this.owner, var3));
         }
      }
   }

   public ServerSelectionList(GuiMultiplayer var1, Minecraft var2, int var3, int var4, int var5, int var6, int var7) {
      super(var2, var3, var4, var5, var6, var7);
      this.serverListLan = Lists.newArrayList();
      this.lanScanEntry = new ServerListEntryLanScan();
      this.selectedSlotIndex = -1;
      this.owner = var1;
   }

   @Override
   public int getScrollBarX() {
      return super.getScrollBarX() + 30;
   }

   public void setSelectedSlotIndex(int var1) {
      this.selectedSlotIndex = var1;
   }

   public int func_148193_k() {
      return this.selectedSlotIndex;
   }

   @Override
   public int getSize() {
      return this.serverListInternet.size() + 1 + this.serverListLan.size();
   }
}
