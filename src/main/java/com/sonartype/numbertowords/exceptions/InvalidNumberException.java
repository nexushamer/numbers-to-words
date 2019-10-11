package com.sonartype.numbertowords.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InvalidNumberException extends RuntimeException {
    private String description;
}
