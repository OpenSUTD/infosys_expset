# MCQ: Fundamentals

Please resize the Task panel if you are unable to read the code below properly.

## Question 3

Study the code below.

```java
public class MyProgram {
    public static void main(String[] args){
        String time = args[0];
        if (time.equals("am")){
            String message = "Good morning!";
        } else if(time.equals("pm")){
            String message = "Good afternoon!";
        } else {
            String message = "Unrecognised time";
        }
        System.out.println(message);
    }
}
```

What will be the output if the program was built and run with "pm" as the argument?

