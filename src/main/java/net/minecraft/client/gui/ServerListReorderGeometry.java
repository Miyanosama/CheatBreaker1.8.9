package net.minecraft.client.gui;

/** Drag threshold and edge-scroll timing adapted from CheatBreakerZ. */
public final class ServerListReorderGeometry {
   private ServerListReorderGeometry() { }

   public static boolean isDragStart(int pressX, int pressY, int mouseX, int mouseY) {
      return Math.abs(mouseX - pressX) + Math.abs(mouseY - pressY) >= 6;
   }

   public static int targetRow(float rowsTop, int mouseY, int rowHeight, int rowCount) {
      return Math.max(0, Math.min(rowCount - 1, (int)((mouseY - rowsTop) / rowHeight)));
   }

   public static int previewRow(int row, int from, int to) {
      if (row == from) return -1;
      if (from < to && row > from && row <= to) return row - 1;
      if (to < from && row >= to && row < from) return row + 1;
      return row;
   }

   public static int scrollDirection(int mouseY, int top, int bottom) {
      return mouseY < top + 10 ? -1 : mouseY > bottom - 10 ? 1 : 0;
   }

   public static double scrollMultiplier(long dwellMs) {
      double seconds = dwellMs / 1000.0;
      if (seconds < 0.25) return 3.0;
      if (seconds < 0.5) return 3.0 + 4.0 * (seconds - 0.25);
      return Math.min(9.0, 4.0 + 2.0 * (seconds - 0.5));
   }
}
