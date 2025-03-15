Feature: User adds a product to the cart and checkout after logging in

@addToCartSuccess
Scenario: Successfully add product to cart after login
    Given I open the browser and login with valid credentials
    When I add a product to the cart
    Then The product should be added to the cart successfully

@removeFromCartSuccess
Scenario: Successfully remove product from cart
    Given I open the browser and login with valid credentials
    When I add a product to the cart
    When I remove a product from the cart
    Then The product should be removed from the cart successfully

@checkoutProduct
Scenario: User checkout product that has been add to cart
    Given I open the browser and login with valid credentials
    When I add a product to the cart
    When I proceed to checkout
    When I fill in the checkout information
    Then The product should be checked out successfully

@checkoutProductwithoutFirstName
Scenario: User checkout a product, but on the 'Checkout, Your Information' page, the 'First Name' field is left blank
    Given I open the browser and login with valid credentials
    When I add a product to the cart
    When I proceed to checkout
    When I fill in the checkout information without first name
    Then I should see an error message for blank first name

@checkoutProductwithoutLastName
Scenario: User checkout a product, but on the 'Checkout, Your Information' page, the 'Last Name' field is left blank
    Given I open the browser and login with valid credentials
    When I add a product to the cart
    When I proceed to checkout
    When I fill in the checkout information without last name
    Then I should see an error message for blank last name

@checkoutProductwithoutPostalCode
Scenario: User checkout a product, but on the 'Checkout, Your Information' page, the 'ZIP/Postal Code' field is left blank
    Given I open the browser and login with valid credentials
    When I add a product to the cart
    When I proceed to checkout
    When I fill in the checkout information without Postal Code
    Then I should see an error message for blank Postal Code