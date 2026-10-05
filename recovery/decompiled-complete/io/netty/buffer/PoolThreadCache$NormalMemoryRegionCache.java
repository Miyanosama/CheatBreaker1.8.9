package io.netty.buffer;

import io.netty.handler.codec.compression.ZlibUtil;
import io.netty.handler.codec.socks.SocksInitRequest;
import io.netty.handler.ssl.NotSslRecordException;
import javax.vecmath.TexCoord2f;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityPigZombie$AITargetAggressor;

public class PoolThreadCache$NormalMemoryRegionCache<T> extends PoolThreadCache$MemoryRegionCache<T> {
   public NotSslRecordException __junk5447169446297554531;
   public SocksInitRequest __junk7062277546276673600;
   public ZlibUtil __junk2712695437362608394;
   public EntityFallingBlock __junk2013771937841046943;
   public TexCoord2f __junk1853238273444594533;
   public EntityPigZombie$AITargetAggressor __junk6536140303873805613;

   @Override
   public void initBuf(PoolChunk<T> var1, long var2, PooledByteBuf<T> var4, int var5) {
      var1.initBuf(var4, var2, var5);
   }

   public PoolThreadCache$NormalMemoryRegionCache(int var1) {
      super(var1);
   }
}
