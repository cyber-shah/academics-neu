public class Main {
        /**
     * String a = "Hello"; 
     * This creates a string literal in the string pool. 
     * String literals in Java are stored in a special pool known as the string pool, 
     * and if a string with the same content already exists in the pool, the existing reference is reused. 
     * This helps save memory and improves performance.
     * 
     * "Hello" in the string pool is shared among all the references pointing to it. 
     * Multiple variables can point to the same string literal, and they will share the same memory.
     * 
     * 
     * new String("Hello") explicitly creates a new object in the heap, even if the content is the same as an 
     * existing string literal. This can lead to more memory consumption as each object is distinct.
     */
    public static void main(String[] args) {
        // create string literals
        // they will have the same hash codes, or same memory location
        String a = "he";
        String b = "he";
        print_details(a, "a");
        print_details(b, "b");

        boolean result = a == b;
        System.out.println("a == b is :: " + result);

        // convert to upper case
        // changes the memory location and hash code
        String aUpper = a.toUpperCase();
        String bUpper = b.toUpperCase();
        System.out.println("");
        print_details(aUpper, "aUpper");
        print_details(bUpper, "bUpper");

        System.out.println(" d == c is ::" + bUpper == aUpper);

        // create string OBJECTS
        // they will have different hash codes, or different memory location
        String aObject = new String("he");
        String bObject = new String("he");
        System.out.println("");
        print_details(aObject, "aObject");
        print_details(bObject, "bObject");
        System.out.println("aObject == bObject is ::" + aObject == bObject);


        // whereas for primitive data types, they will have the same hash codes
        // even if we modify them
        char aChar = 'a';
        char bChar = 'a';
        System.out.println("");
        print_details(aChar, "aChar");
        print_details(bChar, "bChar");
        System.out.println(aChar == bChar);

        // Upper case
        char aCharUpper = Character.toUpperCase(aChar);
        char bCharUpper = Character.toUpperCase(bChar);
        System.out.println("");
        print_details(aCharUpper, "aCharUpper");
        print_details(bCharUpper, "bCharUpper");
        System.out.println(aCharUpper == bCharUpper);

    }

    public static void print_details(String a, String name) {
        System.out.println("Hash code of " + name + " = " + System.identityHashCode(a));
        System.out.println(name + " = " + a);
    }

    public static void print_details(char a, String  name) {
        System.out.println("Hash code of " + name + " = " + System.identityHashCode(a));
        System.out.println(name + " = " + a);
    }


}

