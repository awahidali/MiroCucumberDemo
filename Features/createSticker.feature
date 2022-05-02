Feature: Create Sticker on Miro Application

  Scenario: Create sticker on new canvas
    Given Open the Chrome browser and launch the miro application
    When Enter UserA credential
    Then Create a board with the name Board
    And Open new Board and create there sticker
    When UserA invite UserB to Board
    Then UserB login into service and open Board
    And UserB should see created sticker on Board
