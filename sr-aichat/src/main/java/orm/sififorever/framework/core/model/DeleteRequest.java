package orm.sififorever.framework.core.model;

import lombok.Data;

@Data
public class DeleteRequest {
    private String model;

    public DeleteRequest(String model) {
        this.model = model;
    }
}
