package properties.tests;

import org.junit.jupiter.api.Test;
import static java.lang.String.format;

public class SystemPropertiesTests {
  @Test
  void systemPropertiesTest(){
    System.setProperty("browser", "chrome");
    String browser = System.getProperty("browser");
    System.out.println(browser);
  }

  @Test
  void systemProperties2Test(){
    String browser = System.getProperty("browser", "mozilla");
    System.out.println(browser);
    // запуск gradle task_name -Dbrowser=mozila
  }

  @Test
  void systemProperties3Test(){
    System.setProperty("browser", "chrome");
    String browser = System.getProperty("browser", "mozilla");
    System.out.println(browser);
  }

  @Test
  void systemProperties4Test(){
    String name = System.getProperty("name", "default student");
    String message = format("Hello, %s!", name);
    System.out.println(message);

    //gradle task_name -Dname=Alex Egorov -> Build failed - пробел - кавычки
  }
}
