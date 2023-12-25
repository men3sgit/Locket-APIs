package com.rse.webservice.locket.utils.templates;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class TemplateConfig {
    private Map<String, Object> properties;
    private String path;
}
