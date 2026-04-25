package com.platform.testing.admin.application.project.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectCommand(@NotBlank String name, String description) {

}
