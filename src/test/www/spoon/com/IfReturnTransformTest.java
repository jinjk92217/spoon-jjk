package test.www.spoon.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

//import org.eclipse.jdt.internal.compiler.batch.Main;
import org.junit.Test;
import spoon.Launcher;
//import spoon.reflect.declaration.CtClass;
//
//import java.io.PrintWriter;
//
//import static org.junit.Assert.assertTrue;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtType;
import spoon.testing.Assert;
import www.spoon.com.IfReturnTransform;

public class IfReturnTransformTest {
	@Test
	public void testCompileSourceCodeAfterProcessSourceCodeWithNotNullCheckAdderProcessor() throws Exception {
		Launcher launcher = new Launcher();
		launcher.addInputResource("resources/src/Main.java"); 
		launcher.getEnvironment().setAutoImports(true);
		launcher.getEnvironment().setNoClasspath(true);
		launcher.buildModel();
		CtModel model = launcher.getModel();
		IfReturnTransform transform = new IfReturnTransform();
		File file = new File("resources/test/Main.java");
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			for(CtType<?> s : model.getAllTypes()) {
//				  IfReturnTransform transform = new IfReturnTransform();
				  transform.process((CtClass)s);
				  ps.println(s);
				  
				}
			ps.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertThat(new File("resources/expected/Main.java")).isEqualTo(new File("resources/test/Main.java"));
		
	}
}
