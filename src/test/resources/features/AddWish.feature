Feature: Add a Wish

  As a system user,
  I need to add a product to my wish list to see them later.

  Scenario Outline: <sceneDescription> - <expectedResult>
    Given I am a user logged in, i want to add the product: '<productId>' to my wish list
    When perform a POST to endpoint "/api/v1/<userId>/wishlist"
    Then the system should return the status code '<expectedResult>'
    And must display '<errors>' errors

    Examples:
      | sceneDescription                  | expectedResult | errors | userId                               | productId                            |
      | add product with success          | 201            | 0      | f3882d8d-199c-46d2-8944-936f13645598 | 6ca5e761-7068-4414-8693-b4144272ad7e |
      | error on add without productId    | 400            | 1      | f3882d8d-199c-46d2-8944-936f13645598 |                                      |
      | bad request on add without userId | 404            | 1      |                                      | 6ca5e761-7068-4414-8693-b4144272ad7e |