package pl.coderstrust.accounting.model;

public class CompanyValidator {

  public static void validate(Company company) throws CompanyValidatorException {
    if (company.getName() == null) {
      throw new CompanyValidatorException(
          "Validation of company name field failed. Expected name field filled up, got: ",
          String.valueOf(company.getName()));
    }

    if (company.getTaxId() == null) {
      throw new CompanyValidatorException(
          "Validation of company tax id field failed. Expected tax id field filled up, got: ",
          String.valueOf(company.getTaxId()));
    }

    if (company.getTaxId().toString().length() != 10) {
      throw new CompanyValidatorException(
          "Validation of company tax id field failed. Expected tax id should consist of 10 digits, got: ",
          String.valueOf(company.getTaxId()));
    }

    if (!company.getPostalCode().matches("\\d{2}-\\d{3}")) {
      throw new CompanyValidatorException(
          "Validation of company tax id field failed. Expected tax id should consist of 10 digits, got: ",
          String.valueOf(company.getPostalCode()));
    }

  }

}