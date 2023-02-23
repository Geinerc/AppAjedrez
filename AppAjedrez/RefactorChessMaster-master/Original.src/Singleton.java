public class Singleton {

   // instancia única de la clase
   private static Singleton instance = null;

   // constructor privado para evitar la creación de instancias fuera de la clase
   private Singleton() {}

   // método público estático que devuelve la instancia única de la clase
   public static Singleton getInstance() {
      if (instance == null) {
         instance = new Singleton();
      }
      return instance;
   }

   // métodos de la clase Singleton
   public void doSomething() {
      // código de la instancia
   }
}
