package recovery;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import java.lang.reflect.Field;
import junit.framework.TestCase;
import net.minecraft.client.settings.GameSettings;
import sun.misc.Unsafe;

public class FullbrightBehaviorTest extends TestCase {
    private static <T> T withoutGameStartup(Class<T> type) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
    }

    public void testTogglePreservesUserBrightnessAndWorksWithoutFpsBoost() throws Exception {
        CheatBreaker previous = CheatBreaker.instance;
        try {
            GameSettings game = withoutGameStartup(GameSettings.class);
            game.gammaSetting = 0.37F;
            CheatBreaker.instance = null;
            assertEquals(0.37F, game.method_01290(), 0.0F);

            CheatBreaker client = withoutGameStartup(CheatBreaker.class);
            GlobalSettings settings = withoutGameStartup(GlobalSettings.class);
            Setting fullbright = withoutGameStartup(Setting.class);
            Setting fpsBoost = withoutGameStartup(Setting.class);
            client.globalSettings = settings;
            settings.recoveredField486 = fullbright;
            settings.recoveredField563 = fpsBoost;
            fpsBoost.recoveredField3086 = Boolean.FALSE;
            CheatBreaker.instance = client;

            fullbright.recoveredField3086 = Boolean.TRUE;
            assertEquals(100.0F, game.method_01290(), 0.0F);
            assertEquals(0.37F, game.gammaSetting, 0.0F);
            fullbright.recoveredField3086 = Boolean.FALSE;
            assertEquals(0.37F, game.method_01290(), 0.0F);
            game.gammaSetting = 0.8F;
            fullbright.recoveredField3086 = Boolean.TRUE;
            assertEquals(100.0F, game.method_01290(), 0.0F);
            fullbright.recoveredField3086 = Boolean.FALSE;
            assertEquals(0.8F, game.method_01290(), 0.0F);
        } finally {
            CheatBreaker.instance = previous;
        }
    }
}
