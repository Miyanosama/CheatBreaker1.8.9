package javazoom.jl.decoder;

public class LayerIIDecoder extends LayerIDecoder implements FrameDecoder {
   @Override
   public void readScaleFactorSelection() {
      for (int var1 = 0; var1 < this.num_subbands; var1++) {
         ((LayerIIDecoder$SubbandLayer2)this.subbands[var1]).read_scalefactor_selection(this.stream, this.crc);
      }
   }

   @Override
   public void createSubbands() {
      if (this.mode == 3) {
         for (int var1 = 0; var1 < this.num_subbands; var1++) {
            this.subbands[var1] = new LayerIIDecoder$SubbandLayer2(var1);
         }
      } else if (this.mode == 1) {
         int var2;
         for (var2 = 0; var2 < this.header.intensity_stereo_bound(); var2++) {
            this.subbands[var2] = new LayerIIDecoder$SubbandLayer2Stereo(var2);
         }

         while (var2 < this.num_subbands) {
            this.subbands[var2] = new LayerIIDecoder$SubbandLayer2IntensityStereo(var2);
            var2++;
         }
      } else {
         for (int var3 = 0; var3 < this.num_subbands; var3++) {
            this.subbands[var3] = new LayerIIDecoder$SubbandLayer2Stereo(var3);
         }
      }
   }
}
