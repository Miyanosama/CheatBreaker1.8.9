package recovery;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackGui;
import com.cheatbreaker.client.ui.resourcepack.ResourcePackFolderScanner;
import com.cheatbreaker.client.ui.resourcepack.SelectedResourcePackEntry;
import com.cheatbreaker.client.util.ClientStartupListener;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import junit.framework.TestCase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ChatComponentText;
import sun.misc.Unsafe;

public class ResourcePackPersistenceTest extends TestCase {
    private static <T> T allocate(Class<T> type) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
    }

    private ResourcePackRepository repository(Path directory) throws Exception {
        ResourcePackRepository repository = allocate(ResourcePackRepository.class);
        repository.dirResourcepacks = directory.toFile();
        repository.repositoryEntries = new ArrayList<>();
        repository.repositoryEntriesAll = new ArrayList<>();
        return repository;
    }

    private ResourcePackRepository.Entry entry(ResourcePackRepository repository, String name, int format) {
        ResourcePackRepository.Entry entry = repository.new Entry(new java.io.File(repository.dirResourcepacks, name));
        entry.reResourcePack = new FolderResourcePack(entry.resourcePackFile);
        entry.rePackMetadataSection = new PackMetadataSection(new ChatComponentText(name), format);
        return entry;
    }

    public void testApplyWritesSelectionAndCompatibilityApprovalToOptions() throws Exception {
        Path directory = Files.createTempDirectory(Paths.get(".target"), "resource-pack-options-");
        try {
            RecordingMinecraft minecraft = allocate(RecordingMinecraft.class);
            minecraft.repository = repository(directory);
            DiskSettings settings = new DiskSettings();
            settings.optionsFile = directory.resolve("options.txt").toFile();
            settings.incompatibleResourcePacks.add("removed-pack");
            minecraft.gameSettings = settings;
            ResourcePackRepository.Entry compatible = entry(minecraft.repository, "a", 1);
            ResourcePackRepository.Entry otherFormat = entry(minecraft.repository, "z", 3);
            ResourcePackGui screen = allocate(ResourcePackGui.class);
            screen.j = minecraft;
            screen.recoveredField863 = minecraft.repository;
            screen.recoveredField870 = new GuiButton(0, 0, 0, "Folder");
            screen.recoveredField874 = new GuiButton(1, 0, 0, "Done");
            screen.recoveredField868 = Collections.emptyList();
            screen.recoveredField859 = allocate(SelectedResourcePackEntry.class);
            screen.recoveredField859.recoveredField701 = Arrays.asList(otherFormat, compatible);

            screen.actionPerformed(screen.recoveredField874);

            DiskSettings restored = new DiskSettings();
            restored.optionsFile = settings.optionsFile;
            restored.method_01273();
            assertEquals(Arrays.asList("a", "z"), restored.resourcePacks);
            assertEquals(Collections.singletonList("z"), restored.incompatibleResourcePacks);
            assertTrue(minecraft.refreshed);
        } finally {
            Files.deleteIfExists(directory.resolve("options.txt"));
            Files.delete(directory);
        }
    }

    public void testStartupPreservesSavedPackPriority() throws Exception {
        Path directory = Files.createTempDirectory(Paths.get(".target"), "resource-pack-order-");
        Minecraft previousScanner = ResourcePackFolderScanner.recoveredField1954;
        try {
            RecordingMinecraft minecraft = allocate(RecordingMinecraft.class);
            minecraft.gameSettings = new DiskSettings();
            minecraft.gameSettings.resourcePacks.addAll(Arrays.asList("z", "a"));
            minecraft.repository = repository(directory);
            ResourcePackRepository.Entry z = entry(minecraft.repository, "z", 1);
            ResourcePackRepository.Entry a = entry(minecraft.repository, "a", 1);
            minecraft.repository.repositoryEntries.addAll(Arrays.asList(z, a));
            minecraft.repository.repositoryEntriesAll.addAll(Arrays.asList(a, z));
            ResourcePackFolderScanner.recoveredField1954 = minecraft;
            ClientStartupListener listener = allocate(ClientStartupListener.class);
            listener.recoveredField3563 = minecraft;

            listener.method_28652(null);

            assertEquals(Arrays.asList(z, a), minecraft.repository.getRepositoryEntries());
            assertTrue(minecraft.refreshed);
        } finally {
            ResourcePackFolderScanner.recoveredField1954 = previousScanner;
            Files.delete(directory);
        }
    }

    private static class RecordingMinecraft extends Minecraft {
        ResourcePackRepository repository;
        boolean refreshed;
        RecordingMinecraft() { super(null); }
        @Override public ResourcePackRepository getResourcePackRepository() { return repository; }
        @Override public void refreshResources() { refreshed = true; }
        @Override public void displayGuiScreen(GuiScreen screen) { currentScreen = screen; }
    }

    private static class DiskSettings extends GameSettings {
        @Override public void saveOfOptions() { }
        @Override public void loadOfOptions() { }
        @Override public void sendSettingsToServer() { }
    }
}
