package net.minecraft.entity.ai;

import io.netty.channel.embedded.EmbeddedEventLoop;
import io.netty.handler.codec.rtsp.RtspHeaders$Values;
import net.minecraft.world.chunk.storage.ChunkLoader;
import org.apache.log4j.helpers.PatternParser$CategoryPatternConverter;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$26;

public class EntityAITasks$EntityAITaskEntry {
   public EmbeddedEventLoop field_0006;
   public ChunkLoader field_0002;
   public RtspHeaders$Values field_0005;
   public int priority;
   public EntityAIBase action;
   public LogBrokerMonitor$26 field_0007;
   public PatternParser$CategoryPatternConverter field_0004;

   public EntityAITasks$EntityAITaskEntry(EntityAITasks var1, int var2, EntityAIBase var3) {
      this.field_75732_c = var1;
      super();
      this.priority = var2;
      this.action = var3;
   }
}
