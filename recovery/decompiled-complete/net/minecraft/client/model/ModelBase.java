package net.minecraft.client.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.BlockLog$1;
import net.minecraft.client.particle.EffectRenderer$1;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.gen.structure.StructureOceanMonument$StartMonument;
import net.optifine.util.MathUtils;

public abstract class ModelBase {
   public MathUtils field_0012;
   public boolean r = true;
   public DataWatcher field_0006;
   public int t;
   public boolean isRiding;
   public StructureOceanMonument$StartMonument field_0009;
   public BlockLog$1 field_0011;
   public EffectRenderer$1 field_0002;
   public float p;
   public TileEntityRendererDispatcher field_0007;
   public int u;
   public List<ModelRenderer> boxList = Lists.newArrayList();
   public Map<String, TextureOffset> modelTextureMap = Maps.newHashMap();

   public void a(ModelBase var1) {
      this.p = var1.p;
      this.isRiding = var1.isRiding;
      this.r = var1.r;
   }

   public void setLivingAnimations(EntityLivingBase var1, float var2, float var3, float var4) {
   }

   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
   }

   public void setTextureOffset(String var1, int var2, int var3) {
      this.modelTextureMap.put(var1, new TextureOffset(var2, var3));
   }

   public TextureOffset getTextureOffset(String var1) {
      return this.modelTextureMap.get(var1);
   }

   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
   }

   public ModelRenderer getRandomModelBox(Random var1) {
      return this.boxList.get(var1.nextInt(this.boxList.size()));
   }

   public static void copyModelAngles(ModelRenderer var0, ModelRenderer var1) {
      var1.rotateAngleX = var0.rotateAngleX;
      var1.rotateAngleY = var0.rotateAngleY;
      var1.rotateAngleZ = var0.rotateAngleZ;
      var1.rotationPointX = var0.rotationPointX;
      var1.rotationPointY = var0.rotationPointY;
      var1.rotationPointZ = var0.rotationPointZ;
   }

   public ModelBase() {
      this.t = 64;
      this.u = 32;
   }
}
