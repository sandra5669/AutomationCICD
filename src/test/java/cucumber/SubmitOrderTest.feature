@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to the Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on Confirmation Page

    Examples: 
      | name  									 | password | productName  					|
      | sandramekkadan@gmail.com | Sandra   | ADIDAS ORIGINAL 			|
      
