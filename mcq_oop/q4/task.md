# MCQ: OOP

## Question 4

Study the three classes below.

```java
public class Human {
    public void greet(){
        System.out.println("Hello World!");
    }
}
```

```java
public class SUTDProf extends Human{
    public void greet(){
        super.greet();
        System.out.println("I am a prof at SUTD");
    }
}
```

```java
public class ISTDProf extends SUTDProf {
    public void greet() {
        System.out.println("I'm from ISTD");
    }
}
```

The class `SUTDProf` extended `Human` but created another `void greet()` function. This is known as: