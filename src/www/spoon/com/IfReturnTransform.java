package www.spoon.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import spoon.Launcher;
import spoon.processing.AbstractProcessor;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtExecutableReference;
import spoon.testing.Assert;
//import spoon.testing.Assert;

public class IfReturnTransform extends AbstractProcessor<CtClass<?>>{
//	public static void main(String[] args) {
//		Launcher launcher = new Launcher();
//		launcher.addInputResource("resources/src/Main.java"); 
//		launcher.getEnvironment().setAutoImports(true);
//		launcher.getEnvironment().setNoClasspath(true);
//		launcher.buildModel();
//		CtModel model = launcher.getModel();
//		IfReturnTransform transform = new IfReturnTransform();
//		File file = new File("resources/test/Main.java");
//		try {
//			PrintStream ps = new PrintStream(new FileOutputStream(file));
//			for(CtType<?> s : model.getAllTypes()) {
////				  IfReturnTransform transform = new IfReturnTransform();
//				  transform.process((CtClass)s);
//				  ps.println(s);
//				  
//				}
//			ps.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(Assert.assertThat(new File("resources/expected/Main.java")).isEqualTo(new File("resources/test/Main.java")));
//		
//	}

	@Override
	public void process(CtClass<?> myclass) {
		// TODO Auto-generated method stub
		Iterator<CtExecutableReference<?>> iter = myclass.getDeclaredExecutables().iterator();
		while(iter.hasNext()){
			List<CtStatement> statements = iter.next().getExecutableDeclaration().getBody().getStatements();
			for(int i = 0; i< statements.size(); i++) {
				CtStatement st = statements.get(i);
				if(st instanceof CtIf) {
					CtStatement elseStatement = ((CtIf) st).getElseStatement();
					if(elseStatement == null) {
						CtStatement thenStatement = ((CtIf) st).getThenStatement();
						List<CtElement> elementList = thenStatement.getElements(null);
						CtElement returnStatement = null;
						for(CtElement ctEl: elementList.subList(1, elementList.size())) {
							if(ctEl instanceof CtStatement) {
								if(returnStatement == null) {
									returnStatement = ctEl.clone();
								}
								else {
									returnStatement = null;
									break;
								}
							}
						}
						if(returnStatement instanceof CtReturn) {
							st.replace(returnStatement);
							i--;
						}
					}
				}
			}
			}
	}
}
