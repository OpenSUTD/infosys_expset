## Solution

First option is not true. If you do not write a constructor yourself, Java will always create the default nullary (no arguments) public constructor.

Second option is not true. Public getters and setters refer to the `getName` and `setName` methods. It does not mean that you an access the private attribute `name` from outside the class.

Third option is true. If you do not specify a value, it is `null`.

Fourth option is false. The default is `null` (nothing), not an empty string. 