package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.ui.element.type.CosmeticSelectionElement;
import com.cheatbreaker.client.ui.overlay.element.InputFieldElement;
import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import junit.framework.TestCase;
import sun.misc.Unsafe;

public class CosmeticsSearchTest extends TestCase {
    private CosmeticsMenu menu() throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        CosmeticsMenu menu = (CosmeticsMenu)((Unsafe)field.get(null)).allocateInstance(CosmeticsMenu.class);
        menu.allCosmetics = new ArrayList<>();
        menu.recoveredField61 = new ArrayList<>();
        menu.previewOrder = new ArrayList<>();
        menu.searchField = new InputFieldElement(null, "Search cosmetics...", 0, 0);
        for (int index = 0; index < 12; index++) {
            String name = index % 2 == 0 ? "Blue_Butterfly " + index : "Red Cape " + index;
            ClientResourceManager cosmetic = new ClientResourceManager("local", name,
                CosmeticType.CAPE, 1F, index == 10, "client/capes/imported/" + index + ".png");
            menu.allCosmetics.add(new CosmeticSelectionElement(cosmetic, 1F));
        }
        menu.updateSearchResults();
        return menu;
    }

    public void testDisplayNameSearchResetsPageAndKeepsOriginalSelectionObjects() throws Exception {
        CosmeticsMenu menu = menu();
        menu.recoveredField63 = 2;
        menu.searchField.setText(" BLUE BUTTERFLY ");
        menu.updateSearchResults();
        assertEquals(6, menu.recoveredField61.size());
        assertEquals(0, menu.recoveredField63);
        assertSame(menu.allCosmetics.get(10), menu.recoveredField61.get(5));
        assertSame(menu.allCosmetics.get(10).recoveredField2291, menu.previewOrder.get(5));
        assertTrue(menu.recoveredField61.get(5).recoveredField2291.method_20849());
        menu.recoveredField63 = 1;
        menu.updateSearchResults();
        assertEquals(1, menu.recoveredField63);
    }

    public void testEmptyResultsAndClearingRestoreFullCatalog() throws Exception {
        CosmeticsMenu menu = menu();
        menu.searchField.setText("missing cosmetic");
        menu.updateSearchResults();
        assertTrue(menu.recoveredField61.isEmpty());
        assertTrue(menu.previewOrder.isEmpty());
        menu.searchField.setText("");
        menu.updateSearchResults();
        assertEquals(12, menu.recoveredField61.size());
        assertSame(menu.allCosmetics.get(10), menu.recoveredField61.get(10));
        assertTrue(menu.recoveredField61.get(10).recoveredField2291.method_20849());
        menu.searchField.setText("Blue_Butterfly 10");
        menu.updateSearchResults();
        assertEquals(1, menu.recoveredField61.size());
    }
    public void testCategorySwitchFiltersAndResetsPageWithoutLosingSelection() throws Exception {
        CosmeticsMenu menu = menu();
        ClientResourceManager wing = new ClientResourceManager("local", "Blue wing", CosmeticType.WINGS,
            0.125F, true, "client/wings/blue.png");
        menu.allCosmetics.add(new CosmeticSelectionElement(wing, 1F));
        Field type = CosmeticsMenu.class.getDeclaredField("selectedType");
        type.setAccessible(true);
        type.set(menu, CosmeticType.WINGS);
        Field query = CosmeticsMenu.class.getDeclaredField("searchQuery");
        query.setAccessible(true);
        query.set(menu, null);
        menu.recoveredField63 = 2;
        menu.updateSearchResults();
        assertEquals(0, menu.recoveredField63);
        assertEquals(1, menu.recoveredField61.size());
        assertSame(wing, menu.previewOrder.get(0));
        assertTrue(wing.method_20849());
        type.set(menu, CosmeticType.CAPE);
        query.set(menu, null);
        menu.updateSearchResults();
        assertEquals(12, menu.recoveredField61.size());
        assertTrue(wing.method_20849());
    }

}
