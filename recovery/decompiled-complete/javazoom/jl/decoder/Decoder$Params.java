package javazoom.jl.decoder;

import io.netty.bootstrap.AbstractBootstrap;
import net.minecraft.block.state.BlockStateBase$1;
import net.minecraft.network.play.server.S33PacketUpdateSign;

public class Decoder$Params implements Cloneable {
   public OutputChannels outputChannels = OutputChannels.BOTH;
   public BlockStateBase$1 __junk111504070511346001;
   public Equalizer equalizer = new Equalizer();
   public AbstractBootstrap __junk7718067782107224386;
   public S33PacketUpdateSign __junk5271884112736496300;

   public void setOutputChannels(OutputChannels var1) {
      if (var1 == null) {
         throw new NullPointerException("out");
      } else {
         this.outputChannels = var1;
      }
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError(this + ": " + var2);
      }
   }

   public OutputChannels getOutputChannels() {
      return this.outputChannels;
   }

   public Equalizer getInitialEqualizerSettings() {
      return this.equalizer;
   }
}
