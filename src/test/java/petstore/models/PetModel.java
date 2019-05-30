package petstore.models;

import lombok.Data;

@Data
public class PetModel {

    private int id;
    private CategoryModel category;
    private String name;
    private String[] photoUrls;
    private TegModel[] tags;
    private String status;




//    public int getId() {
//        return id;
//    }
//
//    public CategoryModel getCategory() {
//        return category;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String[] getPhotoUrls() {
//        return photoUrls;
//    }
//
//    public TegModel[] getTags() {
//        return tags;
//    }
//
//    public String getStatus() {
//        return status;
//    }

    public PetModel(int id , String name, CategoryModel category, String[] photoUrls, TegModel[] tags, String status) {

        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public PetModel (int id, String name) {

        this.id = id;
        this.name = name;
            }

public PetModel (){}



}
