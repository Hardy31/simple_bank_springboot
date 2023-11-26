package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.AgreementService;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class AgreementController {
    @Autowired
    private AgreementService agreementService;

    @GetMapping("agreement/{id}")
    public Agreement getBYId(@PathVariable long id){

        System.out.println("id - ------------" + id);
        return agreementService.getById(id);
    }

    @GetMapping("agreements/all")
    public List<Agreement>getAll(){
        return agreementService.getAll();
    }

    @PostMapping("createAgreement")
    public  Agreement createAgreement(@RequestBody Agreement agreement){
        return  agreementService.create(agreement);
    }

    @DeleteMapping("deleteArgument/{id}")
    public void deleteAgreement(@PathVariable Long id){
        agreementService.delete(id);
    }

    public Agreement editAgreement(@RequestBody Agreement agreement){
        return agreementService.edit(agreement);
    }


}
