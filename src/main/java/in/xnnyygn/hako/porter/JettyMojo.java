package in.xnnyygn.hako.porter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.List;

/**
 * Start Jetty.
 * 
 * @author xnnyygn
 */
@Mojo(name = "jetty")
public class JettyMojo extends AbstractMojo {

  @Parameter(defaultValue = "8080")
  private int port;

  @Parameter(defaultValue = "/${project.artifactId}")
  private String contextPath;

	@Parameter(defaultValue = "src/main/webapp")
	private String webRoot;

  @Parameter(defaultValue = "${project.runtimeClasspathElements}")
  private List<String> projectRuntimeClassPathElements;

  public void execute() throws MojoExecutionException, MojoFailureException {
    Server server = new Server(port);
    server.setHandler(createWebApp());
    try {
      server.start();
      server.join();
    } catch (Exception e) {
      throw new MojoExecutionException("failed to start jetty", e);
    }
  }

  private WebAppContext createWebApp() {
    getLog().info("set context path to [" + contextPath + "]");
    WebAppContext context = new WebAppContext();
    context.setResourceBase(webRoot);
    context.setContextPath(contextPath);
		debugRuntimeClassPathElements();
		String projectClassPath = joinClassPath(projectRuntimeClassPathElements);
    context.setExtraClasspath(projectClassPath);
    return context;
  }

	private void debugRuntimeClassPathElements() {
		getLog().debug("Jetty Extra Class Path");
		for(String element : projectRuntimeClassPathElements) {
			getLog().debug(element);
		}
	}

  private String joinClassPath(List<String> paths) {
		StringBuilder pathBuilder = new StringBuilder();
		for(String path : paths) {
			pathBuilder.append(path).append(',');
		}
		return pathBuilder.toString();
	}

}
