package net.minecraft.client.gui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.serverlist.PinnedServerEntry;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.LanServerDetector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class ServerSelectionList extends GuiListExtended {
   public List<ServerListEntryLanDetected> serverListLan;
   public int selectedSlotIndex;
   public GuiMultiplayer owner;
   public List serverListInternet = Lists.newArrayList();
   public GuiListExtended.IGuiListEntry lanScanEntry;
   private int dragPressRow = -1;
   private int dragPressX;
   private int dragPressY;
   private int dragPressScroll;
   private ServerListEntryNormal draggingEntry;
   private int dragTargetRow = -1;
   private long lastScrollTime;
   private long bandEnterTime;
   private long bandExitTime;
   private int lastScrollDirection;
   private double scrollPixels;

   public boolean isRowDragging() { return this.draggingEntry != null; }

   @Override
   public void a(int width, int height, int top, int bottom) {
      this.resetRowDrag();
      super.a(width, height, top, bottom);
   }

   public void resetRowDrag() {
      this.dragPressRow = -1;
      this.draggingEntry = null;
      this.dragTargetRow = -1;
      this.setEnabled(true);
      this.initialClickY = -1;
   }

   void noteRowPress(int mouseX, int mouseY) {
      if (mouseY < this.d || mouseY > this.bottom) return;
      int row = this.c(mouseX, mouseY);
      int rowX = this.left + this.b / 2 - this.v_() / 2 + 2;
      // The icon contains the existing connect and reorder buttons.
      if (row < 0 || !(this.getListEntry(row) instanceof ServerListEntryNormal) || mouseX <= rowX + 32) return;
      this.dragPressRow = row;
      this.dragPressX = mouseX;
      this.dragPressY = mouseY;
      this.dragPressScroll = this.getAmountScrolled();
   }

   @Override
   public boolean b(int mouseX, int mouseY, int button) {
      this.resetRowDrag();
      boolean consumed = super.b(mouseX, mouseY, button);
      if (button == 0 && !consumed) this.noteRowPress(mouseX, mouseY);
      return consumed;
   }

   @Override
   public boolean c(int mouseX, int mouseY, int button) {
      if (button == 0) {
         this.tickRowDrag(mouseX, mouseY, false, Display.isActive(), Minecraft.getSystemTime());
      }
      return super.c(mouseX, mouseY, button);
   }

   private int targetRow(int mouseY) {
      int target = ServerListReorderGeometry.targetRow(this.d + 4 - this.getAmountScrolled() + this.headerPadding,
         mouseY, this.slotHeight, this.serverListInternet.size());
      int closest = -1;
      for (int row = 0; row < this.serverListInternet.size(); row++) {
         if (this.getListEntry(row) instanceof ServerListEntryNormal
            && (closest < 0 || Math.abs(row - target) < Math.abs(closest - target))) closest = row;
      }
      return closest;
   }

   void tickRowDrag(int mouseX, int mouseY, boolean pressed, boolean focused, long now) {
      if (this.dragPressRow < 0) return;
      if (!focused || this.dragPressRow >= this.serverListInternet.size()
         || !(this.getListEntry(this.dragPressRow) instanceof ServerListEntryNormal)) {
         this.resetRowDrag();
         return;
      }
      if (!pressed) {
         int from = this.dragPressRow;
         int to = this.targetRow(mouseY);
         boolean moved = this.draggingEntry != null && to >= 0 && to != from;
         this.resetRowDrag();
         if (moved) this.owner.moveServerRow(from, to);
         return;
      }
      if (this.draggingEntry == null) {
         if (!ServerListReorderGeometry.isDragStart(this.dragPressX, this.dragPressY, mouseX, mouseY)) return;
         this.draggingEntry = (ServerListEntryNormal)this.getListEntry(this.dragPressRow);
         this.draggingEntry.field_148298_f = 0L;
         this.amountScrolled = this.dragPressScroll;
         this.setEnabled(false);
         this.initialClickY = -1;
         this.lastScrollTime = now;
         this.bandEnterTime = 0L;
         this.bandExitTime = 0L;
         this.lastScrollDirection = 0;
         this.scrollPixels = 0.0;
      }
      int direction = ServerListReorderGeometry.scrollDirection(mouseY, this.d, this.bottom);
      if (direction != 0) {
         if (this.bandEnterTime == 0L || this.bandExitTime != 0L && now - this.bandExitTime > 200L) {
            this.bandEnterTime = now;
            this.scrollPixels = 0.0;
         }
         this.bandExitTime = 0L;
         this.scrollPixels += Math.max(0L, Math.min(100L, now - this.lastScrollTime)) * 0.144
            * ServerListReorderGeometry.scrollMultiplier(now - this.bandEnterTime);
         int pixels = (int)this.scrollPixels;
         this.scrollPixels -= pixels;
         if (pixels > 0) this.scrollBy(direction * pixels);
      } else if (this.lastScrollDirection != 0) {
         this.bandExitTime = now;
      }
      this.lastScrollDirection = direction;
      this.lastScrollTime = now;
      this.dragTargetRow = this.targetRow(mouseY);
   }

   @Override
   public void a(int mouseX, int mouseY, float partialTicks) {
      this.tickRowDrag(mouseX, mouseY, Mouse.isButtonDown(0), Display.isActive(), Minecraft.getSystemTime());
      super.a(mouseX, mouseY, partialTicks);
      if (this.draggingEntry == null) return;
      int x = this.left + this.b / 2 - this.v_() / 2;
      int y = Math.max(this.d, Math.min(this.bottom - this.slotHeight, mouseY - this.slotHeight / 2));
      Gui.drawRect(x, y, x + this.v_(), y + this.slotHeight, 0xA0000000);
      this.draggingEntry.drawEntry(this.dragPressRow, x, y, this.v_(), this.slotHeight - 4, mouseX, mouseY, false);
      int originalY = this.d + 4 - this.getAmountScrolled() + this.headerPadding + this.dragPressRow * this.slotHeight;
      if (originalY + this.slotHeight > this.d && originalY < this.bottom) {
         Gui.drawRect(x, Math.max(this.d, originalY), x + this.v_(), Math.min(this.bottom, originalY + this.slotHeight), 0x40000000);
      }
      int targetY = this.d + 4 - this.getAmountScrolled() + this.headerPadding + this.dragTargetRow * this.slotHeight;
      if (this.dragTargetRow > this.dragPressRow) targetY += this.slotHeight;
      targetY = Math.max(this.d + 1, Math.min(this.bottom - 1, targetY));
      Gui.drawRect(x, targetY - 1, x + this.v_(), targetY + 1, 0xFFFFE080);
   }

   @Override
   public GuiListExtended.IGuiListEntry getListEntry(int var1) {
      if (var1 < this.serverListInternet.size()) {
         return (GuiListExtended.IGuiListEntry)this.serverListInternet.get(var1);
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

   public void func_148194_a(List<LanServerDetector.LanServer> var1) {
      this.serverListLan.clear();

      for (LanServerDetector.LanServer var3 : var1) {
         this.serverListLan.add(new ServerListEntryLanDetected(this.owner, var3));
      }
   }

   public void func_148195_a(ServerList var1) {
      this.resetRowDrag();
      this.serverListInternet.clear();

      for (int var2 = 0; var2 < var1.countServers(); var2++) {
         ServerData var3 = var1.getServerData(var2);
         if (var3.recoveredField3389) {
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
