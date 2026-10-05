package javazoom.jl.decoder;

import org.apache.log4j.config.PropertySetterException;

public class LayerIDecoder implements FrameDecoder {
   public int which_channels;
   public LayerIDecoder$Subband[] subbands;
   public int mode;
   public int num_subbands;
   public Header header;
   public SynthesisFilter filter2;
   public PropertySetterException __junk3561493627590054383;
   public Bitstream stream;
   public Crc16 crc = null;
   public SynthesisFilter filter1;
   public Obuffer buffer;

   @Override
   public void decodeFrame() {
      this.num_subbands = this.header.number_of_subbands();
      this.subbands = new LayerIDecoder$Subband[32];
      this.mode = this.header.mode();
      this.createSubbands();
      this.readAllocation();
      this.readScaleFactorSelection();
      if (this.crc != null || this.header.checksum_ok()) {
         this.readScaleFactors();
         this.readSampleData();
      }
   }

   public void readSampleData() {
      boolean var1 = false;
      boolean var2 = false;
      int var3 = this.header.mode();

      do {
         for (int var4 = 0; var4 < this.num_subbands; var4++) {
            var1 = this.subbands[var4].read_sampledata(this.stream);
         }

         do {
            for (int var5 = 0; var5 < this.num_subbands; var5++) {
               var2 = this.subbands[var5].put_next_sample(this.which_channels, this.filter1, this.filter2);
            }

            this.filter1.calculate_pcm_samples(this.buffer);
            if (this.which_channels == 0 && var3 != 3) {
               this.filter2.calculate_pcm_samples(this.buffer);
            }
         } while (!var2);
      } while (!var1);
   }

   public void createSubbands() {
      if (this.mode == 3) {
         for (int var1 = 0; var1 < this.num_subbands; var1++) {
            this.subbands[var1] = new LayerIDecoder$SubbandLayer1(var1);
         }
      } else if (this.mode == 1) {
         int var2;
         for (var2 = 0; var2 < this.header.intensity_stereo_bound(); var2++) {
            this.subbands[var2] = new LayerIDecoder$SubbandLayer1Stereo(var2);
         }

         while (var2 < this.num_subbands) {
            this.subbands[var2] = new LayerIDecoder$SubbandLayer1IntensityStereo(var2);
            var2++;
         }
      } else {
         for (int var3 = 0; var3 < this.num_subbands; var3++) {
            this.subbands[var3] = new LayerIDecoder$SubbandLayer1Stereo(var3);
         }
      }
   }

   public LayerIDecoder() {
      this.crc = new Crc16();
   }

   public void create(Bitstream var1, Header var2, SynthesisFilter var3, SynthesisFilter var4, Obuffer var5, int var6) {
      this.stream = var1;
      this.header = var2;
      this.filter1 = var3;
      this.filter2 = var4;
      this.buffer = var5;
      this.which_channels = var6;
   }

   public void readScaleFactorSelection() {
   }

   public void readAllocation() {
      for (int var1 = 0; var1 < this.num_subbands; var1++) {
         this.subbands[var1].read_allocation(this.stream, this.header, this.crc);
      }
   }

   public void readScaleFactors() {
      for (int var1 = 0; var1 < this.num_subbands; var1++) {
         this.subbands[var1].read_scalefactor(this.stream, this.header);
      }
   }
}
