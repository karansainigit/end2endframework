Feature: Login into Application

Scenario Outline: Positive test validating login
Given Initialize browser with chrome
And Navigate to "http://live.demoguru99.com/index.php/" site
And Click on Login link in Home page to land on Sign In page
When User enters <username> and <password> and click on Login
Then Verify User is successfully logged in
And Close browsers

Examples:
|username			|password	|
|test123@gmail.com	|1234567890	|
|test123@yahoo.com	|9876543210	|