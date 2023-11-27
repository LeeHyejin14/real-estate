package com.zerobase.realestate.service;

import com.zerobase.realestate.dto.UserDto.BrokerSignUpRequest;
import com.zerobase.realestate.dto.UserDto.SignInRequest;
import com.zerobase.realestate.dto.UserDto.UserSignUpRequest;
import com.zerobase.realestate.entity.User;
import com.zerobase.realestate.repository.UserRepository;
import com.zerobase.realestate.security.dto.JwtTokenDto;
import com.zerobase.realestate.security.service.JwtTokenProvider;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Transactional
  public void userSignUp(UserSignUpRequest request) {
    if (userRepository.existsById(request.getId())) {
      throw new DuplicateKeyException("아이디가 이미 존재합니다.");
    }

    User user = User.builder()
        .id(request.getId())
        .password(BCrypt.hashpw(request.getPassword(),
            BCrypt.gensalt()))
        .name(request.getName())
        .phoneNumber(request.getPhoneNumber())
        .build();

    userRepository.save(user);
  }

  @Transactional
  public void brokerSignUp(BrokerSignUpRequest request) {
    if (userRepository.existsById(request.getId())) {
      throw new DuplicateKeyException("아이디가 이미 존재합니다.");
    }

    User user = User.builder()
        .id(request.getId())
        .password(BCrypt.hashpw(request.getPassword(),
            BCrypt.gensalt()))
        .name(request.getName())
        .phoneNumber(request.getPhoneNumber())
        .address(request.getAddress())
        .build();

    userRepository.save(user);
  }

  public JwtTokenDto signIn(SignInRequest request) {
    User user = userRepository.findById(request.getId())
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

    if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("비밀번호를 확인하세요.");
    }

    String refreshToken = jwtTokenProvider.createRefreshToken();

    jwtTokenProvider.updateRefreshToken(user.getId(), refreshToken);

    return JwtTokenDto.createJwtToken(jwtTokenProvider.createAccessToken(user.getUserKey()), refreshToken);
  }

}
