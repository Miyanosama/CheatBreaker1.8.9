package org.apache.log4j.spi;

import org.apache.log4j.or.ObjectRenderer;
import org.apache.log4j.or.RendererMap;

public interface RendererSupport {
   void setRenderer(Class var1, ObjectRenderer var2);

   RendererMap getRendererMap();
}
