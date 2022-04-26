package hu.nye.progkor.controller;

import java.util.List;

import hu.nye.progkor.ContactService;
import hu.nye.progkor.model.ContactDTO;
import hu.nye.progkor.model.request.ContactRequest;
import hu.nye.progkor.model.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Rest Controller.
 */
@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/contacts")
public class ContactRestController {

    private final ContactService contactService;
    private final Converter<ContactDTO, ContactResponse> contactDtoToResponseConverter;
    private final Converter<ContactRequest, ContactDTO> contactRequestToDtoConverter;

    /**
     * Get all contact.
     */
    @GetMapping
    public List<ContactResponse> getContacts() {
        return contactService.getAllContacts().stream()
                .map(contactDtoToResponseConverter::convert)
                .toList();
    }

    /**
     * Get contact which has id in param.
     */
    @GetMapping("/{id}")
    public ContactResponse getContact(final @PathVariable Long id) {
        return contactDtoToResponseConverter.convert(contactService.getContact(id));
    }

    /**
     * Create contact.
     */
    @PostMapping
    public ContactResponse createContact(final @RequestBody ContactRequest contactRequest) {
        return contactDtoToResponseConverter.convert(
                contactService.createContact(contactRequestToDtoConverter.convert(contactRequest))
        );
    }

    /**
     * Update contact.
     */
    @PutMapping("/{id}")
    public ContactResponse updateContact(final @PathVariable Long id, final @RequestBody ContactRequest contactRequest) {
        return contactDtoToResponseConverter.convert(
                contactService.updateContact(id, contactRequestToDtoConverter.convert(contactRequest))
        );
    }

    /**
     * Delete contact.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(final @PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

