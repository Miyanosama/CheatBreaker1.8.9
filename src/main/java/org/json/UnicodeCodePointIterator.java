package org.json;

import org.json.UnicodeCodePoints;

import java.util.Iterator;

public class UnicodeCodePointIterator implements Iterator<Integer> {
   public int recoveredField162;
   public UnicodeCodePoints recoveredField163;
   public int recoveredField164;

   @Override
   public void remove() {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean hasNext() {
      return this.recoveredField164 < this.recoveredField162;
   }

   public Integer next() {
      int var1 = this.recoveredField163.recoveredField1487.codePointAt(this.recoveredField164);
      this.recoveredField164 = this.recoveredField164 + Character.charCount(var1);
      return var1;
   }

   public UnicodeCodePointIterator(UnicodeCodePoints var1) {
      this.recoveredField163 = var1;
      this.recoveredField164 = 0;
      this.recoveredField162 = this.recoveredField163.recoveredField1487.length();
   }
}
