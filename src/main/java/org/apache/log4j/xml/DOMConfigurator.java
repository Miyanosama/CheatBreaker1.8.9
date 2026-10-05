package org.apache.log4j.xml;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DOMConfigurator implements Configurator {
   public static final String recoveredField1736 = "renderedClass";
   public static final String recoveredField1737 = "category";
   public static Class class$org$apache$log4j$spi$Filter;
   public static final String recoveredField1738 = "configDebug";
   public static final String recoveredField1739 = "categoryFactory";
   public static final String recoveredField1740 = "log4j:configuration";
   public static final String recoveredField1741 = "root-ref";
   public static final String recoveredField1742 = "renderer";
   public static Class class$org$apache$log4j$spi$ErrorHandler;
   public static final String recoveredField1743 = "appender-ref";
   public static final String recoveredField1744 = "logger";
   public static final String recoveredField1745 = "name";
   public static final String recoveredField1746 = "layout";
   public static final String recoveredField1747 = "debug";
   public static final String recoveredField1748 = "logger-ref";
   public static final String recoveredField1749 = "ref";
   public static final String recoveredField1750 = "appender";
   public static final String recoveredField1751 = "configuration";
   public Properties props;
   public static final String recoveredField1752 = "class";
   public static Class class$org$apache$log4j$spi$LoggerFactory;
   public static final String recoveredField1753 = "threshold";
   public static final String recoveredField1754 = "additivity";
   public static final String recoveredField1755 = "loggerFactory";
   public static final String recoveredField1756 = "value";
   public static final String recoveredField1757 = "priority";
   public LoggerFactory catFactory = null;
   public static final String recoveredField1758 = "filter";
   public Hashtable appenderBag = new Hashtable();
   public static final String recoveredField1759 = "param";
   public static final String recoveredField1760 = "";
   public static final String recoveredField1761 = "root";
   public static Class class$java$lang$String;
   public static final String recoveredField1762 = "javax.xml.parsers.DocumentBuilderFactory";
   public static final String recoveredField1763 = "reset";
   public static final String recoveredField1764 = "renderingClass";
   public LoggerRepository repository;
   public static final String recoveredField1765 = "errorHandler";
   public static final String recoveredField1766 = "level";
   public static final String recoveredField1767 = "throwableRenderer";
   public static Class[] ONE_STRING_PARAM = new Class[]{
      DOMConfigurator.class$java$lang$String == null
         ? (DOMConfigurator.class$java$lang$String = class$("java.lang.String"))
         : DOMConfigurator.class$java$lang$String
   };

   public void doConfigure(Reader var1, LoggerRepository var2) throws javax.xml.parsers.FactoryConfigurationError {
      DOMConfigurator$4 var3 = new DOMConfigurator$4(this, var1);
      this.doConfigure(var3, var2);
   }

   public Layout parseLayout(Element var1) {
      String var2 = this.subst(var1.getAttribute("class"));
      LogLog.debug("Parsing layout of class: \"" + var2 + "\"");

      try {
         Object var3 = Loader.loadClass(var2).newInstance();
         Layout var4 = (Layout)var3;
         PropertySetter var5 = new PropertySetter(var4);
         NodeList var6 = var1.getChildNodes();
         int var7 = var6.getLength();

         for (int var8 = 0; var8 < var7; var8++) {
            Node var9 = var6.item(var8);
            if (var9.getNodeType() == 1) {
               Element var10 = (Element)var9;
               String var11 = var10.getTagName();
               if (var11.equals("param")) {
                  this.setParameter(var10, var5);
               } else {
                  parseUnrecognizedElement(var3, var10, this.props);
               }
            }
         }

         var5.activate();
         return var4;
      } catch (Exception var12) {
         if (var12 instanceof InterruptedException || var12 instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         LogLog.error("Could not create the Layout. Reported error follows.", var12);
         return null;
      }
   }

   public void parseFilters(Element var1, Appender var2) {
      String var3 = this.subst(var1.getAttribute("class"));
      Filter var4 = (Filter)OptionConverter.instantiateByClassName(
         var3,
         class$org$apache$log4j$spi$Filter == null
            ? (class$org$apache$log4j$spi$Filter = class$("org.apache.log4j.spi.Filter"))
            : class$org$apache$log4j$spi$Filter,
         null
      );
      if (var4 != null) {
         PropertySetter var5 = new PropertySetter(var4);
         NodeList var6 = var1.getChildNodes();
         int var7 = var6.getLength();

         for (int var8 = 0; var8 < var7; var8++) {
            Node var9 = var6.item(var8);
            if (var9.getNodeType() == 1) {
               Element var10 = (Element)var9;
               String var11 = var10.getTagName();
               if (var11.equals("param")) {
                  this.setParameter(var10, var5);
               } else {
                  quietParseUnrecognizedElement(var4, var10, this.props);
               }
            }
         }

         var5.activate();
         LogLog.debug("Adding filter of type [" + var4.getClass() + "] to appender named [" + var2.getName() + "].");
         var2.addFilter(var4);
      }
   }

   public ThrowableRenderer parseThrowableRenderer(Element var1) {
      String var2 = this.subst(var1.getAttribute("class"));
      LogLog.debug("Parsing throwableRenderer of class: \"" + var2 + "\"");

      try {
         Object var3 = Loader.loadClass(var2).newInstance();
         ThrowableRenderer var4 = (ThrowableRenderer)var3;
         PropertySetter var5 = new PropertySetter(var4);
         NodeList var6 = var1.getChildNodes();
         int var7 = var6.getLength();

         for (int var8 = 0; var8 < var7; var8++) {
            Node var9 = var6.item(var8);
            if (var9.getNodeType() == 1) {
               Element var10 = (Element)var9;
               String var11 = var10.getTagName();
               if (var11.equals("param")) {
                  this.setParameter(var10, var5);
               } else {
                  parseUnrecognizedElement(var3, var10, this.props);
               }
            }
         }

         var5.activate();
         return var4;
      } catch (Exception var12) {
         if (var12 instanceof InterruptedException || var12 instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         LogLog.error("Could not create the ThrowableRenderer. Reported error follows.", var12);
         return null;
      }
   }

   public void parseChildrenOfLoggerElement(Element var1, Logger var2, boolean var3) {
      PropertySetter var4 = new PropertySetter(var2);
      var2.removeAllAppenders();
      NodeList var5 = var1.getChildNodes();
      int var6 = var5.getLength();

      for (int var7 = 0; var7 < var6; var7++) {
         Node var8 = var5.item(var7);
         if (var8.getNodeType() == 1) {
            Element var9 = (Element)var8;
            String var10 = var9.getTagName();
            if (var10.equals("appender-ref")) {
               Element var11 = (Element)var8;
               Appender var12 = this.findAppenderByReference(var11);
               String var13 = this.subst(var11.getAttribute("ref"));
               if (var12 != null) {
                  LogLog.debug("Adding appender named [" + var13 + "] to category [" + var2.getName() + "].");
               } else {
                  LogLog.debug("Appender named [" + var13 + "] not found.");
               }

               var2.addAppender(var12);
            } else if (var10.equals("level")) {
               this.parseLevel(var9, var2, var3);
            } else if (var10.equals("priority")) {
               this.parseLevel(var9, var2, var3);
            } else if (var10.equals("param")) {
               this.setParameter(var9, var4);
            } else {
               quietParseUnrecognizedElement(var2, var9, this.props);
            }
         }
      }

      var4.activate();
   }

   public String subst(String var1) {
      return subst(var1, this.props);
   }

   public void parseRenderer(Element var1) {
      String var2 = this.subst(var1.getAttribute("renderingClass"));
      String var3 = this.subst(var1.getAttribute("renderedClass"));
      if (this.repository instanceof RendererSupport) {
         RendererMap.addRenderer((RendererSupport)this.repository, var3, var2);
      }
   }

   public static void configure(String var0) throws javax.xml.parsers.FactoryConfigurationError {
      new DOMConfigurator().doConfigure(var0, LogManager.getLoggerRepository());
   }

   public static void setParameter(Element var0, PropertySetter var1, Properties var2) {
      String var3 = subst(var0.getAttribute("name"), var2);
      String var4 = var0.getAttribute("value");
      var4 = subst(OptionConverter.convertSpecialChars(var4), var2);
      var1.setProperty(var3, var4);
   }

   public void parseLevel(Element var1, Logger var2, boolean var3) {
      String var4 = var2.getName();
      if (var3) {
         var4 = "root";
      }

      String var5 = this.subst(var1.getAttribute("value"));
      LogLog.debug("Level value for " + var4 + " is  [" + var5 + "].");
      if (!"inherited".equalsIgnoreCase(var5) && !"null".equalsIgnoreCase(var5)) {
         String var6 = this.subst(var1.getAttribute("class"));
         if ("".equals(var6)) {
            var2.setLevel(OptionConverter.toLevel(var5, Level.DEBUG));
         } else {
            LogLog.debug("Desired Level sub-class: [" + var6 + ']');

            try {
               Class var7 = Loader.loadClass(var6);
               Method var8 = var7.getMethod("toLevel", ONE_STRING_PARAM);
               Level var9 = (Level)var8.invoke(null, var5);
               var2.setLevel(var9);
            } catch (Exception var10) {
               if (var10 instanceof InterruptedException || var10 instanceof InterruptedIOException) {
                  Thread.currentThread().interrupt();
               }

               LogLog.error("Could not create level [" + var5 + "]. Reported error follows.", var10);
               return;
            }
         }
      } else if (var3) {
         LogLog.error("Root level cannot be inherited. Ignoring directive.");
      } else {
         var2.setLevel(null);
      }

      LogLog.debug(var4 + " level set to " + var2.getLevel());
   }

   public void parseRoot(Element var1) {
      Logger var2 = this.repository.getRootLogger();
      synchronized (var2) {
         this.parseChildrenOfLoggerElement(var1, var2, true);
      }
   }

   public static Object parseElement(Element var0, Properties var1, Class var2) throws java.lang.Exception {
      String var3 = subst(var0.getAttribute("class"), var1);
      Object var4 = OptionConverter.instantiateByClassName(var3, var2, null);
      if (var4 != null) {
         PropertySetter var5 = new PropertySetter(var4);
         NodeList var6 = var0.getChildNodes();
         int var7 = var6.getLength();

         for (int var8 = 0; var8 < var7; var8++) {
            Node var9 = var6.item(var8);
            if (var9.getNodeType() == 1) {
               Element var10 = (Element)var9;
               String var11 = var10.getTagName();
               if (var11.equals("param")) {
                  setParameter(var10, var5, var1);
               } else {
                  parseUnrecognizedElement(var4, var10, var1);
               }
            }
         }

         return var4;
      } else {
         return null;
      }
   }

   public void parseErrorHandler(Element var1, Appender var2) {
      ErrorHandler var3 = (ErrorHandler)OptionConverter.instantiateByClassName(
         this.subst(var1.getAttribute("class")),
         class$org$apache$log4j$spi$ErrorHandler == null
            ? (class$org$apache$log4j$spi$ErrorHandler = class$("org.apache.log4j.spi.ErrorHandler"))
            : class$org$apache$log4j$spi$ErrorHandler,
         null
      );
      if (var3 != null) {
         var3.setAppender(var2);
         PropertySetter var4 = new PropertySetter(var3);
         NodeList var5 = var1.getChildNodes();
         int var6 = var5.getLength();

         for (int var7 = 0; var7 < var6; var7++) {
            Node var8 = var5.item(var7);
            if (var8.getNodeType() == 1) {
               Element var9 = (Element)var8;
               String var10 = var9.getTagName();
               if (var10.equals("param")) {
                  this.setParameter(var9, var4);
               } else if (var10.equals("appender-ref")) {
                  var3.setBackupAppender(this.findAppenderByReference(var9));
               } else if (var10.equals("logger-ref")) {
                  String var11 = var9.getAttribute("ref");
                  Logger var12 = this.catFactory == null ? this.repository.getLogger(var11) : this.repository.getLogger(var11, this.catFactory);
                  var3.setLogger(var12);
               } else if (var10.equals("root-ref")) {
                  Logger var13 = this.repository.getRootLogger();
                  var3.setLogger(var13);
               } else {
                  quietParseUnrecognizedElement(var3, var9, this.props);
               }
            }
         }

         var4.activate();
         var2.setErrorHandler(var3);
      }
   }

   public Appender findAppenderByName(Document var1, String var2) {
      Appender var3 = (Appender)this.appenderBag.get(var2);
      if (var3 != null) {
         return var3;
      } else {
         Element var4 = null;
         NodeList var5 = var1.getElementsByTagName("appender");

         for (int var6 = 0; var6 < var5.getLength(); var6++) {
            Node var7 = var5.item(var6);
            NamedNodeMap var8 = var7.getAttributes();
            Node var9 = var8.getNamedItem("name");
            if (var2.equals(var9.getNodeValue())) {
               var4 = (Element)var7;
               break;
            }
         }

         if (var4 == null) {
            LogLog.error("No appender named [" + var2 + "] could be found.");
            return null;
         } else {
            var3 = this.parseAppender(var4);
            if (var3 != null) {
               this.appenderBag.put(var2, var3);
            }

            return var3;
         }
      }
   }

   public void doConfigure(InputSource var1, LoggerRepository var2) throws javax.xml.parsers.FactoryConfigurationError {
      if (var1.getSystemId() == null) {
         var1.setSystemId("dummy://log4j.dtd");
      }

      DOMConfigurator$5 var3 = new DOMConfigurator$5(this, var1);
      this.doConfigure(var3, var2);
   }

   public void doConfigure(URL var1, LoggerRepository var2) {
      DOMConfigurator$2 var3 = new DOMConfigurator$2(this, var1);
      this.doConfigure(var3, var2);
   }

   public static void quietParseUnrecognizedElement(Object var0, Element var1, Properties var2) {
      try {
         parseUnrecognizedElement(var0, var1, var2);
      } catch (Exception var4) {
         if (var4 instanceof InterruptedException || var4 instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         LogLog.error("Error in extension content: ", var4);
      }
   }

   public Appender findAppenderByReference(Element var1) {
      String var2 = this.subst(var1.getAttribute("ref"));
      Document var3 = var1.getOwnerDocument();
      return this.findAppenderByName(var3, var2);
   }

   public static void configureAndWatch(String var0, long var1) {
      XMLWatchdog var3 = new XMLWatchdog(var0);
      var3.setDelay(var1);
      var3.start();
   }

   public void parseCategory(Element var1) {
      String var2 = this.subst(var1.getAttribute("name"));
      String var4 = this.subst(var1.getAttribute("class"));
      Logger var3;
      if ("".equals(var4)) {
         LogLog.debug("Retreiving an instance of org.apache.log4j.Logger.");
         var3 = this.catFactory == null ? this.repository.getLogger(var2) : this.repository.getLogger(var2, this.catFactory);
      } else {
         LogLog.debug("Desired logger sub-class: [" + var4 + ']');

         try {
            Class var5 = Loader.loadClass(var4);
            Method var6 = var5.getMethod("getLogger", ONE_STRING_PARAM);
            var3 = (Logger)var6.invoke(null, var2);
         } catch (InvocationTargetException var9) {
            if (var9.getTargetException() instanceof InterruptedException || var9.getTargetException() instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }

            LogLog.error("Could not retrieve category [" + var2 + "]. Reported error follows.", var9);
            return;
         } catch (Exception var10) {
            LogLog.error("Could not retrieve category [" + var2 + "]. Reported error follows.", var10);
            return;
         }
      }

      synchronized (var3) {
         boolean var11 = OptionConverter.toBoolean(this.subst(var1.getAttribute("additivity")), true);
         LogLog.debug("Setting [" + var3.getName() + "] additivity to [" + var11 + "].");
         var3.setAdditivity(var11);
         this.parseChildrenOfLoggerElement(var1, var3, false);
      }
   }

   public void parse(Element var1) {
      String var2 = var1.getTagName();
      if (!var2.equals("log4j:configuration")) {
         if (!var2.equals("configuration")) {
            LogLog.error("DOM element is - not a <log4j:configuration> element.");
            return;
         }

         LogLog.warn("The <configuration> element has been deprecated.");
         LogLog.warn("Use the <log4j:configuration> element instead.");
      }

      String var3 = this.subst(var1.getAttribute("debug"));
      LogLog.debug("debug attribute= \"" + var3 + "\".");
      if (!var3.equals("") && !var3.equals("null")) {
         LogLog.setInternalDebugging(OptionConverter.toBoolean(var3, true));
      } else {
         LogLog.debug("Ignoring debug attribute.");
      }

      String var4 = this.subst(var1.getAttribute("reset"));
      LogLog.debug("reset attribute= \"" + var4 + "\".");
      if (!"".equals(var4) && OptionConverter.toBoolean(var4, false)) {
         this.repository.resetConfiguration();
      }

      String var5 = this.subst(var1.getAttribute("configDebug"));
      if (!var5.equals("") && !var5.equals("null")) {
         LogLog.warn("The \"configDebug\" attribute is deprecated.");
         LogLog.warn("Use the \"debug\" attribute instead.");
         LogLog.setInternalDebugging(OptionConverter.toBoolean(var5, true));
      }

      String var6 = this.subst(var1.getAttribute("threshold"));
      LogLog.debug("Threshold =\"" + var6 + "\".");
      if (!"".equals(var6) && !"null".equals(var6)) {
         this.repository.setThreshold(var6);
      }

      Object var7 = null;
      Element var8 = null;
      org.w3c.dom.Node var9 = null;
      NodeList var10 = var1.getChildNodes();
      int var11 = var10.getLength();

      for (int var12 = 0; var12 < var11; var12++) {
         var9 = var10.item(var12);
         if (var9.getNodeType() == 1) {
            var8 = (Element)var9;
            var7 = var8.getTagName();
            if (var7.equals("categoryFactory") || var7.equals("loggerFactory")) {
               this.parseCategoryFactory(var8);
            }
         }
      }

      for (int var20 = 0; var20 < var11; var20++) {
         var9 = var10.item(var20);
         if (var9.getNodeType() == 1) {
            var8 = (Element)var9;
            var7 = var8.getTagName();
            if (var7.equals("category") || var7.equals("logger")) {
               this.parseCategory(var8);
            } else if (var7.equals("root")) {
               this.parseRoot(var8);
            } else if (var7.equals("renderer")) {
               this.parseRenderer(var8);
            } else if (var7.equals("throwableRenderer")) {
               if (this.repository instanceof ThrowableRendererSupport) {
                  ThrowableRenderer var13 = this.parseThrowableRenderer(var8);
                  if (var13 != null) {
                     ((ThrowableRendererSupport)this.repository).setThrowableRenderer(var13);
                  }
               }
            } else if (!var7.equals("appender") && !var7.equals("categoryFactory") && !var7.equals("loggerFactory")) {
               quietParseUnrecognizedElement(this.repository, var8, this.props);
            }
         }
      }
   }

   public static void configureAndWatch(String var0) {
      configureAndWatch(var0, 60000L);
   }

   public Appender parseAppender(Element var1) {
      String var2 = this.subst(var1.getAttribute("class"));
      LogLog.debug("Class name: [" + var2 + ']');

      try {
         Object var3 = Loader.loadClass(var2).newInstance();
         Appender var4 = (Appender)var3;
         PropertySetter var5 = new PropertySetter(var4);
         var4.setName(this.subst(var1.getAttribute("name")));
         NodeList var6 = var1.getChildNodes();
         int var7 = var6.getLength();

         for (int var8 = 0; var8 < var7; var8++) {
            Node var9 = var6.item(var8);
            if (var9.getNodeType() == 1) {
               Element var10 = (Element)var9;
               if (var10.getTagName().equals("param")) {
                  this.setParameter(var10, var5);
               } else if (var10.getTagName().equals("layout")) {
                  var4.setLayout(this.parseLayout(var10));
               } else if (var10.getTagName().equals("filter")) {
                  this.parseFilters(var10, var4);
               } else if (var10.getTagName().equals("errorHandler")) {
                  this.parseErrorHandler(var10, var4);
               } else if (var10.getTagName().equals("appender-ref")) {
                  String var11 = this.subst(var10.getAttribute("ref"));
                  if (var4 instanceof AppenderAttachable) {
                     AppenderAttachable var12 = (AppenderAttachable)var4;
                     LogLog.debug("Attaching appender named [" + var11 + "] to appender named [" + var4.getName() + "].");
                     var12.addAppender(this.findAppenderByReference(var10));
                  } else {
                     LogLog.error(
                        "Requesting attachment of appender named ["
                           + var11
                           + "] to appender named ["
                           + var4.getName()
                           + "] which does not implement org.apache.log4j.spi.AppenderAttachable."
                     );
                  }
               } else {
                  parseUnrecognizedElement(var3, var10, this.props);
               }
            }
         }

         var5.activate();
         return var4;
      } catch (Exception var13) {
         if (var13 instanceof InterruptedException || var13 instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         LogLog.error("Could not create an Appender. Reported error follows.", var13);
         return null;
      }
   }

   public void setParameter(Element var1, PropertySetter var2) {
      String var3 = this.subst(var1.getAttribute("name"));
      String var4 = var1.getAttribute("value");
      var4 = this.subst(OptionConverter.convertSpecialChars(var4));
      var2.setProperty(var3, var4);
   }

   public void parseCategoryFactory(Element var1) {
      String var2 = this.subst(var1.getAttribute("class"));
      if ("".equals(var2)) {
         LogLog.error("Category Factory tag class attribute not found.");
         LogLog.debug("No Category Factory configured.");
      } else {
         LogLog.debug("Desired category factory: [" + var2 + ']');
         Object var3 = OptionConverter.instantiateByClassName(
            var2,
            class$org$apache$log4j$spi$LoggerFactory == null
               ? (class$org$apache$log4j$spi$LoggerFactory = class$("org.apache.log4j.spi.LoggerFactory"))
               : class$org$apache$log4j$spi$LoggerFactory,
            null
         );
         if (var3 instanceof LoggerFactory) {
            this.catFactory = (LoggerFactory)var3;
         } else {
            LogLog.error("Category Factory class " + var2 + " does not implement org.apache.log4j.LoggerFactory");
         }

         PropertySetter var4 = new PropertySetter(var3);
         org.w3c.dom.Element var5 = null;
         org.w3c.dom.Node var6 = null;
         NodeList var7 = var1.getChildNodes();
         int var8 = var7.getLength();

         for (int var9 = 0; var9 < var8; var9++) {
            var6 = var7.item(var9);
            if (var6.getNodeType() == 1) {
               var5 = (Element)var6;
               if (var5.getTagName().equals("param")) {
                  this.setParameter((Element)var5, var4);
               } else {
                  quietParseUnrecognizedElement(var3, (Element)var5, this.props);
               }
            }
         }
      }
   }

   public static void parseUnrecognizedElement(Object var0, Element var1, Properties var2) throws java.lang.Exception {
      boolean var3 = false;
      if (var0 instanceof UnrecognizedElementHandler) {
         var3 = ((UnrecognizedElementHandler)var0).parseUnrecognizedElement(var1, var2);
      }

      if (!var3) {
         LogLog.warn("Unrecognized element " + var1.getNodeName());
      }
   }

   public static String subst(String var0, Properties var1) {
      try {
         return OptionConverter.substVars(var0, var1);
      } catch (IllegalArgumentException var3) {
         LogLog.warn("Could not perform variable substitution.", var3);
         return var0;
      }
   }

   public static void configure(URL var0) throws javax.xml.parsers.FactoryConfigurationError {
      new DOMConfigurator().doConfigure(var0, LogManager.getLoggerRepository());
   }

   public void doConfigure(String var1, LoggerRepository var2) {
      DOMConfigurator$1 var3 = new DOMConfigurator$1(this, var1);
      this.doConfigure(var3, var2);
   }

   public void doConfigure(Element var1, LoggerRepository var2) {
      this.repository = var2;
      this.parse(var1);
   }

   public void doConfigure(InputStream var1, LoggerRepository var2) throws javax.xml.parsers.FactoryConfigurationError {
      DOMConfigurator$3 var3 = new DOMConfigurator$3(this, var1);
      this.doConfigure(var3, var2);
   }

   public static void configure(Element var0) {
      DOMConfigurator var1 = new DOMConfigurator();
      var1.doConfigure(var0, LogManager.getLoggerRepository());
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public void doConfigure(DOMConfigurator.ParseAction var1, LoggerRepository var2) throws javax.xml.parsers.FactoryConfigurationError {
      DocumentBuilderFactory var3 = null;
      this.repository = var2;

      try {
         LogLog.debug("System property is :" + OptionConverter.getSystemProperty("javax.xml.parsers.DocumentBuilderFactory", null));
         var3 = DocumentBuilderFactory.newInstance();
         LogLog.debug("Standard DocumentBuilderFactory search succeded.");
         LogLog.debug("DocumentBuilderFactory is: " + var3.getClass().getName());
      } catch (FactoryConfigurationError var6) {
         Exception var5 = var6.getException();
         LogLog.debug("Could not instantiate a DocumentBuilderFactory.", var5);
         throw var6;
      }

      try {
         var3.setValidating(true);
         DocumentBuilder var4 = var3.newDocumentBuilder();
         var4.setErrorHandler(new SAXErrorHandler());
         var4.setEntityResolver(new Log4jEntityResolver());
         Document var9 = var1.parse(var4);
         this.parse(var9.getDocumentElement());
      } catch (Exception var7) {
         if (var7 instanceof InterruptedException || var7 instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         LogLog.error("Could not parse " + var1.toString() + ".", var7);
      }
   }

   public interface ParseAction {
      Document parse(DocumentBuilder var1) throws org.xml.sax.SAXException, java.io.IOException ;
   }
}
