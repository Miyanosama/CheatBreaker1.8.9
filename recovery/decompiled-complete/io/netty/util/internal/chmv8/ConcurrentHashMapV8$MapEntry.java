package io.netty.util.internal.chmv8;

import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.util.internal.Cleaner0;
import java.util.Map.Entry;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.item.ItemSnow;
import net.minecraft.world.gen.structure.StructureVillagePieces$1;
import org.apache.log4j.helpers.ISO8601DateFormat;

public class ConcurrentHashMapV8$MapEntry<K, V> implements Entry<K, V> {
   public Cleaner0 __junk6864411851467272555;
   public ByteArrayDecoder __junk5111513917242179276;
   public ConcurrentHashMapV8<K, V> map;
   public BlockStoneSlab __junk1653108065708980540;
   public EncoderException __junk2435695562564648409;
   public ISO8601DateFormat __junk1288631934553865980;
   public StructureVillagePieces$1 __junk8527800531094011731;
   public V val;
   public ItemSnow __junk2248786070136018786;
   public K key;

   @Override
   public boolean equals(Object var1) {
      Object var2;
      Object var3;
      Entry var4;
      return var1 instanceof Entry
         && (var2 = (var4 = (Entry)var1).getKey()) != null
         && (var3 = var4.getValue()) != null
         && (var2 == this.key || var2.equals(this.key))
         && (var3 == this.val || var3.equals(this.val));
   }

   @Override
   public String toString() {
      return this.key + "=" + this.val;
   }

   @Override
   public V getValue() {
      return this.val;
   }

   public ConcurrentHashMapV8$MapEntry(K var1, V var2, ConcurrentHashMapV8<K, V> var3) {
      this.key = (K)var1;
      this.val = (V)var2;
      this.map = var3;
   }

   @Override
   public V setValue(V var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         Object var2 = this.val;
         this.val = (V)var1;
         this.map.put(this.key, (V)var1);
         return (V)var2;
      }
   }

   @Override
   public K getKey() {
      return this.key;
   }

   @Override
   public int hashCode() {
      return this.key.hashCode() ^ this.val.hashCode();
   }
}
