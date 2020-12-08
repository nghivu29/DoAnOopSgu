package test.data;

public class Author extends LibObject{

    public static Author create(String name){
        Author author = new Author();
        author.name = name;
        if (!author.init()){
            return null;
        }
        return author;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        return true;
    }
}
