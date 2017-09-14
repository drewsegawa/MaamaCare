package drawde.mamacare.Mothers;

/**
 * Created by emute on 14/09/2017.
 */

public class Mother {
    String id, name;

    public Mother(String name, String id){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public String getName(){ return  name; }
}
