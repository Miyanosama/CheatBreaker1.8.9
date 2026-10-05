package net.minecraft.village;

import io.netty.handler.codec.http.QueryStringEncoder$Param;
import io.netty.handler.codec.spdy.SpdySession$StreamComparator;
import net.minecraft.client.resources.GrassColorReloadListener;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.http.HttpPipelineRequest;
import recovered.unidentified.UnidentifiedClass1883;

public class Village$VillageAggressor {
   public UnidentifiedClass1883 field_0004;
   public GrassColorReloadListener field_0003;
   public HttpPipelineRequest field_0006;
   public int agressionTime;
   public EntityLivingBase agressor;
   public SpdySession$StreamComparator field_0008;
   public QueryStringEncoder$Param field_0005;
   public ModelManager field_0002;

   public Village$VillageAggressor(Village var1, EntityLivingBase var2, int var3) {
      this.field_75591_c = var1;
      super();
      this.agressor = var2;
      this.agressionTime = var3;
   }
}
