package reco_context;

import org.eclipse.jdt.core.dom.*;
import reco_context.info.MethodInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liwp on 2017/5/21.
 */
public class RecoASTVisitor extends ASTVisitor{
    public boolean visit(MethodDeclaration methodDeclaration) {
        System.out.println("::::");
        if(methodDeclaration.getBody() != null) {
            List statements = methodDeclaration.getBody().statements();
            statements.forEach(statement -> {
                System.out.println(statement.getClass());
                MethodInfo info = new MethodInfo();
                RecoASTVisitorUtils.parseMethodBody(info, methodDeclaration.getBody());
                info.methodCalls.forEach(iMethodBinding -> {
                    System.out.println(iMethodBinding.getDeclaringClass().getBinaryName() + " " + iMethodBinding.getName());
                });
            });
        }
        return true;
    }




}
