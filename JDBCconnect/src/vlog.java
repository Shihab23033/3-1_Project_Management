public class vlog {
    private String name;
    private String statement;

    public vlog() {

    }
    public vlog (String nm, String st){
        this.name= nm;
        this.statement= st;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }


}
