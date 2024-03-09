import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Site {
        WebDriver driver= new ChromeDriver();
        public Site(){
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.ugrasu.ru/timetable/faculty/163");
        }
        void quit(){
            driver.quit();
        }
        void enterGroup(String groupLink){
            WebElement Button = driver.findElement(By.xpath("//*[contains(text(), '" + groupLink + "')]"));
            Button.click();
        }
    public String generateWeek(WebDriver driver, String dayWeek,String dayWeek2, String dayName, String noClassesMessage) {
        StringBuilder result = new StringBuilder();

        if (dayWeek.contains("Занятий нет")) {
            result.append(dayName);
            result.append("\n\n");
            result.append(noClassesMessage);
            result.append("\n________________________________________________\n");
        } else {
            WebElement parentElement = driver.findElement(By.id(dayWeek2));
            List<WebElement> disciplineListElements = parentElement.findElements(By.className("discipline-list"));

            result.append("\n                       ").append(dayName);
            result.append("\n__________________________");

            String time, nomerPari, disciplina, prepod, cabinet;

            for (WebElement element : disciplineListElements) {
                time = element.findElement(By.className("disc-time")).getText();
                nomerPari = element.findElement(By.className("disc-number")).getText();
                disciplina = element.findElement(By.className("disc-name")).getText();
                prepod = element.findElement(By.className("disc-teacher-link")).getText();
                cabinet = element.findElement(By.className("disc-cab")).getText();

                result.append("\n" + "Пара №: ").append(nomerPari).append("\n").append("\n").append(time).append("\n").append("\n").append(disciplina).append("\n").append("\n").append("Преподователь: ").append(prepod).append("\n").append("\n").append("Кабинет: ").append(cabinet);
                result.append("\n__________________________");
            }

            result.append("\n________________________________________________\n");
        }

        return result.toString();
    }
        String generalInfo() {
            StringBuilder result = new StringBuilder("________________________________________________\n");
            String dayWeek1 =(driver.findElement(By.xpath("//*[@id=\"dayWeek1\"]")).getText());
            String dayWeek2 =(driver.findElement(By.xpath("//*[@id=\"dayWeek2\"]")).getText());
            String dayWeek3 =(driver.findElement(By.xpath("//*[@id=\"dayWeek3\"]")).getText());
            String dayWeek4 =(driver.findElement(By.xpath("//*[@id=\"dayWeek4\"]")).getText());
            String dayWeek5 =(driver.findElement(By.xpath("//*[@id=\"dayWeek5\"]")).getText());
            String dayWeek6 =(driver.findElement(By.xpath("//*[@id=\"dayWeek6\"]")).getText());

            result.append(generateWeek(driver, dayWeek1,"dayWeek1", "Понедельник","Занятий нет((МЕД))"));
            result.append(generateWeek(driver, dayWeek2, "dayWeek2", "Вторник","Занятий нет(очен жал)"));
            result.append(generateWeek(driver, dayWeek3, "dayWeek3", "Среда","Занятий нет((МЕД))"));
            result.append(generateWeek(driver, dayWeek4, "dayWeek4", "Четверг","Занятий нет(   (   )"));
            result.append(generateWeek(driver, dayWeek5, "dayWeek5", "Пятница","Занятий нет((МЕД))"));
            result.append(generateWeek(driver, dayWeek6, "dayWeek6", "Суббота","Занятий нет((МЕД))"));

            return result.toString();
            }
        String todayInfo(){
             StringBuilder result = new StringBuilder("________________________________________________\n");

            String dayWeek1 =(driver.findElement(By.xpath("//*[@id=\"dayWeek1\"]")).getText());
            String dayWeek2 =(driver.findElement(By.xpath("//*[@id=\"dayWeek2\"]")).getText());
            String dayWeek3 =(driver.findElement(By.xpath("//*[@id=\"dayWeek3\"]")).getText());
            String dayWeek4 =(driver.findElement(By.xpath("//*[@id=\"dayWeek4\"]")).getText());
            String dayWeek5 =(driver.findElement(By.xpath("//*[@id=\"dayWeek5\"]")).getText());
            String dayWeek6 =(driver.findElement(By.xpath("//*[@id=\"dayWeek6\"]")).getText());

             if(dayWeek1.contains("сегодня")){
                 result.append(generateWeek(driver, dayWeek1,"dayWeek1", "Понедельник","Занятий нет((МЕД))"));
             }
            if(dayWeek2.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek2, "dayWeek2", "Вторник","Занятий нет(очен жал)"));
            }
            if(dayWeek3.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek3, "dayWeek3", "Среда","Занятий нет(ну вроде)"));
            }
            if(dayWeek4.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek4, "dayWeek4", "Четверг","Занятий нет(   (   )"));
            }
            if(dayWeek5.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek5, "dayWeek5", "Пятница","Занятий нет(       =))"));
            }
            if(dayWeek6.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek6, "dayWeek6", "Суббота","Занятий нет(hype)"));
            }
            return result.toString();
        }
        String tomorrowInfo(){
            StringBuilder result = new StringBuilder("________________________________________________\n");

            String dayWeek1 =(driver.findElement(By.xpath("//*[@id=\"dayWeek1\"]")).getText());
            String dayWeek2 =(driver.findElement(By.xpath("//*[@id=\"dayWeek2\"]")).getText());
            String dayWeek3 =(driver.findElement(By.xpath("//*[@id=\"dayWeek3\"]")).getText());
            String dayWeek4 =(driver.findElement(By.xpath("//*[@id=\"dayWeek4\"]")).getText());
            String dayWeek5 =(driver.findElement(By.xpath("//*[@id=\"dayWeek5\"]")).getText());
            String dayWeek6 =(driver.findElement(By.xpath("//*[@id=\"dayWeek6\"]")).getText());

            if(dayWeek1.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek2, "dayWeek2", "Вторник","Занятий нет"));
            }
            if(dayWeek2.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek3, "dayWeek3", "Среда","Занятий нет"));
            }
            if(dayWeek3.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek4, "dayWeek4", "Четверг","Занятий нет"));
            }
            if(dayWeek4.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek5, "dayWeek5", "Пятница","Занятий нет"));
            }
            if(dayWeek5.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek6, "dayWeek6", "Суббота","Занятий нет"));
            }
            if(dayWeek6.contains("сегодня")){
                result.append(generateWeek(driver, dayWeek1,"dayWeek1", "Понедельник","Занятий нет"));
            }
            return result.toString();
        }
    }