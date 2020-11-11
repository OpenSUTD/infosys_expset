# Solution

Read up on static vs dynamic binding.

In this case, dynamic binding is used, so the correct `greet` will be resolved at runtime depending on what `h` actually is. In this case, it resolves to `ISTDProf`.

However, `ISTDProf.greet` does not make any calls to super, so it will only print "I'm from ISTD".