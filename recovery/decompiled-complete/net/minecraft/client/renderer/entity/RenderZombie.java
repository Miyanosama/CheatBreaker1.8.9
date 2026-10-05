package net.minecraft.client.renderer.entity;

import com.google.common.collect.Lists;
import io.netty.channel.FixedRecvByteBufAllocator$HandleImpl;
import io.netty.util.concurrent.ImmediateEventExecutor$ImmediatePromise;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.optifine.expr.FunctionType;

public class RenderZombie extends RenderBiped<EntityZombie> {
   public static ResourceLocation zombieVillagerTextures = new ResourceLocation("textures/entity/zombie/zombie_villager.png");
   public List<LayerRenderer<EntityZombie>> field_177121_n;
   public static ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
   public FixedRecvByteBufAllocator$HandleImpl field_0001;
   public ModelBiped field_82434_o;
   public List<LayerRenderer<EntityZombie>> field_177122_o;
   public ImmediateEventExecutor$ImmediatePromise field_0007;
   public FunctionType field_0004;
   public ModelZombieVillager zombieVillagerModel;

   public void doRender(EntityZombie var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_82427_a(var1);
      super.doRender(var1, var2, var4, var6, var8, var9);
   }

   public RenderZombie(RenderManager var1) {
      super(var1, new ModelZombie(), 0.5F, 1.0F);
      LayerRenderer var2 = this.h.get(0);
      this.field_82434_o = this.a;
      this.zombieVillagerModel = new ModelZombieVillager();
      this.a(new LayerHeldItem(this));
      RenderZombie$1 var3 = new RenderZombie$1(this, this);
      this.a(var3);
      this.field_177122_o = Lists.newArrayList(this.h);
      if (var2 instanceof LayerCustomHead) {
         this.b(var2);
         this.a(new LayerCustomHead(this.zombieVillagerModel.e));
      }

      this.b(var3);
      this.a(new LayerVillagerArmor(this));
      this.field_177121_n = Lists.newArrayList(this.h);
   }

   public void func_82427_a(EntityZombie var1) {
      if (var1.isVillager()) {
         this.f = this.zombieVillagerModel;
         this.h = this.field_177121_n;
      } else {
         this.f = this.field_82434_o;
         this.h = this.field_177122_o;
      }

      this.a = (ModelBiped)this.f;
   }

   public void rotateCorpse(EntityZombie var1, float var2, float var3, float var4) {
      if (var1.isConverting()) {
         var3 += (float)(Math.cos(var1.W * 3.25) * Math.PI * 0.25);
      }

      super.rotateCorpse(var1, var2, var3, var4);
   }

   public ResourceLocation getEntityTexture(EntityZombie var1) {
      return var1.isVillager() ? zombieVillagerTextures : zombieTextures;
   }
}
