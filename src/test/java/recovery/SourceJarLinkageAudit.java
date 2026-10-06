package recovery;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/** Checks constructor references in the packaged artifact, without initializing game classes. */
public final class SourceJarLinkageAudit {
    public static void main(String[] args) throws Exception {
        List<String> failures = audit(new File(args[0]));
        if (!failures.isEmpty()) {
            throw new IllegalStateException("JAR constructor linkage failures: " + failures);
        }
        System.out.println("Source JAR constructor linkage audit passed.");
    }

    static List<String> audit(File jar) throws Exception {
        final Map<String, Set<String>> constructors = new HashMap<String, Set<String>>();
        final List<String[]> references = new ArrayList<String[]>();
        try (ZipFile zip = new ZipFile(jar)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.getName().endsWith(".class")) continue;
                try (InputStream input = zip.getInputStream(entry)) {
                    final ClassReader reader = new ClassReader(input);
                    final Set<String> declared = new HashSet<String>();
                    constructors.put(reader.getClassName(), declared);
                    reader.accept(new ClassVisitor(Opcodes.ASM5) {
                        @Override
                        public MethodVisitor visitMethod(int access, final String name,
                                final String descriptor, String signature, String[] exceptions) {
                            if ("<init>".equals(name)) declared.add(descriptor);
                            return new MethodVisitor(Opcodes.ASM5) {
                                @Override
                                public void visitMethodInsn(int opcode, String owner, String target,
                                        String targetDescriptor, boolean isInterface) {
                                    if ("<init>".equals(target)) {
                                        references.add(new String[]{reader.getClassName() + "." + name
                                                + descriptor, owner, targetDescriptor});
                                    }
                                }
                            };
                        }
                    }, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                }
            }
        }
        List<String> failures = new ArrayList<String>();
        for (String[] reference : references) {
            Set<String> declared = constructors.get(reference[1]);
            if (declared != null && !declared.contains(reference[2])) {
                failures.add(reference[0] + " -> " + reference[1] + ".<init>" + reference[2]);
            }
        }
        return failures;
    }
}
