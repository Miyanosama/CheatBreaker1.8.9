package org.slf4j;

import java.io.Serializable;
import java.util.Iterator;

public interface Marker extends Serializable {
   String field_0000 = "*";
   String field_0001 = "+";

   boolean hasReferences();

   void add(Marker var1);

   boolean remove(Marker var1);

   boolean hasChildren();

   String getName();

   Iterator<Marker> iterator();

   boolean contains(String var1);

   @Override
   int hashCode();

   boolean contains(Marker var1);

   @Override
   boolean equals(Object var1);
}
