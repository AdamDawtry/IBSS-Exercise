Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

    Background:
      Given User exists for Username "Alex20" with Password "ABC123"

    Scenario: Valid password change facilitated
      When Username set to "Alex20"
      And Password set to "ABC123"
      And new password set to "DEF456"
      And verify password set to "DEF456"
      And change submitted
      Then response is "Password changed successfully"

    Scenario: New password not entered
      When Username set to "Alex20"
      And Password set to "ABC123"
      When change submitted
      Then response is "Please enter a new password"

    Scenario: Mismatched new password
      When Username set to "Alex20"
      And Password set to "ABC123"
      And new password set to "DEF456"
      And verify password set to "DEF555"
      And change submitted
      Then response is "New passwords do not match"

    Scenario: New password contains no digits
      When Username set to "Alex20"
      And Password set to "ABC123"
      And new password set to "DEF"
      And verify password set to "DEF"
      And change submitted
      Then response is "Password must contain at least 1 digit"

    Scenario: New password is too short
      When Username set to "Alex20"
      And Password set to "ABC123"
      And new password set to "D2"
      And verify password set to "D2"
      And change submitted
      Then response is "Password must contain at least 3 characters"

    Scenario: Password change cancelled
      When Username set to "Alex20"
      And Password set to "ABC123"
      And new password set to "DEF456"
      And verify password set to "DEF456"
      And change cancelled
      Then response is "Password change cancelled"
    
    Scenario: User does not exist
      When Username set to "Sarah3"
      And Password set to "ABC123"
      And new password set to "DEF456"
      And verify password set to "DEF456"
      And change cancelled
      Then response is "No user with that username exists"
    
    Scenario: Given password does not match account password
      When Username set to "Alex20"
      And Password set to "ABC345"
      And new password set to "DEF456"
      And verify password set to "DEF456"
      And change cancelled
      Then response is "Current password is incorrect"