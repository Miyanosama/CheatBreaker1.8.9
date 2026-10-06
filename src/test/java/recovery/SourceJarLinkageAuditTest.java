package recovery;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import junit.framework.TestCase;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SourceJarLinkageAuditTest extends TestCase {
    public void testRejectsMissingConstructorInPackagedJar() throws Exception {
        assertEquals(1, auditFixture("(I)V"));
    }

    public void testAcceptsMatchingConstructorInPackagedJar() throws Exception {
        assertEquals(0, auditFixture("()V"));
    }

    private int auditFixture(String constructor) throws Exception {
        Path directory = Paths.get(".target/test-data");
        Files.createDirectories(directory);
        Path jar = Files.createTempFile(directory, "linkage-", ".jar");
        try {
            ClassWriter target = new ClassWriter(0);
            target.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "fixture/Target", null,
                    "java/lang/Object", null);
            target.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_NATIVE, "<init>",
                    constructor, null, null).visitEnd();
            target.visitEnd();
            ClassWriter caller = new ClassWriter(0);
            caller.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "fixture/Caller", null,
                    "java/lang/Object", null);
            MethodVisitor method = caller.visitMethod(Opcodes.ACC_STATIC, "create", "()V", null, null);
            method.visitCode();
            method.visitTypeInsn(Opcodes.NEW, "fixture/Target");
            method.visitInsn(Opcodes.DUP);
            method.visitMethodInsn(Opcodes.INVOKESPECIAL, "fixture/Target", "<init>", "()V", false);
            method.visitInsn(Opcodes.POP);
            method.visitInsn(Opcodes.RETURN);
            method.visitMaxs(2, 0);
            method.visitEnd();
            caller.visitEnd();
            try (ZipOutputStream zip = new ZipOutputStream(Files.newOutputStream(jar))) {
                zip.putNextEntry(new ZipEntry("fixture/Target.class"));
                zip.write(target.toByteArray());
                zip.closeEntry();
                zip.putNextEntry(new ZipEntry("fixture/Caller.class"));
                zip.write(caller.toByteArray());
                zip.closeEntry();
            }
            return SourceJarLinkageAudit.audit(jar.toFile()).size();
        } finally {
            Files.deleteIfExists(jar);
        }
    }
}
