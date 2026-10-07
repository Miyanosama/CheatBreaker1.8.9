package recovery;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import java.lang.reflect.Field;
import java.util.List;
import junit.framework.TestCase;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import sun.misc.Unsafe;

public class ParticlePhysicsTest extends TestCase {
   private CheatBreaker previous;
   private GlobalSettings settings;
   private CollisionWorld world;

   private static <T> T allocate(Class<T> type) throws Exception {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
   }

   @Override
   public void setUp() {
      try {
         initialize();
      } catch (Exception failure) {
         throw new RuntimeException(failure);
      }
   }

   private void initialize() throws Exception {
      previous = CheatBreaker.instance;
      CheatBreaker client = allocate(CheatBreaker.class);
      settings = allocate(GlobalSettings.class);
      settings.recoveredField563 = allocate(Setting.class);
      settings.recoveredField563.recoveredField3086 = Boolean.TRUE;
      settings.disableParticlePhysics = allocate(Setting.class);
      settings.disableParticlePhysics.recoveredField3086 = Boolean.TRUE;
      client.globalSettings = settings;
      CheatBreaker.instance = client;
      world = allocate(CollisionWorld.class);
      world.B = new NoopProfiler();
   }

   @Override
   public void tearDown() {
      CheatBreaker.instance = previous;
   }

   private <T extends Entity> T entity(Class<T> type) throws Exception {
      T entity = allocate(type);
      entity.o = world;
      entity.setEntityBoundingBox(new AxisAlignedBB(0, 2, 0, 1, 3, 1));
      entity.resetPositionToBB();
      return entity;
   }

   public void testEnabledSkipsWorldQueriesAndClearsOldCollisionFlagsForParticleSubclasses() throws Exception {
      EntityCrit2FX particle = entity(EntityCrit2FX.class);
      particle.C = particle.D = particle.recoveredField1643 = particle.recoveredField1644 = true;
      particle.d(1, -0.5, 2);
      assertEquals(0, world.queries);
      assertEquals(1.5, particle.s, 0.0);
      assertEquals(1.5, particle.t, 0.0);
      assertEquals(2.5, particle.u, 0.0);
      assertEquals(1.0, particle.getEntityBoundingBox().a, 0.0);
      assertFalse(particle.C);
      assertFalse(particle.D);
      assertFalse(particle.recoveredField1643);
      assertFalse(particle.recoveredField1644);
      assertFalse("The particle's native noClip flag must not be changed", particle.T);

      settings.disableParticlePhysics.recoveredField3086 = Boolean.FALSE;
      expectCollisionQuery(particle);
   }

   public void testMasterSwitchStartupAndNonParticleEntitiesKeepNativeCollisionPath() throws Exception {
      EntityFX particle = entity(EntityFX.class);
      settings.recoveredField563.recoveredField3086 = Boolean.FALSE;
      expectCollisionQuery(particle);
      settings.recoveredField563.recoveredField3086 = Boolean.TRUE;
      CheatBreaker.instance = null;
      expectCollisionQuery(particle);
      CheatBreaker.instance = previous;
      // Non-particle movement remains untouched even with both optimization switches on.
      CheatBreaker client = allocate(CheatBreaker.class);
      client.globalSettings = settings;
      CheatBreaker.instance = client;
      expectCollisionQuery(entity(EntityItem.class));
   }

   public void testGravityDragAgeAndNativeNoClipArePreserved() throws Exception {
      EntityFX particle = entity(EntityFX.class);
      particle.T = true;
      particle.g = 10;
      particle.i = 1.0F;
      particle.v = 0.5;
      particle.w = 0.25;
      particle.x = -0.5;
      particle.onUpdate();
      assertEquals(1, particle.f);
      assertEquals(1.0, particle.s, 0.0);
      assertEquals(2.21, particle.t, 0.000001);
      assertEquals(0.0, particle.u, 0.0);
      assertEquals(0.5 * 0.98F, particle.v, 0.000001);
      assertEquals(0.21 * 0.98F, particle.w, 0.000001);
      assertTrue(particle.T);
      assertEquals(0, world.queries);
      settings.disableParticlePhysics.recoveredField3086 = Boolean.FALSE;
      particle.d(0, 0.1, 0);
      assertTrue(particle.T);
      assertEquals(0, world.queries);
   }

   private void expectCollisionQuery(Entity entity) {
      int before = world.queries;
      try {
         entity.d(0.1, -0.1, 0.1);
         fail("Native movement must query world collisions");
      } catch (CollisionQuery expected) {
         assertEquals(before + 1, world.queries);
      }
   }

   private static class CollisionQuery extends RuntimeException { }

   private static class NoopProfiler extends Profiler {
      @Override public void startSection(String section) { }
   }

   private static class CollisionWorld extends World {
      int queries;
      private CollisionWorld() { super(null, null, null, null, true); }
      @Override public IChunkProvider createChunkProvider() { return null; }
      @Override public int getRenderDistanceChunks() { return 0; }
      @Override public List<AxisAlignedBB> a(Entity entity, AxisAlignedBB bounds) {
         queries++;
         throw new CollisionQuery();
      }
   }
}
