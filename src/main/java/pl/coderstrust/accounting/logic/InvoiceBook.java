package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceEntry;
import pl.coderstrust.accounting.model.validator.InvoiceValidator;
import pl.coderstrust.accounting.model.validator.exception.InvoiceValidationException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class InvoiceBook {

  private final Database database;
  private final InvoiceValidator invoiceValidator;

  public InvoiceBook(Database database, InvoiceValidator invoiceValidator) {
    this.database = database;
    this.invoiceValidator = invoiceValidator;
  }

  public void saveInvoice(Invoice invoice) {
    final Collection<InvoiceValidationException> validationErrors = invoiceValidator.validateInvoiceForSave(
        invoice);
    if (validationErrors.isEmpty()) {
      database.saveInvoice(invoice);
    } else {
      for (InvoiceValidationException exception : validationErrors) {
        exception.printStackTrace();
      }
    }
  }

  public void updateInvoice(Invoice invoice) {
    if (invoice == null) {
      throw new IllegalArgumentException("Invoice to update cannot be null");
    }
    if (invoice.getId() == null) {
      throw new IllegalArgumentException("Invoice to update must have a valid ID");
    }
    Invoice current = database.get(invoice.getId());
    if (current == null) {
      throw new IllegalArgumentException(
          "Cannot update: An invoice with given ID : " + invoice.getId() + " doesn't exist");

    } else {
      Invoice invoiceToUpdate = prepareInvoiceToUpdate(invoice, current);
      Collection<InvoiceValidationException> validationExceptions = invoiceValidator
          .validateInvoiceForUpdate(invoiceToUpdate);
      if (validationExceptions.isEmpty()) {
        database.updateInvoice(invoiceToUpdate);
      } else {
        StringBuilder sb = new StringBuilder("The updated invoice is not correct: ");
        for (InvoiceValidationException exception : validationExceptions) {
          sb.append(exception.getMessage());
          sb.append("\n");
        }
        throw new IllegalArgumentException(sb.toString());
      }
    }
  }

  private Invoice prepareInvoiceToUpdate(Invoice invoice, Invoice current) {
    String identifier = invoice.getIdentifier() == null ? current.getIdentifier() : invoice
        .getIdentifier();
    LocalDate issuedDate =
        invoice.getIssuedDate() == null ? current.getIssuedDate() : invoice.getIssuedDate();
    List<InvoiceEntry> entries =
        invoice.getEntries() == null ? current.getEntries() : invoice.getEntries();
    Company buyer = invoice.getBuyer() == null ? current.getBuyer() : invoice.getBuyer();
    Company seller = invoice.getSeller() == null ? current.getSeller() : invoice.getSeller();
    return new Invoice(invoice.getId(), identifier, issuedDate, buyer, seller,
        entries);
  }

  public void removeInvoice(int id) throws IOException {
    if (database.get(id) != null) {
      database.removeInvoice(id);
    } else {
      throw new IllegalArgumentException(
          "Cannot remove: An invoice with given ID : " + id + " doesn't exist");
    }
  }

  public Collection<Invoice> findInvoices(Invoice searchParams, LocalDate issuedDateFrom,
      LocalDate issuedDateTo) {
    return database.find(searchParams, issuedDateFrom, issuedDateTo);
  }

}
