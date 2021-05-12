package project.altynbaev.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.altynbaev.dao.*;
import project.altynbaev.dto.user.*;
import project.altynbaev.mapper.MapperFacade;
import project.altynbaev.model.Document;
import project.altynbaev.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService  {

    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;
    private final DocumentDao documentDao;
    private final DocRepository docRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, MapperFacade mapperFacade, DocumentDao documentDao, DocRepository docRepository, CountryRepository countryRepository) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.documentDao = documentDao;
        this.docRepository = docRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public UserGetDto findById(int id) {
        User user = userDao.findById(id);
        UserGetDto dto = mapperFacade.map(user, UserGetDto.class);
        dto.setDocName(documentDao.findById(id).getDoc().getDocName());
        dto.setDocNumber(documentDao.findById(id).getDocumentNumber());
        dto.setDocDate(documentDao.findById(id).getDocumentDate());
        dto.setCitizenshipName(user.getCountry().getCountryName());
        dto.setCitizenshipCode(user.getCountry().getCountryCode());

        return dto;
    }

    @Transactional
    @Override
    public void save(UserSaveDto userSaveDTO) {
        User user = mapperFacade.map(userSaveDTO, User.class);
        user.setOffice(officeDao.findById(userSaveDTO.getOfficeId()));
        user.setCountry(countryRepository.findByCountryCode(userSaveDTO.getCitizenshipCode()).get());
        userDao.save(user);
        Document document = new Document();
        document.setUser(user);
        document.setDoc(docRepository.findByDocCode(userSaveDTO.getDocCode()).get());
        document.setDocumentNumber(userSaveDTO.getDocNumber());
        document.setDocumentDate(userSaveDTO.getDocDate());
        documentDao.save(document);
    }

    @Transactional
    @Override
    public void update(UserUpdateDto userUpdateDTO) {
        User user = mapperFacade.map(userUpdateDTO, User.class);
        user.setOffice(officeDao.findById(userUpdateDTO.getOfficeId()));
        Document document = documentDao.findById(userUpdateDTO.getId());
        document.setDoc(docRepository.findByDocName(userUpdateDTO.getDocName()).get());
        document.setDocumentNumber(userUpdateDTO.getDocNumber());
        document.setDocumentDate(userUpdateDTO.getDocDate());
        user.setCountry(countryRepository.findByCountryCode(userUpdateDTO.getCitizenshipCode()).get());
        userDao.update(user, userUpdateDTO.getId());
    }

    @Transactional
    @Override
    public List<UserFilterOutDto> filter(UserFilterInDto userFilterInDTO) {
        User user = mapperFacade.map(userFilterInDTO, User.class);
        user.setOffice(officeDao.findById(userFilterInDTO.getOfficeId()));
        if (userFilterInDTO.getCitizenshipCode() != 0) {
            user.setCountry(countryRepository.findByCountryCode(userFilterInDTO.getCitizenshipCode()).get());
        }
        List<User> list = userDao.filter(user, userFilterInDTO.getDocCode());

        return mapperFacade.mapAsList(list, UserFilterOutDto.class);
    }

}
