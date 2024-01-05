package com.rse.webservice.locket.payload.view.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ViewCreateRequest {

    private Long storyId;
    private Long accountId;

}
