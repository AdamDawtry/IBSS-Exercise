Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

    Background:
      Given User exists for Username "Alex20" with Password "ABC123"
      And Username set to "Alex20"
      And Password set to "ABC123"

    Scenario: Valid password change facilitated
      When new password set to "DEF456"
      And verify password set to "DEF456"
      And change submitted
      Then response is "Password changed successfully"

    Scenario: New password not entered
      When change submitted
      Then response is "Please enter a new password"

    Scenario: Mismatched new password
      When new password set to "DEF456"
      And verify password set to "DEF555"
      And change submitted
      Then response is "New passwords do not match"

    Scenario: New password contains no digits
      When new password set to "DEF"
      And verify password set to "DEF"
      And change submitted
      Then response is "Password must contain at least 1 digit"

    Scenario: New password is too short
      When new password set to "D2"
      And verify password set to "D2"
      And change submitted
      Then response is "Password must contain at least 3 characters"

    Scenario: Password change cancelled
      When new password set to "DEF456"
      And verify password set to "DEF456"
      And change cancelled
      Then response is "Password change cancelled"