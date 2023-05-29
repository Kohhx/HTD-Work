package OOP_Static_Instance;
class Student {

    static int a, b, c;

    // Initializer block to initialize values for static variables
    // Static block always executed first when object is created
    static {
        System.out.println("Static block called!!");
        a = 100;
        b = 200;
        c = 300;
    }

    static void display() {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    // ==========================

    int m, n, o;

    // Instance block used initialize value of instance variable
    // ** Instance block will be executed before constructor
    {
        System.out.println("Instance block called!!");
        m = 10;
        n = 20;
        o = 30;
    }

    public Student() {
        System.out.println("Constructor called");
    }

    void display1() {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        System.out.println(m);
        System.out.println(n);
        System.out.println(o);
    }

}

public class Day14_09052023_2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student.display();
        Student s = new Student();
		s.display1();
		Student.display();
    }
}
