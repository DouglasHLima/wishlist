Feature: Has on List

  As a system user,
  i need to see if a specific item is in the list

  Scenario Outline: <sceneDescription> - <expectedResult>
    Given I am a logged-in user:'<userId>' and added the item '<productId>' to my list
    When perform a GET to endpoint "/api/v1/<userId>/wishlist/<productToSearch>".
    Then the system should return '<expectedResult>'
    And with following status code '<statusCode>'

    Examples:
      | sceneDescription                    | expectedResult | statusCode | userId                               | productId                            | productToSearch                      |
      | when the product is on the list     | true           | 200        | f3882d8d-199c-46d2-8944-936f13645598 | 6ca5e761-7068-4414-8693-b4144272ad7e | 6ca5e761-7068-4414-8693-b4144272ad7e |
      | when the product is not on the list | false          | 200        | f3882d8d-199c-46d2-8944-936f13645598 | 6ca5e761-7068-4414-8693-b4144272ad7e | 3b9492e9-c9a6-43b2-8636-48b84c83c11d |