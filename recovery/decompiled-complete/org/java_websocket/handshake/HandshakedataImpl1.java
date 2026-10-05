package org.java_websocket.handshake;

import com.cheatbreaker.client.ui.mainmenu.CosmeticsMenu;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.world.gen.structure.StructureVillagePieces$Church;
import net.optifine.shaders.SVertexFormat;
import net.optifine.shaders.uniform.CustomUniform;

public class HandshakedataImpl1 implements HandshakeBuilder {
   public C09PacketHeldItemChange field_0006;
   public CosmeticsMenu field_0005;
   public EntityPigZombie field_0001;
   public StructureVillagePieces$Church field_0007;
   public SVertexFormat field_0000;
   public CustomUniform field_0003;
   public TreeMap<String, String> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
   public byte[] content;

   @Override
   public void setContent(byte[] var1) {
      this.content = var1;
   }

   @Override
   public void put(String var1, String var2) {
      this.map.put(var1, var2);
   }

   @Override
   public boolean hasFieldValue(String var1) {
      return this.map.containsKey(var1);
   }

   @Override
   public byte[] getContent() {
      return this.content;
   }

   @Override
   public String getFieldValue(String var1) {
      String var2 = this.map.get(var1);
      return var2 == null ? "" : var2;
   }

   @Override
   public Iterator<String> iterateHttpFields() {
      return Collections.unmodifiableSet(this.map.keySet()).iterator();
   }
}
