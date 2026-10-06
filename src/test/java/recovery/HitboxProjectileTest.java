package recovery;

import com.cheatbreaker.client.module.type.HitboxSettings;
import com.cheatbreaker.client.module.type.HitboxesModule;
import java.lang.reflect.Field;
import junit.framework.TestCase;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import sun.misc.Unsafe;

public class HitboxProjectileTest extends TestCase {
    private static <T> T allocate(Class<T> type) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
    }

    private HitboxesModule module() throws Exception {
        HitboxesModule module = allocate(HitboxesModule.class);
        module.recoveredField327 = allocate(HitboxSettings.class);
        module.recoveredField328 = allocate(HitboxSettings.class);
        module.recoveredField329 = allocate(HitboxSettings.class);
        module.recoveredField330 = allocate(HitboxSettings.class);
        module.recoveredField331 = allocate(HitboxSettings.class);
        return module;
    }

    public void testArrowsFireballsAndThrownItemsUseProjectileSettings() throws Exception {
        HitboxesModule module = module();
        Class<?>[] types = {EntityArrow.class, EntitySmallFireball.class, EntityLargeFireball.class,
                EntityWitherSkull.class, EntitySnowball.class, EntityEgg.class, EntityEnderPearl.class,
                EntityPotion.class, EntityExpBottle.class};
        for (Class<?> type : types) {
            assertSame(type.getSimpleName(), module.recoveredField329,
                    module.method_09734((Entity)allocate(type)));
        }
    }

    public void testOtherEntityCategoriesKeepTheirOwnSettings() throws Exception {
        HitboxesModule module = module();
        assertSame(module.recoveredField331, module.method_09734(allocate(EntityPlayerSP.class)));
        assertSame(module.recoveredField327, module.method_09734(allocate(EntityItem.class)));
        assertSame(module.recoveredField330, module.method_09734(allocate(EntityXPOrb.class)));
        assertSame(module.recoveredField328, module.method_09734(allocate(EntityPig.class)));
    }
}
