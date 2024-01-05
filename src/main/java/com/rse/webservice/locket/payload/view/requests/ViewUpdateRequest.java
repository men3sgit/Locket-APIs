package com.rse.webservice.locket.payload.view.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ViewUpdateRequest {

    private Long storyId;

    private Long accountId;

    private String interactKind;

}
