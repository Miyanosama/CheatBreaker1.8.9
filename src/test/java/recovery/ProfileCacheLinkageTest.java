package recovery;

import com.mojang.authlib.GameProfile;
import java.util.List;
import java.util.UUID;
import junit.framework.TestCase;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.PlayerProfileCache$1;

public class ProfileCacheLinkageTest extends TestCase {
    public void testProfileCacheInitializationAndLookupCallback() {
        assertEquals(List.class, PlayerProfileCache.TYPE.getRawType());
        assertEquals(PlayerProfileCache.ProfileEntry.class,
                PlayerProfileCache.TYPE.getActualTypeArguments()[0]);
        assertNull(PlayerProfileCache.TYPE.getOwnerType());
        GameProfile[] result = new GameProfile[1];
        PlayerProfileCache$1 callback = new PlayerProfileCache$1(result);
        GameProfile profile = new GameProfile(UUID.randomUUID(), "TestPlayer");
        callback.onProfileLookupSucceeded(profile);
        assertSame(profile, result[0]);
        callback.onProfileLookupFailed(profile, new Exception("lookup failed"));
        assertNull(result[0]);
    }
}
