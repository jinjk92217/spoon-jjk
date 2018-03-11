package test.www.spoon.com;

import java.io.File;
import org.junit.Test;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.testing.Assert;
import www.spoon.com.KnobsTransform;

public class KnobsTransformTest {
	@Test
	public void testCompileSourceCodeAfterProcessSourceCodeWithNotNullCheckAdderProcessor() throws Exception {
		SpoonAPI launcher = new Launcher();
		launcher.getEnvironment().setNoClasspath(true);
		launcher.addInputResource("./resources/src/KnobsTest.java");
		launcher.setSourceOutputDirectory("./resources/test/");
		launcher.addProcessor(new KnobsTransform());
		launcher.run();
		Assert.assertThat(new File("resources/expected/A.java")).isEqualTo(new File("resources/test/A.java"));
		Assert.assertThat(new File("resources/expected/KnobsTest.java")).isEqualTo(new File("resources/test/KnobsTest.java"));
	}
}
