package org.apache.log4j.helpers;

import io.netty.handler.codec.compression.JZlibEncoder;
import io.netty.util.ResourceLeakException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumType;

public class NullEnumeration implements Enumeration {
   public VertexFormatElement$EnumType field_0001;
   public ResourceLeakException field_0003;
   public JZlibEncoder field_0000;
   public static NullEnumeration instance = new NullEnumeration();

   public static NullEnumeration getInstance() {
      return instance;
   }

   public boolean hasMoreElements() {
      return false;
   }

   public Object nextElement() {
      throw new NoSuchElementException();
   }
}
