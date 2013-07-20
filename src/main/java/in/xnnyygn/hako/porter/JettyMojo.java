package in.xnnyygn.hako.porter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Start Jetty.
 * 
 * @author xnnyygn
 */
@Mojo(name = "jetty")
public class JettyMojo extends AbstractMojo {

  @Parameter(defaultValue = "8080")
  private int port;

  @Parameter(defaultValue = "${project.artifactId}")
  private String contextPath;

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
    context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
    context.setResourceBase(".");
    context.setContextPath(contextPath);
    context.setExtraClasspath("target/classes");
    return context;
  }

}
