package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class CategoryPath {
   public LinkedList _categoryElements = new LinkedList();

   public boolean isEmpty() {
      boolean var1 = false;
      if (this._categoryElements.size() == 0) {
         var1 = true;
      }

      return var1;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer(100);
      var1.append("\n");
      var1.append("===========================\n");
      var1.append("CategoryPath:                   \n");
      var1.append("---------------------------\n");
      var1.append("\nCategoryPath:\n\t");
      if (this.size() > 0) {
         for (int var2 = 0; var2 < this.size(); var2++) {
            var1.append(this.categoryElementAt(var2).toString());
            var1.append("\n\t");
         }
      } else {
         var1.append("<<NONE>>");
      }

      var1.append("\n");
      var1.append("===========================\n");
      return var1.toString();
   }

   public CategoryPath() {
   }

   public void addCategoryElement(CategoryElement var1) {
      this._categoryElements.addLast(var1);
   }

   public CategoryPath(String var1) {
      String var2 = var1;
      if (var1 == null) {
         var2 = "Debug";
      }

      var2 = var2.replace('/', '.');
      var2 = var2.replace('\\', '.');
      StringTokenizer var3 = new StringTokenizer(var2, ".");

      while (var3.hasMoreTokens()) {
         String var4 = var3.nextToken();
         this.addCategoryElement(new CategoryElement(var4));
      }
   }

   public int size() {
      return this._categoryElements.size();
   }

   public void removeAllCategoryElements() {
      this._categoryElements.clear();
   }

   public CategoryElement categoryElementAt(int var1) {
      return (CategoryElement)this._categoryElements.get(var1);
   }
}
