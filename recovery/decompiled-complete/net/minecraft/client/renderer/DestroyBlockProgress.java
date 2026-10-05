package net.minecraft.client.renderer;

import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.ssl.OpenSslSessionStats;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.util.BlockPos;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$1;

public class DestroyBlockProgress {
   public OpenSslSessionStats field_0003;
   public int createdAtCloudUpdateTick;
   public HttpObjectAggregator field_0002;
   public BlockPos position;
   public ModelHumanoidHead field_0000;
   public int miningPlayerEntId;
   public LogBrokerMonitor$1 field_0007;
   public int partialBlockProgress;

   public BlockPos getPosition() {
      return this.position;
   }

   public void setCloudUpdateTick(int var1) {
      this.createdAtCloudUpdateTick = var1;
   }

   public int getPartialBlockDamage() {
      return this.partialBlockProgress;
   }

   public DestroyBlockProgress(int var1, BlockPos var2) {
      this.miningPlayerEntId = var1;
      this.position = var2;
   }

   public void setPartialBlockDamage(int var1) {
      if (var1 > 10) {
         var1 = 10;
      }

      this.partialBlockProgress = var1;
   }

   public int getCreationCloudUpdateTick() {
      return this.createdAtCloudUpdateTick;
   }
}
