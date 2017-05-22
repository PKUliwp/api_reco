package reco_context;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import org.apache.commons.io.FileUtils;


/**
 * Created by liwp on 2017/5/7.
 */
public class test {
    public static void main(String args[]) {
//        try {
//            System.setOut(new PrintStream(new File("/Users/liwp/Desktop/out.txt")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        test2();




    }

    public static void test1() {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        Collection<File> javaFiles = FileUtils.listFiles(new File("/Users/liwp/Desktop/lucene/src"), new String[]{"java"}, true);
        Set<String> srcPathSet = new HashSet<>();
        Set<String> srcFolderSet = new HashSet<>();
        for (File javaFile : javaFiles) {
            String srcPath = javaFile.getAbsolutePath();
            String srcFolderPath = javaFile.getParentFile().getAbsolutePath();
            srcPathSet.add(srcPath);
            srcFolderSet.add(srcFolderPath);
        }
        String[] srcPaths = new String[srcPathSet.size()];
        srcPathSet.toArray(srcPaths);
        String[] srcFolderPaths = new String[srcFolderSet.size()];
        srcFolderSet.toArray(srcFolderPaths);

        parser.setEnvironment(null, srcFolderPaths, null, true);
        parser.setResolveBindings(true);
        Map<String, String> options = new Hashtable<>();
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);
        parser.setCompilerOptions(options);
        parser.setBindingsRecovery(true);


        parser.createASTs(srcPaths, null, new String[]{}, new FileASTRequestor() {
            @Override
            public void acceptAST(String sourceFilePath, CompilationUnit javaUnit) {

                javaUnit.accept(new RecoASTVisitor());

            }
        }, null);
    }

    public static void test2() {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        Collection<File> javaFiles = FileUtils.listFiles(new File("/Users/liwp/Desktop/lucene/src"), new String[]{"java"}, true);

        String[] srcPaths = new String[1];
        srcPaths[0] = "/Users/liwp/Desktop/lucene/src/lucene-6.4.1/core/src/java/org/apache/lucene/index/BufferedUpdates.java";
        String[] srcFolderPaths = new String[1];
        srcFolderPaths[0] = "/Users/liwp/Desktop/lucene/src/lucene-6.4.1/core/src/java/org/apache/lucene/index";

        parser.setEnvironment(null, srcFolderPaths, null, true);
        parser.setResolveBindings(true);
        Map<String, String> options = new Hashtable<>();
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);
        parser.setCompilerOptions(options);
        parser.setBindingsRecovery(true);


        parser.createASTs(srcPaths, null, new String[]{}, new FileASTRequestor() {
            @Override
            public void acceptAST(String sourceFilePath, CompilationUnit javaUnit) {

                javaUnit.accept(new RecoASTVisitor());

            }
        }, null);
    }



}
