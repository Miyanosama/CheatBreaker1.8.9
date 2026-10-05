package javazoom.jl.decoder;

import net.minecraft.client.renderer.RenderGlobal$2;
import net.minecraft.realms.Realms;

public class Decoder implements DecoderErrors {
   public Decoder$Params params;
   public SynthesisFilter filter2;
   public LayerIDecoder l1decoder;
   public LayerIIDecoder l2decoder;
   public static Decoder$Params DEFAULT_PARAMS = new Decoder$Params();
   public Obuffer output;
   public RenderGlobal$2 __junk6560284497713761626;
   public int outputFrequency;
   public Equalizer equalizer = new Equalizer();
   public boolean initialized;
   public int outputChannels;
   public Realms __junk7538697863989167164;
   public SynthesisFilter filter1;
   public LayerIIIDecoder l3decoder;

   public void initialize(Header var1) {
      float var2 = 32700.0F;
      int var3 = var1.mode();
      int var4 = var1.layer();
      int var5 = var3 == 3 ? 1 : 2;
      if (this.output == null) {
         this.output = new SampleBuffer(var1.frequency(), var5);
      }

      float[] var6 = this.equalizer.getBandFactors();
      this.filter1 = new SynthesisFilter(0, var2, var6);
      if (var5 == 2) {
         this.filter2 = new SynthesisFilter(1, var2, var6);
      }

      this.outputChannels = var5;
      this.outputFrequency = var1.frequency();
      this.initialized = true;
   }

   public int getOutputFrequency() {
      return this.outputFrequency;
   }

   public DecoderException newDecoderException(int var1) {
      return new DecoderException(var1, null);
   }

   public Obuffer decodeFrame(Header var1, Bitstream var2) {
      if (!this.initialized) {
         this.initialize(var1);
      }

      int var3 = var1.layer();
      this.output.clear_buffer();
      FrameDecoder var4 = this.retrieveDecoder(var1, var2, var3);
      var4.decodeFrame();
      this.output.write_buffer(1);
      return this.output;
   }

   public Decoder(Decoder$Params var1) {
      if (var1 == null) {
         var1 = DEFAULT_PARAMS;
      }

      this.params = var1;
      Equalizer var2 = this.params.getInitialEqualizerSettings();
      if (var2 != null) {
         this.equalizer.setFrom(var2);
      }
   }

   public FrameDecoder retrieveDecoder(Header var1, Bitstream var2, int var3) {
      Object var4 = null;
      switch (var3) {
         case 1:
            if (this.l1decoder == null) {
               this.l1decoder = new LayerIDecoder();
               this.l1decoder.create(var2, var1, this.filter1, this.filter2, this.output, 0);
            }

            var4 = this.l1decoder;
            break;
         case 2:
            if (this.l2decoder == null) {
               this.l2decoder = new LayerIIDecoder();
               this.l2decoder.create(var2, var1, this.filter1, this.filter2, this.output, 0);
            }

            var4 = this.l2decoder;
            break;
         case 3:
            if (this.l3decoder == null) {
               this.l3decoder = new LayerIIIDecoder(var2, var1, this.filter1, this.filter2, this.output, 0);
            }

            var4 = this.l3decoder;
      }

      if (var4 == null) {
         throw this.newDecoderException(513, null);
      } else {
         return (FrameDecoder)var4;
      }
   }

   public void setOutputBuffer(Obuffer var1) {
      this.output = var1;
   }

   public int getOutputChannels() {
      return this.outputChannels;
   }

   public DecoderException newDecoderException(int var1, Throwable var2) {
      return new DecoderException(var1, var2);
   }

   public int getOutputBlockSize() {
      return 2304;
   }

   public Decoder() {
      this(null);
   }

   public static Decoder$Params getDefaultParams() {
      return (Decoder$Params)DEFAULT_PARAMS.clone();
   }

   public void setEqualizer(Equalizer var1) {
      if (var1 == null) {
         var1 = Equalizer.PASS_THRU_EQ;
      }

      this.equalizer.setFrom(var1);
      float[] var2 = this.equalizer.getBandFactors();
      if (this.filter1 != null) {
         this.filter1.setEQ(var2);
      }

      if (this.filter2 != null) {
         this.filter2.setEQ(var2);
      }
   }
}
