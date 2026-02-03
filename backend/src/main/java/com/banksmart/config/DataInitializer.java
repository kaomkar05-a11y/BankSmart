package com.banksmart.config;

import com.banksmart.model.FormField;
import com.banksmart.model.Module;
import com.banksmart.repository.ModuleRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
  private final ModuleRepository moduleRepository;

  public DataInitializer(ModuleRepository moduleRepository) {
    this.moduleRepository = moduleRepository;
  }

  @Override
  public void run(String... args) {
    if (moduleRepository.count() > 0) {
      return;
    }

    moduleRepository.saveAll(List.of(
        createAccountOpeningModule(),
        createCashCounterModule(),
        createPassbookModule(),
        createLoanModule(),
        createAtmChequeModule()
    ));
  }

  private Module createAccountOpeningModule() {
    Module module = new Module();
    module.setSlug("account-opening");
    module.setTitle("Account Opening Desk");
    module.setPurpose("Use this desk to open a savings or student account, submit KYC, and get your account number and welcome kit.");
    module.setRequiredDocuments(List.of(
        "Aadhaar card (original + photocopy)",
        "PAN card (original + photocopy)",
        "Two passport size photographs with light background",
        "Address proof if Aadhaar address is outdated (electricity bill, rent agreement, or gas connection receipt)",
        "Student ID or admission letter for student accounts"
    ));
    module.setProcessSteps(List.of(
        "Collect a token and choose 'Account Opening' at the kiosk if available.",
        "Wait for your turn and meet the desk officer.",
        "Fill the account opening form with name, address, nominee, and occupation details.",
        "Submit KYC documents and provide live signature on the specimen card.",
        "Bank staff enters your details, verifies originals, and takes your photograph if required.",
        "Receive the account number, passbook request slip, and schedule for debit card delivery."
    ));
    module.setCommonMistakes(List.of(
        "Signature mismatch between form and Aadhaar or PAN.",
        "Leaving nominee details blank or using a nickname instead of full legal name.",
        "Submitting an address proof that is older than three months when the bank requires a recent one.",
        "Using multiple handwriting styles on the same form, which triggers manual verification."
    ));
    module.setFormHighlights(List.of(
        "Full Name: Write exactly as on Aadhaar and PAN, including middle name.",
        "Nominee Section: Add nominee name, relationship, and date of birth; avoid abbreviations.",
        "Address: Use house number, landmark, and PIN code. Do not skip PIN.",
        "Occupation: Select one category and write employer/college name clearly.",
        "Specimen Signature: Sign inside the box only; avoid touching borders."
    ));
    module.setFormFields(List.of(
        new FormField("Account Type", "Tick Savings/Student based on eligibility; ask for zero-balance option if eligible.", "Do not tick multiple account types."),
        new FormField("Applicant Name", "Match Aadhaar/PAN spelling exactly; include middle name.", "Avoid initials if your IDs show full name."),
        new FormField("Address & PIN", "Write house number, street, city, and PIN code in full.", "Leaving PIN blank delays KYC verification."),
        new FormField("Nominee Details", "Provide full legal name and relationship with date of birth.", "Skipping nominee forces extra declarations later."),
        new FormField("Specimen Signature", "Sign within the box using the same style as PAN signature.", "Do not overwrite or touch borders.")
    ));
    return module;
  }

  private Module createCashCounterModule() {
    Module module = new Module();
    module.setSlug("cash-transaction");
    module.setTitle("Cash Deposit / Withdrawal Counter");
    module.setPurpose("Use this counter to deposit cash into an account or withdraw cash using a withdrawal slip or cheque.");
    module.setRequiredDocuments(List.of(
        "Passbook or account number",
        "Deposit slip for cash deposit",
        "Withdrawal slip with account number and signature",
        "Cheque leaf (if withdrawing via cheque)",
        "Valid ID if requested for large cash transactions"
    ));
    module.setProcessSteps(List.of(
        "Fill the deposit or withdrawal slip completely before reaching the counter.",
        "Stand in the cash queue and present the slip along with cash or cheque.",
        "Cashier verifies account details, counts cash, and matches signature.",
        "Transaction is posted in the system and a receipt is stamped.",
        "For withdrawals, collect cash and count it at the counter window.",
        "Keep the stamped slip as proof for future reference."
    ));
    module.setCommonMistakes(List.of(
        "Writing the amount in words incorrectly (e.g., missing 'Only').",
        "Leaving the account number blank or incorrect.",
        "Not counting cash before leaving the counter.",
        "Using old or torn deposit slips that are not accepted by the cashier."
    ));
    module.setFormHighlights(List.of(
        "Account Number: Write clearly in block digits, one box per digit.",
        "Amount in Words: Use rupees only, add the word 'Only' at the end.",
        "Denomination Table: Fill exact count of notes for deposits; totals must match.",
        "Signature: Must match the bank's signature record.",
        "Date: Use DD/MM/YYYY format as per the slip."
    ));
    module.setFormFields(List.of(
        new FormField("Account Number", "Copy from passbook or SMS exactly; use one box per digit.", "Incorrect account number causes rejection."),
        new FormField("Amount in Figures", "Write the exact amount in numbers with commas.", "Avoid overwriting or unclear digits."),
        new FormField("Amount in Words", "Write in rupees and end with 'Only'.", "Missing 'Only' can lead to manual correction."),
        new FormField("Denomination Breakdown", "List note counts for each denomination on deposit slips.", "Total must match the amount written above."),
        new FormField("Signature", "Sign as per bank records.", "Signature mismatch pauses the transaction.")
    ));
    return module;
  }

  private Module createPassbookModule() {
    Module module = new Module();
    module.setSlug("passbook-update");
    module.setTitle("Passbook Update Section");
    module.setPurpose("Use the passbook section to print the latest transactions and keep your account history updated.");
    module.setRequiredDocuments(List.of(
        "Passbook or printed account statement request",
        "Account number (for manual printing)",
        "Valid ID if passbook is damaged and you need a replacement"
    ));
    module.setProcessSteps(List.of(
        "Insert the passbook into the update machine, face-up and aligned.",
        "Wait while the machine prints the latest transactions.",
        "If the machine is unavailable, take a token and approach the passbook desk.",
        "For replacement, submit a written request with a small fee if applicable.",
        "Check that the updated lines are readable before leaving."
    ));
    module.setCommonMistakes(List.of(
        "Inserting the passbook upside down, causing printing on wrong pages.",
        "Skipping updates for months, leading to multiple blank pages being skipped.",
        "Leaving the passbook at the machine without collecting it after printing.",
        "Not reporting faded print immediately, making it harder to reprint."
    ));
    module.setFormHighlights(List.of(
        "Passbook Replacement Form: Mention the old passbook number if visible.",
        "Reason for Replacement: Write 'damaged' or 'lost' clearly and sign.",
        "Contact Number: Provide a reachable mobile number for SMS updates."
    ));
    module.setFormFields(List.of(
        new FormField("Account Number", "Write the savings account number as per passbook.", "Do not use ATM card number."),
        new FormField("Reason for Request", "State 'printing not clear', 'damaged', or 'lost'.", "Avoid vague reasons like 'needed'."),
        new FormField("Registered Mobile", "Enter the mobile number linked to the account.", "Incorrect number delays SMS confirmations."),
        new FormField("Signature", "Sign within the provided box.", "Signature mismatch requires branch verification.")
    ));
    return module;
  }

  private Module createLoanModule() {
    Module module = new Module();
    module.setSlug("loan-department");
    module.setTitle("Loan Department");
    module.setPurpose("Visit the loan department to understand eligibility, submit loan applications, and clarify repayment terms.");
    module.setRequiredDocuments(List.of(
        "Aadhaar and PAN copies",
        "Income proof (salary slips for 3 months or ITR for 2 years)",
        "Bank statements for last 6 months",
        "Employment letter or business registration proof",
        "Property documents for secured loans"
    ));
    module.setProcessSteps(List.of(
        "Discuss loan type and eligibility with the loan officer.",
        "Collect the loan application form and checklist.",
        "Submit documents and fill in income, liabilities, and reference details.",
        "Bank performs credit check and verifies workplace or residence.",
        "Sanction letter is issued with interest rate and EMI schedule.",
        "Sign agreement and receive disbursal as per bank policy."
    ));
    module.setCommonMistakes(List.of(
        "Overstating income or hiding existing EMIs.",
        "Submitting outdated ITR or missing pages of bank statements.",
        "Not checking processing fees and prepayment charges in the sanction letter.",
        "Skipping co-applicant signature when required."
    ));
    module.setFormHighlights(List.of(
        "Loan Amount: Keep it realistic based on income and EMI capacity.",
        "Employer Details: Write full company name, address, and landline if available.",
        "References: Provide two references who can be contacted and know you personally.",
        "Declaration: Read before signing; it confirms accuracy of all details."
    ));
    module.setFormFields(List.of(
        new FormField("Loan Type", "Select home, education, personal, or vehicle based on need.", "Multiple selections delay processing."),
        new FormField("Requested Amount", "Enter amount that matches income and EMI capacity.", "Inflated amount increases rejection risk."),
        new FormField("Monthly Income", "Use net salary or average business income.", "Do not exclude existing EMIs."),
        new FormField("Existing Loans", "List all ongoing EMIs with lender names.", "Hiding liabilities affects credit checks."),
        new FormField("References", "Provide two reachable references with address and phone.", "Avoid same surname without explanation.")
    ));
    return module;
  }

  private Module createAtmChequeModule() {
    Module module = new Module();
    module.setSlug("atm-cheque-services");
    module.setTitle("ATM & Cheque Services");
    module.setPurpose("Use this area for ATM card setup, cheque book requests, and learning safe cheque practices.");
    module.setRequiredDocuments(List.of(
        "Account number and passbook",
        "ATM request form or welcome kit acknowledgement",
        "Cheque book requisition slip or request letter",
        "Valid ID for card PIN reset requests"
    ));
    module.setProcessSteps(List.of(
        "Submit ATM card request and verify mobile number for OTP.",
        "Collect ATM card in sealed envelope or track courier delivery.",
        "Set PIN at the ATM in a secure environment; avoid help from strangers.",
        "For cheque book, submit requisition slip and confirm delivery address.",
        "Use cheque with date, payee name, amount in words, and signature."
    ));
    module.setCommonMistakes(List.of(
        "Sharing ATM PIN with shopkeepers or acquaintances.",
        "Writing cheque date in the past or future beyond validity.",
        "Leaving the payee name blank, which increases fraud risk.",
        "Using initials instead of full signature on cheque leaf."
    ));
    module.setFormHighlights(List.of(
        "ATM Request: Verify registered mobile number for SMS alerts.",
        "Cheque Requisition: Mention account number and leaf count requested.",
        "Cheque Layout: Date on top right, payee name on " + "Pay" + " line, amount in words across the center.",
        "Signature Panel: Sign exactly as per bank records, no overwriting."
    ));
    module.setFormFields(List.of(
        new FormField("ATM Card Request", "Tick if you want debit card and SMS alerts enabled.", "Do not share PIN or request form with others."),
        new FormField("Mobile Number", "Use the number linked to the account for OTP.", "Unregistered numbers cause OTP failures."),
        new FormField("Cheque Book Leaves", "Select 25 or 50 leaves based on usage.", "Requesting too many leaves may need approval."),
        new FormField("Payee Name", "Write full beneficiary name in capital letters.", "Avoid leaving the payee line blank."),
        new FormField("Cheque Date", "Write DD/MM/YYYY within validity.", "Post-dated cheques beyond 3 months can bounce.")
    ));
    return module;
  }
}
