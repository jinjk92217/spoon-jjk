package www.spoon.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.processing.AbstractProcessor;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtRHSReceiver;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.CtTypedElement;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.testing.Assert;


class AttributeNameGenerator{
	private int i = 1;
	private String baseName = "_spoonKnobsAttributeName";
    public String next()
    {
            return baseName + String.valueOf(i++);
    }
}

public class KnobsTransform extends AbstractProcessor<CtClass<?>>{
//	public static void main(String[] args) {
//		SpoonAPI launcher = new Launcher();
//		launcher.getEnvironment().setNoClasspath(true);
//		launcher.addInputResource("./resources/src/KnobsTest.java");
//		launcher.setSourceOutputDirectory("./resources/expected/");
//		launcher.addProcessor(new KnobsTransform());
//		launcher.run();
//	}

	@Override
	public void process(CtClass<?> myClass) {
		// TODO Auto-generated method stub
		AttributeNameGenerator gen = new AttributeNameGenerator();
		Iterator<CtExecutableReference<?>> iter = myClass.getDeclaredExecutables().iterator();
		while (iter.hasNext()) {
			List<CtElement> elements = iter.next().getExecutableDeclaration().getBody().getElements(null);
			for (CtElement ctEl : elements) {
				if (ctEl instanceof CtLocalVariable || ctEl instanceof CtAssignment) {
					CtExpression assignment = ((CtRHSReceiver) ctEl).getAssignment();
					if (assignment instanceof CtVariableRead || assignment == null) {
						// For example int a = b; or int a; do not transform
						continue;
					}
					CtTypeReference type = ((CtTypedElement) ctEl).getType();
//						CtTypeReference type = assignment.getType(); Do not use because of nulltype

					String knobName = gen.next();
					((CtRHSReceiver) ctEl).setAssignment(getFactory().Code().createCodeSnippetExpression(knobName));
					CtField newField = getFactory().Code().createCtField(knobName, type, assignment.toString(), ModifierKind.PRIVATE);
					myClass.addFieldAtTop(newField);

				}
			}
		}
	}
}
