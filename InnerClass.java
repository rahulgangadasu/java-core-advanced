package java_code;

public class InnerClass {
    void show(){
        System.out.println("in InnerClass");
    }
    public class InnerClass1{
        void show(){
            System.out.println("in InnerClass1");
        }
        public class InnerClass2{
            void show(){
                System.out.println("in InnerClass2");
            }
           
        }
        }
        public static void main(String[] args) {
            InnerClass.InnerClass1.InnerClass2 in = new InnerClass().new InnerClass1().new InnerClass2();
            in.show();
        }
    }
    
