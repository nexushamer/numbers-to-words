package com.sonartype.numbertowords.controllers;

import com.sonartype.numbertowords.models.MessageResponse;
import com.sonartype.numbertowords.services.ConverterService;
import com.sonartype.numbertowords.validators.MaxSizeString;
import com.sonartype.numbertowords.validators.ValidStringNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/numbers-converter")
@Validated
public class NumbersConverterController {
    private final ConverterService converterService;

    @Autowired
    public NumbersConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping(value = "/{numbers}")
    public @ResponseBody
    MessageResponse numbersToWords(
            @Valid @NotBlank @MaxSizeString @ValidStringNumbers
            @PathVariable("numbers") final String numbers) {
        final String words = converterService.numbersToWords(numbers);

        return new MessageResponse(words);
    }

}
