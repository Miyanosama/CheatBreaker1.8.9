package recovered.unidentified;

import java.util.List;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f$Deserializer;
import net.minecraft.client.util.JsonException$1;
import net.minecraft.entity.monster.EntityIronGolem$AINearestAttackableTargetNonCreeper$1;
import net.minecraft.util.AxisAlignedBB;
import org.newsclub.net.unix.NarSystem;

public class UnidentifiedClass3475 extends UnidentifiedClass1472 {
   public ItemTransformVec3f$Deserializer field_0008;
   public double field_0001;
   public JsonException$1 field_0004;
   public double field_0005;
   public double field_0003;
   public BlockRendererDispatcher field_0006;
   public List<AxisAlignedBB> field_0007;
   public NarSystem field_0000;
   public EntityIronGolem$AINearestAttackableTargetNonCreeper$1 field_0002;

   public double method_21507() {
      return this.field_0001;
   }

   public UnidentifiedClass3475(List<AxisAlignedBB> var1, double var2, double var4, double var6) {
      this.field_0007 = var1;
      this.field_0003 = var2;
      this.field_0005 = var4;
      this.field_0001 = var6;
   }

   public List<AxisAlignedBB> method_21505() {
      return this.field_0007;
   }

   public double method_21506() {
      return this.field_0005;
   }

   public double method_21504() {
      return this.field_0003;
   }
}
