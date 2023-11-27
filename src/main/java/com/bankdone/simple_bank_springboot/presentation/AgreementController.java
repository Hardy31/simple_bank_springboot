package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.AgreementService;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class AgreementController {

    private final AgreementService agreementService;

    @GetMapping("agreement/{id}")
    public Agreement getBYId(@PathVariable long id) {
        return agreementService.getById(id);
    }

    @GetMapping("agreements/all")
    public List<Agreement> getAll() {
        return agreementService.getAll();
    }

    @PostMapping("createAgreement")
    public Agreement createAgreement(@RequestBody Agreement agreement) {
        return agreementService.create(agreement);
    }

    @DeleteMapping("deleteArgument/{id}")
    public void deleteAgreement(@PathVariable Long id) {
        agreementService.delete(id);
    }

    public Agreement editAgreement(@RequestBody Agreement agreement) {
        return agreementService.edit(agreement);
    }

}
