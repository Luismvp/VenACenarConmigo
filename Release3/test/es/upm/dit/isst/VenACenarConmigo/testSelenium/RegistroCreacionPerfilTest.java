package es.upm.dit.isst.VenACenarConmigo.testSelenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistroCreacionPerfilTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	//Indicamos donde se encuentra el archivo con el driver de chrome
	System.setProperty("webdriver.chrome.driver","/home/isst/chromedriver");
	driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testRegistroCreacionPerfil() throws Exception {
    driver.get("http://localhost:8080/VenACenarConmigoTest/");
    driver.findElement(By.linkText("Registro")).click();
    driver.findElement(By.id("nombre")).click();
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("Javier");
    driver.findElement(By.id("apellidos")).clear();
    driver.findElement(By.id("apellidos")).sendKeys("González Santos");
    driver.findElement(By.id("nacimiento")).clear();
    driver.findElement(By.id("nacimiento")).sendKeys("04/01/1997");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("javier.g.s@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("javier");
    driver.findElement(By.id("repPassword")).clear();
    driver.findElement(By.id("repPassword")).sendKeys("javier");
    driver.findElement(By.id("telefono")).clear();
    driver.findElement(By.id("telefono")).sendKeys("626443091");
    driver.findElement(By.id("ciudad")).clear();
    driver.findElement(By.id("ciudad")).sendKeys("Madrid");
    driver.findElement(By.id("codigoPostal")).clear();
    driver.findElement(By.id("codigoPostal")).sendKeys("28902");
    driver.findElement(By.id("profesion")).clear();
    driver.findElement(By.id("profesion")).sendKeys("Estudiante de Humanidades");
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("Soy una persona alegre, divertida y con mucho arte para la cocina");
    driver.findElement(By.id("btnSubmit")).click();
    assertEquals("Hemos procedido a realizar tu registro como usuario", driver.findElement(By.xpath("//h1")).getText());
    driver.get("http://localhost:8080/VenACenarConmigoTest/Login.jsp");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("javier.g.s@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("javier");
    driver.findElement(By.xpath("//form[@action='LoginServlet']")).click();
    driver.findElement(By.id("btnSubmit")).click();
    assertEquals("Javier González Santos", driver.findElement(By.xpath("//div[@id='nombre']/h1/b")).getText());
    assertEquals("Nacid@ el 04/01/1997", driver.findElement(By.xpath("//div[@id='userInfo']/h3/b")).getText());
    assertEquals("Ciudad: Madrid", driver.findElement(By.xpath("//div[@id='userInfo']/h3[3]")).getText());
    assertEquals("Ocupación: Estudiante de Humanidades", driver.findElement(By.xpath("//div[@id='userInfo']/h3[4]")).getText());
    assertEquals("Descripción personal:", driver.findElement(By.xpath("//div[@id='userInfo']/h3[5]/b")).getText());
    driver.findElement(By.linkText("Logout")).click();
    driver.get("http://localhost:8080/VenACenarConmigoTest/borrarUsuarios.jsp");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("javier.g.s@gmail.com");
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("javier.g.s@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("javier");
    driver.findElement(By.xpath("//form[@action='LoginServlet']")).click();
    driver.findElement(By.id("btnSubmit")).click();
    assertEquals("Log in", driver.findElement(By.xpath("//h1")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
