# MCQ: OOP

## Question 2

Study the code below.

```java
public class MyProgram {
    public static String getLongestName(? names){
        int longest_length = 0;
        String longest_name = null;
        for(String name : names){
            if(name.length() > longest_length){
                longest_length = name.length();
                longest_name = name;
            }
        }
        return longest_name;
    }
}
```

What can `?` (the type of argument `names`) be if I want `getLongestName` to accept any of the following **without changing the method body**:

- `ArrayList<String>`
- `LinkedList<String>`
- `HashSet<String>`

Hint: study the Java documentation on each of these classes.