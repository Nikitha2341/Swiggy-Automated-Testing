package swiggyAutomation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

public class SwiggyAutomation {

    public static void main(String[] args) {
        try {
            // Setup ChromeDriver using WebDriverManager
        	WebDriverManager.chromedriver().setup();

            // Create ChromeOptions instance
            ChromeOptions options = new ChromeOptions();

            // Add incognito argument
            options.addArguments("--incognito");

            // Optional: start maximized
            options.addArguments("--start-maximized");

            // Initialize ChromeDriver with options
            WebDriver driver = new ChromeDriver(options);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            Actions actions = new Actions(driver);

            driver.manage().window().maximize();
            driver.get("https://www.swiggy.com/");
            System.out.println("✅ Opened Swiggy homepage");
          
            // Step 1: Click Sign In
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(text(),'Sign in') or contains(text(),'Log in') or contains(text(),'Sign up')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

            // Wait for overlay to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("overlay-sidebar-root")));

            // 2 Enter Mobile Number
            WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='mobile' or @placeholder='Enter your mobile number']")));
            phoneInput.sendKeys("9133988793");

            // 3 Click Continue
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"overlay-sidebar-root\"]/div/div/div[2]/div/div/div/form/div[2]/a")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);

            System.out.println("⏸️ Please enter the OTP manually in the browser...");
            Thread.sleep(25000); // Wait for OTP entry

            // Step 3: Print Page Details
            System.out.println("Page Title: " + driver.getTitle());
            System.out.println("URL: " + driver.getCurrentUrl());

            // Step 4: Enter Location
         // Enter text in the input box
            WebElement searchInput = driver.findElement(By.xpath("//input[@type='text']")); // adjust if needed
            searchInput.sendKeys("Bangalore");

            // Wait for the suggestions to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_2BgUI']")));

            // Select the first suggestion
            WebElement firstSuggestion = driver.findElement(By.xpath("(//div[@class='_2BgUI'])[1]"));
            firstSuggestion.click();

            // Step 5: Search Restaurant

            WebElement searchBox = driver.findElement(By.xpath("//div[text()='Search for restaurant, item or more']"));
            // Click to activate the search
            searchBox.click();      
            WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search for restaurants and food']")));
            searchBar.sendKeys("Domino's Pizza");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@data-testid='autosuggest-item'])[1]")));
            // Re-locate and click the first suggestion
            driver.findElement(By.xpath("(//button[@data-testid='autosuggest-item'])[1]")).click();            
            Thread.sleep(8000);
            
         // Wait until the restaurant card is clickable
            WebElement restaurantCard = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a[data-testid='resturant-card-anchor-container']")));

            // Click the restaurant card
            restaurantCard.click();
            Thread.sleep(8000);
            

            WebElement addButton = driver.findElement(By.xpath("((//div[@data-testid='normal-dish-item'])[2]//button[.//div[text()='Add']])[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);

            // Step 6: Add Food Item


         // Wait for the "Continue" button inside the popup to be clickable
                     WebElement continueButton = wait.until(
                         ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='menu-customize-continue-button']"))
                     );

                     // Click the "Continue" button
                     continueButton.click();
                     
                     WebElement addItemButton = wait.until(
                             ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-cy='customize-footer-add-button']"))
                         );

                         // Click the "Add Item to Cart" button
                         addItemButton.click();

                         // Optional: Log the action
                         System.out.println("Clicked the 'Add Item to Cart' button");
           
            takeScreenshot(driver, "ItemAdded.png");

         // Step 7: View Cart
            try {
                // Wait for the View Cart button to appear in DOM
                WebElement viewCartButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[@id='view-cart-btn' or .//span[normalize-space(text())='View Cart']]")
                ));

                // Scroll to it (helps if hidden under sticky elements)
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewCartButton);

                // Wait a moment for any overlay/floating element to vanish
                Thread.sleep(1500);

                // Double-check if button is clickable (retry if intercepted)
                wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));

                // Click using JS to bypass overlay interception
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartButton);

                System.out.println("🛒 Successfully clicked 'View Cart' button!");

            } catch (ElementClickInterceptedException e) {
                System.out.println("⚠️ Element click intercepted, trying JavaScript click fallback...");
                WebElement fallbackButton = driver.findElement(By.id("view-cart-btn"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fallbackButton);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("❌ Could not click 'View Cart' button");
            }

            Thread.sleep(5000);

            WebElement increaseQty = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='BbiqG' and text()='+']")));
            increaseQty.click();
            takeScreenshot(driver, "QuantityIncreased.png");

         // Wait for the 'REPEAT LAST' button to appear and click it
            WebElement repeatLastButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space(text())='REPEAT LAST']")
            ));
            repeatLastButton.click();
            Thread.sleep(5000);
            
            // Step 8: Add Address
         // Wait for the "Add New" button to appear and click it
            WebElement addNewButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='_3kHjz _6RaiM' and normalize-space(text())='Add New']")
            ));
            addNewButton.click();
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='building']"))).sendKeys("123, 4th Floor");
            driver.findElement(By.xpath("//input[@name='landmark']")).sendKeys("Near City Mall");
            
            WebElement homeButton = driver.findElement(By.xpath("(//div[@class='_1qiSu'])[1]"));
            	
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homeButton);
            	homeButton.click();
            
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@class='_1kz4H']")).click();
            takeScreenshot(driver, "AddressAdded.png");
            
            WebElement restaurant = driver.findElement(By.xpath("//div[@class='_2h0kv']"));
            System.out.println("💰 Restaurant Name: " + restaurant.getText());
            
            WebElement dishName = driver.findElement(By.xpath("//div[@class='_1i2tH']"));
            System.out.println("💰 Dish Name: " + dishName.getText());
            
            WebElement total = driver.findElement(By.xpath("//div[@class='_1wC-f']"));
            System.out.println("💰 Cart Total: " + total.getText());

            // Step 9: Proceed to Pay
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='_4dnMB']"))).click();
            takeScreenshot(driver, "PaymentPage.png");


            System.out.println("✅ Automation completed successfully!");
            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(System.getProperty("user.dir") + "/screenshots/" + fileName);
            FileUtils.copyFile(src, dest);
            System.out.println("📸 Screenshot saved: " + fileName);
        } catch (Exception e) {
            System.out.println("❌ Screenshot failed: " + e.getMessage());
        }
    }
}


