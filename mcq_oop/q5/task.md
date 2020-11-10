# MCQ: OOP

## Question 5

Study the three classes below (same as question 4).

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

What is printed to the console when the following code is run?

```java
public class MyProgram {
    public static void main(String[] args){
        Human h = new ISTDProf();
        h.greet();
    }
}
```