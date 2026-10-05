import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import java.util.zip.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.commons.*;
import com.google.gson.Gson;

/** Offline recovery only. Never loads or executes client classes. */
public class RecoveryTool implements Opcodes {
    static Map<String,ClassNode> classes = new LinkedHashMap<>();
    static Map<String,Map<Integer,String>> tables = new HashMap<>();
    static int folded, inlined;
    static AbstractInsnNode push(Object v) {
        if(v instanceof Integer){int i=(Integer)v;if(i>=-1&&i<=5)return new InsnNode(ICONST_0+i);if(i>=-128&&i<=127)return new IntInsnNode(BIPUSH,i);if(i>=-32768&&i<=32767)return new IntInsnNode(SIPUSH,i);}
        return new LdcInsnNode(v);
    }
    static Number number(AbstractInsnNode n){Object i=integer(n);if(i instanceof Number)return (Number)i;if(n instanceof LdcInsnNode && ((LdcInsnNode)n).cst instanceof Number)return (Number)((LdcInsnNode)n).cst;int op=n==null?-1:n.getOpcode();if(op==FCONST_0||op==FCONST_1||op==FCONST_2)return (float)(op-FCONST_0);if(op==DCONST_0||op==DCONST_1)return (double)(op-DCONST_0);if(op==LCONST_0||op==LCONST_1)return (long)(op-LCONST_0);return null;}
    static Object integer(AbstractInsnNode n) {
        if(n==null)return null;
        int op=n.getOpcode();
        if(op>=ICONST_M1 && op<=ICONST_5)return op-ICONST_0;
        if(n instanceof IntInsnNode && op!=NEWARRAY)return ((IntInsnNode)n).operand;
        if(n instanceof LdcInsnNode && ((LdcInsnNode)n).cst instanceof Integer)return ((LdcInsnNode)n).cst;
        return null;
    }
    static void load(String path)throws Exception {
        try(ZipFile z=new ZipFile(path)) {
            Enumeration<? extends ZipEntry> e=z.entries();
            while(e.hasMoreElements()) {
                ZipEntry en=e.nextElement(); if(!en.getName().endsWith(".class"))continue;
                ClassNode c=new ClassNode();new ClassReader(read(z.getInputStream(en))).accept(c,0);classes.put(c.name,c);
            }
        }
    }
    static byte[] read(InputStream in)throws Exception {try(InputStream r=in;ByteArrayOutputStream b=new ByteArrayOutputStream()){byte[] buf=new byte[65536];int n;while((n=r.read(buf))!=-1)b.write(buf,0,n);return b.toByteArray();}}
    static void normalize() {
        for(ClassNode c:classes.values())for(MethodNode m:c.methods) {
            boolean changed;
            do {changed=false;
                for(AbstractInsnNode n:m.instructions.toArray()) {
                    int op=n.getOpcode();
                    if(op==LAND||op==LOR||op==LXOR||op==LADD||op==LSUB||op==LMUL||op==LSHL||op==LSHR||op==LUSHR){AbstractInsnNode b=n.getPrevious(),a=b==null?null:b.getPrevious();Number x=number(a),y=number(b);if(x!=null&&y!=null){long v;switch(op){case LAND:v=x.longValue()&y.longValue();break;case LOR:v=x.longValue()|y.longValue();break;case LXOR:v=x.longValue()^y.longValue();break;case LADD:v=x.longValue()+y.longValue();break;case LSUB:v=x.longValue()-y.longValue();break;case LMUL:v=x.longValue()*y.longValue();break;case LSHL:v=x.longValue()<<(y.intValue()&63);break;case LSHR:v=x.longValue()>>(y.intValue()&63);break;default:v=x.longValue()>>>(y.intValue()&63);}m.instructions.set(n,push(v));m.instructions.remove(a);m.instructions.remove(b);folded++;changed=true;}continue;}
                    if(op==FMUL||op==FDIV||op==FADD||op==FSUB||op==DMUL||op==DDIV||op==DADD||op==DSUB){AbstractInsnNode b=n.getPrevious(),a=b==null?null:b.getPrevious();Number x=number(a),y=number(b);if(x!=null&&y!=null){Object v;if(op==FMUL)v=x.floatValue()*y.floatValue();else if(op==FDIV)v=x.floatValue()/y.floatValue();else if(op==FADD)v=x.floatValue()+y.floatValue();else if(op==FSUB)v=x.floatValue()-y.floatValue();else if(op==DMUL)v=x.doubleValue()*y.doubleValue();else if(op==DDIV)v=x.doubleValue()/y.doubleValue();else if(op==DADD)v=x.doubleValue()+y.doubleValue();else v=x.doubleValue()-y.doubleValue();m.instructions.set(n,push(v));m.instructions.remove(a);m.instructions.remove(b);folded++;changed=true;}continue;}
                    if(op!=IAND && op!=IOR && op!=IXOR && op!=IADD && op!=ISUB && op!=IMUL && op!=ISHL && op!=ISHR && op!=IUSHR)continue;
                    AbstractInsnNode b=n.getPrevious(),a=b==null?null:b.getPrevious();Object va=integer(a),vb=integer(b);
                    if(va==null||vb==null)continue;int x=(Integer)va,y=(Integer)vb,v=0;
                    switch(op){case IAND:v=x&y;break;case IOR:v=x|y;break;case IXOR:v=x^y;break;case IADD:v=x+y;break;case ISUB:v=x-y;break;case IMUL:v=x*y;break;case ISHL:v=x<<(y&31);break;case ISHR:v=x>>(y&31);break;case IUSHR:v=x>>>(y&31);break;}
                    m.instructions.set(n,push(v));m.instructions.remove(a);m.instructions.remove(b);folded++;changed=true;
                }
            }while(changed);
        }
        for(ClassNode c:classes.values()) {
            if(c.methods.size()!=1 || !c.methods.get(0).name.equals("<clinit>") || c.fields.size()!=1 || !c.fields.get(0).desc.equals("[Ljava/lang/String;"))continue;
            MethodNode m=c.methods.get(0);Map<Integer,String> t=new HashMap<>();boolean safe=true;
            for(AbstractInsnNode n:m.instructions.toArray()) {
                int op=n.getOpcode();
                if(op==AASTORE){AbstractInsnNode str=n.getPrevious(),idx=str.getPrevious();Object i=integer(idx);if(i==null||!(str instanceof LdcInsnNode)||!(((LdcInsnNode)str).cst instanceof String)){safe=false;break;}t.put((Integer)i,(String)((LdcInsnNode)str).cst);}
                if(op>=INVOKEVIRTUAL&&op<=INVOKEDYNAMIC)safe=false;
            }
            if(safe && !t.isEmpty())tables.put(c.name+"/"+c.fields.get(0).name,t);
        }
        for(ClassNode c:classes.values())for(MethodNode m:c.methods)for(AbstractInsnNode n:m.instructions.toArray()) {
            if(n.getOpcode()!=AALOAD)continue;
            AbstractInsnNode idx=n.getPrevious(),get=idx==null?null:idx.getPrevious();Object i=integer(idx);
            if(i==null||!(get instanceof FieldInsnNode)||get.getOpcode()!=GETSTATIC)continue;
            FieldInsnNode f=(FieldInsnNode)get;Map<Integer,String> t=tables.get(f.owner+"/"+f.name);
            if(t!=null && t.containsKey((Integer)i)){m.instructions.set(n,new LdcInsnNode(t.get((Integer)i)));m.instructions.remove(idx);m.instructions.remove(get);inlined++;}
        }
    }
    static Map<String,Object> object(Object... kv){Map<String,Object> r=new LinkedHashMap<>();for(int i=0;i<kv.length;i+=2)r.put((String)kv[i],kv[i+1]);return r;}
    static Object constant(Object o) {if(o instanceof Double && !Double.isFinite((Double)o) || o instanceof Float && !Float.isFinite((Float)o))return object("number",o.toString());return o instanceof Type?object("type",((Type)o).getDescriptor()):o instanceof Handle?o.toString():o;}
    static void inspect(String output)throws Exception {
        List<Object> out=new ArrayList<>();
        for(ClassNode c:classes.values()) {
            List<Object> fs=new ArrayList<>(),ms=new ArrayList<>();
            for(FieldNode f:c.fields)fs.add(object("name",f.name,"desc",f.desc,"access",f.access,"value",constant(f.value)));
            for(MethodNode m:c.methods) {
                List<Object> ins=new ArrayList<>();Map<LabelNode,Integer> labels=new IdentityHashMap<>();int pos=0;
                for(AbstractInsnNode n:m.instructions.toArray()){if(n instanceof LabelNode)labels.put((LabelNode)n,pos);if(n.getOpcode()>=0)pos++;}
                for(AbstractInsnNode n:m.instructions.toArray()) {
                    int op=n.getOpcode();if(op<0)continue;Object v=integer(n);
                    if(v!=null){ins.add(Arrays.asList("const",v));continue;}
                    if(n instanceof LdcInsnNode)ins.add(Arrays.asList("const",constant(((LdcInsnNode)n).cst)));
                    else if(n instanceof FieldInsnNode){FieldInsnNode f=(FieldInsnNode)n;ins.add(Arrays.asList(op,"field",f.owner,f.name,f.desc));}
                    else if(n instanceof MethodInsnNode){MethodInsnNode f=(MethodInsnNode)n;ins.add(Arrays.asList(op,"method",f.owner,f.name,f.desc));}
                    else if(n instanceof TypeInsnNode)ins.add(Arrays.asList(op,"type",((TypeInsnNode)n).desc));
                    else if(n instanceof VarInsnNode)ins.add(Arrays.asList(op,"var",((VarInsnNode)n).var));
                    else if(n instanceof JumpInsnNode)ins.add(Arrays.asList(op,"jump",labels.get(((JumpInsnNode)n).label)));
                    else if(n instanceof IincInsnNode){IincInsnNode i=(IincInsnNode)n;ins.add(Arrays.asList(op,"inc",i.var,i.incr));}
                    else if(n instanceof IntInsnNode)ins.add(Arrays.asList(op,"int",((IntInsnNode)n).operand));
                    else if(n instanceof TableSwitchInsnNode){TableSwitchInsnNode t=(TableSwitchInsnNode)n;ins.add(Arrays.asList(op,"switch",t.min,t.max));}
                    else if(n instanceof LookupSwitchInsnNode)ins.add(Arrays.asList(op,"switch",((LookupSwitchInsnNode)n).keys));
                    else if(n instanceof InvokeDynamicInsnNode){InvokeDynamicInsnNode d=(InvokeDynamicInsnNode)n;ins.add(Arrays.asList(op,"dynamic",d.name,d.desc,d.bsm.toString(),Arrays.toString(d.bsmArgs)));}
                    else if(n instanceof MultiANewArrayInsnNode){MultiANewArrayInsnNode d=(MultiANewArrayInsnNode)n;ins.add(Arrays.asList(op,"array",d.desc,d.dims));}
                    else ins.add(Arrays.asList(op));
                }
                ms.add(object("name",m.name,"desc",m.desc,"access",m.access,"exceptions",m.exceptions,"signature",m.signature,"ins",ins));
            }
            out.add(object("name",c.name,"super",c.superName,"interfaces",c.interfaces,"access",c.access,"source",c.sourceFile,"outer",c.outerClass,"fields",fs,"methods",ms));
        }
        try(Writer w=new OutputStreamWriter(new FileOutputStream(output),"UTF-8")){new Gson().toJson(out,w);}
    }
    static void jar(String input,String output,Remapper remap)throws Exception {
        Set<String> written=new HashSet<>();
        try(ZipFile z=new ZipFile(input);JarOutputStream out=new JarOutputStream(new FileOutputStream(output))) {
            Enumeration<? extends ZipEntry> e=z.entries();
            while(e.hasMoreElements()) {
                ZipEntry en=e.nextElement();if(en.isDirectory())continue;String name=en.getName();byte[] b;
                if(name.endsWith(".class")) {ClassNode c=classes.get(name.substring(0,name.length()-6));ClassWriter w=new ClassWriter(0);if(remap==null)c.accept(w);else c.accept(new ClassRemapper(w,remap));b=w.toByteArray();name=(remap==null?c.name:remap.mapType(c.name))+".class";}
                else {if(name.matches("META-INF/.*\\.(SF|RSA|DSA)"))continue;b=read(z.getInputStream(en));}
                if(!written.add(name))throw new IOException("Duplicate output "+name);out.putNextEntry(new JarEntry(name));out.write(b);out.closeEntry();
            }
        }

    }
    static class Names extends Remapper {
        Map<String,String> cs=new HashMap<>(),fs=new HashMap<>(),ms=new HashMap<>();
        Names(String path)throws Exception {for(String line:Files.readAllLines(Paths.get(path),java.nio.charset.StandardCharsets.UTF_8)){String[] s=line.split("\t");if(s.length<3)continue;if(s[0].equals("CLASS"))cs.put(s[1],s[2]);else if(s[0].equals("FIELD"))fs.put(s[1]+"/"+s[2]+" "+s[3],s[4]);else if(s[0].equals("METHOD"))ms.put(s[1]+"/"+s[2]+s[3],s[4]);}}
        public String map(String name){return cs.getOrDefault(name,name);}
        public Object mapValue(Object value){if(value instanceof String){String s=(String)value;if(cs.containsKey(s))return cs.get(s);String n=s.replace('.','/');if(cs.containsKey(n))return cs.get(n).replace('/','.');}return super.mapValue(value);}
        String find(String owner,String name,String desc,boolean method,Set<String> visited){if(!visited.add(owner))return null;String v=(method?ms:fs).get(owner+"/"+name+(method?"":" ")+desc);if(v!=null)return v;ClassNode c=classes.get(owner);if(c!=null){if(c.superName!=null){v=find(c.superName,name,desc,method,visited);if(v!=null)return v;}for(String it:c.interfaces){v=find(it,name,desc,method,visited);if(v!=null)return v;}}return null;}
        public String mapFieldName(String owner,String name,String desc){String v=find(owner,name,desc,false,new HashSet<String>());return v==null?name:v;}
        public String mapMethodName(String owner,String name,String desc){if(name.startsWith("<"))return name;String v=find(owner,name,desc,true,new HashSet<String>());return v==null?name:v;}
        public String mapAnnotationAttributeName(String descriptor,String name){String owner=Type.getType(descriptor).getInternalName();ClassNode c=classes.get(owner);if(c!=null)for(MethodNode m:c.methods)if(m.name.equals(name))return mapMethodName(owner,name,m.desc);return name;}
    }
    static void metadata(String[] args)throws Exception {
        Map<String,ClassNode> refs=new HashMap<>();
        for(int i=4;i<args.length;i++)try(ZipFile z=new ZipFile(args[i])){Enumeration<? extends ZipEntry> e=z.entries();while(e.hasMoreElements()){ZipEntry en=e.nextElement();if(!en.getName().endsWith(".class"))continue;ClassNode c=new ClassNode();new ClassReader(read(z.getInputStream(en))).accept(c,0);refs.putIfAbsent(c.name,c);}}
        int restored=0;List<Object> removed=new ArrayList<>();Set<String> uses=new HashSet<>();
        for(ClassNode c:classes.values())for(MethodNode m:c.methods)for(AbstractInsnNode n:m.instructions.toArray())if(n instanceof FieldInsnNode){FieldInsnNode f=(FieldInsnNode)n;uses.add(f.owner+"/"+f.name+" "+f.desc);}
        for(ClassNode c:classes.values()) {
            // The obfuscator injected inconsistent cross-class InnerClasses
            // entries. Rebuild metadata only from identified reference classes.
            c.innerClasses=new ArrayList<>();c.outerClass=null;c.outerMethod=null;c.outerMethodDesc=null;
            ClassNode r=refs.get(c.name);
            if(r!=null){c.access|=r.access&ACC_SYNTHETIC;c.innerClasses=new ArrayList<>();for(InnerClassNode it:r.innerClasses)if(classes.containsKey(it.name))c.innerClasses.add(new InnerClassNode(it.name,it.outerName,it.innerName,it.access));if(r.outerClass!=null&&classes.containsKey(r.outerClass)){c.outerClass=r.outerClass;c.outerMethod=r.outerMethod;c.outerMethodDesc=r.outerMethodDesc;}
                for(FieldNode f:c.fields)for(FieldNode rf:r.fields)if(f.name.equals(rf.name)&&f.desc.equals(rf.desc))f.access|=rf.access&ACC_SYNTHETIC;
                for(MethodNode m:c.methods)for(MethodNode rm:r.methods)if(m.name.equals(rm.name)&&m.desc.equals(rm.desc))m.access|=rm.access&(ACC_SYNTHETIC|ACC_BRIDGE);
                restored++;}
            if((c.access&ACC_ENUM)!=0&&"java/lang/Enum".equals(c.superName))for(MethodNode m:c.methods)if(m.name.equals("<init>"))m.access&=~(ACC_PUBLIC|ACC_PROTECTED);
            if(c.fields!=null)for(Iterator<FieldNode> it=c.fields.iterator();it.hasNext();){FieldNode f=it.next();if(!f.name.startsWith("field_")||!f.desc.startsWith("L")||f.signature!=null||f.value!=null||f.visibleAnnotations!=null||f.invisibleAnnotations!=null)continue;String type=f.desc.substring(1,f.desc.length()-1);if(!classes.containsKey(type)||uses.contains(c.name+"/"+f.name+" "+f.desc))continue;
                // Full original declarations remain in named.jar and the complete
                // decompilation archive; this is a separately audited source view.
                removed.add(object("owner",c.name,"name",f.name,"descriptor",f.desc,"reason","unreferenced opaque object field; source-input cleanup only"));it.remove();
            }
        }
        int inferred=0;
        for(ClassNode c:classes.values())if(c.outerClass==null)for(MethodNode ctor:c.methods)if(ctor.name.equals("<init>")){
            Type[] at=Type.getArgumentTypes(ctor.desc);if(at.length==0||at[0].getSort()!=Type.OBJECT)continue;String pn=at[0].getInternalName();if(!pn.matches(".*\\$[0-9]+"))continue;
            boolean captured=false;for(FieldNode f:c.fields)if((f.access&ACC_SYNTHETIC)!=0&&f.desc.equals(at[0].getDescriptor()))captured=true;if(!captured)continue;ClassNode p=classes.get(pn);if(p==null)continue;MethodNode creator=null;int count=0;
            for(MethodNode m:p.methods)for(AbstractInsnNode n:m.instructions.toArray())if(n instanceof TypeInsnNode&&n.getOpcode()==NEW&&((TypeInsnNode)n).desc.equals(c.name)){creator=m;count++;}
            if(count==1){c.outerClass=pn;c.outerMethod=creator.name;c.outerMethodDesc=creator.desc;InnerClassNode it=new InnerClassNode(c.name,null,null,0);c.innerClasses.add(it);p.innerClasses.add(new InnerClassNode(c.name,null,null,0));inferred++;break;}
        }
        try(Writer w=new OutputStreamWriter(new FileOutputStream(args[3]),"UTF-8")){new Gson().toJson(removed,w);}
        jar(args[1],args[2],null);System.out.println("Metadata restored for "+restored+" classes; inferred anonymous="+inferred+"; source-only padding removal="+removed.size());
    }
    public static void main(String[] args)throws Exception {
        load(args[1]);
        if(args[0].equals("inspect")){normalize();inspect(args[2]);if(args.length>3)jar(args[1],args[3],null);System.out.println("Classes="+classes.size()+" tables="+tables.size()+" folded="+folded+" inlined="+inlined);}
        else if(args[0].equals("remap")){jar(args[1],args[3],new Names(args[2]));System.out.println("Remapped "+classes.size()+" classes");}
        else if(args[0].equals("metadata"))metadata(args);
    }
}
