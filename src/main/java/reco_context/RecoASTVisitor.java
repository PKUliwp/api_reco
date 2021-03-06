package reco_context;

import org.eclipse.jdt.core.dom.*;
import reco_context.info.MethodInfo;
import reco_context.pool.TestMethodPool;
import reco_context.pool.MethodPool;
import reco_query.entity.entities.MethodEntity;

import java.util.*;

/**
 * Created by liwp on 2017/5/21.
 */
public class RecoASTVisitor extends ASTVisitor{
    public enum Type{
        train, test
    }
    Type type;

    RecoASTVisitor(Type t) {
        type = t;
    }

    public boolean visit(MethodDeclaration methodDeclaration) {
        System.out.println("::::");
        if(methodDeclaration.getBody() != null) {
            List statements = methodDeclaration.getBody().statements();
            List<MethodEntity> block = new ArrayList<>();
            statements.forEach(statement -> {
                //System.out.println(statement.getClass());
                MethodInfo info = new MethodInfo();
                RecoASTVisitorUtils.parseMethodBody(info, methodDeclaration.getBody());
                info.methodCalls.forEach(iMethodBinding -> {
                    //System.out.println(iMethodBinding.getParameterTypes().length>0 ? iMethodBinding.getParameterTypes()[0]:0);
                    //System.out.println(iMethodBinding.getReturnType());
                    System.out.println(iMethodBinding.getDeclaringClass().getBinaryName() + " " + iMethodBinding.getName());


                    MethodEntity methodEntity = new MethodEntity();
                    StringBuilder builder = new StringBuilder();
                    Arrays.stream(iMethodBinding.getParameterTypes()).forEach(p -> builder.append(p.getName().toString()));
                    methodEntity.setParams(builder.toString());
                    methodEntity.setBelongTo(iMethodBinding.getDeclaringClass().getBinaryName().toString());
                    methodEntity.setName(iMethodBinding.getName().toString());
                    methodEntity.setRt(iMethodBinding.getReturnType().getName().toString());
                    methodEntity.setAbsoluteName();

                    block.add(methodEntity);
                });
            });

            if(type == Type.train) {
                MethodPool.methodBlocks.add(block);
            } else {
                TestMethodPool.methodBlocks.add(block);
            }
        }
        return true;
    }




}
