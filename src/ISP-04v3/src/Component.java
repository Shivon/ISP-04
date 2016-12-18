/**
 * Created by murat on 14.12.16.
 */
public class Component {

    private String name;
    private String[] content;
    private int count;

    public Component(String name)
    {
        this.name = name;
        content = new String[Constants.AMOUNT_OF_OPTIONS];
        count = 0;
    }

    public void insertContent(String[] content)
    {
        this.content = content;
    }

    public void insertContent(String content) throws Exception {
        if(count == Constants.AMOUNT_OF_OPTIONS) throw new Exception("Compontent ist bereits voll!");
        this.content[count] = content;
        count++;
    }

    public String getOption(int index)
    {
        return content[index];
    }

    public String getName()
    {
        return name;
    }

    public String[] values()
    {
        return content;
    }
}