package com.duofan.weichou.controller.v1.condition.common;

import com.duofan.weichou.controller.v1.condition.common.BaseCondition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCondition extends BaseCondition {
    private String name;

    private boolean gender;
    public String getName() {
        if (this.name == null) {
            return "";
        }
        return this.name;
    }
}

