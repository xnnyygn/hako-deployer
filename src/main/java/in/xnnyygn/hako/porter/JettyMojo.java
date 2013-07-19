package in.xnnyygn.hako.porter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Start Jetty.
 * 
 * @goal jetty
 * @author xnnyygn
 */
public class JettyMojo extends AbstractMojo {

  /**
   * @parameter default-value="8080"
   */
  private int port;

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
    WebAppContext context = new WebAppContext();
    context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
    context.setResourceBase(".");
    context.setContextPath("/simple");
    context.setExtraClasspath("target/classes");
    return context;
  }

}
