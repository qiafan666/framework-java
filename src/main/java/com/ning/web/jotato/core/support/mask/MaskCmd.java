package com.ning.web.jotato.core.support.mask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class MaskCmd {

    private String ruleKey;

    private String value;
}
