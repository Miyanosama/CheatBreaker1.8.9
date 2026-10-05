package recovered.unidentified;

import io.netty.channel.sctp.oio.OioSctpServerChannel;
import io.netty.handler.codec.spdy.DefaultSpdyStreamFrame;
import javazoom.jl.converter.Converter;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer$EnumStatus;
import net.optifine.config.ConnectedParser$2;
import org.apache.log4j.jmx.AppenderDynamicMBean;

public enum UnidentifiedEnum4287 {
   field_0005,
   field_0002,
   field_0000;
   public Converter field_0008;
   public EntityPlayer$EnumStatus field_0004;
   // $VF: synthetic field
   public static UnidentifiedEnum4287[] field_0007 = new UnidentifiedEnum4287[]{UnidentifiedEnum4287.field_0000, UnidentifiedEnum4287.field_0002, field_0005};
   public AppenderDynamicMBean field_0001;
   public DefaultSpdyStreamFrame field_0009;
   public OioSctpServerChannel field_0006;
   public ConnectedParser$2 field_0003;
   public BlockFurnace field_0010;

   public static UnidentifiedEnum4287 method_25990(String var0) {
      return Enum.valueOf(UnidentifiedEnum4287.class, var0);
   }
}
