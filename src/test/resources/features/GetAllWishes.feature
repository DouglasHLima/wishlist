Feature: Get all Wishes

  as a system user,
  I need to consult my complete wish list

 Scenario Outline: must seek all added wishes
    When perform a GET to endpoint "/api/v1/<userId>/wishlist"
    Then the system should return the list
    And with status code '<expectedResult>'

    Examples:
      | expectedResult | userId                               |
      | 200            | f3882d8d-199c-46d2-8944-936f13645598 |