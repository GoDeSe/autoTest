package main;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Brands {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Denis\\IdeaProjects\\TestClass3\\driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://auto.drom.ru/region25//");

        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[7]/div[4]/div[5]/div")).click();
        //открыли полный список брендов

        int yMax = 30;
        List<String> brandList = new ArrayList<>();
        List<Integer> mountList = new ArrayList<>();
        for (int col = 1; col<5; col++) {
            int y = 1;
            if (col == 4){
                yMax = 28;
            }
            while (y <= yMax) {
                WebElement brand = driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[7]/div[" + col + "]/div[" + y + "]/div/div/div/span[1]"));
                WebElement amount = driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[7]/div[" + col + "]/div[" + y + "]/div/div/div/span[2]"));
                if (amount.getText().equals("")) {
                    brandList.add(brand.getText());
                    mountList.add(0);
                } else {
                    brandList.add(brand.getText());
                    mountList.add(Integer.parseInt(amount.getText()));
                }
                y++;
            }
        }
        String[] brands = new String[brandList.size()];
        Integer[] amounts = new Integer[mountList.size()];
        for (int x = 0; x<brands.length; x++){
            brands[x] = String.valueOf(brandList.get(x));
            amounts[x] = mountList.get(x);
        }
        for (int u = 0; u<brands.length; u++) {
            for (int z = u+1; z < brands.length; z++) {
                if (amounts[u] < amounts[z]) {
                    int element = amounts[z];
                    amounts[z] = amounts[u];
                    amounts[u] = element;
                    String element2 = brands[z];
                    brands[z] = brands[u];
                    brands[u] = element2;
                }
            }
        }
        for (int m = 0; m<20; m++) {
            System.out.println(brands[m] + " " + amounts[m]);
        }
    }
}
