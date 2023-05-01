public class Container implements Comparable<Container> {
    private String UUID;
    private String shortName;
    private String longName;
    public String get_UUID(){
        return UUID;
    }
    public String get_shortName(){
        return shortName;
    }
    public String get_longName(){
        return longName;
    }
    public void set_UUID(String UUID){
        this.UUID = UUID ;
    }
    public void set_shortName(String shortName){
        this.shortName = shortName ;
    }
    public void set_longName(String longName){
        this.longName = longName ;
    }
    public String toString(){
        return  "    <CONTAINER UUID=" + get_UUID()+ ">\n" +
                "        <SHORT-NAME>"+ get_shortName()+"</SHORT-NAME>\n" +
                "        <LONG-NAME>"+ get_longName()+"</LONG-NAME>\n" +
                "    </CONTAINER>";
    }
    public int compareTo(Container a){
        if(this.get_shortName().charAt(9)>a.get_shortName().charAt(9))
            return 1;
        else if(this.get_shortName().charAt(9)<a.get_shortName().charAt(9))
            return -1;
        else
            return 0;
    }
}
