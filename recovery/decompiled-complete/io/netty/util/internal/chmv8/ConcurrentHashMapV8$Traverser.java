package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.ui.fading.MinMaxFade;
import io.netty.handler.stream.ChunkedWriteHandler$1;
import io.netty.util.DefaultAttributeMap;
import javax.vecmath.Matrix4f;
import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.server.management.ItemInWorldManager;
import net.optifine.util.StrUtils;

public class ConcurrentHashMapV8$Traverser<K, V> {
   public ConcurrentHashMapV8$Node<K, V> next;
   public ConcurrentHashMapV8$Node<K, V>[] tab;
   public ItemInWorldManager __junk6881768221797667323;
   public RenderGhast __junk1410931641582969565;
   public EntityMinecartChest __junk741042421742187833;
   public MinMaxFade __junk2450681784599880680;
   public Matrix4f __junk5707002942326905643;
   public DefaultAttributeMap __junk6303193717985706923;
   public int index;
   public int baseSize;
   public int baseIndex;
   public int baseLimit;
   public StrUtils __junk7227703871110592068;
   public AnimationMetadataSectionSerializer __junk4916780240395459176;
   public ChunkedWriteHandler$1 __junk4054196414890162592;

   public ConcurrentHashMapV8$Node<K, V> advance() {
      Object var1 = this.next;
      if (this.next != null) {
         var1 = ((ConcurrentHashMapV8$Node)var1).next;
      }

      while (true) {
         if (var1 != null) {
            return this.next = (ConcurrentHashMapV8$Node<K, V>)var1;
         }

         if (this.baseIndex >= this.baseLimit) {
            break;
         }

         ConcurrentHashMapV8$Node[] var2 = this.tab;
         if (this.tab == null) {
            break;
         }

         int var4;
         int var10000 = var4 = var2.length;
         int var3 = this.index;
         if (var10000 <= this.index || var3 < 0) {
            break;
         }

         if ((var1 = ConcurrentHashMapV8.tabAt(var2, this.index)) != null && ((ConcurrentHashMapV8$Node)var1).hash < 0) {
            if (var1 instanceof ConcurrentHashMapV8$ForwardingNode) {
               this.tab = ((ConcurrentHashMapV8$ForwardingNode)var1).nextTable;
               var1 = null;
               continue;
            }

            if (var1 instanceof ConcurrentHashMapV8$TreeBin) {
               var1 = ((ConcurrentHashMapV8$TreeBin)var1).first;
            } else {
               var1 = null;
            }
         }

         if ((this.index = this.index + this.baseSize) >= var4) {
            this.index = ++this.baseIndex;
         }
      }

      return this.next = null;
   }

   public ConcurrentHashMapV8$Traverser(ConcurrentHashMapV8$Node<K, V>[] var1, int var2, int var3, int var4) {
      this.tab = var1;
      this.baseSize = var2;
      this.baseIndex = this.index = var3;
      this.baseLimit = var4;
      this.next = null;
   }
}
