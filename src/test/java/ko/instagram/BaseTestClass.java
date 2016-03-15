package ko.instagram;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Базовый класс. От него наследуются тесты. Нужен для того, что бы в каждом
 * тесте не дублировать код
 * @author timestamp
 */
public class BaseTestClass {

    public WebDriver driver;
    private final int DEFAULT_IMPLICITLY_WAIT = 15000;

    /**
     * Аннотация @BeforeClass отмечает метод как метод инициализации класса
     * теста. Метод инициализации тестового класса запускается только один раз и
     * выполняется только перед выполнением любых других методов в тестовом
     * классе.
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * Аннотация @AfterClass помечает метод как метод финализации класса теста.
     * Метод финализатора тестового класса выполняется только один раз и только
     * после выполнения других методов в тестовом классе
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Инициализатор теста. Аннотация @Before отмечает метод как метод
     * инициализации теста. Метод инициализации теста выполняется перед каждым
     * тестом в тестовом классе.
     */
    @Before
    public void setUp() {
        switch (System.getProperty("webdriver.driver", "firefox")) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICITLY_WAIT, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    /**
     * Финализатор теста. Аннотация @After помечает метод как метод финализации
     * теста. Метод финализатора теста выполняется после каждого теста в
     * тестовом классе.
     */
    @After
    public void tearDown() {
        driver.quit();
    }
}