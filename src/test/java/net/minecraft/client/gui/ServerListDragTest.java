package net.minecraft.client.gui;

import com.cheatbreaker.client.ui.serverlist.PinnedServerEntry;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import junit.framework.TestCase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagList;
import sun.misc.Unsafe;

public class ServerListDragTest extends TestCase {
   private static <T> T withoutGameStartup(Class<T> type) throws Exception {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
   }

   private ServerSelectionList list(RecordingMultiplayer owner) throws Exception {
      ServerSelectionList list = new ServerSelectionList(owner, null, 320, 300, 32, 220, 36);
      list.serverListInternet.add(withoutGameStartup(PinnedServerEntry.class));
      for (int index = 0; index < 3; index++) list.serverListInternet.add(withoutGameStartup(ServerListEntryNormal.class));
      return list;
   }

   public void testThresholdAndDropMoveOnlyOnReleaseAndResetDoubleClickClock() throws Exception {
      RecordingMultiplayer owner = withoutGameStartup(RecordingMultiplayer.class);
      ServerSelectionList list = list(owner);
      ServerListEntryNormal entry = (ServerListEntryNormal)list.getListEntry(1);
      entry.field_148298_f = 123L;
      list.noteRowPress(100, 80);
      list.tickRowDrag(102, 81, true, true, 1000L);
      assertFalse(list.isRowDragging());
      assertEquals(0, owner.moves);
      list.tickRowDrag(100, 120, true, true, 1010L);
      assertTrue(list.isRowDragging());
      assertFalse(list.getEnabled());
      assertEquals(0L, entry.field_148298_f);
      assertEquals(0, owner.moves);
      list.tickRowDrag(100, 120, false, true, 1020L);
      assertEquals(1, owner.moves);
      assertEquals(1, owner.from);
      assertEquals(2, owner.to);
      assertTrue(list.getEnabled());
      assertFalse(list.isRowDragging());
   }

   public void testPinnedLanAndIconCannotStartDraggingAndFocusLossCancels() throws Exception {
      RecordingMultiplayer owner = withoutGameStartup(RecordingMultiplayer.class);
      ServerSelectionList list = list(owner);
      for (int[] point : new int[][] {{100, 45}, {100, 185}, {20, 80}}) {
         list.noteRowPress(point[0], point[1]);
         list.tickRowDrag(100, 120, true, true, 1000L);
         assertFalse(list.isRowDragging());
      }
      list.noteRowPress(100, 80);
      list.tickRowDrag(100, 120, true, true, 1010L);
      assertTrue(list.isRowDragging());
      list.tickRowDrag(100, 120, false, false, 1020L);
      assertFalse(list.isRowDragging());
      assertEquals(0, owner.moves);
   }

   public void testDropAbovePinnedRowClampsToFirstSavedServer() throws Exception {
      RecordingMultiplayer owner = withoutGameStartup(RecordingMultiplayer.class);
      ServerSelectionList list = list(owner);
      list.noteRowPress(100, 150);
      list.tickRowDrag(100, 80, true, true, 1000L);
      list.tickRowDrag(100, 0, false, true, 1010L);
      assertEquals(3, owner.from);
      assertEquals(1, owner.to);
   }

   public void testEdgeScrollTimingAndThresholdMatchReference() {
      assertFalse(ServerListReorderGeometry.isDragStart(100, 100, 102, 103));
      assertTrue(ServerListReorderGeometry.isDragStart(100, 100, 102, 104));
      assertEquals(-1, ServerListReorderGeometry.scrollDirection(32, 32, 220));
      assertEquals(0, ServerListReorderGeometry.scrollDirection(100, 32, 220));
      assertEquals(1, ServerListReorderGeometry.scrollDirection(220, 32, 220));
      assertEquals(3.0, ServerListReorderGeometry.scrollMultiplier(250L), 0.0);
      assertEquals(4.0, ServerListReorderGeometry.scrollMultiplier(500L), 0.0);
      assertEquals(9.0, ServerListReorderGeometry.scrollMultiplier(5000L), 0.0);
   }

   public void testDraggingAtBottomScrollsALongList() throws Exception {
      RecordingMultiplayer owner = withoutGameStartup(RecordingMultiplayer.class);
      ServerSelectionList list = list(owner);
      for (int index = 0; index < 10; index++) list.serverListInternet.add(withoutGameStartup(ServerListEntryNormal.class));
      list.noteRowPress(100, 80);
      list.tickRowDrag(100, 215, true, true, 1000L);
      list.tickRowDrag(100, 215, true, true, 1100L);
      assertTrue(list.getAmountScrolled() > 0);
      assertFalse(list.getEnabled());
      list.tickRowDrag(100, 215, false, false, 1110L);
      assertEquals(0, owner.moves);
   }

   public void testPreviewMakesSpaceDownwardWithoutChangingStoredRows() throws Exception {
      RecordingMultiplayer owner = withoutGameStartup(RecordingMultiplayer.class);
      ServerSelectionList list = list(owner);
      Object first = list.getListEntry(1);
      Object second = list.getListEntry(2);
      list.noteRowPress(100, 80);
      list.tickRowDrag(100, 150, true, true, 1000L);
      assertEquals(0, list.previewRow(0));
      assertEquals(-1, list.previewRow(1));
      assertEquals(1, list.previewRow(2));
      assertEquals(2, list.previewRow(3));
      assertEquals(4, list.previewRow(4));
      assertSame(first, list.getListEntry(1));
      assertSame(second, list.getListEntry(2));
      assertEquals(0, owner.moves);
      list.resetRowDrag();
      for (int row = 0; row < list.getSize(); row++) assertEquals(row, list.previewRow(row));
   }

   public void testPreviewMakesSpaceUpwardAndTracksANewDropTarget() throws Exception {
      RecordingMultiplayer owner = withoutGameStartup(RecordingMultiplayer.class);
      ServerSelectionList list = list(owner);
      list.noteRowPress(100, 150);
      list.tickRowDrag(100, 80, true, true, 1000L);
      assertEquals(0, list.previewRow(0));
      assertEquals(2, list.previewRow(1));
      assertEquals(3, list.previewRow(2));
      assertEquals(-1, list.previewRow(3));
      assertEquals(4, list.previewRow(4));
      list.tickRowDrag(100, 120, true, true, 1010L);
      assertEquals(1, list.previewRow(1));
      assertEquals(3, list.previewRow(2));
      assertEquals(-1, list.previewRow(3));
      list.tickRowDrag(100, 120, false, true, 1020L);
      assertEquals(1, owner.moves);
      assertEquals(3, owner.from);
      assertEquals(2, owner.to);
   }

   public void testMoveUsesInsertionOrderAndProtectsPinnedServers() throws Exception {
      ServerList list = withoutGameStartup(ServerList.class);
      ServerData pinned = new ServerData(true, "Pinned", "pinned.invalid", false);
      ServerData first = new ServerData("First", "same.invalid", false);
      ServerData second = new ServerData("Second", "same.invalid", false);
      ServerData third = new ServerData("Third", "third.invalid", false);
      list.servers = new ArrayList<>(Arrays.asList(pinned, first, second, third));
      assertTrue(list.moveServer(list.servers.indexOf(first), list.servers.indexOf(third)));
      assertEquals(Arrays.asList(pinned, second, third, first), list.servers);
      assertTrue(list.moveServer(3, 1));
      assertEquals(Arrays.asList(pinned, first, second, third), list.servers);
      assertFalse(list.moveServer(1, 0));
      assertFalse(list.moveServer(-1, 2));
      assertFalse(list.moveServer(1, 1));
      assertFalse(list.moveServer(1, 5));
   }

   public void testReorderedServersAreSavedWithMetadataAndWithoutPinnedRows() throws Exception {
      Path parent = Files.createDirectories(Paths.get(".target", "test-data"));
      Path directory = Files.createTempDirectory(parent, "server-drag-");
      try {
         ServerList list = withoutGameStartup(ServerList.class);
         list.mc = withoutGameStartup(Minecraft.class);
         list.mc.mcDataDir = directory.toFile();
         ServerData first = new ServerData("First", "first.invalid", false);
         first.setResourceMode(ServerData.ServerResourceMode.ENABLED);
         list.servers = new ArrayList<>(Arrays.asList(new ServerData(true, "Pinned", "pinned.invalid", false),
            first, new ServerData("Second", "second.invalid", false), new ServerData("Third", "third.invalid", false)));
         assertTrue(list.moveServer(1, 3));
         list.saveServerList();
         NBTTagList saved = CompressedStreamTools.read(directory.resolve("servers.dat").toFile()).getTagList("servers", 10);
         assertEquals(3, saved.tagCount());
         assertEquals("Second", ServerData.getServerDataFromNBTCompound(saved.getCompoundTagAt(0)).serverName);
         assertEquals("Third", ServerData.getServerDataFromNBTCompound(saved.getCompoundTagAt(1)).serverName);
         ServerData restored = ServerData.getServerDataFromNBTCompound(saved.getCompoundTagAt(2));
         assertEquals("First", restored.serverName);
         assertEquals("first.invalid", restored.serverIP);
         assertEquals(ServerData.ServerResourceMode.ENABLED, restored.getResourceMode());
      } finally {
         Files.deleteIfExists(directory.resolve("servers.dat"));
         Files.deleteIfExists(directory.resolve("servers.dat_tmp"));
         Files.deleteIfExists(directory);
      }
   }

   private static final class RecordingMultiplayer extends GuiMultiplayer {
      int moves;
      int from;
      int to;
      RecordingMultiplayer() { super(null); }
      @Override public void moveServerRow(int from, int to) { this.moves++; this.from = from; this.to = to; }
   }
}
