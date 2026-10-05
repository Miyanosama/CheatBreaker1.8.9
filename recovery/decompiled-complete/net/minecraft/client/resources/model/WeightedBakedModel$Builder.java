package net.minecraft.client.resources.model;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import javax.vecmath.Tuple3d;
import net.minecraft.client.gui.ServerListEntryLanDetected;
import net.minecraft.creativetab.CreativeTabs$9;
import net.minecraft.network.NettyEncryptionTranslator;
import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;

public class WeightedBakedModel$Builder {
   public ServerListEntryLanDetected field_0003;
   public CreativeTabs$9 field_0005;
   public WorldGeneratorBonusChest field_0002;
   public NettyEncryptionTranslator field_0004;
   public List<WeightedBakedModel$MyWeighedRandomItem> listItems = Lists.newArrayList();
   public Tuple3d field_0001;

   public WeightedBakedModel build() {
      Collections.sort(this.listItems);
      return new WeightedBakedModel(this.listItems);
   }

   public WeightedBakedModel$Builder add(IBakedModel var1, int var2) {
      this.listItems.add(new WeightedBakedModel$MyWeighedRandomItem(var1, var2));
      return this;
   }

   public IBakedModel first() {
      return this.listItems.get(0).model;
   }
}
