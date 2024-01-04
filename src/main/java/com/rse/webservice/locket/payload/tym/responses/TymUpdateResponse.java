package com.rse.webservice.locket.payload.tym.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class TymUpdateResponse {
    private Boolean success;
}
