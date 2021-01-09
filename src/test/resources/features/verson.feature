Feature: the version can be retrived
    Scenario: client makes call to GET /version
        When client calls /version
        Then client receives SC 200
        And client receives version 1.0