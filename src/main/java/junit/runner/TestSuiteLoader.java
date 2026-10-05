package junit.runner;

public interface TestSuiteLoader {
   Class load(String var1) throws java.lang.ClassNotFoundException ;

   Class reload(Class var1) throws java.lang.ClassNotFoundException ;
}
