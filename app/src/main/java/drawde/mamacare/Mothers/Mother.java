package drawde.mamacare.Mothers;


public class Mother {
    String id, name;

    public Mother(String name, String id){
    	/**
    	 * Instantiating variables
    	 */
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public String getName(){ return  name; }
}
