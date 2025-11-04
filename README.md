# Swiggy Automated Testing using Selenium WebDriver

The goal of this assignment is to automate the process of ordering food on Swiggy using Selenium WebDriver. 
The script should:
● Navigate through the website 
● Search for a restaurant 
● Add items to the cart 
● Enter the delivery address 
● Proceed to the final payment page (without completing the payment)
📖 Overview

 LINK OF AUTOMATED TESTING VIDEO: https://drive.google.com/file/d/1M2bdHN6hWjxlTkIMgfVB1XCdTkWcS01c/view?usp=sharing

This project automates the process of ordering food on Swiggy
 using Selenium WebDriver in Java.
The automation script simulates a real user journey — from logging in, searching for a restaurant, adding food items to the cart, entering a delivery address, and proceeding to the payment page — without completing the actual payment.

This demonstrates end-to-end web automation testing, element identification, synchronization, and UI validation using Selenium.

🧩 Features Automated
Step	Description
1. Login to Swiggy	Opens Swiggy homepage, clicks “Login”, and enters the phone number (manual OTP entry required).
2. Validate Page Title & URL	Prints page title and current URL to console for verification.
3. Enter Location	Types a city name (e.g., Bengaluru) and selects the correct location from suggestions.
4. Search for a Restaurant	Searches for a restaurant (e.g., Domino’s Pizza) and selects it from the results.
5. Select Food Item	Chooses the second food item from the menu and adds it to the cart.
6. Increase Quantity	Opens the cart and increases the item quantity to 2.
7. Enter Delivery Address	Fills in a new address (door number, landmark, home) and saves it.
8. Proceed to Payment Page	Clicks “Proceed to Pay” and ensures the payment page loads successfully.

⚙️ Tech Stack

Language: Java

Automation Tool: Selenium WebDriver

Build Tool: Maven (if applicable)

Browser: Google Chrome

IDE Used: IntelliJ IDEA / Eclipse

Additional Tools: Screen Recorder (for script execution recording)

🧾 Prerequisites

Before running the script, ensure you have:

Java 8 or later

Selenium WebDriver dependencies configured

Chrome browser and compatible ChromeDriver installed

Stable internet connection

Swiggy account with valid mobile number
# Console Output Example
Page Title: Order food online from India’s best food delivery service | Swiggy  
Current URL: https://www.swiggy.com/  
Selected Restaurant: Domino’s Pizza  
Selected Food Item: Veg Loaded Pizza  
Cart Total: ₹ 499  
