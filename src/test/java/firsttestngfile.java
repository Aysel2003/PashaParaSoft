import net.bytebuddy.build.Plugin;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class firsttestngfile {
    public WebDriver driver ;
    @BeforeClass
    public void setUp() {
        System.out.println("Launching firefox browser");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Aysel\\Downloads\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://parabank.parasoft.com/parabank/admin.htm");
    }
    @Test (priority = 1)
    public void logintestcase() {
        driver.findElement(By.xpath("//input[@name = \"username\"]")).sendKeys("Aysel"); //Sending name
        driver.findElement(By.xpath("//input[@name = \"password\"]")).sendKeys("ayyPara03"); // Sending PWD
        driver.findElement(By.xpath("//input[@class= \"button\"]")).click();
    }

    @Test (priority = 2)
    public void opennewaccount() {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/ul/li[1]/a")).click();
        Select accounttype = new Select(driver.findElement(By.xpath("//*[@id=\"type\"]")));
        accounttype.selectByValue("0");
        Select amount = new Select(driver.findElement(By.xpath("//*[@id=\"fromAccountId\"]")));
        amount.selectByValue("16674");
        driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div/input")).click();
    }

    @Test (priority = 3)
    public void requestloan() {
        driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[7]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"amount\"]")).sendKeys("1000");
        driver.findElement(By.xpath("//*[@id=\"downPayment\"]")).sendKeys("100");
        Select fromaccount = new Select(driver.findElement(By.xpath("//*[@id=\"fromAccountId\"]")));
        fromaccount.selectByValue("14676");
        driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/table/tbody/tr[4]/td[2]/input")).click();
    }

    @Test (priority = 4)
    public void findtransactions() {
        driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[5]/a")).click();
        Select account = new Select(driver.findElement(By.xpath("//*[@id=\"accountId\"]")));
        account.selectByValue("14676");
        driver.findElement(By.xpath("//*[@id=\"criteria.amount\"]")).sendKeys("1000");
        driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div[9]/button")).click();
    }

    @Test (priority = 5)
    public void Customercare() {
        driver.findElement(By.xpath("//*[@id=\"headerPanel\"]/ul[2]/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Aysel");
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("aysel.dadasheva.std@bhos.edu.az");
        driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("0505156636");
        driver.findElement(By.xpath("//*[@id=\"message\"]")).sendKeys("Experiencing issues with account login.");
        driver.findElement(By.xpath("//*[@id=\"contactForm\"]/table/tbody/tr[5]/td[2]/input")).click();
    }

    @Test (priority = 6)
    public void transferfund() {
        driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"amount\"]")).sendKeys("100");
        Select fromaccount = new Select(driver.findElement(By.xpath("//*[@id=\"fromAccountId\"]")));
        fromaccount.selectByValue("17007");
        Select toaccount = new Select(driver.findElement(By.xpath("//*[@id=\"toAccountId\"]")));
        toaccount.selectByValue("14676");
        driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div[2]/input")).click();
    }

    @Test (priority = 7)
    public void logout() {
        driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[8]/a")).click();
    }

    @Test (priority = 8)
    public void LoginError() {
        driver.findElement(By.xpath("//input[@name = \"username\"]")).sendKeys("Aysel"); //Sending name
        driver.findElement(By.xpath("//input[@name = \"password\"]")).sendKeys("ayyPara03"); // Sending PWD
        driver.findElement(By.xpath("//input[@class= \"button\"]")).click();
        String actual_error = "The username and password could not be verified.";
        WebElement expected_error = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/p"));
        String actual_error_message = expected_error.getText();
        Assert.assertEquals(actual_error, actual_error_message);
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
