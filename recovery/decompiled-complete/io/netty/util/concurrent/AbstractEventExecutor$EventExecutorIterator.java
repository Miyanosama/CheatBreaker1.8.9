package io.netty.util.concurrent;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.minecraft.client.audio.SoundListSerializer;
import net.minecraft.client.particle.EntityDiggingFX$Factory;
import net.minecraft.realms.RealmsVertexFormat;

public class AbstractEventExecutor$EventExecutorIterator implements Iterator<EventExecutor> {
   public SoundListSerializer __junk2872728631659768082;
   public RealmsVertexFormat __junk4655056926415550101;
   public EntityDiggingFX$Factory __junk2121862423461216095;
   public boolean nextCalled;

   public AbstractEventExecutor$EventExecutorIterator(AbstractEventExecutor var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public boolean hasNext() {
      return !this.nextCalled;
   }

   public EventExecutor next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         this.nextCalled = true;
         return this.this$0;
      }
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("read-only");
   }
}
