package hu.nye.progkor.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.lowagie.text.DocumentException;
import hu.nye.progkor.ContactService;
import hu.nye.progkor.exception.NotFoundException;
import hu.nye.progkor.export.ContactsToPDF;
import hu.nye.progkor.model.ContactDTO;
import hu.nye.progkor.model.request.ContactRequest;
import hu.nye.progkor.model.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Contact controller handle request and redirect to the correct page.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;
    private final Converter<ContactDTO, ContactResponse> dataToResponse;
    private final Converter<ContactRequest, ContactDTO> requestToDto;

    /**
     * Create contact with GET method.
     */
    @GetMapping(path = "/create.html")
    public String contactsCreatePage(final Model model) {
        model.addAttribute("loginBar", UserController.isLogin());
        return "contacts/create";
    }

    /**
     * Create contact with POST method.
     */
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createContactPage(final Model model, final ContactRequest contactRequest) {
        model.addAttribute("loginBar", UserController.isLogin());
        final ContactResponse contact = dataToResponse.convert(
                contactService.createContact(
                        requestToDto.convert(contactRequest)
                )
        );
        model.addAttribute("success", true);
        return "contacts/create.html";
    }

    /**
     * List contacts.
     */
    @GetMapping(path = "/list.html")
    public String getAllContactsPage(final Model model) {
        model.addAttribute("loginBar", UserController.isLogin());
        final List<ContactResponse> contacts = contactService.getAllContacts().stream()
                .map(dataToResponse::convert)
                .toList();
        model.addAttribute("contacts", contacts);
        return "contacts/list";
    }

    /**
     * Edit contact with GET method.
     */
    @GetMapping(path = "/{id}/edit.html")
    public String updateContactPage(final RedirectAttributes redirectAttributes, final Model model,
                                    final @PathVariable("id") Long id) {
        model.addAttribute("loginBar", UserController.isLogin());
        try {
            final ContactDTO contact = contactService.getContact(id);
            model.addAttribute("contact", contact);
            return "contacts/edit";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("loginBar", UserController.isLogin());
            return "/exception/404.html";
        }
    }

    /**
     * Edit contact with POST method.
     */
    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editContactPage(final RedirectAttributes redirectAttributes,
                                  final Model model,
                                  final @RequestParam(value = "id", required = false) Long id,
                                  final ContactRequest contactRequest
    ) {
        model.addAttribute("loginBar", UserController.isLogin());
        try {
            final ContactResponse contact = dataToResponse.convert(
                    contactService.updateContact(id, requestToDto.convert(contactRequest)));
            return "redirect:/contacts/list.html";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("loginBar", UserController.isLogin());
            return "/exception/nicetry";
        }
    }

    /**
     * Remove contact.
     */
    @GetMapping(path = "/remove/{id}")
    public String removeContact(final RedirectAttributes redirectAttributes,
                                final @PathVariable("id") Long id) {
        redirectAttributes.addAttribute("loginBar", UserController.isLogin());
        try {
            contactService.deleteContact(id);
            redirectAttributes.addFlashAttribute("success", true);
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("success", false);
        }
        return "redirect:/contacts/list.html";
    }

    /**
     * Card page with GET method.
     */
    @GetMapping(path = "/{id}/contactCard.html")
    public String showContactPage(final RedirectAttributes redirectAttributes, final Model model,
                                    final @PathVariable("id") Long id) {
        model.addAttribute("loginBar", UserController.isLogin());
        try {
            final ContactDTO contact = contactService.getContact(id);
            model.addAttribute("contact", dataToResponse.convert(contact));
            return "contacts/contactCard";
        } catch (NotFoundException e) {
            return "/exception/404.html";
        }
    }

    /**
     * Export all contact to pdf.
     */
    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=contacts_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<ContactDTO> listUsers = contactService.getAllContacts();

        ContactsToPDF exporter = new ContactsToPDF(listUsers);
        exporter.export(response);

    }
}
