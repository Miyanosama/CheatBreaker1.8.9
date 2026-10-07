package recovery;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.ConfigManager;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.event.EventBus;
import com.cheatbreaker.client.module.type.AnimationsModule;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import junit.framework.TestCase;
import net.minecraft.client.Minecraft;
import sun.misc.Unsafe;

public class MinimalBobbingTest extends TestCase {
    private CheatBreaker previous;
    private Minecraft previousMinecraft;

    private static <T> T allocate(Class<T> type) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
    }

    @Override public void setUp() {
        previous = CheatBreaker.instance;
        previousMinecraft = Minecraft.theMinecraft;
        try {
            Minecraft.theMinecraft = allocate(Minecraft.class);
            CheatBreaker.instance = allocate(CheatBreaker.class);
            CheatBreaker.instance.configManager = allocate(ConfigManager.class);
            EventBus bus = allocate(EventBus.class);
            bus.recoveredField2205 = new ConcurrentHashMap<>();
            CheatBreaker.instance.recoveredField1568 = bus;
        } catch (Exception error) {
            CheatBreaker.instance = previous;
            Minecraft.theMinecraft = previousMinecraft;
            throw new IllegalStateException(error);
        }
    }

    @Override public void tearDown() {
        CheatBreaker.instance = previous;
        Minecraft.theMinecraft = previousMinecraft;
    }

    public void testToggleDefaultsAndRegistration() {
        AnimationsModule module = new AnimationsModule();
        assertTrue(module.getSettingsList().contains(module.minimalBobbing));
        assertEquals(Setting.Type.BOOLEAN, module.minimalBobbing.getType());
        assertEquals(Boolean.FALSE, module.minimalBobbing.getValue());
        assertTrue(module.shouldBobScreen(true));
        assertFalse(module.shouldBobScreen(false));
    }

    public void testCameraBobbingRespectsBothTogglesAndModuleState() {
        AnimationsModule module = new AnimationsModule();
        module.minimalBobbing.setValue(true, false);
        assertFalse(module.shouldBobScreen(true));
        assertFalse(module.shouldBobScreen(false));

        module.setDefaultState(false);
        assertTrue(module.shouldBobScreen(true));
        assertFalse(module.shouldBobScreen(false));

        module.setDefaultState(true);
        assertFalse(module.shouldBobScreen(true));
        module.minimalBobbing.setValue(false, false);
        assertTrue(module.shouldBobScreen(true));
        assertFalse(module.shouldBobScreen(false));
    }
}
