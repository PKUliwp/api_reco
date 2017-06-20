package reco_context;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;

import java.io.*;
import java.util.*;
import org.apache.commons.io.FileUtils;
import reco_context.pool.TestMethodPool;


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


        TestSourceParser.parse();
        System.out.println("!!!!!!!");
        TestMethodPool.methodBlocks.forEach(block -> {
            System.out.println("\n");
            block.forEach(methodEntity -> {
                System.out.println(methodEntity.displayName());
            });
        });

        TestMethodPool.build();
        System.out.println(TestMethodPool.methodDistributions.size());



    }







}
