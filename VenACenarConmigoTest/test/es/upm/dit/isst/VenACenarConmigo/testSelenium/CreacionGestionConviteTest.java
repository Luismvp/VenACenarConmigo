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

public class CreacionGestionConviteTest {
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
  public void testCreacionGestionConvite() throws Exception {
    driver.get("http://localhost:8080/VenACenarConmigoTest/");
    driver.findElement(By.linkText("Registro")).click();
    driver.findElement(By.id("nombre")).click();
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("Javier");
    driver.findElement(By.id("apellidos")).click();
    driver.findElement(By.id("apellidos")).clear();
    driver.findElement(By.id("apellidos")).sendKeys("González Santos");
    driver.findElement(By.id("nacimiento")).clear();
    driver.findElement(By.id("nacimiento")).sendKeys("04/01/1997");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("javier.g.s@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("javier");
    driver.findElement(By.id("repPassword")).clear();
    driver.findElement(By.id("repPassword")).sendKeys("javier");
    driver.findElement(By.id("telefono")).clear();
    driver.findElement(By.id("telefono")).sendKeys("634562212");
    driver.findElement(By.id("ciudad")).clear();
    driver.findElement(By.id("ciudad")).sendKeys("Madrid");
    driver.findElement(By.id("codigoPostal")).clear();
    driver.findElement(By.id("codigoPostal")).sendKeys("28902");
    driver.findElement(By.id("profesion")).clear();
    driver.findElement(By.id("profesion")).sendKeys("Estudiante de Humanidades");
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("Soy una persona alegre y con mucho arte para la cocina");
    driver.findElement(By.id("btnSubmit")).click();
    driver.get("http://localhost:8080/VenACenarConmigoTest/");
    driver.findElement(By.linkText("Registro")).click();
    driver.findElement(By.id("nombre")).click();
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("Alberto");
    driver.findElement(By.id("apellidos")).click();
    driver.findElement(By.id("apellidos")).clear();
    driver.findElement(By.id("apellidos")).sendKeys("Gallego Sanz");
    driver.findElement(By.id("nacimiento")).clear();
    driver.findElement(By.id("nacimiento")).sendKeys("04/05/1991");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("alberto.g.s@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("alberto");
    driver.findElement(By.id("repPassword")).clear();
    driver.findElement(By.id("repPassword")).sendKeys("alberto");
    driver.findElement(By.id("telefono")).clear();
    driver.findElement(By.id("telefono")).sendKeys("647889932");
    driver.findElement(By.id("ciudad")).clear();
    driver.findElement(By.id("ciudad")).sendKeys("Madrid");
    driver.findElement(By.id("codigoPostal")).clear();
    driver.findElement(By.id("codigoPostal")).sendKeys("28043");
    driver.findElement(By.id("profesion")).clear();
    driver.findElement(By.id("profesion")).sendKeys("Empleado de RRHH");
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("Soy tranquilo y amante de la comida tradicional");
    driver.findElement(By.id("btnSubmit")).click();
    driver.get("http://localhost:8080/VenACenarConmigoTest/");
    driver.findElement(By.linkText("Registro")).click();
    driver.findElement(By.id("nombre")).click();
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("Elena");
    driver.findElement(By.id("apellidos")).clear();
    driver.findElement(By.id("apellidos")).sendKeys("García Sanz");
    driver.findElement(By.id("nacimiento")).clear();
    driver.findElement(By.id("nacimiento")).sendKeys("04/07/1995");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("elena.g.s@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("elena");
    driver.findElement(By.id("repPassword")).clear();
    driver.findElement(By.id("repPassword")).sendKeys("elena");
    driver.findElement(By.id("telefono")).clear();
    driver.findElement(By.id("telefono")).sendKeys("654223212");
    driver.findElement(By.id("ciudad")).clear();
    driver.findElement(By.id("ciudad")).sendKeys("Madrid");
    driver.findElement(By.id("codigoPostal")).clear();
    driver.findElement(By.id("codigoPostal")).sendKeys("28992");
    driver.findElement(By.id("profesion")).clear();
    driver.findElement(By.id("profesion")).sendKeys("Emprendedora");
    driver.findElement(By.id("descripcion")).click();
    driver.findElement(By.id("descripcion")).click();
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("Soy una persona muy activa, y siempre me gusta probar platos nuevos");
    driver.findElement(By.id("btnSubmit")).click();
    driver.get("http://localhost:8080/VenACenarConmigoTest/Login.jsp");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("javier.g.s@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("javier");
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.linkText("Crear Convite")).click();
    driver.findElement(By.id("nombre")).click();
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("Comida inaugural");
    driver.findElement(By.id("fecha")).clear();
    driver.findElement(By.id("fecha")).sendKeys("11/06/2018");
    driver.findElement(By.id("horaComienzo")).clear();
    driver.findElement(By.id("horaComienzo")).sendKeys("13:30");
    driver.findElement(By.id("horaFin")).clear();
    driver.findElement(By.id("horaFin")).sendKeys("16:00");
    driver.findElement(By.id("ciudad")).clear();
    driver.findElement(By.id("ciudad")).sendKeys("Madrid");
    driver.findElement(By.id("area")).clear();
    driver.findElement(By.id("area")).sendKeys("Moncloa");
    driver.findElement(By.name("menu")).clear();
    driver.findElement(By.name("menu")).sendKeys("Aperitivos, potaje de alubias, chocolates belgas");
    driver.findElement(By.id("numInvitados")).clear();
    driver.findElement(By.id("numInvitados")).sendKeys("7");
    driver.findElement(By.id("precioInvitado")).click();
    driver.findElement(By.id("precioInvitado")).clear();
    driver.findElement(By.id("precioInvitado")).sendKeys("7");
    driver.findElement(By.id("descripcion")).click();
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("Nos conoceremos mejor");
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.id("inv1")).click();
    driver.findElement(By.id("inv1")).clear();
    driver.findElement(By.id("inv1")).sendKeys("alberto.g.s@gmail.com");
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.linkText("Gestionar\n					mis convites")).click();
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.id("comentario")).click();
    driver.findElement(By.id("comentario")).clear();
    driver.findElement(By.id("comentario")).sendKeys("Será una gran comida");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    assertEquals("alberto.g.s@gmail.com", driver.findElement(By.xpath("//div[2]/h4")).getText());
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.id("inv1")).click();
    driver.findElement(By.id("inv1")).clear();
    driver.findElement(By.id("inv1")).sendKeys("elena.g.s@gmail.com");
    driver.findElement(By.id("btnSubmit2")).click();
    assertEquals("elena.g.s@gmail.com", driver.findElement(By.xpath("//div[2]/h4[3]")).getText());
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("alberto.g.s@gmail.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("alberto");
    driver.findElement(By.id("btnSubmit")).click();
    assertEquals("Notificaciones1", driver.findElement(By.linkText("Notificaciones1")).getText());
    driver.findElement(By.linkText("Notificaciones1")).click();
    driver.findElement(By.id("btnSubmit2")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("elena.g.s@gmail.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("elena");
    driver.findElement(By.id("btnSubmit")).click();
    assertEquals("Notificaciones1", driver.findElement(By.linkText("Notificaciones1")).getText());
    driver.findElement(By.linkText("Notificaciones1")).click();
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("javier.g.s@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("javier");
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.linkText("Gestionar\n					mis convites")).click();
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.get("http://localhost:8080/VenACenarConmigoTest/borrarUsuarios.jsp");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("alberto.g.s@gmail.com");
    driver.findElement(By.id("btnSubmit")).click();
    driver.get("http://localhost:8080/VenACenarConmigoTest/borrarUsuarios.jsp");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("elena.g.s@gmail.com");
    driver.findElement(By.id("btnSubmit")).click();
    driver.get("http://localhost:8080/VenACenarConmigoTest/borrarUsuarios.jsp");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("javier.g.s@gmail.com");
    driver.findElement(By.id("btnSubmit")).click();
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
