package com.pial.gym.gymapi.service.impl;

import com.pial.gym.gymapi.dto.model.User;
import com.pial.gym.gymapi.dto.request.UsersGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.UserCreateRequest;
import com.pial.gym.gymapi.entity.CompanyEntity;
import com.pial.gym.gymapi.entity.RoleEntity;
import com.pial.gym.gymapi.entity.UserEntity;
import com.pial.gym.gymapi.repository.ICompanyRepository;
import com.pial.gym.gymapi.repository.IRoleRepository;
import com.pial.gym.gymapi.repository.IUserRepository;
import com.pial.gym.gymapi.service.IUserService;
import com.pial.gym.gymapi.specification.UserSpecification;
import com.pial.gym.gymapi.utils.DateUtils;
import com.pial.gym.gymapi.utils.ModelConvertionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IRoleRepository iRoleRepository;
    @Autowired
    private ICompanyRepository iCompanyRepository;
    @Autowired
    private DateUtils dateUtils;
    @Autowired
    private MessageSource messageSource;

    @Override
    public User getDetail(Integer id) {
        User user = new User();
        try {
            user = ModelConvertionUtils.getUser(iUserRepository.findById(id).orElseThrow(() ->
                    new Exception(messageSource.getMessage(
                            "msg.user.find.notfound", null, LocaleContextHolder.getLocale()))));
        } catch (Exception ex) {
            log.error("Error user detail: {}", ex.getMessage());
        }
        return user;
    }

    @Override
    public String create(UserCreateRequest request) {
        String message = messageSource.getMessage("msg.user.creation.success", null, LocaleContextHolder.getLocale());
        try {
            UserEntity existingUserEntity = iUserRepository.findByUsernameOrEmail(request.getUsername(), request.getEmail()).orElse(null);
            if (existingUserEntity == null) {
                RoleEntity role = iRoleRepository.findById(request.getRoleId()).orElseThrow(() ->
                        new Exception(messageSource.getMessage(
                                "msg.role.find.notfound", null, LocaleContextHolder.getLocale())));
                CompanyEntity company = iCompanyRepository.findById(request.getCompanyId()).orElseThrow(() ->
                        new Exception(messageSource.getMessage(
                                "msg.company.find.notfound", null, LocaleContextHolder.getLocale())));
                UserEntity userEntity = getUserEntity(request, role, company);
                iUserRepository.saveAndFlush(userEntity);
            }
        } catch (Exception ex) {
            log.error("Error create user: {}", ex.getMessage());
            message = messageSource.getMessage("msg.user.creation.error", null, LocaleContextHolder.getLocale());
        }
        return message;
    }

    @Override
    public Slice<User> getAllByFilter(UsersGetAllByFilterRequest request) {
        Specification<UserEntity> specification = getSpecificationFilter(request);

        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Slice<UserEntity> userEntitySlice = iUserRepository.findAll(specification, pageable);
        List<User> userList = new ArrayList<>();
        userEntitySlice.forEach(userEntity -> {
            userList.add(ModelConvertionUtils.getUser(userEntity));
        });

        boolean hasNext = userList.size() > pageable.getPageSize();
        List<User> sliceContent = hasNext ? userList.subList(0, pageable.getPageSize()) : userList;

        return new SliceImpl<>(sliceContent, pageable, hasNext);
    }

    private Specification<UserEntity> getSpecificationFilter(UsersGetAllByFilterRequest request) {
        Specification<UserEntity> specification = Specification.unrestricted();
        if (request.getUsername() != null) {
            specification = specification.and(UserSpecification.findByUsername(request.getUsername()));
        }
        if (request.getName() != null) {
            specification = specification.and(UserSpecification.findByName(request.getName()));
        }
        if (request.getLastName() != null) {
            specification = specification.and(UserSpecification.findByLasName(request.getLastName()));
        }
        if (request.getEmail() != null) {
            specification = specification.and(UserSpecification.findByEmail(request.getEmail()));
        }
        if (request.getGender() != null) {
            specification = specification.and(UserSpecification.findByGender(request.getGender()));
        }
        if (request.getRoleId() != null) {
            specification = specification.and(UserSpecification.findByRole(request.getRoleId()));
        }
        if (request.getCompanyId() != null) {
            specification = specification.and(UserSpecification.findByCompany(request.getCompanyId()));
        }
        if (request.getStatus() != null) {
            specification = specification.and(UserSpecification.findByStatus(request.getStatus()));
        }
        if (request.getIsFilterCreationDate() != null) {
            if (request.getIsFilterCreationDate()) {
                Date startDate = dateUtils.convertStringToDate(request.getFromDate());
                Date endDate = dateUtils.convertStringToDate(request.getToDate());
                specification = specification.and(UserSpecification.findByCreationDate(startDate, endDate));
            }
        }
        if (request.getIsFilterModificationDate() != null) {
            if (request.getIsFilterModificationDate()) {
                Date startDate = dateUtils.convertStringToDate(request.getFromDate());
                Date endDate = dateUtils.convertStringToDate(request.getToDate());
                specification = specification.and(UserSpecification.findByModificationDate(startDate, endDate));
            }
        }

        return specification;
    }

    private static UserEntity getUserEntity(UserCreateRequest request, RoleEntity role, CompanyEntity company) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(request.getPassword());
        userEntity.setName(request.getName());
        userEntity.setLastName(request.getLastName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPhone(request.getPhone());
        userEntity.setGender(request.getGender());
        userEntity.setRole(role);
        userEntity.setCompany(company);
        userEntity.setStatus(true);
        userEntity.setCreationDate(new Date());
        userEntity.setModificationDate(new Date());
        return userEntity;
    }
}
