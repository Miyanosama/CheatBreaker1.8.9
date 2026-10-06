package recovery;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.ConfigManager;
import com.cheatbreaker.client.event.EventBus;
import com.cheatbreaker.client.module.type.HurtcamModule;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import junit.framework.TestCase;
import sun.misc.Unsafe;

public class HurtcamModuleTest extends TestCase {
    private CheatBreaker previous;

    private static <T> T allocate(Class<T> type) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
    }

    @Override public void setUp() throws Exception {
        previous = CheatBreaker.instance;
        CheatBreaker.instance = allocate(CheatBreaker.class);
        CheatBreaker.instance.configManager = allocate(ConfigManager.class);
        EventBus bus = allocate(EventBus.class);
        bus.recoveredField2205 = new ConcurrentHashMap<>();
        CheatBreaker.instance.recoveredField1568 = bus;
    }

    @Override public void tearDown() { CheatBreaker.instance = previous; }

    public void testPercentageSliderDefaultsAndScaling() {
        HurtcamModule module = new HurtcamModule();
        assertFalse(module.isEnabled());
        assertTrue(module.getSettingsList().contains(module.intensity));
        assertEquals(0.0F, ((Number)module.intensity.recoveredField3097).floatValue(), 0.0F);
        assertEquals(100.0F, ((Number)module.intensity.recoveredField3099).floatValue(), 0.0F);
        assertEquals(1.0F, module.getIntensityMultiplier(), 0.0F);
        for (float percent : new float[]{0.0F, 25.0F, 50.0F, 75.0F, 100.0F}) {
            module.intensity.setValue(percent, false);
            assertEquals(percent / 100.0F, module.getIntensityMultiplier(), 0.0F);
        }
    }

    public void testInvalidStoredValuesCannotExceedCameraRange() {
        HurtcamModule module = new HurtcamModule();
        module.intensity.setValue(-10.0F, false);
        assertEquals(0.0F, module.getIntensityMultiplier(), 0.0F);
        module.intensity.setValue(200.0F, false);
        assertEquals(1.0F, module.getIntensityMultiplier(), 0.0F);
        module.intensity.setValue(Float.NaN, false);
        assertEquals(1.0F, module.getIntensityMultiplier(), 0.0F);
    }
}
