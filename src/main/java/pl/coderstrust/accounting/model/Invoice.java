package pl.coderstrust.accounting.model;

import java.time.LocalDate;
import java.util.List;

public class Invoice {

  private final Integer id;
  private final String identifier;
  private final LocalDate issuedDate;
  private final Company buyer;
  private final Company seller;
  private List<InvoiceEntry> entries;

  public Invoice(Integer id, String identifier, LocalDate issuedDate, Company buyer, Company seller) {
    this.id = id;
    this.identifier = identifier;
    this.issuedDate = issuedDate;
    this.buyer = buyer;
    this.seller = seller;
  }

  public Integer getId() {
    return id;
  }

  public String getIdentifier() {
    return identifier;
  }

  public LocalDate getIssuedDate() {
    return issuedDate;
  }

  public Company getBuyer() {
    return buyer;
  }

  public Company getSeller() {
    return seller;
  }

  public List<InvoiceEntry> getEntries() {
    return entries;
  }

  public void setEntries(List<InvoiceEntry> entries) {
    this.entries = entries;
  }
}