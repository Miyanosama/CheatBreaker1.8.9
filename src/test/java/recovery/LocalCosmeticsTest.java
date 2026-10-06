package recovery;

import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import com.cheatbreaker.client.util.cosmetic.LocalCosmetics;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

public class LocalCosmeticsTest extends TestCase {
    public void testSelectionReplacementAndUnequipSurviveRestart() throws Exception {
        File directory = new File(".target/local-cosmetics-test-" + System.nanoTime());
        File config = new File(directory, "selection.properties");
        try {
            LocalCosmetics local = new LocalCosmetics(config);
            assertNull(local.getEquipped(CosmeticType.CAPE));
            List<ClientResourceManager> capes = new ArrayList<>();
            ClientResourceManager wings = null;
            for (ClientResourceManager cosmetic : local.getCosmetics()) {
                if (cosmetic.method_20848() == CosmeticType.CAPE) capes.add(cosmetic);
                else wings = cosmetic;
            }
            assertTrue(capes.size() > 1);
            assertNotNull(wings);
            ClientResourceManager importedCape = capes.get(capes.size() - 1);
            assertTrue(importedCape.method_20859().getResourcePath().startsWith("client/capes/imported/"));
            local.toggle(capes.get(0));
            local.toggle(wings);
            local.toggle(importedCape);
            assertFalse(capes.get(0).method_20849());
            assertSame(importedCape, local.getEquipped(CosmeticType.CAPE));
            LocalCosmetics restarted = new LocalCosmetics(config);
            assertEquals(importedCape.method_20859(), restarted.getEquipped(CosmeticType.CAPE).method_20859());
            assertEquals(wings.method_20859(), restarted.getEquipped(CosmeticType.WINGS).method_20859());
            restarted.toggle(restarted.getEquipped(CosmeticType.CAPE));
            restarted.toggle(restarted.getEquipped(CosmeticType.WINGS));
            restarted = new LocalCosmetics(config);
            assertNull(restarted.getEquipped(CosmeticType.CAPE));
            assertNull(restarted.getEquipped(CosmeticType.WINGS));
        } finally {
            Files.deleteIfExists(config.toPath());
            Files.deleteIfExists(new File(config.getPath() + ".tmp").toPath());
            Files.deleteIfExists(directory.toPath());
        }
    }

    public void testEveryCatalogEntryHasTextureAndWingsPreview() {
        LocalCosmetics local = new LocalCosmetics(new File(".target/no-local-cosmetics-config"));
        assertEquals(631, local.getCosmetics().size());
        for (ClientResourceManager cosmetic : local.getCosmetics()) {
            assertNotNull(cosmetic.method_20858(), getClass().getResource(
                "/assets/minecraft/" + cosmetic.method_20859().getResourcePath()));
            if (cosmetic.method_20848() == CosmeticType.WINGS) {
                assertNotNull(cosmetic.method_20858(), getClass().getResource(
                    "/assets/minecraft/" + cosmetic.method_20850().getResourcePath()));
            }
        }
    }
}
