package com.thinktank._66daysserver.global.service;

import com.thinktank._66daysserver.domain.admin.dto.CustomOAuth2User;
import com.thinktank._66daysserver.domain.admin.dto.GoogleResponse;
import com.thinktank._66daysserver.domain.admin.dto.OAuth2Response;
import com.thinktank._66daysserver.domain.admin.dto.UserDTO;
import com.thinktank._66daysserver.domain.users.model.Users;
import com.thinktank._66daysserver.domain.users.repository.UsersRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsersRepository userRepository;

    public CustomOAuth2UserService(UsersRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

        } else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());

        } else {

            return null;
        }

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        Users existData = userRepository.findByUsername(username);

        if (existData == null) {

            Users users = new Users();
            users.setUserID(users.getUserID());
            users.setEmail(oAuth2Response.getEmail());
            users.setName(oAuth2Response.getName());
            users.setRole("ROLE_USER");

            userRepository.save(users);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");


            return new CustomOAuth2User(userDTO);


        } else {

            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());

            userRepository.save(existData);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(String.valueOf(existData.getUserID()));
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());

            return new CustomOAuth2User(userDTO);

        }

    }

}
