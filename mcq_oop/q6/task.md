# MCQ: OOP

## Question 6

Study the three classes below. The methods have been made static, and super calls removed.

```java
public class Human {
    public static void greet(){
        System.out.println("Hello World!");
    }
}
```

```java
public class SUTDProf extends Human{
    public static void greet(){
        System.out.println("I am a prof at SUTD");
    }
}
```

```java
public class ISTDProf extends SUTDProf {
    public static void greet() {
        System.out.println("I'm from ISTD");
    }
}
```

What is printed to the console when the following code is run?

```java
public class MyProgram {
    public static void main(String[] args){
        Human h = new ISTDProf();
        h.greet();
    }
}
```