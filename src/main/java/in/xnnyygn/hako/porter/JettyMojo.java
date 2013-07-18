package in.xnnyygn.hako.porter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

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
    getLog().info("Start Jetty on port " + port);
  }

}
