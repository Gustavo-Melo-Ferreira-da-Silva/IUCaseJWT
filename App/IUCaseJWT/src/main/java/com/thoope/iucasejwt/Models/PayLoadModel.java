package com.thoope.iucasejwt.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PayLoadModel(String role, String seed, String name) {

    public PayLoadModel(@JsonProperty(value = "Role", required = true) String role,
                        @JsonProperty(value = "Seed", required = true) String seed,
                        @JsonProperty(value = "Name", required = true) String name) {
        this.role = role;
        this.seed = seed;
        this.name = name;
    }
}
