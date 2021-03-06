package hu.nye.progkor.export;


import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import hu.nye.progkor.model.ContactDto;

/**
 * Export contacts to PDF file.
 */
public class ContactsToPdf {

  private List<ContactDto> contacts;

  public ContactsToPdf(List<ContactDto> contacts) {
    this.contacts = contacts;
  }

  private void writeTableHeader(PdfPTable table) {
    PdfPCell cell = new PdfPCell();
    cell.setBackgroundColor(Color.GRAY);
    cell.setPadding(5);

    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    font.setColor(Color.WHITE);

    cell.setPhrase(new Phrase("ID", font));
    table.addCell(cell);

    cell.setPhrase(new Phrase("Név", font));
    table.addCell(cell);

    cell.setPhrase(new Phrase("Telefonszám", font));
    table.addCell(cell);

    cell.setPhrase(new Phrase("E-mail cím", font));
    table.addCell(cell);

    cell.setPhrase(new Phrase("Lakcím", font));
    table.addCell(cell);

  }

  private void writeTableData(PdfPTable table) {
    for (ContactDto dto : contacts) {
      table.addCell(String.valueOf(dto.id()));
      table.addCell(dto.firstName() + " " + dto.lastName());
      table.addCell(String.valueOf(dto.phoneNumber()));
      table.addCell(String.valueOf(dto.emailAddress()));
      table.addCell(String.valueOf(dto.address()));
    }
  }

  /**
   * Export all contacts in PDF.
   * Create header and body for table.
   */
  public void export(HttpServletResponse response) throws DocumentException, IOException {
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, response.getOutputStream());

    document.open();
    Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    font.setSize(18);
    font.setColor(Color.GRAY);

    Paragraph p = new Paragraph("Összes névjegy", font);
    p.setAlignment(Paragraph.ALIGN_CENTER);

    document.add(p);

    PdfPTable table = new PdfPTable(5);
    table.setWidthPercentage(100f);
    table.setWidths(new float[]{0.5f, 3.5f, 2f, 3.0f, 2f});
    table.setSpacingBefore(10);

    writeTableHeader(table);
    writeTableData(table);

    document.add(table);

    document.close();

  }
}
