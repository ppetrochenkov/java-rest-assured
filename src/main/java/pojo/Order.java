package pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    //TODO replace with ENUM (placed, approved, delivered)
    private String status;

    private boolean complete;
}
