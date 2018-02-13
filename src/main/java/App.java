import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.mitre.synthea.engine.Generator;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
  
  /**
   * Display usage info - what are the command line args, examples, etc.
   */
  public static void usage() {
    System.out.println("Usage: run_synthea [-s seed] [-p populationSize] [state [city]]");
    System.out.println("Examples:");
    System.out.println("run_synthea Massachusetts");
    System.out.println("run_synthea Alaska Juneau");
    System.out.println("run_synthea -s 12345");
    System.out.println("run_synthea -p 1000)");
    System.out.println("run_synthea -s 987 Washington Seattle");
    System.out.println("run_synthea -s 21 -p 100 Utah \"Salt Lake City\"");
  }
  
  /**
   * Run Synthea generation.
   * @param args None. See documentation on configuration.
   * @throws Exception On errors.
   */
  public static void main(String[] args) throws Exception {
    Generator.GeneratorOptions options = new Generator.GeneratorOptions();
    
    boolean validArgs = true;
    if (args != null && args.length > 0) {
      try {
        Queue<String> argsQ = new LinkedList<String>(Arrays.asList(args));
        
        while (!argsQ.isEmpty()) {
          String currArg = argsQ.poll();
          
          if (currArg.equalsIgnoreCase("-s")) {
            String value = argsQ.poll();
            options.seed = Long.parseLong(value);
          } else if (currArg.equalsIgnoreCase("-p")) {
            String value = argsQ.poll();
            options.population = Integer.parseInt(value);
          } else if (options.state == null) {
            options.state = currArg;
          } else {
            // assume it must be the city
            options.city = currArg;
          }
        } 
      } catch (Exception e) {
        e.printStackTrace();
        usage();
        validArgs = false;
      }
    }
    
    if(validArgs) {
      Generator generator = new Generator(options);
      generator.run();
    }
  }
}
