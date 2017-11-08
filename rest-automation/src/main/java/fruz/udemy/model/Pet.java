package fruz.udemy.model;
import fruz.udemy.model.Category;
import fruz.udemy.model.Tag;


public class Pet
{
    private int id;

    private Category category;

    private String status;

    private Tag[] tag;

    private String name;

    private String[] photoUrl;







    public Category getCategory ()
    {
        return category;
    }

    public void setCategory (Category category)
    {
        this.category = category;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Tag[] getTag ()
    {
        return tag;
    }

    public void setTag (Tag[] tag)
    {
        this.tag = tag;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String[] getPhotoUrl ()
    {
        return photoUrl;
    }

    public void setPhotoUrl (String[] photoUrl)
    {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", category = "+category+", status = "+status+", tag = "+tag+", name = "+name+", photoUrl = "+photoUrl+"]";
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}