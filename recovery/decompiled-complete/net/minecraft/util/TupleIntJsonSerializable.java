package net.minecraft.util;

import com.cheatbreaker.client.ui.element.module.ModuleListElement;
import io.netty.handler.codec.http.HttpContentDecoder;
import io.netty.handler.codec.sctp.SctpMessageToMessageDecoder;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.client.particle.EntityFootStepFX$Factory;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.optifine.entity.model.ModelAdapterDragon;
import org.apache.log4j.RollingCalendar;

public class TupleIntJsonSerializable {
   public S37PacketStatistics field_0004;
   public RollingCalendar field_0007;
   public EntityFootStepFX$Factory field_0003;
   public ModuleListElement field_0006;
   public HttpContentDecoder field_0000;
   public BlockStaticLiquid field_0001;
   public int integerValue;
   public IJsonSerializable jsonSerializableValue;
   public ModelAdapterDragon field_0002;
   public SctpMessageToMessageDecoder field_0009;

   public void setJsonSerializableValue(IJsonSerializable var1) {
      this.jsonSerializableValue = var1;
   }

   public int getIntegerValue() {
      return this.integerValue;
   }

   public void setIntegerValue(int var1) {
      this.integerValue = var1;
   }

   public <T extends IJsonSerializable> T getJsonSerializableValue() {
      return (T)this.jsonSerializableValue;
   }
}
