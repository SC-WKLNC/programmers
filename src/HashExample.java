import java.util.HashMap;
import java.util.Map;

public class HashExample {
    public static void main(String[] args){
        HashMap<String,String> map = new HashMap<>();
        map.put("key1","value1");

        String key = "key1";
        int h = key.hashCode();
        hash(key);
        map.get("key1");
        System.out.println("key 1 hashCode : "+  Integer.toBinaryString(h));
        System.out.println("key 1 hashCode 쉬프트 : "+  Integer.toBinaryString(h >>>16));
        System.out.println("key 1 hashCode 쉬프트 : "+Integer.toBinaryString(h^(h>>16)));

    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}


