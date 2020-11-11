## Solution

Use `.equals()` to compare objects (e.g. strings) and `==` to compare primitives.

To be more exact, `==` is the _identity operator_, so two objects references ("variables") are only `==` if they point they actually point to the same object residing in memory, like so:

```java
public class MyProgram {
    public static void main(String[] args){
        String s1 = "hello";
        String s2 = s1;
        boolean eq = s1 == s2; // true
    }
}
```  