package org.json;

import java.util.Iterator;

public class UnicodeCodePoints implements Iterable<Integer> {
   public String recoveredField1487;

   public UnicodeCodePoints(String var1) {
      this.recoveredField1487 = var1;
   }

   @Override
   public Iterator<Integer> iterator() {
      return new UnicodeCodePointIterator(this);
   }
}
