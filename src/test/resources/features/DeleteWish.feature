Feature: Delete a wish by id

  As a system user,
  i need to delete a specific product on my wishlist

  Scenario Outline: <sceneDescription> - <expectedResult>
    Given I am a logged-in user:'<userId>' and added the item '<productId>' to my wishlist
    When perform a DELETE to endpoint "/api/v1/<userId>/wishlist/<productToDelete>".
    Then the system should return the following status code '<statusCode>'

    Examples:
      | sceneDescription                                | statusCode | userId                               | productId                            | productToDelete                      |
      | when delete with success                        | 204        | f3882d8d-199c-46d2-8944-936f13645598 | 6ca5e761-7068-4414-8693-b4144272ad7e | 6ca5e761-7068-4414-8693-b4144272ad7e |
      | bad request when the product is not on the list | 400        | f3882d8d-199c-46d2-8944-936f13645598 | 6ca5e761-7068-4414-8693-b4144272ad7e | 3b9492e9-c9a6-43b2-8636-48b84c83c11d |