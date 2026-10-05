package net.minecraft.util;

import com.cheatbreaker.client.websocket.shared.WSPacketServerUpdate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public abstract class ChatComponentStyle implements IChatComponent {
   public EnumParticleTypes field_0003;
   public Vec3i field_0000;
   public WSPacketServerUpdate field_0001;
   public ChatStyle style;
   public List<IChatComponent> a = Lists.newArrayList();

   @Override
   public IChatComponent setChatStyle(ChatStyle var1) {
      this.style = var1;

      for (IChatComponent var3 : this.a) {
         var3.getChatStyle().setParentStyle(this.getChatStyle());
      }

      return this;
   }

   @Override
   public int hashCode() {
      return 31 * this.style.hashCode() + this.a.hashCode();
   }

   public static Iterator<IChatComponent> createDeepCopyIterator(Iterable<IChatComponent> var0) {
      Iterator var1 = Iterators.concat(Iterators.transform(var0.iterator(), new ChatComponentStyle$1()));
      return Iterators.transform(var1, new ChatComponentStyle$2());
   }

   @Override
   public String getUnformattedText() {
      StringBuilder var1 = new StringBuilder();

      for (IChatComponent var3 : this) {
         var1.append(var3.getUnformattedTextForChat());
      }

      return var1.toString();
   }

   @Override
   public IChatComponent appendSibling(IChatComponent var1) {
      var1.getChatStyle().setParentStyle(this.getChatStyle());
      this.a.add(var1);
      return this;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ChatComponentStyle)) {
         return false;
      } else {
         ChatComponentStyle var2 = (ChatComponentStyle)var1;
         return this.a.equals(var2.a) && this.getChatStyle().equals(var2.getChatStyle());
      }
   }

   @Override
   public String toString() {
      return "BaseComponent{style=" + this.style + ", siblings=" + this.a + '}';
   }

   @Override
   public ChatStyle getChatStyle() {
      if (this.style == null) {
         this.style = new ChatStyle();

         for (IChatComponent var2 : this.a) {
            var2.getChatStyle().setParentStyle(this.style);
         }
      }

      return this.style;
   }

   @Override
   public List<IChatComponent> getSiblings() {
      return this.a;
   }

   @Override
   public Iterator<IChatComponent> iterator() {
      return Iterators.concat(Iterators.forArray(new ChatComponentStyle[]{this}), createDeepCopyIterator(this.a));
   }

   @Override
   public IChatComponent appendText(String var1) {
      return this.appendSibling(new ChatComponentText(var1));
   }

   @Override
   public String getFormattedText() {
      StringBuilder var1 = new StringBuilder();

      for (IChatComponent var3 : this) {
         var1.append(var3.getChatStyle().getFormattingCode());
         var1.append(var3.getUnformattedTextForChat());
         var1.append(EnumChatFormatting.RESET);
      }

      return var1.toString();
   }
}
