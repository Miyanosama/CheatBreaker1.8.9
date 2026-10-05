package net.minecraft.client.util;

import com.google.common.collect.Lists;
import io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder;
import java.util.List;
import net.minecraft.client.renderer.EntityRenderer$1;
import org.apache.commons.lang3.StringUtils;

public class JsonException$Entry {
   public List<String> field_151375_b;
   public EntityRenderer$1 field_0003;
   public String field_151376_a = null;
   public SpdyHeaderBlockRawDecoder field_0002;

   public String func_151372_b() {
      return StringUtils.join(this.field_151375_b, "->");
   }

   public JsonException$Entry() {
      this.field_151375_b = Lists.newArrayList();
   }

   @Override
   public String toString() {
      return this.field_151376_a != null
         ? (!this.field_151375_b.isEmpty() ? this.field_151376_a + " " + this.func_151372_b() : this.field_151376_a)
         : (!this.field_151375_b.isEmpty() ? "(Unknown file) " + this.func_151372_b() : "(Unknown file)");
   }

   public void func_151373_a(String var1) {
      this.field_151375_b.add(0, var1);
   }
}
