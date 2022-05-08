Feature: Add a Wish Validator

  As system,
  It is important to set some limits
  to protect the proper functioning of the ecosystem

  Scenario: Bad request when try to add more products than allowed
    Given A user Post 20 wishes to endpoint '/api/v1/f3882d8d-199c-46d2-8944-936f13645598/wishlist'
    When perform a POST to endpoint "/api/v1/f3882d8d-199c-46d2-8944-936f13645598/wishlist" adding a new wish
    Then the system should return the status code 400 - Bad Request
    And user GET user wishlist on endpoint '/api/v1/f3882d8d-199c-46d2-8944-936f13645598/wishlist' must be 20 products on size.