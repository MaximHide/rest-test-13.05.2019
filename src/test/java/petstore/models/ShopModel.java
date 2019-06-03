package petstore.models;

import lombok.Data;

@Data
public class ShopModel {


    private int id;
    private int petId;
    private int quantity;
    private String date;
    private String status;
    private boolean complete;


    public ShopModel(int id, int petId, int quantity, String date, String status, boolean comlete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
        this.complete = complete;
    }

    public ShopModel(int id, int petId, int quantity,  String status, boolean comlete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;

        this.status = status;
        this.complete = complete;
    }
    public ShopModel() {}


}



