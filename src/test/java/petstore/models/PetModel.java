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

    public PetModel (String name, String status) {

        this.name = name;
        this.status = status;
    }

    public PetModel( String name,CategoryModel category, String[] photoUrls, TegModel[] tags, String status) {
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public PetModel (){}



}
