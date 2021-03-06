package pl.coderstrust.accounting.model.validator;

import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.validator.exception.CompanyValidationException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class CompanyValidator {

  public Collection<CompanyValidationException> validate(Company company) {
    List<CompanyValidationException> validationExceptions = new LinkedList<>();
    if (company.getName() == null) {
      validationExceptions.add(new CompanyValidationException(
          "Validation of company name field failed. Expected name field not empty, got: "
              + String.valueOf(company.getName())));
    }

    if (company.getTaxId() == null) {
      validationExceptions.add(new CompanyValidationException(
          "Validation of company tax id field failed. Expected tax id field not empty, got: "
              + String.valueOf(company.getTaxId())));
    } else {
      if (company.getTaxId().length() != 10) {
        validationExceptions.add(new CompanyValidationException(
            "Validation of company tax id field failed. Expected tax id should consist of 10 digits, got: "
                + String.valueOf(company.getTaxId())));
      }
    }
    if (company.getPostalCode() == null || !company.getPostalCode().matches("\\d{2}-\\d{3}")) {
      validationExceptions.add(new CompanyValidationException(
          "Validation of company postal code field failed : "
              + String.valueOf(company.getPostalCode())));
    }

    if (company.getStreetAndNumber() == null) {
      validationExceptions.add(new CompanyValidationException(
          "Validation of company street and number field failed. Expected street and number field "
              + "not empty, got: " + String.valueOf(company.getStreetAndNumber())));
    }

    if (company.getLocation() == null) {
      validationExceptions.add(new CompanyValidationException(
          "Validation of company location field failed. Expected location field not empty, got: "
              + String.valueOf(company.getStreetAndNumber())));
    }
    return validationExceptions;
  }
}