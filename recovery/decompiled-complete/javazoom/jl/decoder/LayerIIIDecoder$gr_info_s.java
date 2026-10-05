package javazoom.jl.decoder;

import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.item.ItemMinecart$1;

public class LayerIIIDecoder$gr_info_s {
   public int region0_count;
   public int scalefac_scale;
   public int mixed_block_flag;
   public int scalefac_compress;
   public int big_values;
   public int[] subblock_gain;
   public ItemMinecart$1 __junk5133066286979195170;
   public int window_switching_flag;
   public int preflag;
   public int[] table_select;
   public int block_type;
   public ViewFrustum __junk6486435621733765245;
   public int region1_count;
   public int global_gain;
   public int part2_3_length = 0;
   public int count1table_select;

   public LayerIIIDecoder$gr_info_s() {
      this.big_values = 0;
      this.global_gain = 0;
      this.scalefac_compress = 0;
      this.window_switching_flag = 0;
      this.block_type = 0;
      this.mixed_block_flag = 0;
      this.region0_count = 0;
      this.region1_count = 0;
      this.preflag = 0;
      this.scalefac_scale = 0;
      this.count1table_select = 0;
      this.table_select = new int[3];
      this.subblock_gain = new int[3];
   }
}
