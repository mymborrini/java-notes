/**
* SERIALISATION DESERIALISATION AND EXTERNALISATION:
* 
* SERIALISATION and DESERIALISATION:
* It's the conversion of Java object into a stream of bytes, which we can then transfer over a network, in a file, or in a database
* 
* Classes that are eligible for serialisation need to implement a special marker interface Serializable, 
* In order to a class to be Serializable it must:
*  . implement Serializable interface
*  . All the fields in the class must be Serializable, transient (non-Serializable) or static, which since belongs to a class 
*    it will not be Serialize 
* 
* Employee object will be converted into an ObjectOutputStream (serialisation) then it will save into a file with accept stream of bytes FileOutputStream().
* This will save a file of bytes
* ObjectOutputStream can be converted as say before not only in a FileInputStream but in a Socket.getInputStream() or Socket.getOutputStream().
* 
* The JVM associates a version (long number) with each Serializable class, we use the serialVersionUID attribute to remmeber versions of a Serializable
* class to verify that a loaded class and a serialized object are compatible.
* 
* Deserialisation does not use a constructor, after creating an obejct it use reflection to write the data to the fields. 
* 
* EXTERNALISATION:
* Is used whenever you need to customise your serialisation mechanism.
* Externalisation give the programmer full control in reading and writing objects during serialisation and deserialisation.
* 
* When implements Externalisable interface you have to implements two methods: writeExternal and readExternal, which receive as an argument.
* ObjectOutput and ObjectInput (these are two interfaces that ObjectOutputStream and ObjectInputStream implements by default.)
* 
* So JVM will proceed in this way during serialisation.
*  . Check if Externalisable is implememnted
*  . CHeck if Serializable is implememnted and in this case use ObjectOutputStream as a "default class".
*/ 
 class Serializations {
     
     
     static class Employee implements Serializable {
         private String name;
         private Integer age;
         
         public Employee(String name, Integer age){
             this.name = name;
             this.age = age;
         }
         
     }
     
    
    public void run() throws Exception {
        Employee emp = new Employee("Mattia", 32);
        FileOutputStream fos = new FileOutputStream("/emp.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(emp);
        
        oos.close();
        fos.close();
    }
    
    // This will deserialise the state from before saved in a file into an object
    public void deserialise() throws Exception {
        FileInputStream fis = new FileInputStream("/emp.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Employee emp = (Employee) ois.readObject();
        
        ois.close();
        fis.close();
    }
     
 }  
   