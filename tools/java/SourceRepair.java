import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.commons.*;
import com.google.gson.Gson;

/** Source-only repairs inferred from delegation bytecode; never loads client classes. */
public class SourceRepair implements Opcodes {
  public static void main(String[] args) throws Exception {
    Map<String,ClassNode> classes=new LinkedHashMap<>();
    try(ZipFile z=new ZipFile(args[0])) {
      Enumeration<? extends ZipEntry> entries=z.entries();
      while(entries.hasMoreElements()) {ZipEntry e=entries.nextElement();if(!e.getName().endsWith(".class"))continue;
        ByteArrayOutputStream b=new ByteArrayOutputStream();byte[] buf=new byte[8192];InputStream in=z.getInputStream(e);int l;while((l=in.read(buf))!=-1)b.write(buf,0,l);
        ClassNode c=new ClassNode();new ClassReader(b.toByteArray()).accept(c,0);classes.put(c.name,c);
      }
    }
    Map<String,String> renames=new HashMap<>(), aliases=new HashMap<>(), fieldNames=new HashMap<>();List<Object> audit=new ArrayList<>();
    int fieldIndex=0;
    for(ClassNode c:classes.values())for(FieldNode f:c.fields)if(f.name.matches("field_[0-9]+"))fieldNames.put(c.name+"/"+f.name+" "+f.desc,"recoveredField"+(fieldIndex++));
    aliases.put("recovered/unidentified/UnidentifiedClass3556","com/cheatbreaker/client/event/EventBus");
    for(String n:classes.keySet()) if(n.contains("$")&&!classes.containsKey(n.split("\\$")[0])&&!n.startsWith("com/cheatbreaker/client/event/EventBus$"))aliases.put(n,n.replace('$','_'));
    for(ClassNode c:classes.values()) {
      for(InnerClassNode it:c.innerClasses){ClassNode child=classes.get(it.name);if(child!=null&&(child.access&ACC_PUBLIC)!=0)it.access=(it.access&~(ACC_PRIVATE|ACC_PROTECTED))|ACC_PUBLIC;}
      boolean inner=false;for(InnerClassNode it:c.innerClasses)if(it.name.equals(c.name))inner=true;
      // Standalone captured objects need their fields and an explicit super call first.
      if(!inner)for(FieldNode f:c.fields)f.access&=~ACC_SYNTHETIC;
      for(MethodNode m:c.methods) {
        if((m.access&ACC_SYNTHETIC)==0||(m.access&ACC_STATIC)!=0||m.name.startsWith("lambda$")||m.name.startsWith("access$")||m.name.startsWith("<"))continue;
        MethodInsnNode target=null;boolean good=true;int count=0;
        for(AbstractInsnNode i:m.instructions.toArray()) {if(i.getOpcode()<0)continue;count++;
          if(i instanceof MethodInsnNode) {MethodInsnNode call=(MethodInsnNode)i;if(call.owner.equals(c.name)&&!call.name.equals("<init>")&&target==null)target=call;else good=false;}
          else if(i instanceof FieldInsnNode || i instanceof JumpInsnNode || i.getOpcode()==NEW || i.getOpcode()==ATHROW)good=false;
        }
        if(!good||target==null||count>32||Type.getArgumentTypes(m.desc).length!=Type.getArgumentTypes(target.desc).length||m.desc.equals(target.desc))continue;
        MethodNode impl=null;for(MethodNode t:c.methods)if(t.name.equals(target.name)&&t.desc.equals(target.desc))impl=t;
        if(impl==null)continue;
        m.access|=ACC_BRIDGE;
        if(!m.name.startsWith("method_")&&!m.name.equals(target.name)) {
          boolean collision=false;for(MethodNode t:c.methods)if(t!=impl&&t!=m&&t.name.equals(m.name)&&t.desc.equals(impl.desc))collision=true;
          if(!collision) {renames.put(c.name+"/"+impl.name+impl.desc,m.name);audit.add(Arrays.asList(c.name,impl.name,impl.desc,m.name,"typed implementation of synthetic delegation bridge"));}
        }
      }
    }
    Remapper remap=new Remapper(){
      public String map(String n){return aliases.getOrDefault(n,n);}
      public String mapMethodName(String o,String n,String d){return renames.getOrDefault(o+"/"+n+d,n);}
      String field(String o,String n,String d,Set<String> seen){if(!seen.add(o))return null;String value=fieldNames.get(o+"/"+n+" "+d);if(value!=null)return value;ClassNode c=classes.get(o);if(c!=null){if(c.superName!=null){value=field(c.superName,n,d,seen);if(value!=null)return value;}for(String i:c.interfaces){value=field(i,n,d,seen);if(value!=null)return value;}}return null;}
      public String mapFieldName(String o,String n,String d){String value=field(o,n,d,new HashSet<>());return value==null?n:value;}
    };
    try(JarOutputStream out=new JarOutputStream(new FileOutputStream(args[1]))) {
      for(ClassNode c:classes.values()) {ClassWriter w=new ClassWriter(0);c.accept(new ClassRemapper(w,remap));out.putNextEntry(new JarEntry(remap.mapType(c.name)+".class"));out.write(w.toByteArray());out.closeEntry();}
    }
    try(Writer w=new OutputStreamWriter(new FileOutputStream(args[2]),"UTF-8")){new Gson().toJson(Arrays.asList(aliases,audit),w);}
    System.out.println("Source class aliases="+aliases.size()+"; inferred typed bridge names="+renames.size());
  }
}
