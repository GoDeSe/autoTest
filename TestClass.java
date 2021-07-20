package ru.autoDrom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

public class TestClass {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Denis\\IdeaProjects\\TestClass2\\driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        String log = ("dez1993@mail.ru");    //мой логин
        String pass = ("qwe321");      //мой пароль

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://auto.drom.ru/");

        WebElement mainpage = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[3]/a"));
        mainpage.click();
        WebElement sign = driver.findElement(By.xpath("//*[@id=\"sign\"]"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement login = driver.findElement(By.xpath("//*[@id=\"signbutton\"]"));
        sign.sendKeys(log);
        password.sendKeys(pass);
        login.click();
        //авторизировались на сайте

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[2]/a[1]")).click();

        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[1]/div[1]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[1]/div[1]/div[2]/div[3]")).click();
        //отфильтровали Toyota
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[1]/div[2]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[1]/div[2]/div[2]/div[90]")).click();
        //отфильтровали Harrier
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[2]/div[3]/div[2]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[2]/div[3]/div[2]/div[2]/div[6]")).click();
        //отфильтровали гибрид
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[3]/div[3]/div[1]/label")).click();
        //не проданные
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[4]/div[2]/span")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[4]/div[3]/div[3]/div[1]/div/div[1]/div/div/div[1]/input")).sendKeys("1");
        //пробег от 1км
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[2]/div[2]/div[1]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[2]/div[2]/div[1]/div[2]/div[17]")).click();
        //от 2007г
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[3]/form/div[5]/div[3]/button")).click();
        //поиск


        int c = 1;
        for (int p = 1; p < 2; p++)
        {
        while (c<20) {
            int x = 5;
            WebElement carYear = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div[1]/div[1]/div[4]/div/div[2]/a[" + c + "]/div[2]/div[1]/div"));
            //проверка года авто
            int a = Integer.parseInt(carYear.getText().substring(16));
            if (a < 2007) {
                System.out.println("Car" + c + "<2007");
            }
            WebElement sold = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div[1]/div[1]/div[4]/div/div[2]/a[" + c + "]"));
            //проверка не проданности авто
            if (sold.getAttribute("text-decoration") == ("line-through")) {
                System.out.println("Car " + c + " is sold");
            }
            try {
                WebElement mileage = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div[1]/div[1]/div[4]/div/div[2]/a[" + c + "]/div[2]/div[2]/span[" + x + "]"));
            } catch (NoSuchElementException e) {
                x = x - 1;
            }
            WebElement mileage = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div[1]/div[1]/div[4]/div/div[2]/a[" + c + "]/div[2]/div[2]/span[" + x + "]"));
            //проверка пробега
            String[] mileage2 = mileage.getText().split(" ");
            int mileage3 = Integer.parseInt(mileage2[0]);
            if (mileage3 >= 1){
            }
            else{
                System.out.println("Car " + c + " <1km mileage");
            }
            c++;
        }
        //переход на следующую страницу + окончание программы
        WebElement nextPage = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div[1]/div[1]/div[4]/div/div[3]/div/div/a"));
        nextPage.click();
        if (driver.getCurrentUrl().contains("page2")){
            System.out.println("End of program");
        }
        }
        driver.quit();
    }
}