## Solution

In Java, a new variable scope is created whenever a set of curly
  braces are opened, and variables will exist within the same scope or nested scopes.
  Since `message` is only created in the `if` scope, it will be destroyed upon leaving
  the `if` block.