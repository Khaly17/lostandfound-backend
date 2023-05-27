package sn.work.lostandfound.objet;

import org.hibernate.Hibernate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import sn.work.lostandfound.SemanticSimilarity.SemanticSimilarity;
import sn.work.lostandfound.SemanticSimilarity.SemanticSimilarityScore;
import sn.work.lostandfound.constant.Constant;
import sn.work.lostandfound.correspondence.Correspondence;
import sn.work.lostandfound.correspondence.CorrespondenceRepository;
import sn.work.lostandfound.correspondence.CorrespondenceServiceImpl;
import sn.work.lostandfound.myException.NotFoundException;
import sn.work.lostandfound.notification.*;
import sn.work.lostandfound.payment.*;
import sn.work.lostandfound.person.Person;
import sn.work.lostandfound.person.PersonConverter;
import sn.work.lostandfound.person.PersonDto;
import sn.work.lostandfound.person.PersonRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObjetServiceImpl implements ObjetService{
    private final ObjetRepository objetRepository;
    private final ObjetConverter objetConverter;
    private final CorrespondenceServiceImpl correspondenceService;
    private final CorrespondenceRepository correspondenceRepository;
    private final NotificationRepository notificationRepository;
    private final PaymentServiceImpl paymentService;
    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedDate = currentDate.format(formatter);

    public ObjetServiceImpl(
            ObjetRepository objetRepository,
            ObjetConverter objetConverter,
            CorrespondenceServiceImpl correspondenceService,
            CorrespondenceRepository correspondenceRepository,
            NotificationRepository notificationRepository,
            PaymentServiceImpl paymentService,
            PersonRepository personRepository,
            PersonConverter personConverter
    ) {
        this.objetRepository = objetRepository;
        this.objetConverter = objetConverter;
        this.correspondenceService = correspondenceService;
        this.correspondenceRepository = correspondenceRepository;
        this.notificationRepository = notificationRepository;
        this.paymentService = paymentService;
        this.personRepository = personRepository;
        this.personConverter = personConverter;
    }

@Override
    public ResponseEntity<?> addObjet(ObjetDto objetDto) {
       if (objetDto.getObjetNumber() != null){
           if (objetRepository.existsByObjetNumberAndStatus(objetDto.getObjetNumber(),objetDto.getStatus())){
               return ResponseEntity.status(HttpStatus.CONFLICT)
                       .body("Cet numéro est déjà utilisé.");
           }
       }

        Objet objet = objetConverter.convertToEntity(objetDto);
//        LocalDateTime formattedDate = LocalDateTime.now();
        objet.setCreatedDate(formattedDate);
        objet.setUpdatedDate(formattedDate);

        Objet objetSaved = objetRepository.save(objet);
        processCorrespondence(objetSaved);
        ObjetDto savedObjetDto = objetConverter.convertToDto(objetSaved);
        return ResponseEntity.ok(savedObjetDto);

//        try {
//        } catch (DataIntegrityViolationException e) {
//            String errorMessage = "L'objet avec le numéro " + objetDto.getObjetNumber() + " existe déjà.";
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
//        }
    }


    private void processCorrespondence(Objet objet) {
        List<ObjetDto> objetDtoList = findAllObjets();
        Correspondence correspondence = getBestCorrespondence(objet, objetDtoList);
        if (correspondence != null) {
            correspondenceRepository.save(correspondence);
            pushNotification(correspondence.getUserIdFound(), correspondence.getObjetFoundId());
            pushNotification(correspondence.getUserIdLost(), correspondence.getObjetLostId());
        }
    }
//    private Correspondence getBestCorrespondence(Objet objetSaved, List<ObjetDto> objetDtoList) {
//        double bestScore = 0.0;
//        Correspondence correspondence = null;
//        if (objetDtoList.size() == 1 && objetSaved.getStatus() == false){
//
//            Payment payment = new Payment();
//            Invoice invoice = new Invoice();
//            Store store = new Store();
//
//            invoice.setTotal_amount(Constant.PAYMENT_AMOUNT);
//            invoice.setDescription(Constant.INVOICE_DESCRIPTION);
//            store.setName(objetSaved.getObjetName());
//            payment.setInvoice(invoice);
//            payment.setStore(store);
//
//            PaymentResponse paymentResponse = paymentService.addPayment(payment);
//            objetSaved.setToken(paymentResponse.getToken());
//            updateObjet(objetSaved.getId(), objetConverter.convertToDto(objetSaved));
//        }
//        for (ObjetDto objetDto : objetDtoList) {
//            if (objetDto.getId().equals(objetSaved.getId())) {
//                continue;
//            }
//
//            SemanticSimilarity similarity = new SemanticSimilarity();
//
//            if (objetSaved.getStatus()) {
//                objetSaved.setToken("NO_TOKEN");
//                similarity.setObj_found(objetSaved);
//                similarity.setObj_lost(objetConverter.convertToEntity(objetDto));
//            } else {
//                Payment payment = new Payment();
//                Invoice invoice = new Invoice();
//                Store store = new Store();
//
//                invoice.setTotal_amount(Constant.PAYMENT_AMOUNT);
//                invoice.setDescription(Constant.INVOICE_DESCRIPTION);
//                store.setName(objetSaved.getObjetName());
//                payment.setInvoice(invoice);
//                payment.setStore(store);
//
//                PaymentResponse paymentResponse = paymentService.addPayment(payment);
//                objetSaved.setToken(paymentResponse.getToken());
//                updateObjet(objetSaved.getId(), objetConverter.convertToDto(objetSaved));
//                similarity.setObj_lost(objetSaved);
//                similarity.setObj_found(objetConverter.convertToEntity(objetDto));
//            }
//
//            SemanticSimilarityScore semanticSimilarityScore = correspondenceService.getScore(similarity);
//
//            if (bestScore < semanticSimilarityScore.getSimilarity_score()) {
//                bestScore = semanticSimilarityScore.getSimilarity_score();
//                correspondence = new Correspondence();
//                correspondence.setCorrespondenceDate(new Date());
//
//                if (objetSaved.getStatus()) {
//                    ResponseEntity<?> responseEntityFound = getPersonByObjetId(objetSaved.getId(), true);
//                    ResponseEntity<?> responseEntityLost = getPersonByObjetId(objetDto.getId(), true);
//
//                    if (responseEntityFound.getStatusCode() == HttpStatus.OK &&
//                            responseEntityLost.getStatusCode() == HttpStatus.OK) {
//                        PersonDto personFound = (PersonDto) responseEntityFound.getBody();
//                        PersonDto personLost = (PersonDto) responseEntityLost.getBody();
//
//                        correspondence.setUserIdFound(personFound.getId());
//                        correspondence.setObjetFoundId(objetSaved.getId());
//                        correspondence.setUserIdLost(personLost.getId());
//                        correspondence.setObjetLostId(objetDto.getId());
//                    }
//                } else {
//                    ResponseEntity<?> responseEntityFound = getPersonByObjetId(objetDto.getId(), true);
//                    ResponseEntity<?> responseEntityLost = getPersonByObjetId(objetSaved.getId(), true);
//
//                    if (responseEntityFound.getStatusCode() == HttpStatus.OK &&
//                            responseEntityLost.getStatusCode() == HttpStatus.OK) {
//                        PersonDto personFound = (PersonDto) responseEntityFound.getBody();
//                        PersonDto personLost = (PersonDto) responseEntityLost.getBody();
//
//                        correspondence.setUserIdFound(personFound.getId());
//                        correspondence.setObjetFoundId(objetDto.getId());
//                        correspondence.setUserIdLost(personLost.getId());
//                        correspondence.setObjetLostId(objetSaved.getId());
//                    }
//                }
//
//                correspondence.setCorrespondenceStatus(Constant.CORRESPONDENCE_STATUS_SUCCESS);
//                correspondence.setCorrespondenceScore(bestScore);
//                correspondence.setCorrespondenceDate(new Date());
//            }
//        }
//
//        return correspondence;
//    }

    private Correspondence getBestCorrespondence(Objet objetSaved, List<ObjetDto> objetDtoList) {
        double bestScore = 0.0;
        Correspondence correspondence = null;

        if (objetDtoList.size() == 1 && !objetSaved.getStatus()) {
            createPaymentAndToken(objetSaved);
        }

        for (ObjetDto objetDto : objetDtoList) {
            if (objetDto.getId().equals(objetSaved.getId())) {
                continue;
            }

            SemanticSimilarity similarity = new SemanticSimilarity();

            if (objetSaved.getStatus()) {
                objetSaved.setToken("NO_TOKEN");
                similarity.setObj_found(objetSaved);
                similarity.setObj_lost(objetConverter.convertToEntity(objetDto));
            } else {
                createPaymentAndToken(objetSaved);
                similarity.setObj_lost(objetSaved);
                similarity.setObj_found(objetConverter.convertToEntity(objetDto));
            }

            SemanticSimilarityScore semanticSimilarityScore = correspondenceService.getScore(similarity);

            if (bestScore < semanticSimilarityScore.getSimilarity_score()) {
                bestScore = semanticSimilarityScore.getSimilarity_score();
                correspondence = new Correspondence();
                correspondence.setCorrespondenceDate(new Date());

                ResponseEntity<?> responseEntityFound;
                ResponseEntity<?> responseEntityLost;

                if (objetSaved.getStatus()) {
                    responseEntityFound = getPersonByObjetId(objetSaved.getId(), true);
                    responseEntityLost = getPersonByObjetId(objetDto.getId(), true);
                    correspondence.setObjetFoundId(objetSaved.getId());
                    correspondence.setObjetLostId(objetDto.getId());
                } else {
                    responseEntityFound = getPersonByObjetId(objetDto.getId(), true);
                    responseEntityLost = getPersonByObjetId(objetSaved.getId(), true);
                    correspondence.setObjetFoundId(objetDto.getId());
                    correspondence.setObjetLostId(objetSaved.getId());
                }

                if (responseEntityFound.getStatusCode() == HttpStatus.OK &&
                        responseEntityLost.getStatusCode() == HttpStatus.OK) {
                    PersonDto personFound = (PersonDto) responseEntityFound.getBody();
                    PersonDto personLost = (PersonDto) responseEntityLost.getBody();

                    correspondence.setUserIdFound(personFound.getId());
                    correspondence.setUserIdLost(personLost.getId());
                }

                correspondence.setCorrespondenceStatus(Constant.CORRESPONDENCE_STATUS_SUCCESS);
                correspondence.setCorrespondenceScore(bestScore);
                correspondence.setCorrespondenceDate(new Date());
            }
        }

        return correspondence;
    }

    private void createPaymentAndToken(Objet objet) {
        Payment payment = new Payment();
        Invoice invoice = new Invoice();
        Store store = new Store();

        invoice.setTotal_amount(Constant.PAYMENT_AMOUNT);
        invoice.setDescription(Constant.INVOICE_DESCRIPTION);
        store.setName(objet.getObjetName());
        payment.setInvoice(invoice);
        payment.setStore(store);

        PaymentResponse paymentResponse = paymentService.addPayment(payment);
        objet.setToken(paymentResponse.getToken());
        updateObjet(objet.getId(), objetConverter.convertToDto(objet));
    }

    public void pushNotification(Long userId, Long objId){
        ObjetDto obj = objetRepository.findById(objId).map(objetConverter::convertToDto).get();
        Notification notification = new Notification();

        notification.setUserId(userId);
        notification.setObjId(objId);
        notification.setCreatedAt(new Date());

        if(obj.getStatus()){
            notification.setMotif(Constant.OBJET_FOUND_MOTIF);
            notification.setDescription(Constant.OBJET_FOUND_DESCRIPTION+obj.getDescription());
        }else{
            notification.setMotif(Constant.OBJET_LOST_MOTIF);
            notification.setDescription(Constant.OBJET_LOST_DESCRIPTION+obj.getDescription());
        }
        notificationRepository.save(notification);
    }

    @Override
    public ObjetDto updateObjet(Long id, ObjetDto objetDto) {
        Objet objetToUpdate = objetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Objet not found with id " + id));

        objetToUpdate.setObjetNumber(objetDto.getObjetNumber());
        objetToUpdate.setObjetName(objetToUpdate.getObjetName());
        objetToUpdate.setStatus(objetDto.getStatus());
        objetToUpdate.setPhoto(objetDto.getPhoto());
        objetToUpdate.setUpdatedDate(formattedDate);
        objetToUpdate.setDescription(objetDto.getDescription());

        Objet objetSaved = objetRepository.save(objetToUpdate);
        return objetConverter.convertToDto(objetSaved);
    }
    @Override
    public void deleteObjet(Long id) {
        objetRepository.deleteById(id);
    }

    @Override
    public List<ObjetDto> findAllObjets() {
        List<Objet> objets = objetRepository.findAll();
        return objets.stream().map(objetConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ObjetDto findObjetById(Long id) {
        return null;
    }

    @Override
    public List<ObjetDto> findObjetByStatus(Boolean status) {
        return null;
    }

    @Override
    public List<ObjetDto> findObjetsByPerson(Long personId) {
        return null;
    }

    @Override
    public Optional<ObjetDto> findObjetByNumber(String objetNumber) {
        return Optional.empty();
    }

    public ResponseEntity<?> getPersonByObjetNumber(String objetNumber) {
        Objet objet = objetRepository.findByObjetNumber(objetNumber)
                .orElseThrow(() -> new IllegalArgumentException("L'objet avec le numéro spécifié n'a pas été trouvé."));

        boolean paymentVerified = verifyPayment(objet.getToken());

        if (paymentVerified) {
            Person person = objet.getPerson();
            return ResponseEntity.ok(person);
        } else {
            String errorMessage = "Vous devez d'abord finaliser le paiement.";
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errorMessage);
        }
    }
    public ResponseEntity<?> getPersonByObjetId(Long objetId, Boolean add) {
        Objet objet = objetRepository.findById(objetId)
                .orElseThrow(() -> new IllegalArgumentException("L'objet avec l'ID spécifié n'a pas été trouvé."));
        boolean paymentVerified = add || verifyPayment(objet.getToken());

        if (paymentVerified) {
            Person person = objet.getPerson();
            Hibernate.initialize(person);
            return ResponseEntity.ok(personConverter.convertToDto(person));
        } else {
            String errorMessage = "Vous devez d'abord finaliser le paiement.";
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errorMessage);
        }
    }

    private boolean verifyPayment(String token) {
        try {
            ConfirmPayment confirmPayment = paymentService.verifyPayment(token);
            if (confirmPayment.getStatus() == Constant.PAYMENT_STATUS_SUCCESS) {
                return true;
            } else {
                throw new IllegalStateException("Vous devez d'abord finaliser le paiement.");
            }
        } catch (IllegalStateException e) {
            String errorMessage = e.getMessage();
            return false;
        } catch (Exception e) {
            String errorMessage = "Une erreur s'est produite lors de la vérification du paiement.";
            System.out.println(errorMessage);
            return false;
        }
    }

}
