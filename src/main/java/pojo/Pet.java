package pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Pet {
    private int id;
    private Category category;
    private String name;

    private List<String> photoUrls;
    private List<Tag> tags;
    //TODO replace with ENUM (available, pending, sold)
    private String status;
}
