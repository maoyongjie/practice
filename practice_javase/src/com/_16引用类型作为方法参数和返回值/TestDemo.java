package com._16引用类型作为方法参数和返回值;

/*


 */
public class TestDemo {

    public static void main(String[] args) {
        String name = "mao";
        Name name1 = new Name();
        name1.setName("mao");
        changeName(name1);

        System.out.println(name1.getName());
    }

    public static void changeName(Name name){
        name.setName(name.getName()+name.getName());
    }

}

 class Name{
    String name;

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }
 }
