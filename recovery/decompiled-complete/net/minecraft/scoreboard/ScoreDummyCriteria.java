package net.minecraft.scoreboard;

import io.netty.buffer.SlicedByteBuf;
import io.netty.handler.codec.marshalling.ChannelBufferByteOutput;
import java.util.List;
import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.entity.passive.EntityRabbit$RabbitMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityBrewingStand;

public class ScoreDummyCriteria implements IScoreObjectiveCriteria {
   public SlicedByteBuf field_0003;
   public ChannelBufferByteOutput field_0005;
   public EntityRabbit$RabbitMoveHelper field_0002;
   public ModelEnderMite field_0004;
   public TileEntityBrewingStand field_0000;
   public String dummyName;

   public ScoreDummyCriteria(String var1) {
      this.dummyName = var1;
      IScoreObjectiveCriteria.INSTANCES.put(var1, this);
   }

   @Override
   public int setScore(List<EntityPlayer> var1) {
      return 0;
   }

   @Override
   public String getName() {
      return this.dummyName;
   }

   @Override
   public boolean isReadOnly() {
      return false;
   }

   @Override
   public IScoreObjectiveCriteria$EnumRenderType getRenderType() {
      return IScoreObjectiveCriteria$EnumRenderType.INTEGER;
   }
}
