package io.netty.util.internal.chmv8;

import java.lang.ref.WeakReference;
import javax.vecmath.Vector3f;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.util.Vec3i;
import net.minecraft.world.gen.structure.StructureVillagePieces$House3;
import net.minecraft.world.storage.WorldInfo$1;
import net.optifine.reflect.ReflectorResolver;

public class ForkJoinTask$ExceptionNode extends WeakReference<ForkJoinTask<?>> {
   public StructureVillagePieces$House3 __junk6187768983923265641;
   public ReflectorResolver __junk3758823073582217831;
   public Vec3i __junk6566742357073854964;
   public WorldInfo$1 __junk8442882676585429809;
   public ForkJoinTask$ExceptionNode next;
   public Throwable ex;
   public long thrower;
   public Vector3f __junk3502898507051353336;
   public EntityBubbleFX __junk1027640529653678776;

   public ForkJoinTask$ExceptionNode(ForkJoinTask<?> var1, Throwable var2, ForkJoinTask$ExceptionNode var3) {
      super(var1, ForkJoinTask.access$000());
      this.ex = var2;
      this.next = var3;
      this.thrower = Thread.currentThread().getId();
   }
}
